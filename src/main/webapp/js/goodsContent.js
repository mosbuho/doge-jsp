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
		fetch("/doge-jsp/addToCart.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ member_id, goods_id, quantity })
		})
			.then(response => {
				if (response.ok) {
					alert("장바구니에 추가되었습니다.");
				} else {
					alert("장바구니에 추가 중 오류가 발생했습니다. 다시 시도해주세요.");
				}
			});
	} else {
		alert("로그인 후 이용 가능합니다.");
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
		alert("로그인 후 이용 가능합니다.");
	}
}

function newQuestion(member_id, goods_id, m_id) {
	if (member_id !== 0) {
		let content = document.getElementById('QnAContent');
		fetch("/doge-jsp/newQuestion.do", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ member_id: member_id, goods_id: goods_id, content: content.value })
		})
			.then(response => response.json())
			.then(data => {
				if (data.question_id != 0) {
					alert("문의가 등록되었습니다.");

					let newQuestion = document.createElement('div');
					newQuestion.classList.add('question');
					newQuestion.id = `question-${data.question_id}`;

					newQuestion.innerHTML = `
					    <div class="question-top">
					        <div class="question-top-left">${m_id}</div>
					        <div class="question-top-right">
					            <button>수정</button>
					            <button onclick="delQuestion(${data.question_id})">삭제</button>
					        </div>
					    </div>
					    <div class="question-bottom">${content.value}</div>
					`;
					document.querySelector('.goods-QnA').appendChild(newQuestion);
					content.value = '';
				} else {
					alert("문의 등록 중 오류가 발생했습니다. 다시 시도해주세요.");
				}
			});
	} else {
		alert("로그인 후 이용 가능합니다.");
	}
}

function delQuestion(question_id) {
	if (confirm("문의를 삭제하시겠습니까?")) {
		fetch("/doge-jsp/delQuestion.do", {
			method: "POST",
			headers: { "Content-Type": "applicdation/json" },
			body: JSON.stringify({ question_id: question_id })
		})
			.then(response => {
				if (response.ok) {
					document.querySelector(`#question-${question_id}`).remove();
					alert("문의가 삭제되었습니다.");
				} else {
					alert("문의 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
				}
			})
	}
}