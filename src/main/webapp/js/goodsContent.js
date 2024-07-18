function decrementQuantity() {
	let display = document.getElementById('display-quantity');
	let currentValue = parseInt(display.textContent);
	let minValue = 1;

	if (currentValue > minValue) {
		display.textContent = currentValue - 1;
	}
}

function incrementQuantity() {
	let display = document.getElementById('display-quantity');
	let currentValue = parseInt(display.textContent);
	let maxValue = parseInt(document.getElementById('goods-quantity').textContent);

	if (currentValue < maxValue) {
		display.textContent = currentValue + 1;
	}
}

function addToCart(member_id, goods_id) {
	let quantity = parseInt(document.getElementById('display-quantity').textContent);
	if (member_id !== 0) {
		fetch('/doge-jsp/addToCart.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ member_id, goods_id, quantity })
		})
			.then(response => response.json())
			.then(data => alert(data.success ? '장바구니에 추가되었습니다.' : '장바구니에 추가 중 오류가 발생했습니다. 다시 시도해주세요.'))
			.catch(() => alert('장바구니에 추가 중 오류가 발생했습니다. 다시 시도해주세요.'));
	} else {
		alert('로그인 후 이용 가능합니다.');
	}
}

function purchase(member_id) {
	if (member_id !== 0) {
		let quantity = parseInt(document.getElementById('display-quantity').textContent);
		document.getElementById('quantity').value = quantity;

		let form = document.createElement('form');
		form.method = 'POST';
		form.action = '/doge-jsp/purchaseForm.do';

		let inputIds = ['member_id', 'goods_id', 'quantity'];
		inputIds.forEach(id => {
			let input = document.getElementById(id);
			input.name = id;
			form.appendChild(input.cloneNode());
		});
		document.body.appendChild(form);
		form.submit();
	} else {
		alert('로그인 후 이용 가능합니다.');
	}
}

function newQuestion(member_id, goods_id, m_id) {
	let today = new Date();
	let formattedDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	if (member_id !== 0) {
		let content = document.getElementById('QnAContent');
		if (content.value.trim() !== '') {
			fetch('/doge-jsp/newQuestion.do', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ member_id: member_id, goods_id: goods_id, content: content.value })
			})
				.then(response => response.json())
				.then(data => {
					if (data.question_id != 0) {
						let qnaNone = document.querySelector('.goods-QnA').querySelector('#QnANone');
						if (qnaNone) {
							qnaNone.remove();
						}

						let newQuestion = document.createElement('div');
						newQuestion.classList.add('question');
						newQuestion.id = `question-${data.question_id}`;

						newQuestion.innerHTML = `
					    <div class='question-top'>
					        <div class='question-top-left'>${m_id}</div>
					        <div class='question-top-right'>
					            <button onclick='editQuestion(${data.question_id})'>수정</button>
					            <button onclick='delQuestion(${data.question_id})'>삭제</button>
					        </div>
					    </div>
					    <div class="question-bottom" id="questionContent-${data.question_id}" data-original-content="${content.value}">${content.value}</div>
						<div class="answer-date">${formattedDate}</div>
					`;
						document.querySelector('.goods-QnA').appendChild(newQuestion);
						content.value = '';
						alert('문의가 등록되었습니다.');
					} else {
						alert('문의 등록 중 오류가 발생했습니다. 다시 시도해주세요.');
					}
				})
				.catch(() => alert('문의 등록 중 오류가 발생했습니다. 다시 시도해주세요.'));
		} else {
			alert('값을 입력해주세요.');
		}
	} else {
		alert('로그인 후 이용 가능합니다.');
	}
}

function delQuestion(question_id) {
	if (confirm('문의를 삭제하시겠습니까?')) {
		fetch('/doge-jsp/delQuestion.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ question_id: question_id })
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					document.querySelector(`#question-${question_id}`).remove();
					alert('문의가 삭제되었습니다.');
				} else {
					alert('문의 삭제 중 오류가 발생했습니다. 다시 시도해주세요.')
				}
			})
			.catch(() => alert('문의 삭제 중 오류가 발생했습니다. 다시 시도해주세요.'));
	}
}

function delQuestion(question_id) {
	if (confirm('문의를 삭제하시겠습니까?')) {
		fetch('/doge-jsp/delQuestion.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ question_id: question_id })
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					document.querySelector(`#question-${question_id}`).remove();
					alert('문의가 삭제되었습니다.');
				} else {
					alert('문의 삭제 중 오류가 발생했습니다. 다시 시도해주세요.')
				}
			})
			.catch(() => alert('문의 삭제 중 오류가 발생했습니다. 다시 시도해주세요.'));
	}
}

function editQuestion(question_id) {
	const questionBottom = document.getElementById('questionContent-' + question_id);
	const originalContent = questionBottom.getAttribute('data-original-content');
	questionBottom.innerHTML = `<textarea class='edit-content' maxlength='1000'>${originalContent}</textarea>`;
	const questionDiv = document.getElementById('question-' + question_id);
	questionDiv.querySelector('.question-top-right').innerHTML = `
        <button onclick='updateQuestion(${question_id})'>등록</button>
        <button onclick='cancelEdit(${question_id})'>취소</button>`;
}

function updateQuestion(question_id) {
	const questionBottom = document.getElementById('questionContent-' + question_id);
	const updatedContent = questionBottom.querySelector('.edit-content').value;
	if (updatedContent.trim() !== '') {
		fetch('/doge-jsp/updateQuestion.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ question_id: question_id, content: updatedContent })
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					questionBottom.innerHTML = updatedContent;
					questionBottom.setAttribute('data-original-content', updatedContent);
					const questionDiv = document.getElementById('question-' + question_id);
					questionDiv.querySelector('.question-top-right').innerHTML = `
                    <button onclick='editQuestion(${question_id})'>수정</button>
                    <button onclick='delQuestion(${question_id})'>삭제</button>`;
					alert('수정이 완료되었습니다.');
				} else {
					alert('수정 중 오류가 발생했습니다. 다시 시도해주세요.');
				}
			})
			.catch(() => alert('수정 중 예기치 못한 오류가 발생했습니다. 다시 시도해주세요.'));
	} else {
		alert('값을 입력해주세요.');
	}
}

function cancelEdit(question_id) {
	const questionBottom = document.getElementById('questionContent-' + question_id);
	const originalContent = questionBottom.getAttribute('data-original-content');
	questionBottom.innerHTML = originalContent;
	const questionDiv = document.getElementById('question-' + question_id);
	questionDiv.querySelector('.question-top-right').innerHTML = `
        <button onclick='editQuestion(${question_id})'>수정</button>
        <button onclick='delQuestion(${question_id})'>삭제</button>`;
}