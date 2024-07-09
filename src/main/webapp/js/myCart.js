document.addEventListener('DOMContentLoaded', function() {
	const cardNumber = document.getElementById('card_number');
	const expiryDate = document.getElementById('expiry_date');
	const cvv = document.getElementById('cvv');

	cardNumber.addEventListener('input', function(e) {
		let value = e.target.value.replace(/\D/g, '');
		if (value.length > 16) value = value.slice(0, 16);
		let formattedValue = '';
		for (let i = 0; i < value.length; i++) {
			if (i > 0 && i % 4 === 0) formattedValue += '-';
			formattedValue += value[i];
		}
		e.target.value = formattedValue;
	});

	expiryDate.addEventListener('input', function(e) {
		let value = e.target.value.replace(/\D/g, '');
		if (value.length > 4) value = value.slice(0, 4);
		if (value.length > 2) {
			value = value.slice(0, 2) + '/' + value.slice(2);
		}
		e.target.value = value;
	});

	cvv.addEventListener('input', function(e) {
		e.target.value = e.target.value.replace(/\D/g, '').slice(0, 3);
	});

	initializePrices();
});

// ===========================================================

function updateQuantity(goodsId, delta) {
	const quantityInput = document.getElementById('quantity-' + goodsId);
	let quantity = parseInt(quantityInput.value) + delta;
	if (quantity < 0) quantity = 0;
	quantityInput.value = quantity;

	const priceElement = document.getElementById('price-' + goodsId);
	const dogePrice = parseFloat(priceElement.dataset.dogePrice);
	const usdPrice = parseFloat(priceElement.dataset.usdPrice);

	const totalElement = document.getElementById('total-' + goodsId);
	const dogeTotalElement = totalElement.querySelector('.doge-price');
	const usdTotalElement = totalElement.querySelector('.usd-price');

	const dogeTotal = dogePrice * quantity;
	const usdTotal = usdPrice * quantity;

	dogeTotalElement.textContent = dogeTotal.toLocaleString(undefined, { maximumFractionDigits: 0 });
	usdTotalElement.textContent = usdTotal.toFixed(2);

	updateTotalAmount();
}

function updateTotalAmount() {
	let dogeTotalAmount = 0;
	let usdTotalAmount = 0;
	const totalElements = document.querySelectorAll('[id^="total-"]');
	totalElements.forEach(element => {
		dogeTotalAmount += parseFloat(element.querySelector('.doge-price').textContent.replace(/,/g, ''));
		usdTotalAmount += parseFloat(element.querySelector('.usd-price').textContent);
	});
	document.getElementById('cart-total-doge').textContent = dogeTotalAmount.toLocaleString(undefined, { maximumFractionDigits: 0 });
	document.getElementById('cart-total-usd').textContent = usdTotalAmount.toFixed(2);
}

function initializePrices() {
	const priceElements = document.querySelectorAll('[id^="price-"]');
	priceElements.forEach(element => {
		const goodsId = element.id.split('-')[1];
		const dogePrice = parseFloat(element.dataset.dogePrice);
		const usdPrice = parseFloat(element.dataset.usdPrice);
		const quantity = parseInt(document.getElementById('quantity-' + goodsId).value);

		const totalElement = document.getElementById('total-' + goodsId);
		const dogeTotalElement = totalElement.querySelector('.doge-price');
		const usdTotalElement = totalElement.querySelector('.usd-price');

		dogeTotalElement.textContent = (dogePrice * quantity).toLocaleString(undefined, { maximumFractionDigits: 0 });
		usdTotalElement.textContent = (usdPrice * quantity).toFixed(2);
	});

	updateTotalAmount();
}

// ===========================================================

function purchase(member_id) {
	const member_name = document.getElementById("member_name").value;
	const member_addr = document.getElementById("member_addr").value;
	const card_name = document.getElementById("card_name").value;
	const card_number = document.getElementById("card_number").value;
	const expiry_date = document.getElementById("expiry_date").value;
	const cvv = document.getElementById("cvv").value;

	if (member_name.trim() === '' || member_addr.trim() === '' || card_name === '' || card_number.trim() === '' || expiry_date.trim() === '' || cvv.trim() === '') {
		alert("입력값을 확인해주세요.");
	} else {
		let goodsList = [];
		const cartItems = document.querySelectorAll('.cart-item');
		cartItems.forEach(item => {
			const itemId = item.querySelector('.cart-details input').id.split('-')[1];
			const quantity = parseInt(document.getElementById(`quantity-${itemId}`).value);
			const total_usd = parseInt(document.getElementById('cart-total-usd').textContent.trim().replace(/,/g, ''));
			const total_doge = parseInt(document.getElementById('cart-total-doge').textContent.trim().replace(/,/g, ''));
			if (quantity > 0) {
				goodsList.push({ goods_id: itemId, quantity: quantity, total_usd: total_usd, total_doge: total_doge });
			}
		});
		if (goodsList.length === 0) {
			alert("주문할 상품을 선택해주세요.");
		} else {
			fetch("/doge-jsp/purchaseAllProcess.do", {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify({ member_id: member_id, name: member_name, goodsList: goodsList, addr: member_addr })
			})
				.then(response => {
					if (response.ok) {
						alert("구매가 완료되었습니다.");
						location.href = "/doge-jsp/index.do";
					} else {
						alert("구매 처리 중 오류가 발생했습니다.");
					}
				});
		}
	}
}