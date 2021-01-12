package com.example.spring03.service.board;

import java.util.List;

import com.example.spring03.model.board.dto.BoardDTO;

public interface BoardService {
	
	 //요청 > controller > serviceimpl >  
	 //dao > serviceimpl > controller > jsp 
	
	public void deleteFile(String fullName); // 첨부파일 삭제
	public List<String> getAttach(int bno); // 첨부파일 목록
	public void addAttach(String fullName); // 첨부파일 저장
	public void updateAttach(String fullName, int bno); // 첨부파일 수정
	public void create(BoardDTO dto) throws Exception; // 글쓰기

	public BoardDTO read(int bno) throws Exception; // 글읽기
	public void update(BoardDTO dto) throws Exception; // 글수정
	public void delete(int bno) throws Exception; // 글삭제
	
	public List<BoardDTO> listAll(int start, int end, //목록(페이지 나누기, 검색 기능 포함)
			String search_option, String keyword) throws Exception; 
	
	public void increaseViewcnt(int bno) throws Exception; // 조회수 증가
	public int countArticle(String search_option, String keyword) throws Exception; // 레코드 갯수 계산
}
