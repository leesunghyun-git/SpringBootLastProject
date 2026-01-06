<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.post-headline{
	overflow:hidden;
	white-space: nowrap;
	text-overflow:ellipsis;
}
</style>
</head>
<body>
<div id="busan_find">
 <!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>부산 여행 검색</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12" style="margin-top:20px">
                    <!-- <nav aria-label="breadcrumb">
                        <ol class="breadcrumb"> -->
	                      	<input type="text" size=20 class="input-sm" v-model="store.address" ref="addressRef" @keyup.enter="store.find(addressRef)">
	                      	<button type="button" class="btn btn-sm btn-primary" @click="store.find(addressRef)">검색</button>
                    <!--     </ol>
                    </nav> -->
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" >
        <div class="container">
            <div class="row">
                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4" v-for="(vo,index) in store.list" :key="index">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.6s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                        	<a :href="`/busan/detail_before?contentid=`+vo.contentid+`&contenttype=`+vo.contenttype">
                            <img :src="vo.image1" style="width:350px;height:240px;">
                            </a>
                     
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a :href="`/busan/detail_before?contentid=`+vo.contentid+`&contenttype=`+vo.contenttype">{{vo.address}}</a>
                                    </div>
<!--                                     Post Date
                                    <div class="post-date">
                                        <a href="#">-</a>
                                    </div> -->
                                </div>
                                <!-- Post Comment & Share Area -->
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a :href="`/busan/detail_before?contentid=`+vo.contentid+`&contenttype=`+vo.contenttype"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                    </div>
                                    <!-- Post Comments -->
                                    <div class="post-comments">
                                        <a :href="`/busan/detail_before?contentid=`+vo.contentid+`&contenttype=`+vo.contenttype"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                    </div>
                                    <!-- Post Share -->
                                    <div class="post-share">
                                        <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <a :href="`/busan/detail_before?contentid=`+vo.contentid+`&contenttype=`+vo.contenttype">
                                <h4 class="post-headline">{{vo.title}}</h4>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">

                            	<li class="page-item" v-if="store.startPage>1">
                                    <a class="page-link" @click="store.movePage(store.startPage-1)" style="cursor:pointer"><i class="fa fa-angle-double-left" aria-hidden="true"></i> Prev</a>
                                </li>

 
                                <li v-for="i in store.range" :class="store.curPage==i?'active page-item':'page-item'" :key="i">
                                    <a class="page-link" @click="store.movePage(i)" style="cursor:pointer">{{i}}</a>
                                </li>

                                <li class="page-item" v-if="store.endPage<store.totalPage">
                                    <a class="page-link" @click="store.movePage(store.endPage+1)" style="cursor:pointer">Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>

                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>Page {{store.curPage}} of {{store.totalPage}} results</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
</div>
	<script src="/vue/axios.js"></script>
	<script src="/vue/busan/busanStore.js"></script>
	<script src="/vue/busan/busanFindAction.js"></script>
</body>
</html>