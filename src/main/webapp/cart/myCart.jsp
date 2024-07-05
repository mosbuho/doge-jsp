<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
main {
	padding-top: 120px;
	margin: 0 auto;
	width: 400px;
	align-items: center;
	display: flex;
	flex-direction: column;
	margin: 0 auto;
}

.cart-item {
	width: 100%;
	align-items: center;
	display: flex;
	margin-bottom: 20px;
	border-bottom: 1px solid #ccc;
	padding-bottom: 10px;
	display: flex;
	align-items: center;
}

.cart-item img {
	width: 100px;
	height: 100px;
	margin-right: 20px;
}

.cart-details {
	flex-grow: 1;
}

.cart-quantity {
	display: flex;
	align-items: center;
}

.cart-quantity button {
	margin: 0 5px;
	background: var(--button-background);
	color: var(--button-color);
	outline: none;
	border: none;
	width: 20px;
	height: 20px;
	color: var(--button-color);
	outline: none;
	border: none;
	width: 20px;
	cursor: pointer;
}

.cart-quantity input {
	width: 40px;
	text-align: center;
	background: var(--input-background);
	outline: none;
	border: none;
	height: 19px;
}

.cart-item-title {
	font-size: 1.2em;
	margin-bottom: 10px;
}

.cart-item-total {
	color: #888;
	margin-bottom: 10px;
}

.checkout-button {
	margin-top: 20px;
	width: 100%;
	height: 30px;
	border-radius: 7px;
	border: none;
	outline: none;
	background: var(--button-background);
	color: var(--button-color);
	cursor: pointer;
}
</style>
<script>
function updateQuantity(goodsId, delta) {
    const quantityInput = document.getElementById('quantity-' + goodsId);
    let quantity = parseInt(quantityInput.value) + delta;
    if (quantity < 1) quantity = 1;
    quantityInput.value = quantity;
    const price = parseFloat(document.getElementById('price-' + goodsId).innerText);
    const total = price * quantity;
    document.getElementById('total-' + goodsId).innerText = total.toFixed(2);
    updateTotalAmount();
}

function updateTotalAmount() {
    let totalAmount = 0;
    const totalElements = document.querySelectorAll('[id^="total-"]');
    totalElements.forEach(element => {
        totalAmount += parseFloat(element.innerText);
    });
    document.getElementById('cart-total-amount').innerText = totalAmount.toFixed(2);
}

// 페이지 로드 시 총 금액 계산
window.onload = updateTotalAmount;
</script>
</head>
<body>
	<h1>My Cart</h1>
	<c:forEach var="item" items="${cartList}">
		<div class="cart-item">
			<img src="/doge-jsp/img/${item.title_img}" alt="${item.title}">
			<div class="cart-details">
				<div class="cart-item-title">${item.title}</div>
				<div>
					개당 가격 : $<span id="price-${item.goods_id}">${item.price}</span>
				</div>
				<div class="cart-item-total">
					총 가격 : $<span id="total-${item.goods_id}">${item.price * item.quantity}</span>
				</div>
				<div class="cart-quantity">
					<button onclick="updateQuantity(${item.goods_id}, -1)">-</button>
					<input type="text" id="quantity-${item.goods_id}"
						value="${item.quantity}" readonly>
					<button onclick="updateQuantity(${item.goods_id}, 1)">+</button>
				</div>
			</div>
		</div>
	</c:forEach>
	<div>
		총 금액 : $<span id="cart-total-amount"></span>
	</div>
	<button class="checkout-button">구매하기</button>
</body>
</html>
