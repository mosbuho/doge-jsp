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
	const member_name = document.getElementById("member_name").value;
	const member_addr = document.getElementById("member_addr").value;
	const card_name = document.getElementById("card_name").value;
	const card_number = document.getElementById("card_number").value;
	const expiry_date = document.getElementById("expiry_date").value;
	const cvv = document.getElementById("cvv").value;


	if (member_name.trim() === '' || member_addr.trim() === '' || card_name === '' || card_number.trim() === '' || expiry_date.trim() === '' || cvv.trim() === '') {
		alert("입력값을 확인해주세요.");
	} else {
		fetch("/doge-jsp/purchaseProcess.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ member_id: member_id, name: member_name, goods_id: goods_id, quantity: quantity, addr: member_addr })
		})
			.then(response => {
				if (response.ok) {
					alert("구매 완료");
					location.href = "/doge-jsp/index.do";
				} else {
					alert("구매 처리 중 오류가 발생했습니다.");
				}
			});
	}
}