package command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QuestionBean;
import dao.QuestionDAO;
import process.CommandAction;

public class ManagerAnswerFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			int question_id = Integer.parseInt(request.getParameter("question_id"));
			QuestionDAO qdao = QuestionDAO.getInstance();
			QuestionBean question = qdao.getQuestion(question_id);
			request.setAttribute("question", question);
			return "manager/managerAnswerForm.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}