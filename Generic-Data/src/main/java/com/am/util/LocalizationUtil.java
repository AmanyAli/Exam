package com.am.util;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.am.model.LanguageSupportConfig;
import com.am.service.ServiceUtil;
import com.am.service.language.LanguageSupportConfigService;

/**
 * @author Amany Ali
 * 
 *Localization Utility class to provide all method related to localization
 *
 */
public class LocalizationUtil {
	public static final Locale DEFAULT_EMPTY_LOCALE = new Locale("en", "EN");
	public static Map<String, Locale> localeMap = new HashMap<>();
	public static final String defaultEmptyLanguage = "en";
	public static String defaultLanguage = "en";
	public static LanguageSupportConfig defaultLanguageConfig;
	public static final String UNDERSCORE = "_";
	public static List<LanguageSupportConfig> languages;

	public static List<LanguageSupportConfig> initLanguages() {

		if (languages == null) {

			LanguageSupportConfigService languageSupportConfigService = ServiceUtil.getApplicationService()
					.getLanguageSupportConfigService();

			languages = languageSupportConfigService.getAll();

			int count = 0;
			boolean defaultSet = false;

			for (LanguageSupportConfig lang : languages) {
				count++;
				String locale = lang.getLocale();
				localeMap.put(locale, new Locale(locale.toLowerCase()));
				if (lang.isDefaultLanguage() || (count == languages.size() && !defaultSet)) {
					defaultLanguage = locale;
					defaultLanguageConfig = lang;
					defaultSet = true;

				}

			}

		}
		return languages;

	}

	public static LanguageSupportConfig getDefaultLanguageConfig() {
		return defaultLanguageConfig;
	}

	public static void setDefaultLanguageConfig(LanguageSupportConfig defaultLanguageConfig) {
		LocalizationUtil.defaultLanguageConfig = defaultLanguageConfig;
	}

}
