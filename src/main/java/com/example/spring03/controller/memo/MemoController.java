package com.example.spring03.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.memo.dto.MemoDTO;
import com.example.spring03.model.service.memo.MemoService;

@Controller
@RequestMapping("/memo/*") //공통적인 매핑
public class MemoController {
	
	@Inject
	MemoService memoService;
	
	@RequestMapping("list.do") //   /memo/list.do
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoService.list(); //메모 리스트 리턴
		mav.setViewName("memo/memo_list"); //출력 페이지의 이름
		mav.addObject("list", items);	//출력 페이지에 전달 할 변수
		
		return mav;	//페이지로 이동
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		//memoService.insert(dto);
		memoService.insert(dto.getWrite(),dto.getMemo());
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		mav.setViewName("memo/view");
		mav.addObject("dto",memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, MemoDTO dto) {
		memoService.update(dto);
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		memoService.delete(idx);
		return "redirect:/memo/list.do";
	}
	
}






//@Controller  - 컨트롤러등록
//@Service  - 서비스 bean(객체)으로 등록
//@Respository  - dao bean으로 등록
//
//@Inject == @Autowired
//
//@RequestMapping  - url 매핑
//@RequestParam  - get,post개별 변수
//@ModelAttribute
//@ResponseBody  - 제이쓴