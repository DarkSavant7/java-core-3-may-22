package ru.gb.java_core_3.l6_log_test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingExampleWithSlf4jAndLombok {

    public static void main(String[] args) {

        double d = .0324;
        String s = "Hi";

        log.info("Number = {} and string = {}", d, s);

        log.trace("Trace log");
        log.debug("Debug log");
        log.info("Info log");
        log.warn("Warn log");
        log.error("Error log");

        new Thread(() -> log.info("Another thread")).start();

        try {
            throw new RuntimeException("AAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            log.error("an error occured", e);
        }
    }
}
