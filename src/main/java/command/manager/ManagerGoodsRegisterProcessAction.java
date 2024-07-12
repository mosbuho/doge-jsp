package command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.CommandAction;

public class ManagerGoodsRegisterProcessAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String uploadedImageName = request.getParameter("uploadedImageName");
			int price = Integer.parseInt(request.getParameter("price"));
			int discount = Integer.parseInt(request.getParameter("discount"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String category = request.getParameter("category");

			return "manager/managerGoodsList.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}