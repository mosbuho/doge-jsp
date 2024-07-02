function decrementQuantity() {
	let display = document.getElementById('display-quantity');
	let currentValue = parseInt(display.textContent);
	let minValue = 1;

	if (currentValue > minValue) {
		display.textContent = currentValue - 1;
	}
}

function incrementQuantity() {
	let display = document.getElementById('display-quantity');
	let currentValue = parseInt(display.textContent);
	let maxValue = parseInt(document.getElementById('goods-quantity').textContent);

	if (currentValue < maxValue) {
		display.textContent = currentValue + 1;
	}
}

function addToCart(member_id, goods_id) {
	let display = document.getElementById('display-quantity');
	let quantity = parseInt(display.textContent);

	if (member_id !== 0) {
		fetch("/doge-jsp/addToCart.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ member_id, goods_id, quantity })
		});
		alert("장바구니에 추가되었습니다.");
	} else {
		alert("로그인 후 가능합니다.")
	}
}

function buyNow() {
	alert("구매하기");
}