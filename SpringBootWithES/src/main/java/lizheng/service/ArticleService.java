package lizheng.service;

import java.util.List;

import lizheng.model.Article;

public interface ArticleService {

	void createIndex() throws Exception;
	
	void createIndexMapping() throws Exception;
	
	void index() throws Exception;
	
	List<Article> searchList();
	
	String analyze(String text);
}
