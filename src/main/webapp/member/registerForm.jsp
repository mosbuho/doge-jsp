<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<link rel="stylesheet" href="/doge-jsp/css/form.css">
	<script src="/doge-jsp/js/register.js"></script>
	<div class="form-container" id="registerContainer">
		<h2>회원가입</h2>
		<form id="registerForm">
			<div class="input-group">
				<label for="id">아이디</label> <input type="text" id="id" name="id" placeholder="영문, 숫자 4자 이상 16자 이하"
					required>
			</div>
			<div class="input-group">
				<label for="pw">비밀번호</label> <input type="password" id="pw" name="pw"
					placeholder="영문, 숫자, 특수문자를 포함한 8자 이상 16자 이하" required>
			</div>
			<div class="input-group">
				<label for="pwCheck">비밀번호 확인</label> <input type="password" id="pwCheck" name="pw" required>
			</div>
			<div class="input-group">
				<label for="phone">핸드폰 번호</label> <input type="text" id="phone" name="phone" placeholder="-를 제외한 숫자만 입력"
					required>
			</div>
			<div class="input-group">
				<label for="addr">주소</label> <input type="text" id="addr" name="addr" required>
			</div>
			<button type="button" onclick="submitRegisterForm()">회원가입</button>
		</form>
	</div>