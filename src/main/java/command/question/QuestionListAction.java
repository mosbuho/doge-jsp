package command.question;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QuestionBean;
import dao.QuestionDAO;
import process.CommandAction;

public class QuestionListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = (int) request.getSession().getAttribute("member_id");
		QuestionDAO qdao = QuestionDAO.getInstance();
		HashMap<Integer, ArrayList<QuestionBean>> questionList = qdao.getQuestionList(member_id);
		request.setAttribute("questionList", questionList);
		return "/question/questionList.jsp";
	}
}