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

		JSONObject jsonRequest = new JSONObject(sb.toString());
		int member_id = jsonRequest.getInt("member_id");
		int goods_id = jsonRequest.getInt("goods_id");
		int quantity = jsonRequest.getInt("quantity");
		String addr = jsonRequest.getString("addr");

		PurchaseDAO pdao = PurchaseDAO.getInstance();
		pdao.purchase(member_id, goods_id, quantity, addr, UUID.randomUUID().toString());

		GoodsDAO gdao = GoodsDAO.getInstance();
		gdao.purchaseGoods(goods_id, quantity);

		return null;
	}
}