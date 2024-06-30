<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/doge-jsp/css/goodsMain.css">
<div class="product-container">
	<c:forEach var="goods" items="${goodsList}">
		<div class="product-item">
			<img src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
			<p class="product-name">${goods.title}</p>
			<p class="product-price">${goods.price}원</p>
		</div>
	</c:forEach>
</div>