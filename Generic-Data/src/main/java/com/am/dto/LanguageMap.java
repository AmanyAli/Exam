package com.am.dto;

import java.util.HashMap;

import com.am.util.LocalizationUtil;

/**
 * @author Amany Ali
 * 
 *         Embedded Language Object to support language translation
 * 
 *         Key String represents the locale language. Value String represents
 *         the translated text of the locale language
 *
 */
public class LanguageMap extends HashMap<String, String> {

	
	private String text;

	public LanguageMap() {

		// langMap = new HashMap<>();
	}

	public String getText() {

		text = get(LocalizationUtil.defaultLanguage);
		text = getDefaultText(text);
		return text;
	}

	public String getDefaultText() {

		return getDefaultText(null);
	}

	private String getDefaultText(String text) {
		if (text == null || text.isEmpty()) {

			text = get(LocalizationUtil.defaultEmptyLanguage);
		}

		return text;
	}

	public String getText(String locale) {

		if (locale == null) {
			return getText();
		}
		text = get(locale);
		text = getDefaultText(text);

		return text;
	}

	public void setText(String text) {
		this.put(LocalizationUtil.defaultLanguage, text);
		this.text = text;
	}

}
