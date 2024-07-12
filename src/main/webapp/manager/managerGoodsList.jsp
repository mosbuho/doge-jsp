<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/doge-jsp/css/managerGoodsList.css">

<div class="header">
	<h1>상품 목록</h1>
	<a href="/doge-jsp/managerUserList.do">유저 목록</a>
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
			<tr>
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
