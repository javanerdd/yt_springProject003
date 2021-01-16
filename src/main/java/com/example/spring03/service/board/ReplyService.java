package com.example.spring03.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring03.model.board.dto.ReplyDTO;

public interface ReplyService {
	
	public void create(ReplyDTO dto); // ´ñ±Û ÀÔ·Â
	
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session); //´ñ±Û¸ñ·Ï
	
	public ReplyDTO detail(Integer bno); //´ñ±Û »ó¼¼
	
	public void update(ReplyDTO dto); //´ñ±Û ¼öÁ¤
	
	public void delete(Integer bno); //´ñ±× »èÁ¦
	
	public int count(Integer bno); //´ñ±Û °¹¼ö
}
