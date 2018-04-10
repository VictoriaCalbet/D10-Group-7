
package services;

import java.util.ArrayList;
import java.util.List;

import domain.Article;

public class TabooSearchServiceTest {

	public static void main(final String[] args) {
		List<String> words;
		words = new ArrayList<String>();
		words.add("chaoscollegechemical");
		//words.add("college");
		//words.add("chemical");
		words.add("Article 4");
		final List<Article> arts = TabooSearchService.search(words);
		for (final Article a : arts)
			System.out.println(a.getBody());

	}

}
