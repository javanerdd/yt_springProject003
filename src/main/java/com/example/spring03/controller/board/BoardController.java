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
	
	@RequestMapping("list.do") //���
	public ModelAndView list() throws Exception{
		List<BoardDTO> list = boardService.listAll(0, 0, "", ""); //���
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // �̵��� ������
		
		Map<String,Object> map = new HashMap<>();
		map.put("list", list); // �ʿ� �ڷ� ����
		mav.addObject("map",map); // ������ ����
		
		return mav; // ������ �̵�(���)
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
}
