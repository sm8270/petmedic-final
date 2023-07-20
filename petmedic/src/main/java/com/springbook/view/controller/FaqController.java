package com.springbook.view.controller;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.pet.faq.FaqService;
import com.spring.pet.faq.FaqVO;
import com.spring.pet.notice.NoticeVO;

@Controller
public class FaqController {

	@Autowired @Qualifier("faqService")
	public FaqService faqService;
	
	// [관리자] 자주묻는질문 등록
	@RequestMapping("/insertFAQ")
	public String insertFAQ(FaqVO vo) {
		System.out.println("FAQ insert 컨트롤러 시작");
		String faq_cont = vo.getFaq_cont();
		faq_cont = faq_cont.replace("\r\n","<br>");
		vo.setFaq_cont(faq_cont);
		faqService.insertFAQ(vo);
		System.out.println("FAQ insert 컨트롤러 끗");
		return "faq/getFaqList_admin";
	}

	// [관리자] 수정페이지로 보내줘
	@RequestMapping("/updateFAQ")
	public String updateFAQForm(FaqVO vo, Model model) {
		System.out.println("업데이트 폼으로 나를 보내줘");
		System.out.println("내 데이터좀 가져와줘");
		
		vo = faqService.getFaq(vo);
	    String faq_cont = vo.getFaq_cont();
	    faq_cont = faq_cont.replace("<br>","\r\n");
	    vo.setFaq_cont(faq_cont);
		model.addAttribute("faqList", vo);
		
		return "faq/updateFAQ";
	}
	
	// [관리자] 자주묻는질문 수정
	@RequestMapping("/modiFAQ")
	public String updateFAQ(FaqVO vo) {
		System.out.println("FAQ update 컨트롤러 시작");
		System.out.println("몇번 글을 수정할거니? " + vo.getFaq_int());
		String faq_cont = vo.getFaq_cont();
		faq_cont = faq_cont.replace("\r\n","<br>");
		vo.setFaq_cont(faq_cont);
		faqService.updateFAQ(vo);
		System.out.println("vo.get타이틀 변경후: " + vo.getFaq_title());
		System.out.println("FAQ update 컨트롤러 끗");
		return "/faq/getFaqList_admin";
	}

	// [관리자] 자주묻는질문 삭제
	@RequestMapping("/deleteFAQ")
	public String deleteFAQ(FaqVO vo) {
		System.out.println("FAQ delete 컨트롤러 시작");	
		System.out.println("몇번 글을 삭제할거니? " + vo.getFaq_int());
		faqService.deleteFAQ(vo);
		System.out.println("FAQ delete 컨트롤러 끗");
		return "/faq/getFaqList_admin";
	}
	
	// [공통] 자주묻는질문 리스트 가져오기
	@RequestMapping("/getFaqList")
	@ResponseBody
	public List<FaqVO> getFaqList(FaqVO vo, Model model){
		System.out.println("FAQ getFaqList 컨트롤러 시작");
		System.out.println("faq_cate_btn ? " + vo.getFaq_cate_btn());
		System.out.println("FaqSearch 셀렉트박스 선택값: " + vo.getFaqSearch());
		System.out.println("Faq 입력한 검색어: " + vo.getFaqSearchKeyword());
		
		if (vo.getFaqSearch() == null) {
			model.addAttribute("faqList", faqService.getFaqList(vo));
			System.out.println("model에 있니? " + model.getAttribute("faqList"));
			System.out.println("FAQ getFaqList 컨트롤러 끗");
			return faqService.getFaqList(vo);
		} else {
			System.out.println("으아아아아아아아아앙 검색faq");
			model.addAttribute("faqList", faqService.getFaqSearchList(vo));
			return faqService.getFaqSearchList(vo);
		}
	}
	
	// [공통] getFaqList으로 이동
	@RequestMapping("/togetFaqList")
	public String toFaq_list_wrap(FaqVO vo) {
		System.out.println("와라");
		return "/faq/getFaqList";
	}
	
	// [관리자] getFaqList_admin으로 이동
	@RequestMapping("/togetFaqListAdmin")
	public String toFaq_list_wrap_admin(FaqVO vo) {
		return "/faq/getFaqList_admin";
	}
	
	// [관리자] insertFAQ으로 이동
	@RequestMapping("/toInsertFAQ")
	public String toInsertFAQ(FaqVO vo, HttpSession session) {
		return "/faq/insertFAQ";
	}
	
	// [관리자] 공지사항 선택해서 전체삭제되도록
   @RequestMapping("/deleteCheckedFaq")
   public String deleteCheckedFaq(@RequestParam(value="faq_int", required = false) int[] faq_int_list, FaqVO vo) {
	    System.out.println("받아오는 notice_int_list 배열 몇개요? " + faq_int_list.length);
	    int delSum = 0;
	    
	    // 배열이 가지고있는 값들을 반복해서 출력해주고 delete문을 반복해서 돌린다
	    for(int faq_intValue : faq_int_list) {
	        vo.setFaq_int(faq_intValue);
	        System.out.println(faq_intValue + " " + "vo에 설정된 notice_int값 : " + vo.getFaq_int());
	        if (faqService.deleteFAQ(vo) > 0) { delSum++; }
	    }
	    
	    if(delSum > 0) {
	    	System.out.println("delSum 몇개요? " + delSum);
	    	return "notice/getFaqList_admin";
	    } else {
	    	return "error";
	    }
   }
	
}