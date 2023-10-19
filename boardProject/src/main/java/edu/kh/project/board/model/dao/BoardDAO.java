package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 게시판 종류 목록 조회
	public List<Map<String, Object>> selectBoardTypeList() {

		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}

	// 특정 게시판의 삭제되지 않은 게시판 갯수 확인
	public int getListCount(int boardCode) {
		
		
		
		return sqlSession.selectOne("boardMapper.getListCount", boardCode);
	}

	// 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
	public List<Board> selectBoardList(Pagination pagination, int boardCode) {
		
		// RowBounds 객체
		// - MyBatis에서 페이징처리를 위해 제공하는 객체
		// - offset만큼 건너뛰고 그 다음 지정된 행 개수(limi) 만큼 조회
		
		// 1) offset계산
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		// 1페이지는 0만큼 건너튀고 조회
		// 2페이지는 10만큼 건너뛰고 조회 
		// 3페이지는 20만큼 건너뛰고 조회 
		
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		
		// 3) selectList("namespace.id", 파리미터, RowBounds) 호출
		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	}

	// 게시글 상세 조회
	public Board selectBoardList(Map<String, Object> map) {
		return sqlSession.selectOne("boardMapper.selectBoard", map);
	}

	/** 좋아요 여부 확인 DAO
	 * @param map
	 * @return
	 */
	public int boardLikeCheck(Map<String, Object> map) {
		
		
		int result = sqlSession.selectOne("boardMapper.boardLikeCheck",map);
		
		
		return result;
	}

	/** 조회수 증가 DAO
	 * @param boardNo
	 * @return
	 */
	public int updateReadCount(int boardNo) {
		return sqlSession.update("boardMapper.updateReadCount", boardNo);
	}

	public int addLike(Map<String, Integer> paramMap) {
		return sqlSession.insert("boardMapper.addLike", paramMap);
	}

	public int delLike(Map<String, Integer> paramMap) {
		return sqlSession.delete("boardMapper.delLike", paramMap);
	}

	public int countBoardLike(Integer boardNo) {
		return sqlSession.selectOne("boardMapper.countBoardLike",boardNo);
	}

}
