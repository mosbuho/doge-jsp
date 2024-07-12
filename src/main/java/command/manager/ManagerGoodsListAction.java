package command.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;
import process.CommandAction;

public class ManagerGoodsListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		GoodsDAO gdao = GoodsDAO.getInstance();
		ArrayList<GoodsBean> goodsList = gdao.getGoodsList(null);
		request.setAttribute("goodsList", goodsList);
		return "manager/managerGoodsList.jsp";
	}
}