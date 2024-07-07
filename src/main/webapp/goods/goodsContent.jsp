<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="memberId" value="${empty sessionScope.member_id ? 0 : sessionScope.member_id}" />
<link rel="stylesheet" type="text/css" href="/doge-jsp/css/goodsContent.css">
<script src="/doge-jsp/js/goodsContent.js"></script>
<div>
	<div class="goods-detail-container">
		<div class="goods-image">
			<img src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
		</div>
		<div class="goods-info">
			<h1 class="goods-title">${goods.title}</h1>
			<p class="goods-price">${dogePrice}DOGE&nbsp;-&nbsp;\$${goods.price}.00</p>
			<p id="goods-quantity" class="goods-quantity">${goods.quantity}개</p>
			<p class="goods-reg_date">${goods.reg_date}</p>
			<div class="user-quantity">
				<button class="quantity-button" type="button" onclick="decrementQuantity()">-</button>
				<span id="display-quantity">1</span>
				<button class="quantity-button" type="button" onclick="incrementQuantity()">+</button>
			</div>
			<input type="hidden" id="member_id" value="${memberId}"> <input type="hidden" id="goods_id" value="${goods.goods_id}"> <input type="hidden" id="quantity" value="1">
			<div class="goods-actions">
				<button class="add-to-cart" onClick="addToCart(${memberId}, ${goods.goods_id})">장바구니 넣기</button>
				<button class="buy-now" onClick="purchase(${memberId})">바로구매</button>
			</div>
		</div>
	</div>
	<hr>
	<div class="goods-description">
		<h2>제품 상세 정보</h2>
		<p>${goods.description}</p>
	</div>
	<hr>
	<div class="goods-QnA">
		<h2>QnA</h2>
		<c:choose>
			<c:when test="${empty questionList}">
				<p id="QnANone">등록된 문의가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="question" items="${questionList}">
					<div class="question" id="question-${question.question_id}">
						<div class="question-top">
							<div class="question-top-left">${question.m_id}</div>
							<c:if test="${question.member_id == sessionScope.member_id}">
								<div class="question-top-right">
									<button>수정</button>
									<button onclick="delQuestion(${question.question_id})">삭제</button>
								</div>
							</c:if>
						</div>
						<div class="question-bottom">${question.content}</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<hr>
	<div class="goods-new-QnA">
		<textarea id="QnAContent" placeholder="문의 내용을 입력해주세요." maxlength="1000"></textarea>
		<button onclick="newQuestion(${memberId}, ${goods.goods_id}, '${sessionScope.m_id}')">등록</button>
	</div>
</div>