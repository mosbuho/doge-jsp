package command.purchase;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PurchaseBean;
import dao.PurchaseDAO;
import process.CommandAction;

public class PurchaseListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = (int) request.getSession().getAttribute("member_id");
		PurchaseDAO pdao = PurchaseDAO.getInstance();
		HashMap<String, ArrayList<PurchaseBean>> purchaseMap = pdao.getPurchaseList(member_id);
		request.setAttribute("purchaseMap", purchaseMap);
		return "/purchase/purchaseList.jsp";
	}
}