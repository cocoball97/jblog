package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<CategoryVo> findCategory(String blog_id) {
		return sqlSession.selectList("category.findCategory",blog_id);
	}

}
