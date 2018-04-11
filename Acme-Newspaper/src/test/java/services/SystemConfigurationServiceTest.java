/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Article;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SystemConfigurationServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SystemConfigurationService	systemConfigurationService;


	// Tests ------------------------------------------------------------------

	@Test
	public void test1() {
		Collection<Article> articles;

		articles = this.systemConfigurationService.getArticlesWithSpamWords();

		System.out.println(articles);
	}

	@Test
	public void test2() {
		Collection<Article> articles;

		articles = this.systemConfigurationService.getArticlesWithSpamWords();

		System.out.println(articles);
	}
}
