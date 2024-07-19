document.addEventListener('DOMContentLoaded', function() {
	const table = document.querySelector('table');

	table.addEventListener('click', function(e) {
		if (e.target.classList.contains('edit-btn')) {
			const row = e.target.closest('tr');
			makeRowEditable(row);
		} else if (e.target.classList.contains('save-btn')) {
			const row = e.target.closest('tr');
			saveRow(row);
		} else if (e.target.classList.contains('cancel-btn')) {
			const row = e.target.closest('tr');
			cancelEdit(row);
		}
	});

	function makeRowEditable(row) {
		const nameCell = row.cells[4];
		const addrCell = row.cells[5];
		const stateCell = row.cells[8];
		const actionCell = row.cells[11];

		nameCell.innerHTML = `<input type="text" class="edit-name" value="${nameCell.textContent}">`;
		addrCell.innerHTML = `<input type="text" class="edit-addr" value="${addrCell.textContent}">`;
		const currentState = stateCell.textContent.trim();
		stateCell.innerHTML = `
            <select class="edit-state">
                <option value="0" ${currentState === '배송 준비중' ? 'selected' : ''}>배송 준비중</option>
                <option value="1" ${currentState === '배송 중' ? 'selected' : ''}>배송 중</option>
                <option value="2" ${currentState === '배송 완료' ? 'selected' : ''}>배송 완료</option>
            </select>
        `;
		actionCell.innerHTML = `
            <button class="save-btn">등록</button>
            <button class="cancel-btn">취소</button>
        `;
	}

	function saveRow(row) {
		const purchase_id = row.cells[0].textContent;
		const name = row.querySelector('.edit-name').value;
		const addr = row.querySelector('.edit-addr').value;
		const delivery_state = row.querySelector('.edit-state').value;
		fetch('/doge-jsp/managerPurchaseUpdate.do', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				purchase_id: purchase_id,
				name: name,
				addr: addr,
				delivery_state: delivery_state
			}),
		})
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					alert("정보가 수정되었습니다.");
					row.cells[4].textContent = name;
					row.cells[5].textContent = addr;
					row.cells[8].textContent = ['배송 준비중', '배송 중', '배송 완료'][delivery_state];
					row.cells[11].innerHTML = '<button class="edit-btn">수정</button>';
				} else {
					alert("정보가 수정 중 오류가 발생했습니다. 다시 시도해주세요.");
				}
			})
			.catch((error) => alert(error));
	}

	function cancelEdit(row) {
		const nameCell = row.cells[4];
		const addrCell = row.cells[5];
		const stateCell = row.cells[8];
		const actionCell = row.cells[11];

		nameCell.textContent = nameCell.querySelector('input').getAttribute('value');
		addrCell.textContent = addrCell.querySelector('input').getAttribute('value');

		const select = stateCell.querySelector('select');
		const selectedOption = select.options[select.selectedIndex];
		stateCell.textContent = selectedOption.textContent;

		actionCell.innerHTML = '<button class="edit-btn">수정</button>';
	}
});