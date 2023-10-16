package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


public interface BoardService {

	List<Map<String, Object>> selectBoardTypeList();

}
