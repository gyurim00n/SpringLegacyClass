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
   views/ajax/upload
  </xmp>
  <!-- http://localhost/cmr/upload -->
  <form action="" method="post" enctype="multipart/form-data">
  
  	<div> <input type="text" name="output" value="hello world!"> </div>
  	<div> 
  	
  		<input type="file" name="attach" multiple="multiple"> 
  		<button type="button" id="btnAjaxUpload"> ajax file upload </button>
  		
  		
  		<br>
  		<div class="uploadDiv">
  			<ul>
  			
  			</ul>
  		</div>
  	</div>
  	
  	
  	<hr>
  	<div><input type="text" name="writer" value="admin"></div>
       <div><input type="text" name="email" value="admin@naver.com"></div>
  	
  	
  	<div> <input type="submit"> </div>
  <!-- CSRF 토큰 -->
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  
  </form>
</div>
<script>
$(function(){
	//확장자: 실행파일 체크 
	//5mb 이상? X
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");//;
	var maxSize= 5242880; //5MB
	
	function checkExtension(fileName, fileSize){
		if(fileSize > maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없다.");
			return false;
		}
		return true;
	}
	
	
	$("#btnAjaxUpload").on("click", function(){
		var inputFiles =$(":file[name='attach']");
		var inputFile=inputFiles.get(0);
		
		if (inputFile.files.length === 0) {
	        alert('업로드할 파일을 선택해주세요!');
	        return;
	    }
		
		//console.log(inputFile.files);
		//FormData : 파일 저장-> ajax 처리
		var formData = new FormData();
		for (let file of inputFile.files) {
			//console.log(file.name+ "" + file.size);
			//만약에 실행파일인 경우에는 서버에 업로드 x
			if(!checkExtension(file.name, file.size)){
				
				return false;
			}//if
			formData.append("attachList", file);
		}//for
		
		//fetch(), js, jq
		$.ajax({
			url:'uploadAjax' // /ajax/upload/Ajax 요청
			, processData: false
		    , contentType: false 
			, type:'post'
			, data:formData
			, success: function(result){
				//alert("ajax file uploaded...");
				console.log(result);
				
				var content= "";
				$(result).each(function(i, fname){
					content += "<li>" + fname + "</li>";
					
				});
				
				$(".uploadDiv ul").html(content);
			}
		});//ajax
		
		
		
	});// $("#btnAjaxUpload").on("click"
	
}); //$function(){}

</script>
</body>
</html>