package com.example.spring03.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.model.board.dao.BoardDAO;
import com.example.spring03.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	BoardDAO boardDao;
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(BoardDTO dto) throws Exception { //�۾���
		boardDao.create(dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {

		return boardDao.read(bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		return boardDao.listAll(start, end, search_option, keyword);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		boardDao.increaseViewcnt(bno);
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		return boardDao.countArticle(search_option, keyword);
	}

}
