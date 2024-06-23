package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.CommandAction;

@WebServlet(urlPatterns = { "/Controller", "*.do" }, initParams = {
		@WebInitParam(name = "propertyConfig", value = "commandMapping.properties") })

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> commandMap = new HashMap<String, Object>();

	public Controller() {
		super();
	}

	public void init(ServletConfig config) {
		String props = config.getInitParameter("propertyConfig");
		String path = "/property";
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(path) + "\\" + props;

		Properties pr = new Properties();
		try (FileInputStream fis = new FileInputStream(realPath)) {
			pr.load(fis);
		} catch (FileNotFoundException e) {
			System.err.println("파일 찾을 수 없음 : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("파일 읽기 중 에러 : " + e.getMessage());
		}

		Iterator<?> keyIter = pr.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);

			try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			} catch (ClassNotFoundException e) {
				System.err.println("클래스 찾을 수 없음 : " + e.getMessage());
			} catch (InstantiationException e) {
				System.err.println("클래스 인스턴스화 실패 : " + e.getMessage());
			} catch (IllegalAccessException e) {
				System.err.println("클래스 접근 제한으로 인스턴스 생성 실패 : " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("부적절한 인수로 클래스 인스턴스 생성 실패 : " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("클래스 생성자 호출 중 예외 발생 : " + e.getMessage());
			} catch (NoSuchMethodException e) {
				System.err.println("필요한 생성자를 찾을 수 없음 : " + e.getMessage());
			} catch (SecurityException e) {
				System.err.println("보안 예외 발생 : " + e.getMessage());
			} catch (Exception e) {
				System.err.println("예기치 않은 예외 발생 : " + e.getMessage());
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProcess(request, response);
	}

	private void requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		String command = request.getRequestURI();

		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		com = (CommandAction) commandMap.get(command);

		try {
			view = com.requestProcess(request, response);
		} catch (Throwable e) {
			System.err.println("예기치 못한 오류 발생: " + e.getMessage());
		}
		
		request.setAttribute("cont", view);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}