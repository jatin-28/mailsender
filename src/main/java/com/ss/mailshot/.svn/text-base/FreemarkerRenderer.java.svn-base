package com.ss.mailshot;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class FreemarkerRenderer {
    private static Logger logger = Logger.getLogger(FreemarkerRenderer.class);
    private Configuration freemarkerConfig = new Configuration();
    private String templateLoadPath = "/";

    public void setTemplateLoadPath(String templateLoadPath) {
        this.templateLoadPath = templateLoadPath;
    }

    public FreemarkerRenderer() {
        /* Create and adjust the configuration */
        freemarkerConfig.setClassForTemplateLoading(FreemarkerRenderer.class, templateLoadPath);
        freemarkerConfig.setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);

        logger.info("Freemarker Version: " + freemarkerConfig.getVersionNumber());
    }

    /**
     * Converts using a filename
     * @param templateName - filename for rendering
     * @param root
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String convert(String templateName, Map root)
            throws IOException, TemplateException {
        String output = null;

        /* Get or create a template */
        Template temp = freemarkerConfig.getTemplate(templateName);

        /* Merge data-com.ss.mailshot.data.datamodel with template */
        StringWriter writer = new StringWriter();
        temp.process(root, writer);

        output = writer.toString();
        return output;
    }

    /**
     * Renders with the loaded template as string.
     * @param templateData
     * @param root
     * @return
     */
    public String convert(String templateName, String templateData, Map root)
            throws IOException, TemplateException {
        String output = null;

        Template freemarkerTemplate = new Template(templateName, new StringReader(templateData), freemarkerConfig);

        /* Merge data-com.ss.mailshot.data.datamodel with template */
        StringWriter writer = new StringWriter();
        freemarkerTemplate.process(root, writer);

        output = writer.toString();
        return output;
    }
}