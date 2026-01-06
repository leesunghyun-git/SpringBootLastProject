const {createApp,onMounted,ref} = Vue
		const {createPinia} = Pinia
		const seoulFindApp = createApp({
			setup(){
				// store 읽기
				const store = useSeoulStore()
				// ref 읽기
				const addressRef=ref(null)
				// onMounted()
				onMounted(()=>{
					store.seoulFindData()
				})
				// return
				return {
					store,
					addressRef
				}
				
			}
		})
seoulFindApp.use(createPinia())
seoulFindApp.mount('#seoul_find')