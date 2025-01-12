package jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	private SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVo uservo) {
		return sqlSession.insert("user.insert", uservo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findIdAndPassword", Map.of("id", id, "password", password));
	}
	
	public UserVo findById(String id) {
		System.out.println("findById called with id: " + id); // 로그 추가
		return sqlSession.selectOne("user.findById", id);
	}
}
