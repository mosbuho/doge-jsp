function validateForm() {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;
	const pwCheck = document.getElementById('pwCheck').value;
	const phone = document.getElementById('phone').value;

	if (id.length < 4 || id.length > 16) {
		alert('아이디는 4자 이상 16자 이하여야 합니다.');
		return false;
	}

	const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
	if (!pwRegex.test(pw)) {
		alert('비밀번호는 영어, 특수문자, 숫자를 포함하여 8자 이상 16자 이하여야 합니다.');
		return false;
	}

	if (pw !== pwCheck) {
		alert('비밀번호가 일치하지 않습니다.');
		return false;
	}

	const phoneRegex = /^\d{11}$/;
	if (!phoneRegex.test(phone)) {
		alert('핸드폰 번호는 -를 제외한 숫자로만 입력해주세요');
		return false;
	}
	
	return true;
}

function submitRegisterForm() {
	if (validateForm()) {
		alert('회원가입 폼 제출');
	}
}