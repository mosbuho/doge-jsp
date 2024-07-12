<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/doge-jsp/css/questionList.css">

<div class="header">
	<h1>문의 내역</h1>
	<a href="/doge-jsp/updateMemberForm.do">내 정보</a>
	<a href="/doge-jsp/purchaseList.do">구매내역</a>
	<span>문의내역</span>
</div>

<div class="questionListDiv">
	<c:if test="${empty questionList}">
		<div class="empty_list">문의내역이 존재하지 않습니다.</div>
	</c:if>
	<c:if test="${not empty questionList}">
		<c:forEach var="goodsEntry" items="${questionList}">
			<div class="goods-group">
				<div class="goods-image">
					<a href="/doge-jsp/goodsContent.do?goods_id=${goodsEntry.key}">
						<img src="/doge-jsp/img/${goodsEntry.value[0].title_img}" alt="${goodsEntry.value[0].title}" />
					</a>
				</div>
				<div class="goods-details">
					<div class="goods-title">${goodsEntry.value[0].title}</div>
					<div class="questions">
						<c:forEach var="question" items="${goodsEntry.value}">
							<div class="question-item">
								<div class="question-content">
									<script>
										var text = '${question.content}';
										var maxLength = 15;
										document
												.write(text.length > maxLength ? text
														.slice(0, maxLength)
														+ '...'
														: text);
									</script>
									<span class="question-date">${question.reg_date}</span>
								</div>
								<div class="divider"></div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>