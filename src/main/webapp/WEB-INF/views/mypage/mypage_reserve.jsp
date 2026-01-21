<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const ID='${sessionScope.userid}'
</script>
<style type="text/css">
.toast-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 9999;
}

/* Toast 박스 */
.toast {
  width: 320px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 6px 20px rgba(0,0,0,0.15);
  overflow: hidden;
  opacity: 0;
  transform: translateY(20px);
  transition: all 0.4s ease;
  pointer-events: none;
}

/* 보여질 때 */
.toast.show {
  opacity: 1;
  transform: translateY(0);
  pointer-events: auto;
}

/* Header */
.toast-header {
  background: #28a745; /* success */
  color: white;
  padding: 10px 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* 제목 */
.toast-title {
  font-size: 15px;
  font-weight: bold;
}

/* 닫기 버튼 */
.toast-close {
  background: transparent;
  border: none;
  color: white;
  font-size: 18px;
  cursor: pointer;
}

/* Body */
.toast-body {
  padding: 14px;
  font-size: 14px;
  color: #333;
}
</style>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center"><h3>예약 목록</h3></td>
		</tr>
	</table>
	<div  id="reserveApp">
	<div class="row">
		<table class="table">
			<thead>
				<tr class="danger">
					<th class="text-center">예약번호</th>
					<th class="text-center">업체명</th>
					<th class="text-center"></th>
					<th class="text-center">예약일</th>
					<th class="text-center">예약시간</th>
					<th class="text-center">인원</th>
					<th class="text-center">등록일</th>
					<th class="text-center"></th>				
				</tr>
			</thead>
			<tbody>
				<tr v-for="(vo,i) in store.reserve_list" :key="i" >
					<td class="text-center">{{vo.no}}</td>
					<td class="text-center">{{vo.svo.title}}</td>
					<td class="text-center"><img :src="vo.svo.image1" style="width:30px;height:30px;"></td>
					<td class="text-center">{{vo.rday}}</td>
					<td class="text-center">{{vo.rtime}}</td>
					<td class="text-center">{{vo.rinwon}}</td>
					<td class="text-center">{{vo.dbday}}</td>
					<td class="text-center">
						<button class="btn btn-xs btn-info" v-if="vo.isreserve===0">예약 대기</button>
						<button class="btn btn-xs btn-info" @click="store.reserveDetail(vo.no)" v-else>예약 완료</button>
						<button class="btn btn-xs btn-danger" v-if="vo.iscancel===0" @click="store.reserveCancel(vo.no)">취소</button>
						<button class="btn btn-xs btn-danger" v-else disabled="disabled">취소 대기</button>
					</td>				
				</tr>
			</tbody>
		</table>
		<div v-if="store.isShow">
		<table class="table">
			<tbody>
				<tr>
					<th><h3>예약 정보</h3></th>
				</tr>
				<tr>
					<th class="text-center">예약 번호</th>
					<td class="text-center">{{store.reserve_detail.no}}</td>
					<th class="text-center">예약일</th>
					<td class="text-center">{{store.reserve_detail.rday}}</td>
					<th class="text-center">예약 시간</th>
					<td class="text-center">{{store.reserve_detail.rtime}}</td>
					<th class="text-center">예약인원</th>
					<td class="text-center">{{store.reserve_detail.rinwon}}</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
		 		<tbody>
		 			<tr>
		 				<th colspan="3"><h3>맛집 정보</h3></th>
		 			</tr>
		 			<tr>
		 				<td width="30%" class="text-center" rowspan="2">
		 					<img :src="store.reserve_detail.svo.image1 " style="width:100%">
		 				</td>
		 				<td colspan="2"><h3>{{store.reserve_detail.svo.title }}</h3></td>
		 			</tr>
		 			<tr>
		 				<td width="15%" class="text-center">주소</td>
		 				<td width="55%">{{store.reserve_detail.svo.address }}</td>
		 			</tr>
		 			<tr>
		 				<td colspan="2">
		 					<button type="button" class="btn btn-sm btn-danger" @click="store.isShow=false">닫기</button>
		 				</td>
		 			</tr>
		 		</tbody>
		 	</table>
		 	</div>
		 	<div class="toast-container">
			  <div id="reserveToast" class="toast">
			    <div class="toast-header">
			      <strong class="toast-title">예약 알림</strong>
			      <button class="toast-close" onclick="hideToast()">×</button>
			    </div>
			    <div class="toast-body" id="toastMsg"></div>
			  </div>
			</div>
	</div>
	</div>
	<script src="/vue/axios.js"></script>
	<script src="/vue/mypage/mypageStore.js"></script>
	<script>
	const {createPinia} = Pinia
	const {createApp,onMounted} = Vue
	const mypageApp = createApp({
		setup(){
		const store = useMypageStore()
		onMounted(()=>{
			store.dataRecv()
			store.connect(ID)
		})
		return {
			store
		}
		}
		
	})
	mypageApp.use(createPinia())
	mypageApp.mount('#reserveApp')
	</script>
</body>
</html>