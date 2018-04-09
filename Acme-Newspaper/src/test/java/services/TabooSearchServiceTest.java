
package services;

import java.util.List;

import domain.Article;

public class TabooSearchServiceTest {

	public static void main(final String[] args) {
		final List<Article> arts = TabooSearchService.search("chaos");
		for (final Article a : arts)
			System.out.println(a.getBody());

	}

}
