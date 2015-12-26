package com.ss.mailshot.data.datamodel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 14-Mar-2010
 * Time: 12:20:07
 */
@Entity
@Table(name = "TEMPLATE_META_DATA")
public class TemplateMetaData {
    @Id
    @GeneratedValue
    private Long id;

    
    private String templateName;
    private String templateCSVFile;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Field> fields = new ArrayList<Field>();

    @OneToOne(cascade = CascadeType.ALL)
    private EMailBean emailBean = new EMailBean();

    @Lob
    private String templateText;

    public Long getId() {
        return id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateCSVFile() {
        return templateCSVFile;
    }

    public void setTemplateCSVFile(String templateCSVFile) {
        this.templateCSVFile = templateCSVFile;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public EMailBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EMailBean emailBean) {
        this.emailBean = emailBean;
    }

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    @Override
    public String toString() {
        return templateName;
    }

    public String toDebugString() {
        return "TemplateMetaData{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                ", templateCSVFile='" + templateCSVFile + '\'' +
                ", fields=" + fields +
                ", emailBean=" + emailBean +
                ", templateText='" + templateText + '\'' +
                '}';
    }
}
