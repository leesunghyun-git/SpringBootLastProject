const {defineStore} = Pinia
const useMypageStore = defineStore({
	state:()=>({
		reserve_list:[],
		isShow:false,
		reserve_detail:{},
		stomp:null
	}),
	actions:{
		connect(id){
			const sock = new SockJS("/ws")
			this.stomp = Stomp.over(sock)
			
			this.stomp.connect({},()=>{
				this.stomp.subscribe('/sub/notice/'+id,(msg)=>{
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
		},
		async dataRecv(){
			const {data} = await api.get('/mypage/reserve_list/')
			console.log(data)
			this.reserve_list=data
		},
		async reserveCancel(no){
				const {data} = await api.get('/mypage/reserve_cancel_vue/',{
					params:{
						no:no
					}
				})
				this.reserve_list = data
		},
		async reserveDetail(no){
			const {data}=await api.get('/mypage/reserve_detail_vue/',{
				params:{
					no:no
				}
			})
			this.reserve_detail=data	
			this.isShow=true		
		},
		showToast(message){
		  const toast = document.getElementById("reserveToast")
		  const toastMsg = document.getElementById("toastMsg")

		  toastMsg.innerText = message;
		  toast.classList.add("show");

		  // 3초 후 자동 닫힘
		  
		  setTimeout(() => {
		     hideToast()
		  }, 5000);
		}
	
		
	}
})
function hideToast() {
	const toast = document.getElementById("reserveToast");
	toast.classList.remove("show");
}