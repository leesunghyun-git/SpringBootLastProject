<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	const SESSION_ID='${sessionScope.userid}'
	const CNO = '${param.contentid}'
</script>
<title>Insert title here</title>
<style type="text/css">
.page-link {
	cursor:pointer;
}
.a-btn {
	cursor:pointer;
}
</style>
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
                            <li class="breadcrumb-item"><a href="/seoul/list?contenttype=12"><i class="fa fa-home" aria-hidden="true"></i> 서울</a></li>
                            <li class="breadcrumb-item active" aria-current="page">관광지</li>
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
            				<td width="30%" class="text-center" rowspan="6">
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
            				<td width="55%">${vo.avo.infocenter }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">휴무일</td>
            				<td width="55%">${vo.avo.restdate }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">사용시간</td>
            				<td width="55%">${vo.avo.usetime }</td>
            			</tr>
            			<tr>
            				<td width="15%" class="text-center">주차</td>
            				<td width="55%">${vo.avo.parking }</td>
            			</tr>
            		</tbody>
            	</table>
            	<table class="table">
            		<tr>
            			<td>${vo.avo.msg }</td>
            		</tr>
            		<tr>
            			<td class="text-right">
            				<a href="javascript:history.back()" class="btn btn-sm btn-success">목록</a>
            			</td>
            		</tr>
            		<tr>
            			<td class="text-center">
            				<div class="map_wrap">
							    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
							
							    <div id="menu_wrap" class="bg_white">
							        <div class="option">
							            <div>
							                <form onsubmit="searchPlaces(); return false;">
							                    키워드 : <input type="text" value="${addr } 맛집" id="keyword" size="15"> 
							                    <button type="submit">검색하기</button> 
							                </form>
							            </div>
							        </div>
							        <hr>
							        <ul id="placesList"></ul>
							        <div id="pagination"></div>
							    </div>
							</div>
            			</td>
            		</tr>
            	</table>
      	       <!-- Comment Area Start -->
      	          
        	</div>
        	<div id="comment">
                      <div class="comment_area section_padding_50 clearfix">
                          <h4 class="mb-30">댓글 ({{store.count	}})</h4>

                          <ol>
                              <!-- Single Comment Area -->
                              <li class="single_comment_area" v-for="(rvo,index) in store.list" :key="index">
                                  <div class="comment-wrapper d-flex">
                                      <!-- Comment Meta -->
                                      <div class="comment-author">
                                          <img src="/img/man.png" v-if="rvo.sex==='남자'">
                                          <img src="/img/woman.png" v-if="rvo.sex==='여자'">
                                      </div>
                                      <!-- Comment Content -->
                                      <div class="comment-content">
                                          <span class="comment-date text-muted">{{rvo.dbday}}</span>
                                          <h5>{{rvo.name}}</h5>
                                          <p>{{rvo.msg}}</p>
                                          <a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
                                          <a  @click="store.commonsDelete(rvo.no)" class="active a-btn"  v-if="store.sessionId===rvo.id	">삭제</a>
                                          <a  @click="" class="a-btn">댓글</a>
                                          <div class="comment-form" v-if="store.upReplyNo===rvo.no">
			                                <textarea name="message" cols="60" rows="3" placeholder="Message" style="float: left;display: inline-block;resize: none;padding-left:10px;" v-model="store.updateMsg[rvo.no]" ref="msg"></textarea>
			                                <button type="button" class="btn-primary" style="float: left;width: 80px;height: 75px;display: inline-block;" @click="store.commonsUpdate(rvo.no)">댓글수정</button>
			                              </div>
                                      </div>
                                  </div> 
                              </li>
                          </ol>
                      </div>

                      <!-- Leave A Comment -->
                      <div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!==''" style="padding-left:180px;">
                          <div class="comment-form">
                            <textarea name="message" cols="80" rows="5" placeholder="Message" style="float: left;display: inline-block;resize: none;padding-left:10px;" v-model="store.msg" ref="msgRef"></textarea>
                            <button type="submit" class="btn-primary" style="float: left;width: 80px;height: 122px;display: inline-block;" @click="store.commonsInsert(msgRef)">댓글쓰기</button>
                          </div>
                      </div>
                      <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            	<li class="page-item" v-if="store.startPage>1">
                                    <a class="page-link" @click="store.movePage(store.startPage-1)"><i class="fa fa-angle-double-left" aria-hidden="true"></i> Prev</a>
                                </li>
                                <li :class="i===store.curPage?'page-item active':'page-item'" v-for="(i,index) in store.range" :key="index">
                                    <a class="page-link" @click="store.movePage(i)">{{i}}</a>
                                </li>
                                <li class="page-item" v-if="store.endPage<store.totalPage">
                                    <a class="page-link" @click="store.movePage(store.endPage+1)">Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
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
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ba7c1bf5703be19d075b1df1555ef2f&libraries=services"></script>
		<script src="/vue/map.js"></script>
		<script src="/vue/axios.js"></script>
		<script src="/vue/commonsReply/commonsReplyStore.js"></script>
		<script>
			const {createApp,onMounted,ref} = Vue
			const {createPinia} = Pinia
			const commonsReplyApp = createApp({
				setup(){
					const store=useCommonseReplyStore()
					const msgRef = ref(null)
					onMounted(()=>{
						store.cno = CNO
						store.sessionId = SESSION_ID
						store.commonsReplyList()
					})
					return{
						store,
						msgRef
					}
				}
			})
			commonsReplyApp.use(createPinia())
			commonsReplyApp.mount('#comment')
			
		</script>
    </section>
</body>
</html>