<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="memberId"
	value="${empty sessionScope.member_id ? 0 : sessionScope.member_id}" />
<link rel="stylesheet" type="text/css"
	href="/doge-jsp/css/goodsContent.css">
<script src="/doge-jsp/js/goodsContent.js"></script>
<div>
	<div class="goods-detail-container">
		<div class="goods-image">
			<img src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
		</div>
		<div class="goods-info">
			<h1 class="goods-title">${goods.title}</h1>
			<p class="goods-price">${goods.price}원</p>
			<p id="goods-quantity" class="goods-quantity">${goods.quantity}개</p>
			<p class="goods-reg_date">${goods.reg_date}출시</p>
			<div class="user-quantity">
				<button class="quantity-button" type="button"
					onclick="decrementQuantity()">-</button>
				<span id="display-quantity">1</span>
				<button class="quantity-button" type="button"
					onclick="incrementQuantity()">+</button>
			</div>
			<input type="hidden" id="member_id" value="${memberId}"> <input
				type="hidden" id="goods_id" value="${goods.goods_id}"> <input
				type="hidden" id="quantity" value="1">
			<div class="goods-actions">
				<button class="add-to-cart"
					onClick="addToCart(${memberId}, ${goods.goods_id})">장바구니
					넣기</button>
				<button class="buy-now" onClick="purchase(${memberId})">바로구매</button>
			</div>
		</div>
	</div>
	<hr>
	<div class="goods-description">
		<h2>제품 상세 정보</h2>
		<p>${goods.description}</p>
	</div>
</div>