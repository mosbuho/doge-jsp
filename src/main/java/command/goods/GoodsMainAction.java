package command.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class GoodsMainAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		GoodsDAO gdao = GoodsDAO.getInstance();
		String category = request.getParameter("category");
		ArrayList<GoodsBean> goodsList = gdao.getGoodsList(category);
		request.setAttribute("goodsList", goodsList);
		return "/goods/goodsMain.jsp";
	}
}