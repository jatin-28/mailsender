package com.ss.mailshot.dao;

import com.ss.mailshot.data.datamodel.MailHost;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 27-Mar-2010
 * Time: 11:46:28
 */
@Repository
public class MailServerDAOImpl implements MailServerDAO {
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<MailHost> getList() {
        return this.sessionFactory.getCurrentSession().createQuery("from MailHost ").list();
    }

    @Override
    public MailHost persistOrMerge(MailHost mailHost) {
        return (MailHost) this.sessionFactory.getCurrentSession().merge(mailHost);
    }

    @Override
    public boolean delete(MailHost mailHost) {
        this.sessionFactory.getCurrentSession().delete(mailHost);
        return true;
    }
}
