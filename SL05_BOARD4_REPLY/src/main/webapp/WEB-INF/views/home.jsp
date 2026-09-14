<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href= "/board/list"> 게시글 목록</a>

<hr>
<form>
  <label for="empno">아이디 : </label>
  <input type="text" id="empno" name="empno" autofocus="autofocus" value="7369">
  <button type="button" id="btnCheck">중복체크</button>
  <br>
  <span id="idCheckResult"></span>
</form>

<script>
$("#btnCheck").on("click", function(){
	var empno = $("#empno").val();
	if(!empno){
		
		alert("아이디를 입력하세요!");
		return;
	}
	//ajax 요청
	$.ajax({
		url:`/empnoCheck/\${empno}`,
		type:'GET',
		success: function(response){
			if(response === "AVAILABLE"){
				$("#idCheckResult").text("사용가능한 아이디입니다.").css("color", "green");
			}else{
				$("#idCheckResult").text("이미 사용중인 아이디입니다.").css("color", "red");
			}
		},error:function(){
			
		}
			
	
	})
	
	
	/*
	$.ajax({
		url: "/emp/empno",
		type: "GET",
		data: {empno: empno},
		success: function(response){
			
		}, error: function(){
			
		}
			*/ //제대로 된 rest방식이 아니다.
			
		
	

});//
</script>
<xmp>
											
</xmp>
</body>
</html>
