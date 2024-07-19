<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="/doge-jsp/css/managerPurchaseList.css">
<div class="header">
	<h1>주문 목록</h1>
	<a href="/doge-jsp/managerUserList.do">유저 목록</a>
	<a href="/doge-jsp/managerGoodsList.do">상품 목록</a>
	<a href="/doge-jsp/managerQuestionList.do">QnA</a>
</div>
<table border="1">
	<thead>
		<tr>
			<th>주문 ID</th>
			<th>유저 ID</th>
			<th>상품 ID</th>
			<th>수량</th>
			<th>수령인</th>
			<th>배송지</th>
			<th>USD</th>
			<th>DOGE</th>
			<th>배송 상태</th>
			<th>주문일</th>
			<th>주문 ID</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="purchase" items="${purchaseList}">
			<tr>
				<td>${purchase.purchase_id}</td>
				<td>${purchase.member_id}</td>
				<td>${purchase.goods_id}</td>
				<td>${purchase.quantity}</td>
				<td>${purchase.name}</td>
				<td>${purchase.addr}</td>
				<td>
					\$<fmt:formatNumber value="${purchase.total_usd}" type="number" groupingUsed="true" />.00
				</td>
				<td>
					<fmt:formatNumber value="${purchase.total_doge}" type="number" groupingUsed="true" /> DOGE
				</td>
				<td>
					<c:choose>
						<c:when test="${purchase.delivery_state == 0}">배송 준비중</c:when>
						<c:when test="${purchase.delivery_state == 1}">배송 중</c:when>
						<c:when test="${purchase.delivery_state == 2}">배송 완료</c:when>
					</c:choose>
				</td>
				<td>${purchase.reg_date}</td>
				<td>${purchase.transaction_id}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>