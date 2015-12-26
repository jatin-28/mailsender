package com.ss.mailshot.gui;

import com.csvreader.CsvReader;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.ss.mailshot.data.datamodel.EMailBean;
import com.ss.mailshot.data.datamodel.Field;
import com.ss.mailshot.data.datamodel.TemplateMetaData;
import com.ss.mailshot.gui.validation.EmailTextValidator;
import com.ss.mailshot.gui.validation.NotEmptyValidator;
import com.ss.mailshot.gui.validation.ValidationStatusListener;
import com.ss.mailshot.service.TemplateService;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateEditTemplate extends JDialog implements ValidationStatusListener {
    private static final Logger logger = Logger.getLogger(CreateEditTemplate.class);

    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JTextField templateNameTextField;
    private JTextField emailSubjectTextField;
    private JTextField emailToTextField;
    private JTextField emailCCTextField;
    private JTextField emailBCCTextField;
    private JTextField csvFileTextField;
    private HTMLEditorPane templateTextEditorPane;
    private JList fieldList;
    private JTextField emailFromTextField;
    private JTextField emailReplyToTextField;
    private JPanel templateTextPanel;

    private TemplateMetaData templateData;
    private boolean dataUpdated = false;
    private JFileChooser fileChooser;

    @Autowired(required = true)
    private TemplateService templateService;

    public CreateEditTemplate() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        csvFileTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int returnVal = fileChooser.showOpenDialog(CreateEditTemplate.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    csvFileTextField.setText(selectedFile.getAbsolutePath());

                    try {
                        CsvReader csvReader = new CsvReader(new FileReader(selectedFile));
                        csvReader.readHeaders();
                        String[] headers = csvReader.getHeaders();
                        List<Field> fieldsList = new ArrayList<Field>(headers.length);
                        for (String header : headers) {
                            fieldsList.add(new Field(header, ""));
                        }
                        templateData.setFields(fieldsList);
                        fieldList.setListData(fieldsList.toArray(new Field[fieldsList.size()]));
                    } catch (IOException e) {
                        logger.error("Error occurred with file: " + selectedFile, e);
                    }
                }
            }
        });

        fieldList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();

                if (!lsm.isSelectionEmpty()) {
                    //templateTextEditorPane.
                    // TODO insert text value into edtior pane.
                    fieldList.getSelectedValue();
                }
            }
        });

        // add validators to text component.
        emailFromTextField.setInputVerifier(new EmailTextValidator(this, emailFromTextField, "Email address not valid.", false));
        emailReplyToTextField.setInputVerifier(new EmailTextValidator(this, emailReplyToTextField, "Email address not valid.", false));
        emailCCTextField.setInputVerifier(new EmailTextValidator(this, emailCCTextField, "Email address not valid.", true));
        emailBCCTextField.setInputVerifier(new EmailTextValidator(this, emailBCCTextField, "Email address not valid.", true));

        emailToTextField.setInputVerifier(new NotEmptyValidator(this, emailToTextField, "Email address cannot be empty."));
        emailSubjectTextField.setInputVerifier(new NotEmptyValidator(this, emailSubjectTextField, "Subject cannot be empty."));

        //set up wysiwwyg editing
        templateTextEditorPane = new HTMLEditorPane();
        templateTextPanel.add(templateTextEditorPane);
//        templateTextEditorPane.setEditorKitForContentType("text/html", new WysiwygHTMLEditorKit());
//        templateTextEditorPane.setContentType("text/html");
    }

    private void onOK() {
        dataUpdated = false;
        if (isModified(templateData)) {
            getData(templateData);
            templateService.createTemplate(templateData);
            dataUpdated = true;
            logger.info("Saved template: " + templateData.toDebugString());
        }
        dispose();
    }

    private void onCancel() {
        dataUpdated = false;
        dispose();
    }

    private void setEmailData(EMailBean templateData) {
        emailCCTextField.setText(templateData.getCcEmailAddresses());
        emailBCCTextField.setText(templateData.getBccEmailAddresses());
        emailSubjectTextField.setText(templateData.getSubject());
        emailToTextField.setText(templateData.getToEmailAddress());
        emailFromTextField.setText(templateData.getFromEmailAddress());
        emailReplyToTextField.setText(templateData.getReplytoAddress());
    }

    private void getEmailData(EMailBean data) {
        data.setCcEmailAddresses(emailCCTextField.getText());
        data.setBccEmailAddresses(emailBCCTextField.getText());
        data.setSubject(emailSubjectTextField.getText());
        data.setToEmailAddress(emailToTextField.getText());
        data.setFromEmailAddress(emailFromTextField.getText());
        data.setReplytoAddress(emailReplyToTextField.getText());
    }

    private boolean isModified(EMailBean data) {
        if (emailCCTextField.getText() != null ? !emailCCTextField.getText().equals(data.getCcEmailAddresses()) : data.getCcEmailAddresses() != null)
            return true;
        if (emailBCCTextField.getText() != null ? !emailBCCTextField.getText().equals(data.getBccEmailAddresses()) : data.getBccEmailAddresses() != null)
            return true;
        if (emailSubjectTextField.getText() != null ? !emailSubjectTextField.getText().equals(data.getSubject()) : data.getSubject() != null)
            return true;
        if (emailToTextField.getText() != null ? !emailToTextField.getText().equals(data.getToEmailAddress()) : data.getToEmailAddress() != null)
            return true;
        if (emailFromTextField.getText() != null ? !emailFromTextField.getText().equals(data.getFromEmailAddress()) : data.getFromEmailAddress() != null)
            return true;
        if (emailReplyToTextField.getText() != null ? !emailReplyToTextField.getText().equals(data.getReplytoAddress()) : data.getReplytoAddress() != null)
            return true;
        return false;
    }

    public boolean isDataUpdated() {
        return dataUpdated;
    }

    public void setData(TemplateMetaData data) {
        setTemplateData(data);
    }

    public void getData(TemplateMetaData data) {
        getTemplateData(data);
    }

    public void setTemplateData(TemplateMetaData data) {
        templateData = data;
        csvFileTextField.setText(data.getTemplateCSVFile());
        templateNameTextField.setText(data.getTemplateName());
        templateTextEditorPane.setText(data.getTemplateText() == null ? "" : data.getTemplateText());
        setEmailData(data.getEmailBean());
        fieldList.setListData(data.getFields().toArray());
    }

    public void getTemplateData(TemplateMetaData data) {
        data.setTemplateCSVFile(csvFileTextField.getText());
        data.setTemplateName(templateNameTextField.getText());
        data.setTemplateText(templateTextEditorPane.getText());
        getEmailData(data.getEmailBean());
    }

    public boolean isModified(TemplateMetaData data) {
        if (csvFileTextField.getText() != null ? !csvFileTextField.getText().equals(data.getTemplateCSVFile()) : data.getTemplateCSVFile() != null)
            return true;
        if (templateNameTextField.getText() != null ? !templateNameTextField.getText().equals(data.getTemplateName()) : data.getTemplateName() != null)
            return true;
        if (templateTextEditorPane.getText() != null ? !templateTextEditorPane.getText().equals(data.getTemplateText()) : data.getTemplateText() != null)
            return true;
        return isModified(data.getEmailBean());
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    @Override
    public void validateFailed() {
        buttonSave.setEnabled(false);
    }

    @Override
    public void validatePassed() {
        buttonSave.setEnabled(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSave = new JButton();
        buttonSave.setText("Save");
        panel2.add(buttonSave, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Template Name:");
        panel3.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("CSV File:");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        csvFileTextField = new JTextField();
        panel3.add(csvFileTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Email CC:");
        panel3.add(label3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Email BCC:");
        panel3.add(label4, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        templateNameTextField = new JTextField();
        panel3.add(templateNameTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        emailCCTextField = new JTextField();
        panel3.add(emailCCTextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        emailBCCTextField = new JTextField();
        panel3.add(emailBCCTextField, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Email Subject:");
        panel3.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailSubjectTextField = new JTextField();
        panel3.add(emailSubjectTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Email To:");
        panel3.add(label6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailToTextField = new JTextField();
        emailToTextField.setToolTipText("Place the field heading in the CSV file here. During testing, you can supply an email address here. ");
        panel3.add(emailToTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Email From:");
        panel3.add(label7, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Email ReplyTo:");
        panel3.add(label8, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailFromTextField = new JTextField();
        panel3.add(emailFromTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        emailReplyToTextField = new JTextField();
        panel3.add(emailReplyToTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fieldList = new JList();
        fieldList.setSelectionMode(0);
        scrollPane1.setViewportView(fieldList);
        final JLabel label9 = new JLabel();
        label9.setText("Fields:");
        panel3.add(label9, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Template Text:");
        panel3.add(label10, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        templateTextPanel = new JPanel();
        templateTextPanel.setLayout(new BorderLayout(0, 0));
        panel3.add(templateTextPanel, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label1.setLabelFor(templateNameTextField);
        label2.setLabelFor(csvFileTextField);
        label3.setLabelFor(emailCCTextField);
        label4.setLabelFor(emailBCCTextField);
        label5.setLabelFor(emailSubjectTextField);
        label6.setLabelFor(emailToTextField);
        label7.setLabelFor(emailFromTextField);
        label8.setLabelFor(emailReplyToTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
