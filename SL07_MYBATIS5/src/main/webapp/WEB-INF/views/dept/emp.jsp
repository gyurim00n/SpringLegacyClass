<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2026. 9. 8. 오전 10:09:17</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
</head>
<body>
	<header>
		<h1 class="main">
			<a href="#" style="position: absolute; top: 30px;">Gyur HOme</a>
		</h1>
		<ul>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</header>
	<div>
		<xmp class="code"> emp.jsp </xmp>
		<form action="/scott/dept" method="post">
			<table id="tbl-emp">
				<caption></caption>
				<thead>
					<tr>
						<th></th>
						<th>Dno</th>
						<th>Dname</th>
						<th>Eno</th>
						<th>Ename</th>
						<th>Job</th>
						<th>Hiredate</th>
						<th>Sal</th>
						<th>Gr</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ desList }" var="dto">
						<c:forEach items="${dto.empList }" var="edto" varStatus="status">
							<tr>
								<c:if test="${status.first }">
									<td rowspan="${dto.empList.size() }">${ dto.deptno }</td>
									<td rowspan="${dto.empList.size() }">${ dto.dname }</td>
								</c:if>
								
								<td><input type="checkbox" value="${ edto.empno }" name="empno"></td>

								
								
								<td>${ edto.empno }</td>
								<td>${ edto.ename }</td>
								<td>${ edto.job }</td>
								<td><fmt:formatDate value="${ edto.hiredate }" pattern="yyyy-MM-dd" /></td>
								<td>${	edto.sal }</td>
								<td>${  edto.salgradeDTO.grade }</td>
							</tr>
						</c:forEach>

					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<button id="home" class="home">HOme</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>

	</div>


	<script>
</script>
</body>
</html>