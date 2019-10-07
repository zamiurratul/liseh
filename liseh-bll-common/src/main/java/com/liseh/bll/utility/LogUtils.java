package com.liseh.bll.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class LogUtils {
    private static final Logger LOGGER = LogManager.getLogger(LogUtils.class);

    public static void error(String message) {
        String errorMessage = String.format("Exception: %tc | %s", new Date(), message);
        LOGGER.error(errorMessage);
    }
}
