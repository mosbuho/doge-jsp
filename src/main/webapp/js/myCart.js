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

window.onload = initializePrices;