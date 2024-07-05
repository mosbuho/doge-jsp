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
		String id = request.getParameter("id");
		GoodsDAO gdao = GoodsDAO.getInstance();
		GoodsBean goods = gdao.getGoods(Integer.parseInt(id));
		request.setAttribute("goods", goods);

		DogePrice dp = DogePrice.getInstance();
		int dogePrice = (int) Math.round(goods.getPrice() * dp.getDogePrice());

		request.setAttribute("dogePrice", dogePrice);
		return "/goods/goodsContent.jsp";
	}
}