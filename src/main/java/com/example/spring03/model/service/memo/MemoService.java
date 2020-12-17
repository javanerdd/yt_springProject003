package com.example.spring03.model.service.memo;

import java.util.List;

import com.example.spring03.model.memo.dto.MemoDTO;

public interface MemoService {
	
	public List<MemoDTO> list();
	
	public void insert(MemoDTO dto);
	
	public void insert(String write, String memo);
	
	public MemoDTO memo_view(int idx);
	
	public void update(MemoDTO dto);
	
	public void delete(int idx);

}
