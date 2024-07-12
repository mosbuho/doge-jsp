package command.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import process.CommandAction;

@MultipartConfig
public class ImageUploadProcessAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String uploadPath = "C:\\Users\\geonu\\Documents\\eclipse-workspace\\doge-jsp\\src\\main\\webapp\\img\\";
		Part filePart = request.getPart("file");
		String newFileName = UUID.randomUUID().toString() + ".webp";

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		JSONObject jsonResponse = new JSONObject();

		try (InputStream fileContent = filePart.getInputStream();
				OutputStream os = new FileOutputStream(new File(uploadPath, newFileName))) {

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileContent.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			jsonResponse.put("success", true);
			jsonResponse.put("fileName", newFileName);
		} catch (Exception e) {
			jsonResponse.put("success", false);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}