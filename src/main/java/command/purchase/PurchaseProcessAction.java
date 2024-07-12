package command.purchase;

import java.io.BufferedReader;
import java.util.UUID;

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

		int pdaoResult = 0;
		int gdaoResult = 0;

		JSONObject jsonRequest = new JSONObject(sb.toString());
		int member_id = jsonRequest.getInt("member_id");
		int goods_id = jsonRequest.getInt("goods_id");
		int quantity = jsonRequest.getInt("quantity");
		int total_usd = jsonRequest.getInt("total_usd");
		int total_doge = jsonRequest.getInt("total_doge");
		String name = jsonRequest.getString("name");
		String addr = jsonRequest.getString("addr");

		PurchaseDAO pdao = PurchaseDAO.getInstance();
		pdaoResult = pdao.purchase(member_id, goods_id, quantity, name, addr, total_usd, total_doge,
				UUID.randomUUID().toString());

		GoodsDAO gdao = GoodsDAO.getInstance();
		gdaoResult = gdao.purchaseGoods(goods_id, quantity);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", pdaoResult != 0 && gdaoResult != 0);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}