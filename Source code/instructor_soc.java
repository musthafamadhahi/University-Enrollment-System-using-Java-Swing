
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class instructor_soc extends javax.swing.JFrame {
        ArrayList<String> moduleArray = new ArrayList<>();
         ArrayList<String> instructorArray = new ArrayList<>();

    /**
     * Creates new form instructor_sob
     */
    public instructor_soc() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        
        fill_instructor();
        fill_module();
        show_instructor();
        show_instructor1();
        show_module();
        show_instruct();
    }
    
    private void fill_instructor(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query;
            query = "SELECT * FROM instructor WHERE faculty='Computing'  ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String lec;
                lec = rs.getString("instructor_no");
                select_instructor_cb.addItem(lec);
                instructorArray.add(lec);
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
                select_module_cb.addItem(module);
                moduleArray.add(module);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public ArrayList<instructor> instructorList(){
        ArrayList<instructor> instructorList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT `instructor_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `starting_date`, `faculty` FROM `instructor` WHERE faculty='Computing' ";
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
        Object[] row = new Object[7];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getInstructor_no();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getNic_no();
            row[4] = list.get(i).getContact_no();
            row[5] = list.get(i).getEmail();
            row[6] = list.get(i).getStarting_date();
            model.addRow(row);
        }
    }
    
    public void show_instructor1(){
        ArrayList<instructor> list = instructorList();
        DefaultTableModel model = (DefaultTableModel) instructor_list.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getInstructor_no();
            row[1] = list.get(i).getName();
            model.addRow(row);
        }
    }
    
    public ArrayList<module> moduleList(){
        ArrayList<module> moduleList = new ArrayList<>();
          try{
              Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT module_code, module_title FROM module WHERE faculty='Computing' ";
            PreparedStatement pst = c.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            module mod;
            while(rs.next()){
               mod = new module(rs.getString("module_code"), rs.getString("module_title"));
               moduleList.add(mod);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return moduleList;
    }
    
    public void show_module(){
        ArrayList<module> list = moduleList();
        DefaultTableModel model = (DefaultTableModel) module_list.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getModule_code();
            row[1] = list.get(i).getModule_title();
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        logo_label = new javax.swing.JLabel();
        home_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        add_instructor_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        instructor_no_tb = new javax.swing.JTextField();
        name_tb = new javax.swing.JTextField();
        address_tb = new javax.swing.JTextField();
        nic_no_tb = new javax.swing.JTextField();
        contact_no_tb = new javax.swing.JTextField();
        email_tb = new javax.swing.JTextField();
        add_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        search_btn = new javax.swing.JButton();
        starting_date_tb = new com.toedter.calendar.JDateChooser();
        update_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        add_module_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        instructor_table = new javax.swing.JTable();
        assign_module_panel = new javax.swing.JPanel();
        back_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        select_instructor_cb = new javax.swing.JComboBox<>();
        select_module_cb = new javax.swing.JComboBox<>();
        venue_cb = new javax.swing.JComboBox<>();
        day_cb = new javax.swing.JComboBox<>();
        starting_time_js = new javax.swing.JSpinner();
        end_time_js = new javax.swing.JSpinner();
        assign_btni = new javax.swing.JButton();
        reset_btni = new javax.swing.JButton();
        update_btni = new javax.swing.JButton();
        search_btnl = new javax.swing.JButton();
        delete_btni = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        instructor_list = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        module_list = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        instruct_table = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Instructor | School of Computing");

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));

        logo_label.setBackground(new java.awt.Color(102, 204, 0));
        logo_label.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        logo_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_label.setText("NSBM Green University");
        logo_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logo_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Instructor Number");

        jLabel3.setText("Name");

        jLabel4.setText("Address");

        jLabel1.setText("NIC Number");

        jLabel5.setText("Contact Number");

        jLabel6.setText("Email");

        jLabel7.setText("Starting Date");

        add_btn.setText("Add Instructor");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        reset_btn.setText("Reset");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        search_btn.setText("Search");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        update_btn.setText("Update");
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        add_module_btn.setText("Assign modules to Instructors --->");
        add_module_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_module_btnActionPerformed(evt);
            }
        });

        instructor_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "NIC", "Contact", "Email", "Starting Date"
            }
        ));
        instructor_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instructor_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(instructor_table);

        javax.swing.GroupLayout add_instructor_panelLayout = new javax.swing.GroupLayout(add_instructor_panel);
        add_instructor_panel.setLayout(add_instructor_panelLayout);
        add_instructor_panelLayout.setHorizontalGroup(
            add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_instructor_panelLayout.createSequentialGroup()
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_instructor_panelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(84, 84, 84)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nic_no_tb)
                            .addComponent(contact_no_tb)
                            .addComponent(email_tb)
                            .addComponent(starting_date_tb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(instructor_no_tb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_tb)
                            .addComponent(address_tb)))
                    .addGroup(add_instructor_panelLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reset_btn)
                            .addComponent(update_btn))
                        .addGap(91, 91, 91)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delete_btn)
                            .addComponent(add_btn))))
                .addGap(47, 47, 47)
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_instructor_panelLayout.createSequentialGroup()
                        .addComponent(search_btn)
                        .addGap(209, 209, 209)
                        .addComponent(add_module_btn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
        add_instructor_panelLayout.setVerticalGroup(
            add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_instructor_panelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(instructor_no_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_btn)
                    .addComponent(add_module_btn))
                .addGap(23, 23, 23)
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(name_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(address_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_instructor_panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nic_no_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(contact_no_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(email_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(starting_date_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add_btn)
                            .addComponent(reset_btn)))
                    .addGroup(add_instructor_panelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(add_instructor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_btn)
                    .addComponent(delete_btn))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jPanel2.add(add_instructor_panel, "card3");

        back_btn.setText("<--- Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        jLabel8.setText("Select Instructor");

        jLabel9.setText("Select Module");

        jLabel10.setText("Venue");

        jLabel11.setText("Day");

        jLabel12.setText("Starting Time");

        jLabel13.setText("End Time");

        venue_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAB A", "LAB B", "LAB C", "LAB D" }));
        venue_cb.setToolTipText("");

        day_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));

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

        search_btnl.setText("Search");
        search_btnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnlActionPerformed(evt);
            }
        });

        delete_btni.setText("Delete");
        delete_btni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btniActionPerformed(evt);
            }
        });

        instructor_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Instructor ID", "Name"
            }
        ));
        jScrollPane2.setViewportView(instructor_list);

        module_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Title"
            }
        ));
        jScrollPane3.setViewportView(module_list);

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
        jScrollPane4.setViewportView(instruct_table);

        javax.swing.GroupLayout assign_module_panelLayout = new javax.swing.GroupLayout(assign_module_panel);
        assign_module_panel.setLayout(assign_module_panelLayout);
        assign_module_panelLayout.setHorizontalGroup(
            assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_module_panelLayout.createSequentialGroup()
                .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(assign_module_panelLayout.createSequentialGroup()
                        .addContainerGap(49, Short.MAX_VALUE)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(assign_module_panelLayout.createSequentialGroup()
                                .addComponent(reset_btni)
                                .addGap(50, 50, 50)
                                .addComponent(assign_btni)
                                .addGap(48, 48, 48)
                                .addComponent(update_btni)
                                .addGap(8, 8, 8))
                            .addGroup(assign_module_panelLayout.createSequentialGroup()
                                .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)))
                                .addGap(97, 97, 97)
                                .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(select_instructor_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(select_module_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(venue_cb, 0, 100, Short.MAX_VALUE)
                                    .addComponent(day_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(starting_time_js)
                                    .addComponent(end_time_js))))
                        .addGap(41, 41, 41)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delete_btni)
                            .addComponent(search_btnl)))
                    .addGroup(assign_module_panelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(back_btn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(53, 53, 53)
                .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(assign_module_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addGap(80, 80, 80))
        );
        assign_module_panelLayout.setVerticalGroup(
            assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assign_module_panelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(assign_module_panelLayout.createSequentialGroup()
                        .addComponent(back_btn)
                        .addGap(82, 82, 82)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(select_instructor_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(search_btnl)
                        .addGap(8, 8, 8)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(select_module_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(venue_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(day_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(starting_time_js, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(end_time_js, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(assign_btni)
                            .addComponent(reset_btni)
                            .addComponent(update_btni)
                            .addComponent(delete_btni)))
                    .addGroup(assign_module_panelLayout.createSequentialGroup()
                        .addGroup(assign_module_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        starting_time_js.setModel(new SpinnerDateModel());
        starting_time_js.setEditor(new JSpinner.DateEditor(starting_time_js, "HH:mm"));
        end_time_js.setModel(new SpinnerDateModel());
        end_time_js.setEditor(new JSpinner.DateEditor(end_time_js, "HH:mm"));

        jPanel2.add(assign_module_panel, "card2");

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        home_soc page = new home_soc();
        page.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_home_btnActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `instructor`(`instructor_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `starting_date`, `faculty`) VALUES (?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1,instructor_no_tb.getText());
            pst.setString(2,name_tb.getText());
            pst.setString(3,address_tb.getText());
            pst.setString(4,nic_no_tb.getText());
            pst.setString(5,contact_no_tb.getText());
            pst.setString(6,email_tb.getText());
            SimpleDateFormat starting_date = new SimpleDateFormat("yyyy-MM-dd");
            String sd = starting_date.format(starting_date_tb.getDate());
            pst.setString(7,sd);
            pst.setString(8, "Computing");
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instructor_table.getModel();
            model.setRowCount(0);
            show_instructor();
            JOptionPane.showMessageDialog(null,"Inserted Sucsessfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

        select_instructor_cb.removeAllItems();
        fill_instructor();
    }//GEN-LAST:event_add_btnActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
        // TODO add your handling code here:
        instructor_no_tb.setText("");
        name_tb.setText("");
        address_tb.setText("");
        nic_no_tb.setText("");
        contact_no_tb.setText("");
        email_tb.setText("");
        starting_date_tb.setCalendar(null);
    }//GEN-LAST:event_reset_btnActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        // TODO add your handling code here:
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM instructor WHERE instructor_no=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, instructor_no_tb.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_name = rs.getString("name");
                name_tb.setText(set_name);

                String set_address = rs.getString("address");
                address_tb.setText(set_address);

                String set_nic_no = rs.getString("nic_no");
                nic_no_tb.setText(set_nic_no);

                String set_contact_no = rs.getString("contact_no");
                contact_no_tb.setText(set_contact_no);

                String set_email = rs.getString("email");
                email_tb.setText(set_email);

                starting_date_tb.setDate(rs.getDate("starting_date"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE instructor SET  instructor_no=?, name=?, address =?, nic_no=?, contact_no=?, email=?, starting_date=? WHERE instructor_no=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, instructor_no_tb.getText());
            pst.setString(2,name_tb.getText());
            pst.setString(3,address_tb.getText());
            pst.setString(4,nic_no_tb.getText());
            pst.setString(5,contact_no_tb.getText());
            pst.setString(6,email_tb.getText());
            SimpleDateFormat starting_date = new SimpleDateFormat("yyyy-MM-dd");
            String sd = starting_date.format(starting_date_tb.getDate());
            pst.setString(7,sd);
            pst.setString(8,instructor_no_tb.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instructor_table.getModel();
            model.setRowCount(0);
            show_instructor();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
                String query = "DELETE FROM instructor WHERE instructor_no=?";
                PreparedStatement pst = c.prepareStatement(query);
                pst.setString(1,instructor_no_tb.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel) instructor_table.getModel();
                model.setRowCount(0);
                show_instructor();
                JOptionPane.showMessageDialog(null,"Deleted Sucsessfully!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }

        select_instructor_cb.removeAllItems();
        fill_instructor();
    }//GEN-LAST:event_delete_btnActionPerformed

    private void add_module_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_module_btnActionPerformed
        add_instructor_panel.setVisible(false);
        assign_module_panel.setVisible(true);

    }//GEN-LAST:event_add_module_btnActionPerformed

    private void instructor_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instructor_tableMouseClicked
        int i = instructor_table.getSelectedRow();
        TableModel model = instructor_table.getModel();
        instructor_no_tb.setText(model.getValueAt(i, 0).toString());
        name_tb.setText(model.getValueAt(i, 1).toString());
        address_tb.setText(model.getValueAt(i, 2).toString());
        nic_no_tb.setText(model.getValueAt(i, 3).toString());
        contact_no_tb.setText(model.getValueAt(i, 4).toString());
        email_tb.setText(model.getValueAt(i, 5).toString());
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 6));
            starting_date_tb.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_instructor_tableMouseClicked

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        add_instructor_panel.setVisible(true);
        assign_module_panel.setVisible(false);
    }//GEN-LAST:event_back_btnActionPerformed

    private void assign_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_btniActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "INSERT INTO `instruct`(`instructor_no`, `module_code`, `day`, `venue`, `start_time`, `end_time`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(query);
            String lecturer;
            lecturer = select_instructor_cb.getSelectedItem().toString();
            pst.setString(1,lecturer);
            String module;
            module = select_module_cb.getSelectedItem().toString();
            pst.setString(2,module);
            String day;
            day = day_cb.getSelectedItem().toString();
            pst.setString(3,day);
            String venue;
            venue = venue_cb.getSelectedItem().toString();
            pst.setString(4,venue);
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TimeZone.getDefault());
            String st=sdf.format(starting_time_js.getValue());
            pst.setString(5, st);
            String et=sdf.format(end_time_js.getValue());
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

    private void reset_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btniActionPerformed
        select_instructor_cb.setSelectedIndex(0);
        select_module_cb.setSelectedIndex(0);
        day_cb.setSelectedIndex(0);
        venue_cb.setSelectedIndex(0);
        java.util.Date date = new java.util.Date();
        Object stime = new java.sql.Time(date.getTime());
        starting_time_js.setValue(stime);
        Object etime = new java.sql.Time(date.getTime());
        end_time_js.setValue(etime);
    }//GEN-LAST:event_reset_btniActionPerformed

    private void update_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btniActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "UPDATE `instruct` SET `day`=?,`venue`=?,`starting_time`=?,`end_time`=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setInt(1, Integer.parseInt((String) venue_cb.getSelectedItem()));
            pst.setInt(2, Integer.parseInt((String) day_cb.getSelectedItem()));
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TimeZone.getDefault());
            String st=sdf.format(starting_time_js.getValue());
            pst.setString(5, st);
            String et=sdf.format(end_time_js.getValue());
            pst.setString(6, et);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) instruct_table.getModel();
            model.setRowCount(0);
            show_instruct();
            JOptionPane.showMessageDialog(null,"Updateded Sucsessfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_update_btniActionPerformed

    private void search_btnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnlActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
            String query = "SELECT * FROM instruct WHERE instructor_no=? AND module_code=?";
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, (String) select_instructor_cb.getSelectedItem());
            pst.setString(2, (String) select_module_cb.getSelectedItem());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String set_day = rs.getString("day");
                day_cb.setSelectedItem(set_day);

                String set_venue = rs.getString("venue");
                venue_cb.setSelectedItem(set_venue);

                starting_time_js.setValue(rs.getTime("start_time"));

                end_time_js.setValue(rs.getTime("end_time"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_btnlActionPerformed

    private void delete_btniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btniActionPerformed
        int opt = JOptionPane.showConfirmDialog(null, "Are sure to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","");
                String query = "DELETE FROM instruct WHERE instructor_no=? AND module_code=?";
                PreparedStatement pst = c.prepareStatement(query);
                pst.setString(1, (String) select_instructor_cb.getSelectedItem());
                pst.setString(2, (String) select_module_cb.getSelectedItem());
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

    private void instruct_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instruct_tableMouseClicked
        int i = instruct_table.getSelectedRow();
        TableModel model = instruct_table.getModel();

        String lecturer = model.getValueAt(i, 0).toString();
        select_instructor_cb.setSelectedIndex( instructorArray.indexOf(lecturer));

        String module = model.getValueAt(i, 1).toString();
        select_module_cb.setSelectedIndex( moduleArray.indexOf(module));

        String day = model.getValueAt(i, 2).toString();
        switch(day){
            case "Monday":
            day_cb.setSelectedIndex(0);
            break;
            case "Tuesday":
            day_cb.setSelectedIndex(1);
            break;
            case "Wednesday":
            day_cb.setSelectedIndex(2);
            break;
            case "Thursday":
            day_cb.setSelectedIndex(3);
            break;
            case "Friday":
            day_cb.setSelectedIndex(4);
            break;
        }

        String venue = model.getValueAt(i, 3).toString();
        switch(venue){
            case "4th Floor":
            venue_cb.setSelectedIndex(0);
            break;
            case "Mini Auditorium":
            venue_cb.setSelectedIndex(1);
            break;
            case "W001":
            venue_cb.setSelectedIndex(2);
            break;
            case "W002":
            venue_cb.setSelectedIndex(3);
            break;
        }

        try{
            Date st=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 4));
            starting_time_js.setValue(st);
            Date et=new SimpleDateFormat("HH:mm").parse((String) model.getValueAt(i, 5));
            end_time_js.setValue(et);
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
            java.util.logging.Logger.getLogger(instructor_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(instructor_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(instructor_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(instructor_soc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new instructor_soc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JPanel add_instructor_panel;
    private javax.swing.JButton add_module_btn;
    private javax.swing.JTextField address_tb;
    private javax.swing.JButton assign_btni;
    private javax.swing.JPanel assign_module_panel;
    private javax.swing.JButton back_btn;
    private javax.swing.JTextField contact_no_tb;
    private javax.swing.JComboBox<String> day_cb;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton delete_btni;
    private javax.swing.JTextField email_tb;
    private javax.swing.JSpinner end_time_js;
    private javax.swing.JButton home_btn;
    private javax.swing.JTable instruct_table;
    private javax.swing.JTable instructor_list;
    private javax.swing.JTextField instructor_no_tb;
    private javax.swing.JTable instructor_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel logo_label;
    private javax.swing.JTable module_list;
    private javax.swing.JTextField name_tb;
    private javax.swing.JTextField nic_no_tb;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton reset_btni;
    private javax.swing.JButton search_btn;
    private javax.swing.JButton search_btnl;
    private javax.swing.JComboBox<String> select_instructor_cb;
    private javax.swing.JComboBox<String> select_module_cb;
    private com.toedter.calendar.JDateChooser starting_date_tb;
    private javax.swing.JSpinner starting_time_js;
    private javax.swing.JButton update_btn;
    private javax.swing.JButton update_btni;
    private javax.swing.JComboBox<String> venue_cb;
    // End of variables declaration//GEN-END:variables
}
