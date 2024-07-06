package command.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.DogePrice;
import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class GoodsMainAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		GoodsDAO gdao = GoodsDAO.getInstance();
		String category = request.getParameter("category");
		ArrayList<GoodsBean> goodsList = gdao.getGoodsList(category);

		DogePrice dp = DogePrice.getInstance();
		double tmpPrice = dp.getDogePrice();

		request.setAttribute("goodsList", goodsList);
		request.setAttribute("tmpPrice", tmpPrice);

		return "/goods/goodsMain.jsp";
	}
}