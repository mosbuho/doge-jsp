<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/doge-jsp/css/managerGoodsRegisterForm.css">
<script src="/doge-jsp/js/managerUpdateGoodsForm.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/compressorjs/1.1.1/compressor.min.js"></script>
<h1>상품 수정</h1>
<form action="/doge-jsp/managerGoodsRegisterProcess.do" method="post" enctype="multipart/form-data" class="form-container">
	<div>
		<label for="title_img">사진</label>
		<br>
		<div id="preview">
		<img src="/doge-jsp/img/${goods.title_img}" />
		</div>
		<br>
		<input type="file" id="title_img" name="title_img" accept="image/*" required>
		<br>
		<input type="hidden" id="uploadedImageName" name="uploadedImageName">
	</div>
	<div class="form-content">
		<label for="title">제목</label>
		<br>
		<input type="text" id="title" name="title" value="${goods.title}" required>
		<br>
		<br>
		<label for="description">설명</label>
		<br>
		<textarea id="description" name="description" required>${goods.description}</textarea>
		<br>
		<br>
		<label for="price">가격</label>
		<br>
		<input type="number" id="price" name="price" value="${goods.price}" required>
		<br>
		<br>
		<label for="discount">할인율(%)</label>
		<br>
		<input type="number" id="discount" name="discount" value="${goods.discount}" required>
		<br>
		<br>
		<label for="quantity">수량</label>
		<br>
		<input type="number" id="quantity" name="quantity" value="${goods.quantity}" required>
		<br>
		<br>
		<label for="category">카테고리</label>
		<br>
		<select id="category" name="category" required>
			<option value="">카테고리 선택</option>
			<option value="other">기타</option>
		</select>
		<br>
		<br>
		<button class="submitButton" onClick="submitGoodsForm(${goods.goods_id})" type="button">수정</button>
	</div>
</form>