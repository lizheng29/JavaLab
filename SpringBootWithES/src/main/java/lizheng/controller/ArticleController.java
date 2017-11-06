package lizheng.controller;

import java.util.List;

import lizheng.model.Article;
import lizheng.service.ArticleService;

import lizheng.service.JestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
//@Controller
public class ArticleController {

	@Autowired
	ArticleService 	articleService;

	@Autowired
	private JestService jestService;
	
	@GetMapping(value = "/createIndex")
	public String show(){
		try {
			articleService.createIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "createIndex";
	}
	
	@GetMapping(value = "/mapping")
	public String mapping(){
		try {
			articleService.createIndexMapping();;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mapping";
	}
	
	@GetMapping(value = "/create")
	public String index() throws Exception{
		articleService.index();
		return "create";
	}
	
	@GetMapping(value = "/search")
	public String search() throws Exception{
		articleService.searchList();
		return "search";
	}
	
	@GetMapping(value = "/hello")
	public String hello() throws Exception{
		jestService.processFile();
		return "hello";
	}
	
	@GetMapping(value = "/analyze")
	public String analyze() throws Exception{
		return articleService.analyze("Jest maven repository is hosted on Sonatype which is then synced to Maven Central with a slight delay.");
	}
}
