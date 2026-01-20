const {defineStore} = Pinia
const useAdminStore = defineStore({
	state:()=>({
		reserve_list:[],
		
	}),
	actions:{
		async dataRecv(){
			const {data} = await api.get('/admin/reserve_list/')
			console.log(data)
			this.reserve_list=data
		}
	}
})