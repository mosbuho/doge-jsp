document.addEventListener('DOMContentLoaded', function() {
	const questionList = document.querySelector('.question-list');

	questionList.addEventListener('click', function(e) {
		const questionItem = e.target.closest('.question-item');
		if (!questionItem) return;

		const questionId = questionItem.dataset.id;
		const answerContent = questionItem.querySelector('.answer-content');
		const answerForm = questionItem.querySelector('.answer-form');
		const answerBtn = questionItem.querySelector('.answer-btn');
		const deleteBtn = questionItem.querySelector('.delete-btn');
		const answerEditForm = questionItem.querySelector('.answer-edit-form');
		const editAnswerBtn = questionItem.querySelector('.edit-answer-btn');
		const deleteAnswerBtn = questionItem.querySelector('.delete-answer-btn');
		const cancelAnswerEditBtn = questionItem.querySelector('.cancel-answer-edit-btn');
		const answerActionsEdit = questionItem.querySelector('.answer-actions-edit');

		if (e.target.classList.contains('answer-btn')) {
			answerForm.style.display = 'block';
			answerBtn.style.display = 'none';
			deleteBtn.style.display = 'none';
		} else if (e.target.classList.contains('submit-btn')) {
			const answerContent = answerForm.querySelector('textarea').value;
			submitAnswer(questionId, answerContent);
		} else if (e.target.classList.contains('cancel-btn')) {
			answerForm.style.display = 'none';
			answerBtn.style.display = 'inline-block';
			deleteBtn.style.display = 'inline-block';
		} else if (e.target.classList.contains('delete-btn')) {
			if (confirm('정말 삭제하시겠습니까?')) {
				deleteQuestion(questionId);
			}
		} else if (e.target.classList.contains('edit-answer-btn')) {
			answerContent.style.display = 'none';
			answerEditForm.style.display = 'block';
			editAnswerBtn.style.display = 'none';
			deleteAnswerBtn.style.display = 'none';
			answerActionsEdit.style.display = 'block';
		} else if (e.target.classList.contains('save-answer-btn')) {
			const editedAnswerContent = answerEditForm.querySelector('textarea').value;
			const answerId = answerContent.dataset.answerId;
			updateAnswer(answerId, editedAnswerContent);
		} else if (e.target.classList.contains('cancel-answer-edit-btn')) {
			answerContent.style.display = 'block';
			answerEditForm.style.display = 'none';
			editAnswerBtn.style.display = 'inline-block';
			deleteAnswerBtn.style.display = 'inline-block';
			answerActionsEdit.style.display = 'none';
		} else if (e.target.classList.contains('delete-answer-btn')) {
			const answerId = answerContent.dataset.answerId;
			if (confirm('정말 삭제하시겠습니까?')) {
				deleteAnswer(answerId);
			}
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
				document.querySelector(`.question-item[data-id="${question_id}"]`).remove();
				alert('질문이 삭제되었습니다.');
			} else {
				alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요.');
			}
		})
		.catch(() => alert('삭제 중 오류가 발생했습니다.'));
}

function submitAnswer(question_id, content) {
	fetch(`/doge-jsp/managerNewAwnserProcess.do`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ question_id: question_id, content: content }),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				location.reload();
			} else {
				alert('답변 등록 중 오류가 발생했습니다. 다시 시도해주세요.');
			}
		})
		.catch(() => alert('답변 등록 중 오류가 발생했습니다.'));
}

function updateAnswer(answer_id, content) {
	fetch(`/doge-jsp/managerUpdateAnswerProcess.do`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ answer_id: answer_id, content: content }),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				location.reload();
			} else {
				alert('답변 수정 중 오류가 발생했습니다. 다시 시도해주세요.');
			}
		})
		.catch(() => alert('답변 수정 중 오류가 발생했습니다.'));
}

function deleteAnswer(answer_id) {
	fetch(`/doge-jsp/managerDeleteAnswerProcess.do`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ answer_id: answer_id }),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				location.reload();
			} else {
				alert('답변 삭제 중 오류가 발생했습니다. 다시 시도해주세요.');
			}
		})
		.catch(() => alert('답변 삭제 중 오류가 발생했습니다.'));
}