
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
public class undergraduate_soc extends javax.swing.JFrame {
    
           ArrayList<String> degreeArray = new ArrayList<>();
         ArrayList<String> moduleArray = new ArrayList<>();

    /**
     * Creates new form undergraduate_sob
     */
           String gender;
    public undergraduate_soc() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        
        fill_degree();
        fill_module();
        show_undergraduate_student();
        show_module();
    } 
    
    private void fill_degree(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM degree WHERE faculty='Computing'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String degree;
                degree = rs.getString("degree_title");
                degree_cbe.addItem(degree);
                degree_cbv.addItem(degree);
                degreeArray.add(degree);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void fill_module(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM module WHERE faculty='Computing'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String module;
                module = rs.getString("module_code");
                module_code_cbr.addItem(module);
                select_module_cbrr.addItem(module);
                moduleArray.add(module);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public ArrayList<undergraduate_student> ugsList(){
        ArrayList<undergraduate_student> ugsList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `register_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `dob`, `gender`, `admission_date`, `degree`, `intake`, `subject1`, `subject1_result`, `subject2`, `subject2_result`, `subject3`, `subject3_result`, `english_result`, `district_rank`, `island_rank` FROM `student` WHERE faculty='Computing' AND undergraduate_flag='Yes' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            undergraduate_student ugs;
            while(rs.next()){
               ugs = new undergraduate_student(rs.getString("register_no"),rs.getString("name"),rs.getString("address"),rs.getString("nic_no"),rs.getString("contact_no"),rs.getString("email"),rs.getString("dob"),rs.getString("gender"),rs.getString("admission_date"),rs.getString("degree"),rs.getString("intake"),rs.getString("subject1"),rs.getString("subject1_result"),rs.getString("subject2"),rs.getString("subject2_result"),rs.getString("subject3"),rs.getString("subject3_result"),rs.getString("english_result"),rs.getString("district_rank"),rs.getString("island_rank") );
               ugsList.add(ugs);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return ugsList;
    }
    
    private void show_undergraduate_student(){
        ArrayList<undergraduate_student> list = ugsList();
        DefaultTableModel model = (DefaultTableModel) ugs_table.getModel();
        Object[] row = new Object[20];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getRegister_no();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getNic_no();
            row[4] = list.get(i).getContact_no();
            row[5] = list.get(i).getEmail();
            row[6] = list.get(i).getDob();
            row[7] = list.get(i).getGender();
            row[8] = list.get(i).getAdmission_date();
            row[9] = list.get(i).getDegree();
            row[10] = list.get(i).getIntake();
            row[11] = list.get(i).getSubject1();
            row[12] = list.get(i).getSubject1_result();
            row[13] = list.get(i).getSubject2();
            row[14] = list.get(i).getSubject2_result();
            row[15] = list.get(i).getSubject3();
            row[16] = list.get(i).getSubject3_result();
            row[17] = list.get(i).getEnglish_result();
            row[18] = list.get(i).getDistrict_rank();
            row[19] = list.get(i).getIsland_rank();
            model.addRow(row);
        }
    }
    
    private ArrayList<module> moduleList(){
        ArrayList<module> moduleList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT module.module_title, module.semester, module.credit_value, module.fee FROM follow INNER JOIN module ON follow.module_code=module.module_code WHERE follow.register_no=? AND module.year=?";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            module mod;
            while(rs.next()){
               mod = new module(rs.getString("module_title"), rs.getInt("semester"), rs.getInt("credit_value"), rs.getInt("fee"));
               moduleList.add(mod);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return moduleList;
    }
    
    private void show_module(){
        ArrayList<module> list = moduleList();
        DefaultTableModel model = (DefaultTableModel) follow_table.getModel();
        Object[] row = new Object[4];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_title();
            row[1] = list.get(i).getSemester();
            row[2] = list.get(i).getCredit_value();
            row[3] = list.get(i).getFee();
            model.addRow(row);
        }
    }
    
    private ArrayList<result> resultList(){
        ArrayList<result> resultList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT module_code, grade FROM results WHERE register_no=? AND module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, register_no_tbr.getText());
            pst.setString(2, module_code_cbr.getSelectedItem().toString());
            ResultSet rs = pst.executeQuery();
            result result;
            while(rs.next()){
               result = new result(rs.getString("module_code"), rs.getString("grade"));
               resultList.add(result);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return resultList;
    }
    
    private void show_result(){
        ArrayList<result> list = resultList();
        DefaultTableModel model = (DefaultTableModel) results_table.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_code();
            row[1] = list.get(i).getGrade();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        enroll_btn = new javax.swing.JButton();
        register_btn = new javax.swing.JButton();
        view_btn = new javax.swing.JButton();
        results_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        home_btn = new javax.swing.JButton();
        logo_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        enroll_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        enroll__btne = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        register_no_tbe = new javax.swing.JTextField();
        name_tbe = new javax.swing.JTextField();
        address_tbe = new javax.swing.JTextField();
        nic_no_tbe = new javax.swing.JTextField();
        contact_no_tbe = new javax.swing.JTextField();
        email_tbe = new javax.swing.JTextField();
        dob_tbe = new com.toedter.calendar.JDateChooser();
        male_rbe = new javax.swing.JRadioButton();
        female_rbe = new javax.swing.JRadioButton();
        admission_date_tbe = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        degree_cbe = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        subject1_tbe = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        subject2_tbe = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        subject3_tbe = new javax.swing.JTextField();
        subject1_cbe = new javax.swing.JComboBox<>();
        subject2_cbe = new javax.swing.JComboBox<>();
        subject3_cbe = new javax.swing.JComboBox<>();
        general_english_cbe = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        district_rank_tbe = new javax.swing.JTextField();
        island_rank_tbe = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        intake_cbe = new javax.swing.JComboBox<>();
        reset_btne = new javax.swing.JButton();
        view_panel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        dob_tbv = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        address_tbv = new javax.swing.JTextField();
        male_rbv = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        female_rbv = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        admission_date_tbv = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        name_tbv = new javax.swing.JTextField();
        degree_cbv = new javax.swing.JComboBox<>();
        nic_no_tbv = new javax.swing.JTextField();
        contact_no_tbv = new javax.swing.JTextField();
        register_no_tbv = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        email_tbv = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        subject1_tbv = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        subject2_tbv = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        subject3_tbv = new javax.swing.JTextField();
        subject1_cbv = new javax.swing.JComboBox<>();
        subject2_cbv = new javax.swing.JComboBox<>();
        subject3_cbv = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        general_english_cbv = new javax.swing.JComboBox<>();
        reset_btnv = new javax.swing.JButton();
        intake_cbv = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        island_rank_tbv = new javax.swing.JTextField();
        district_rank_tbv = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        search_btnv = new javax.swing.JButton();
        update_btnv = new javax.swing.JButton();
        delete_btnv = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ugs_table = new javax.swing.JTable();
        results_panel = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        register_no_tbr = new javax.swing.JTextField();
        module_code_cbr = new javax.swing.JComboBox<>();
        grade_cbr = new javax.swing.JComboBox<>();
        search_btnr = new javax.swing.JButton();
        reset_btnr = new javax.swing.JButton();
        add_result_btnr = new javax.swing.JButton();
        update_btnr = new javax.swing.JButton();
        delete_btnr = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        results_table = new javax.swing.JTable();
        show_table_btnr = new javax.swing.JButton();
        register_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        register_no_tbrr = new javax.swing.JTextField();
        year_cbrr = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        follow_table = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        total_credits_tbrr = new javax.swing.JTextField();
        total_fees_tbrr = new javax.swing.JTextField();
        pay_btnrr = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        semester_cbrr = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        select_module_cbrr = new javax.swing.JComboBox<>();
        register_btnrr = new javax.swing.JButton();
        show_subject_btnrr = new javax.swing.JButton();
        delete_btnrr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Undergraduate Student | School of Computing");

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        enroll_btn.setBackground(new java.awt.Color(255, 255, 51));
        enroll_btn.setText("Enroll Student");
        enroll_btn.setPreferredSize(new java.awt.Dimension(120, 25));
        enroll_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enroll_btnActionPerformed(evt);
            }
        });

        register_btn.setBackground(new java.awt.Color(255, 255, 51));
        register_btn.setText("Register Subject");
        register_btn.setPreferredSize(new java.awt.Dimension(120, 25));
        register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register_btnActionPerformed(evt);
            }
        });

        view_btn.setBackground(new java.awt.Color(255, 255, 51));
        view_btn.setText("View Student");
        view_btn.setPreferredSize(new java.awt.Dimension(120, 25));
        view_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_btnActionPerformed(evt);
            }
        });

        results_btn.setBackground(new java.awt.Color(255, 255, 51));
        results_btn.setText("Results");
        results_btn.setPreferredSize(new java.awt.Dimension(120, 25));
        results_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                results_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enroll_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(results_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(enroll_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(view_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(results_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(102, 204, 0));

        home_btn.setBackground(new java.awt.Color(255, 51, 51));
        home_btn.setText("Home");
        home_btn.setMaximumSize(new java.awt.Dimension(60, 60));
        home_btn.setMinimumSize(new java.awt.Dimension(60, 60));
        home_btn.setPreferredSize(new java.awt.Dimension(80, 80));
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });

        logo_label.setBackground(new java.awt.Color(102, 204, 0));
        logo_label.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        logo_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_label.setText("NSBM Green University");
        logo_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1130, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(logo_label)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(logo_label)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 255, 0));
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Register Number");

        enroll__btne.setText("Enroll -->");
        enroll__btne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enroll__btneActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel6.setText("Address");

        jLabel7.setText("Contact Number");

        jLabel8.setText("Email");

        jLabel9.setText("Date of birth");

        jLabel10.setText("Gender");

        jLabel11.setText("Admission Date");

        jLabel12.setText("NIC Number");

        buttonGroup1.add(male_rbe);
        male_rbe.setText("Male");

        buttonGroup1.add(female_rbe);
        female_rbe.setText("Female");

        jLabel13.setText("Degree");

        jLabel14.setText("A/L Results");

        jLabel15.setText("Subject 1");

        jLabel16.setText("Subject 2");

        jLabel17.setText("Subject 3");

        subject1_cbe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        subject2_cbe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        subject3_cbe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        general_english_cbe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        jLabel18.setText("General English");

        jLabel19.setText("District Rank");

        jLabel20.setText("Island Rank");

        jLabel21.setText("Intake");

        intake_cbe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "February", "July" }));

        reset_btne.setText("Reset");
        reset_btne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enroll_panelLayout = new javax.swing.GroupLayout(enroll_panel);
        enroll_panel.setLayout(enroll_panelLayout);
        enroll_panelLayout.setHorizontalGroup(
            enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enroll_panelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(enroll_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(reset_btne))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, enroll_panelLayout.createSequentialGroup()
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, enroll_panelLayout.createSequentialGroup()
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(30, 30, 30)
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email_tbe)
                                    .addComponent(contact_no_tbe)
                                    .addComponent(dob_tbe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(admission_date_tbe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(enroll_panelLayout.createSequentialGroup()
                                        .addComponent(male_rbe)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(female_rbe))
                                    .addComponent(degree_cbe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(intake_cbe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(register_no_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(address_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nic_no_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(158, 158, 158)))
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enroll_panelLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(subject2_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subject2_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(subject1_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subject1_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enroll_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(49, 49, 49))
                                    .addGroup(enroll_panelLayout.createSequentialGroup()
                                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(subject3_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel19)
                                                    .addComponent(jLabel20))
                                                .addGap(27, 27, 27)
                                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(enroll__btne)
                                                    .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(district_rank_tbe, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                        .addComponent(island_rank_tbe)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(general_english_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subject3_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(enroll_panelLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel14)))
                .addGap(171, 171, 171))
        );
        enroll_panelLayout.setVerticalGroup(
            enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enroll_panelLayout.createSequentialGroup()
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enroll_panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(register_no_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(address_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nic_no_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contact_no_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(21, 21, 21)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(email_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dob_tbe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(enroll_panelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(enroll_panelLayout.createSequentialGroup()
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(subject1_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(subject1_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(subject2_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(subject2_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(subject3_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subject3_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(general_english_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel19))
                            .addComponent(district_rank_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(island_rank_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(male_rbe)
                    .addComponent(female_rbe))
                .addGap(18, 18, 18)
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(admission_date_tbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(degree_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(enroll_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enroll__btne)
                    .addComponent(reset_btne)
                    .addComponent(intake_cbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jPanel3.add(enroll_panel, "card3");

        jLabel24.setText("Register Number");

        jLabel25.setText("Name");

        jLabel26.setText("Contact Number");

        buttonGroup1.add(male_rbv);
        male_rbv.setText("Male");

        jLabel27.setText("Date of birth");

        buttonGroup1.add(female_rbv);
        female_rbv.setText("Female");

        jLabel28.setText("Email");

        jLabel29.setText("Gender");

        jLabel30.setText("NIC Number");

        jLabel31.setText("A/L Results");

        jLabel32.setText("Degree");

        jLabel33.setText("Address");

        jLabel34.setText("Admission Date");

        jLabel35.setText("Subject 2");

        jLabel36.setText("Subject 3");

        subject1_cbv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        subject2_cbv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        subject3_cbv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        jLabel37.setText("Subject 1");

        general_english_cbv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F" }));

        reset_btnv.setText("Reset");
        reset_btnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnvActionPerformed(evt);
            }
        });

        intake_cbv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "February", "July" }));

        jLabel38.setText("Intake");

        jLabel39.setText("Island Rank");

        jLabel40.setText("District Rank");

        jLabel41.setText("General English");

        search_btnv.setText("Search");
        search_btnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnvActionPerformed(evt);
            }
        });

        update_btnv.setText("Update");
        update_btnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnvActionPerformed(evt);
            }
        });

        delete_btnv.setText("Delete");
        delete_btnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnvActionPerformed(evt);
            }
        });

        ugs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Register No", "Name", "Address", "NIC Number", "Contact Number", "Email", "DOB", "Gender", "Admission Date", "Degree", "Intake", "Sub 1", "Result", "Sub 2", "Result", "Sub 3 ", "Result", "English", "District Rank", "Island Rank"
            }
        ));
        ugs_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ugs_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ugs_table);

        javax.swing.GroupLayout view_panelLayout = new javax.swing.GroupLayout(view_panel);
        view_panel.setLayout(view_panelLayout);
        view_panelLayout.setHorizontalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_panelLayout.createSequentialGroup()
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(search_btnv)
                        .addGap(67, 67, 67)
                        .addComponent(reset_btnv)
                        .addGap(74, 74, 74)
                        .addComponent(update_btnv))
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(view_panelLayout.createSequentialGroup()
                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28))
                                .addGap(54, 54, 54)
                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(name_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(register_no_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(address_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(nic_no_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(contact_no_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(email_tbv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(view_panelLayout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel41))
                                    .addGroup(view_panelLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(delete_btnv)
                                            .addGroup(view_panelLayout.createSequentialGroup()
                                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel32)
                                                    .addComponent(jLabel34)
                                                    .addComponent(jLabel29)
                                                    .addComponent(jLabel27))
                                                .addGap(42, 42, 42)
                                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(dob_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(degree_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(view_panelLayout.createSequentialGroup()
                                                        .addComponent(male_rbv)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(female_rbv))
                                                    .addComponent(admission_date_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(intake_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(85, 85, 85)
                                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel37)
                                            .addComponent(jLabel40))
                                        .addGap(49, 49, 49)
                                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(view_panelLayout.createSequentialGroup()
                                                .addComponent(district_rank_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel39))
                                            .addComponent(subject1_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subject2_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subject3_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31))))
                                .addGap(34, 34, 34)
                                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(general_english_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subject3_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subject2_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subject1_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(island_rank_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        view_panelLayout.setVerticalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(register_no_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27))
                            .addComponent(dob_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel29)
                            .addComponent(male_rbv)
                            .addComponent(female_rbv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admission_date_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(address_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))))
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(subject1_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject1_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subject2_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(subject2_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(nic_no_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(subject3_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degree_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject3_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(contact_no_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intake_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel41)
                    .addComponent(general_english_cbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel40)
                    .addComponent(district_rank_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(island_rank_tbv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_btnv)
                    .addComponent(reset_btnv)
                    .addComponent(update_btnv)
                    .addComponent(delete_btnv))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel3.add(view_panel, "card5");

        jLabel44.setText("Register Number");

        jLabel45.setText("Module Code");

        jLabel46.setText("Grade");

        grade_cbr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E" }));

        search_btnr.setText("Search");
        search_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnrActionPerformed(evt);
            }
        });

        reset_btnr.setText("Reset");
        reset_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnrActionPerformed(evt);
            }
        });

        add_result_btnr.setText("Add Result");
        add_result_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_result_btnrActionPerformed(evt);
            }
        });

        update_btnr.setText("Update");
        update_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnrActionPerformed(evt);
            }
        });

        delete_btnr.setText("Delete");
        delete_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnrActionPerformed(evt);
            }
        });

        results_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module", "Grade"
            }
        ));
        results_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                results_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(results_table);

        show_table_btnr.setText("Show Results");
        show_table_btnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_table_btnrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout results_panelLayout = new javax.swing.GroupLayout(results_panel);
        results_panel.setLayout(results_panelLayout);
        results_panelLayout.setHorizontalGroup(
            results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(results_panelLayout.createSequentialGroup()
                .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(update_btnr)
                    .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(results_panelLayout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addComponent(jLabel44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, results_panelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(reset_btnr, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(88, 88, 88)
                .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(add_result_btnr)
                    .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(grade_cbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(results_panelLayout.createSequentialGroup()
                                .addComponent(module_code_cbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search_btnr))
                            .addComponent(register_no_tbr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(delete_btnr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(show_table_btnr)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        results_panelLayout.setVerticalGroup(
            results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(results_panelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(results_panelLayout.createSequentialGroup()
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(register_no_tbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(show_table_btnr))
                        .addGap(19, 19, 19)
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(module_code_cbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_btnr))
                        .addGap(35, 35, 35)
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(grade_cbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reset_btnr)
                            .addComponent(add_result_btnr))
                        .addGap(32, 32, 32)
                        .addGroup(results_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update_btnr)
                            .addComponent(delete_btnr))))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jPanel3.add(results_panel, "card6");

        jLabel5.setText("Register Number");

        jLabel47.setText("Year");

        year_cbrr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        follow_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module", "Semester", "Credit", "Fee", "Payment"
            }
        ));
        jScrollPane6.setViewportView(follow_table);
        if (follow_table.getColumnModel().getColumnCount() > 0) {
            follow_table.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel48.setText("Total Credits");

        jLabel49.setText("Payment Left");

        pay_btnrr.setText("Pay All");
        pay_btnrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_btnrrActionPerformed(evt);
            }
        });

        jLabel52.setText("Semester");

        semester_cbrr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        semester_cbrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semester_cbrrActionPerformed(evt);
            }
        });

        jLabel50.setText("Select Module");

        register_btnrr.setText("Register");
        register_btnrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register_btnrrActionPerformed(evt);
            }
        });

        show_subject_btnrr.setText("Search");
        show_subject_btnrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_subject_btnrrActionPerformed(evt);
            }
        });

        delete_btnrr.setText("Remove Module");
        delete_btnrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnrrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout register_panelLayout = new javax.swing.GroupLayout(register_panel);
        register_panel.setLayout(register_panelLayout);
        register_panelLayout.setHorizontalGroup(
            register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_panelLayout.createSequentialGroup()
                .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel47)
                            .addComponent(jLabel52)
                            .addComponent(jLabel50)))
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(register_btnrr)))
                .addGap(63, 63, 63)
                .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addComponent(delete_btnrr)
                        .addGap(172, 172, 172)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(register_panelLayout.createSequentialGroup()
                                .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel49))
                                .addGap(66, 66, 66)
                                .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pay_btnrr)
                                    .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(total_credits_tbrr, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(total_fees_tbrr))))))
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addComponent(register_no_tbrr, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(show_subject_btnrr))
                    .addComponent(year_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semester_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select_module_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        register_panelLayout.setVerticalGroup(
            register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_panelLayout.createSequentialGroup()
                .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(total_credits_tbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(total_fees_tbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(register_panelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(register_no_tbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(show_subject_btnrr))
                        .addGap(18, 18, 18)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(year_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(semester_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(select_module_cbrr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(register_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(register_btnrr)
                            .addComponent(delete_btnrr))))
                .addGap(45, 45, 45)
                .addComponent(pay_btnrr)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jPanel3.add(register_panel, "card4");

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enroll_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enroll_btnActionPerformed
        enroll_panel.setVisible(true);
        register_panel.setVisible(false);
        view_panel.setVisible(false);
        results_panel.setVisible(false);
        
    }//GEN-LAST:event_enroll_btnActionPerformed

    private void register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register_btnActionPerformed
         enroll_panel.setVisible(false);
        register_panel.setVisible(true);
        view_panel.setVisible(false);
        results_panel.setVisible(false);
    }//GEN-LAST:event_register_btnActionPerformed

    private void view_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_btnActionPerformed
        enroll_panel.setVisible(false);
        register_panel.setVisible(false);
        view_panel.setVisible(true);
        results_panel.setVisible(false);
    }//GEN-LAST:event_view_btnActionPerformed

    private void results_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_results_btnActionPerformed
        enroll_panel.setVisible(false);
        register_panel.setVisible(false);
        view_panel.setVisible(false);
        results_panel.setVisible(true);
    }//GEN-LAST:event_results_btnActionPerformed

    private void enroll__btneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enroll__btneActionPerformed
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO student (`register_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `dob`, `gender`, `admission_date`, `degree`, `intake`, `subject1`, `subject1_result`, `subject2`, `subject2_result`, `subject3`, `subject3_result`, `english_result`, `district_rank`, `island_rank`, `undergraduate_flag`, `postgraduate_flag`,`faculty`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,register_no_tbe.getText());
            pst.setString(2,name_tbe.getText());
            pst.setString(3,address_tbe.getText());
            pst.setString(4,nic_no_tbe.getText());
            pst.setString(5,contact_no_tbe.getText());
            pst.setString(6,email_tbe.getText());
            SimpleDateFormat sdf_dob = new SimpleDateFormat("yyyy-MM-dd");
            String dob = sdf_dob.format(dob_tbe.getDate());
            pst.setString(7,dob);
            if(male_rbe.isSelected()){
                gender="Male";
            }
            else if(female_rbe.isSelected()){
                gender="Female";
            }
            pst.setString(8,gender);
            SimpleDateFormat sdf_a_date = new SimpleDateFormat("yyyy-MM-dd");
            String a_date = sdf_a_date.format(dob_tbe.getDate());
            pst.setString(9,a_date);
            String degree;
            degree = degree_cbe.getSelectedItem().toString();
            pst.setString(10,degree);
            String intake;
            intake = intake_cbe.getSelectedItem().toString();
            pst.setString(11,intake);
            pst.setString(12,subject1_tbe.getText());
            String sub1;
            sub1 = subject1_cbe.getSelectedItem().toString();
            pst.setString(13,sub1);
            pst.setString(14,subject2_tbe.getText());
             String sub2;
            sub2 = subject2_cbe.getSelectedItem().toString();
            pst.setString(15,sub2);
            pst.setString(16,subject3_tbe.getText());
             String sub3;
            sub3 = subject3_cbe.getSelectedItem().toString();
            pst.setString(17,sub3);
             String ge;
            ge = general_english_cbe.getSelectedItem().toString();
            pst.setString(18,ge);
            pst.setString(19,district_rank_tbe.getText());
            pst.setString(20,island_rank_tbe.getText());
            pst.setString(21,"Yes");
            pst.setString(22, "No");
            pst.setString(23, "Computing");
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) ugs_table.getModel();
            model.setRowCount(0);
            show_undergraduate_student();
            JOptionPane.showMessageDialog(null,"Inserted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_enroll__btneActionPerformed

    private void reset_btneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btneActionPerformed
        register_no_tbe.setText("");
        name_tbe.setText("");
        address_tbe.setText("");
        nic_no_tbe.setText("");
        contact_no_tbe.setText("");
        email_tbe.setText("");
        dob_tbe.setCalendar(null);
        buttonGroup1.clearSelection();
        admission_date_tbe.setCalendar(null);
        degree_cbe.setSelectedIndex(0);
        intake_cbe.setSelectedIndex(0);
        subject1_tbe.setText("");
        subject1_cbe.setSelectedIndex(0);
        subject2_tbe.setText("");
        subject2_cbe.setSelectedIndex(0);
        subject3_tbe.setText("");
        subject3_cbe.setSelectedIndex(0);
        general_english_cbe.setSelectedIndex(0);
        district_rank_tbe.setText("");
        island_rank_tbe.setText("");
    }//GEN-LAST:event_reset_btneActionPerformed

    private void reset_btnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnvActionPerformed
        register_no_tbv.setText("");
        name_tbv.setText("");
        address_tbv.setText("");
        nic_no_tbv.setText("");
        contact_no_tbv.setText("");
        email_tbv.setText("");
        dob_tbv.setCalendar(null);
        buttonGroup1.clearSelection();
        admission_date_tbv.setCalendar(null);
        degree_cbv.setSelectedIndex(0);
        intake_cbv.setSelectedIndex(0);
        subject1_tbv.setText("");
        subject1_cbv.setSelectedIndex(0);
        subject2_tbv.setText("");
        subject2_cbv.setSelectedIndex(0);
        subject3_tbv.setText("");
        subject3_cbv.setSelectedIndex(0);
        general_english_cbv.setSelectedIndex(0);
        district_rank_tbv.setText("");
        island_rank_tbv.setText("");
    }//GEN-LAST:event_reset_btnvActionPerformed

    private void search_btnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnvActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM student WHERE register_no=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, register_no_tbv.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_name = rs.getString("name");
                name_tbv.setText(set_name);
                
                String set_address = rs.getString("address");
                address_tbv.setText(set_address);
                
                String set_nic_no = rs.getString("nic_no");
                nic_no_tbv.setText(set_nic_no);
                
                String set_contact_no = rs.getString("contact_no");
                contact_no_tbv.setText(set_contact_no);
                
                 String set_email = rs.getString("email");
                email_tbv.setText(set_email);
                
                dob_tbv.setDate(rs.getDate("dob"));
                
                 String set_gender = rs.getString("gender");
                if(set_gender.equals("Male")){
                    male_rbv.setSelected(true);
                }
                else{
                    female_rbv.setSelected(true);
                }
                
                admission_date_tbv.setDate(rs.getDate("admission_date"));
                
                String set_degree = rs.getString("degree");
                switch(set_degree){
                    case "Item 1":
                        degree_cbv.setSelectedIndex(0);
                        break;
                     case "Item 2":
                        degree_cbv.setSelectedIndex(1);
                        break;
                     case "Item 3":
                        degree_cbv.setSelectedIndex(2);
                        break;
                     case "Item 4":
                        degree_cbv.setSelectedIndex(3);
                        break;
                }
                
                String set_intake = rs.getString("intake");
                switch(set_intake){
                    case "February":
                        intake_cbv.setSelectedIndex(0);
                        break;
                     case "July":
                        intake_cbv.setSelectedIndex(1);
                }
                
                String set_sub1 = rs.getString("subject1");
                subject1_tbv.setText(set_sub1);
                
                String set_sub1r = rs.getString("subject1_result");
                switch(set_sub1r){
                    case "A":
                        subject1_cbv.setSelectedIndex(0);
                        break;
                     case "B":
                        subject1_cbv.setSelectedIndex(1);
                        break;
                     case "C":
                        subject1_cbv.setSelectedIndex(2);
                        break;
                     case "S":
                        subject1_cbv.setSelectedIndex(3);
                        break;
                      case "F":
                        subject1_cbv.setSelectedIndex(4);
                        break;
                }
                
                String set_sub2 = rs.getString("subject2");
                subject2_tbv.setText(set_sub2);
                
                String set_sub2r = rs.getString("subject2_result");
                switch(set_sub2r){
                    case "A":
                        subject2_cbv.setSelectedIndex(0);
                        break;
                     case "B":
                        subject2_cbv.setSelectedIndex(1);
                        break;
                     case "C":
                        subject2_cbv.setSelectedIndex(2);
                        break;
                     case "S":
                        subject2_cbv.setSelectedIndex(3);
                        break;
                      case "F":
                        subject2_cbv.setSelectedIndex(4);
                        break;
                }
                
                 String set_sub3 = rs.getString("subject3");
                subject3_tbv.setText(set_sub3);
                
                String set_sub3r = rs.getString("subject3_result");
                switch(set_sub3r){
                    case "A":
                        subject3_cbv.setSelectedIndex(0);
                        break;
                     case "B":
                        subject3_cbv.setSelectedIndex(1);
                        break;
                     case "C":
                        subject3_cbv.setSelectedIndex(2);
                        break;
                     case "S":
                        subject3_cbv.setSelectedIndex(3);
                        break;
                      case "F":
                        subject3_cbv.setSelectedIndex(4);
                        break;
                }
                
                String set_ger = rs.getString("english_result");
                switch(set_ger){
                    case "A":
                        general_english_cbv.setSelectedIndex(0);
                        break;
                     case "B":
                       general_english_cbv.setSelectedIndex(1);
                        break;
                     case "C":
                        general_english_cbv.setSelectedIndex(2);
                        break;
                     case "S":
                        general_english_cbv.setSelectedIndex(3);
                        break;
                      case "F":
                        general_english_cbv.setSelectedIndex(4);
                        break;
                }
                
                 String set_dr = rs.getString("district_rank");
                district_rank_tbv.setText(set_dr);
                
                 String set_ir = rs.getString("island_rank");
                island_rank_tbv.setText(set_ir);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnvActionPerformed

    private void update_btnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnvActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE student SET  register_no=?, name=?, address =?, nic_no=?, contact_no=?, email=?, dob=?, gender=?, admission_date=?, degree=?, intake=?, subject1=?, subject1_result=?, subject2=?, subject2_result=?, subject3=?, subject3_result=?, english_result=?, district_rank=?, island_rank=? WHERE register_no=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1, register_no_tbv.getText());
            pst.setString(2,name_tbv.getText());
            pst.setString(3,address_tbv.getText());
            pst.setString(4,nic_no_tbv.getText());
            pst.setString(5,contact_no_tbv.getText());
            pst.setString(6,email_tbv.getText());
            SimpleDateFormat sdf_dob = new SimpleDateFormat("yyyy-MM-dd");
            String dob = sdf_dob.format(dob_tbv.getDate());
            pst.setString(7,dob);
            if(male_rbv.isSelected()){
                gender="Male";
            }
            else if(female_rbv.isSelected()){
                gender="Female";
            }
            pst.setString(8,gender);
            SimpleDateFormat sdf_a_date = new SimpleDateFormat("yyyy-MM-dd");
            String a_date = sdf_a_date.format(admission_date_tbv.getDate());
            pst.setString(9,a_date);
            String degree;
            degree = degree_cbv.getSelectedItem().toString();
            pst.setString(10,degree);
            String intake;
            intake = intake_cbv.getSelectedItem().toString();
            pst.setString(11,intake);
            pst.setString(12,subject1_tbv.getText());
            String sub1;
            sub1 = subject1_cbv.getSelectedItem().toString();
            pst.setString(13,sub1);
            pst.setString(14,subject2_tbv.getText());
             String sub2;
            sub2 = subject2_cbv.getSelectedItem().toString();
            pst.setString(15,sub2);
            pst.setString(16,subject3_tbv.getText());
             String sub3;
            sub3 = subject3_cbv.getSelectedItem().toString();
            pst.setString(17,sub3);
             String ge;
            ge = general_english_cbv.getSelectedItem().toString();
            pst.setString(18,ge);
            pst.setString(19,district_rank_tbv.getText());
            pst.setString(20,island_rank_tbv.getText());
            pst.setString(21,register_no_tbv.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) ugs_table.getModel();
            model.setRowCount(0);
            show_undergraduate_student();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btnvActionPerformed

    private void delete_btnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnvActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM student WHERE register_no=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1,register_no_tbv.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) ugs_table.getModel();
            model.setRowCount(0);
            show_undergraduate_student();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
                
    }//GEN-LAST:event_delete_btnvActionPerformed

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        home_soc page = new home_soc();
        page.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_home_btnActionPerformed

    private void ugs_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ugs_tableMouseClicked
        int i = ugs_table.getSelectedRow();
        TableModel model = ugs_table.getModel();
        register_no_tbv.setText(model.getValueAt(i, 0).toString());
        name_tbv.setText(model.getValueAt(i, 1).toString());
        address_tbv.setText(model.getValueAt(i, 2).toString());
        nic_no_tbv.setText(model.getValueAt(i, 3).toString());
        contact_no_tbv.setText(model.getValueAt(i, 4).toString());
        email_tbv.setText(model.getValueAt(i, 5).toString());
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 6));
            dob_tbv.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        String gender = model.getValueAt(i, 7).toString();
        if(gender.equals("Male")){
            male_rbv.setSelected(true);
        }
        else{
            female_rbv.setSelected(true);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 8));
            admission_date_tbv.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        String module = model.getValueAt(i, 9).toString();
        degree_cbv.setSelectedIndex( degreeArray.indexOf(module));
        
        String intake = model.getValueAt(i, 10).toString();
        switch(intake){
            case "February":
                intake_cbv.setSelectedIndex(0);
                break;
            case "July":
                intake_cbv.setSelectedIndex(1);
                break;
        }
        subject1_tbv.setText(model.getValueAt(i, 11).toString());
        String s1r = model.getValueAt(i, 12).toString();
        switch(s1r){
            case "A":
                subject1_cbv.setSelectedIndex(0);
                break;
            case "B":
                subject1_cbv.setSelectedIndex(1);
                break;
            case "C":
                subject1_cbv.setSelectedIndex(2);
                break;
            case "S":
                subject1_cbv.setSelectedIndex(3);
                break;
            case "F":
               subject1_cbv.setSelectedIndex(4);
                break;
        }
        subject2_tbv.setText(model.getValueAt(i, 13).toString());
        String s2r = model.getValueAt(i, 14).toString();
        switch(s2r){
            case "A":
                subject2_cbv.setSelectedIndex(0);
                break;
            case "B":
                subject2_cbv.setSelectedIndex(1);
                break;
            case "C":
                subject2_cbv.setSelectedIndex(2);
                break;
            case "S":
                subject2_cbv.setSelectedIndex(3);
                break;
            case "F":
                subject2_cbv.setSelectedIndex(4);
                break;
        }
        subject3_tbv.setText(model.getValueAt(i, 15).toString());
        String s3r = model.getValueAt(i, 16).toString();
        switch(s3r){
            case "A":
                subject3_cbv.setSelectedIndex(0);
                break;
            case "B":
                subject3_cbv.setSelectedIndex(1);
                break;
            case "C":
                subject3_cbv.setSelectedIndex(2);
                break;
            case "S":
                subject3_cbv.setSelectedIndex(3);
                break;
            case "F":
                subject3_cbv.setSelectedIndex(4);
                break;
        }
        String ger = model.getValueAt(i, 17).toString();
        switch(ger){
            case "A":
                general_english_cbv.setSelectedIndex(0);
                break;
            case "B":
                general_english_cbv.setSelectedIndex(1);
                break;
            case "C":
                general_english_cbv.setSelectedIndex(2);
                break;
            case "S":
                general_english_cbv.setSelectedIndex(3);
                break;
            case "F":
                general_english_cbv.setSelectedIndex(4);
                break;
        }
        district_rank_tbv.setText(model.getValueAt(i, 18).toString());
        island_rank_tbv.setText(model.getValueAt(i, 19).toString());
    }//GEN-LAST:event_ugs_tableMouseClicked

    private void reset_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnrActionPerformed
        register_no_tbr.setText("");
        module_code_cbr.setSelectedIndex(0);
        grade_cbr.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) results_table.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_reset_btnrActionPerformed

    private void search_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnrActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM results WHERE register_no=? AND module_code=? ";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, register_no_tbr.getText());
            pst.setString(2, module_code_cbr.getSelectedItem().toString());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_grade = rs.getString("grade");
                grade_cbr.setSelectedItem(set_grade);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnrActionPerformed

    private void add_result_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_result_btnrActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `results`(`register_no`, `module_code`, `grade`) VALUES (?,?,?) ";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,register_no_tbr.getText());
            pst.setString(2,module_code_cbr.getSelectedItem().toString());
            pst.setString(3, (String) grade_cbr.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) results_table.getModel();
            model.setRowCount(0);
            show_result();
            JOptionPane.showMessageDialog(null,"Inserted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_add_result_btnrActionPerformed

    private void update_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnrActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE `results` SET `grade`=? WHERE register_no=? AND module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) grade_cbr.getSelectedItem());
            pst.setString(2,register_no_tbr.getText());
            pst.setString(3,module_code_cbr.getSelectedItem().toString());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) results_table.getModel();
            model.setRowCount(0);
            show_result();
            JOptionPane.showMessageDialog(null,"Updated Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btnrActionPerformed

    private void delete_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnrActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM results WHERE register_no=? AND module_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1,register_no_tbr.getText());
            pst.setString(2,module_code_cbr.getSelectedItem().toString());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) results_table.getModel();
            model.setRowCount(0);
            show_result();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_delete_btnrActionPerformed

    private void results_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_results_tableMouseClicked
        int i = results_table.getSelectedRow();
        TableModel model = results_table.getModel();
        
        String module = model.getValueAt(i, 0).toString();
        module_code_cbr.setSelectedItem(module);
        
        String grade = model.getValueAt(i, 1).toString();
        grade_cbr.setSelectedItem(grade);
    }//GEN-LAST:event_results_tableMouseClicked

    private void show_table_btnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_table_btnrActionPerformed
        show_result();
    }//GEN-LAST:event_show_table_btnrActionPerformed

    private void pay_btnrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_btnrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_btnrrActionPerformed

    private void show_subject_btnrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_subject_btnrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_show_subject_btnrrActionPerformed

    private void register_btnrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register_btnrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_register_btnrrActionPerformed

    private void delete_btnrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_btnrrActionPerformed

    private void semester_cbrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semester_cbrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semester_cbrrActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(undergraduate_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(undergraduate_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(undergraduate_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(undergraduate_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new undergraduate_soc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_result_btnr;
    private javax.swing.JTextField address_tbe;
    private javax.swing.JTextField address_tbv;
    private com.toedter.calendar.JDateChooser admission_date_tbe;
    private com.toedter.calendar.JDateChooser admission_date_tbv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField contact_no_tbe;
    private javax.swing.JTextField contact_no_tbv;
    private javax.swing.JComboBox<String> degree_cbe;
    private javax.swing.JComboBox<String> degree_cbv;
    private javax.swing.JButton delete_btnr;
    private javax.swing.JButton delete_btnrr;
    private javax.swing.JButton delete_btnv;
    private javax.swing.JTextField district_rank_tbe;
    private javax.swing.JTextField district_rank_tbv;
    private com.toedter.calendar.JDateChooser dob_tbe;
    private com.toedter.calendar.JDateChooser dob_tbv;
    private javax.swing.JTextField email_tbe;
    private javax.swing.JTextField email_tbv;
    private javax.swing.JButton enroll__btne;
    private javax.swing.JButton enroll_btn;
    private javax.swing.JPanel enroll_panel;
    private javax.swing.JRadioButton female_rbe;
    private javax.swing.JRadioButton female_rbv;
    private javax.swing.JTable follow_table;
    private javax.swing.JComboBox<String> general_english_cbe;
    private javax.swing.JComboBox<String> general_english_cbv;
    private javax.swing.JComboBox<String> grade_cbr;
    private javax.swing.JButton home_btn;
    private javax.swing.JComboBox<String> intake_cbe;
    private javax.swing.JComboBox<String> intake_cbv;
    private javax.swing.JTextField island_rank_tbe;
    private javax.swing.JTextField island_rank_tbv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel logo_label;
    private javax.swing.JRadioButton male_rbe;
    private javax.swing.JRadioButton male_rbv;
    private javax.swing.JComboBox<String> module_code_cbr;
    private javax.swing.JTextField name_tbe;
    private javax.swing.JTextField name_tbv;
    private javax.swing.JTextField nic_no_tbe;
    private javax.swing.JTextField nic_no_tbv;
    private javax.swing.JButton pay_btnrr;
    private javax.swing.JButton register_btn;
    private javax.swing.JButton register_btnrr;
    private javax.swing.JTextField register_no_tbe;
    private javax.swing.JTextField register_no_tbr;
    private javax.swing.JTextField register_no_tbrr;
    private javax.swing.JTextField register_no_tbv;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reset_btne;
    private javax.swing.JButton reset_btnr;
    private javax.swing.JButton reset_btnv;
    private javax.swing.JButton results_btn;
    private javax.swing.JPanel results_panel;
    private javax.swing.JTable results_table;
    private javax.swing.JButton search_btnr;
    private javax.swing.JButton search_btnv;
    private javax.swing.JComboBox<String> select_module_cbrr;
    private javax.swing.JComboBox<String> semester_cbrr;
    private javax.swing.JButton show_subject_btnrr;
    private javax.swing.JButton show_table_btnr;
    private javax.swing.JComboBox<String> subject1_cbe;
    private javax.swing.JComboBox<String> subject1_cbv;
    private javax.swing.JTextField subject1_tbe;
    private javax.swing.JTextField subject1_tbv;
    private javax.swing.JComboBox<String> subject2_cbe;
    private javax.swing.JComboBox<String> subject2_cbv;
    private javax.swing.JTextField subject2_tbe;
    private javax.swing.JTextField subject2_tbv;
    private javax.swing.JComboBox<String> subject3_cbe;
    private javax.swing.JComboBox<String> subject3_cbv;
    private javax.swing.JTextField subject3_tbe;
    private javax.swing.JTextField subject3_tbv;
    private javax.swing.JTextField total_credits_tbrr;
    private javax.swing.JTextField total_fees_tbrr;
    private javax.swing.JTable ugs_table;
    private javax.swing.JButton update_btnr;
    private javax.swing.JButton update_btnv;
    private javax.swing.JButton view_btn;
    private javax.swing.JPanel view_panel;
    private javax.swing.JComboBox<String> year_cbrr;
    // End of variables declaration//GEN-END:variables
}
