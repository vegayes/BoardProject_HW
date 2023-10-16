package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 게시판 종류 목록 조회
	public List<Map<String, Object>> selectBoardTypeList() {

		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}

}
