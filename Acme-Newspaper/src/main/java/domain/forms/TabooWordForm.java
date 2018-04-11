
package domain.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TabooWordForm {

	private String	oldTabooWord;
	private String	tabooWord;


	@NotBlank
	@NotNull
	public String getOldTabooWord() {
		return this.oldTabooWord;
	}

	public void setOldTabooWord(final String oldTabooWord) {
		this.oldTabooWord = oldTabooWord;
	}

	@NotBlank
	@NotNull
	public String getTabooWord() {
		return this.tabooWord;
	}

	public void setTabooWord(final String tabooWord) {
		this.tabooWord = tabooWord;
	}
}
