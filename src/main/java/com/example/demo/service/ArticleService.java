package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	// 서비스 메서드
	public Article getForPrintArticle(int id) {
		Article article = articleRepository.getForPrintArticle(id);

		controlForPrintData(article);

		return article;
	}

	private void controlForPrintData(Article article) {
		if (article == null) {
			return;
		}
		ResultData userCanModifyRd = userCanModify(article);
		article.setUserCanModify(userCanModifyRd.isSuccess());

		ResultData userCanDeleteRd = userCanDelete(article);
		article.setUserCanDelete(userCanDeleteRd.isSuccess());
	}

	public ResultData userCanDelete(Article article) {


		return ResultData.from("S-1", Ut.f("%d번 글이 삭제 되었습니다", article.getId()));
	}

	public ResultData userCanModify(Article article) {


		return ResultData.from("S-1", Ut.f("%d번 글을 수정했습니다", article.getId()));
	}

	public ResultData<Integer> writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);


		return ResultData.from("S-1", Ut.f("글이 생성되었습니다"));
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

}
