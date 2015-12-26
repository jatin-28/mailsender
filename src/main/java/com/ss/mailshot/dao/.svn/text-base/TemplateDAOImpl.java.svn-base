package com.ss.mailshot.dao;

import com.ss.mailshot.data.datamodel.TemplateMetaData;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 21-Mar-2010
 * Time: 17:26:22
 */
@Repository
public class TemplateDAOImpl implements TemplateDAO {
    @Autowired(required = true)
    private SessionFactory sessionFactory;


    @Override
    public TemplateMetaData findById(Long id) {
        TemplateMetaData data = (TemplateMetaData) this.sessionFactory.getCurrentSession().createQuery(
                "from TemplateMetaData t where t.id=?").setParameter(0, id)
                .uniqueResult();
        // hack to eager rather than lazy load
        Hibernate.initialize(data.getFields());
        return data;
    }

    @Override
    public List<TemplateMetaData> getList() {
        return this.sessionFactory.getCurrentSession().createQuery("from TemplateMetaData").list();
    }

    @Override
    public TemplateMetaData persistOrMerge(TemplateMetaData template) {
        return (TemplateMetaData) this.sessionFactory.getCurrentSession().merge(template);
    }

    @Override
    public boolean delete(TemplateMetaData template) {
        this.sessionFactory.getCurrentSession().delete(template);
        return true;
    }

}
