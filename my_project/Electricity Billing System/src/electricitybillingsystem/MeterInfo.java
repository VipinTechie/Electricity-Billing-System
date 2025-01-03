package electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MeterInfo extends JFrame implements ActionListener {

    Choice meterlocation, metertype, phasecode, billtype;
    JButton next;
    String meternumber;

    MeterInfo(String meternumber) {
        this.meternumber = meternumber;

        // Frame settings
        setSize(700, 500);
        setLocation(400, 200);

        // Panel settings
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        // Heading
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        // Meter Number
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        p.add(lblmeternumber);

        // Meter Location
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 200, 20);
        p.add(meterlocation);

        // Meter Type
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 200, 20);
        p.add(metertype);

        // Phase Code
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240, 200, 200, 20);
        p.add(phasecode);

        // Bill Type
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240, 240, 200, 20);
        p.add(billtype);

        // Days
        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        p.add(lblemail);

        JLabel lblemails = new JLabel("30 days");
        lblemails.setBounds(240, 280, 100, 20);
        p.add(lblemails);

        // Note
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        p.add(lblphone);

        JLabel lblphones = new JLabel("By default, the bill is calculated for 30 days.");
        lblphones.setBounds(240, 320, 400, 20);
        p.add(lblphones);

        // Submit Button
        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        // Image (Optional)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        add(image, BorderLayout.WEST);

        // Final Frame Settings
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Collecting Data
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String bill = billtype.getSelectedItem();
            String days = "30";

            // Insert Query
            String query = "INSERT INTO meter_info (meter, location, type, code, bill, days) VALUES (?, ?, ?, ?, ?, ?)";

            try (Conn c = new Conn(); 
                 PreparedStatement pst = c.getConnection().prepareStatement(query)) {

                pst.setString(1, meternumber);
                pst.setString(2, location);
                pst.setString(3, type);
                pst.setString(4, code);
                pst.setString(5, bill);
                pst.setString(6, days);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MeterInfo("123456");
    }
}
