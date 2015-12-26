package com.ss.mailshot.service;

import com.ss.mailshot.dao.TemplateDAO;
import com.ss.mailshot.data.datamodel.TemplateMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 21-Mar-2010
 * Time: 17:44:59
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired(required = true)
    private TemplateDAO templateDAO;

    @Transactional
    public TemplateMetaData createTemplate(TemplateMetaData template) {
        return templateDAO.persistOrMerge(template);
    }

    @Transactional(readOnly = true)
    public TemplateMetaData retrieveTemplate(Long id) {
        return templateDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<TemplateMetaData> getTemplates() {
        return templateDAO.getList();
    }

    @Transactional
    public boolean deleteTemplate(TemplateMetaData template) {        
        return templateDAO.delete(template);
    }
}
