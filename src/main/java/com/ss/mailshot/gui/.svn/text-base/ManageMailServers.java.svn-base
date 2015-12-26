package com.ss.mailshot.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.ss.mailshot.data.datamodel.MailHost;
import com.ss.mailshot.service.MailServerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ManageMailServers extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField mailServerTextField1;
    private JTextField usernameTextField;
    private JTextField mailPortTextField;
    private JPasswordField passwordPasswordField;
    private JList mailServersList;
    private JButton newButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JCheckBox SMPTAuthCheckBox;
    private JFormattedTextField delayintervalTextField;
    private JFormattedTextField messagesSentPerHourTextField;

    @Autowired(required = true)
    private MailServerService mailServerService;
    private MailHost mailHostData = new MailHost();

    public ManageMailServers() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

// On open
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshMailServersList();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }

            @Override
            public void windowStateChanged(WindowEvent windowEvent) {

            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setData(new MailHost());
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getData(mailHostData);
                mailServerService.createMailServer(mailHostData);
                refreshMailServersList();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MailHost value = (MailHost) mailServersList.getSelectedValue();
                if (value != null) {
                    mailServerService.deleteMailServer(value);
                    setData(new MailHost());
                    refreshMailServersList();
                }
            }
        });
        mailServersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList list = (JList) listSelectionEvent.getSource();
                MailHost value = (MailHost) mailServersList.getSelectedValue();
                if (value != null) {
                    setData(value);
                }
            }
        });

        delayintervalTextField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                String interval = delayintervalTextField.getText();
                long intervalLong = interval == null ? 1 : Long.parseLong(interval);
                messagesSentPerHourTextField.setText((60 * 60 / intervalLong) + "");
            }
        });

        messagesSentPerHourTextField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                String messages = messagesSentPerHourTextField.getText();
                if (messages != null) {
                    int numOfMessages = Integer.parseInt(messages);
                    delayintervalTextField.setText((60 * 60 / numOfMessages) + "");
                }
            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }

    public static void main(String[] args) {
        ManageMailServers dialog = new ManageMailServers();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setData(MailHost data) {
        mailHostData = data;
        nameTextField.setText(data.getName());
        descriptionTextField.setText(data.getDescription());
        mailServerTextField1.setText(data.getMailServer());
        usernameTextField.setText(data.getUsername());
        mailPortTextField.setText(data.getMailServerPort() + "");
        passwordPasswordField.setText(data.getPassword());
        SMPTAuthCheckBox.setSelected(data.isSmtpAuth());
        delayintervalTextField.setText(data.getMessageIntervalInSecs() + "");
        messagesSentPerHourTextField.setText((60 * 60 / data.getMessageIntervalInSecs()) + "");
    }

    public void getData(MailHost data) {
        data.setName(nameTextField.getText());
        data.setDescription(descriptionTextField.getText());
        data.setMailServer(mailServerTextField1.getText());
        data.setUsername(usernameTextField.getText());
        data.setPassword(new String(passwordPasswordField.getPassword()));
        data.setSmtpAuth(SMPTAuthCheckBox.isSelected());

        String port = mailPortTextField.getText();
        data.setMailServerPort(port == null ? null : Integer.parseInt(port));

        String interval = delayintervalTextField.getText();
        long intervalLong = interval == null ? 1 : Long.parseLong(interval);
        data.setMessageIntervalInSecs(intervalLong);
    }

    public boolean isModified(MailHost data) {
        if (nameTextField.getText() != null ? !nameTextField.getText().equals(data.getName()) : data.getName() != null)
            return true;
        if (descriptionTextField.getText() != null ? !descriptionTextField.getText().equals(data.getDescription()) : data.getDescription() != null)
            return true;
        if (mailServerTextField1.getText() != null ? !mailServerTextField1.getText().equals(data.getMailServer()) : data.getMailServer() != null)
            return true;
        if (usernameTextField.getText() != null ? !usernameTextField.getText().equals(data.getUsername()) : data.getUsername() != null)
            return true;
        if (mailPortTextField.getText() != null ? !mailPortTextField.getText().equals(data.getMailServerPort()) : data.getMailServerPort() != null)
            return true;
        if (delayintervalTextField.getText() != null ? !delayintervalTextField.getText().equals(data.getMessageIntervalInSecs()) : data.getMessageIntervalInSecs() != null)
            return true;
        if (passwordPasswordField.getPassword() != null ? !new String(passwordPasswordField.getPassword()).equals(data.getPassword()) : data.getPassword() != null)
            return true;
        if (SMPTAuthCheckBox.isSelected() != data.isSmtpAuth()) return true;
        return false;
    }

    public void setMailServerService(MailServerService mailServerService) {
        this.mailServerService = mailServerService;
    }

    private void refreshMailServersList() {
        List<MailHost> mailHosts = mailServerService.getMailServers();

        if (mailHosts != null && !mailHosts.isEmpty()) {
            Object[] array = mailHosts.toArray(new MailHost[mailHosts.size()]);
            this.mailServersList.setListData(array);
        }
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
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Name:");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        panel3.add(nameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Description:");
        panel3.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionTextField = new JTextField();
        panel3.add(descriptionTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Mail Server:");
        panel3.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Username:");
        panel3.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mailServerTextField1 = new JTextField();
        panel3.add(mailServerTextField1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameTextField = new JTextField();
        panel3.add(usernameTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Mail Port:");
        panel3.add(label5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Password:");
        panel3.add(label6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mailPortTextField = new JTextField();
        panel3.add(mailPortTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordPasswordField = new JPasswordField();
        panel3.add(passwordPasswordField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        mailServersList = new JList();
        mailServersList.setSelectionMode(0);
        scrollPane1.setViewportView(mailServersList);
        final JLabel label7 = new JLabel();
        label7.setText("Mail Servers:");
        panel3.add(label7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel3.add(panel4, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        newButton = new JButton();
        newButton.setText("New");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel4.add(newButton, gbc);
        saveButton = new JButton();
        saveButton.setText("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(saveButton, gbc);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(deleteButton, gbc);
        SMPTAuthCheckBox = new JCheckBox();
        SMPTAuthCheckBox.setHorizontalTextPosition(10);
        SMPTAuthCheckBox.setText("SMPT Auth:");
        panel3.add(SMPTAuthCheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Delay Interval per Messages(secs):");
        label8.setToolTipText("The interval before the next message is sent.");
        panel3.add(label8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Messages Sent Per Hour");
        panel3.add(label9, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayintervalTextField = new JFormattedTextField();
        delayintervalTextField.setText("1");
        panel3.add(delayintervalTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        messagesSentPerHourTextField = new JFormattedTextField();
        panel3.add(messagesSentPerHourTextField, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label1.setLabelFor(nameTextField);
        label2.setLabelFor(descriptionTextField);
        label3.setLabelFor(mailServerTextField1);
        label4.setLabelFor(usernameTextField);
        label5.setLabelFor(mailPortTextField);
        label6.setLabelFor(passwordPasswordField);
        label7.setLabelFor(scrollPane1);
        label9.setLabelFor(messagesSentPerHourTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
