<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/doge-jsp/css/goodsMain.css">
<div class="goods-container">
	<c:forEach var="goods" items="${goodsList}">
		<div class="goods-item">
			<a href="/doge-jsp/goodsContent.do?id=${goods.goods_id}"> <img
				src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
				<p class="goods-name">${goods.title}</p>
				<p class="goods-price">${goods.price}원</p>
			</a>
		</div>
	</c:forEach>
</div>