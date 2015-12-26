package com.ss.mailshot.service;

import com.ss.mailshot.data.datamodel.TemplateMetaData;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 21-Mar-2010
 * Time: 17:41:26
 */
public interface TemplateService {
    TemplateMetaData retrieveTemplate(Long id);

    TemplateMetaData createTemplate(TemplateMetaData template);

    List<TemplateMetaData> getTemplates();

    boolean deleteTemplate(TemplateMetaData template);
}
