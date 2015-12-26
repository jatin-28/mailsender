package com.ss.mailshot.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.ss.mailshot.data.datamodel.TemplateMetaData;
import com.ss.mailshot.service.TemplateService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 14-Mar-2010
 * Time: 10:58:15
 */
public class MailShotGUI implements InitializingBean {
    private JPanel panel1;
    private JList templateList;
    private JButton useButton;
    private JButton editButton;
    private JButton newButton;
    private JButton deleteButton;
    private JButton manageMailServersButton;

    @Autowired(required = true)
    private TemplateService templateService;

    @Autowired(required = true)
    private CreateEditTemplate createEditTemplateDialog;

    @Autowired(required = true)
    private ManageMailServers manageMailServers;

    @Autowired(required = true)
    private MailerDialog mailerDialog;

    public MailShotGUI() {
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TemplateMetaData templateData = new TemplateMetaData();
                callCreateEditDialog(templateData);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TemplateMetaData templateData = (TemplateMetaData) templateList.getSelectedValue();
                if (templateData != null) {
                    templateData = templateService.retrieveTemplate(templateData.getId());
                    if (templateData != null) {
                        callCreateEditDialog(templateData);
                    } else {
                        // TODO Toast unable to retrieve template
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TemplateMetaData templateData = (TemplateMetaData) templateList.getSelectedValue();
                if (templateData != null) {
                    if (templateService.deleteTemplate(templateData)) refreshTemplateList();
                }
            }
        });
        manageMailServersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manageMailServers.pack();
                manageMailServers.setVisible(true);
            }
        });
        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TemplateMetaData templateData = (TemplateMetaData) templateList.getSelectedValue();
                if (templateData != null) {
                    mailerDialog.setTemplate(templateData);
                    mailerDialog.pack();
                    mailerDialog.setVisible(true);
                }

            }
        });
    }

    private void callCreateEditDialog(TemplateMetaData templateData) {
        createEditTemplateDialog.setData(templateData);
        createEditTemplateDialog.pack();
        createEditTemplateDialog.setVisible(true);

        // call refreshTemplateList to refreshTemplateList the data.
        //We only want to do this when data was changed
        if (createEditTemplateDialog.isDataUpdated()) refreshTemplateList();
    }

    private void refreshTemplateList() {
        List<TemplateMetaData> templates = templateService.getTemplates();

        if (templates != null && !templates.isEmpty()) {
            Object[] array = templates.toArray(new TemplateMetaData[templates.size()]);
            this.templateList.setListData(array);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshTemplateList();
        JFrame frame = new JFrame("Mailshot");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setVisible(true);
    }

    public void setManageMailServers(ManageMailServers manageMailServers) {
        this.manageMailServers = manageMailServers;
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
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.NORTH);
        final JToolBar toolBar1 = new JToolBar();
        panel2.add(toolBar1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, BorderLayout.SOUTH);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        panel1.add(panel4, BorderLayout.CENTER);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel5.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        templateList = new JList();
        templateList.setSelectionMode(0);
        scrollPane1.setViewportView(templateList);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        panel5.add(panel6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        useButton = new JButton();
        useButton.setText("Use");
        useButton.setMnemonic('U');
        useButton.setDisplayedMnemonicIndex(0);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel6.add(useButton, gbc);
        editButton = new JButton();
        editButton.setText("Edit");
        editButton.setMnemonic('E');
        editButton.setDisplayedMnemonicIndex(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(editButton, gbc);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setMnemonic('D');
        deleteButton.setDisplayedMnemonicIndex(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(deleteButton, gbc);
        newButton = new JButton();
        newButton.setText("New");
        newButton.setMnemonic('N');
        newButton.setDisplayedMnemonicIndex(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(newButton, gbc);
        manageMailServersButton = new JButton();
        manageMailServersButton.setText("Manage Mail Servers");
        manageMailServersButton.setMnemonic('M');
        manageMailServersButton.setDisplayedMnemonicIndex(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(manageMailServersButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}