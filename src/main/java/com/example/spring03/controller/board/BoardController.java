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
			@RequestParam(defaultValue="1") int curPage, //처음에 값이 안넘어 오기때문에 기본값1을 넣음
			@RequestParam(defaultValue="all") String search_option,
			@RequestParam(defaultValue="") String keyword) throws Exception{
		
		int count = boardService.countArticle(search_option, keyword);
		
		Pager pager = new Pager(count,curPage); //레코드와 원하는 페이지 번호를 주게 되면
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		
		List<BoardDTO> list = boardService.listAll(start, end, search_option, keyword); // 목록
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // 이동할 페이지 지정
		
		Map<String, Object> map = new HashMap<>(); // 데이터를 맵에 저장
		map.put("list", list);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager);
		mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
		
		return mav; // board/list.jsp로 이동
	}
	
	
	@RequestMapping("write.do") //글쓰기 페이지
	public String write() {
		System.out.println("~~~~~~컨트롤러단 write()....");
		return "board/write";
	}
	
	@RequestMapping("insert.do") // 글쓰기
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
		
		String writer = (String)session.getAttribute("userid"); //로그인한 사용자 아이디
		dto.setWriter(writer); 
		boardService.create(dto); //레코드가 저장됨
		
		return "redirect:/board/list.do";  //목록 갱신
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
		mav.addObject("dto", boardService.read(bno)); //게시물내용
		mav.addObject("curPage", curPage);
		mav.addObject("search_option",search_option);
		mav.addObject("keyword",keyword);
		
		return mav;
	}
}
