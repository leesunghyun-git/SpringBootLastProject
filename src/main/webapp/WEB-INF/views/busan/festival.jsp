<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const SESSION_ID='${sessionScope.userid}'
	const CNO = '${param.contentid}'
</script>
</head>
<body>
 <!-- ****** Breadcumb Area Start ****** -->
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
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/busan/list?contenttype=12"><i class="fa fa-home" aria-hidden="true"></i> 부산</a></li>
                            <li class="breadcrumb-item active" aria-current="page">축제&공연</li>
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
            <div class="row">
            	<table class="table">
            		<tbody>
            			<tr>
            				<td width="30%" class="text-center" rowspan="10">
            					<img src="${vo.image1 }" style="width:100%">
            				</td>
            				<td colspan="2"><h3>${vo.title }</h3></td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">주소</td>
            				<td width="55%">${vo.address }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">시작날짜</td>
            				<td width="55%">${vo.fvo.eventstartdate }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">종료날짜</td>
            				<td width="55%">${vo.fvo.eventenddate }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">연령제한</td>
            				<td width="55%">${vo.fvo.agelimit }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">상영시간</td>
            				<td width="55%">${vo.fvo.playtime }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">소요시간</td>
            				<td width="55%">${vo.fvo.spendtime }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">이용가능시간</td>
            				<td width="55%">${vo.fvo.usetime }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">장소</td>
            				<td width="55%">${vo.fvo.eventplace }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">홈페이지</td>
            				<td width="55%">${vo.fvo.eventhomepage }</td>
            			</tr>
            		</tbody>
            	</table>
            	<table class="table">
            		<tr>
            			<td>${vo.fvo.msg }</td>
            		</tr>
            		<tr>
            			<td class="text-right">
            				<a href="javascript:history.back()" class="btn btn-sm btn-success">목록</a>
            			</td>
            		</tr>
            	</table>
            </div>
        </div>
    </section>
</body>
</html>