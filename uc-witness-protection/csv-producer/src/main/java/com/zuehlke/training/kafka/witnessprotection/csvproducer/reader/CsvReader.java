package com.zuehlke.training.kafka.witnessprotection.csvproducer.reader;

import com.zuehlke.training.kafka.witnessprotection.csvproducer.io.Fields;
import com.zuehlke.training.kafka.witnessprotection.csvproducer.io.LineMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

@Component
public class CsvReader {

    private Logger logger = LoggerFactory.getLogger(CsvReader.class);

    private final ResourceLoader resourceLoader;

    public CsvReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public void readAndCallback(String filename, Consumer<LineMessage> consume) {
        CsvBeanReader beanReader = null;
        try {
            Resource resource = resourceLoader.getResource(filename);
            beanReader = new CsvBeanReader(new InputStreamReader(resource.getInputStream()), CsvPreference.STANDARD_PREFERENCE);
            // Consume the header line
            beanReader.getHeader(true);
            final CellProcessor[] processors = Fields.getProcessors();

            LineMessage message;
            while( (message = beanReader.read(LineMessage.class,  Fields.getFieldMapping(), processors)) != null ) {
                consume.accept(message);
            }
            logger.info("All lines read");

        } catch (FileNotFoundException e) {
            logger.error("No fIle found", e);
        } catch (IOException e) {
            logger.error("Error while reading file: " +  filename, e);
        } finally {
            closeReader(beanReader);
        }
    }

    private void closeReader(CsvBeanReader beanReader) {
        if( beanReader != null ) {
            try {
                beanReader.close();
            } catch (IOException e) {
                logger.error("Error while closing", e);
            }
        }
    }


}
