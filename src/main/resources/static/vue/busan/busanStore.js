const {defineStore} = Pinia
// store => 저장 공간 (출력에 필요한 데이터를 모아서 관리)
// static 변수 => 멤버
const initalStore=()=>({
	list:[], // 검색된 데이터
	curPage:1,
	totalPage:0,
	endPage:0,
	startPage:0,
	address:'강서구'
})
const useBusanStore = defineStore('busan_find',{
	// 모든 컴포넌트에서 사용이 가능하게 공유 변수 설정
	state:initalStore,
	getters:{
		range:(state)=>{
			const arr = []
			for(let i=state.startPage;i<=state.endPage;i++)
			{
				arr.push(i) // 맨뒤에 값을 설정
			}
			return arr
		}
	}, // 기능 => 사용자 요청 처리
	actions:{
		// 서버로부터 요청값 받기
		
		async busanFindData(){
			const {data} = await api.get('/busan/find_vue/',{
				params:{
					page:this.curPage,
					address:this.address
				}
			})
			this.setPageData(data)
		},
		setPageData(data){
			this.list = data.list
			this.curPage = data.curPage
			this.totalPage = data.totalPage
			this.endPage = data.endPage
			this.startPage = data.startPage
			this.address = data.address
		},
		// prev / next / pageChange
		movePage(page){
			this.curPage=page
			this.busanFindData()
		},
		find(addressRef){
			if(this.address==='')
			{
				addressRef?.focus()
				return
			}
			this.curPage=1
			this.busanFindData()
		}
	}
})