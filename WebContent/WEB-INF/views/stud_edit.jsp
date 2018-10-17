<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class='page-header'>학생수정</h1>
		
		<!-- 수정을 위한 HTML 폼 시작 -->
		<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/stud_edit_ok.do">
		  
		  <!-- 상태유지를 위한 일련번호 값의 처리 -->
		  <input type="hidden" name="profno" value="${item.studno}" />
		  
		  <div class="form-group">
		    <label for="name" class="col-sm-2 control-label">학생이름</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name="name" value="${item.name}" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="userid" class="col-sm-2 control-label">아이디</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="userid" name="userid" value="${item.userid}" />
		    </div>
		  </div>
		 
		 <div class="form-group">
		    <label for="grade" class="col-sm-2 control-label">학년</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="grade" name="grade" value="${item.grade}"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="idnum" class="col-sm-2 control-label">주민등록번호</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="idnum" name="idnum" value="${item.idnum}"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="birthdate" class="col-sm-2 control-label">생년월일</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="birthdate" name="birthdate" value="${item.birthdate}"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="height" class="col-sm-2 control-label">키</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="height" name="height" value="${item.height}"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="weight" class="col-sm-2 control-label">몸무게</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="weight" name="weight" value="${item.weight}"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="deptno" class="col-sm-2 control-label">소속학과</label>
		    <div class="col-sm-10">
		      <select name="deptno" class="form-control">
		      	<option value="">---- 소속학과를 선택하세요 ----</option>
		      	<c:forEach var="dept" items="${dept_list}">
		      		<c:choose>
		      			<c:when test="${dept.deptno == item.deptno}">
		      				<option value='${dept.deptno}' selected>${dept.dname}</option>
		      			</c:when>
		      			<c:otherwise>
		      				<option value='${dept.deptno}'>${dept.dname}</option>
		      			</c:otherwise>
		      		</c:choose>
		      	</c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="deptno" class="col-sm-2 control-label">소속교수</label>
		    <div class="col-sm-10">
		      <select name="profno" class="form-control">
		      	<option value="">---- 소속교수를 선택하세요 ----</option>
		      	<c:forEach var="prof" items="${prof_list}">
		      		<c:choose>
		      			<c:when test="${prof.profno == item.profno}">
		      				<option value='${prof.profno}' selected>${prof.name}</option>
		      			</c:when>
		      			<c:otherwise>
		      				<option value='${prof.profno}'>${prof.name}</option>
		      			</c:otherwise>
		      		</c:choose>
		      	</c:forEach>
		      </select>
		    </div>
		  </div>

		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">저장하기</button>
		    </div>
		  </div>
		</form>
		<!--// 수정을 위한 HTML 폼 끝  -->
	</div>
</body>
</html>



