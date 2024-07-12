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


function submitUpdateForm(member_id) {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;
	const pwCheck = document.getElementById('pwCheck').value;
	const name = document.getElementById('name').value;
	const phone = document.getElementById('phone').value;
	const addr = document.getElementById('addr').value;

	const idRegex = /^[a-zA-Z0-9]+$/;
	const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
	const nameRegex = /^[a-zA-Z가-힣]{2,24}$/;

	if (id.trim() === '' || pw.trim() === '' || pwCheck.trim() === '' || name.trim() === '' || phone.trim() === '' || addr.trim() === '') {
		alert('값을 입력해주세요.');
	} else if (id.length < 4 || id.length > 16 || !idRegex.test(id)) {
		alert('아이디는 영문, 숫자로 이루어진 4자 이상 16자 이하여야 합니다.');
		return;
	} else if (!pwRegex.test(pw)) {
		alert('비밀번호는 영어, 특수문자, 숫자를 포함하여 8자 이상 16자 이하여야 합니다.');
		return;
	} else if (pw !== pwCheck) {
		alert('비밀번호가 일치하지 않습니다.');
		return;
	} else if (!nameRegex.test(name)) {
		alert('이름은 2자 이상의 한글 혹은 영문이어야 합니다.');
		return;
	} else {
		const formData = { member_id: member_id, pw: pw, name: name, phone: phone, addr: addr };

		fetch('/doge-jsp/updateMemberProcess.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(formData)
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					alert('정보 수정 성공');
					history.back();
				} else {
					alert('정보 수정 중 오류가 발생했습니다. 다시 시도해주세요.');
				}
			})
			.catch(() => alert('정보 수정 중 오류가 발생했습니다. 다시 시도해주세요.'));
	}
}

function submitDelete(member_id) {
	if (confirm('삭제하시겠습니까?')) {
		fetch(`/doge-jsp/managerUserDeleteProcess.do`, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ member_id: member_id })
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					alert('삭제가 완료되었습니다.');
					history.back();
				} else {
					alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요.');
				}
			})
			.catch(() => alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요.'));
	}
}