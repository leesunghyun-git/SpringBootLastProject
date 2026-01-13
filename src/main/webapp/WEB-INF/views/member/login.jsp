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
   <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>회원가입</h2>
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
                            <%-- 검색기 --%>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" id="join_section">
        <div class="container" style="max-width: 800px; margin-top: 40px;">
        	<div class="row justify-content-center">
        		<form action="/member/login_process" method="post">
        		<table class="table"  style="width:460px;">
        			<tbody>
        				<tr>
        					<th class="text-center" width="20%">ID</th>
        					<td width="80%"><input type="text" size=20 class="input-sm" name="userid"></td>
        				</tr>
        				<tr>
        					<th class="text-center" width="20%">Password</th>
        					<td width="80%"><input type="password" size=20 class="input-sm" name="userpwd"></td>
        				</tr>
        				<tr>
        					<td colspan="2">
        						<input type="checkbox" name="remember-me"> 자동로그인
        					</td>
        				</tr>
        				<c:if test="${message!=null }">
        				<tr>
        					<td colspan="2" style="color:red">
        					${message}
        					</td>
        				</tr>
        				</c:if>
        				<tr>
        					<td colspan="2" class="text-right">
        						<button class="btn-sm btn-warning" type="submit">로그인</button>
        						<button class="btn-sm btn-danger" type="button" onclick="javascript:history.back()">뒤로가기</button>
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