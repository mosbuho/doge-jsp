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
		String name = jsonRequest.getString("name");
		String addr = jsonRequest.getString("addr");
		JSONArray goodsList = jsonRequest.getJSONArray("goodsList");

		PurchaseDAO pdao = PurchaseDAO.getInstance();
		GoodsDAO gdao = GoodsDAO.getInstance();

		int pdaoResult = 0;
		int gdaoResult = 0;
		int cdaoResult = 0;

		for (int i = 0; i < goodsList.length(); i++) {
			JSONObject item = goodsList.getJSONObject(i);
			int goods_id = item.getInt("goods_id");
			int quantity = item.getInt("quantity");
			int total_usd = item.getInt("total_usd");
			int total_doge = item.getInt("total_doge");
			pdaoResult = pdao.purchase(member_id, goods_id, quantity, name, addr, total_usd, total_doge,
					UUID.randomUUID().toString());
			gdaoResult = gdao.purchaseGoods(goods_id, quantity);
		}
		CartDAO cdao = CartDAO.getInstance();
		cdaoResult = cdao.delCart(member_id);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", pdaoResult != 0 && gdaoResult != 0 && cdaoResult != 0);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}