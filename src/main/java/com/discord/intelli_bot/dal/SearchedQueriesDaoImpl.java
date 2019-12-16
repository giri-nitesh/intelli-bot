package com.discord.intelli_bot.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.discord.intelli_bot.utils.AppConstants;

/**
 * Represents the concrete implementation of {@link SearchedQueriesDaoImpl}
 * interface. The persistence mechanism used here is the primary memory
 * 
 * @author Nitesh
 *
 */
public class SearchedQueriesDaoImpl {
	private String dbFilePath;

	public SearchedQueriesDaoImpl() {
		this.dbFilePath = AppConstants.DB_FOLDER + AppConstants.DB_FILE;
	}

	/**
	 * 
	 * @param str
	 * @throws IOException
	 */
	public void saveRecordInDb(String str) throws IOException {
		File file = new File(dbFilePath);

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write(str + "\n");
		br.close();
	}

	/**
	 * Reads record from Databse. User would have searched a term multiple times,
	 * but it would be returning only unique items.
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readRecordsFromDb() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(dbFilePath));
		StringBuilder builder = new StringBuilder();
		String strCurrentLine;
		Set<String> set = new HashSet<>();
		while ((strCurrentLine = reader.readLine()) != null) {
			set.add(strCurrentLine.trim());
		}
		reader.close();
		for (String item : set) {
			builder.append(item);
			builder.append("\n");
		}
		return builder.toString();
	}
}
