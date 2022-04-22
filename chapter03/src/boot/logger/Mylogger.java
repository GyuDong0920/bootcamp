package boot.logger;

import java.util.logging.Logger;

public class Mylogger {
	
	public static void printLog(String str) {
		Logger logger = Logger.getLogger("myCustomLog");
		logger.warning(str);
	}
}
