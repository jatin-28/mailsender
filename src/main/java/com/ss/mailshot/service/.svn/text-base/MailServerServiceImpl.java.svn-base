package com.ss.mailshot.service;

import com.ss.mailshot.dao.MailServerDAO;
import com.ss.mailshot.data.datamodel.MailHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 27-Mar-2010
 * Time: 11:50:32
 */
@Service
public class MailServerServiceImpl implements MailServerService {
    @Autowired(required = true)
    private MailServerDAO mailServerDAO;

    @Transactional
    public MailHost createMailServer(MailHost mailHost) {
        return mailServerDAO.persistOrMerge(mailHost);
    }

    @Transactional
    public List<MailHost> getMailServers() {
        return mailServerDAO.getList();
    }

    @Transactional
    public boolean deleteMailServer(MailHost mailHost) {
        return mailServerDAO.delete(mailHost);
    }
}
