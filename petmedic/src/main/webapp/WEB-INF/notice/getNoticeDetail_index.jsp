<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/header.jsp"%>
<%@ include file="/petmedic_navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body>
<div class="container noticeWrap">
		<div class="container noticeHeadWrap">
			<div class="noticeTitle">
				<h4 class="h4">공지사항</h4>
			</div>
		</div>
	<div class="noticeContents container">
	
		<table class="noticeDetailTable table">
		<tbody>
			<tr>
				<th>제&nbsp;&nbsp;&nbsp;&nbsp;목</th>
				<td>${noticeList.notice_title}</td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>${noticeList.notice_admin}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${noticeList.notice_date}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td colspan="2" class="notice_cont_box">
					${noticeList.notice_cont}
				</td>
			</tr>
			</tbody>
		</table>
		
		<div class="container">
			<div class="goNolist">
				<label for="noticeListBtn"></label>
				<button type="button" class="abuttons" onclick="location.href='/toGetNoticeList'">목록으로</button>
			</div>
		</div>
		
	</div>
</div>
<%@ include file="/footer.jsp"%>
</body>
</html>