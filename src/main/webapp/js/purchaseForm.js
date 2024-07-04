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
});


function submitPurchase(member_id, goods_id, quantity) {
	const addr = document.getElementById('member_addr').value;

	fetch("/doge-jsp/purchaseProcess.do", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ member_id: member_id, goods_id: goods_id, quantity: quantity, addr: addr })
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('네트워크 오류');
			}
			return response.json();
		})
		.then(data => {
			if (data.check) {
				alert("구매 완료");
				location.href = "/doge-jsp/index.do";
			} else {
				alert("구매 처리 중 오류가 발생했습니다.");
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('구매 요청 중 오류가 발생했습니다.');
		});
}