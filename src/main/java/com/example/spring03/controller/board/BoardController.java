package com.example.spring03.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.board.dto.BoardDTO;
import com.example.spring03.service.board.BoardService;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do") //목록
	public ModelAndView list() throws Exception{
		List<BoardDTO> list = boardService.listAll(0, 0, "", ""); //목록
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // 이동할 페이지
		
		Map<String,Object> map = new HashMap<>();
		map.put("list", list); // 맵에 자료 저장
		mav.addObject("map",map); // 데이터 저장
		
		return mav; // 페이지 이동(출력)
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
}
