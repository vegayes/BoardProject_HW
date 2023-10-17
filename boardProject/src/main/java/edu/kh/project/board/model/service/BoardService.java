package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


public interface BoardService {

	List<Map<String, Object>> selectBoardTypeList();

	
	// 게시글 목록조회 
	Map<String, Object> selectBoardList(int boardCode, int cp);

}
