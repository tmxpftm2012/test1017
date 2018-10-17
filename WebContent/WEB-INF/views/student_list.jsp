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
		<div class="page-header clearfix">
			<h1 class='pull-left'>학생목록</h1>

			<div style='margin-top: 30px;' class="pull-right">
				<form method='get' action='${pageContext.request.contextPath}/stud_list.do' style="width: 300px;">
					<div class="input-group">
						<input type="text" name='keyword' class="form-control" 
							placeholder="학생이름 검색" value="${keyword}" />
						<span class="input-group-btn">
							<button class="btn btn-success" type="submit">
								<i class='glyphicon glyphicon-search'></i>
							</button>
							<a href="${pageContext.request.contextPath}/stud_add.do" class="btn btn-primary">학생추가</a>
						</span>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 조회결과를 출력하기 위한 표 시작 -->
		<table class="table">
			<thead>
				<tr>
					<th class="info text-center">학생번호</th>
					<th class="info text-center">이름</th>
					<th class="info text-center">아이디</th>
					<th class="info text-center">학년</th>
					<th class="info text-center">주민등록번호</th>
					<th class="info text-center">생년월일</th>
					<th class="info text-center">전화번호</th>
					<th class="info text-center">키</th>
					<th class="info text-center">몸무게</th>
					<th class="info text-center">학과이름</th>
					<th class="info text-center">교수이름</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test='${fn:length(list)>0}'>
					<c:forEach var="item" items="${list}">
				<tr>
					<td class="text-center">${item.studno}</td>
					<td class="text-center">
						<c:url var='readUrl' value="/stud_view.do">
							<c:param name="studno" value="${item.studno}"/>
						</c:url>
						<a href='${readUrl}'>${item.name}</a>
					</td>
					<td class="text-center">${item.userid}</td>
					<td class="text-center">${item.grade}</td>
					<td class="text-center">${item.idnum}</td>
					<td class="text-center">${item.birthdate}</td>
					<td class="text-center">${item.tel}</td>
					<td class="text-center">${item.height}</td>
					<td class="text-center">${item.weight}</td>
					<td class="text-center">${item.dname}</td>
					<td class="text-center">${item.pname}</td>
				</tr>
				</c:forEach>

			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8" class="text-center">조회된 데이터가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>	
			</tbody>
		</table>
		<!--// 조회결과를 출력하기 위한 표 끝  -->
		
		<!-- 페이지 번호 -->
		
		<nav class='text-center'>
    		<ul class="pagination">
        		<!-- 이전 그룹 -->
				<c:choose>
					<c:when test="${pageHelper.prevPage>0}">
						<!-- 이전 그룹에 대한 페이지 번호가 존재한다면 -->
						<!-- 이전 그룹으로 이동하기 위한 URL을 생성해서 'prevUrl'에 저장 -->
						<c:url var='prevUrl' value="/stud_list.do">
							<c:param name="keyword" value="${keyword}"></c:param>
							<c:param name="page" value="${pageHelper.prevPage}"></c:param>
						</c:url>
						<li><a href="${prevUrl}">&laquo;</a></li>
					</c:when>
					<c:otherwise>
						<!-- 이전 그룹에 대한 페이지 번호가 존재하지 않는다면? -->
						<li class="disabled"><a href="#">&laquo;</a></li>
					</c:otherwise>
				</c:choose>
				
				<!-- 페이지 번호 -->
				<c:forEach var="i" begin="${pageHelper.startPage}" end='${pageHelper.endPage}' step="1">
				
					<!-- 각 페이지 번호로 이동할 수 있는 URL을 생성하여 page url 에 저장 -->
					<c:url var="pageUrl" value="/stud_list.do">
						<c:param name="keyword" value="${keyword}"></c:param>
						<c:param name="page" value="${i}"></c:param>
					</c:url>
					
					<!-- 반복중의 페이지 번호와 현재 위치한 페이지 번호가 같은 경우에 대한 분기 -->
					<c:choose>
						<c:when test="${pageHelper.page == i}">
							<li class="active"><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							 <li><a href="${pageUrl}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

        		<!-- 다음 그룹 -->
				<c:choose>
					<c:when test="${pageHelper.nextPage>0}">
						<!-- 다음 그룹에 대한 페이지 번호가 존재한다면 -->
						<!-- 다음 그룹으로 이동하기 위한 URL을 생성해서 'prevUrl'에 저장 -->
						<c:url var='nextUrl' value="/stud_list.do">
							<c:param name="keyword" value="${keyword}"></c:param>
							<c:param name="page" value="${pageHelper.nextPage}"></c:param>
						</c:url>
						<li><a href="${nextUrl}">&raquo;</a></li>
					</c:when>
					<c:otherwise>
						<!-- 다음 그룹에 대한 페이지 번호가 존재하지 않는다면? -->
						<li class="disabled"><a href="#">&raquo;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</body>
</html>



