package command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class ManagerGoodsUpdateFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			int goods_id = Integer.parseInt(request.getParameter("goods_id"));
			GoodsDAO gdao = GoodsDAO.getInstance();
			GoodsBean goods = gdao.getGoods(goods_id);
			request.setAttribute("goods", goods);
			return "manager/managerGoodsUpdateForm.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}