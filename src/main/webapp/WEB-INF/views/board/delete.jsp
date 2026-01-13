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
                        <h2>상세 보기</h2>
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
                            <li class="breadcrumb-item active" aria-current="page">상세 보기</li>
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
            <div class="row" style="width:400px;margin: 0px auto;">
            	<form action="/board/delete_ok" method="post">
            	<table class="table">
            		<tbody>
            			<tr>
            				<td class="text-center">
            					비밀번호: <input type="password" name="pwd" size="15" class="input-sm" required>
            					<input type="hidden" name="no" value="${no }">
            				</td>
            			</tr>
            			<tr>
            				<td class="text-center">	
            					<button type="submit" class="btn btn-sm btn-warning">삭제</button>
            					<button type="button" class="btn btn-sm btn-primary" onclick="javascript:history.back()">취소</button>
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