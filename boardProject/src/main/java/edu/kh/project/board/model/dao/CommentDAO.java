package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Comment> select(int boardNo) {
		return sqlSession.selectList("boardMapper.selectCommentList", boardNo);
	}

	public int insert(Comment comment) {
		return sqlSession.insert("boardMapper.insertComment", comment);
	}

	public int delete(int commentNo) {
		return sqlSession.delete("boardMapper.deleteComment", commentNo);
	}

	public int update(Comment comment) {
		return sqlSession.delete("boardMapper.updateComment", comment);
	}


	
}
