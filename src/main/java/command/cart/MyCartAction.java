package command.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.DogePrice;
import bean.CartBean;
import bean.MemberBean;
import dao.CartDAO;
import dao.MemberDAO;
import process.CommandAction;

public class MyCartAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = (int) request.getSession().getAttribute("member_id");

		CartDAO cdao = CartDAO.getInstance();
		ArrayList<CartBean> cartList = cdao.getCartList(member_id);

		DogePrice dp = DogePrice.getInstance();
		double tmpPrice = dp.getDogePrice();

		MemberDAO mdao = MemberDAO.getInstance();
		MemberBean member = mdao.getMember(member_id);

		request.setAttribute("tmpPrice", tmpPrice);
		request.setAttribute("cartList", cartList);
		request.setAttribute("member", member);
		return "/cart/myCart.jsp";
	}
}