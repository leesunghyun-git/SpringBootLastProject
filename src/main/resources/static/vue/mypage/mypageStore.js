const {defineStore} = Pinia
const useMypageStore = defineStore({
	state:()=>({
		reserve_list:[],
		
	}),
	actions:{
		async dataRecv(){
			const {data} = await api.get('/mypage/reserve_list/')
			console.log(data)
			this.reserve_list=data
		}
	}
})