package com.example.spring03.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring03.model.board.dto.ReplyDTO;

public interface ReplyService {
	
	public void create(ReplyDTO dto); // ��� �Է�
	
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session); //��۸��
	
	public ReplyDTO detail(Integer bno); //��� ��
	
	public void update(ReplyDTO dto); //��� ����
	
	public void delete(Integer bno); //��� ����
	
	public int count(Integer bno); //��� ����
}
