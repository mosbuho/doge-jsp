document.addEventListener('DOMContentLoaded', function() {
	const phone = document.getElementById('phone');

	phone.addEventListener('input', function(e) {
		let value = e.target.value.replace(/\D/g, '');
		if (value.length > 11) value = value.slice(0, 11);
		let formattedValue = '';
		for (let i = 0; i < value.length; i++) {
			if (i === 3 || i === 7) formattedValue += '-';
			formattedValue += value[i];
		}
		e.target.value = formattedValue;
	});
});



async function submitRegisterForm() {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;
	const pwCheck = document.getElementById('pwCheck').value;
	const name = document.getElementById('name').value;
	const phone = document.getElementById('phone').value;
	const addr = document.getElementById('addr').value;

	const idRegex = /^[a-zA-Z0-9]+$/;
	const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
	const nameInput = document.getElementById('name');
	const nameRegex = /^[a-zA-Z가-힣]{2,24}$/;

	if (id.length < 4 || id.length > 16 || !idRegex.test(id)) {
		alert('아이디는 영문, 숫자로 이루어진 4자 이상 16자 이하여야 합니다.');
		return;
	} else if (!pwRegex.test(pw)) {
		alert('비밀번호는 영어, 특수문자, 숫자를 포함하여 8자 이상 16자 이하여야 합니다.');
		return;
	} else if (pw !== pwCheck) {
		alert('비밀번호가 일치하지 않습니다.');
		return;
	} else if (!nameRegex.test(nameInput.value)) {
		alert("이름은 2자 이상의 한글 혹은 영문이어야 합니다.")
	} else {
		const formData = { id: id, pw: pw, name: name, phone: phone, addr: addr };

		const response = await fetch("/doge-jsp/registerProcess.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(formData)
		});

		const result = await response.json();

		if (result.check) {
			alert("회원가입 성공");
			location.href = "/doge-jsp/index.do";
		} else {
			alert("아이디가 중복됩니다. 다른 아이디를 사용해주세요.");
		}
	}
}