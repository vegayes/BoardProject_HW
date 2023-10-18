package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dto.Board;


public interface BoardService {

	List<Map<String, Object>> selectBoardTypeList();

	
	// 게시글 목록조회 
	Map<String, Object> selectBoardList(int boardCode, int cp);

	// 게시글 상세 조회
	Board selectBoardList(Map<String, Object> map);

}
