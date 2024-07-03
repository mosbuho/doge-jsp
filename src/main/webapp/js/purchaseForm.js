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


function submitPurchase() {
	alert("결제 완료");
}