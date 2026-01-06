const {createApp,onMounted,ref} = Vue
		const {createPinia} = Pinia
		const busanFindApp = createApp({
			setup(){
				// store 읽기
				const store = useBusanStore()
				// ref 읽기
				const addressRef=ref(null)
				// onMounted()
				onMounted(()=>{
					store.busanFindData()
				})
				// return
				return {
					store,
					addressRef
				}
				
			}
		})
busanFindApp.use(createPinia())
busanFindApp.mount('#busan_find')