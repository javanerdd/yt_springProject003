package com.example.spring03.model.service.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.model.memo.dao.MemoDAO;
import com.example.spring03.model.memo.dto.MemoDTO;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Inject
	MemoDAO memoDao;
	@Override
	public List<MemoDTO> list() {
		// TODO Auto-generated method stub
		return memoDao.list();
	}

	@Override
	public void insert(MemoDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(String write, String memo) {
		memoDao.insert(write, memo);
	}

	@Override
	public MemoDTO memo_view(int idx) {
		
		return memoDao.memo_view(idx);
	}

	@Override
	public void update(MemoDTO dto) {
		memoDao.memo_update(dto);
	}

	@Override
	public void delete(int idx) {
		memoDao.memo_delete(idx);
	}

}
