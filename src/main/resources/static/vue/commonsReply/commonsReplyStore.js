const {defineStore} = Pinia
const useCommonseReplyStore=defineStore('commons_reply',{
	// 공통 사용변수 => 변경이 될때마다 HTML에 반영
	//							 ----- mount()
	state:()=>({
		list:[],
		cno:0,
		curPage:1,
		startPage:0,
		endPage:0,
		totalPage:0,
		sessionId:'',
		msg:'',
		updateMsg:{},
		upReplyNo:null,
		count:0
	}),
	getters:{
		range:(state)=>{
			const arr = []
			for (let i=state.startPage;i<=state.endPage;i++)
			{
				arr.push(i)
			}
			return arr
		}
	},
	actions:{
		async commonsReplyList(){
			const {data} = await api.get('/commons/list_vue/',{
				params:{
					cno:this.cno,
					page:this.curPage
				}
			})
			this.setPageData(data)
		},
		setPageData(data){
			this.list = data.list
			this.curPage = data.curPage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.totalPage = data.totalPage
			this.count = data.count
		},
		async commonsInsert(msgRef){
			if(this.msg==='')
			{
				msgRef?.focus()
				return
			}
			const {data} = await api.post('/commons/insert_vue/',{
				cno:this.cno,
				msg:this.msg,
				page:this.curPage
			})
			this.setPageData(data)
			this.msg=''
		},
		movePage(page){
			this.curPage=page
			this.commonsReplyList(page)
		},
		setCno(cno){
			this.cno = cno
		},
		async commonsDelete(no){
			const {data} = await api.delete('/commons/delete_vue/',{
				params:{
					no:no,
					cno:this.cno,
					page:this.curPage
				}
			})
			this.setPageData(data)
		},
		toggleUpdate(no,msg){
			this.upReplyNo=this.upReplyNo===no?null:no
			this.updateMsg[no]=msg
		},
		async commonsUpdate(no){
			const {data} = await api.put('/commons/update_vue/',{
				no:no,
				cno:this.cno,
				msg:this.updateMsg[no],
				page:this.curPage
			})
			this.upReplyNo=null
			this.setPageData(data)
		}
	}
	
})