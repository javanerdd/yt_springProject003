package com.example.spring03.service.board;

import java.util.List;

import com.example.spring03.model.board.dto.BoardDTO;

public interface BoardService {
	
	 //��û > controller > serviceimpl >  
	 //dao > serviceimpl > controller > jsp 
	
	public void deleteFile(String fullName); // ÷������ ����
	public List<String> getAttach(int bno); // ÷������ ���
	public void addAttach(String fullName); // ÷������ ����
	public void updateAttach(String fullName, int bno); // ÷������ ����
	public void create(BoardDTO dto) throws Exception; // �۾���

	public BoardDTO read(int bno) throws Exception; // ���б�
	public void update(BoardDTO dto) throws Exception; // �ۼ���
	public void delete(int bno) throws Exception; // �ۻ���
	
	public List<BoardDTO> listAll(int start, int end, //���(������ ������, �˻� ��� ����)
			String search_option, String keyword) throws Exception; 
	
	public void increaseViewcnt(int bno) throws Exception; // ��ȸ�� ����
	public int countArticle(String search_option, String keyword) throws Exception; // ���ڵ� ���� ���
}
