<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>자유 게시판</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/main"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">자유 게시판</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width:800px;margin: 0px auto;">
            	<table class="table">
            		<tr>
            			<td class="text-left">
            				<a href="/board/insert" class="btn btn-sm btn-warning">새글</a>
            			</td>
            		</tr>
            	</table>
            	<table class="table">
            		<thead>
            			<tr class="table-danger">
            				<th width="10%" class="text-center">번호</th>
            				<th width="45%" class="text-center">제목</th>
            				<th width="15%" class="text-center">이름</th>
            				<th width="20%" class="text-center">작성일</th>
            				<th width="10%" class="text-center">조회수</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="vo" items="${list }">
            				<tr onclick="location.href='/board/detail?no=${vo.no}'" style="cursor:pointer">
	            				<td width="10%" class="text-center">${vo.no }</td>
	            				<td width="45%" class="text-left">${vo.subject }&nbsp;
	            				<c:if test="${vo.replycount!=0 }">
	                        	(${vo.replycount })
	                      		</c:if>
	            				<c:if test="${today==vo.dbday }">
	            					<sup><img src="/img/new.gif"></sup>
	            				</c:if>
	            				</td>
	            				<td width="15%" class="text-center">${vo.name }</td>
	            				<td width="20%" class="text-center">${vo.dbday }</td>
	            				<td width="10%" class="text-center">${vo.hit }</td>
            				</tr>
            			</c:forEach>
            				<tr>
            					<td class="text-center" colspan="5">
            						<a href="/board/list?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-warning">이전</a>
            						&nbsp; ${curPage } page / ${totalPage } page &nbsp;
            						<a href="/board/list?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-success">다음</a>
            					</td>
            				</tr>
            		</tbody>
            	</table>
            </div>
        </div>
    </section>
</body>
</html>