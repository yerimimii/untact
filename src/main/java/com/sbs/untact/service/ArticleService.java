package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
//component와 service는 같고, component는 autowired의 대상에게 붙인다. 
public class ArticleService {
	
	private int articlesLastId;
	private List<Article> articles;
	
	public ArticleService() {
		// 멤버변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();

		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목2", "내용2"));
	}

	public Article getArticle(int id) {
		for(Article article : articles ) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public ResultData add(String title, String body) {
		int id = ++articlesLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		
		articles.add(new Article(id, regDate, updateDate, title, body));

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}
	public ResultData deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return new ResultData("S-1", "삭제하였습니다.", "id", id);
			}
		}

		return new ResultData("F-1", "해당게시물은 존재하지 않습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);
	
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		
		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);

	}

}
