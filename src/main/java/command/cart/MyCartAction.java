package command.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartBean;
import dao.CartDAO;
import process.CommandAction;

public class MyCartAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = (int) request.getSession().getAttribute("member_id");
		CartDAO cdao = CartDAO.getInstance();
		ArrayList<CartBean> cartList = cdao.getCartList(member_id);
		request.setAttribute("cartList", cartList);
		return "/cart/myCart.jsp";
	}
}