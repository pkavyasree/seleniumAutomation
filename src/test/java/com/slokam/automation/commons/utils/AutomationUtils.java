package com.slokam.automation.commons.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

public class AutomationUtils {

	public static final String REPORTS = "./reports";
	public static final String LATEST_REPORTS = REPORTS + "/latest/";

	private static String getCurrentDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateFormat.format(cal.getTime());
		return time;
	}

	public static String getCurrentDate() {
		return new AutomationUtils().getCurrentDateTime().substring(0, 11);
	}

	public static void deleteAndCreateLatestReportsFolder() {
		File destDir = new File(LATEST_REPORTS);
		if (destDir.exists()) {
			destDir.delete();
		}
		destDir.mkdir();
	}

	public static void createBackupFolder() {
		File destDir = new File(REPORTS + "/" + getCurrentDateTime());
		if (!destDir.exists()) {
			destDir.mkdir();
		}

		File sourceDir = new File(LATEST_REPORTS);
		
		
		try {
			File[] directoryListing = sourceDir.listFiles();
			if (directoryListing != null) {
				for (File file : directoryListing) {
					FileUtils.copyFile(file, new File(destDir+"/"+file.getName()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AutomationUtils.createBackupFolder();
	}
}
