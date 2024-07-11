<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/doge-jsp/css/managerUserList.css">
<script src="/doge-jsp/js/managerUserList.js"></script>
<table class="styled-table">
	<thead>
		<tr>
			<th>고유번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>번호</th>
			<th>주소</th>
			<th>가입일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${memberList}">
			<tr onclick="updateForm(${member.member_id})">
				<td>
					<c:out value="${member.member_id}" />
				</td>
				<td>
					<c:out value="${member.id}" />
				</td>
				<td>
					<c:out value="${member.name}" />
				</td>
				<td>
					<c:out value="${member.phone}" />
				</td>
				<td>
					<c:out value="${member.addr}" />
				</td>
				<td>
					<c:out value="${member.reg_date}" />
				</td>
			</tr>

		</c:forEach>
	</tbody>
</table>