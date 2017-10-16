package lizheng.serviceimp;

import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lizheng.model.Article;
import lizheng.service.ArticleService;
import lizheng.service.JestService;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceimp implements ArticleService {

	final Logger logger = LoggerFactory.getLogger(ArticleServiceimp.class);
	
	@Autowired
	JestService jestService;
	
	@Override
	public void index() throws Exception {
		Article a1 = new Article();
		a1.setId(1L);
		a1.setContent("大家好，这个是第一篇Article");
		a1.setPostTime(new Date());
		
		Article a2 = new Article();
		a2.setId(2L);
		a2.setContent("大家好，这个是第二篇Article");
		a2.setPostTime(new Date());
		
		Article a3 = new Article();
		//a3.setId(3L);
		a3.setContent("大家好，这个是自动创建Index的Article");
		a3.setPostTime(new Date());
		
		List<Object> aList = new ArrayList<Object>();
		//aList.add(a1);
		//aList.add(a2);
		aList.add(a3);
		jestService.index("article_automake", "introduce_automake", aList);
	}

	@Override
	public List<Article> searchList() {
		
		SearchSourceBuilder searchSourceBuilder =SearchSourceBuilder.searchSource();
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.termQuery("id", 1L));
		searchSourceBuilder.query(boolQuery);
		
		List<Article> datalist = new ArrayList<Article>();
		try {
			SearchResult result = jestService.search("article", "introduce", searchSourceBuilder.toString());
			if (!result.isSucceeded()) {
				logger.error(result.getErrorMessage());
				datalist=null;
			} else {
				List<Hit<Article, Void>> hits = result.getHits(Article.class);
				
				for (Hit<Article, Void> hit : hits) {  
					Article article = hit.source;  
					datalist.add(article);
					logger.info(article.getContent());
			        }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datalist;
	}

	@Override
	public void createIndex() throws Exception{
		System.out.println("=================create index========================"+jestService.createIndex("article"));
	}

	@Override
	public void createIndexMapping() throws Exception {
		 XContentBuilder mappingBuilder = null;
	        try {
	            mappingBuilder = XContentFactory.jsonBuilder()
	                    .startObject()
	                    .startObject("introduce")
	                    .startObject("properties")
	                    .startObject("id")
	                    	.field("type", "long")
	                    	.field("index", "not_analyzed")
	                    	//.field("analyzer", "english")
	                    	.endObject()
	                    .startObject("content")
	                    	.field("type", "string")
	                    	.field("index", "analyzed")
	                    	.endObject()
	                    .startObject("postTime")
	                    	.field("type", "date")
	                    	.field("format", "strict_date_optional_time||epoch_millis")
	                    	.endObject()
	                    .endObject()
	                    .endObject()
	                    .endObject();
	        } catch (Exception e) {
	            logger.error("--------- putIndexMapping 创建 mapping 失败：", e);
	        }
	        logger.info(mappingBuilder.string());
	        System.out.println("==================create mapping======================="+jestService.createIndexMapping("article", "introduce", mappingBuilder.string()));
	}

	@Override
	public String analyze(String text) {
		return jestService.analyze(text);
	}



}
