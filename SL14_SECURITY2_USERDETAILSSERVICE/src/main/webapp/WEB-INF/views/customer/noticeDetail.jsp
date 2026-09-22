<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li id="home"><a href="">HOME</a></li>
		<li><a href="">고객센터</a></li>
		<li><a href="">공지사항</a></li>
	</ul>
	<div id="notice-article-detail" class="article-detail margin-large">
		<dl class="article-detail-row">
			<dt class="article-detail-title">제목</dt>
			<dd class="article-detail-data">${ noticeVO.title }</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">작성일</dt>
			<dd class="article-detail-data">${ noticeVO.regdate }</dd>
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
				<!-- <a href="">flag.png</a> -->
				<%-- <a href="/customer/upload/${ noticeVO.filesrc }">${ noticeVO.filesrc }</a> --%>
				<a
					href="download.htm?dir=/customer/upload&file=${ noticeVO.filesrc }">${ noticeVO.filesrc }</a>
			</dd>
		</dl>

		<div class="article-content">${ noticeVO.content }</div>
	</div>
	<p class="article-comment margin-small">
		<a class="btn-list button" href="notice.htm">목록</a>
		<sec:authorize access="isAuthenticated()">
		
		<sec:authentication property="principal" var="userInfo"/>
		<%-- <sec:authorize access="isAuthenticated() and principal.member_x.id == '${noticeVO.writer}'"> --%>
		<c:if test="${noticeVO.writer eq userInfo.username}">
			<a class="btn-edit button"
				href="noticeEdit.htm?seq=${ noticeVO.seq }">수정</a>
			<a class="btn-del button"
				href="noticeDel.htm?seq=${ noticeVO.seq }&filesrc=${noticeVO.filesrc}">삭제</a>
		</c:if>
		</sec:authorize>
	</p>
	<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
		<dl class="article-detail-row">
			<dt class="article-detail-title">▲ 다음글</dt>
			<dd class="article-detail-data">다음 글이 없습니다.</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">▼ 이전글</dt>
			<dd class="article-detail-data">제 12회 창업스쿨</dd>
		</dl>
	</div>
</div>








