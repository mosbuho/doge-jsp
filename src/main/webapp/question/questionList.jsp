<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Question List</title>
<link rel="stylesheet" href="/doge-jsp/css/questionList.css" />
</head>
<body>
	<main>
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
                                    document.write(text.length > maxLength ? text.slice(0, maxLength) + '...' : text);
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
	</main>
</body>
</html>