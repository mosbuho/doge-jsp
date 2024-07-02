package command.cart;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.CartBean;
import dao.CartDAO;
import process.CommandAction;

public class AddToCartAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = request.getReader()) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		JSONObject jsonRequest = new JSONObject(sb.toString());
		int member_id = jsonRequest.getInt("member_id");
		int goods_id = jsonRequest.getInt("goods_id");
		int quantity = jsonRequest.getInt("quantity");

		CartBean cart = new CartBean(member_id, goods_id, quantity);

		CartDAO cdao = CartDAO.getInstance();
		cdao.addCart(cart);

		return null;
	}
}