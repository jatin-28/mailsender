package com.ss.mailshot.dao;

import com.ss.mailshot.data.datamodel.TemplateMetaData;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 21-Mar-2010
 * Time: 17:25:35
 */
public interface TemplateDAO {
    TemplateMetaData findById(Long id);

    List<TemplateMetaData> getList();

    TemplateMetaData persistOrMerge(TemplateMetaData template);

    boolean delete(TemplateMetaData template);
}
