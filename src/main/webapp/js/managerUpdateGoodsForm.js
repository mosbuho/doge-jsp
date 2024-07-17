document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('title_img').addEventListener('change', function(e) {
		const file = e.target.files[0];
		if (!file) return;

		const allowedTypes = ['image/jpeg', 'image/png', 'image/webp'];
		if (!allowedTypes.includes(file.type)) {
			alert('JPG, PNG, WEBP 형식의 이미지만 업로드 가능합니다.');
			return;
		}

		if (file.size > 5 * 1024 * 1024) {
			alert('파일 크기는 5MB를 초과할 수 없습니다.');
			return;
		}

		new Compressor(file, {
			quality: 0.9,
			maxWidth: 330,
			maxHeight: 330,
			success(result) {
				const formData = new FormData();
				formData.append('file', result, result.name);
				fetch('/doge-jsp/imageUploadProcess.do', {
					method: 'POST',
					header: { 'Content-Type': 'multipart/form-data' },
					body: formData
				})
					.then(response => response.json())
					.then(data => {
						if (data.success) {
							const preview = document.getElementById('preview');
							preview.innerHTML = '';
							const img = document.createElement('img');
							img.src = URL.createObjectURL(result);
							img.style.width = '330px';
							img.style.height = '330px';
							preview.appendChild(img);
							document.getElementById('uploadedImageName').value = data.fileName;
						} else {
							alert('이미지 업로드 중 오류가 발생했습니다. 다시 시도해주세요.');
						}
					})
					.catch(() => alert('이미지 업로드 중 오류가 발생했습니다. 다시 시도해주세요.'));
			}
		});
	});
});

function submitGoodsForm(goods_id) {
	let formData = {
		goods_id: goods_id,
		title: document.getElementById('title').value,
		description: document.getElementById('description').value,
		uploadedImageName: document.getElementById('uploadedImageName').value,
		price: parseInt(document.getElementById('price').value),
		discount: parseInt(document.getElementById('discount').value),
		quantity: parseInt(document.getElementById('quantity').value),
		category: document.getElementById('category').value
	};

	fetch('/doge-jsp/managerGoodsUpdateProcess.do', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				alert('상품 수정이 완료되었습니다.');
				location.href = '/doge-jsp/managerGoodsList.do';
			} else {
				alert('상품 수정 중 오류가 발생하였습니다. 다시 시도해주세요.');
			}
		})
		.catch(() => alert('상품 수정 중 오류가 발생하였습니다. 다시 시도해주세요.'));
}

function submitGoodsDelete(goods_id) {
	if (confirm("정말 삭제하시겠습니까?")) {
		fetch('/doge-jsp/managerGoodsDeleteProcess.do', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ "goods_id": goods_id })
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					alert('상품 삭제가 완료되었습니다.');
					location.href = '/doge-jsp/managerGoodsList.do';
				} else {
					alert('상품 삭제 중 오류가 발생하였습니다. 다시 시도해주세요.');
				}
			})
			.catch(() => alert('상품 수정 중 오류가 발생하였습니다. 다시 시도해주세요.'));
	}
}