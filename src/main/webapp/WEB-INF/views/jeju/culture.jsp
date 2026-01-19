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
                            <li class="breadcrumb-item"><a href="/jeju/list?contenttype=12"><i class="fa fa-home" aria-hidden="true"></i> 제주</a></li>
                            <li class="breadcrumb-item active" aria-current="page">문화 시설</li>
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
            				<td width="30%" class="text-center" rowspan="9">
            					<img src="${vo.image1 }" style="width:100%">
            				</td>
            				<td colspan="2"><h3>${vo.title }</h3></td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">주소</td>
            				<td width="55%">${vo.address }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">안내</td>
            				<td width="55%">${vo.cvo.infocenter }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">요금</td>
            				<td width="55%">${vo.cvo.usefee }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">할인</td>
            				<td width="55%">${vo.cvo.discountinfo }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">소요시간</td>
            				<td width="55%">${vo.cvo.spendtime }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">주차</td>
            				<td width="55%">${vo.cvo.parking }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">주차요금</td>
            				<td width="55%">${vo.cvo.parkingfee }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">사용 가능 시간</td>
            				<td width="55%">${vo.cvo.usetime }</td>
            			</tr>
            		</tbody>
            	</table>
            	<table class="table">
            		<tr>
            			<td>${vo.cvo.msg }</td>
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