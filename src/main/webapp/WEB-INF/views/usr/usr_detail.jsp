<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세 화면</title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="/resources/css/greeting.css">
 <link rel="stylesheet" type="text/css" href="/resources/css/member_detail.css"> -->
</head>
<body>
	<div class="usr_container">
		<%-- <%@ include file= "../left_side_menu.jsp"%> --%>
		<div class="main">
			<h1>사원 상세 정보</h1>
			<hr />
			<div class="top"></div>
			<table>
				<tr>
					<td>사번</td>
					<td><input type="text" value="${usr.getNo()}" readOnly /></td>
				</tr>
				<tr>
					<td>성명</td>
					<td><input type="text" value="${usr.getNm()}" readOnly /></td>
				</tr>
			</table>
		</div>
		<!-- top end -->
		<hr />
		
		<div class="main-bottom">
			<table>
				<tr>
					<td>생년월일</td>
					<td><input type="text" value="${usr.getBirth()}" name="birth" readOnly/></td>
					<td>입사일</td>
					<td><input type="text" value="${usr.getEntrDate()}" id="entrDate" readOnly/></td>					
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="text" value="${usr.getSex()}" name="sex" readOnly /></td>				
				</tr>
				
				<tr>	
					<td>주소</td>
					<td><input type="text" value="${usr.getAddr()} " name="addr" readOnly/></td>			
				</tr>
				<tr>
					<td>휴대폰 번호</td>
					<td><input type="text" value="${usr.getMblNo()}" name="mblNo" readOnly/></td>			
					<td>이메일</td>
					<td><input type="text" value="${usr.getEmailAddr()}" name="emailAddr" readOnly/></td>
				</tr>
				<tr>
					<td>기술등급</td>
					<td><input type="text" value="${usr.getJobSkill()}" name="jobSkill" readOnly/></td>
					<td>개발분야</td>
					<td><input type="text" value="${usr.getDevPt()}" name="devPt" readOnly/></td>
				</tr>
				<tr>			
					<td>재직상태</td>
					<td><input type="text" value="${usr.getInoffi_sts()}" name="jobRank" readOnly/></td>
					<td>직급</td>
					<td><input type="text" value="${usr.getJobRank()}" name="jobRank" readOnly/></td>				
				</tr>
				<tr>
					<td colspan="4"><hr/><a href="modify_usr?no=${usr.no}">수정하기</a>&nbsp;&nbsp;<a href="remove_usr?no=${usr.no}">삭제하기</a><hr/></td>
				</tr>			
			</table>
		</div>
	</div>
</body>
</html>