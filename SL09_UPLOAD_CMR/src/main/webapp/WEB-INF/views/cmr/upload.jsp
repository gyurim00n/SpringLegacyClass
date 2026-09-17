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
   views/cmr/upload
  </xmp>
  <!-- http://localhost/cmr/upload -->
  <form action="" method="post" enctype="multipart/form-data">
  
  	<div> <input type="text" name="output" value="hello world!"> </div>
  	<div> <input type="file" name="attach"> </div>
  	<div> <input type="submit"> </div>
  <!-- CSRF 토큰 -->
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  
  </form>
</div>
<script>
</script>
</body>
</html>