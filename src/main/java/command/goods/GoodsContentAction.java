package command.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class GoodsContentAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		GoodsDAO gdao = GoodsDAO.getInstance();
		String id = request.getParameter("id");
		GoodsBean goods = gdao.getGoods(Integer.parseInt(id));
		request.setAttribute("goods", goods);
		return "/goods/goodsContent.jsp";
	}
}