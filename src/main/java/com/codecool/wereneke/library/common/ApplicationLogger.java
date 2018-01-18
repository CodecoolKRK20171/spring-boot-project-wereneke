package com.codecool.wereneke.library.common;

import com.codecool.wereneke.library.LibraryApplication;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

@Service
public class ApplicationLogger implements LoggerService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);
    private Marker marker = MarkerManager.getMarker("START");

    @Override
    public void logError(String message) {
        logger.error(marker, message);
    }

    @Override
    public void logInfo(String message) {
        logger.info(marker, message);
    }
}
