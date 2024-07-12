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