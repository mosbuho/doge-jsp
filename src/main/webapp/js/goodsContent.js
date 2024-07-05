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
	let quantity = parseInt(document.getElementById('display-quantity').textContent);
	if (member_id !== 0) {
		fetch("/doge-jsp/addToCart.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ member_id, goods_id, quantity })
		})
			.then(response => {
				if (response.ok) {
					alert("장바구니에 추가되었습니다.");
				}
			})
			.catch(() => {
				alert("장바구니 추가 중 문제가 발생했습니다. 다시 시도해주세요.");
			});
	} else {
		alert("로그인 후 가능합니다.");
	}
}

function purchase(member_id) {
	if (member_id !== 0) {
		let quantity = parseInt(document.getElementById('display-quantity').textContent);
		document.getElementById('quantity').value = quantity;

		let form = document.createElement('form');
		form.method = 'POST';
		form.action = '/doge-jsp/purchaseForm.do';

		let inputIds = ['member_id', 'goods_id', 'quantity'];
		inputIds.forEach(id => {
			let input = document.getElementById(id);
			input.name = id;
			form.appendChild(input.cloneNode());
		});

		document.body.appendChild(form);
		form.submit();
	} else {
		alert("로그인 후 가능합니다.");
	}
}