package com.tenjava.entries.ParaPenguin.t2.util;

import com.tenjava.entries.ParaPenguin.t2.TenJava;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	public static Logger logger;
	public static boolean debug;

	static {
		logger = TenJava.get().getLogger();
	}

	public static void log(Exception ex) {
		if(debug) {
			ex.printStackTrace();
		} else {
			logger.info(ex.getMessage());
		}
	}

	public static void severe(String msg) {
		logger.severe(msg);
	}

	public static void warning(String msg) {
		logger.warning(msg);
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void log(Level level, String msg) {
		logger.log(level, msg);
	}

}
