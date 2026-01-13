<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <h2>새글 작성</h2>
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
                            <li class="breadcrumb-item"><a href="/board/list"><i class="fa fa-home" aria-hidden="true"></i> 자유 게시판</a></li>
                            <li class="breadcrumb-item active" aria-current="page">새글 작성</li>
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
            	<form action="/board/insert_ok" method="post">
            	<table class="table">
            		<tbody>
            			<tr>
            				<th width="15%" class="danger">이름</th>
            				<td width="85%" class="text-left">
            					<input type="text" name="name" size="20" class="input-sm" required>
            				</td>
            			</tr>
            			<tr>
            				<th width="15%" class="danger">제목</th>
            				<td width="85%" class="text-left">
            					<input type="text" name="subject" size="60" class="input-sm" required>
            				</td>
            			</tr>
            			<tr>
            				<th width="15%" class="danger">내용</th>
            				<td width="85%" class="text-left">
            					<textarea rows=10 cols= 60 name=content required style="resize:none"></textarea>
            				</td>
            			</tr>
            			<tr>
            				<th width="15%" class="danger">비밀번호</th>
            				<td width="85%" class="text-left">
            					<input type="password" name="pwd" size="20" class="input-sm" required>
            				</td>
            			</tr>
            			<tr>
            				<td class="text-center" colspan=2>
            					<a href="/board/list" class="btn btn-sm btn-primary">취소</a>
            					<button type="submit" class="btn btn-sm btn-success">작성</button>
            				</td>
            			</tr>
            		</tbody>
            	</table>
            	</form>
            </div>
         </div>
       </section>
</body>
</html>