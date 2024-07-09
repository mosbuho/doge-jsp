<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="/doge-jsp/css/purchaseList.css">
<div class="header">
	<h1>구매 내역</h1>
	<a href="/doge-jsp/updateMemberForm.do">내 정보</a>
	<span>구매내역</span>
	<a>문의내역</a>
</div>
<div class="purchase-list">
	<c:forEach var="transactionId" items="${purchaseMap.keySet()}">
		<c:set var="firstPurchase" value="${purchaseMap[transactionId][0]}" />
		<div class="transaction">
			<span class="purchase-id">주문번호 : ${transactionId}</span>
			<c:forEach var="purchase" items="${purchaseMap[transactionId]}">
				<div class="in-transaction">
					<img src="/doge-jsp/img/${purchase.title_img}">
					<div class="purchase-text">
						<div class="purchase-header">
							<span class="purchase-title">${purchase.title}</span>
							<span class="purchase-price">\$${purchase.price}.00</span>
						</div>
						<div>주문 수량 : ${purchase.quantity}</div>
						<div>주문자(수령인) : ${purchase.name}</div>
						<div>배송지 : ${purchase.addr}</div>
						<div class="delivery-state">
							<c:choose>
								<c:when test="${purchase.delivery_state == 0}">주문 접수</c:when>
								<c:when test="${purchase.delivery_state == 1}">배송 중</c:when>
								<c:when test="${purchase.delivery_state == 2}">배송 완료</c:when>
								<c:otherwise>알 수 없음</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</c:forEach>
			<span class="total-price">
				결제 금액:
				<fmt:formatNumber value="${firstPurchase.total_doge}" type="number" groupingUsed="true" />
				DOGE - $
				<fmt:formatNumber value="${firstPurchase.total_usd}" type="number" groupingUsed="true" />.00
			</span>
			<span class="purchase-date">주문일 : ${firstPurchase.reg_date}</span>
		</div>
	</c:forEach>
</div>