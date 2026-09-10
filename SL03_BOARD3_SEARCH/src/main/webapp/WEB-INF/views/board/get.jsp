<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2026. 9. 8. 오전 10:09:17</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
   글 보기 get.jsps
  </xmp>
  
 <form action="/board/register" method="post">
     <table>  
       <tbody>
         <tr>
           <th>글번호</th>
           <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
         </tr> 
         <tr>
           <th>제목</th>
           <td><input type="text" name="title" class="full"  readonly="readonly"  value="${ boardVO.title }"></td>        
         </tr> 
         <tr>
           <th>내용</th>
           <td><textarea  name="content" class="full" readonly="readonly"><c:out value="${ boardVO.content }"></c:out></textarea></td>        
         </tr> 
         <tr>
           <th>작성자</th>
           <td><input type="text" name="writer" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
         </tr>  
       </tbody> 
       <tfoot>
         <tr>
           <td colspan="2">
             <button type="button"  data-oper="modify" class="edit">Modify</button>
             <button type="button"  data-oper="remove" class="delete">Delete</button>
             <button type="button" data-oper="list"  class="list">List</button>
           </td>
         </tr>
       </tfoot>
     </table>
     
     <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
     <input type="hidden" name="pageNum" value="${criteria.pageNum}">
	<input type="hidden" name="amount" value="${criteria.amount}">
       
  </form>
  
  
</div>
<script>
$(function(){
	const formObj = $("form");
	$("tfoot button").on("click", function(){
		
		//data-oper = "modify"
		let operation = $(this).data("oper");
		if(operation=="modify"){
			// location.href = /board/modify?bno=2

			formObj.attr({
				"action": "/board/modify",
				"method": "get"
			})
			.submit();
		}else if(operation=="remove"){
			//작성자 확인 X
			if(confirm("정말 삭제하시겠습니까?")){
				//action = "/board/remove" method="get"
				formObj.attr({
					"action": "/board/remove",
					"method": "get"
				}).submit();
				
			}//if
		}else if(operation=="list"){
			
			const pageNumClone= $(":hidden[name='pageNum']").clone();
			const pageAmountClone= $(":hidden[name='amount']").clone();
			
			formObj.attr({
				"action": "/board/list",
				"method": "get"
			})
			.empty()//폼(form) 내부에 남아 있는 기존 입력 필드나 검색 조건(input, hidden 등)을 모두 지우고 깨끗한 상태로 제출하기 위해서 넣은 것입니다.
			.append(pageNumClone)
			.append(pageAmountClone)
			.submit();
		}
		
		
		
	
	});
	
	
	
	//수정이 완료되 후 경고창(모달창) 띄우는 코딩 추가 
	/* var result= '<c:out value="${param.result}" />'; */
	var result= '<c:out value="${result}" />';

	checkModal(result);
	
	history.replaceState({}, null, null);
	
	function checkModal(result){
		/* if(parseInt(result)> 0){
			
			alert(`\${result} 번이 등록되었습니다.`);
		}//if
		 */
		if(result == "SUCCESS"){
			
			alert(`${param.bno}번이 수정되었습니다`);  //   ?bno=21  EL
			return;
		}
		
	}//checkmodal
	
	
	
	
	
})

</script>
</body>
</html>