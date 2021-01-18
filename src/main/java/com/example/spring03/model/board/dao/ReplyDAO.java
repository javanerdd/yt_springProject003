package com.example.spring03.model.board.dao;

import java.util.List;

import com.example.spring03.model.board.dto.ReplyDTO;

public interface ReplyDAO {
	
	public List<ReplyDTO> list(Integer bno, int start, int end); //��� ���
	public int count(int bno); //��� ����
	public void create(ReplyDTO dto); //��� �Է�
	public void update(ReplyDTO dto); //��� ����
	public void delete(Integer bno); //��� ����
	
}
