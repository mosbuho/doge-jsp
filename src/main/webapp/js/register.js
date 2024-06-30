async function submitRegisterForm() {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;
	const pwCheck = document.getElementById('pwCheck').value;
	const phone = document.getElementById('phone').value;
	const addr = document.getElementById('addr').value;

	const idRegex = /^[a-zA-Z0-9]+$/;
	const phoneRegex = /^\d{11}$/;
	const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;

	if (id.length < 4 || id.length > 16 || !idRegex.test(id)) {
		alert('아이디는 영문, 숫자로 이루어진 4자 이상 16자 이하여야 합니다.');
		return;
	} else if (!pwRegex.test(pw)) {
		alert('비밀번호는 영어, 특수문자, 숫자를 포함하여 8자 이상 16자 이하여야 합니다.');
		return;
	} else if (pw !== pwCheck) {
		alert('비밀번호가 일치하지 않습니다.');
		return;
	} else if (!phoneRegex.test(phone)) {
		alert('핸드폰 번호는 -를 제외한 숫자로만 입력해주세요');
		return;
	} else {
		const formData = { id: id, pw: pw, phone: phone, addr: addr };

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