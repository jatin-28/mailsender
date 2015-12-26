package com.ss.mailshot.gui.validation;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 10-Apr-2010
 * Time: 10:16:42
 */
public class EmailTextValidator extends AbstractValidator {
    private static final Logger logger = Logger.getLogger(EmailTextValidator.class);
    private boolean canBeNull = false;

    public EmailTextValidator(JDialog parent, JTextField c, String message, boolean canBeNull) {
        super(parent, c, message);
        this.canBeNull = canBeNull;
    }

    /**
     * Validates email address 
     * @param c The JComponent to be validated.
     * @return
     */
    @Override
    protected boolean validationCriteria(JComponent c) {
        boolean returnVal = false;
        String textField = ((JTextField)c).getText();

        try {
            // first validate if it can be null.
            returnVal = (!StringUtils.hasText(textField) && canBeNull);

            // if still false, and has text then validate the address
            if(!returnVal && StringUtils.hasText(textField) ) {
                // all that needs to be done is use java's inbuilt parser.
                new InternetAddress(textField, true);
                returnVal = true;
            }

        } catch (AddressException e) {
            returnVal = false;
            logger.debug(e);
        }

        return returnVal;
    }

}
