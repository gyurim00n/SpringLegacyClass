<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>

	<form action="" method="post" enctype="multipart/form-data">

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
		<input type="hidden" name="${ _csrf.parameterName }" 	value="${ _csrf.token }">
	</form>
</div>
