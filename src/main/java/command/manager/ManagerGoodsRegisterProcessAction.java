package command.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class ManagerGoodsRegisterProcessAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			int result = 0;

			request.setCharacterEncoding("UTF-8");

			StringBuilder sb = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}

			JSONObject jsonObject = new JSONObject(sb.toString());
			String title = jsonObject.getString("title");
			String description = jsonObject.getString("description");
			String uploadedImageName = jsonObject.getString("uploadedImageName");
			int price = jsonObject.getInt("price");
			int discount = jsonObject.getInt("discount");
			int quantity = jsonObject.getInt("quantity");
			String category = jsonObject.getString("category");

			GoodsDAO gdao = GoodsDAO.getInstance();
			result = gdao.registerGoods(
					new GoodsBean(title, description, uploadedImageName, price, discount, quantity, category));

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", result != 0);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse.toString());
		}
		return null;
	}
}