<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/doge-jsp/css/goodsContent.css">
<div>
	<div class="goods-detail-container">
		<div class="goods-image">
			<img src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
		</div>
		<div class="goods-info">
			<h1 class="goods-title">${goods.title}</h1>
			<p class="goods-price">${goods.price} 원</p>
			<p class="goods-price">${goods.quantity} 개</p>
			<p class="goods-price">${goods.reg_date} 출시</p>
			<div class="goods-actions">
				<button class="add-to-cart">장바구니 넣기</button>
				<button class="buy-now">바로 구매하기</button>
			</div>
		</div>
	</div>
	<div class="goods-description">
		<h2>제품 상세 정보</h2>
		<p>${goods.description}</p>
	</div>
</div>
