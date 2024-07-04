package command.purchase;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.GoodsDAO;
import dao.PurchaseDAO;
import process.CommandAction;

public class PurchaseProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}

		JSONObject jsonRequest = new JSONObject(sb.toString());
		int member_id = jsonRequest.getInt("member_id");
		int goods_id = jsonRequest.getInt("goods_id");
		int quantity = jsonRequest.getInt("quantity");
		String addr = jsonRequest.getString("addr");

		PurchaseDAO pdao = PurchaseDAO.getInstance();
		int pdaoRes = pdao.purchase(member_id, goods_id, quantity, addr);

		GoodsDAO gdao = GoodsDAO.getInstance();
		int gdaoRes = gdao.purchaseGoods(goods_id, quantity);

		JSONObject jsonResponse = new JSONObject();

		if (pdaoRes == 0 || gdaoRes == 0) {
			jsonResponse.put("check", false);
		} else {
			jsonResponse.put("check", true);
		}
		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}