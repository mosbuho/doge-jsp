<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/doge-jsp/css/managerQuestionList.css">
<script src="/doge-jsp/js/managerQuestionList.js"></script>
<div class="header">
	<h1>상품 목록</h1>
	<a href="/doge-jsp/managerUserList.do">유저 목록</a>
	<a href="/doge-jsp/managerGoodsList.do">상품 목록</a>
</div>

<div class="question-list">
	<c:forEach var="question" items="${questionList}">
		<div class="question-item" data-id="${question.question_id}">
			<div class="question-content">
				<p>
					<strong>질문 ID : </strong>
					${question.question_id}
					<strong>| 유저 ID : </strong>
					${question.member_id}
					<strong>| 상품 ID : </strong>
					${question.goods_id}
				</p>
				<p>${question.content}</p>
				<p class="reg_date">${question.reg_date}</p>
			</div>
			<div class="question-actions">
				<button class="answer-btn">답변</button>
				<button class="delete-btn">삭제</button>
			</div>
			<div class="answer-form" style="display: none;">
				<textarea placeholder="답변을 입력하세요"></textarea>
			</div>
		</div>
	</c:forEach>
</div>
