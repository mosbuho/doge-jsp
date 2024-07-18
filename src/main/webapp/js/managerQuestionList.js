document.addEventListener('DOMContentLoaded', function() {
	const questionList = document.querySelector('.question-list');

	questionList.addEventListener('click', function(e) {
		const questionItem = e.target.closest('.question-item');
		if (!questionItem) return;

		const questionId = questionItem.dataset.id;
		const answerForm = questionItem.querySelector('.answer-form');
		const answerBtn = questionItem.querySelector('.answer-btn');
		const deleteBtn = questionItem.querySelector('.delete-btn');

		if (e.target.classList.contains('answer-btn')) {
			answerForm.style.display = 'block';
			answerBtn.textContent = '등록';
			deleteBtn.textContent = '취소';
		} else if (e.target.classList.contains('delete-btn')) {
			if (deleteBtn.textContent === '삭제') {
				if (confirm('정말 삭제하시겠습니까?')) {
					deleteQuestion(questionId);
				}
			} else {
				answerForm.style.display = 'none';
				answerBtn.textContent = '답변';
				deleteBtn.textContent = '삭제';
			}
		} else if (e.target.classList.contains('submit-answer')) {
			const answerContent = answerForm.querySelector('textarea').value;
			submitAnswer(questionId, answerContent);
		} else if (e.target.classList.contains('cancel-answer')) {
			answerForm.style.display = 'none';
			answerBtn.textContent = '답변';
			deleteBtn.textContent = '삭제';
		}
	});
});

function deleteQuestion(question_id) {
	fetch(`/doge-jsp/delQuestion.do`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ question_id: question_id }),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				alert('질문이 삭제되었습니다. ');
			} else {
				alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요. ');
			}
		})
		.catch(() => alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요. '));
}

function submitAnswer(questionId, content) {
	fetch(`/doge-jsp/managerNewAwnserProcess.do`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ questionId, content }),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				alert('답변이 등록되었습니다. ');
			} else {
				alert('답변 등록 중 오류가 발생했습니다. 다시 시도해주세요. ');
			}
		})
		.catch(() => alert('답변 등록 중 오류가 발생했습니다. 다시 시도해주세요. '));
}