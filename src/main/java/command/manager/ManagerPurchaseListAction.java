package command.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PurchaseBean;
import dao.PurchaseDAO;
import process.CommandAction;

public class ManagerPurchaseListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		PurchaseDAO pdao = PurchaseDAO.getInstance();
		ArrayList<PurchaseBean> purchaseList = pdao.getPurchaseListAll();
		request.setAttribute("purchaseList", purchaseList);
		return "manager/managerPurchaseList.jsp";
	}
}
