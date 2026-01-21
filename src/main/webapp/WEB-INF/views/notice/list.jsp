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
                        <h2>${vo.title }</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
 	<table class="table">
		<tr>
			<td class="text-center"><h3>공지 목록</h3></td>
		</tr>
	</table>
	<table class="table">
		<thead>
			<tr class="success">
				<th class="text-center" width="10%">
					번호
				</th>
				<th class="text-center" width="45%">
					제목
				</th>
				<th class="text-center" width="15%">
					이름
				</th>
				<th class="text-center" width="20%">
					작성일
				</th>
				<th class="text-center" width="10%">
					조회수
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
			<tr onclick="location.href='/notice/detail?no=${vo.no}'" style="cursor:pointer">
				<td class="text-center" width="10%">
					${vo.no }
				</td>
				<td width="45%">
					[${vo.type }]&nbsp;${vo.subject}
				</td>
				<td class="text-center" width="15%">
					${ vo.name}
				</td>
				<td class="text-center" width="20%">
					${vo.dbday} 
				</td>
				<td class="text-center" width="10%">
					${vo.hit }
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" class="text-center">
					<c:if test="${curPage>1 }">
						<a href="/notice/list?page=${curPage-1 }" class="btn btn-sm btn-primary">이전</a>
					</c:if>
						${curPage } page / ${totalPage} pages
					<c:if test="${curPage<totalPage }">
						<a href="/notice/list?page=${curPage+1 }" class="btn btn-sm btn-primary">다음</a>
					</c:if>		
				</td>
			</tr>
		</tbody>
	</table>           
  
            </div>
        </div>
    </div>
</body>
</html>