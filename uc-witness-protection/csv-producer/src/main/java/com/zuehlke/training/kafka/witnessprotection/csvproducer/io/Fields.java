package com.zuehlke.training.kafka.witnessprotection.csvproducer.io;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.stream.Stream;

public enum Fields {
    ID("id", new Optional(new ParseLong())),
    CREATEUSER("createUser", new Optional()),
    CREATEDATE("crateDate", new Optional()),
    MODIFYUSER("modifyUser", new Optional()),
    MODIFYDATE("modifyDate", new Optional()),
    RUNDATE("runDate", new Optional()),
    PERS_LAUFNUMMER("persLaufnummer", new Optional()),
    PEVE_ID("peveId", new Optional()),
    STATUS("status", new Optional()),
    DATA("data", new Optional());

    String name;
    CellProcessor cellProcessor;

    Fields(String name, CellProcessor cellProcessor) {
        this.name = name;
        this.cellProcessor = cellProcessor;
    }

    public String getName() {
        return name;
    }

    public CellProcessor getCellProcessor() {
        return cellProcessor;
    }

    public static String[] getFieldMapping() {
        return Stream.of(Fields.values())
                .map(Fields::getName)
                .toArray(String[]::new);
    }

    public static CellProcessor[] getProcessors() {
        return Stream.of(Fields.values())
                .map(Fields::getCellProcessor)
                .toArray(CellProcessor[]::new);
    }
}
