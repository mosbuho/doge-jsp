async function submitLoginForm() {
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;

	const response = await fetch("/doge-jsp/loginProcess.do", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ id, pw })
	});

	const result = await response.json();
	if (result.member_id !== 0) {
		alert("로그인 성공");
		location.href = "/doge-jsp/index.do";
	} else {
		alert("아이디 또는 비밀번호가 올바르지 않습니다.");
	}
}