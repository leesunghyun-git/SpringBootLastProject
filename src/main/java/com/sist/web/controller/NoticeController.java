package com.sist.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.NoticeService;
import com.sist.web.vo.NoticeVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller/*
	1. Spring-Boot
	 	= ORM (Mybatis)
	 			| - CRUD / 동적 쿼리
	 	= RestFul => Get / Post / Put / Delete
	 	= FileUpload
	 	= WebSocket : SockJS / Stomp = > stomp
	 	= Kafka
	 	= Tast / JUnit
	-----------------------------------------------
		Batch / Spring AI	: Ajax , Vue , React
		=> ThymeLeaf
		=> oracle / MySQL,Mariadb
					----- JPA
	---------------------------------------------
*/
public class NoticeController {
	private final NoticeService nService;
	@GetMapping("/admin/notice")
	public String admin_notice(Model model,@RequestParam(value="page",required = false)String page) {
		if(page ==null)
			page="1";
		int curPage= Integer.parseInt(page);
		List<NoticeVO> list=nService.noticeListData((curPage-1)*10);
		int totalPage = nService.noticeTotalPage();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("list", list);
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/notice_list.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@GetMapping("/admin/notice_insert")
	public String admin_notice_insert(Model model) {
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/notice_insert.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
		
	}
	@PostMapping("/admin/notice_insert_ok")
	public String postMethodName(@ModelAttribute NoticeVO vo,HttpServletRequest request) throws Exception{
		//TODO: process POST request
		String uploadDir=request.getServletContext().getRealPath("/upload");
		File dir = new File(uploadDir);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		List<MultipartFile> files = vo.getFiles();
		String filename="";
		boolean bCheck = false;
		for(MultipartFile file:files)
		{
			if(file.isEmpty())
			{
				bCheck=false; // 파일이 없는 상태
			}
			else {
				String oname = file.getOriginalFilename();
				File f=new File(uploadDir+"/"+oname);
				
				if(f.exists())
				{
					String name = oname.substring(0,oname.lastIndexOf("."));
					String ext = oname.substring(oname.lastIndexOf("."));
					int count=1;
					while(f.exists())
					{
						String newName= name+"("+count+")"+ext;
						f=new File(uploadDir+"/"+newName);
						count++;
					}
				}
				bCheck=true;
				filename+=f.getName()+",";
				Path path=Path.of(uploadDir,f.getName());
				Files.copy(file.getInputStream(), path);
			}
		}
		if(bCheck==true)
		{
			filename=filename.substring(0,filename.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilecount(files.size());
		}
		else {
			vo.setFilename("");
			vo.setFilecount(0);
		}
		nService.noticeInsert(vo);
		return "redirect:/admin/notice";
	}
	@GetMapping("/notice/list")
	public String notice_list(Model model,@RequestParam(value="page",required = false)String page) {
		if(page ==null)
			page="1";
		int curPage= Integer.parseInt(page);
		List<NoticeVO> list=nService.noticeListData((curPage-1)*10);
		int totalPage = nService.noticeTotalPage();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("list", list);
		model.addAttribute("curCat", "board");
		model.addAttribute("main_jsp", "../notice/list.jsp");
		return "main/main";
	}
	@GetMapping("/admin/notice_detail")
	public String admin_notice_detail(Model model,@RequestParam("no")int no) {
		NoticeVO vo = nService.noticeAdminDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/notice_detail.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
		
	}
	@GetMapping("/notice/detail")
	public String notice_detail(Model model,@RequestParam("no")int no) {
		NoticeVO vo = nService.noticeUserDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("main_jsp", "../notice/detail.jsp");
		return "main/main";
		
	}
	@GetMapping("/admin/notice_delete")
	public String admin_notice_delete(@RequestParam("no")int no,HttpServletRequest request) {
		NoticeVO vo = nService.noticeFileData(no);
		if(vo.getFilecount()>0)
		{
			String path = request.getServletContext().getRealPath("/upload");
			StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
			while(st.hasMoreTokens())
			{
				File f=new File(path+"/"+st.nextToken());
				f.delete();
			}
			
		}
		nService.noticeDeleteData(no);
		return "redirect:/admin/notice";
		
	}
	@GetMapping("/admin/notice_update")
	public String admin_notice_update(Model model,@RequestParam("no")int no) {
		NoticeVO vo = nService.noticeAdminDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "adminpage");
		model.addAttribute("admin_jsp", "../admin/notice_update.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
		
	}
	@PostMapping("/admin/notice_update_ok")
	public String adminc_notice_update_Ok(@ModelAttribute NoticeVO vo,RedirectAttributes ra)
	{
		nService.noticeUpdate(vo);
		ra.addAttribute("no", vo.getNo());
		return "redirect:/admin/notice_detail";
	}
	
}
