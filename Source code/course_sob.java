
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
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
public class course_sob extends javax.swing.JFrame {

         ArrayList<String> degreeArray = new ArrayList<>();
         ArrayList<String> moduleArray = new ArrayList<>();
         ArrayList<String> lecturerArray = new ArrayList<>();
         ArrayList<String> instructorArray = new ArrayList<>();
    /**
     * Creates new form course_sob
     */
    public course_sob() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        
       
        
        fill_degree();
        fill_lecturer();
        fill_module();
        fill_instructor();
        show_degree();
        show_module();
        show_lecturer();
        show_module1();
        show_module2();
        show_teach();
        show_instruct();
    }
    
    private void fill_degree(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM degree WHERE faculty='Business'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String degree;
                degree = rs.getString("degree_title");
                degree_cb.addItem(degree);
                degreeArray.add(degree);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void fill_lecturer(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM lecturer WHERE faculty='Business'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String lec;
                lec = rs.getString("lecturer_no");
                select_lecturer_cbl.addItem(lec);
                lecturerArray.add(lec);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void fill_instructor(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM instructor WHERE faculty='Business'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String instructor;
                instructor = rs.getString("instructor_no");
                select_instructor_cbi.addItem(instructor);
                instructorArray.add(instructor);
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
            query = "SELECT * FROM module WHERE faculty='Business'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String module;
                module = rs.getString("module_code");
                select_module_cbl.addItem(module);
                select_module_cbi.addItem(module);
                moduleArray.add(module);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public ArrayList<lecturer> lecturerList(){
        ArrayList<lecturer> lecturerList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `lecturer_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `starting_date`, `faculty` FROM `lecturer` WHERE faculty='Business' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            lecturer lec;
            while(rs.next()){
               lec = new lecturer(rs.getString("lecturer_no"),rs.getString("name"),rs.getString("address"),rs.getString("nic_no"),rs.getString("contact_no"),rs.getString("email"),rs.getString("starting_date"),rs.getString("faculty") );
               lecturerList.add(lec);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return lecturerList;
    }
    
    public void show_lecturer(){
        ArrayList<lecturer> list = lecturerList();
        DefaultTableModel model = (DefaultTableModel) lecturer_list.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getLecturer_no();
            row[1] = list.get(i).getName();
            model.addRow(row);
        }
    }
    
    public ArrayList<instructor> instructorList(){
        ArrayList<instructor> instructorList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `instructor_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `starting_date`, `faculty` FROM `instructor` WHERE faculty='Business' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            instructor instructor;
            while(rs.next()){
               instructor = new instructor(rs.getString("instructor_no"),rs.getString("name"),rs.getString("address"),rs.getString("nic_no"),rs.getString("contact_no"),rs.getString("email"),rs.getString("starting_date"),rs.getString("faculty") );
              instructorList.add(instructor);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return instructorList;
    }
    
    public void show_instructor(){
        ArrayList<instructor> list = instructorList();
        DefaultTableModel model = (DefaultTableModel) instructor_list.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getInstructor_no();
            row[1] = list.get(i).getName();
            model.addRow(row);
        }
    }
    
    public ArrayList<degree> degreeList(){
        ArrayList<degree> degreeList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `degree_code`, `degree_title` FROM `degree` WHERE faculty='Business' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            degree degree;
            while(rs.next()){
               degree = new degree(rs.getString("degree_code"), rs.getString("degree_title"));
               degreeList.add(degree);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return degreeList;
    }
    
    public void show_degree(){
        ArrayList<degree> list = degreeList();
        DefaultTableModel model = (DefaultTableModel) degree_table.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getDegree_code();
            row[1] = list.get(i).getDegree_title();
            model.addRow(row);
        }
    }
    
    
    
    public ArrayList<module> moduleList(){
        ArrayList<module> moduleList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `module_code`, `module_title`, `year`, `semester`, `credit_value`, `fee`, `type`, `degree` FROM `module` WHERE faculty='Business' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            module mod;
            while(rs.next()){
               mod = new module(rs.getString("module_code"), rs.getString("module_title"),rs.getInt("year"),rs.getInt("semester"), rs.getInt("credit_value"), rs.getInt("fee"),rs.getString("type"), rs.getString("degree"));
               moduleList.add(mod);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return moduleList;
    }
    
    public void show_module(){
        ArrayList<module> list = moduleList();
        DefaultTableModel model = (DefaultTableModel) module_table.getModel();
        Object[] row = new Object[8];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_code();
            row[1] = list.get(i).getModule_title();
            row[2] = list.get(i).getYear();
            row[3] = list.get(i).getSemester();
            row[4] = list.get(i).getCredit_value();
            row[5] = list.get(i).getFee();
            row[6] = list.get(i).getType();
            row[7] = list.get(i).getDegree();
            model.addRow(row);
        }
    }
    
    public void show_module1(){
        ArrayList<module> list = moduleList();
        DefaultTableModel model = (DefaultTableModel) module_list1.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_code();
            row[1] = list.get(i).getModule_title();
            model.addRow(row);
        }
    }
    
    public void show_module2(){
        ArrayList<module> list = moduleList();
        DefaultTableModel model = (DefaultTableModel) module_list2.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_code();
            row[1] = list.get(i).getModule_title();
            model.addRow(row);
        }
    }
    
    public ArrayList<teach> teachList(){
        ArrayList<teach> teachList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `lecturer_no`, `module_code`, `day`, `venue`, `start_time`, `end_time` FROM `teach`";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            teach teach;
            while(rs.next()){
               teach = new teach(rs.getString("lecturer_no"), rs.getString("module_code"), rs.getString("day"), rs.getString("venue"),rs.getString("start_time"),rs.getString("end_time"));
               teachList.add(teach);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return teachList;
    }
    
    public void show_teach(){
        ArrayList<teach> list = teachList();
        DefaultTableModel model = (DefaultTableModel) teach_table.getModel();
        Object[] row = new Object[6];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getLecturer_no();
            row[1] = list.get(i).getModule_code();
            row[2] = list.get(i).getDay();
            row[3] = list.get(i).getVenue();
            row[4] = list.get(i).getStart_time();
            row[5] = list.get(i).getEnd_time();
            model.addRow(row);
        }
    }
    
    public ArrayList<instruct> instructList(){
        ArrayList<instruct> instructList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `instructor_no`, `module_code`, `day`, `venue`, `start_time`, `end_time` FROM `instruct`";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            instruct instruct;
            while(rs.next()){
               instruct = new instruct(rs.getString("instructor_no"), rs.getString("module_code"), rs.getString("day"), rs.getString("venue"),rs.getString("start_time"),rs.getString("end_time"));
               instructList.add(instruct);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return instructList;
    }
    
    public void show_instruct(){
        ArrayList<instruct> list = instructList();
        DefaultTableModel model = (DefaultTableModel) instruct_table.getModel();
        Object[] row = new Object[6];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getInstructor_no();
            row[1] = list.get(i).getModule_code();
            row[2] = list.get(i).getDay();
            row[3] = list.get(i).getVenue();
            row[4] = list.get(i).getStart_time();
            row[5] = list.get(i).getEnd_time();
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

        jPanel1 = new javax.swing.JPanel();
        home_btn = new javax.swing.JButton();
        logo_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        add_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reset_btnd = new javax.swing.JButton();
        add_btnd = new javax.swing.JButton();
        update_btnd = new javax.swing.JButton();
        delete_btnd = new javax.swing.JButton();
        degree_number_tb = new javax.swing.JTextField();
        degree_title_tb = new javax.swing.JTextField();
        search_btnd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        module_no_tb = new javax.swing.JTextField();
        module_title_tb = new javax.swing.JTextField();
        year_cb = new javax.swing.JComboBox<>();
        semester_cb = new javax.swing.JComboBox<>();
        credit_value_cb = new javax.swing.JComboBox<>();
        type_cb = new javax.swing.JComboBox<>();
        fee_tb = new javax.swing.JTextField();
        degree_cb = new javax.swing.JComboBox<>();
        reset_btnm = new javax.swing.JButton();
        add_btnm = new javax.swing.JButton();
        update_btnm = new javax.swing.JButton();
        delete_btnm = new javax.swing.JButton();
        search_btnm = new javax.swing.JButton();
        assign_lecturer_btn = new javax.swing.JButton();
        assign_instructor_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        degree_table = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        module_table = new javax.swing.JTable();
        assign_lecturer_panel = new javax.swing.JPanel();
        back_btnl = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        select_lecturer_cbl = new javax.swing.JComboBox<>();
        venue_cbl = new javax.swing.JComboBox<>();
        day_cbl = new javax.swing.JComboBox<>();
        starting_time_jsl = new javax.swing.JSpinner();
        end_time_jsl = new javax.swing.JSpinner();
        assign_btnl = new javax.swing.JButton();
        select_module_cbl = new javax.swing.JComboBox<>();
        reset_btnl = new javax.swing.JButton();
        update_btnl = new javax.swing.JButton();
        delete_btnl = new javax.swing.JButton();
        search_btnl = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        teach_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        lecturer_list = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        module_list1 = new javax.swing.JTable();
        assign_instructor_panel = new javax.swing.JPanel();
        back_btni = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        select_module_cbi = new javax.swing.JComboBox<>();
        select_instructor_cbi = new javax.swing.JComboBox<>();
        venue_cbi = new javax.swing.JComboBox<>();
        day_cbi = new javax.swing.JComboBox<>();
        starting_time_jsi = new javax.swing.JSpinner();
        end_time_jsi = new javax.swing.JSpinner();
        assign_btni = new javax.swing.JButton();
        reset_btni = new javax.swing.JButton();
        update_btni = new javax.swing.JButton();
        delete_btni = new javax.swing.JButton();
        search_btni = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        instruct_table = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        module_list2 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        instructor_list = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Courses & Modules | School of Business");

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1179, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(logo_label)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(logo_label)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.CardLayout());

        jLabel1.setText("Degree Number");

        jLabel2.setText("Degree Title");

        reset_btnd.setText("Reset");
        reset_btnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btndActionPerformed(evt);
            }
        });

        add_btnd.setText("Add Degree");
        add_btnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btndActionPerformed(evt);
            }
        });

        update_btnd.setText("Update");
        update_btnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btndActionPerformed(evt);
            }
        });

        delete_btnd.setText("Delete");
        delete_btnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btndActionPerformed(evt);
            }
        });

        search_btnd.setText("Search");
        search_btnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btndActionPerformed(evt);
            }
        });

        jLabel3.setText("Module Number");

        jLabel4.setText("Module Title");

        jLabel5.setText("Year");

        jLabel6.setText("Semester");

        jLabel7.setText("Credit Value");

        jLabel8.setText("Fee");

        jLabel9.setText("Type");

        jLabel10.setText("Degree");

        year_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        semester_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        credit_value_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        type_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compulsory", "Optional" }));

        reset_btnm.setText("Reset");
        reset_btnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnmActionPerformed(evt);
            }
        });

        add_btnm.setText("Add Module");
        add_btnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnmActionPerformed(evt);
            }
        });

        update_btnm.setText("Update");
        update_btnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnmActionPerformed(evt);
            }
        });

        delete_btnm.setText("Delete");
        delete_btnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnmActionPerformed(evt);
            }
        });

        search_btnm.setText("Search");
        search_btnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnmActionPerformed(evt);
            }
        });

        assign_lecturer_btn.setText("Assign Leturers");
        assign_lecturer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_lecturer_btnActionPerformed(evt);
            }
        });

        assign_instructor_btn.setText("Assign Instructor");
        assign_instructor_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_instructor_btnActionPerformed(evt);
            }
        });

        degree_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Degree Code", "Degree Title"
            }
        ));
        degree_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                degree_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(degree_table);

        module_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Module Title", "Year", "Semester", "Credit Value", "Fee", "Type", "Degree"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        module_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                module_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(module_table);

        javax.swing.GroupLayout add_panelLayout = new javax.swing.GroupLayout(add_panel);
        add_panel.setLayout(add_panelLayout);
        add_panelLayout.setHorizontalGroup(
            add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_panelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reset_btnm)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(reset_btnd)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addComponent(add_btnm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update_btnm))
                    .addComponent(degree_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(type_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fee_tb)
                    .addComponent(credit_value_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(semester_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(year_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(module_title_tb)
                    .addComponent(module_no_tb)
                    .addComponent(degree_title_tb)
                    .addComponent(degree_number_tb)
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addComponent(add_btnd)
                        .addGap(44, 44, 44)
                        .addComponent(update_btnd)))
                .addGap(40, 40, 40)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(delete_btnd)
                        .addComponent(search_btnd))
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete_btnm)
                            .addComponent(search_btnm))
                        .addGap(2, 2, 2)))
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(assign_lecturer_btn)
                        .addGap(137, 137, 137)
                        .addComponent(assign_instructor_btn)
                        .addGap(246, 246, 246))
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        add_panelLayout.setVerticalGroup(
            add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_panelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(degree_number_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_btnd))
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(degree_title_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reset_btnd)
                            .addComponent(add_btnd)
                            .addComponent(update_btnd)
                            .addComponent(delete_btnd))
                        .addGap(43, 43, 43)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(module_no_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_btnm)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(module_title_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assign_lecturer_btn)
                    .addComponent(assign_instructor_btn))
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(year_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(semester_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(credit_value_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(fee_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(type_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(degree_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(add_panelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_btnm)
                    .addComponent(add_btnm)
                    .addComponent(update_btnm)
                    .addComponent(delete_btnm))
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jPanel2.add(add_panel, "card2");

        back_btnl.setText("<--- Back");
        back_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnlActionPerformed(evt);
            }
        });

        jLabel11.setText("Select Lecturer");

        jLabel12.setText("Select Module");

        jLabel13.setText("Venue");

        jLabel14.setText("Day");

        jLabel15.setText("Starting Time");

        jLabel16.setText("End Time");

        venue_cbl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4th Floor", "Mini Auditorium", "W001", "W002" }));
        venue_cbl.setToolTipText("");

        day_cbl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));

        assign_btnl.setText("Assign");
        assign_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_btnlActionPerformed(evt);
            }
        });

        reset_btnl.setText("Reset");
        reset_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnlActionPerformed(evt);
            }
        });

        update_btnl.setText("Update");
        update_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnlActionPerformed(evt);
            }
        });

        delete_btnl.setText("Delete");
        delete_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnlActionPerformed(evt);
            }
        });

        search_btnl.setText("Search");
        search_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnlActionPerformed(evt);
            }
        });

        teach_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer", "Module", "Day", "Venue", "Starting Time", "End Time"
            }
        ));
        teach_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teach_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(teach_table);

        lecturer_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer ID", "Name"
            }
        ));
        jScrollPane2.setViewportView(lecturer_list);

        module_list1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Title"
            }
        ));
        jScrollPane3.setViewportView(module_list1);

        javax.swing.GroupLayout assign_lecturer_panelLayout = new javax.swing.GroupLayout(assign_lecturer_panel);
        assign_lecturer_panel.setLayout(assign_lecturer_panelLayout);
        assign_lecturer_panelLayout.setHorizontalGroup(
            assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(search_btnl)
                    .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)))
                            .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                                        .addComponent(reset_btnl)
                                        .addGap(51, 51, 51)
                                        .addComponent(assign_btnl))
                                    .addComponent(back_btnl))))
                        .addGap(51, 51, 51)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                                .addComponent(update_btnl)
                                .addGap(65, 65, 65)
                                .addComponent(delete_btnl))
                            .addComponent(select_module_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_lecturer_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(venue_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(starting_time_jsl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(end_time_jsl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assign_lecturer_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        assign_lecturer_panelLayout.setVerticalGroup(
            assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                        .addComponent(back_btnl)
                        .addGap(59, 59, 59)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(select_lecturer_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(3, 3, 3)
                        .addComponent(search_btnl)
                        .addGap(10, 10, 10)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(select_module_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(venue_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(day_cbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(36, 36, 36)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(starting_time_jsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(36, 36, 36)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(end_time_jsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(assign_lecturer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(assign_btnl)
                            .addComponent(reset_btnl)
                            .addComponent(update_btnl)
                            .addComponent(delete_btnl)))
                    .addGroup(assign_lecturer_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        starting_time_jsl.setModel(new SpinnerDateModel());
        starting_time_jsl.setEditor(new JSpinner.DateEditor(starting_time_jsl, "HH:mm"));
        end_time_jsl.setModel(new SpinnerDateModel());
        end_time_jsl.setEditor(new JSpinner.DateEditor(end_time_jsl, "HH:mm"));

        jPanel2.add(assign_lecturer_panel, "card2");

        back_btni.setText("<--- Back");
        back_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btniActionPerformed(evt);
            }
        });

        jLabel17.setText("Select Instructor");

        jLabel18.setText("Select Module");

        jLabel19.setText("Venue");

        jLabel20.setText("Day");

        jLabel21.setText("Starting Time");

        jLabel22.setText("End Time");

        venue_cbi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAB A", "LAB B", "LAB C", "LAB D" }));
        venue_cbi.setToolTipText("");

        day_cbi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));

        assign_btni.setText("Assign");
        assign_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_btniActionPerformed(evt);
            }
        });

        reset_btni.setText("Reset");
        reset_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btniActionPerformed(evt);
            }
        });

        update_btni.setText("Update");
        update_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btniActionPerformed(evt);
            }
        });

        delete_btni.setText("Delete");
        delete_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btniActionPerformed(evt);
            }
        });

        search_btni.setText("Serach");
        search_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btniActionPerformed(evt);
            }
        });

        instruct_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Instructor", "Module", "Day", "Venue", "Starting Time", "End Time"
            }
        ));
        instruct_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instruct_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(instruct_table);

        module_list2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Title"
            }
        ));
        jScrollPane6.setViewportView(module_list2);

        instructor_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Instructor ID", "Name"
            }
        ));
        jScrollPane7.setViewportView(instructor_list);

        javax.swing.GroupLayout assign_instructor_panelLayout = new javax.swing.GroupLayout(assign_instructor_panel);
        assign_instructor_panel.setLayout(assign_instructor_panelLayout);
        assign_instructor_panelLayout.setHorizontalGroup(
            assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reset_btni)
                    .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22))
                            .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(assign_btni)))
                        .addGap(51, 51, 51)
                        .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(select_instructor_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_module_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(venue_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(starting_time_jsi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(end_time_jsi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                                .addComponent(update_btni)
                                .addGap(51, 51, 51)
                                .addComponent(delete_btni))
                            .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(search_btni))))
                    .addComponent(back_btni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        assign_instructor_panelLayout.setVerticalGroup(
            assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_instructor_panelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(back_btni)
                .addGap(59, 59, 59)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select_instructor_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(4, 4, 4)
                .addComponent(search_btni)
                .addGap(7, 7, 7)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select_module_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(35, 35, 35)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(venue_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(day_cbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(starting_time_jsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(32, 32, 32)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(end_time_jsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_btni)
                    .addComponent(assign_btni)
                    .addComponent(update_btni)
                    .addComponent(delete_btni))
                .addGap(81, 81, 81))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assign_instructor_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(assign_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        starting_time_jsi.setModel(new SpinnerDateModel());
        starting_time_jsi.setEditor(new JSpinner.DateEditor(starting_time_jsl, "HH:mm"));
        end_time_jsi.setModel(new SpinnerDateModel());
        end_time_jsi.setEditor(new JSpinner.DateEditor(end_time_jsl, "HH:mm"));

        jPanel2.add(assign_instructor_panel, "card2");

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void assign_lecturer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_lecturer_btnActionPerformed
         add_panel.setVisible(false);
        assign_lecturer_panel.setVisible(true);
        assign_instructor_panel.setVisible(false);
    }//GEN-LAST:event_assign_lecturer_btnActionPerformed

    private void assign_instructor_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_instructor_btnActionPerformed
        add_panel.setVisible(false);
        assign_lecturer_panel.setVisible(false);
        assign_instructor_panel.setVisible(true);
    }//GEN-LAST:event_assign_instructor_btnActionPerformed

    private void back_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnlActionPerformed
        add_panel.setVisible(true);
        assign_lecturer_panel.setVisible(false);
        assign_instructor_panel.setVisible(false);
    }//GEN-LAST:event_back_btnlActionPerformed

    private void back_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btniActionPerformed
        add_panel.setVisible(true);
        assign_lecturer_panel.setVisible(false);
        assign_instructor_panel.setVisible(false);
    }//GEN-LAST:event_back_btniActionPerformed

    private void assign_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_btnlActionPerformed
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `teach`(`lecturer_no`, `module_code`, `day`, `venue`, `start_time`, `end_time`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(query);
            String lecturer;
            lecturer = select_lecturer_cbl.getSelectedItem().toString();
            pst.setString(1,lecturer);
            String module;
            module = select_module_cbl.getSelectedItem().toString();
            pst.setString(2,module);
            String day;
            day = day_cbl.getSelectedItem().toString();
            pst.setString(3,day);
            String venue;
            venue = venue_cbl.getSelectedItem().toString();
            pst.setString(4,venue);
           SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
             sdf.setTimeZone(TimeZone.getDefault());
             String st=sdf.format(starting_time_jsl.getValue());
            pst.setString(5, st);
            String et=sdf.format(end_time_jsl.getValue());
            pst.setString(6, et);
            pst.executeUpdate();
             DefaultTableModel model = (DefaultTableModel) teach_table.getModel();
            model.setRowCount(0);
            show_teach();
            JOptionPane.showMessageDialog(null,"Added Sucsessfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_assign_btnlActionPerformed

    private void assign_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_btniActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `instructor`(`instructor_no`, `modue_code`, `day`, `venue`, `start_time`, `end_time`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(query);
            String lecturer;
            lecturer = select_instructor_cbi.getSelectedItem().toString();
            pst.setString(1,lecturer);
            String module;
            module = select_module_cbi.getSelectedItem().toString();
            pst.setString(2,module);
             String day;
            day = select_instructor_cbi.getSelectedItem().toString();
            pst.setString(3,day);
            String venue;
            venue = venue_cbi.getSelectedItem().toString();
            pst.setString(4,venue);
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
             sdf.setTimeZone(TimeZone.getDefault());
             String st=sdf.format(starting_time_jsi.getValue());
            pst.setString(5, st);
            String et=sdf.format(end_time_jsi.getValue());
            pst.setString(6, et);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instruct_table.getModel();
            model.setRowCount(0);
            show_instruct();
            JOptionPane.showMessageDialog(null,"Added Sucsessfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_assign_btniActionPerformed

    private void reset_btndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btndActionPerformed
        degree_number_tb.setText("");
        degree_title_tb.setText("");
    }//GEN-LAST:event_reset_btndActionPerformed

    private void add_btndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btndActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `degree`(`degree_code`, `degree_title`, `faculty`) VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,degree_number_tb.getText());
            pst.setString(2,degree_title_tb.getText());
            pst.setString(3,"Business");
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) degree_table.getModel();
            model.setRowCount(0);
            show_degree();
            JOptionPane.showMessageDialog(null,"Inserted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        degree_cb.removeAllItems();
        fill_degree();
    }//GEN-LAST:event_add_btndActionPerformed

    private void search_btndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btndActionPerformed
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM degree WHERE degree_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, degree_number_tb.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_degree_no = rs.getString("degree_code");
                degree_number_tb.setText(set_degree_no);
                
                String set_degree_title = rs.getString("degree_title");
                degree_title_tb.setText(set_degree_title);
               
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btndActionPerformed

    private void update_btndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btndActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE degree SET   degree_title=?  WHERE degree_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1, degree_title_tb.getText());
            pst.setString(2,degree_number_tb.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) degree_table.getModel();
            model.setRowCount(0);
            show_degree();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        degree_cb.removeAllItems();
        fill_degree();
    }//GEN-LAST:event_update_btndActionPerformed

    private void delete_btndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btndActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM degree WHERE degree_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1,degree_number_tb.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) degree_table.getModel();
            model.setRowCount(0);
            show_degree();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
        degree_cb.removeAllItems();
        fill_degree();
    }//GEN-LAST:event_delete_btndActionPerformed

    private void reset_btnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnmActionPerformed
        module_no_tb.setText("");
        module_title_tb.setText("");
        year_cb.setSelectedIndex(0);
        semester_cb.setSelectedIndex(0);
        credit_value_cb.setSelectedIndex(0);
        fee_tb.setText("");
        type_cb.setSelectedIndex(0);
        degree_cb.setSelectedIndex(0);
    }//GEN-LAST:event_reset_btnmActionPerformed

    private void add_btnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnmActionPerformed
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `module`(`module_code`, `module_title`, `year`, `semester`, `credit_value`, `fee`, `type`, `faculty`, `degree`) VALUES (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,module_no_tb.getText());
            pst.setString(2,module_title_tb.getText());
            pst.setInt(3, Integer.parseInt((String) year_cb.getSelectedItem()));
            pst.setInt(4, Integer.parseInt((String) semester_cb.getSelectedItem()));
            pst.setInt(5, Integer.parseInt((String) credit_value_cb.getSelectedItem()));
            pst.setInt(6,Integer.parseInt(fee_tb.getText()));
            pst.setString(7, (String) type_cb.getSelectedItem());
            pst.setString(8, "Business");
            pst.setString(9, (String) degree_cb.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) module_table.getModel();
            model.setRowCount(0);
            show_module();
            JOptionPane.showMessageDialog(null,"Inserted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         select_module_cbl.removeAllItems();
         select_module_cbi.removeAllItems();
        fill_module();
    }//GEN-LAST:event_add_btnmActionPerformed

    private void search_btnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnmActionPerformed
            try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM module WHERE module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, module_no_tb.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_module_title = rs.getString("module_title");
                module_title_tb.setText(set_module_title);
                
                String set_year = rs.getString("year");
                year_cb.setSelectedItem(set_year);
                
                String set_semester = rs.getString("semester");
                semester_cb.setSelectedItem(set_semester);
                
                String set_creditv = rs.getString("credit_value");
                credit_value_cb.setSelectedItem(set_creditv);
                
                String set_fee = rs.getString("fee");
                fee_tb.setText(set_fee);
                
                String set_type = rs.getString("type");
                type_cb.setSelectedItem(set_type);
                
                String set_degree = rs.getString("degree");
                degree_cb.setSelectedItem(set_degree);
               
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnmActionPerformed

    private void update_btnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnmActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE `module` SET `module_title`=?,`year`=?,`semester`=?,`credit_value`=?,`fee`=?,`type`=?,`degree`=? WHERE `module_code`=?";
             PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,module_title_tb.getText());
            pst.setInt(2, Integer.parseInt((String) year_cb.getSelectedItem()));
            pst.setInt(3, Integer.parseInt((String) semester_cb.getSelectedItem()));
            pst.setInt(4, Integer.parseInt((String) credit_value_cb.getSelectedItem()));
            pst.setInt(5,Integer.parseInt(fee_tb.getText()));
            pst.setString(6, (String) type_cb.getSelectedItem());
            pst.setString(7, (String) degree_cb.getSelectedItem());
            pst.setString(8, module_no_tb.getText());
            pst.executeUpdate();
             DefaultTableModel model = (DefaultTableModel) module_table.getModel();
            model.setRowCount(0);
            show_module();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        select_module_cbl.removeAllItems();
         select_module_cbi.removeAllItems();
        fill_module();
    }//GEN-LAST:event_update_btnmActionPerformed

    private void delete_btnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnmActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM module WHERE module_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1,module_no_tb.getText());
            pst.executeUpdate();
             DefaultTableModel model = (DefaultTableModel) module_table.getModel();
            model.setRowCount(0);
            show_module();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
        select_module_cbl.removeAllItems();
         select_module_cbi.removeAllItems();
        fill_module();
    }//GEN-LAST:event_delete_btnmActionPerformed

    private void reset_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnlActionPerformed
        select_lecturer_cbl.setSelectedIndex(0);
        select_module_cbl.setSelectedIndex(0);
        day_cbl.setSelectedIndex(0);
        venue_cbl.setSelectedIndex(0);
        java.util.Date date = new java.util.Date();
        Object stime = new java.sql.Time(date.getTime());
        starting_time_jsl.setValue(stime);
        Object etime = new java.sql.Time(date.getTime());
        end_time_jsl.setValue(etime);
    }//GEN-LAST:event_reset_btnlActionPerformed

    private void search_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnlActionPerformed
        try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM teach WHERE lecturer_no=? AND module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) select_lecturer_cbl.getSelectedItem());
            pst.setString(2, (String) select_module_cbl.getSelectedItem());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_day = rs.getString("day");
                day_cbl.setSelectedItem(set_day);
                
                String set_venue = rs.getString("venue");
                venue_cbl.setSelectedItem(set_venue);
                
                starting_time_jsl.setValue(rs.getTime("start_time"));
                
                end_time_jsl.setValue(rs.getTime("end_time"));
               
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnlActionPerformed

    private void update_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnlActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE `teach` SET `day`=?,`venue`=?,`start_time`=?,`end_time`=? WHERE lecturer_no=? AND module_code=? ";
             PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) day_cbl.getSelectedItem());
             pst.setString(2, (String) venue_cbl.getSelectedItem());
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
             sdf.setTimeZone(TimeZone.getDefault());
             String st=sdf.format(starting_time_jsl.getValue());
            pst.setString(3, st);
            String et=sdf.format(end_time_jsl.getValue());
            pst.setString(4, et);
            pst.setString(5, (String) select_lecturer_cbl.getSelectedItem());
             pst.setString(6, (String) select_module_cbl.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) teach_table.getModel();
            model.setRowCount(0);
            show_teach();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btnlActionPerformed

    private void delete_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnlActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM teach WHERE lecturer_no=? AND module_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1, (String) select_lecturer_cbl.getSelectedItem());
             pst.setString(2, (String) select_module_cbl.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) teach_table.getModel();
            model.setRowCount(0);
            show_teach();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_delete_btnlActionPerformed

    private void reset_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btniActionPerformed
        select_instructor_cbi.setSelectedIndex(0);
        select_module_cbi.setSelectedIndex(0);
        day_cbi.setSelectedIndex(0);
        venue_cbi.setSelectedIndex(0);
        java.util.Date date = new java.util.Date();
        Object stime = new java.sql.Time(date.getTime());
        starting_time_jsi.setValue(stime);
        Object etime = new java.sql.Time(date.getTime());
        end_time_jsi.setValue(etime);
    }//GEN-LAST:event_reset_btniActionPerformed

    private void search_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btniActionPerformed
         try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM instruct WHERE instructor_no=? AND module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) select_instructor_cbi.getSelectedItem());
            pst.setString(2, (String) select_module_cbi.getSelectedItem());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_day = rs.getString("day");
                day_cbi.setSelectedItem(set_day);
                
                String set_venue = rs.getString("venue");
                venue_cbi.setSelectedItem(set_venue);
                
                starting_time_jsi.setValue(rs.getTime("start_time"));
                
                end_time_jsi.setValue(rs.getTime("end_time"));
               
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btniActionPerformed

    private void update_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btniActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE `teach` SET `day`=?,`venue`=?,`starting_time`=?,`end_time`=? WHERE instructor_no=? AND module_code=?";
             PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) day_cbi.getSelectedItem());
             pst.setString(2, (String) venue_cbi.getSelectedItem());
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
             sdf.setTimeZone(TimeZone.getDefault());
             String st=sdf.format(starting_time_jsi.getValue());
            pst.setString(3, st);
            String et=sdf.format(end_time_jsi.getValue());
            pst.setString(4, et);
            pst.setString(5, (String) select_instructor_cbi.getSelectedItem());
             pst.setString(6, (String) select_module_cbi.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instruct_table.getModel();
            model.setRowCount(0);
            show_instruct();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btniActionPerformed

    private void delete_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btniActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "DELETE FROM teach WHERE instructor_no=? AND module_code=?";
             PreparedStatement pst = c.prepareStatement(query);
             pst.setString(1, (String) select_instructor_cbi.getSelectedItem());
             pst.setString(2, (String) select_module_cbi.getSelectedItem());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instruct_table.getModel();
            model.setRowCount(0);
            show_instruct();
            JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_delete_btniActionPerformed

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        home_sob page = new home_sob();
        page.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_home_btnActionPerformed

    private void degree_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_degree_tableMouseClicked
        int i = degree_table.getSelectedRow();
        TableModel model = degree_table.getModel();
        degree_number_tb.setText(model.getValueAt(i, 0).toString());
        degree_title_tb.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_degree_tableMouseClicked

    private void module_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_module_tableMouseClicked
        int i = module_table.getSelectedRow();
        TableModel model = module_table.getModel();
        module_no_tb.setText(model.getValueAt(i, 0).toString());
        module_title_tb.setText(model.getValueAt(i, 1).toString());
        int year = Integer.parseInt(model.getValueAt(i, 2).toString());
        switch(year){
            case 1:
                year_cb.setSelectedIndex(0);
                break;
            case 2:
                year_cb.setSelectedIndex(1);
                break;
            case 3:
                year_cb.setSelectedIndex(2);
                break;
            case 4:
                year_cb.setSelectedIndex(3);
                break;
        }
        
        int semester = Integer.parseInt(model.getValueAt(i, 3).toString());
        switch(semester){
            case 1:
                semester_cb.setSelectedIndex(0);
                break;
            case 2:
                semester_cb.setSelectedIndex(1);
                break;
        }
        
        int credit_value = Integer.parseInt(model.getValueAt(i, 4).toString());
        switch(credit_value){
            case 1:
                credit_value_cb.setSelectedIndex(0);
                break;
            case 2:
                credit_value_cb.setSelectedIndex(1);
                break;
            case 3:
                credit_value_cb.setSelectedIndex(2);
                break;
        }
        
        fee_tb.setText(model.getValueAt(i, 5).toString());
        
        String type = model.getValueAt(i, 6).toString();
        switch(type){
            case "Compulsory":
                type_cb.setSelectedIndex(0);
                break;
            case "Optional":
                type_cb.setSelectedIndex(1);
                break;
        }
        
        String degree = model.getValueAt(i, 7).toString();
        degree_cb.setSelectedIndex( degreeArray.indexOf(degree));
    }//GEN-LAST:event_module_tableMouseClicked

    private void teach_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teach_tableMouseClicked
        int i = teach_table.getSelectedRow();
        TableModel model = teach_table.getModel();
        
        String lecturer = model.getValueAt(i, 0).toString();
        select_lecturer_cbl.setSelectedIndex( lecturerArray.indexOf(lecturer));
        
        String module = model.getValueAt(i, 1).toString();
        select_module_cbl.setSelectedIndex( moduleArray.indexOf(module));
        
        String day = model.getValueAt(i, 2).toString();
        switch(day){
            case "Monday":
                day_cbl.setSelectedIndex(0);
                break;
            case "Tuesday":
                day_cbl.setSelectedIndex(1);
                break;
           case "Wednesday":
                day_cbl.setSelectedIndex(2);
                break;
           case "Thursday":
                day_cbl.setSelectedIndex(3);
                break;
           case "Friday":
                day_cbl.setSelectedIndex(4);
                break;
        }
        
        String venue = model.getValueAt(i, 3).toString();
        switch(venue){
            case "4th Floor":
                venue_cbl.setSelectedIndex(0);
                break;
            case "Mini Auditorium":
                venue_cbl.setSelectedIndex(1);
                break;
           case "W001":
                venue_cbl.setSelectedIndex(2);
                break;
           case "W002":
                venue_cbl.setSelectedIndex(3);
                break;
        }
        
         try{
             Date st=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 4));
            starting_time_jsl.setValue(st);
            Date et=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 5));
            end_time_jsl.setValue(et);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_teach_tableMouseClicked

    private void instruct_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instruct_tableMouseClicked
        int i = instruct_table.getSelectedRow();
        TableModel model = instruct_table.getModel();
        
        String lecturer = model.getValueAt(i, 0).toString();
        select_instructor_cbi.setSelectedIndex(instructorArray.indexOf(lecturer));
        
        String module = model.getValueAt(i, 1).toString();
        select_module_cbi.setSelectedIndex( moduleArray.indexOf(module));
        
        String day = model.getValueAt(i, 2).toString();
        switch(day){
            case "Monday":
                day_cbi.setSelectedIndex(0);
                break;
            case "Tuesday":
                day_cbi.setSelectedIndex(1);
                break;
           case "Wednesday":
                day_cbi.setSelectedIndex(2);
                break;
           case "Thursday":
                day_cbi.setSelectedIndex(3);
                break;
           case "Friday":
                day_cbi.setSelectedIndex(4);
                break;
        }
        
        String venue = model.getValueAt(i, 3).toString();
        switch(venue){
            case "LAB A":
                venue_cbi.setSelectedIndex(0);
                break;
            case "LAB B":
                venue_cbi.setSelectedIndex(1);
                break;
           case "LAB C":
                venue_cbi.setSelectedIndex(2);
                break;
           case "LAB D":
                venue_cbi.setSelectedIndex(3);
                break;
        }
        try{
             Date st=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 4));
            starting_time_jsi.setValue(st);
            Date et=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 5));
            end_time_jsi.setValue(et);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_instruct_tableMouseClicked

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
            java.util.logging.Logger.getLogger(course_sob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(course_sob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(course_sob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(course_sob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new course_sob().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btnd;
    private javax.swing.JButton add_btnm;
    private javax.swing.JPanel add_panel;
    private javax.swing.JButton assign_btni;
    private javax.swing.JButton assign_btnl;
    private javax.swing.JButton assign_instructor_btn;
    private javax.swing.JPanel assign_instructor_panel;
    private javax.swing.JButton assign_lecturer_btn;
    private javax.swing.JPanel assign_lecturer_panel;
    private javax.swing.JButton back_btni;
    private javax.swing.JButton back_btnl;
    private javax.swing.JComboBox<String> credit_value_cb;
    private javax.swing.JComboBox<String> day_cbi;
    private javax.swing.JComboBox<String> day_cbl;
    private javax.swing.JComboBox<String> degree_cb;
    private javax.swing.JTextField degree_number_tb;
    private javax.swing.JTable degree_table;
    private javax.swing.JTextField degree_title_tb;
    private javax.swing.JButton delete_btnd;
    private javax.swing.JButton delete_btni;
    private javax.swing.JButton delete_btnl;
    private javax.swing.JButton delete_btnm;
    private javax.swing.JSpinner end_time_jsi;
    private javax.swing.JSpinner end_time_jsl;
    private javax.swing.JTextField fee_tb;
    private javax.swing.JButton home_btn;
    private javax.swing.JTable instruct_table;
    private javax.swing.JTable instructor_list;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable lecturer_list;
    private javax.swing.JLabel logo_label;
    private javax.swing.JTable module_list1;
    private javax.swing.JTable module_list2;
    private javax.swing.JTextField module_no_tb;
    private javax.swing.JTable module_table;
    private javax.swing.JTextField module_title_tb;
    private javax.swing.JButton reset_btnd;
    private javax.swing.JButton reset_btni;
    private javax.swing.JButton reset_btnl;
    private javax.swing.JButton reset_btnm;
    private javax.swing.JButton search_btnd;
    private javax.swing.JButton search_btni;
    private javax.swing.JButton search_btnl;
    private javax.swing.JButton search_btnm;
    private javax.swing.JComboBox<String> select_instructor_cbi;
    private javax.swing.JComboBox<String> select_lecturer_cbl;
    private javax.swing.JComboBox<String> select_module_cbi;
    private javax.swing.JComboBox<String> select_module_cbl;
    private javax.swing.JComboBox<String> semester_cb;
    private javax.swing.JSpinner starting_time_jsi;
    private javax.swing.JSpinner starting_time_jsl;
    private javax.swing.JTable teach_table;
    private javax.swing.JComboBox<String> type_cb;
    private javax.swing.JButton update_btnd;
    private javax.swing.JButton update_btni;
    private javax.swing.JButton update_btnl;
    private javax.swing.JButton update_btnm;
    private javax.swing.JComboBox<String> venue_cbi;
    private javax.swing.JComboBox<String> venue_cbl;
    private javax.swing.JComboBox<String> year_cb;
    // End of variables declaration//GEN-END:variables
}
