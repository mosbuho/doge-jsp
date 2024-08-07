package command.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.DogePrice;
import bean.GoodsBean;
import bean.MemberBean;
import dao.GoodsDAO;
import dao.MemberDAO;
import process.CommandAction;

public class PurchaseFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		int member_id = Integer.parseInt(request.getParameter("member_id"));
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		MemberDAO mdao = MemberDAO.getInstance();
		MemberBean member = mdao.getMember(member_id);

		GoodsDAO gdao = GoodsDAO.getInstance();
		GoodsBean goods = gdao.getGoods(goods_id);

		DogePrice dp = DogePrice.getInstance();
		double tmpPrice = dp.getDogePrice();
		int dogePrice = (int) Math.round(goods.getPrice() * tmpPrice);

		request.setAttribute("member", member);
		request.setAttribute("goods", goods);
		request.setAttribute("quantity", quantity);
		request.setAttribute("dogePrice", dogePrice);

		return "/purchase/purchaseForm.jsp";
	}
}