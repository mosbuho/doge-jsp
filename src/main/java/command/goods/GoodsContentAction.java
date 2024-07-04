package command.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.DogePrice;
import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class GoodsContentAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		GoodsDAO gdao = GoodsDAO.getInstance();
		String id = request.getParameter("id");
		GoodsBean goods = gdao.getGoods(Integer.parseInt(id));
		request.setAttribute("goods", goods);

		DogePrice dp = DogePrice.getInstance();
		double tmpPrice = dp.getDogePrice();
		int dogePrice = (int) Math.round(goods.getPrice() * tmpPrice);

		request.setAttribute("dogePrice", dogePrice);
		return "/goods/goodsContent.jsp";
	}
}