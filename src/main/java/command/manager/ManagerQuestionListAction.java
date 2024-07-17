package command.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QuestionBean;
import dao.QuestionDAO;
import process.CommandAction;

public class ManagerQuestionListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			QuestionDAO qdao = QuestionDAO.getInstance();
			ArrayList<QuestionBean> questionList = qdao.getAllQuestions();
			request.setAttribute("questionList", questionList);
			return "manager/managerQuestionList.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}