<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/doge-jsp/css/managerQuestionList.css">
<script src="/doge-jsp/js/managerQuestionList.js"></script>
<div class="header">
	<h1>QnA</h1>
	<a href="/doge-jsp/managerUserList.do">유저 목록</a>
	<a href="/doge-jsp/managerGoodsList.do">상품 목록</a>
	<a></a>
</div>

<table border="1">
	<thead>
		<tr>
			<th>질문 ID</th>
			<th>멤버 ID</th>
			<th>상품 ID</th>
			<th>내용</th>
			<th>생성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="question" items="${questionList}">
			<tr onClick="managerAnswerForm(${question.question_id})">
				<td>${question.question_id}</td>
				<td>${question.member_id}</td>
				<td>${question.goods_id}</td>
				<td>${question.content}</td>
				<td>${question.reg_date}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
