package org.asciidoctor.demos.loghandlers;

import org.asciidoctor.log.LogHandler;
import org.asciidoctor.log.LogRecord;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLogHandler implements LogHandler {

    private final AtomicInteger counter = new AtomicInteger();

    private final String id = UUID.randomUUID().toString();

    public MyLogHandler() {
        System.out.println("MyLogHandler created!!");
    }

    @Override
    public void log(LogRecord logRecord) {
        counter.getAndIncrement();
        System.out.printf("[%s] Error in %s: %s%n", id, logRecord.getCursor().getFile(), logRecord);
    }
}
