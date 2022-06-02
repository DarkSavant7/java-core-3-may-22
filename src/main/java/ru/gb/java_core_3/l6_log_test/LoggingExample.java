package ru.gb.java_core_3.l6_log_test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Trace << Debug << Info << Warn << Error << Fatal
public class LoggingExample {
//    private static final Logger log = LogManager.getLogger();
//    private static final Logger log = LogManager.getLogger("super_logger");
    private static final Logger log = LogManager.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        String str = "JNIKHJ B";
        int a = 123;

        log.trace("Trace log");
        log.debug("Debug log");
        log.info("Info {} log {}", a, str);
        log.warn("Warn log");
        log.error("Error log");
        log.fatal("Fatal log");
        log.error("THread log {}", Thread.currentThread());

        new Thread(() -> log.info("Info from another thread")).start();

        try {
            throw new Exception("AAAAAAAAA");
        } catch (Exception e) {
//            log.error(e);
            log.throwing(e);
        }
    }
}
