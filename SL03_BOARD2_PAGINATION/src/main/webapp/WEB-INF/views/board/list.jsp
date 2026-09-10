<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<xmp class="code"> board/list.jsp </xmp>

		<table>
			<caption style="text-align: right;">
				<a href="/board/register">글쓰기</a>
			</caption>
			<thead>
				<tr>
					<th>#번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr colspan="5">no board...
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list }" var="board">
							<tr>

								<td><c:out value="${board.bno}" /></td>
								<td><a class="move" href="${board.bno}"><c:out
											value="${board.title}" /></a></td>
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate value="${board.regdate }"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${board.updatedate }"
										pattern="yyyy-MM-dd" /></td>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>


			</tbody>
			<tfoot>
				<tr>
					<td colspan="5" class="center">
						<div class="pagination">
							<!-- prev -->
							<c:if test="${pageMaker.prev}">
								<a href="${pageMaker.startPage - 1 }"> &laquo;</a>
							</c:if>
							<!-- [1] 2 3 ... 10  -->
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" step="1" var="num">
								<a href="${num}"
									class="${num eq pageMaker.criteria.pageNum ? 'active' :''  }">
									${num} </a>
							</c:forEach>

							<!-- next -->
							<c:if test="${pageMaker.next}">
								<a href="${pageMaker.endPage + 1 }"> &raquo;</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>

		<!-- 페이지 이동할 때 사용함 -->
		<form id="actionForm" action="/board/list" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
		</form>
	</div>
	<script>

$(function(){
	/* var result= '<c:out value="${param.result}" />'; */
	var result= '<c:out value="${result}" />';
	//alert("2번글 등록되었습니다.");
	checkModal(result);
	
	history.replaceState({}, null, null);
	
	function checkModal(result){
		if(parseInt(result)> 0){
			
			alert(`\${result} 번이 등록되었습니다.`);
		}//if
		
		if(result === "REMOVE_SUCCEED"){
			alert(`${param.bno}번이 삭제되었습니다`);
			return;
		}
		
	}//checkmodal
	
	
	//페이징 블럭에서 번호 클릭해 이동...
	var actionForm = $("#actionForm");
	
	$(".pagination a").on("click", function(){
	
		event.preventDefault();
		let pageNum = $(this).attr("href");
		
		actionForm
			.attr("action", "/board/list")
			.find(":hidden[name=pageNum]")
				.val(pageNum)
				.end()	
			.submit();
		
		
	})//pagination
	
	//제목을 클릭 -> 페이지 이동 + pageNum, amount
	$("a.move").on("click", function(){
		event.preventDefault();
		let bno = $(this).attr("href");
		
		actionForm
			.attr("action", "/board/get")
			.append(`<input type="hidden" name="bno" value="\${bno}">`)
			.submit();
		
	})//a.move
})//$(function

</script>
</body>
</html>
