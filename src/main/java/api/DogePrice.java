package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class DogePrice {
	private static DogePrice instance = new DogePrice();

	public static DogePrice getInstance() {
		return instance;
	}

	private DogePrice() {
	}

	public double getDogePrice() throws Exception {
		URL url = new URL(
				"https://api.diadata.org/v1/assetQuotation/Dogechain/0x0000000000000000000000000000000000000000");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
			StringBuilder sb = new StringBuilder();
			String output = null;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			conn.disconnect();
			JSONObject jsonObj = new JSONObject(sb.toString());
			return Math.round(1 / (Math.round(jsonObj.getDouble("Price") * 10000.0) / 10000.0) * 100.0) / 100.0;
		}
	}
}
