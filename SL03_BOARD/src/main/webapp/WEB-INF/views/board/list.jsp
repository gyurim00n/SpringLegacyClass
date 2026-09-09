<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">Gyur HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>  
  <xmp class="code">
   board/list.jsp
  </xmp>
  
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
    			<tr colspan="5"> no board...</tr>
    	</c:when>
    	<c:otherwise>
    		<c:forEach items="${list }" var = "board">
    			<tr>
    			
	    			<td><c:out value="${board.bno}" /></td>
	    			<td> <a href="/board/get?bno=${board.bno}"><c:out value="${board.title}" /></a></td>
	    			<td><c:out value="${board.writer}" /></td>
	    			<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd"/></td>
	    			<td><fmt:formatDate value="${board.updatedate }" pattern="yyyy-MM-dd"/></td>
    			
    			</tr>
    		</c:forEach>
    	</c:otherwise>
    </c:choose>
    
    
   </tbody>
  </table>   
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
			alert(`\${param.bno}번이 삭제되었습니다`);
			return;
		}
		
	}//checkmodal
	
	
	
	
})//$(function

</script>
</body>
</html>
