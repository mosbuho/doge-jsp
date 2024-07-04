function submitLoginForm() {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;

	fetch("/doge-jsp/managerLoginProcess.do", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ id, pw })
	})
		.then(response => response.json())
		.then(result => {
			if (result.check) {
				alert("로그인 성공");
				location.href = "/doge-jsp/index.do";
			} else {
				alert("아이디 또는 비밀번호가 올바르지 않습니다.");
			}
		})
		.catch(alert('로그인 요청 중 오류가 발생했습니다.'));
}