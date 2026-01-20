const {defineStore} = Pinia

const useReserveStore = defineStore({
	state:()=>({
		loc_list:		[
		  "강남구","강동구","강북구","강서구",
		  "관악구","광진구","구로구","금천구",
		  "노원구","도봉구","동대문구","동작구",
		  "마포구","서대문구","서초구","성동구",
		  "성북구","송파구","양천구","영등포구",
		  "용산구","은평구","종로구","중구","중랑구"
		],
		loc:'all',
		curPage:1,
		totalPage:0,
		food_list:[],
		time_list:[],
		image:'',
		title:'',
		cno:null,
		day:'',
		time:'',
		inwon:'',
		isDate:false,
		isTime:false,
		isInwon:false,
		isReserveBtn:false,
		time_list:[], // 랜덤
		inwon_list:[
			"2명","3명","4명","5명","6명","7명","8명","9명","단체"
		]
		
	}),
	actions:{
		dateSelect(info){
			this.day = info
		},
		async dataRecv(){
			const {data}=await api.get('/reserve/food_list_vue/',{
				params:{
					address:this.loc,
					page:this.curPage
				}
			})
			this.food_list = data.list
			this.totalPage= data.totalPage
		},
		prev(){
			if(this.curPage>1)
			{
				this.curPage=this.curPage-1
			}
			else{
				this.curPage=this.curPage
			}
			this.dataRecv()
		},
		next(){
			if(this.curPage<this.totalPage)
			{
				this.curPage=this.curPage+1
			}
			else{
				this.curPage=this.curPage
			}
			this.dataRecv()
		},
		changeLoc(){
			this.curPage=1
			this.dataRecv()
		},
		select(vo){
			this.image=vo.image1
			this.title=vo.title
			this.cno=vo.contentid
			this.isDate=true
		},
		async timeListData(){
			const {data} = await api.get('/reserve/time_list_vue/')
			this.time_list=data.list
			
		},
		timeSelect(time){
			this.time = time
			this.isInwon=true
		},
		inwonSelect(inwon){
			this.inwon=inwon
			this.isReserveBtn=true
		},
		// 예약
		async reserveInsert(){
			const {data} = await api.post('/reserve/insert_vue/',{
				cno:this.cno,
				rday:this.day,
				rtime:this.time,
				rinwon:this.inwon
			})
			if(data==='YES')
			{
				alert('예약 되었습니다.')
				location.href="/mypage/mypage_reserve"
			}
			else
			{
				alert('예약에 실패 했습니다.')
				this.cno=null
				this.title=''
				this.day=''
				this.time=''
				this.inwon=''
				this.image=''
				this.isDate=false
				this.isTime=false
				this.isInwon=false
				this.isReserveBtn=false	
			}
		}
		
		
		
	}
})