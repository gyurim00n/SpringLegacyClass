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
  <div>
  	<h3>Reply</h3>
  	<button id="addReplyBtn">New Reply</button>
  	<!-- 모달창 -->
  	
<!-- Modal -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true" style="border:solid 1px gray">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">&times;</button>
              <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Reply</label> 
                <input class="form-control" name='reply' value='New Reply!!!!'>
              </div>      
              <div class="form-group">
                <label>Replyer</label> 
                <input class="form-control" name='replyer' value='replyer'>
              </div>
              <div class="form-group">
                <label>Reply Date</label> 
                <input class="form-control" name='replyDate' value='2018-01-01 13:13'>
              </div>
      
            </div>
<div class="modal-footer">
        <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
        <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
        <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
        <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
      </div>          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
<!-- /.modal -->
  	
  	<div>
  		<ul class="chat">
  		
  		</ul>
  	</div>
  </div>
  
  
</div>


<script src="/resources/js/reply.js"></script>

<script>
$(function(){
	const formObj = $("form");
	$("tfoot button").on("click", function(){
		
		let operation = $(this).data("oper");
		if(operation=="modify"){
			formObj.attr({
				"action": "/board/modify",
				"method": "get"
			})
			.submit();
		}else if(operation=="remove"){
			if(confirm("정말 삭제하시겠습니까?")){
				formObj.attr({
					"action": "/board/remove",
					"method": "get"
				}).submit();
			}
		}else if(operation=="list"){
			const pageNumClone= $(":hidden[name='pageNum']").clone();
			const pageAmountClone= $(":hidden[name='amount']").clone();
			
			formObj.attr({
				"action": "/board/list",
				"method": "get"
			})
			.empty()
			.append(pageNumClone)
			.append(pageAmountClone)
			.submit();
		}
	});
	
	var result= '<c:out value="${result}" />';
	checkModal(result);
	history.replaceState({}, null, null);
	
	function checkModal(result){
		if(result == "SUCCESS"){
			alert(`${param.bno}번이 수정되었습니다`); 
			return;
		}
	}
});
</script>

<script>
$(function(){
	/*
	console.log("==================")
	console.log("reply.js test.");
	
	var bnoValue= '<c:out value= "${boardVO.bno}"/>'
	
	replyService.getList({bno:bnoValue, page:1}, function(list){
		for (var i = 0, len = list.length || 0; i < len; i++) { 
			console.log(list[i]);
		}
	})
	*/
}); // <-- 주석 처리된 블록의 닫는 괄호 추가
</script>

<script>
$(function(){

	var bnoValue = '<c:out value= "${boardVO.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	function showList(page){
		replyService.getList({bno: bnoValue, page: page || 1}, function(list) {
			
			var str = "";
			if(list == null || list.length == 0){
				replyUL.html("");
				return;
			}
			
			for (var i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
				str += "  <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>"; 
				str += "    <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
				str += "    <p>" + list[i].reply + "</p></div></li>";
			}

			replyUL.html(str);
			
		}); 
	} 

	var modal = $(".modal");
	modal.hide();
    var modalInputReply = modal.find("input[name='reply']");
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replyDate']");
    
    var modalModBtn = $("#modalModBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    $("#modalCloseBtn").on("click", function(e){
      modal.hide();
    });
    
    $("#addReplyBtn").on("click", function(e){
    	modal.find("input").val("");
        modalInputReplyDate.closest("div").hide();
        modal.find("button[id !='modalCloseBtn']").hide();
        
        modalRegisterBtn.show();
        
    	$(".modal").show();
    }); // <-- #addReplyBtn 이벤트의 닫는 괄호 추가
    
    //댓글 등록 버튼
    modalRegisterBtn.on("click", function(e){
    	//
    	//js Object
    	var reply = {
    			reply:modalInputReply.val(),
    			replyer:modalInputReplyer.val(),
    			bno:bnoValue
    			
    	};
    	
    	replyService.add(reply, function(result){
    		alert(result); //SUCCESS
    		modal.find("input").val("");//초기화
    		modal.hide();
    		
    		showList(1);
    	});
    	
    })

    //댓글 상세보기 클릭 이벤트 처리
    $(".chat").on("click", "li", function (e){
    	
    	var rno = $(this).data("rno");
    	//console.log(">>> rno:" + rno);
    	replyService.get(rno, function(reply ){
    		
    		 modalInputReply.val(reply.reply);
    	        modalInputReplyer.val(reply.replyer);
    	        modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
    	        .attr("readonly","readonly");
    	        modal.data("rno", reply.rno);
    	        
    	        modal.find("button[id !='modalCloseBtn']").hide();
    	        modalModBtn.show();
    	        modalRemoveBtn.show();
    		$(".modal").show();
    		
    		
    	});
    	
    	
    })
    
    
    //삭제버틍
    modalRemoveBtn.on("click", function(e){
    	
    	var rno = modal.data("rno");
    	replyService.remove(rno, function(result){
    		alert(result);
    		modal.hide();
    		showList(1);
    		
    		
    	})		
    		
    })//modalremovebtn 
    
    
    
    
    
    //수정처리
    modalModBtn.on("click", function(e){
    	var reply = {
    			rno: modal.data("rno"),
    			reply: modalInputReply.val(),
    	};
    	replyService.update(reply, function(result){
    		alert(result);
    		modal.hide();
    		showList(1);
    	});
    })
    
    
}); // <-- $(function(){}) 전체의 닫는 괄호 추가
</script>

</body>
</html>