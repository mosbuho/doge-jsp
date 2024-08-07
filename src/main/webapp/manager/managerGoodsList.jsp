<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/doge-jsp/css/managerGoodsList.css">
<script src="/doge-jsp/js/managerGoodsList.js"></script>
<div class="header">
	<h1>상품 목록</h1>
	<a href="/doge-jsp/managerUserList.do">유저 목록</a>
	<a href="/doge-jsp/managerPurchaseList.do">주문 목록</a>
	<a href="/doge-jsp/managerQuestionList.do">QnA</a>
	<a class="create-button" href="/doge-jsp/managerGoodsRegisterForm.do">새 상품 추가</a>
</div>

<table border="1">
	<thead>
		<tr>
			<th>상품 ID</th>
			<th>제목</th>
			<th>설명</th>
			<th>가격</th>
			<th>할인</th>
			<th>수량</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="goods" items="${goodsList}">
			<tr onclick="updateForm(${goods.goods_id})">
				<td>${goods.goods_id}</td>
				<td>${goods.title}</td>
				<td>${goods.description}</td>
				<td>\$${goods.price}.00</td>
				<td>${goods.discount}%</td>
				<td>${goods.quantity}</td>
				<td>${goods.reg_date}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>