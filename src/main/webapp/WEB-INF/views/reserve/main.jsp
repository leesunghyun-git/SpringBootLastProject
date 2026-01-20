<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
<script>
const CNO = '${param.cno}'
const LOC = '${param.loc}'
</script>
<style type="text/css">
.post-headline{
	overflow:hidden;
	white-space: nowrap;
	text-overflow:ellipsis;
}
.card{
  box-shadow:0 4px 10px rgba(0,0,0,0.05);
  border:none;
  border-radius:12px;
}
.card-header{
  font-weight:700;
  font-size:1.1rem;
}
#food_list{
  height:600px;
  overflow-y:auto;
}
.food-item:hover{
  background-color:#f1f1f1;
  cursor:pointer;
}
img#food_poster{
  border-radius:8px;
}
.r_select{
	min-heigth:48px;
	font-size:16px;
	border-radius: 6px;
	border:1px solid #ddd;
	font-weight: bold
}
.r_select:hover{
	border-color:#5cb85c;
	box-shadow: 0 0 5px rgba(92,184,92,0.4);
}
.link:hover{
	cursor:pointer;
	background-color:orange 
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
                        <h2>맛집 예약</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav" id="reserve_div">
        <div class="container my-4">
    <div class="row g-4">
      <!-- 맛집 정보 -->
      <div class="col-md-4">
        <div class="card h-100">
          <div class="card-header bg-danger text-white text-center">맛집 정보</div>
          <div class="card-body" id="food_list">
           	<table class="table">
           		<tr>
           			<td class="text-center" colspan="2">
           				<select class="form-control r_select" v-model="store.loc" @change="store.changeLoc">
           					<option value="all">서울전체</option>
           					<option v-for="d in store.loc_list" :value="d">{{d}}</option>
           				</select>
           			</td>
           		</tr>
           		<tr v-for="(vo,index) in store.food_list" :key="index" @click="store.select(vo)" class="link">
           			<td>
         				<img :src="vo.image1" style="min-width:60px;min-height:60px;max-width:60px;max-height: 60px;" class="img-rounded">
           			</td>
           			<td style="line-height: 30px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">{{vo.title}}<br>{{vo.address}}</td>
           		</tr>
           		<tr>
           			<td colspan="2" class="text-center">
           				<button type="button" @click="store.prev" class="btn btn-success btn-sm" v-if="store.curPage>1">이전</button>
           				{{store.curPage}} page / {{store.totalPage}} pages
           				<button type="button" @click="store.next" class="btn btn-info btn-sm" v-if="store.curPage<store.totalPage">다음</button>
           			</td>
           		</tr>
           	</table>
          </div>
        </div>
      </div>

      <!-- 예약일 정보 -->
      <div class="col-md-5">
        <div class="card h-100">
          <div class="card-header bg-info text-white text-center">예약일 정보</div>
          <div class="card-body text-center" id="food_rdays">
            <div id="calendar">
            	
            </div>
          </div>
        </div>
      </div>

      <!-- 예약 정보 -->
      <div class="col-md-3">
        <div class="card h-100">
          <div class="card-header bg-success text-white text-center">예약 정보</div>
          <div class="card-body text-center">
            <table class="table table-borderless text-start" v-if="store.isDate" id="reserve_info">
              <tbody>
              	<tr><td colspan="2"><img :src="store.image" style="width:200px;height:200px;"></td></tr>
                <tr><td class="text-muted">업체명</td><td id="food_name">{{store.title}}</td></tr>
                <tr><td class="text-muted">예약일</td><td id="food_reserve_day">{{store.day}}</td></tr>
                <tr><td class="text-muted">예약시간</td><td id="food_reserve_time">{{store.time}}</td></tr>
                <tr><td class="text-muted">예약인원</td><td id="food_reserve_inwon">{{store.inwon}}</td></tr>
              	<tr><td colspan="2" class="text-right"><button type="button" class="btn btn-success btn-sm" v-if="store.isReserveBtn" @click="store.reserveInsert">예약하기</button></td></tr>
              </tbody>
            </table>
            
          </div>
        </div>
      </div>
    </div>

    <div class="row g-4 mt-3">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header bg-primary text-white text-center">시간 정보</div>
          <div class="card-body text-center">
            <div class="d-flex justify-content-center gap-2 flex-wrap" id="reserve_time2" v-if="store.isTime">
              <span class="btn btn-xs btn-success" v-for="(t,i) in store.time_list" :key="i" @click="store.timeSelect(t)" style="margin-left:3px;">{{t}}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card">
          <div class="card-header bg-info text-white text-center">인원 정보</div>
          <div class="card-body text-center">
            <div class="d-flex justify-content-center gap-2 flex-wrap" id="reserve_time2" v-if="store.isInwon">
              <span class="btn btn-xs btn-info" v-for="(t,i) in store.inwon_list" :key="i" @click="store.inwonSelect(t)" style="margin-left:3px;margin-top:3px;">{{t}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
    </div>
    <script src="/vue/axios.js"></script>
    <script src="/vue/reserve/reserveStore.js"></script>
	<script>
		const {createPinia} = Pinia
		const {createApp,onMounted,ref,watch} = Vue
		const reserveApp = createApp({
			setup(){
				const store = useReserveStore()
				onMounted(()=>{
				if(CNO !==null)
				{
					store.cno = CNO
					store.loc = LOC
					store.firstLink()
				}
				store.dataRecv()
		        })
		        watch(()=>store.cno,(newVal)=>{
					if(!newVal) return
					
					const calendar=new FullCalendar.Calendar(
		            document.getElementById('calendar'), {
		               initialView: 'dayGridMonth',
		               height: 450,
		               validRange: {
		                  start: new Date().toISOString().split("T")[0]
		               },
		               dateClick(info){
							/* alert(info.dateStr) */
							store.dateSelect(info.dateStr)
							store.timeListData()
							store.isTime=true
		           	}
		        	})
					calendar.render()
		        })
				
			return{
				store
			}
			}
		})
		reserveApp.use(createPinia())
		reserveApp.mount('#reserve_div')
	</script>
</body>
</html>