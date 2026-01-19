const {defineStore} = Pinia
const useBoardReplyStore = defineStore('board_reply',{
	// static => 공통으로 사용되는 변수
	// react => useState('')
	state:()=>({
		list:[],
		count:0,
		bno:0,
		msg:'',
		upReplyNo:null,
		updateMsg:{},
		sessionId:'',
		reReplyNo:null,
		reReplyMsg:{}
	}),
	actions:{
		toggleUpdate(no,msg){
			this.upReplyNo=this.upReplyNo===no?null:no
			this.updateMsg[no]=msg
			this.reReplyNo=null
		},
		
		async replyListData(bno){
			this.bno=bno
			const {data} = await api.get('/reply/list_vue/',{
				params:{
					bno:this.bno
				}
			})
			this.list = data.list
			this.count= data.count
		},
		async insertReply(msgRef){
			if(this.msg=='')
			{
				msgRef?.focus()
				return
			}
			const {data} = await api.post('/reply/insert_vue/',{
				bno:this.bno,
				msg:this.msg,
				id:this.sessionId
			})
			this.list=data.list
			this.count=data.count
			this.msg =''
		},
		async replyUpdate(no){
			if(!this.updateMsg[no]){
				return	
			}
			const {data} = await api.put("/reply/update_vue/",{
				no:no,
				bno:this.bno,
				msg:this.updateMsg[no]
			})
			this.list = data.list
			this.count = data.count
			this.upReplyNo=null
			this.updateMsg[no] = null
		},
		async replyDelete(no){
			const {data} = await api.delete('/reply/delete_vue/',{
				params:{
					no:no,
					bno:this.bno
				}
			})
			this.list = data.list
			this.count = data.count
		}
		
		
	}	
	
})