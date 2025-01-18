package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<CategoryVo> findCategory(String id, Long categoryId) {
		return sqlSession.selectList("category.findCategory",Map.of("id", id, "categoryId", categoryId));
	}

	public List<CategoryVo> findCategoryList(String id) {
		return sqlSession.selectList("category.findCategoryList", id);
	}

	public void insert(String id, String name, String description) {
		sqlSession.insert("category.insert", Map.of("id",id, "name", name, "description", description));
	}

}
