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
		},
		async reserveOk(no,id) {
					const {data} = await api.get('/admin/reserve_ok_vue/',{
						params:{
							no:no,
							id:id
						}
			})
			this.reserve_list = data
		},
		async reserveDelete(no,id) {
			const {data} = await api.delete('/admin/reserve_delete_vue/',{
				params:{
					no:no,
					id:id
				}
			})
			this.reserve_list = data
		}
		
	}
})