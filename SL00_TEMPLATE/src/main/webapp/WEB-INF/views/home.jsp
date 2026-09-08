<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<a href= "/time"> /time </a>
<xmp>
1. pom.xml -> pom_original.xml 수정
2. web.xml -> web_original.xml 수정

org.doit.ik.aop
org.doit.ik.config
org.doit.ik.controller
org.doit.ik.domain
org.doit.ik.exception
org.doit.ik.security
org.doit.ik.service
org.doit.ik.util

http://localhost/time 요청-> 서버 시간 -> 응답 time.jsp
							서비스+DAO													
</xmp>
</body>
</html>
