package com.discord.intelli_bot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Loads properties from the file
 * 
 * @author Nitesh
 *
 */
public class PropertiesHandler {
	private static Properties properties;
	private String fileName;
	private File file;

	/**
	 * @param fileName
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public PropertiesHandler(String fileName) throws URISyntaxException, IOException {
		this.fileName = fileName;
		initializeProperties();
	}

	public Properties getProperties() {
		return properties;
	}

	private void initializeProperties() throws URISyntaxException, IOException {
		properties = new Properties();

		file = new File(AppConstants.PROPERTIES_FOLDER_PATH + fileName);

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} else {
			properties.load(new FileInputStream(file));
		}
	}

}
