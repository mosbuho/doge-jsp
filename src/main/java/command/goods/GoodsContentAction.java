package command.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.DogePrice;
import bean.GoodsBean;
import bean.QuestionBean;
import dao.GoodsDAO;
import dao.QuestionDAO;
import process.CommandAction;

public class GoodsContentAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String goods_id = request.getParameter("goods_id");
		GoodsDAO gdao = GoodsDAO.getInstance();
		GoodsBean goods = gdao.getGoods(Integer.parseInt(goods_id));
		request.setAttribute("goods", goods);

		QuestionDAO qdao = QuestionDAO.getInstance();
		ArrayList<QuestionBean> questionList = qdao.getQuestionsWithAnswers(Integer.parseInt(goods_id));
		request.setAttribute("questionList", questionList);

		DogePrice dp = DogePrice.getInstance();
		int dogePrice = (int) Math.round(goods.getPrice() * dp.getDogePrice());
		request.setAttribute("dogePrice", dogePrice);

		return "/goods/goodsContent.jsp";
	}
}