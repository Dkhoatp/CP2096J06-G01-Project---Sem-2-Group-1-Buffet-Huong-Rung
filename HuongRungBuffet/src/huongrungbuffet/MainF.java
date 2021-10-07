/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huongrungbuffet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dell
 */
public class MainF extends javax.swing.JFrame {

    /**
     * Creates new form Main
     *
     */
    private String sqlCustomer = "SELECT * FROM Customer";

    public MainF() throws SQLException, ClassNotFoundException {
        initComponents();
        //Giao dien dang nhap
        setLocationRelativeTo(null);
        setSize(1030, 730);
        setTitle("Dang Nhap");
        initTable();
        LoadMainF();
    }

    public void EmpAlertDialog() {
        jDNewEmpAlert.setVisible(true);
        jDNewEmpAlert.setSize(361, 250);
        jDNewEmpAlert.setLocationRelativeTo(this);
    }

    public void ShowAllEmpDate() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("EmployeeID");
            vcthead.add("FullName ");
            vcthead.add("Title");
            vcthead.add("Phone");
            vcthead.add("birthday");
            vcthead.add("Eaddress");
            vcthead.add("Department");
            vcthead.add("Username");
            vcthead.add("Password");
            vcthead.add("IdentificationNumber");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select [EmployeeID], [FullName], [Title], [Phone], [BirthDay], [EAddress], [ParkName], [UserName], [Password], [IdentificationNumber] from [dbo].[Employee] as a join [dbo].[Departments] as b on a.DepartmentID = b.DepartmentID order by ParkName";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                String bd = format.format(rs.getDate(5));
                vt.add(bd);
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));
                vt.add(rs.getString(8));
                vt.add(rs.getString(9));
                vt.add(rs.getString(10));

                vctdata.add(vt);
            }
            jTableShowNhanVien.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ShowAllFood() {
        try {
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("Name ");
            vcthead.add("Description");
            vcthead.add("Type");
            vcthead.add("Picture");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID order by FoodID";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ShowAllCustomerViewManage() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("Customer ID");
            vcthead.add("FullName");
            vcthead.add("Phone");
            vcthead.add("BirthDay");
            vcthead.add("Identification Number");
            vcthead.add("Point");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select * from Customer order by CustomerID";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                String bd = format.format(rs.getDate(4));
                vt.add(bd);
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));

                vctdata.add(vt);
            }
            jTableShowKhachHang.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
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

        jDQuanLy = new javax.swing.JDialog();
        jPManagerControl = new javax.swing.JPanel();
        Btn1 = new javax.swing.JButton();
        Btn2 = new javax.swing.JButton();
        Btn3 = new javax.swing.JButton();
        Btn4 = new javax.swing.JButton();
        Btn5 = new javax.swing.JButton();
        Btn6 = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        EName = new javax.swing.JLabel();
        EID = new javax.swing.JLabel();
        JFEmpManage = new javax.swing.JPanel();
        jScrollShowNhanVien = new javax.swing.JScrollPane();
        jTableShowNhanVien = new javax.swing.JTable();
        jLEmpTitle = new javax.swing.JLabel();
        EmpID = new javax.swing.JLabel();
        EmpFullname = new javax.swing.JLabel();
        EmpTitle = new javax.swing.JLabel();
        EmpPhone = new javax.swing.JLabel();
        EmpBirthDay = new javax.swing.JLabel();
        EmpAddress = new javax.swing.JLabel();
        EmpDepartmentID = new javax.swing.JLabel();
        EmpUserName = new javax.swing.JLabel();
        EmpPassword = new javax.swing.JLabel();
        EmpIdentificationNumber = new javax.swing.JLabel();
        txtEmpID = new javax.swing.JTextField();
        txtEmpName = new javax.swing.JTextField();
        txtEmpTitle = new javax.swing.JTextField();
        txtEmpPhone = new javax.swing.JTextField();
        txtEmpBirthDay = new javax.swing.JTextField();
        txtEmpAddress = new javax.swing.JTextField();
        txtEmpUserName = new javax.swing.JTextField();
        txtEmpPassword = new javax.swing.JTextField();
        txtEmpIdentification = new javax.swing.JTextField();
        AddNewEmpBtn = new javax.swing.JButton();
        SaveEmpBtn = new javax.swing.JButton();
        DeeleteEmpBtn = new javax.swing.JButton();
        ClearAllEmpTxtField = new javax.swing.JButton();
        jLSearchingEmp = new javax.swing.JLabel();
        jTSearchingByEmp = new javax.swing.JTextField();
        jRBtnEmpID = new javax.swing.JRadioButton();
        jRBtnEmpDepartments = new javax.swing.JRadioButton();
        jRBtnEmpUserName = new javax.swing.JRadioButton();
        jBEmpSearchBy = new javax.swing.JButton();
        CBtEmpDepartment = new javax.swing.JComboBox<>();
        JFCustomerView = new javax.swing.JPanel();
        jScrollShowKhachHang = new javax.swing.JScrollPane();
        jTableShowKhachHang = new javax.swing.JTable();
        jLEmpTitle1 = new javax.swing.JLabel();
        jLCustomerId = new javax.swing.JLabel();
        jLFullName = new javax.swing.JLabel();
        jLCustomerPhone = new javax.swing.JLabel();
        jLshowFullName = new javax.swing.JLabel();
        jLshowCustomerId = new javax.swing.JLabel();
        jLshowCustomerPhone = new javax.swing.JLabel();
        jLCustomerIdenNum = new javax.swing.JLabel();
        jLCustomerBirthday = new javax.swing.JLabel();
        jLCustomerPoint = new javax.swing.JLabel();
        jLshowCustomerIdenNum = new javax.swing.JLabel();
        jLshowCustomerBirthday = new javax.swing.JLabel();
        jLshowCustomerPoint = new javax.swing.JLabel();
        CustomerHightPoints = new javax.swing.JButton();
        CustomerLowPoints = new javax.swing.JButton();
        CustomerDefaultsPoints = new javax.swing.JButton();
        JFFoodManage = new javax.swing.JPanel();
        jScrollShowFoodToManage = new javax.swing.JScrollPane();
        jTShowFoodToManage = new javax.swing.JTable();
        jLEmpTitle2 = new javax.swing.JLabel();
        jLFID = new javax.swing.JLabel();
        jLFName = new javax.swing.JLabel();
        jLFType = new javax.swing.JLabel();
        jLFDescription = new javax.swing.JLabel();
        jTFID = new javax.swing.JTextField();
        jTFName = new javax.swing.JTextField();
        jBAddNewFood = new javax.swing.JButton();
        jBSaveFood = new javax.swing.JButton();
        jBFoodClear = new javax.swing.JButton();
        jBFDelete = new javax.swing.JButton();
        jLSeachingFood = new javax.swing.JLabel();
        jTSeachingFood = new javax.swing.JTextField();
        jRSeachingFoodID = new javax.swing.JRadioButton();
        jRSeachingFoodName = new javax.swing.JRadioButton();
        jBSearchingFood = new javax.swing.JButton();
        jLFoodImg = new javax.swing.JLabel();
        jBFImg = new javax.swing.JButton();
        jLFImgLink = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTADescription = new javax.swing.JTextArea();
        jCBFoodType = new javax.swing.JComboBox<>();
        jLSeachingFood1 = new javax.swing.JLabel();
        jBSortFoodID = new javax.swing.JButton();
        jbSortByName = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JF4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        JF5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        JF6 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jDBep = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        Btn7 = new javax.swing.JButton();
        Btn14 = new javax.swing.JButton();
        Btn21 = new javax.swing.JButton();
        Btn28 = new javax.swing.JButton();
        Btn35 = new javax.swing.JButton();
        Btn36 = new javax.swing.JButton();
        Title5 = new javax.swing.JLabel();
        EName5 = new javax.swing.JLabel();
        EID5 = new javax.swing.JLabel();
        JF32 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton49 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        JF33 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        JF34 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        JF35 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        JF36 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        JF37 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jDkho = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        Btn8 = new javax.swing.JButton();
        Btn9 = new javax.swing.JButton();
        Btn10 = new javax.swing.JButton();
        Btn11 = new javax.swing.JButton();
        Btn12 = new javax.swing.JButton();
        Btn13 = new javax.swing.JButton();
        Title1 = new javax.swing.JLabel();
        EName1 = new javax.swing.JLabel();
        EID1 = new javax.swing.JLabel();
        JF18 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        JF7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        JF8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        JF9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        JF10 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        JF11 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jDThuNgan = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        Btn43 = new javax.swing.JButton();
        Btn44 = new javax.swing.JButton();
        Btn45 = new javax.swing.JButton();
        Btn46 = new javax.swing.JButton();
        Btn47 = new javax.swing.JButton();
        Btn48 = new javax.swing.JButton();
        Title7 = new javax.swing.JLabel();
        EName7 = new javax.swing.JLabel();
        EID7 = new javax.swing.JLabel();
        JF39 = new javax.swing.JPanel();
        jShowCustomer = new javax.swing.JScrollPane();
        jTableCS = new javax.swing.JTable();
        btnAddCs = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        CsID = new javax.swing.JLabel();
        CsName = new javax.swing.JLabel();
        CsPhone = new javax.swing.JLabel();
        CsB = new javax.swing.JLabel();
        CsIN = new javax.swing.JLabel();
        CsPoint = new javax.swing.JLabel();
        txtCsID = new javax.swing.JTextField();
        txtCsName = new javax.swing.JTextField();
        txtCsPhone = new javax.swing.JTextField();
        txtCsBirthday = new javax.swing.JTextField();
        txtCsIN = new javax.swing.JTextField();
        txtCsPoint = new javax.swing.JTextField();
        btnUpCs = new javax.swing.JButton();
        btnDeletCs = new javax.swing.JButton();
        txtSearching = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        JF12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        JF13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        JF14 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        JF15 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        JF16 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jDBar = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        Btn22 = new javax.swing.JButton();
        Btn23 = new javax.swing.JButton();
        Btn24 = new javax.swing.JButton();
        Btn25 = new javax.swing.JButton();
        Btn26 = new javax.swing.JButton();
        Btn27 = new javax.swing.JButton();
        Title3 = new javax.swing.JLabel();
        EName3 = new javax.swing.JLabel();
        EID3 = new javax.swing.JLabel();
        JF20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        JF21 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        JF22 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        JF23 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        JF24 = new javax.swing.JPanel();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        JF25 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jDNewEmpAlert = new javax.swing.JDialog();
        jPNewEmpAlert = new javax.swing.JPanel();
        jTNewEmpAlertTitle = new javax.swing.JLabel();
        jTNewEmpAlertDescription = new javax.swing.JLabel();
        jBNewEmpAlertBtn = new javax.swing.JButton();
        buttonGroupSearchingEmp = new javax.swing.ButtonGroup();
        buttonGroupSearchingFood = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton47 = new javax.swing.JButton();

        jDQuanLy.setLocation(new java.awt.Point(0, 0));
        jDQuanLy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jDQuanLyComponentHidden(evt);
            }
        });

        jPManagerControl.setBackground(new java.awt.Color(255, 153, 51));
        jPManagerControl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn1.setText("Manage Employee");
        Btn1.setPreferredSize(new java.awt.Dimension(111, 33));
        Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn1ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, 30));

        Btn2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn2.setText("View Customer");
        Btn2.setMaximumSize(new java.awt.Dimension(111, 33));
        Btn2.setMinimumSize(new java.awt.Dimension(111, 33));
        Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn2ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 140, -1));

        Btn3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn3.setText("Manage Food");
        Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn3ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 140, -1));

        Btn4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn4.setText("Btn 4");
        Btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn4ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 140, -1));

        Btn5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn5.setText("Btn 5");
        Btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 140, -1));

        Btn6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn6.setText("Btn 6");
        Btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn6ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 140, -1));

        Title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Department1");
        jPManagerControl.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 180, 30));

        EName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        EName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EName.setText("EmployeeName");
        jPManagerControl.add(EName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 170, 30));

        EID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        EID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EID.setText("EmployeeID");
        jPManagerControl.add(EID, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 180, 30));

        JFEmpManage.setBackground(new java.awt.Color(51, 255, 255));
        JFEmpManage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JFEmpManage.setPreferredSize(new java.awt.Dimension(1685, 542));

        jTableShowNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EmployeeID", "FullName", "Title", "Phone", "BirthDay", "Address", "Department", "UserName", "Password", "IdentificationNumber"
            }
        ));
        jTableShowNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableShowNhanVienMouseClicked(evt);
            }
        });
        jScrollShowNhanVien.setViewportView(jTableShowNhanVien);

        jLEmpTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLEmpTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEmpTitle.setText("Employee Manage");

        EmpID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpID.setText("EmployeeID:");

        EmpFullname.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpFullname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpFullname.setText("FullName:");

        EmpTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpTitle.setText("Title:");

        EmpPhone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpPhone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpPhone.setText("Phone:");

        EmpBirthDay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpBirthDay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpBirthDay.setText("Birth day:");

        EmpAddress.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpAddress.setText("Address:");

        EmpDepartmentID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpDepartmentID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpDepartmentID.setText("Department:");

        EmpUserName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpUserName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpUserName.setText("UserName:");

        EmpPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpPassword.setText("Passwword:");

        EmpIdentificationNumber.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        EmpIdentificationNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmpIdentificationNumber.setText("Identification Num:");

        txtEmpID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpIDActionPerformed(evt);
            }
        });

        txtEmpIdentification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpIdentificationActionPerformed(evt);
            }
        });

        AddNewEmpBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AddNewEmpBtn.setText("Add New Employee");
        AddNewEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewEmpBtnActionPerformed(evt);
            }
        });

        SaveEmpBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SaveEmpBtn.setText("Save");
        SaveEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveEmpBtnActionPerformed(evt);
            }
        });

        DeeleteEmpBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        DeeleteEmpBtn.setText("Delete");
        DeeleteEmpBtn.setPreferredSize(new java.awt.Dimension(105, 35));
        DeeleteEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeeleteEmpBtnActionPerformed(evt);
            }
        });

        ClearAllEmpTxtField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ClearAllEmpTxtField.setText("Clear");
        ClearAllEmpTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearAllEmpTxtFieldActionPerformed(evt);
            }
        });

        jLSearchingEmp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLSearchingEmp.setText("Searching by:");

        buttonGroupSearchingEmp.add(jRBtnEmpID);
        jRBtnEmpID.setText("ID");

        buttonGroupSearchingEmp.add(jRBtnEmpDepartments);
        jRBtnEmpDepartments.setText("Departments");

        buttonGroupSearchingEmp.add(jRBtnEmpUserName);
        jRBtnEmpUserName.setText("UserName");

        jBEmpSearchBy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBEmpSearchBy.setText("Seach");
        jBEmpSearchBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEmpSearchByActionPerformed(evt);
            }
        });

        CBtEmpDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Kitchen", "WareHouse", "Cashiers", "Bar" }));

        javax.swing.GroupLayout JFEmpManageLayout = new javax.swing.GroupLayout(JFEmpManage);
        JFEmpManage.setLayout(JFEmpManageLayout);
        JFEmpManageLayout.setHorizontalGroup(
            JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFEmpManageLayout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jLEmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollShowNhanVien)
            .addGroup(JFEmpManageLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                        .addComponent(EmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFEmpManageLayout.createSequentialGroup()
                                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(JFEmpManageLayout.createSequentialGroup()
                                            .addComponent(EmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JFEmpManageLayout.createSequentialGroup()
                                            .addComponent(EmpFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(JFEmpManageLayout.createSequentialGroup()
                                            .addComponent(EmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                                        .addComponent(EmpBirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEmpBirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(EmpDepartmentID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EmpPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EmpUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EmpAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EmpIdentificationNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEmpPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmpUserName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmpAddress, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmpIdentification)
                                    .addComponent(CBtEmpDepartment, javax.swing.GroupLayout.Alignment.LEADING, 0, 117, Short.MAX_VALUE)))
                            .addGroup(JFEmpManageLayout.createSequentialGroup()
                                .addComponent(jLSearchingEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTSearchingByEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRBtnEmpID)
                                .addGap(4, 4, 4)
                                .addComponent(jRBtnEmpDepartments)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRBtnEmpUserName)))
                        .addGap(42, 42, 42)
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ClearAllEmpTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SaveEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DeeleteEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddNewEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBEmpSearchBy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        JFEmpManageLayout.setVerticalGroup(
            JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFEmpManageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLEmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EmpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddNewEmpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmpFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFEmpManageLayout.createSequentialGroup()
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SaveEmpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JFEmpManageLayout.createSequentialGroup()
                                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EmpDepartmentID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBtEmpDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmpUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClearAllEmpTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeeleteEmpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmpBirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpBirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmpIdentificationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpIdentification, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSearchingEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEmpSearchBy)
                    .addComponent(jTSearchingByEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBtnEmpID)
                    .addComponent(jRBtnEmpDepartments)
                    .addComponent(jRBtnEmpUserName))
                .addGap(18, 18, 18)
                .addComponent(jScrollShowNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        JFCustomerView.setBackground(new java.awt.Color(255, 255, 153));
        JFCustomerView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JFCustomerView.setPreferredSize(new java.awt.Dimension(1685, 542));

        jTableShowKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "FullName", "Phone", "BirthDay", "Identification Number", "Point"
            }
        ));
        jTableShowKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableShowKhachHangMouseClicked(evt);
            }
        });
        jScrollShowKhachHang.setViewportView(jTableShowKhachHang);

        jLEmpTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLEmpTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEmpTitle1.setText("Customer View");

        jLCustomerId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCustomerId.setText("Customer ID:");

        jLFullName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLFullName.setText("Full Name:");

        jLCustomerPhone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCustomerPhone.setText("Phone:");

        jLshowFullName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLshowCustomerId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLshowCustomerPhone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLCustomerIdenNum.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCustomerIdenNum.setText("Identification Numr:");

        jLCustomerBirthday.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCustomerBirthday.setText("BirthDay:");

        jLCustomerPoint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCustomerPoint.setText("Point:");

        jLshowCustomerIdenNum.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLshowCustomerBirthday.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLshowCustomerPoint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        CustomerHightPoints.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CustomerHightPoints.setText("Hight Points");
        CustomerHightPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerHightPointsActionPerformed(evt);
            }
        });

        CustomerLowPoints.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CustomerLowPoints.setText("Low Points");
        CustomerLowPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerLowPointsActionPerformed(evt);
            }
        });

        CustomerDefaultsPoints.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CustomerDefaultsPoints.setText("Defaults");
        CustomerDefaultsPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDefaultsPointsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFCustomerViewLayout = new javax.swing.GroupLayout(JFCustomerView);
        JFCustomerView.setLayout(JFCustomerViewLayout);
        JFCustomerViewLayout.setHorizontalGroup(
            JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFCustomerViewLayout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLEmpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFCustomerViewLayout.createSequentialGroup()
                .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JFCustomerViewLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(CustomerDefaultsPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CustomerHightPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CustomerLowPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(JFCustomerViewLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLshowFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLshowCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLshowCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCustomerIdenNum, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCustomerBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCustomerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLshowCustomerIdenNum, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLshowCustomerBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLshowCustomerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addComponent(jScrollShowKhachHang)
        );
        JFCustomerViewLayout.setVerticalGroup(
            JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFCustomerViewLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLEmpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFCustomerViewLayout.createSequentialGroup()
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLCustomerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLshowCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLshowFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLFullName))
                        .addGap(39, 39, 39)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLCustomerPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLshowCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JFCustomerViewLayout.createSequentialGroup()
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLshowCustomerBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLCustomerBirthday))
                        .addGap(35, 35, 35)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLshowCustomerIdenNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLCustomerIdenNum))
                        .addGap(39, 39, 39)
                        .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLCustomerPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLshowCustomerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(JFCustomerViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerHightPoints)
                    .addComponent(CustomerLowPoints)
                    .addComponent(CustomerDefaultsPoints))
                .addGap(18, 18, 18)
                .addComponent(jScrollShowKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JFFoodManage.setBackground(new java.awt.Color(255, 204, 0));
        JFFoodManage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JFFoodManage.setPreferredSize(new java.awt.Dimension(1685, 0));

        jTShowFoodToManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FoodID", "Name", "Desciption", "Type", "Picture"
            }
        ));
        jTShowFoodToManage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTShowFoodToManageMouseClicked(evt);
            }
        });
        jScrollShowFoodToManage.setViewportView(jTShowFoodToManage);

        jLEmpTitle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLEmpTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEmpTitle2.setText("Manage Food");

        jLFID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLFID.setText("FoodID:");

        jLFName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLFName.setText("Name:");

        jLFType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLFType.setText("Type:");

        jLFDescription.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLFDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLFDescription.setText("Description:");

        jTFID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTFName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jBAddNewFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBAddNewFood.setText("Add new Food");
        jBAddNewFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddNewFoodActionPerformed(evt);
            }
        });

        jBSaveFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBSaveFood.setText("Save");
        jBSaveFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSaveFoodActionPerformed(evt);
            }
        });

        jBFoodClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBFoodClear.setText("Clear");
        jBFoodClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFoodClearActionPerformed(evt);
            }
        });

        jBFDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBFDelete.setText("Delete");

        jLSeachingFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLSeachingFood.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSeachingFood.setText("Searching by:");

        jTSeachingFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buttonGroupSearchingFood.add(jRSeachingFoodID);
        jRSeachingFoodID.setText("ID");
        jRSeachingFoodID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRSeachingFoodIDActionPerformed(evt);
            }
        });

        buttonGroupSearchingFood.add(jRSeachingFoodName);
        jRSeachingFoodName.setText("Name");

        jBSearchingFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBSearchingFood.setText("Searh");
        jBSearchingFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSearchingFoodActionPerformed(evt);
            }
        });

        jLFoodImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLFoodImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N

        jBFImg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBFImg.setText("Img");
        jBFImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFImgActionPerformed(evt);
            }
        });

        jLFImgLink.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jTADescription.setColumns(20);
        jTADescription.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTADescription.setRows(5);
        jScrollPane3.setViewportView(jTADescription);

        jCBFoodType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCBFoodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Defalut", "Appetizer", "MainCourse", "Dessert" }));

        jLSeachingFood1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLSeachingFood1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSeachingFood1.setText("Sort by");

        jBSortFoodID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBSortFoodID.setText("ID");
        jBSortFoodID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSortFoodIDActionPerformed(evt);
            }
        });

        jbSortByName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbSortByName.setText("Name");
        jbSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSortByNameActionPerformed(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton29.setText("Type");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Default");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFFoodManageLayout = new javax.swing.GroupLayout(JFFoodManage);
        JFFoodManage.setLayout(JFFoodManageLayout);
        JFFoodManageLayout.setHorizontalGroup(
            JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFFoodManageLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLEmpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(JFFoodManageLayout.createSequentialGroup()
                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLFDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JFFoodManageLayout.createSequentialGroup()
                                    .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFFoodManageLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLFType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(JFFoodManageLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLFID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                .addComponent(jLFName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(JFFoodManageLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTFID)
                                                .addComponent(jCBFoodType, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(JFFoodManageLayout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addComponent(jTFName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addComponent(jLSeachingFood1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBSortFoodID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jbSortByName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLFImgLink, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jBFoodClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBFDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                        .addComponent(jBFImg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jBSaveFood, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBAddNewFood, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLFoodImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jRSeachingFoodID)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jRSeachingFoodName))
                                            .addComponent(jTSeachingFood)
                                            .addComponent(jLSeachingFood, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jBSearchingFood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addComponent(jScrollShowFoodToManage, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFFoodManageLayout.setVerticalGroup(
            JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFFoodManageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLEmpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                                        .addComponent(jLSeachingFood, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTSeachingFood, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRSeachingFoodName)
                                            .addComponent(jRSeachingFoodID))
                                        .addGap(18, 18, 18)
                                        .addComponent(jBSearchingFood)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3))
                                    .addComponent(jLFoodImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jBAddNewFood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFID)
                                    .addComponent(jLFID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLFName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBSaveFood))
                                .addGap(19, 19, 19)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLFType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBFoodType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBFoodClear))
                                .addGap(18, 18, 18)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLFDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBFDelete))
                                .addGap(18, 18, 18)
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBFImg)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLSeachingFood1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBSortFoodID)
                                .addComponent(jbSortByName))
                            .addComponent(jLFImgLink, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollShowFoodToManage, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        JF4.setBackground(new java.awt.Color(255, 204, 153));
        JF4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF4.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel7.setText("jLabel7");

        jButton5.setText("jButton5");

        jButton6.setText("jButton6");

        javax.swing.GroupLayout JF4Layout = new javax.swing.GroupLayout(JF4);
        JF4.setLayout(JF4Layout);
        JF4Layout.setHorizontalGroup(
            JF4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JF4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF4Layout.createSequentialGroup()
                .addContainerGap(638, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(121, 121, 121))
        );
        JF4Layout.setVerticalGroup(
            JF4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF4Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel7)
                .addGap(191, 191, 191)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        JF5.setBackground(new java.awt.Color(255, 153, 153));
        JF5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF5.setPreferredSize(new java.awt.Dimension(1685, 0));

        jButton7.setText("jButton7");

        jButton8.setText("jButton8");

        javax.swing.GroupLayout JF5Layout = new javax.swing.GroupLayout(JF5);
        JF5.setLayout(JF5Layout);
        JF5Layout.setHorizontalGroup(
            JF5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF5Layout.createSequentialGroup()
                .addContainerGap(725, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(34, 34, 34))
        );
        JF5Layout.setVerticalGroup(
            JF5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(303, 303, 303)
                .addComponent(jButton8))
        );

        JF6.setBackground(new java.awt.Color(204, 255, 204));
        JF6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF6.setPreferredSize(new java.awt.Dimension(1685, 2));

        jButton9.setText("jButton9");

        jButton10.setText("jButton10");

        javax.swing.GroupLayout JF6Layout = new javax.swing.GroupLayout(JF6);
        JF6.setLayout(JF6Layout);
        JF6Layout.setHorizontalGroup(
            JF6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF6Layout.createSequentialGroup()
                .addContainerGap(568, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(185, 185, 185))
        );
        JF6Layout.setVerticalGroup(
            JF6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF6Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10))
        );

        javax.swing.GroupLayout jDQuanLyLayout = new javax.swing.GroupLayout(jDQuanLy.getContentPane());
        jDQuanLy.getContentPane().setLayout(jDQuanLyLayout);
        jDQuanLyLayout.setHorizontalGroup(
            jDQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDQuanLyLayout.createSequentialGroup()
                .addComponent(jPManagerControl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JFEmpManage, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JFCustomerView, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JFFoodManage, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF4, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF5, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF6, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDQuanLyLayout.setVerticalGroup(
            jDQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF6, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(JF5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(JF4, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(JFCustomerView, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(JFEmpManage, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(jPManagerControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDQuanLyLayout.createSequentialGroup()
                .addComponent(JFFoodManage, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jDBep.setLocation(new java.awt.Point(0, 0));

        jPanel10.setBackground(new java.awt.Color(255, 153, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn7.setText("Btn 1");
        Btn7.setPreferredSize(new java.awt.Dimension(111, 33));
        Btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn7ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 30));

        Btn14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn14.setText("Btn 2");
        Btn14.setMaximumSize(new java.awt.Dimension(111, 33));
        Btn14.setMinimumSize(new java.awt.Dimension(111, 33));
        Btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn14ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, -1));

        Btn21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn21.setText("Btn 3");
        Btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn21ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, -1));

        Btn28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn28.setText("Btn 4");
        Btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn28ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 180, -1));

        Btn35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn35.setText("Btn 5");
        Btn35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn35ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 180, -1));

        Btn36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn36.setText("Btn 6");
        Btn36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn36ActionPerformed(evt);
            }
        });
        jPanel10.add(Btn36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, -1));

        Title5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title5.setText("Department2");
        jPanel10.add(Title5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 220, 30));

        EName5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EName5.setText("EmployeeName");
        jPanel10.add(EName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 220, 30));

        EID5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EID5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EID5.setText("EmployeeID");
        jPanel10.add(EID5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 220, 30));

        JF32.setBackground(new java.awt.Color(51, 255, 255));
        JF32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF32.setPreferredSize(new java.awt.Dimension(1685, 542));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setText("Display all food");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Search food name");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("FoodID");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("FLDate");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Quantity");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FoodID", "FLDate", "Quantity"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jButton49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton49.setText("Save");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton52.setText("Edit");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton53.setText("Delete selected");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        jButton54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton54.setText("Delete List Data");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jButton55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton55.setText("Search list by day");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JF32Layout = new javax.swing.GroupLayout(JF32);
        JF32.setLayout(JF32Layout);
        JF32Layout.setHorizontalGroup(
            JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF32Layout.createSequentialGroup()
                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton53, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JF32Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(32, 32, 32)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JF32Layout.createSequentialGroup()
                                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JF32Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane2))
        );
        JF32Layout.setVerticalGroup(
            JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JF32Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(11, 11, 11)
                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JF32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JF33.setBackground(new java.awt.Color(0, 191, 255));
        JF33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF33.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel5.setText("jLabel2");

        jButton12.setText("jButton3");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("FLDate");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("EmployeeID");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(jTable3);

        jButton56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton56.setText("Edit");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton57.setText("Save");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        jButton58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton58.setText(" Delete select");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton59.setText("Display all");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        jButton60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton60.setText("Delete Data");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Make Fooflist Next Week");

        javax.swing.GroupLayout JF33Layout = new javax.swing.GroupLayout(JF33);
        JF33.setLayout(JF33Layout);
        JF33Layout.setHorizontalGroup(
            JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF33Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel5))
            .addGroup(JF33Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF33Layout.createSequentialGroup()
                        .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JF33Layout.createSequentialGroup()
                                .addGap(594, 594, 594)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF33Layout.createSequentialGroup()
                        .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JF33Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jButton56)
                                .addGap(49, 49, 49)
                                .addComponent(jButton58)
                                .addGap(42, 42, 42)
                                .addComponent(jButton57)
                                .addGap(46, 46, 46)
                                .addComponent(jButton59)
                                .addGap(28, 28, 28)
                                .addComponent(jButton60))
                            .addGroup(JF33Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(73, 73, 73)
                                .addComponent(jTextField8)
                                .addGap(423, 423, 423)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF33Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(109, 109, 109)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1198, 1198, 1198))))
        );
        JF33Layout.setVerticalGroup(
            JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF33Layout.createSequentialGroup()
                .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF33Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JF33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JF33Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                        .addComponent(jButton12))
                    .addGroup(JF33Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(JF33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton56)
                            .addComponent(jButton57)
                            .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton59)
                            .addComponent(jButton60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        JF34.setBackground(new java.awt.Color(255, 204, 0));
        JF34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF34.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel10.setText("jLabel6");

        jButton13.setText("jButton4");

        javax.swing.GroupLayout JF34Layout = new javax.swing.GroupLayout(JF34);
        JF34.setLayout(JF34Layout);
        JF34Layout.setHorizontalGroup(
            JF34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF34Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF34Layout.createSequentialGroup()
                .addContainerGap(950, Short.MAX_VALUE)
                .addComponent(jButton13)
                .addGap(91, 91, 91))
        );
        JF34Layout.setVerticalGroup(
            JF34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF34Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton13))
        );

        JF35.setBackground(new java.awt.Color(255, 204, 153));
        JF35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF35.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel11.setText("jLabel7");

        jButton14.setText("jButton5");

        jButton15.setText("jButton6");

        javax.swing.GroupLayout JF35Layout = new javax.swing.GroupLayout(JF35);
        JF35.setLayout(JF35Layout);
        JF35Layout.setHorizontalGroup(
            JF35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JF35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF35Layout.createSequentialGroup()
                .addContainerGap(920, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addGap(121, 121, 121))
        );
        JF35Layout.setVerticalGroup(
            JF35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF35Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel11)
                .addGap(222, 222, 222)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15))
        );

        JF36.setBackground(new java.awt.Color(255, 153, 153));
        JF36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF36.setPreferredSize(new java.awt.Dimension(1685, 0));

        jButton16.setText("jButton7");

        jButton17.setText("jButton8");

        javax.swing.GroupLayout JF36Layout = new javax.swing.GroupLayout(JF36);
        JF36.setLayout(JF36Layout);
        JF36Layout.setHorizontalGroup(
            JF36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF36Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF36Layout.createSequentialGroup()
                .addContainerGap(1007, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addGap(34, 34, 34))
        );
        JF36Layout.setVerticalGroup(
            JF36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(303, 303, 303)
                .addComponent(jButton17))
        );

        JF37.setBackground(new java.awt.Color(204, 255, 204));
        JF37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF37.setPreferredSize(new java.awt.Dimension(1685, 2));

        jButton18.setText("jButton9");

        jButton19.setText("jButton10");

        javax.swing.GroupLayout JF37Layout = new javax.swing.GroupLayout(JF37);
        JF37.setLayout(JF37Layout);
        JF37Layout.setHorizontalGroup(
            JF37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF37Layout.createSequentialGroup()
                .addContainerGap(848, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addGap(185, 185, 185))
        );
        JF37Layout.setVerticalGroup(
            JF37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF37Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19))
        );

        javax.swing.GroupLayout jDBepLayout = new javax.swing.GroupLayout(jDBep.getContentPane());
        jDBep.getContentPane().setLayout(jDBepLayout);
        jDBepLayout.setHorizontalGroup(
            jDBepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDBepLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF32, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF33, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF34, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF35, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF36, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF37, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDBepLayout.setVerticalGroup(
            jDBepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF37, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF36, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF34, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF33, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF32, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF35, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        jDkho.setLocation(new java.awt.Point(0, 0));

        jPanel6.setBackground(new java.awt.Color(255, 153, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn8.setText("Btn 1");
        Btn8.setPreferredSize(new java.awt.Dimension(111, 33));
        Btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn8ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 30));

        Btn9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn9.setText("Btn 2");
        Btn9.setMaximumSize(new java.awt.Dimension(111, 33));
        Btn9.setMinimumSize(new java.awt.Dimension(111, 33));
        Btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn9ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, -1));

        Btn10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn10.setText("Btn 3");
        Btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn10ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, -1));

        Btn11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn11.setText("Btn 4");
        Btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn11ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 180, -1));

        Btn12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn12.setText("Btn 5");
        Btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn12ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 180, -1));

        Btn13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn13.setText("Btn 6");
        Btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn13ActionPerformed(evt);
            }
        });
        jPanel6.add(Btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, -1));

        Title1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title1.setText("Department3");
        jPanel6.add(Title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 220, 30));

        EName1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EName1.setText("EmployeeName");
        jPanel6.add(EName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 220, 30));

        EID1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EID1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EID1.setText("EmployeeID");
        jPanel6.add(EID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 220, 30));

        JF18.setBackground(new java.awt.Color(51, 255, 255));
        JF18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF18.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel12.setText("jLabel8");

        jButton20.setText("jButton2");

        javax.swing.GroupLayout JF18Layout = new javax.swing.GroupLayout(JF18);
        JF18.setLayout(JF18Layout);
        JF18Layout.setHorizontalGroup(
            JF18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF18Layout.createSequentialGroup()
                .addContainerGap(953, Short.MAX_VALUE)
                .addComponent(jButton20)
                .addGap(88, 88, 88))
        );
        JF18Layout.setVerticalGroup(
            JF18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF18Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton20))
        );

        JF7.setBackground(new java.awt.Color(0, 255, 0));
        JF7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF7.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel13.setText("jLabel2");

        jButton21.setText("jButton3");

        javax.swing.GroupLayout JF7Layout = new javax.swing.GroupLayout(JF7);
        JF7.setLayout(JF7Layout);
        JF7Layout.setHorizontalGroup(
            JF7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF7Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF7Layout.createSequentialGroup()
                .addContainerGap(965, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addGap(82, 82, 82))
        );
        JF7Layout.setVerticalGroup(
            JF7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF7Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21))
        );

        JF8.setBackground(new java.awt.Color(255, 204, 0));
        JF8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF8.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel14.setText("jLabel6");

        jButton22.setText("jButton4");

        javax.swing.GroupLayout JF8Layout = new javax.swing.GroupLayout(JF8);
        JF8.setLayout(JF8Layout);
        JF8Layout.setHorizontalGroup(
            JF8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF8Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF8Layout.createSequentialGroup()
                .addContainerGap(956, Short.MAX_VALUE)
                .addComponent(jButton22)
                .addGap(91, 91, 91))
        );
        JF8Layout.setVerticalGroup(
            JF8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF8Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton22))
        );

        JF9.setBackground(new java.awt.Color(255, 204, 153));
        JF9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF9.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel15.setText("jLabel7");

        jButton23.setText("jButton5");

        jButton24.setText("jButton6");

        javax.swing.GroupLayout JF9Layout = new javax.swing.GroupLayout(JF9);
        JF9.setLayout(JF9Layout);
        JF9Layout.setHorizontalGroup(
            JF9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JF9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF9Layout.createSequentialGroup()
                .addContainerGap(920, Short.MAX_VALUE)
                .addComponent(jButton24)
                .addGap(121, 121, 121))
        );
        JF9Layout.setVerticalGroup(
            JF9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF9Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel15)
                .addGap(222, 222, 222)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton24))
        );

        JF10.setBackground(new java.awt.Color(255, 153, 153));
        JF10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF10.setPreferredSize(new java.awt.Dimension(1685, 0));

        jButton25.setText("jButton7");

        jButton26.setText("jButton8");

        javax.swing.GroupLayout JF10Layout = new javax.swing.GroupLayout(JF10);
        JF10.setLayout(JF10Layout);
        JF10Layout.setHorizontalGroup(
            JF10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF10Layout.createSequentialGroup()
                .addContainerGap(1007, Short.MAX_VALUE)
                .addComponent(jButton26)
                .addGap(34, 34, 34))
        );
        JF10Layout.setVerticalGroup(
            JF10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addGap(303, 303, 303)
                .addComponent(jButton26))
        );

        JF11.setBackground(new java.awt.Color(204, 255, 204));
        JF11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF11.setPreferredSize(new java.awt.Dimension(1685, 2));

        jButton27.setText("jButton9");

        jButton28.setText("jButton10");

        javax.swing.GroupLayout JF11Layout = new javax.swing.GroupLayout(JF11);
        JF11.setLayout(JF11Layout);
        JF11Layout.setHorizontalGroup(
            JF11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF11Layout.createSequentialGroup()
                .addContainerGap(1419, Short.MAX_VALUE)
                .addComponent(jButton28)
                .addGap(185, 185, 185))
        );
        JF11Layout.setVerticalGroup(
            JF11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF11Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton28))
        );

        javax.swing.GroupLayout jDkhoLayout = new javax.swing.GroupLayout(jDkho.getContentPane());
        jDkho.getContentPane().setLayout(jDkhoLayout);
        jDkhoLayout.setHorizontalGroup(
            jDkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDkhoLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF18, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF7, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF8, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF9, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF10, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF11, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDkhoLayout.setVerticalGroup(
            jDkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF11, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF10, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF8, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF7, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF18, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF9, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        jDThuNgan.setLocation(new java.awt.Point(0, 0));

        jPanel12.setBackground(new java.awt.Color(255, 153, 51));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn43.setText("Manage Customer");
        Btn43.setPreferredSize(new java.awt.Dimension(111, 33));
        Btn43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn43ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 30));

        Btn44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn44.setText("Btn 2");
        Btn44.setMaximumSize(new java.awt.Dimension(111, 33));
        Btn44.setMinimumSize(new java.awt.Dimension(111, 33));
        Btn44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn44ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, -1));

        Btn45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn45.setText("Btn 3");
        Btn45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn45ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, -1));

        Btn46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn46.setText("Btn 4");
        Btn46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn46ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 180, -1));

        Btn47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn47.setText("Btn 5");
        Btn47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn47ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 180, -1));

        Btn48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn48.setText("Btn 6");
        Btn48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn48ActionPerformed(evt);
            }
        });
        jPanel12.add(Btn48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, -1));

        Title7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title7.setText("Department4");
        jPanel12.add(Title7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 220, 30));

        EName7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EName7.setText("EmployeeName");
        jPanel12.add(EName7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 220, 30));

        EID7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EID7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EID7.setText("EmployeeID");
        jPanel12.add(EID7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 220, 30));

        JF39.setBackground(new java.awt.Color(51, 255, 255));
        JF39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF39.setPreferredSize(new java.awt.Dimension(1685, 542));

        jTableCS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCSMouseClicked(evt);
            }
        });
        jShowCustomer.setViewportView(jTableCS);

        btnAddCs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddCs.setText("Add Customer new");
        btnAddCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCsActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Customer Manage");
        jLabel27.setPreferredSize(new java.awt.Dimension(247, 49));

        CsID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CsID.setText("Customer ID");

        CsName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CsName.setText("Customer Name:");

        CsPhone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsPhone.setText("Customer Phone:");

        CsB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CsB.setText("Customer BirthDay:");

        CsIN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsIN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CsIN.setText("Identification Number:");

        CsPoint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CsPoint.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CsPoint.setText("Point:");

        btnUpCs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpCs.setText("Update");
        btnUpCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpCsActionPerformed(evt);
            }
        });

        btnDeletCs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDeletCs.setText("Delete Customer");
        btnDeletCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletCsActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSearch.setText("jButton29");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JF39Layout = new javax.swing.GroupLayout(JF39);
        JF39.setLayout(JF39Layout);
        JF39Layout.setHorizontalGroup(
            JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jShowCustomer)
            .addGroup(JF39Layout.createSequentialGroup()
                .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JF39Layout.createSequentialGroup()
                                .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF39Layout.createSequentialGroup()
                                        .addComponent(CsName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCsName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JF39Layout.createSequentialGroup()
                                        .addComponent(CsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JF39Layout.createSequentialGroup()
                                        .addComponent(CsID, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCsID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(89, 89, 89))
                            .addGroup(JF39Layout.createSequentialGroup()
                                .addComponent(txtSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnAddCs)
                        .addGap(135, 135, 135)
                        .addComponent(btnDeletCs)))
                .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JF39Layout.createSequentialGroup()
                                .addComponent(CsIN, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCsIN, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JF39Layout.createSequentialGroup()
                                .addComponent(CsB, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCsBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JF39Layout.createSequentialGroup()
                                .addComponent(CsPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCsPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnUpCs)
                        .addGap(138, 138, 138)
                        .addComponent(btnClear)))
                .addGap(128, 153, Short.MAX_VALUE))
            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JF39Layout.setVerticalGroup(
            JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF39Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCsBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CsB, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCsIN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CsIN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCsPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CsPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpCs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JF39Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF39Layout.createSequentialGroup()
                                .addComponent(txtCsID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF39Layout.createSequentialGroup()
                                .addComponent(CsID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCsName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CsName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JF39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddCs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletCs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jShowCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JF12.setBackground(new java.awt.Color(0, 255, 0));
        JF12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF12.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel17.setText("jLabel2");

        jButton30.setText("jButton3");

        javax.swing.GroupLayout JF12Layout = new javax.swing.GroupLayout(JF12);
        JF12.setLayout(JF12Layout);
        JF12Layout.setHorizontalGroup(
            JF12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF12Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF12Layout.createSequentialGroup()
                .addContainerGap(959, Short.MAX_VALUE)
                .addComponent(jButton30)
                .addGap(82, 82, 82))
        );
        JF12Layout.setVerticalGroup(
            JF12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF12Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton30))
        );

        JF13.setBackground(new java.awt.Color(255, 204, 0));
        JF13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF13.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel18.setText("jLabel6");

        jButton31.setText("jButton4");

        javax.swing.GroupLayout JF13Layout = new javax.swing.GroupLayout(JF13);
        JF13.setLayout(JF13Layout);
        JF13Layout.setHorizontalGroup(
            JF13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF13Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF13Layout.createSequentialGroup()
                .addContainerGap(950, Short.MAX_VALUE)
                .addComponent(jButton31)
                .addGap(91, 91, 91))
        );
        JF13Layout.setVerticalGroup(
            JF13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF13Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton31))
        );

        JF14.setBackground(new java.awt.Color(255, 204, 153));
        JF14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF14.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel19.setText("jLabel7");

        jButton32.setText("jButton5");

        jButton33.setText("jButton6");

        javax.swing.GroupLayout JF14Layout = new javax.swing.GroupLayout(JF14);
        JF14.setLayout(JF14Layout);
        JF14Layout.setHorizontalGroup(
            JF14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JF14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton32, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF14Layout.createSequentialGroup()
                .addContainerGap(920, Short.MAX_VALUE)
                .addComponent(jButton33)
                .addGap(121, 121, 121))
        );
        JF14Layout.setVerticalGroup(
            JF14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF14Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel19)
                .addGap(222, 222, 222)
                .addComponent(jButton32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton33))
        );

        JF15.setBackground(new java.awt.Color(255, 153, 153));
        JF15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF15.setPreferredSize(new java.awt.Dimension(1685, 0));

        jButton34.setText("jButton7");

        jButton35.setText("jButton8");

        javax.swing.GroupLayout JF15Layout = new javax.swing.GroupLayout(JF15);
        JF15.setLayout(JF15Layout);
        JF15Layout.setHorizontalGroup(
            JF15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF15Layout.createSequentialGroup()
                .addContainerGap(1007, Short.MAX_VALUE)
                .addComponent(jButton35)
                .addGap(34, 34, 34))
        );
        JF15Layout.setVerticalGroup(
            JF15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton34)
                .addGap(303, 303, 303)
                .addComponent(jButton35))
        );

        JF16.setBackground(new java.awt.Color(204, 255, 204));
        JF16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF16.setPreferredSize(new java.awt.Dimension(1685, 2));

        jButton36.setText("jButton9");

        jButton37.setText("jButton10");

        javax.swing.GroupLayout JF16Layout = new javax.swing.GroupLayout(JF16);
        JF16.setLayout(JF16Layout);
        JF16Layout.setHorizontalGroup(
            JF16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF16Layout.createSequentialGroup()
                .addContainerGap(848, Short.MAX_VALUE)
                .addComponent(jButton37)
                .addGap(185, 185, 185))
        );
        JF16Layout.setVerticalGroup(
            JF16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF16Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jButton36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton37))
        );

        javax.swing.GroupLayout jDThuNganLayout = new javax.swing.GroupLayout(jDThuNgan.getContentPane());
        jDThuNgan.getContentPane().setLayout(jDThuNganLayout);
        jDThuNganLayout.setHorizontalGroup(
            jDThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDThuNganLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF39, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF12, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF13, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF14, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF15, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF16, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDThuNganLayout.setVerticalGroup(
            jDThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF16, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(JF15, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(JF13, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(JF12, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(JF39, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
            .addComponent(JF14, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
        );

        jDBar.setLocation(new java.awt.Point(0, 0));

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn22.setText("Btn 1");
        Btn22.setPreferredSize(new java.awt.Dimension(111, 33));
        Btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn22ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 30));

        Btn23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn23.setText("Btn 2");
        Btn23.setMaximumSize(new java.awt.Dimension(111, 33));
        Btn23.setMinimumSize(new java.awt.Dimension(111, 33));
        Btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn23ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, -1));

        Btn24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn24.setText("Btn 3");
        Btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn24ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, -1));

        Btn25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn25.setText("Btn 4");
        Btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn25ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 180, -1));

        Btn26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn26.setText("Btn 5");
        Btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn26ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 180, -1));

        Btn27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn27.setText("Btn 6");
        Btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn27ActionPerformed(evt);
            }
        });
        jPanel8.add(Btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, -1));

        Title3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title3.setText("Department5");
        jPanel8.add(Title3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 220, 30));

        EName3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EName3.setText("EmployeeName");
        jPanel8.add(EName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 220, 30));

        EID3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EID3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EID3.setText("EmployeeID");
        jPanel8.add(EID3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 220, 30));

        JF20.setBackground(new java.awt.Color(51, 255, 255));
        JF20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF20.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel20.setText("jLabel8");

        jButton38.setText("jButton2");

        javax.swing.GroupLayout JF20Layout = new javax.swing.GroupLayout(JF20);
        JF20.setLayout(JF20Layout);
        JF20Layout.setHorizontalGroup(
            JF20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF20Layout.createSequentialGroup()
                .addContainerGap(953, Short.MAX_VALUE)
                .addComponent(jButton38)
                .addGap(88, 88, 88))
        );
        JF20Layout.setVerticalGroup(
            JF20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF20Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton38))
        );

        JF21.setBackground(new java.awt.Color(0, 255, 0));
        JF21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF21.setPreferredSize(new java.awt.Dimension(1685, 542));

        jLabel21.setText("jLabel2");

        jButton39.setText("jButton3");

        javax.swing.GroupLayout JF21Layout = new javax.swing.GroupLayout(JF21);
        JF21.setLayout(JF21Layout);
        JF21Layout.setHorizontalGroup(
            JF21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF21Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF21Layout.createSequentialGroup()
                .addContainerGap(965, Short.MAX_VALUE)
                .addComponent(jButton39)
                .addGap(82, 82, 82))
        );
        JF21Layout.setVerticalGroup(
            JF21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF21Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton39))
        );

        JF22.setBackground(new java.awt.Color(255, 204, 0));
        JF22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF22.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel22.setText("jLabel6");

        jButton40.setText("jButton4");

        javax.swing.GroupLayout JF22Layout = new javax.swing.GroupLayout(JF22);
        JF22.setLayout(JF22Layout);
        JF22Layout.setHorizontalGroup(
            JF22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF22Layout.createSequentialGroup()
                .addGap(0, 1079, Short.MAX_VALUE)
                .addComponent(jLabel22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF22Layout.createSequentialGroup()
                .addContainerGap(956, Short.MAX_VALUE)
                .addComponent(jButton40)
                .addGap(91, 91, 91))
        );
        JF22Layout.setVerticalGroup(
            JF22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF22Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton40))
        );

        JF23.setBackground(new java.awt.Color(255, 204, 153));
        JF23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF23.setPreferredSize(new java.awt.Dimension(1685, 0));

        jLabel23.setText("jLabel7");

        jButton41.setText("jButton5");

        jButton42.setText("jButton6");

        javax.swing.GroupLayout JF23Layout = new javax.swing.GroupLayout(JF23);
        JF23.setLayout(JF23Layout);
        JF23Layout.setHorizontalGroup(
            JF23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JF23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton41, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF23Layout.createSequentialGroup()
                .addContainerGap(920, Short.MAX_VALUE)
                .addComponent(jButton42)
                .addGap(121, 121, 121))
        );
        JF23Layout.setVerticalGroup(
            JF23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF23Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel23)
                .addGap(222, 222, 222)
                .addComponent(jButton41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton42))
        );

        JF24.setBackground(new java.awt.Color(255, 153, 153));
        JF24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF24.setPreferredSize(new java.awt.Dimension(1685, 0));

        jButton43.setText("jButton7");

        jButton44.setText("jButton8");

        javax.swing.GroupLayout JF24Layout = new javax.swing.GroupLayout(JF24);
        JF24.setLayout(JF24Layout);
        JF24Layout.setHorizontalGroup(
            JF24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF24Layout.createSequentialGroup()
                .addContainerGap(1007, Short.MAX_VALUE)
                .addComponent(jButton44)
                .addGap(34, 34, 34))
        );
        JF24Layout.setVerticalGroup(
            JF24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton43)
                .addGap(303, 303, 303)
                .addComponent(jButton44))
        );

        JF25.setBackground(new java.awt.Color(204, 255, 204));
        JF25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JF25.setPreferredSize(new java.awt.Dimension(1685, 2));

        jButton45.setText("jButton9");

        jButton46.setText("jButton10");

        javax.swing.GroupLayout JF25Layout = new javax.swing.GroupLayout(JF25);
        JF25.setLayout(JF25Layout);
        JF25Layout.setHorizontalGroup(
            JF25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF25Layout.createSequentialGroup()
                .addContainerGap(848, Short.MAX_VALUE)
                .addComponent(jButton46)
                .addGap(185, 185, 185))
        );
        JF25Layout.setVerticalGroup(
            JF25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF25Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jButton45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton46))
        );

        javax.swing.GroupLayout jDBarLayout = new javax.swing.GroupLayout(jDBar.getContentPane());
        jDBar.getContentPane().setLayout(jDBarLayout);
        jDBarLayout.setHorizontalGroup(
            jDBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDBarLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF20, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF21, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF22, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF23, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF24, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JF25, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDBarLayout.setVerticalGroup(
            jDBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JF25, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF24, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF22, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF21, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF20, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(JF23, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        jPNewEmpAlert.setBackground(new java.awt.Color(255, 204, 204));

        jTNewEmpAlertTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTNewEmpAlertTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTNewEmpAlertTitle.setText("Text");

        jTNewEmpAlertDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTNewEmpAlertDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTNewEmpAlertDescription.setText("Text");

        jBNewEmpAlertBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBNewEmpAlertBtn.setText("jButton48");
        jBNewEmpAlertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNewEmpAlertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPNewEmpAlertLayout = new javax.swing.GroupLayout(jPNewEmpAlert);
        jPNewEmpAlert.setLayout(jPNewEmpAlertLayout);
        jPNewEmpAlertLayout.setHorizontalGroup(
            jPNewEmpAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTNewEmpAlertDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTNewEmpAlertTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPNewEmpAlertLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jBNewEmpAlertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPNewEmpAlertLayout.setVerticalGroup(
            jPNewEmpAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNewEmpAlertLayout.createSequentialGroup()
                .addComponent(jTNewEmpAlertTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTNewEmpAlertDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBNewEmpAlertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDNewEmpAlertLayout = new javax.swing.GroupLayout(jDNewEmpAlert.getContentPane());
        jDNewEmpAlert.getContentPane().setLayout(jDNewEmpAlertLayout);
        jDNewEmpAlertLayout.setHorizontalGroup(
            jDNewEmpAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPNewEmpAlert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDNewEmpAlertLayout.setVerticalGroup(
            jDNewEmpAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPNewEmpAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jPanel3.setBackground(new java.awt.Color(255, 153, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login Title");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("UserName:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("UserName:");

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jButton47.setText("jButton47");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(630, 630, 630)
                .addComponent(jButton47)
                .addContainerGap(315, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(jButton47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ql = "quanly";
        String bep = "bep";
        String kho = "kho";
        String thuNgan = "thungan";
        String bar = "bar";
        if (jTextField1.getText().equals(ql)) {
            jDQuanLy.setTitle("Manager");
            jDQuanLy.setSize(1030, 730);
            jDQuanLy.setLocationRelativeTo(this);
            jDQuanLy.setVisible(true);
            JFEmpManage.setVisible(true);
            ShowAllEmpDate();
            JFCustomerView.setVisible(false);
            JFFoodManage.setVisible(false);
            JF4.setVisible(false);
            JF5.setVisible(false);
            JF6.setVisible(false);
            this.setVisible(false);
        } else if (jTextField1.getText().equals(bep)) {
            jDBep.setTitle("Kitchen");
            jDBep.setSize(1030, 730);
            jDBep.setLocationRelativeTo(this);
            jDBep.setVisible(true);
        } else if (jTextField1.getText().equals(kho)) {
            jDkho.setTitle("WareHouse");
            jDkho.setSize(1030, 730);
            jDkho.setLocationRelativeTo(this);
            jDkho.setVisible(true);
        } else if (jTextField1.getText().equals(thuNgan)) {
            jDThuNgan.setTitle("Cashier");
            jDThuNgan.setSize(1030, 730);
            jDThuNgan.setLocationRelativeTo(this);
            jDThuNgan.setVisible(true);
        } else if (jTextField1.getText().equals(bar)) {
            jDBar.setTitle("Bar");
            jDBar.setSize(1030, 730);
            jDBar.setLocationRelativeTo(this);
            jDBar.setVisible(true);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
        JFEmpManage.setVisible(true);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JF4.setVisible(false);
        JF5.setVisible(false);
        JF6.setVisible(false);
        ShowAllEmpDate();
    }//GEN-LAST:event_Btn1ActionPerformed

    private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
        ShowAllCustomerViewManage();
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(true);
        JFFoodManage.setVisible(false);
        JF4.setVisible(false);
        JF5.setVisible(false);
        JF6.setVisible(false);

    }//GEN-LAST:event_Btn2ActionPerformed

    private void Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn3ActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(true);
        JF4.setVisible(false);
        JF5.setVisible(false);
        JF6.setVisible(false);
        ShowAllFood();

    }//GEN-LAST:event_Btn3ActionPerformed

    private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JF4.setVisible(true);
        JF5.setVisible(false);
        JF6.setVisible(false);

    }//GEN-LAST:event_Btn4ActionPerformed

    private void Btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5ActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JF4.setVisible(false);
        JF5.setVisible(true);
        JF6.setVisible(false);

    }//GEN-LAST:event_Btn5ActionPerformed

    private void Btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn6ActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JF4.setVisible(false);
        JF5.setVisible(false);
        JF6.setVisible(true);

    }//GEN-LAST:event_Btn6ActionPerformed

    private void jSpinner1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner1AncestorAdded

    }//GEN-LAST:event_jSpinner1AncestorAdded

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged

    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner2AncestorAdded

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged

    }//GEN-LAST:event_jSpinner2StateChanged

    private void jSpinner3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner3AncestorAdded

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged

    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner4AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner4AncestorAdded

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged

    }//GEN-LAST:event_jSpinner4StateChanged

    private void jSpinner5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner5AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner5AncestorAdded

    private void jSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner5StateChanged

    }//GEN-LAST:event_jSpinner5StateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jSpinner6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner6AncestorAdded

    private void jSpinner6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner6StateChanged

    }//GEN-LAST:event_jSpinner6StateChanged

    private void jTableShowNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableShowNhanVienMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableShowNhanVien.getModel();
        int selectedRowIndex = jTableShowNhanVien.getSelectedRow();

        txtEmpID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtEmpName.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtEmpTitle.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txtEmpPhone.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txtEmpBirthDay.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtEmpAddress.setText(model.getValueAt(selectedRowIndex, 5).toString());
        CBtEmpDepartment.setSelectedItem(model.getValueAt(selectedRowIndex, 6).toString());
        txtEmpUserName.setText(model.getValueAt(selectedRowIndex, 7).toString());
        txtEmpPassword.setText(model.getValueAt(selectedRowIndex, 8).toString());
        txtEmpIdentification.setText(model.getValueAt(selectedRowIndex, 9).toString());
    }//GEN-LAST:event_jTableShowNhanVienMouseClicked

    private void txtEmpIdentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpIdentificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpIdentificationActionPerformed

    private void ClearAllEmpTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearAllEmpTxtFieldActionPerformed
        txtEmpID.setText("");
        txtEmpName.setText("");
        txtEmpTitle.setText("");
        txtEmpPhone.setText("");
        txtEmpBirthDay.setText("");
        txtEmpAddress.setText("");
        CBtEmpDepartment.setSelectedIndex(0);
        txtEmpUserName.setText("");
        txtEmpPassword.setText("");
        txtEmpIdentification.setText("");
    }//GEN-LAST:event_ClearAllEmpTxtFieldActionPerformed

    private void txtEmpIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpIDActionPerformed

    private void AddNewEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewEmpBtnActionPerformed
        try {
            //Check not null
            String CbBDpt = CBtEmpDepartment.getSelectedItem().toString();
            if (txtEmpID.getText().equals("") || txtEmpName.getText().equals("") || txtEmpTitle.getText().equals("") || txtEmpPhone.getText().equals("") || txtEmpBirthDay.getText().equals("") || txtEmpAddress.getText().equals("") || CbBDpt.equals("") || txtEmpUserName.equals("") || txtEmpPassword.getText().equals("") || txtEmpIdentification.getText().equals("")) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Khong duoc bo trong thong tin");
                return;
            }

            //connection
            java.sql.Connection con = MyConnection.getConnection();

            //kiem tra Employee ID
            String empid = txtEmpID.getText();
            if (empid.matches("E[0-9][0-9]") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (E[0-9][0-9])");
                return;
            }

            String queryEmpID = "select EmployeeID from Employee where EmployeeID=?";
            PreparedStatement pm1 = con.prepareCall(queryEmpID);
            pm1.setString(1, txtEmpID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (txtEmpID.getText().equals(rsEmpID.getString("EmployeeID"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("ID Da ton tai");
                    return;
                }
            }

            //kiem tra Phone
            String empPhone = txtEmpPhone.getText();
            if (empPhone.matches("\\d{9,11}") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("So dien thoai phai la so va do dai tu 9-11");
                return;
            }

            // Date 
            //kiem tra EmpBirthDays
            String day = "";
            String EmpBD = txtEmpBirthDay.getText();
            if (EmpBD.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Ngay sinh khong dung dinh dang (dd/mm/yyyy)");
                return;
            } else {
                day = EmpBD.substring(6, 10) + "/" + EmpBD.substring(3, 5) + "/" + EmpBD.substring(0, 2);
            }

            //kiem tra department
            String dpments = "";
            String cb = CBtEmpDepartment.getSelectedItem().toString();
            switch (cb) {
//                case "Manager":
//                    dpments = "DP01";
//                    break;
                case "Kitchen":
                    dpments = "DP02";
                    break;
                case "WareHouse":
                    dpments = "DP03";
                    break;
                case "Cashiers":
                    dpments = "DP04";
                    break;
                case "Bar":
                    dpments = "DP05";
                    break;
                default:
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("Invalid Department Name");
                    return;
            }

            //kiem tra Username
            String queryEmpUsername = "select UserName from Employee where UserName=?";
            PreparedStatement pmEmpUsername = con.prepareCall(queryEmpUsername);
            pmEmpUsername.setString(1, txtEmpUserName.getText());
            ResultSet rspmEmpUsername = pmEmpUsername.executeQuery();
            while (rspmEmpUsername.next()) {
                if (txtEmpUserName.getText().equals(rspmEmpUsername.getString("UserName"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("UserName Da ton tai");
                    return;
                }
            }

            //kiem tra identification num
            String EmpIdentitfi = "select IdentificationNumber from Employee where IdentificationNumber=?";
            PreparedStatement pmEmpIdentitfi = con.prepareCall(EmpIdentitfi);
            pmEmpIdentitfi.setString(1, txtEmpIdentification.getText());
            ResultSet rsEmpIdentitfi = pmEmpIdentitfi.executeQuery();
            while (rsEmpIdentitfi.next()) {
                if (txtEmpIdentification.getText().equals(rsEmpIdentitfi.getString("IdentificationNumber"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("Cmnd Da ton tai");
                    rsEmpIdentitfi.close();
                    return;
                }
            }

            //insert vao csdl
            String insertNewEmpQuery = "insert into Employee values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pm = con.prepareCall(insertNewEmpQuery);
            pm.setString(1, txtEmpID.getText());
            pm.setString(2, txtEmpName.getText());
            pm.setString(3, txtEmpTitle.getText());
            pm.setString(4, txtEmpPhone.getText());
            pm.setString(5, day);
            pm.setString(6, txtEmpAddress.getText());
            pm.setString(7, dpments);
            pm.setString(8, txtEmpUserName.getText());
            pm.setString(9, txtEmpPassword.getText());
            pm.setString(10, txtEmpIdentification.getText());
            pm.executeUpdate();

            con.close();
            pm.close();

            ShowAllEmpDate();

        } catch (ClassNotFoundException | SQLException ex) {
            EmpAlertDialog();
            jDNewEmpAlert.setTitle("cant Add");
            jTNewEmpAlertTitle.setText("Can not add");
            jTNewEmpAlertDescription.setText("Ngay/thang khong ton tai");
        }
    }//GEN-LAST:event_AddNewEmpBtnActionPerformed

    private void jBNewEmpAlertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNewEmpAlertBtnActionPerformed
        jDNewEmpAlert.setVisible(false);
    }//GEN-LAST:event_jBNewEmpAlertBtnActionPerformed

    private void SaveEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveEmpBtnActionPerformed

        try {
            //Check not null
            String CbBDpt = CBtEmpDepartment.getSelectedItem().toString();
            if (txtEmpID.getText().equals("") || txtEmpName.getText().equals("") || txtEmpTitle.getText().equals("") || txtEmpPhone.getText().equals("") || txtEmpBirthDay.getText().equals("") || txtEmpAddress.getText().equals("") || CbBDpt.equals("") || txtEmpUserName.equals("") || txtEmpPassword.getText().equals("") || txtEmpIdentification.getText().equals("")) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Update");
                jTNewEmpAlertTitle.setText("Can not Update");
                jTNewEmpAlertDescription.setText("Khong duoc bo trong thong tin");
                return;
            }

            //connection
            java.sql.Connection con = MyConnection.getConnection();

            //kiem tra Employee ID
            String empid = txtEmpID.getText();
            if (empid.matches("E[0-9][0-9]") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Update");
                jTNewEmpAlertTitle.setText("Can not Update");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (E[0-9][0-9])");
                return;
            }

            String queryEmpID = "select EmployeeID from Employee where EmployeeID=?";
            PreparedStatement pm1 = con.prepareCall(queryEmpID);
            pm1.setString(1, txtEmpID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (txtEmpID.getText().equals(rsEmpID.getString("EmployeeID")) == false) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Update");
                    jTNewEmpAlertTitle.setText("Can not Update");
                    jTNewEmpAlertDescription.setText("ID Khong ton tai");
                    return;
                }
            }

            //kiem tra Phone
            String empPhone = txtEmpPhone.getText();
            if (empPhone.matches("\\d{9,11}") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("So dien thoai phai la so va do dai tu 9-11");
                return;
            }
            // Date 
            //kiem tra EmpBirthDays
            String day = "";
            String EmpBD = txtEmpBirthDay.getText();
            if (EmpBD.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Ngay sinh khong dung dinh dang (dd/mm/yyyy)");
                return;
            } else {
                day = EmpBD.substring(6, 10) + "/" + EmpBD.substring(3, 5) + "/" + EmpBD.substring(0, 2);
            }
            //kiem tra department
            String dpments = "";
            String cb = CBtEmpDepartment.getSelectedItem().toString();
            switch (cb) {
//                case "Manager":
//                    dpments = "DP01";
//                    break;
                case "Kitchen":
                    dpments = "DP02";
                    break;
                case "WareHouse":
                    dpments = "DP03";
                    break;
                case "Cashiers":
                    dpments = "DP04";
                    break;
                case "Bar":
                    dpments = "DP05";
                    break;
                default:
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Update");
                    jTNewEmpAlertTitle.setText("Can not Update");
                    jTNewEmpAlertDescription.setText("Plaese select Departmnets");
                    return;
            }
            //kiem tra Username
            String queryEmpUsername = "select EmployeeID, UserName from Employee";
            PreparedStatement pmEmpUsername = con.prepareCall(queryEmpUsername);
            ResultSet rspmEmpUsername = pmEmpUsername.executeQuery();
            while (rspmEmpUsername.next()) {
                if (txtEmpID.getText().equals(rspmEmpUsername.getString("EmployeeID"))) {
                    continue;
                }

                if (txtEmpUserName.getText().equals(rspmEmpUsername.getString("UserName"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("UserName Da ton tai");
                    rspmEmpUsername.close();
                    return;
                }
            }
            //kiem tra identification num
            String EmpIdentitfi = "select EmployeeID, IdentificationNumber from Employee";
            PreparedStatement pmEmpIdentitfi = con.prepareCall(EmpIdentitfi);
            ResultSet rsEmpIdentitfi = pmEmpIdentitfi.executeQuery();
            while (rsEmpIdentitfi.next()) {
                if (txtEmpID.getText().equals(rsEmpIdentitfi.getString("EmployeeID"))) {
                    continue;
                }

                if (txtEmpIdentification.getText().equals(rsEmpIdentitfi.getString("IdentificationNumber"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Update");
                    jTNewEmpAlertTitle.setText("Can not Update");
                    jTNewEmpAlertDescription.setText("Cmnd Da ton tai");
                    rsEmpIdentitfi.close();
                    return;
                }
            }

            String EmpUpdate = "update Employee set FullName = ?, Title = ?, Phone = ?, BirthDay = ?, EAddress = ?, DepartmentID = ?, UserName = ?, Password = ?, IdentificationNumber = ? where EmployeeID =?";
            PreparedStatement pmEmpUpdate = con.prepareStatement(EmpUpdate);
            pmEmpUpdate.setString(1, txtEmpName.getText());
            pmEmpUpdate.setString(2, txtEmpTitle.getText());
            pmEmpUpdate.setString(3, txtEmpPhone.getText());
            pmEmpUpdate.setString(4, day);
            pmEmpUpdate.setString(5, txtEmpAddress.getText());
            pmEmpUpdate.setString(6, dpments);
            pmEmpUpdate.setString(7, txtEmpUserName.getText());
            pmEmpUpdate.setString(8, txtEmpPassword.getText());
            pmEmpUpdate.setString(9, txtEmpIdentification.getText());
            pmEmpUpdate.setString(10, txtEmpID.getText());
            pmEmpUpdate.executeUpdate();

            pmEmpUpdate.close();
            con.close();

            ShowAllEmpDate();

        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            EmpAlertDialog();
            jDNewEmpAlert.setTitle("cant Add");
            jTNewEmpAlertTitle.setText("Can not add");
            jTNewEmpAlertDescription.setText("Ngay/thang khong ton tai");
        }
    }//GEN-LAST:event_SaveEmpBtnActionPerformed

    private void DeeleteEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeeleteEmpBtnActionPerformed
        try {
            java.sql.Connection con = MyConnection.getConnection();

            //check usertname
            if (txtEmpID.getText().equals("")) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Delete");
                jTNewEmpAlertTitle.setText("Can not Delete");
                jTNewEmpAlertDescription.setText("Khong duoc bo trong ID");
                return;
            }

            String empid = txtEmpID.getText();
            if (empid.matches("E[0-9][0-9]") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Delete");
                jTNewEmpAlertTitle.setText("Can not Delete");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (E[0-9][0-9])");
                return;
            }

            String queryEmpID = "select EmployeeID from Employee where EmployeeID=?";
            PreparedStatement pm1 = con.prepareCall(queryEmpID);
            pm1.setString(1, txtEmpID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (txtEmpID.getText().equals(rsEmpID.getString("EmployeeID")) == false) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Delete");
                    jTNewEmpAlertTitle.setText("Can not Delete");
                    jTNewEmpAlertDescription.setText("ID Khong ton tai");
                    return;
                }
            }

            String EmpDelete = "delete from Employee where EmployeeID = ?";
            PreparedStatement pmEmpDelete = con.prepareStatement(EmpDelete);
            pmEmpDelete.setString(1, txtEmpID.getText());
            pmEmpDelete.executeUpdate();

            pmEmpDelete.close();
            con.close();

            ShowAllEmpDate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DeeleteEmpBtnActionPerformed

    private void jBEmpSearchByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEmpSearchByActionPerformed
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Vector vctdata = new Vector();
        Vector vcthead = new Vector();
        vcthead.add("EmployeeID");
        vcthead.add("FullName ");
        vcthead.add("Title");
        vcthead.add("Phone");
        vcthead.add("birthday");
        vcthead.add("Eaddress");
        vcthead.add("Department");
        vcthead.add("Username");
        vcthead.add("Password");
        vcthead.add("IdentificationNumber");
        try {
            String find = "";
            if (jRBtnEmpID.isSelected()) {
                find = "select EmployeeID, FullName, Title, Phone, BirthDay, EAddress, ParkName, UserName, Password, IdentificationNumber from Employee as a join Departments as b on a.DepartmentID = b.DepartmentID where EmployeeID like ? order by ParkName";
            }
            if (jRBtnEmpDepartments.isSelected()) {
                find = "select EmployeeID, FullName, Title, Phone, BirthDay, EAddress, ParkName, UserName, Password, IdentificationNumber from Employee as a join Departments as b on a.DepartmentID = b.DepartmentID where ParkName like ? order by ParkName";
            }
            if (jRBtnEmpUserName.isSelected()) {
                find = "select EmployeeID, FullName, Title, Phone, BirthDay, EAddress, ParkName, UserName, Password, IdentificationNumber from Employee as a join Departments as b on a.DepartmentID = b.DepartmentID where UserName like ? order by ParkName";
            }
            Connection con = MyConnection.getConnection();
            PreparedStatement pm = con.prepareCall(find);
            pm.setString(1, ("%" + jTSearchingByEmp.getText() + "%"));
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                String bd = format.format(rs.getDate(5));
                vt.add(bd);
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));
                vt.add(rs.getString(8));
                vt.add(rs.getString(9));
                vt.add(rs.getString(10));

                vctdata.add(vt);
            }
            jTableShowNhanVien.setModel(new DefaultTableModel(vctdata, vcthead));
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBEmpSearchByActionPerformed

    private void jTableShowKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableShowKhachHangMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableShowKhachHang.getModel();
        int selectedRowIndex = jTableShowKhachHang.getSelectedRow();

        jLshowCustomerId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jLshowFullName.setText(model.getValueAt(selectedRowIndex, 1).toString());
        jLshowCustomerPhone.setText(model.getValueAt(selectedRowIndex, 2).toString());
        jLshowCustomerBirthday.setText(model.getValueAt(selectedRowIndex, 3).toString());
        jLshowCustomerIdenNum.setText(model.getValueAt(selectedRowIndex, 4).toString());
        jLshowCustomerPoint.setText(model.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_jTableShowKhachHangMouseClicked

    private void CustomerHightPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerHightPointsActionPerformed
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("Customer ID");
            vcthead.add("FullName");
            vcthead.add("Phone");
            vcthead.add("BirthDay");
            vcthead.add("Identification Number");
            vcthead.add("Point");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select * from Customer order by Point desc";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                String bd = format.format(rs.getDate(4));
                vt.add(bd);
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));

                vctdata.add(vt);
            }
            jTableShowKhachHang.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_CustomerHightPointsActionPerformed

    private void CustomerLowPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerLowPointsActionPerformed
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("Customer ID");
            vcthead.add("FullName");
            vcthead.add("Phone");
            vcthead.add("BirthDay");
            vcthead.add("Identification Number");
            vcthead.add("Point");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select * from Customer order by Point";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                String bd = format.format(rs.getDate(4));
                vt.add(bd);
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));

                vctdata.add(vt);
            }
            jTableShowKhachHang.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_CustomerLowPointsActionPerformed

    private void CustomerDefaultsPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerDefaultsPointsActionPerformed
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("Customer ID");
            vcthead.add("FullName");
            vcthead.add("Phone");
            vcthead.add("BirthDay");
            vcthead.add("Identification Number");
            vcthead.add("Point");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select * from Customer order by CustomerID";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                String bd = format.format(rs.getDate(4));
                vt.add(bd);
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));

                vctdata.add(vt);
            }
            jTableShowKhachHang.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_CustomerDefaultsPointsActionPerformed

    private void jRSeachingFoodIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRSeachingFoodIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRSeachingFoodIDActionPerformed

    private void jTShowFoodToManageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTShowFoodToManageMouseClicked

        //jLFoodImg
        DefaultTableModel model = (DefaultTableModel) jTShowFoodToManage.getModel();
        int selectedRowIndex = jTShowFoodToManage.getSelectedRow();

        ImageIcon imageIcon = new ImageIcon(model.getValueAt(selectedRowIndex, 4).toString());
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        jLFoodImg.setIcon(new ImageIcon(newimg));

        jTFID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jTFName.setText(model.getValueAt(selectedRowIndex, 1).toString());
        jTADescription.setText(model.getValueAt(selectedRowIndex, 2).toString());
        jCBFoodType.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
        jLFImgLink.setText(model.getValueAt(selectedRowIndex, 4).toString());

        //jLFoodImg.setIcon(new ImageIcon(model.getValueAt(selectedRowIndex, 4).toString()));

    }//GEN-LAST:event_jTShowFoodToManageMouseClicked

    private void jBFImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFImgActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter ims = new FileNameExtensionFilter("Photo", "jpg", "png");
        fileChooser.setFileFilter(ims);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(jDQuanLy, "Chọn file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            jLFImgLink.setText("File:" + String.valueOf(file));

            ImageIcon imageIcon = new ImageIcon(String.valueOf(file));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            jLFoodImg.setIcon(new ImageIcon(newimg));

            //   jLFoodImg.setIcon(new ImageIcon(file.getAbsolutePath()));
        }
    }//GEN-LAST:event_jBFImgActionPerformed

    private void jDQuanLyComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDQuanLyComponentHidden
        this.setVisible(true);
    }//GEN-LAST:event_jDQuanLyComponentHidden

    private void Btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn27ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(false);
        JF21.setVisible(false);
        JF22.setVisible(false);
        JF23.setVisible(false);
        JF24.setVisible(false);
        JF25.setVisible(true);
    }//GEN-LAST:event_Btn27ActionPerformed

    private void Btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn26ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(false);
        JF21.setVisible(false);
        JF22.setVisible(false);
        JF23.setVisible(false);
        JF24.setVisible(true);
        JF25.setVisible(false);
    }//GEN-LAST:event_Btn26ActionPerformed

    private void Btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn25ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(false);
        JF21.setVisible(false);
        JF22.setVisible(false);
        JF23.setVisible(true);
        JF24.setVisible(false);
        JF25.setVisible(false);
    }//GEN-LAST:event_Btn25ActionPerformed

    private void Btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn24ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(false);
        JF21.setVisible(false);
        JF22.setVisible(true);
        JF23.setVisible(false);
        JF24.setVisible(false);
        JF25.setVisible(false);
    }//GEN-LAST:event_Btn24ActionPerformed

    private void Btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn23ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(false);
        JF21.setVisible(true);
        JF22.setVisible(false);
        JF23.setVisible(false);
        JF24.setVisible(false);
        JF25.setVisible(false);
    }//GEN-LAST:event_Btn23ActionPerformed

    private void Btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn22ActionPerformed
        // TODO add your handling code here:
        JF20.setVisible(true);
        JF21.setVisible(false);
        JF22.setVisible(false);
        JF23.setVisible(false);
        JF24.setVisible(false);
        JF25.setVisible(false);
    }//GEN-LAST:event_Btn22ActionPerformed

    private void Btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn13ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(false);
        JF7.setVisible(false);
        JF8.setVisible(false);
        JF9.setVisible(false);
        JF10.setVisible(false);
        JF11.setVisible(true);
    }//GEN-LAST:event_Btn13ActionPerformed

    private void Btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn12ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(false);
        JF7.setVisible(false);
        JF8.setVisible(false);
        JF9.setVisible(false);
        JF10.setVisible(true);
        JF11.setVisible(false);
    }//GEN-LAST:event_Btn12ActionPerformed

    private void Btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn11ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(false);
        JF7.setVisible(false);
        JF8.setVisible(false);
        JF9.setVisible(true);
        JF10.setVisible(false);
        JF11.setVisible(false);
    }//GEN-LAST:event_Btn11ActionPerformed

    private void Btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn10ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(false);
        JF7.setVisible(false);
        JF8.setVisible(true);
        JF9.setVisible(false);
        JF10.setVisible(false);
        JF11.setVisible(false);
    }//GEN-LAST:event_Btn10ActionPerformed

    private void Btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn9ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(false);
        JF7.setVisible(true);
        JF8.setVisible(false);
        JF9.setVisible(false);
        JF10.setVisible(false);
        JF11.setVisible(false);
    }//GEN-LAST:event_Btn9ActionPerformed

    private void Btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn8ActionPerformed
        // TODO add your handling code here:
        JF18.setVisible(true);
        JF7.setVisible(false);
        JF8.setVisible(false);
        JF9.setVisible(false);
        JF10.setVisible(false);
        JF11.setVisible(false);
    }//GEN-LAST:event_Btn8ActionPerformed

    private void Btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn7ActionPerformed
        JF32.setVisible(true);
        JF33.setVisible(false);
        JF34.setVisible(false);
        JF35.setVisible(false);
        JF36.setVisible(false);
        JF37.setVisible(false);
    }//GEN-LAST:event_Btn7ActionPerformed

    private void Btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn14ActionPerformed
        JF32.setVisible(false);
        JF33.setVisible(true);
        JF34.setVisible(false);
        JF35.setVisible(false);
        JF36.setVisible(false);
        JF37.setVisible(false);
    }//GEN-LAST:event_Btn14ActionPerformed

    private void Btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn21ActionPerformed
        // TODO add your handling code here:
        JF32.setVisible(false);
        JF33.setVisible(false);
        JF34.setVisible(true);
        JF35.setVisible(false);
        JF36.setVisible(false);
        JF37.setVisible(false);
    }//GEN-LAST:event_Btn21ActionPerformed

    private void Btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn28ActionPerformed
        // TODO add your handling code here:
        JF32.setVisible(false);
        JF33.setVisible(false);
        JF34.setVisible(false);
        JF35.setVisible(true);
        JF36.setVisible(false);
        JF37.setVisible(false);
    }//GEN-LAST:event_Btn28ActionPerformed

    private void Btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn35ActionPerformed
        // TODO add your handling code here:
        JF32.setVisible(false);
        JF33.setVisible(false);
        JF34.setVisible(false);
        JF35.setVisible(false);
        JF36.setVisible(true);
        JF37.setVisible(false);
    }//GEN-LAST:event_Btn35ActionPerformed

    private void Btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn36ActionPerformed
        // TODO add your handling code here:
        JF32.setVisible(false);
        JF33.setVisible(false);
        JF34.setVisible(false);
        JF35.setVisible(false);
        JF36.setVisible(false);
        JF37.setVisible(true);
    }//GEN-LAST:event_Btn36ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Display All Food:
        try {
            jTable2.setVisible(true);
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("FoodName");
            vcthead.add("FDescription");
            vcthead.add("FoodTypeID");
            vcthead.add("Picture");
            java.sql.Connection con = MyConnection.getConnection();
            String sqlf = "select * from Food";
            PreparedStatement pmf = con.prepareStatement(sqlf);
            ResultSet rsa = pmf.executeQuery();
            while (rsa.next()) {
                Vector n = new Vector();
                n.add(rsa.getString("FoodID"));
                n.add(rsa.getString("FoodName"));
                n.add(rsa.getString("FDescription"));
                n.add(rsa.getString("FoodTypeID"));
                n.add(rsa.getString("Picture"));
                vctdata.add(n);
            }
            jTable2.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        // search
        try {
            jTable2.setVisible(false);
            boolean kt = false;
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select * from Food where FoodName = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jTextField2.getText());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("FoodName").equals(jTextField2.getText())) {
                    kt = true;
                }
            }
            if (kt) {

                jTable2.setVisible(true);
                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("FoodID");
                vcthead.add("FoodName");
                vcthead.add("FDescription");
                vcthead.add("FoodTypeID");
                vcthead.add("Picture");
                String sqlf = "select * from Food where FoodName = ?";
                PreparedStatement pmf = con.prepareStatement(sqlf);
                pmf.setString(1, jTextField2.getText());
                ResultSet rsa = pmf.executeQuery();
                while (rsa.next()) {
                    Vector n = new Vector();
                    n.add(rsa.getString("FoodID"));
                    n.add(rsa.getString("FoodName"));
                    n.add(rsa.getString("FDescription"));
                    n.add(rsa.getString("FoodTypeID"));
                    n.add(rsa.getString("Picture"));
                    vctdata.add(n);
                }
                jTable2.setModel(new DefaultTableModel(vctdata, vcthead));
                con.close();
            } else {
                JOptionPane.showMessageDialog(this, "Not Found");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // Save data to SQL:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is empty");
        } else {
            String foodID;
            String flDate;
            String quantity;
            try {
                java.sql.Connection con = MyConnection.getConnection();
                String query = "insert into List(FoodID, FLDate, Quantity) values (?,?,?)";

                for (int i = 0; i < model.getRowCount(); i++) {
                    foodID = model.getValueAt(i, 0).toString();
                    flDate = model.getValueAt(i, 1).toString();
                    quantity = model.getValueAt(i, 2).toString();

                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, foodID);
                    ps.setDate(2, Date.valueOf(flDate));
                    ps.setString(3, quantity);

                    ps.execute();
                }
                //Success msg
                JOptionPane.showMessageDialog(this, "Data insert success");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // Đưa jTextfield vao jTable:
        if (jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter All Data !");
        } else {
            String data[] = {jTextField3.getText(), jTextField4.getText(), jTextField5.getText()};
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(data);
            JOptionPane.showMessageDialog(this, "Add Data Successfully...! !");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");

        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // Delete row on list jTable:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getRowCount() == 1) {
            model.removeRow(jTable1.getSelectedRow());
        } else {
            if (jTable1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select Single Row for delete.");
            }
        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        try {
            // Delete List in data:
            java.sql.Connection con = MyConnection.getConnection();
            int row = jTable2.getSelectedRow();
            String value = (jTable2.getModel().getValueAt(row, 0).toString());
            String sqlc = "Delete from List where foodID=" + value;
            PreparedStatement pmt = con.prepareStatement(sqlc);
            pmt.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Delete successfully!");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // Display list:
        if (jTextField6.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Date !");
        } else {
            try {
                jTable2.setVisible(true);
                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("FoodID");
                vcthead.add("FoodName");
                vcthead.add("FLDate");
                vcthead.add("Quantity");
                java.sql.Connection con = MyConnection.getConnection();
                String sqlf = "select Food.FoodID, Food.FoodName, List.FLDate, List.Quantity from Food, List where Food.FoodID = List.FoodID and FLDate = ?";
                PreparedStatement pmf = con.prepareStatement(sqlf);
                pmf.setDate(1, Date.valueOf(jTextField6.getText()));
                ResultSet rsa = pmf.executeQuery();
                while (rsa.next()) {
                    Vector n = new Vector();
                    n.add(rsa.getString("FoodID"));
                    n.add(rsa.getString("FoodName"));
                    n.add(rsa.getString("FLDate"));
                    n.add(rsa.getString("Quantity"));
                    vctdata.add(n);
                }
                jTable2.setModel(new DefaultTableModel(vctdata, vcthead));
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // edit foodlistnextDay in jtable:
        if (jTextField7.getText().equals("") || jTextField8.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter All Data !");
        } else {
            String data[] = {jTextField7.getText(), jTextField8.getText()};
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.addRow(data);
            JOptionPane.showMessageDialog(this, "Add Data Successfully...! !");
            //jTextField7.setText("");
            //jTextField8.setText("");
        }
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // Save foodlist next day data to SQL:
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is empty");
        } else {

            String flDate;
            String employeeID;
            try {
                java.sql.Connection con = MyConnection.getConnection();
                String query = "insert into FoodlistNextDays(FLDate, EmployeeID) values (?,?)";

                for (int i = 0; i < model.getRowCount(); i++) {
                    flDate = model.getValueAt(i, 0).toString();
                    employeeID = model.getValueAt(i, 1).toString();
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setDate(1, Date.valueOf(flDate));
                    ps.setString(2, employeeID);
                    ps.execute();
                }
                //Success msg
                JOptionPane.showMessageDialog(this, "Data insert success");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // Delete row on jTable FoodlistNextDay:
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        if (model.getRowCount() == 1) {
            model.removeRow(jTable3.getSelectedRow());
        } else {
            if (jTable3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select Single Row for delete.");
            }
        }
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // Display list:
        if (jTextField7.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Date !");
        } else {
            try {
                jTable3.setVisible(true);
                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("FLDate");
                vcthead.add("EmployeeID");
                java.sql.Connection con = MyConnection.getConnection();
                String sqlf = "select * from FoodlistNextDays where FLDate = ?";
                PreparedStatement pmf = con.prepareStatement(sqlf);
                pmf.setDate(1, Date.valueOf(jTextField7.getText()));
                ResultSet rsa = pmf.executeQuery();
                while (rsa.next()) {
                    Vector n = new Vector();
                    n.add(rsa.getString("FLDate"));
                    n.add(rsa.getString("EmployeeID"));
                    vctdata.add(n);
                }
                jTable3.setModel(new DefaultTableModel(vctdata, vcthead));
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        try {
            // Delete foodlistNext week in data:
            java.sql.Connection con = MyConnection.getConnection();
            int row = jTable3.getSelectedRow();
            String value = (jTable3.getModel().getValueAt(row, 0).toString());
            String sqlc = "Delete from FoodlistNextDays where FLDate= ?";
            PreparedStatement pmt = con.prepareStatement(sqlc);
            pmt.setDate(1, Date.valueOf(jTextField7.getText()));
            pmt.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Delete successfully!");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton60ActionPerformed

    private void Btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn43ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(false);
        JF13.setVisible(false);
        JF14.setVisible(false);
        JF15.setVisible(false);
        JF16.setVisible(false);
        JF39.setVisible(true);
    }//GEN-LAST:event_Btn43ActionPerformed

    private void Btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn44ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(true);
        JF13.setVisible(false);
        JF14.setVisible(false);
        JF15.setVisible(false);
        JF16.setVisible(false);
        JF39.setVisible(false);
    }//GEN-LAST:event_Btn44ActionPerformed

    private void Btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn45ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(false);
        JF13.setVisible(true);
        JF14.setVisible(false);
        JF15.setVisible(false);
        JF16.setVisible(false);
        JF39.setVisible(false);
    }//GEN-LAST:event_Btn45ActionPerformed

    private void Btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn46ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(false);
        JF13.setVisible(false);
        JF14.setVisible(true);
        JF15.setVisible(false);
        JF16.setVisible(false);
        JF39.setVisible(false);
    }//GEN-LAST:event_Btn46ActionPerformed

    private void Btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn47ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(false);
        JF13.setVisible(false);
        JF14.setVisible(false);
        JF15.setVisible(true);
        JF16.setVisible(false);
        JF39.setVisible(false);
    }//GEN-LAST:event_Btn47ActionPerformed

    private void Btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn48ActionPerformed
        // TODO add your handling code here:
        JF12.setVisible(false);
        JF13.setVisible(false);
        JF14.setVisible(false);
        JF15.setVisible(false);
        JF16.setVisible(true);
        JF39.setVisible(false);
    }//GEN-LAST:event_Btn48ActionPerformed

    private void jTableCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCSMouseClicked
        // chưa ok
        int Click = jTableCS.getSelectedRow();
        TableModel model = jTableCS.getModel();
        txtCsID.setText(model.getValueAt(Click, 0).toString());
        txtCsName.setText(model.getValueAt(Click, 1).toString());
        txtCsPhone.setText(model.getValueAt(Click, 2).toString());
        txtCsBirthday.setText(model.getValueAt(Click, 3).toString());
        //((JTextField)txtCsBirthday.getDateEditor().getUiComponent()).setText(model.getValueAt(Click,3).toString());
        txtCsIN.setText(model.getValueAt(Click, 4).toString());
        txtCsPoint.setText(model.getValueAt(Click, 5).toString());
        btnUpCs.setEnabled(true);
        btnDeletCs.setEnabled(true);

        LoadMainF();

    }//GEN-LAST:event_jTableCSMouseClicked

    private void btnAddCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCsActionPerformed
        // TODO add your handling code here:
        if (txtCsID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Customer ID");
        } else if (txtCsName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Customer Name");//chưa đc
        } else if (txtCsPhone.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Phone and enter all 10 numbers");
        } else if (txtCsBirthday.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Birth Day");
        } else if (txtCsIN.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Identification Number");
        } else if (txtCsPoint.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Point");
        } else {
            try {
                java.sql.Connection con = MyConnection.getConnection();
                String sql = "insert into Customer (CustomerID, CsName, CsPhone, CsBirthDay, CsIdentificationNumber,Point) values (?,?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtCsID.getText());
                ps.setString(2, txtCsName.getText());
                ps.setString(3, txtCsPhone.getText());
                ps.setString(4, txtCsBirthday.getText());
                ps.setString(5, txtCsIN.getText());
                ps.setString(6, txtCsPoint.getText());

                ps.executeUpdate();
                LoadMainF();
                int rowsAdded = ps.executeUpdate();
                if (rowsAdded != 0) {
                    JOptionPane.showMessageDialog(null, "New customer is added!");
                }
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
                //JOptionPane.showMessageDialog(this, ex.getMessage());
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddCsActionPerformed

    private void btnUpCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpCsActionPerformed
        // TODO add your handling code here:
        int Click = jTableCS.getSelectedRow();
        TableModel model = jTableCS.getModel();
        try {

            java.sql.Connection con = MyConnection.getConnection();
            String sql = "update Customer set CsName=?,CsPhone=?, CsBirthDay=?, CsIdentificationNumber=?, Point=?"
                    + "  where CustomerID=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(6, txtCsID.getText());
            ps.setString(1, txtCsName.getText());
            ps.setString(2, txtCsPhone.getText());
            ps.setDate(3, Date.valueOf(txtCsBirthday.getText()));
            ps.setString(4, txtCsIN.getText());
            ps.setString(5, txtCsPoint.getText());

            ps.executeUpdate();
            LoadMainF();
            JOptionPane.showMessageDialog(null, "Customer is updated!");

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpCsActionPerformed

    private void btnDeletCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletCsActionPerformed
        // chua ok
        int Click = jTableCS.getSelectedRow();
        TableModel model = jTableCS.getModel();
        Click = JOptionPane.showConfirmDialog(null, "", "", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Customer WHERE CustomerID = ?";
            try {
                java.sql.Connection con = MyConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, String.valueOf(txtCsID.getText()));
                ps.executeUpdate();
                // display(sql);
                LoadMainF();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeletCsActionPerformed


    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtCsID.setText("");
        txtCsName.setText("");
        txtCsPhone.setText("");
        txtCsBirthday.setText("");
        txtCsIN.setText("");
        txtCsPoint.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

    }//GEN-LAST:event_btnSearchActionPerformed

    private void jBAddNewFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddNewFoodActionPerformed
        try {
            //Check not null
            String ggg = jCBFoodType.getSelectedItem().toString();
            if (jTFID.getText().equals("") || jTFName.getText().equals("") || ggg.equals("")) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Khong duoc bo trong thong tin");
                return;
            }

            //connection
            java.sql.Connection con = MyConnection.getConnection();

            //kiem tra Food ID
            String Fid = jTFID.getText();
            if (Fid.matches("F[0-9][0-9]") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (F[0-9][0-9])");
                return;
            }

            String queryFid = "select FoodID from Food where FoodID=?";
            PreparedStatement pm1 = con.prepareCall(queryFid);
            pm1.setString(1, jTFID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (jTFID.getText().equals(rsEmpID.getString("FoodID"))) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("ID Da ton tai");
                    return;
                }
            }

            //kiem tra Type
            String FTye = "";
            String item = jCBFoodType.getSelectedItem().toString();
            switch (item) {
                case "Appetizer":
                    FTye = "FT01";
                    break;
                case "MainCourse":
                    FTye = "FT02";
                    break;
                case "Dessert":
                    FTye = "FT03";
                    break;
                default:
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Add");
                    jTNewEmpAlertTitle.setText("Can not add");
                    jTNewEmpAlertDescription.setText("Please select Type");
                    return;
            }

            //kiem tra img 
            if (jLFImgLink.getText() == "") {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Please select Img");
                return;
            }
            //insert vao csdl
            String linkimg = jLFImgLink.getText();
            String sublinkimg = linkimg.substring(5);
            String insertNewFood = "insert into Food values (?,?,?,?,?)";
            PreparedStatement pm = con.prepareCall(insertNewFood);
            pm.setString(1, jTFID.getText());
            pm.setString(2, jTFName.getText());
            pm.setString(3, jTADescription.getText());
            pm.setString(4, FTye);
            pm.setString(5, sublinkimg);
            pm.executeUpdate();

            con.close();
            pm.close();

            ShowAllFood();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jBAddNewFoodActionPerformed

    private void jBFoodClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFoodClearActionPerformed
        jTFID.setText("");
        jTFName.setText("");
        jCBFoodType.setSelectedIndex(0);
        jTADescription.setText("");
        jLFImgLink.setText("");

        ImageIcon imageIcon = new ImageIcon("D:\\CP2096J06-G01-Project---Sem-2-Group-1-Buffet-Huong-Rung\\HuongRungBuffet\\src\\img\\default-image.jpg");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        jLFoodImg.setIcon(new ImageIcon(newimg));
    }//GEN-LAST:event_jBFoodClearActionPerformed

    private void jBSaveFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSaveFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSaveFoodActionPerformed

    private void jBSortFoodIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSortFoodIDActionPerformed
        try {
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("Name ");
            vcthead.add("Description");
            vcthead.add("Type");
            vcthead.add("Picture");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID order by FoodID";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jBSortFoodIDActionPerformed

    private void jbSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSortByNameActionPerformed
        try {
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("Name ");
            vcthead.add("Description");
            vcthead.add("Type");
            vcthead.add("Picture");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID order by FoodName";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jbSortByNameActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        try {
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("Name ");
            vcthead.add("Description");
            vcthead.add("Type");
            vcthead.add("Picture");
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID order by FTypeName";
            PreparedStatement pm = con.prepareCall(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jBSearchingFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSearchingFoodActionPerformed
        Vector vctdata = new Vector();
        Vector vcthead = new Vector();
        vcthead.add("FoodID");
        vcthead.add("Name ");
        vcthead.add("Description");
        vcthead.add("Type");
        vcthead.add("Picture");
        try {
            String find = "";
            if (jRSeachingFoodID.isSelected()) {
                find = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID where FoodID like ? order by FoodID";
            }
            if (jRSeachingFoodName.isSelected()) {
                find = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID where FoodName like ? order by FoodID";
            }
            Connection con = MyConnection.getConnection();
            PreparedStatement pm = con.prepareCall(find);
            pm.setString(1, ("%" + jTSeachingFood.getText() + "%"));
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jBSearchingFoodActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Vector vctdata = new Vector();
        Vector vcthead = new Vector();
        vcthead.add("FoodID");
        vcthead.add("Name ");
        vcthead.add("Description");
        vcthead.add("Type");
        vcthead.add("Picture");
        try {
            String find = "select FoodID, FoodName, FDescription, FTypeName, Picture from Food as a join FoodType as b on a.FoodTypeID = b.FoodTypeID order by FoodID";

            Connection con = MyConnection.getConnection();
            PreparedStatement pm = con.prepareCall(find);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));

                vctdata.add(vt);
            }
            jTShowFoodToManage.setModel(new DefaultTableModel(vctdata, vcthead));
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void initTable() {
        DefaultTableModel model = (DefaultTableModel) jTableCS.getModel();
        model.setColumnIdentifiers(new String[]{"Customer ID", "Customer Name", "Phone", "Birth Day", "Identification Number", "Point"});
        jTableCS.setModel(model);
    }

    private void LoadMainF() {
        try {
            java.sql.Connection con = MyConnection.getConnection();
            DefaultTableModel model = (DefaultTableModel) jTableCS.getModel();
            String sql = "select * from Customer";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("CustomerID"),
                    rs.getString("CsName"),
                    rs.getString("CsPhone"),
                    rs.getString("CsBirthDay"),
                    rs.getString("CsIdentificationNumber"),
                    rs.getString("Point")
                };
                model.addRow(row);
            }
            model.fireTableDataChanged();
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainF().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNewEmpBtn;
    private javax.swing.JButton Btn1;
    private javax.swing.JButton Btn10;
    private javax.swing.JButton Btn11;
    private javax.swing.JButton Btn12;
    private javax.swing.JButton Btn13;
    private javax.swing.JButton Btn14;
    private javax.swing.JButton Btn2;
    private javax.swing.JButton Btn21;
    private javax.swing.JButton Btn22;
    private javax.swing.JButton Btn23;
    private javax.swing.JButton Btn24;
    private javax.swing.JButton Btn25;
    private javax.swing.JButton Btn26;
    private javax.swing.JButton Btn27;
    private javax.swing.JButton Btn28;
    private javax.swing.JButton Btn3;
    private javax.swing.JButton Btn35;
    private javax.swing.JButton Btn36;
    private javax.swing.JButton Btn4;
    private javax.swing.JButton Btn43;
    private javax.swing.JButton Btn44;
    private javax.swing.JButton Btn45;
    private javax.swing.JButton Btn46;
    private javax.swing.JButton Btn47;
    private javax.swing.JButton Btn48;
    private javax.swing.JButton Btn5;
    private javax.swing.JButton Btn6;
    private javax.swing.JButton Btn7;
    private javax.swing.JButton Btn8;
    private javax.swing.JButton Btn9;
    private javax.swing.JComboBox<String> CBtEmpDepartment;
    private javax.swing.JButton ClearAllEmpTxtField;
    private javax.swing.JLabel CsB;
    private javax.swing.JLabel CsID;
    private javax.swing.JLabel CsIN;
    private javax.swing.JLabel CsName;
    private javax.swing.JLabel CsPhone;
    private javax.swing.JLabel CsPoint;
    private javax.swing.JButton CustomerDefaultsPoints;
    private javax.swing.JButton CustomerHightPoints;
    private javax.swing.JButton CustomerLowPoints;
    private javax.swing.JButton DeeleteEmpBtn;
    private javax.swing.JLabel EID;
    private javax.swing.JLabel EID1;
    private javax.swing.JLabel EID3;
    private javax.swing.JLabel EID5;
    private javax.swing.JLabel EID7;
    private javax.swing.JLabel EName;
    private javax.swing.JLabel EName1;
    private javax.swing.JLabel EName3;
    private javax.swing.JLabel EName5;
    private javax.swing.JLabel EName7;
    private javax.swing.JLabel EmpAddress;
    private javax.swing.JLabel EmpBirthDay;
    private javax.swing.JLabel EmpDepartmentID;
    private javax.swing.JLabel EmpFullname;
    private javax.swing.JLabel EmpID;
    private javax.swing.JLabel EmpIdentificationNumber;
    private javax.swing.JLabel EmpPassword;
    private javax.swing.JLabel EmpPhone;
    private javax.swing.JLabel EmpTitle;
    private javax.swing.JLabel EmpUserName;
    private javax.swing.JPanel JF10;
    private javax.swing.JPanel JF11;
    private javax.swing.JPanel JF12;
    private javax.swing.JPanel JF13;
    private javax.swing.JPanel JF14;
    private javax.swing.JPanel JF15;
    private javax.swing.JPanel JF16;
    private javax.swing.JPanel JF18;
    private javax.swing.JPanel JF20;
    private javax.swing.JPanel JF21;
    private javax.swing.JPanel JF22;
    private javax.swing.JPanel JF23;
    private javax.swing.JPanel JF24;
    private javax.swing.JPanel JF25;
    private javax.swing.JPanel JF32;
    private javax.swing.JPanel JF33;
    private javax.swing.JPanel JF34;
    private javax.swing.JPanel JF35;
    private javax.swing.JPanel JF36;
    private javax.swing.JPanel JF37;
    private javax.swing.JPanel JF39;
    private javax.swing.JPanel JF4;
    private javax.swing.JPanel JF5;
    private javax.swing.JPanel JF6;
    private javax.swing.JPanel JF7;
    private javax.swing.JPanel JF8;
    private javax.swing.JPanel JF9;
    private javax.swing.JPanel JFCustomerView;
    private javax.swing.JPanel JFEmpManage;
    private javax.swing.JPanel JFFoodManage;
    private javax.swing.JButton SaveEmpBtn;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel Title3;
    private javax.swing.JLabel Title5;
    private javax.swing.JLabel Title7;
    private javax.swing.JButton btnAddCs;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeletCs;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpCs;
    private javax.swing.ButtonGroup buttonGroupSearchingEmp;
    private javax.swing.ButtonGroup buttonGroupSearchingFood;
    private javax.swing.JButton jBAddNewFood;
    private javax.swing.JButton jBEmpSearchBy;
    private javax.swing.JButton jBFDelete;
    private javax.swing.JButton jBFImg;
    private javax.swing.JButton jBFoodClear;
    private javax.swing.JButton jBNewEmpAlertBtn;
    private javax.swing.JButton jBSaveFood;
    private javax.swing.JButton jBSearchingFood;
    private javax.swing.JButton jBSortFoodID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCBFoodType;
    private javax.swing.JDialog jDBar;
    private javax.swing.JDialog jDBep;
    private javax.swing.JDialog jDNewEmpAlert;
    private javax.swing.JDialog jDQuanLy;
    private javax.swing.JDialog jDThuNgan;
    private javax.swing.JDialog jDkho;
    private javax.swing.JLabel jLCustomerBirthday;
    private javax.swing.JLabel jLCustomerId;
    private javax.swing.JLabel jLCustomerIdenNum;
    private javax.swing.JLabel jLCustomerPhone;
    private javax.swing.JLabel jLCustomerPoint;
    private javax.swing.JLabel jLEmpTitle;
    private javax.swing.JLabel jLEmpTitle1;
    private javax.swing.JLabel jLEmpTitle2;
    private javax.swing.JLabel jLFDescription;
    private javax.swing.JLabel jLFID;
    private javax.swing.JLabel jLFImgLink;
    private javax.swing.JLabel jLFName;
    private javax.swing.JLabel jLFType;
    private javax.swing.JLabel jLFoodImg;
    private javax.swing.JLabel jLFullName;
    private javax.swing.JLabel jLSeachingFood;
    private javax.swing.JLabel jLSeachingFood1;
    private javax.swing.JLabel jLSearchingEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLshowCustomerBirthday;
    private javax.swing.JLabel jLshowCustomerId;
    private javax.swing.JLabel jLshowCustomerIdenNum;
    private javax.swing.JLabel jLshowCustomerPhone;
    private javax.swing.JLabel jLshowCustomerPoint;
    private javax.swing.JLabel jLshowFullName;
    private javax.swing.JPanel jPManagerControl;
    private javax.swing.JPanel jPNewEmpAlert;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRBtnEmpDepartments;
    private javax.swing.JRadioButton jRBtnEmpID;
    private javax.swing.JRadioButton jRBtnEmpUserName;
    private javax.swing.JRadioButton jRSeachingFoodID;
    private javax.swing.JRadioButton jRSeachingFoodName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollShowFoodToManage;
    private javax.swing.JScrollPane jScrollShowKhachHang;
    private javax.swing.JScrollPane jScrollShowNhanVien;
    private javax.swing.JScrollPane jShowCustomer;
    private javax.swing.JTextArea jTADescription;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFName;
    private javax.swing.JLabel jTNewEmpAlertDescription;
    private javax.swing.JLabel jTNewEmpAlertTitle;
    private javax.swing.JTextField jTSeachingFood;
    private javax.swing.JTextField jTSearchingByEmp;
    private javax.swing.JTable jTShowFoodToManage;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableCS;
    private javax.swing.JTable jTableShowKhachHang;
    private javax.swing.JTable jTableShowNhanVien;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jbSortByName;
    private javax.swing.JTextField txtCsBirthday;
    private javax.swing.JTextField txtCsID;
    private javax.swing.JTextField txtCsIN;
    private javax.swing.JTextField txtCsName;
    private javax.swing.JTextField txtCsPhone;
    private javax.swing.JTextField txtCsPoint;
    private javax.swing.JTextField txtEmpAddress;
    private javax.swing.JTextField txtEmpBirthDay;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JTextField txtEmpIdentification;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpPassword;
    private javax.swing.JTextField txtEmpPhone;
    private javax.swing.JTextField txtEmpTitle;
    private javax.swing.JTextField txtEmpUserName;
    private javax.swing.JTextField txtSearching;
    // End of variables declaration//GEN-END:variables
}
