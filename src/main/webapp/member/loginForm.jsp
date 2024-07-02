<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/doge-jsp/css/form.css">
<script src="/doge-jsp/js/login.js"></script>
<div class="form-container" id="loginContainer">
	<h2>로그인</h2>
	<form id="loginForm">
		<div class="input-group">
			<label for="id">아이디</label> <input type="text" id="id" name="id"
				required>
		</div>
		<div class="input-group">
			<label for="pw">비밀번호</label> <input type="password" id="pw" name="pw"
				required>
		</div>
		<button type="button" onclick="submitLoginForm()">로그인</button>
		<span>계정 없으면</span> <a href="/doge-jsp/registerForm.do">회원가입</a>
	</form>
</div>