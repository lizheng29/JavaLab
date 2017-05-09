package lizheng.controller;

import java.util.List;

import lizheng.model.Article;
import lizheng.service.ArticleService;

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
		return "hello world";
	}
}
