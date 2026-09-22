<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>

	<form action="/customer/noticeEdit.htm?${_csrf.parameterName}=${_csrf.token}"
    method="post" enctype="multipart/form-data">
 		<!-- 글번호 추가하기  -->
        <input type="hidden" name="seq"   value="${noticeVO.seq}">
        
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" value="${ noticeVO.title }" />
				</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">작성자</dt>
				<dd class="article-detail-data half-data">${ noticeVO.writer }
				</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">조회수</dt>
				<dd class="article-detail-data half-data">${ noticeVO.hit }</dd>
			</dl>
			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					&nbsp;<input type="file" id="txtFile" name="file" /> <input
						type="hidden" name="o_filesrc" value="${ noticeVO.filesrc }" />
					${ noticeVO.filesrc }
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${ noticeVO.content }</textarea>
			</div>
		</div>
		<p class="article-comment margin-small">
			<!-- GET + <a class="btn-save button" href="noticeEditProc.jsp">수정</a> -->
			<input type="submit" value="수정" class="btn-save button" /> <a
				class="btn-cancel button" href="noticeDetail.htm">취소</a>
		</p>
		<!-- CSRF 토큰 -->
		<%-- <input type="hidden" name="${ _csrf.parameterName }" 	value="${ _csrf.token }"> --%>
	</form>
	
	    <!-- 인증받은 사용자 정보를 출력 -->
    <ol>
       <li>principal: <sec:authentication property="principal"/> </li>
       <li>MemberVO: <sec:authentication property="principal.member_x"/> </li>
       <li>사용자 ID : <sec:authentication property="principal.member_x.id"/></li>
     <li>사용자 이름 : <sec:authentication property="principal.member_x.name"/></li>
     <li>사용자 권한 목록 : <sec:authentication property="principal.member_x.authList"/></li>       
    </ol>
   
</div>
