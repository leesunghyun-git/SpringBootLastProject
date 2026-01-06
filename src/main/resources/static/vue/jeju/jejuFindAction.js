const {createApp,onMounted,ref} = Vue
		const {createPinia} = Pinia
		const jejuFindApp = createApp({
			setup(){
				// store 읽기
				const store = useJejuStore()
				// ref 읽기
				const fdRef=ref(null)
				// onMounted()
				onMounted(()=>{
					store.jejuFindData()
				})
				// return
				return {
					store,
					fdRef
				}
				
			}
		})
jejuFindApp.use(createPinia())
jejuFindApp.mount('#jeju_find')