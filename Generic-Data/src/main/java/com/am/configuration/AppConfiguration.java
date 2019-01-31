package com.am.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amnay Ali
 * 
 *         Application Configuration DB Class
 *
 */
@Configuration
@ComponentScan(value = { "com.am" })

public class AppConfiguration {

	public static boolean isWindows() {

		return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);

	}
}
