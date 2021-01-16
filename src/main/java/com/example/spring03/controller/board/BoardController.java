package com.example.spring03.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.board.dto.BoardDTO;
import com.example.spring03.service.board.BoardService;
import com.example.spring03.service.board.Pager;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do")
	public ModelAndView list(
			@RequestParam(defaultValue="1") int curPage, //ó���� ���� �ȳѾ� ���⶧���� �⺻��1�� ����
			@RequestParam(defaultValue="all") String search_option,
			@RequestParam(defaultValue="") String keyword) throws Exception{
		
		int count = boardService.countArticle(search_option, keyword);
		
		Pager pager = new Pager(count,curPage); //���ڵ�� ���ϴ� ������ ��ȣ�� �ְ� �Ǹ�
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		
		List<BoardDTO> list = boardService.listAll(start, end, search_option, keyword); // ���
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // �̵��� ������ ����
		
		Map<String, Object> map = new HashMap<>(); // �����͸� �ʿ� ����
		map.put("list", list);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager);
		mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
		
		return mav; // board/list.jsp�� �̵�
	}
	
	
	@RequestMapping("write.do") //�۾��� ������
	public String write() {
		System.out.println("~~~~~~��Ʈ�ѷ��� write()....");
		return "board/write";
	}
	
	@RequestMapping("insert.do") // �۾���
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
		
		String writer = (String)session.getAttribute("userid"); //�α����� ����� ���̵�
		dto.setWriter(writer); 
		boardService.create(dto); //���ڵ尡 �����
		
		return "redirect:/board/list.do";  //��� ����
	}
	
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno,
			@RequestParam int curPage,
			@RequestParam String search_option,
			@RequestParam String keyword,
			HttpSession session) throws Exception {
		
		boardService.increaseViewcnt(bno);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardService.read(bno)); //�Խù�����
		mav.addObject("curPage", curPage);
		mav.addObject("search_option",search_option);
		mav.addObject("keyword",keyword);
		
		return mav;
	}
}
