<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/doge-jsp/css/purchaseForm.css">
<script src="/doge-jsp/js/purchaseForm.js"></script>

<div class="purchase-form">
	<h2>결제 정보 입력</h2>

	<div class="purchase-details">
		<div class="left-column">
			<img src="/doge-jsp/img/${goods.title_img}" alt="상품 이미지">
		</div>
		<div class="right-column">
			<div class="product-name">
				<label for="goods_title">상품명 : </label>
				<span>${goods.title}</span>
			</div>
			<div class="quantity">
				<label for="quantity">수량 : </label>
				<span>${quantity}</span>
			</div>
			<div class="total-price">
				<label for="total_price"></label>
				<span>${dogePrice * quantity} DOGE [\$ ${goods.price * quantity}]</span>
			</div>
		</div>
	</div>

	<div class="purchase-form-inputs">
		<label for="member_name">수령인</label>
		<input type="text" id="member_name" name="member_name" required value="${member.name}"><br>
		<label for="member_addr">배송지</label>
		<input type="text" id="member_addr" name="member_addr" required value="${member.addr}"><br>
		<label for="card_name">카드 소유자 이름</label>
		<input type="text" id="card_name" name="card_name" value="${member.name}" required><br>
		<label for="card_number">카드 번호</label>
		<input type="text" id="card_number" name="card_number" required><br>
		<label for="expiry_date">유효 기간 (MM/YY):</label>
		<input type="text" id="expiry_date" name="expiry_date" required><br>
		<label for="cvv">CVV</label>
		<input type="text" id="cvv" name="cvv" required><br>
	</div>

	<button onclick="submitPurchase(${member_id}, ${goods.goods_id}, ${quantity}, ${goods.price * quantity}, ${dogePrice * quantity})">결제 완료</button>
</div>