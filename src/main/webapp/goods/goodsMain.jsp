<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="/doge-jsp/css/goodsMain.css">
<div class="goods-container">
	<c:forEach var="goods" items="${goodsList}">
		<div class="goods-item">
			<a href="/doge-jsp/goodsContent.do?goods_id=${goods.goods_id}"> <img
				src="/doge-jsp/img/${goods.title_img}" alt="${goods.title}">
				<p class="goods-name">${goods.title}</p>
				<p class="goods-price">
					<c:set var="dogePrice" value="${goods.price * tmpPrice}" />
					<fmt:formatNumber value="${dogePrice}" maxFractionDigits="0" />
					DOGE [\$${goods.price}.00]
				</p>
			</a>
		</div>
	</c:forEach>
</div>