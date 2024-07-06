<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/doge-jsp/css/myCart.css">
<script src="/doge-jsp/js/myCart.js"></script>
<h1>장바구니</h1>
<c:forEach var="item" items="${cartList}">
	<div class="cart-item">
		<img src="/doge-jsp/img/${item.title_img}" alt="${item.title}">
		<div class="cart-details">
			<div class="cart-item-title">${item.title}</div>
			<div>
				가격 : <span id="price-${item.goods_id}" data-doge-price="${item.price * tmpPrice}" data-usd-price="${item.price}"> <fmt:formatNumber value="${item.price * tmpPrice}" maxFractionDigits="0" />
					DOGE &nbsp; - &nbsp; $${item.price}0
				</span>
			</div>
			<div class="cart-item-total">
				총 : <span id="total-${item.goods_id}"> <span class="doge-price"></span> DOGE &nbsp; - &nbsp; $<span class="usd-price"></span>
				</span>
			</div>
			<div class="cart-quantity">
				<button onclick="updateQuantity(${item.goods_id}, -1)">-</button>
				<input type="text" id="quantity-${item.goods_id}" value="${item.quantity}" readonly>
				<button onclick="updateQuantity(${item.goods_id}, 1)">+</button>
			</div>
		</div>
	</div>
</c:forEach>
<div>
	총 금액 : <span id="cart-total-doge"></span> DOGE - $<span id="cart-total-usd"></span>
</div>
<div class="purchase-form-inputs">
	<label for="member_name">수령인</label> <input type="text" id="member_name" name="member_name" required value="${member.name}"><br> <label for="member_addr">배송지</label> <input type="text"
		id="member_addr" name="member_addr" required value="${member.addr}"
	><br> <label for="card_name">카드 소유자</label> <input type="text" id="card_name" name="card_name" value="${member.name}" required><br> <label for="card_number">카드 번호</label> <input
		type="text" id="card_number" name="card_number" required
	><br> <label for="expiry_date">유효 기간 (MM/YY):</label> <input type="text" id="expiry_date" name="expiry_date" required><br> <label for="cvv">CVV</label> <input type="text" id="cvv"
		name="cvv" required
	><br>
</div>
<button class="checkout-button" onclick="purchase(${member.member_id})">구매하기</button>