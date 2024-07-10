<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/doge-jsp/css/updateMemberForm.css">
<script src="/doge-jsp/js/updateMemberForm.js"></script>
<div class="header">
	<h1>내 정보</h1>
	<span>내 정보</span>
	<a href="/doge-jsp/purchaseList.do">구매내역</a> <a href="/doge-jsp/questionList.do">문의내역</a>
</div>
<form id="registerForm">
	<div class="input-group">
		<label for="id">아이디</label>
		<input type="text" id="id" name="id" value="${member.id}" readonly required>
	</div>
	<div class="input-group">
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw" placeholder="영문, 숫자, 특수문자를 포함한 8자 이상 16자 이하" required>
	</div>
	<div class="input-group">
		<label for="pwCheck">비밀번호 확인</label>
		<input type="password" id="pwCheck" name="pwCheck" required>
	</div>
	<div class="input-group">
		<label for="name">이름</label>
		<input type="text" id="name" name="name" placeholder="2자 이상 한글" value="${member.name}" required>
	</div>
	<div class="input-group">
		<label for="phone">핸드폰 번호</label>
		<input type="text" id="phone" maxlength="13" name="phone" placeholder="-를 제외한 숫자만 입력" value="${member.phone}" required>
	</div>
	<div class="input-group">
		<label for="addr">주소</label>
		<input type="text" id="addr" name="addr" value="${member.addr}" required>
	</div>
	<button type="button" onclick="submitUpdateForm(${member.member_id})">수정</button>
	<button type="button" class="logoutButton" onclick="logout()">로그아웃</button>
	<button type="button" class="deleteButton" onclick="submitDelete()">탈퇴</button>
</form>