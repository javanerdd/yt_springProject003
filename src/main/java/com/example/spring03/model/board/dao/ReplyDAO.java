package com.example.spring03.model.board.dao;

import java.util.List;

import com.example.spring03.model.board.dto.ReplyDTO;

public interface ReplyDAO {
	
	public List<ReplyDTO> list(Integer bno, int start, int end); //´ñ±Û ¸ñ·Ï
	public int count(int bno); //´ñ±Û °¹¼ö
	public void create(ReplyDTO dto); //´ñ±Û ÀÔ·Â
	public void update(ReplyDTO dto); //´ñ±Û ¼öÁ¤
	public void delete(Integer bno); //´ñ±Û »èÁ¦
	
}
