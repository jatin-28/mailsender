package com.ss.mailshot;

import com.csvreader.CsvReader;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jshah
 * Date: 16-Feb-2010
 * Time: 10:07:29
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static final String UID = "CensusDetails.UID";
    public static final String EMAIL_ADDRESS = "CensusDetails.Email";

    private FreemarkerRenderer renderer;
    private Mailer mailer;
    private String templateName;
    private String subject;
    private boolean testMode = true;
    private String testEmailAddress;
    private int messageIntervalInSecs;

    public void setMessageIntervalInSecs(int messageIntervalInSecs) {
        this.messageIntervalInSecs = messageIntervalInSecs;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void setTestEmailAddress(String testEmailAddress) {
        this.testEmailAddress = testEmailAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRenderer(FreemarkerRenderer renderer) {
        this.renderer = renderer;
    }

    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void process(String[] args) {
        String filename = null;
        String UIDprocessed = null;
        if (args != null && args.length > 0) {
            filename = args[0];
        } else {
            System.out.println("Specify filename!");
            System.exit(1);
        }

        try {
            CsvReader csvReader = new CsvReader(filename);
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            // read first record
            csvReader.readRecord();

            do {
                List<Map> listOfHouseHold = new ArrayList<Map>(1);
                Map root = new HashMap();
                root.put("houseHold", listOfHouseHold);

                Map<String, String> headMap = populateRecordInMap(csvReader, headers);
                root.put("headOfHouseHold", headMap);

                String groupUID = headMap.get(UID);
                String emailAddress = headMap.get(EMAIL_ADDRESS);

                // read in the next record (the last record read should always be the first
                // record of the next household)
                while (csvReader.readRecord() && csvReader.get(UID).equals(groupUID)) {
                    Map<String, String> map2 = populateRecordInMap(csvReader, headers);
                    listOfHouseHold.add(map2);
                }

                if (!root.isEmpty() && StringUtils.hasText(emailAddress)) {
                    // render the template
                    String text = renderer.convert(templateName, root);
                    if (StringUtils.hasText(text)) {
                        if (testMode) emailAddress = testEmailAddress;
                        mailer.sendMail(subject, null, null, text, emailAddress);
                        logger.debug(text + "\n===========================\n");
                        UIDprocessed = UIDprocessed + "," + groupUID;
                    }
                }
                Thread.sleep(messageIntervalInSecs);
                // condition to help end the loop.
            } while (csvReader.getValues() != null && csvReader.getValues().length > 0);

            logger.debug("===== UIDs processed =====: " + UIDprocessed);

            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map populateRecordInMap(CsvReader csvReader, String[] headers) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        for (String header : headers) {
            String value = csvReader.get(header);
            map.put(header, value);
        }
        return map;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Main main = (Main) context.getBean("main");
        main.process(args);
    }

}
