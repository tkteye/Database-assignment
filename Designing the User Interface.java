import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TrainingCompanyGUI extends JFrame implements ActionListener {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    // GUI Components
    JButton insertBtn, retrieveBtn, updateBtn, deleteBtn, backupBtn;
    JTextField dataField;
    JTextArea resultArea;

    Connection conn = null;
    Statement stmt = null;

    public TrainingCompanyGUI() {
        // Setting up the frame
        setTitle("Training Company Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating buttons
        insertBtn = new JButton("Insert Record");
        retrieveBtn = new JButton("Retrieve Record");
        updateBtn = new JButton("Update Record");
        deleteBtn = new JButton("Delete Record");
        backupBtn = new JButton("Backup");

        // Adding action listeners
        insertBtn.addActionListener(this);
        retrieveBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        backupBtn.addActionListener(this);

        // Creating text field and area
        dataField = new JTextField(20);
        resultArea = new JTextArea(10, 30);

        // Setting up the panel and adding components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Data:"));
        panel.add(dataField);
        panel.add(insertBtn);
        panel.add(retrieveBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(backupBtn);
        panel.add(new JScrollPane(resultArea));

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        // Database operations
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            if (e.getSource() == insertBtn) {
                // Call PL/SQL stored procedure for insert
                CallableStatement cstmt = conn.prepareCall("{call insert_record(?)}");
                cstmt.setString(1, dataField.getText());
                cstmt.execute();
                resultArea.setText("Record inserted.");
            } else if (e.getSource() == retrieveBtn) {
                // Call PL/SQL stored procedure for retrieve
                CallableStatement cstmt = conn.prepareCall("{call retrieve_record(?)}");
                cstmt.setString(1, dataField.getText());
                ResultSet rs = cstmt.executeQuery();
                while (rs.next()) {
                    resultArea.append(rs.getString(1) + "\n");
                }
            } else if (e.getSource() == updateBtn) {
                // Call PL/SQL stored procedure for update
                CallableStatement cstmt = conn.prepareCall("{call update_record(?)}");
                cstmt.setString(1, dataField.getText());
                cstmt.execute();
                resultArea.setText("Record updated.");
            } else if (e.getSource() == deleteBtn) {
                // Call PL/SQL stored procedure for delete
                CallableStatement cstmt = conn.prepareCall("{call delete_record(?)}");
                cstmt.setString(1, dataField.getText());
                cstmt.execute();
                resultArea.setText("Record deleted.");
            } else if (e.getSource() == backupBtn) {
                // Execute backup SQL
                stmt.execute("BACKUP DATABASE TO DISK = 'C:\\backup\\TrainingCompanyDB.bak'");
                resultArea.setText("Database backup completed.");
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TrainingCompanyGUI().setVisible(true);
            }
        });
    }
}
