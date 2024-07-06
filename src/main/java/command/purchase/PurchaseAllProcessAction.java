package command.purchase;

import java.io.BufferedReader;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CartDAO;
import dao.GoodsDAO;
import dao.PurchaseDAO;
import process.CommandAction;

public class PurchaseAllProcessAction implements CommandAction {

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
		String addr = jsonRequest.getString("addr");
		JSONArray goodsList = jsonRequest.getJSONArray("goodsList");

		String uuid = UUID.randomUUID().toString();
		PurchaseDAO pdao = PurchaseDAO.getInstance();
		GoodsDAO gdao = GoodsDAO.getInstance();

		int pdaoRes = 0;
		int gdaoRes = 0;
		for (int i = 0; i < goodsList.length(); i++) {
			JSONObject item = goodsList.getJSONObject(i);
			int goods_id = item.getInt("goods_id");
			int quantity = item.getInt("quantity");
			pdaoRes = pdao.purchase(member_id, goods_id, quantity, addr, uuid);
			gdaoRes = gdao.purchaseGoods(goods_id, quantity);
		}
		int cdaoRes = 0;
		CartDAO cdao = CartDAO.getInstance();
		cdaoRes = cdao.delCart(member_id);

		JSONObject jsonResponse = new JSONObject();
		if (pdaoRes == 0 || gdaoRes == 0 || cdaoRes == 0) {
			jsonResponse.put("check", false);
		} else {
			jsonResponse.put("check", true);
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}