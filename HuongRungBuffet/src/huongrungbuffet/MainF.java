/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huongrungbuffet;

import java.awt.Color;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Spinner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
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
        jBNewEmpAlertBtn.setText("OK");
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
                if (rs.getString("EmployeeID").equals("MN")) {
                    continue;
                }
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

    public void hienFoodAp(){
        List<JLabel> jLapImg= new ArrayList<>();
            jLapImg.add(jLapImg1);jLapImg.add(jLapImg2);jLapImg.add(jLapImg3);jLapImg.add(jLapImg4);jLapImg.add(jLapImg5);jLapImg.add(jLapImg6);jLapImg.add(jLapImg7);jLapImg.add(jLapImg8);jLapImg.add(jLapImg9);jLapImg.add(jLapImg10);
            jLapImg.add(jLapImg11);jLapImg.add(jLapImg12);jLapImg.add(jLapImg13);jLapImg.add(jLapImg14);jLapImg.add(jLapImg15);jLapImg.add(jLapImg16);jLapImg.add(jLapImg17);jLapImg.add(jLapImg18);jLapImg.add(jLapImg19);jLapImg.add(jLapImg20);
            jLapImg.add(jLapImg21);jLapImg.add(jLapImg22);jLapImg.add(jLapImg23);jLapImg.add(jLapImg24);jLapImg.add(jLapImg25);jLapImg.add(jLapImg26);jLapImg.add(jLapImg27);jLapImg.add(jLapImg28);jLapImg.add(jLapImg29);jLapImg.add(jLapImg30);
            jLapImg.add(jLapImg31);jLapImg.add(jLapImg32);jLapImg.add(jLapImg33);jLapImg.add(jLapImg34);jLapImg.add(jLapImg35);jLapImg.add(jLapImg36);jLapImg.add(jLapImg37);jLapImg.add(jLapImg38);jLapImg.add(jLapImg39);jLapImg.add(jLapImg40);
            
            List<JLabel> jLapID= new ArrayList<>();
            jLapID.add(jLapID1);jLapID.add(jLapID2);jLapID.add(jLapID3);jLapID.add(jLapID4);jLapID.add(jLapID5);jLapID.add(jLapID6);jLapID.add(jLapID7);jLapID.add(jLapID8);jLapID.add(jLapID9);jLapID.add(jLapID10);
            jLapID.add(jLapID11);jLapID.add(jLapID12);jLapID.add(jLapID13);jLapID.add(jLapID14);jLapID.add(jLapID15);jLapID.add(jLapID16);jLapID.add(jLapID17);jLapID.add(jLapID18);jLapID.add(jLapID19);jLapID.add(jLapID20);
            jLapID.add(jLapID21);jLapID.add(jLapID22);jLapID.add(jLapID23);jLapID.add(jLapID24);jLapID.add(jLapID25);jLapID.add(jLapID26);jLapID.add(jLapID27);jLapID.add(jLapID28);jLapID.add(jLapID29);jLapID.add(jLapID30);
            jLapID.add(jLapID31);jLapID.add(jLapID32);jLapID.add(jLapID33);jLapID.add(jLapID34);jLapID.add(jLapID35);jLapID.add(jLapID36);jLapID.add(jLapID37);jLapID.add(jLapID38);jLapID.add(jLapID39);jLapID.add(jLapID40);
            
            
            List<JLabel> jLapName= new ArrayList<>();
            jLapName.add(jLapName1);jLapName.add(jLapName2);jLapName.add(jLapName3);jLapName.add(jLapName4);jLapName.add(jLapName5);jLapName.add(jLapName6);jLapName.add(jLapName7);jLapName.add(jLapName8);jLapName.add(jLapName9);jLapName.add(jLapName10);
            jLapName.add(jLapName11);jLapName.add(jLapName12);jLapName.add(jLapName13);jLapName.add(jLapName14);jLapName.add(jLapName15);jLapName.add(jLapName16);jLapName.add(jLapName17);jLapName.add(jLapName18);jLapName.add(jLapName19);jLapName.add(jLapName20);
            jLapName.add(jLapName21);jLapName.add(jLapName22);jLapName.add(jLapName23);jLapName.add(jLapName24);jLapName.add(jLapName25);jLapName.add(jLapName26);jLapName.add(jLapName27);jLapName.add(jLapName28);jLapName.add(jLapName29);jLapName.add(jLapName30);
            jLapName.add(jLapName31);jLapName.add(jLapName32);jLapName.add(jLapName33);jLapName.add(jLapName34);jLapName.add(jLapName35);jLapName.add(jLapName36);jLapName.add(jLapName37);jLapName.add(jLapName38);jLapName.add(jLapName39);jLapName.add(jLapName40);
            
            List<JSpinner> jLapSpin= new ArrayList<>();
            jLapSpin.add(jLapSpin1);jLapSpin.add(jLapSpin2);jLapSpin.add(jLapSpin3);jLapSpin.add(jLapSpin4);jLapSpin.add(jLapSpin5);jLapSpin.add(jLapSpin6);jLapSpin.add(jLapSpin7);jLapSpin.add(jLapSpin8);jLapSpin.add(jLapSpin9);jLapSpin.add(jLapSpin10);
            jLapSpin.add(jLapSpin11);jLapSpin.add(jLapSpin12);jLapSpin.add(jLapSpin13);jLapSpin.add(jLapSpin14);jLapSpin.add(jLapSpin15);jLapSpin.add(jLapSpin16);jLapSpin.add(jLapSpin17);jLapSpin.add(jLapSpin18);jLapSpin.add(jLapSpin19);jLapSpin.add(jLapSpin20);
            jLapSpin.add(jLapSpin21);jLapSpin.add(jLapSpin22);jLapSpin.add(jLapSpin23);jLapSpin.add(jLapSpin24);jLapSpin.add(jLapSpin25);jLapSpin.add(jLapSpin26);jLapSpin.add(jLapSpin27);jLapSpin.add(jLapSpin28);jLapSpin.add(jLapSpin29);jLapSpin.add(jLapSpin30);
            jLapSpin.add(jLapSpin31);jLapSpin.add(jLapSpin32);jLapSpin.add(jLapSpin33);jLapSpin.add(jLapSpin34);jLapSpin.add(jLapSpin35);jLapSpin.add(jLapSpin36);jLapSpin.add(jLapSpin37);jLapSpin.add(jLapSpin38);jLapSpin.add(jLapSpin39);jLapSpin.add(jLapSpin40);
            
            ArrayList<String> LinkIngApList = new ArrayList<>();
            ArrayList<String> FoodIDAPList = new ArrayList<>();
            ArrayList<String> FoodNameAPList = new ArrayList<>();
            
            try {
            Connection con = MyConnection.getConnection();
            String find = "select FoodID, FoodName, Picture from Food where FoodTypeID = ?";
            PreparedStatement pm = con.prepareCall(find);
            pm.setString(1, "FT01");
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                
                LinkIngApList.add(rs.getString("Picture"));
                FoodIDAPList.add(rs.getString("FoodID"));
                FoodNameAPList.add(rs.getString("FoodName"));
                
            }
            
            for (int i = 0; i < 40; i++) {
                try {
                    ImageIcon imageIcon = new ImageIcon(String.valueOf(LinkIngApList.get(i)));
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
                    jLapImg.get(i).setIcon(new ImageIcon(newimg));
                    
                    jLapID.get(i).setText(FoodIDAPList.get(i));
                    jLapName.get(i).setText(FoodNameAPList.get(i));
                    ((JSpinner.DefaultEditor) jLapSpin.get(i).getEditor()).getTextField().setEditable(false);
                    jLapImg.get(i).setVisible(true);
                    jLapID.get(i).setVisible(true);
                    jLapName.get(i).setVisible(true);
                    jLapSpin.get(i).setVisible(true);

                } catch (Exception e) {
                    jLapImg.get(i).setVisible(false);
                    jLapID.get(i).setVisible(false);
                    jLapName.get(i).setVisible(false);
                    jLapSpin.get(i).setVisible(false);
                    continue;
                }

            }con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void hienFoodMD(){
        List<JLabel> jLMDImg= new ArrayList<>();
            jLMDImg.add(jLMDImg1);jLMDImg.add(jLMDImg2);jLMDImg.add(jLMDImg3);jLMDImg.add(jLMDImg4);jLMDImg.add(jLMDImg5);jLMDImg.add(jLMDImg6);jLMDImg.add(jLMDImg7);jLMDImg.add(jLMDImg8);jLMDImg.add(jLMDImg9);jLMDImg.add(jLMDImg10);
            jLMDImg.add(jLMDImg11);jLMDImg.add(jLMDImg12);jLMDImg.add(jLMDImg13);jLMDImg.add(jLMDImg14);jLMDImg.add(jLMDImg15);jLMDImg.add(jLMDImg16);jLMDImg.add(jLMDImg17);jLMDImg.add(jLMDImg18);jLMDImg.add(jLMDImg19);jLMDImg.add(jLMDImg20);
            jLMDImg.add(jLMDImg21);jLMDImg.add(jLMDImg22);jLMDImg.add(jLMDImg23);jLMDImg.add(jLMDImg24);jLMDImg.add(jLMDImg25);jLMDImg.add(jLMDImg26);jLMDImg.add(jLMDImg27);jLMDImg.add(jLMDImg28);jLMDImg.add(jLMDImg29);jLMDImg.add(jLMDImg30);
            jLMDImg.add(jLMDImg31);jLMDImg.add(jLMDImg32);jLMDImg.add(jLMDImg33);jLMDImg.add(jLMDImg34);jLMDImg.add(jLMDImg35);jLMDImg.add(jLMDImg36);jLMDImg.add(jLMDImg37);jLMDImg.add(jLMDImg38);jLMDImg.add(jLMDImg39);jLMDImg.add(jLMDImg40);
            
            List<JLabel> jLMNID= new ArrayList<>();
            jLMNID.add(jLMNID1);jLMNID.add(jLMNID2);jLMNID.add(jLMNID3);jLMNID.add(jLMNID4);jLMNID.add(jLMNID5);jLMNID.add(jLMNID6);jLMNID.add(jLMNID7);jLMNID.add(jLMNID8);jLMNID.add(jLMNID9);jLMNID.add(jLMNID10);
            jLMNID.add(jLMNID11);jLMNID.add(jLMNID12);jLMNID.add(jLMNID13);jLMNID.add(jLMNID14);jLMNID.add(jLMNID15);jLMNID.add(jLMNID16);jLMNID.add(jLMNID17);jLMNID.add(jLMNID18);jLMNID.add(jLMNID19);jLMNID.add(jLMNID20);
            jLMNID.add(jLMNID21);jLMNID.add(jLMNID22);jLMNID.add(jLMNID23);jLMNID.add(jLMNID24);jLMNID.add(jLMNID25);jLMNID.add(jLMNID26);jLMNID.add(jLMNID27);jLMNID.add(jLMNID28);jLMNID.add(jLMNID29);jLMNID.add(jLMNID30);
            jLMNID.add(jLMNID31);jLMNID.add(jLMNID32);jLMNID.add(jLMNID33);jLMNID.add(jLMNID34);jLMNID.add(jLMNID35);jLMNID.add(jLMNID36);jLMNID.add(jLMNID37);jLMNID.add(jLMNID38);jLMNID.add(jLMNID39);jLMNID.add(jLMNID40);
            
            
            List<JLabel> jLMNName= new ArrayList<>();
            jLMNName.add(jLMNName1);jLMNName.add(jLMNName2);jLMNName.add(jLMNName3);jLMNName.add(jLMNName4);jLMNName.add(jLMNName5);jLMNName.add(jLMNName6);jLMNName.add(jLMNName7);jLMNName.add(jLMNName8);jLMNName.add(jLMNName9);jLMNName.add(jLMNName10);
            jLMNName.add(jLMNName11);jLMNName.add(jLMNName12);jLMNName.add(jLMNName13);jLMNName.add(jLMNName14);jLMNName.add(jLMNName15);jLMNName.add(jLMNName16);jLMNName.add(jLMNName17);jLMNName.add(jLMNName18);jLMNName.add(jLMNName19);jLMNName.add(jLMNName20);
            jLMNName.add(jLMNName21);jLMNName.add(jLMNName22);jLMNName.add(jLMNName23);jLMNName.add(jLMNName24);jLMNName.add(jLMNName25);jLMNName.add(jLMNName26);jLMNName.add(jLMNName27);jLMNName.add(jLMNName28);jLMNName.add(jLMNName29);jLMNName.add(jLMNName30);
            jLMNName.add(jLMNName31);jLMNName.add(jLMNName32);jLMNName.add(jLMNName33);jLMNName.add(jLMNName34);jLMNName.add(jLMNName35);jLMNName.add(jLMNName36);jLMNName.add(jLMNName37);jLMNName.add(jLMNName38);jLMNName.add(jLMNName39);jLMNName.add(jLMNName40);
            
            
            
            
            List<JSpinner> jLMNSpin= new ArrayList<>();
            jLMNSpin.add(jLMNSpin1);jLMNSpin.add(jLMNSpin2);jLMNSpin.add(jLMNSpin3);jLMNSpin.add(jLMNSpin4);jLMNSpin.add(jLMNSpin5);jLMNSpin.add(jLMNSpin6);jLMNSpin.add(jLMNSpin7);jLMNSpin.add(jLMNSpin8);jLMNSpin.add(jLMNSpin9);jLMNSpin.add(jLMNSpin10);
            jLMNSpin.add(jLMNSpin11);jLMNSpin.add(jLMNSpin12);jLMNSpin.add(jLMNSpin13);jLMNSpin.add(jLMNSpin14);jLMNSpin.add(jLMNSpin15);jLMNSpin.add(jLMNSpin16);jLMNSpin.add(jLMNSpin17);jLMNSpin.add(jLMNSpin18);jLMNSpin.add(jLMNSpin19);jLMNSpin.add(jLMNSpin20);
            jLMNSpin.add(jLMNSpin21);jLMNSpin.add(jLMNSpin22);jLMNSpin.add(jLMNSpin23);jLMNSpin.add(jLMNSpin24);jLMNSpin.add(jLMNSpin25);jLMNSpin.add(jLMNSpin26);jLMNSpin.add(jLMNSpin27);jLMNSpin.add(jLMNSpin28);jLMNSpin.add(jLMNSpin29);jLMNSpin.add(jLMNSpin30);
            jLMNSpin.add(jLMNSpin31);jLMNSpin.add(jLMNSpin32);jLMNSpin.add(jLMNSpin33);jLMNSpin.add(jLMNSpin34);jLMNSpin.add(jLMNSpin35);jLMNSpin.add(jLMNSpin36);jLMNSpin.add(jLMNSpin37);jLMNSpin.add(jLMNSpin38);jLMNSpin.add(jLMNSpin39);jLMNSpin.add(jLMNSpin40);
            
            
            ArrayList<String> LinkIngApList = new ArrayList<>();
            ArrayList<String> FoodIDAPList = new ArrayList<>();
            ArrayList<String> FoodNameAPList = new ArrayList<>();
            
            try {
            Connection con = MyConnection.getConnection();
            String find = "select FoodID, FoodName, Picture from Food where FoodTypeID = ?";
            PreparedStatement pm = con.prepareCall(find);
            pm.setString(1, "FT02");
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                
                LinkIngApList.add(rs.getString("Picture"));
                FoodIDAPList.add(rs.getString("FoodID"));
                FoodNameAPList.add(rs.getString("FoodName"));
                
            }
            
            for (int i = 0; i < 40; i++) {
                try {
                    ImageIcon imageIcon = new ImageIcon(String.valueOf(LinkIngApList.get(i)));
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
                    jLMDImg.get(i).setIcon(new ImageIcon(newimg));
                    
                    jLMNID.get(i).setText(FoodIDAPList.get(i));
                    jLMNName.get(i).setText(FoodNameAPList.get(i));
                    ((JSpinner.DefaultEditor) jLMNSpin.get(i).getEditor()).getTextField().setEditable(false);
                    jLMDImg.get(i).setVisible(true);
                    jLMNID.get(i).setVisible(true);
                    jLMNName.get(i).setVisible(true);
                    jLMNSpin.get(i).setVisible(true);

                } catch (Exception e) {
                    jLMDImg.get(i).setVisible(false);
                    jLMNID.get(i).setVisible(false);
                    jLMNName.get(i).setVisible(false);
                    jLMNSpin.get(i).setVisible(false);
                    continue;
                }

            }con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void hienFoodDS(){
        List<JLabel> jLDSImg= new ArrayList<>();
            jLDSImg.add(jLDSImg1);jLDSImg.add(jLDSImg2);jLDSImg.add(jLDSImg3);jLDSImg.add(jLDSImg4);jLDSImg.add(jLDSImg5);jLDSImg.add(jLDSImg6);jLDSImg.add(jLDSImg7);jLDSImg.add(jLDSImg8);jLDSImg.add(jLDSImg9);jLDSImg.add(jLDSImg10);
            jLDSImg.add(jLDSImg11);jLDSImg.add(jLDSImg12);jLDSImg.add(jLDSImg13);jLDSImg.add(jLDSImg14);jLDSImg.add(jLDSImg15);jLDSImg.add(jLDSImg16);jLDSImg.add(jLDSImg17);jLDSImg.add(jLDSImg18);jLDSImg.add(jLDSImg19);jLDSImg.add(jLDSImg20);
            jLDSImg.add(jLDSImg21);jLDSImg.add(jLDSImg22);jLDSImg.add(jLDSImg23);jLDSImg.add(jLDSImg24);jLDSImg.add(jLDSImg25);jLDSImg.add(jLDSImg26);jLDSImg.add(jLDSImg27);jLDSImg.add(jLDSImg28);jLDSImg.add(jLDSImg29);jLDSImg.add(jLDSImg30);
            jLDSImg.add(jLDSImg31);jLDSImg.add(jLDSImg32);jLDSImg.add(jLDSImg33);jLDSImg.add(jLDSImg34);jLDSImg.add(jLDSImg35);jLDSImg.add(jLDSImg36);jLDSImg.add(jLDSImg37);jLDSImg.add(jLDSImg38);jLDSImg.add(jLDSImg39);jLDSImg.add(jLDSImg40);
            
            List<JLabel> jLDSID= new ArrayList<>();
            jLDSID.add(jLDSID1);jLDSID.add(jLDSID2);jLDSID.add(jLDSID3);jLDSID.add(jLDSID4);jLDSID.add(jLDSID5);jLDSID.add(jLDSID6);jLDSID.add(jLDSID7);jLDSID.add(jLDSID8);jLDSID.add(jLDSID9);jLDSID.add(jLDSID10);
            jLDSID.add(jLDSID11);jLDSID.add(jLDSID12);jLDSID.add(jLDSID13);jLDSID.add(jLDSID14);jLDSID.add(jLDSID15);jLDSID.add(jLDSID16);jLDSID.add(jLDSID17);jLDSID.add(jLDSID18);jLDSID.add(jLDSID19);jLDSID.add(jLDSID20);
            jLDSID.add(jLDSID21);jLDSID.add(jLDSID22);jLDSID.add(jLDSID23);jLDSID.add(jLDSID24);jLDSID.add(jLDSID25);jLDSID.add(jLDSID26);jLDSID.add(jLDSID27);jLDSID.add(jLDSID28);jLDSID.add(jLDSID29);jLDSID.add(jLDSID30);
            jLDSID.add(jLDSID31);jLDSID.add(jLDSID32);jLDSID.add(jLDSID33);jLDSID.add(jLDSID34);jLDSID.add(jLDSID35);jLDSID.add(jLDSID36);jLDSID.add(jLDSID37);jLDSID.add(jLDSID38);jLDSID.add(jLDSID39);jLDSID.add(jLDSID40);
            
            
            List<JLabel> jLDSName= new ArrayList<>();
            jLDSName.add(jLDSName1);jLDSName.add(jLDSName2);jLDSName.add(jLDSName3);jLDSName.add(jLDSName4);jLDSName.add(jLDSName5);jLDSName.add(jLDSName6);jLDSName.add(jLDSName7);jLDSName.add(jLDSName8);jLDSName.add(jLDSName9);jLDSName.add(jLDSName10);
            jLDSName.add(jLDSName11);jLDSName.add(jLDSName12);jLDSName.add(jLDSName13);jLDSName.add(jLDSName14);jLDSName.add(jLDSName15);jLDSName.add(jLDSName16);jLDSName.add(jLDSName17);jLDSName.add(jLDSName18);jLDSName.add(jLDSName19);jLDSName.add(jLDSName20);
            jLDSName.add(jLDSName21);jLDSName.add(jLDSName22);jLDSName.add(jLDSName23);jLDSName.add(jLDSName24);jLDSName.add(jLDSName25);jLDSName.add(jLDSName26);jLDSName.add(jLDSName27);jLDSName.add(jLDSName28);jLDSName.add(jLDSName29);jLDSName.add(jLDSName30);
            jLDSName.add(jLDSName31);jLDSName.add(jLDSName32);jLDSName.add(jLDSName33);jLDSName.add(jLDSName34);jLDSName.add(jLDSName35);jLDSName.add(jLDSName36);jLDSName.add(jLDSName37);jLDSName.add(jLDSName38);jLDSName.add(jLDSName39);jLDSName.add(jLDSName40);
            
            
            List<JSpinner> jDSSpin= new ArrayList<>();
            jDSSpin.add(jLDSpin1);jDSSpin.add(jLDSpin2);jDSSpin.add(jLDSpin3);jDSSpin.add(jLDSpin4);jDSSpin.add(jLDSpin5);jDSSpin.add(jLDSpin6);jDSSpin.add(jLDSpin7);jDSSpin.add(jLDSpin8);jDSSpin.add(jLDSpin9);jDSSpin.add(jLDSpin10);
            jDSSpin.add(jLDSpin11);jDSSpin.add(jLDSpin12);jDSSpin.add(jLDSpin13);jDSSpin.add(jLDSpin14);jDSSpin.add(jLDSpin15);jDSSpin.add(jLDSpin16);jDSSpin.add(jLDSpin17);jDSSpin.add(jLDSpin18);jDSSpin.add(jLDSpin19);jDSSpin.add(jLDSpin20);
            jDSSpin.add(jLDSpin21);jDSSpin.add(jLDSpin22);jDSSpin.add(jLDSpin23);jDSSpin.add(jLDSpin24);jDSSpin.add(jLDSpin25);jDSSpin.add(jLDSpin26);jDSSpin.add(jLDSpin27);jDSSpin.add(jLDSpin28);jDSSpin.add(jLDSpin29);jDSSpin.add(jLDSpin30);
            jDSSpin.add(jLDSpin31);jDSSpin.add(jLDSpin32);jDSSpin.add(jLDSpin33);jDSSpin.add(jLDSpin34);jDSSpin.add(jLDSpin35);jDSSpin.add(jLDSpin36);jDSSpin.add(jLDSpin37);jDSSpin.add(jLDSpin38);jDSSpin.add(jLDSpin39);jDSSpin.add(jLDSpin40);
            
            
            
            ArrayList<String> LinkIngApList = new ArrayList<>();
            ArrayList<String> FoodIDAPList = new ArrayList<>();
            ArrayList<String> FoodNameAPList = new ArrayList<>();
            
            try {
            Connection con = MyConnection.getConnection();
            String find = "select FoodID, FoodName, Picture from Food where FoodTypeID = ?";
            PreparedStatement pm = con.prepareCall(find);
            pm.setString(1, "FT03");
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                
                LinkIngApList.add(rs.getString("Picture"));
                FoodIDAPList.add(rs.getString("FoodID"));
                FoodNameAPList.add(rs.getString("FoodName"));
                
            }
            
            for (int i = 0; i < 40; i++) {
                try {
                    ImageIcon imageIcon = new ImageIcon(String.valueOf(LinkIngApList.get(i)));
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
                    jLDSImg.get(i).setIcon(new ImageIcon(newimg));
                    
                    jLDSID.get(i).setText(FoodIDAPList.get(i));
                    jLDSName.get(i).setText(FoodNameAPList.get(i));
                    ((JSpinner.DefaultEditor) jDSSpin.get(i).getEditor()).getTextField().setEditable(false);
                    jLDSImg.get(i).setVisible(true);
                    jLDSID.get(i).setVisible(true);
                    jLDSName.get(i).setVisible(true);
                    jDSSpin.get(i).setVisible(true);
                    

                } catch (Exception e) {
                    jLDSImg.get(i).setVisible(false);
                    jLDSID.get(i).setVisible(false);
                    jLDSName.get(i).setVisible(false);
                    jDSSpin.get(i).setVisible(false);
                    continue;
                }

            }con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
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
        jbManageEmp = new javax.swing.JButton();
        jbVieCustomer = new javax.swing.JButton();
        jbManageFood = new javax.swing.JButton();
        BtThucDonTuan = new javax.swing.JButton();
        Btn5 = new javax.swing.JButton();
        Btn6 = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        EName = new javax.swing.JLabel();
        EID = new javax.swing.JLabel();
        jBnDangXuat = new javax.swing.JButton();
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
        jBEmpSearchBy1 = new javax.swing.JButton();
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
        jbSortByType = new javax.swing.JButton();
        jbShowDefaultFood = new javax.swing.JButton();
        JFNewFoodListWeek = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTThucDonTuan = new javax.swing.JTable();
        jLThucDonTuan = new javax.swing.JLabel();
        jDCTaoThucDonTuan = new com.toedter.calendar.JDateChooser();
        jLNgayBatDauTD = new javax.swing.JLabel();
        jBTaoThucDonTuan = new javax.swing.JButton();
        TimNgayBatDauTD = new javax.swing.JLabel();
        jDCTimthucDonTuan = new com.toedter.calendar.JDateChooser();
        jBTimKieMThucDonTuan = new javax.swing.JButton();
        FoodTypeDP = new javax.swing.JPanel();
        jTDisplayType = new javax.swing.JTabbedPane();
        jSPAppetizer = new javax.swing.JScrollPane();
        jPFAppetizer = new javax.swing.JPanel();
        jLapImg4 = new javax.swing.JLabel();
        jLapImg2 = new javax.swing.JLabel();
        jLapImg3 = new javax.swing.JLabel();
        jLapImg1 = new javax.swing.JLabel();
        jLapID1 = new javax.swing.JLabel();
        jLapID2 = new javax.swing.JLabel();
        jLapID4 = new javax.swing.JLabel();
        jLapID3 = new javax.swing.JLabel();
        jLapName4 = new javax.swing.JLabel();
        jLapName3 = new javax.swing.JLabel();
        jLapName2 = new javax.swing.JLabel();
        jLapName1 = new javax.swing.JLabel();
        jLapSpin1 = new javax.swing.JSpinner();
        jLapSpin2 = new javax.swing.JSpinner();
        jLapSpin3 = new javax.swing.JSpinner();
        jLapSpin4 = new javax.swing.JSpinner();
        jLapSpin7 = new javax.swing.JSpinner();
        jLapName7 = new javax.swing.JLabel();
        jLapID7 = new javax.swing.JLabel();
        jLapImg7 = new javax.swing.JLabel();
        jLapImg5 = new javax.swing.JLabel();
        jLapID8 = new javax.swing.JLabel();
        jLapName8 = new javax.swing.JLabel();
        jLapSpin8 = new javax.swing.JSpinner();
        jLapSpin6 = new javax.swing.JSpinner();
        jLapSpin5 = new javax.swing.JSpinner();
        jLapName5 = new javax.swing.JLabel();
        jLapID5 = new javax.swing.JLabel();
        jLapImg8 = new javax.swing.JLabel();
        jLapImg6 = new javax.swing.JLabel();
        jLapID6 = new javax.swing.JLabel();
        jLapName6 = new javax.swing.JLabel();
        jLapSpin11 = new javax.swing.JSpinner();
        jLapSpin10 = new javax.swing.JSpinner();
        jLapName10 = new javax.swing.JLabel();
        jLapID10 = new javax.swing.JLabel();
        jLapImg10 = new javax.swing.JLabel();
        jLapImg9 = new javax.swing.JLabel();
        jLapID9 = new javax.swing.JLabel();
        jLapName9 = new javax.swing.JLabel();
        jLapSpin9 = new javax.swing.JSpinner();
        jLapImg11 = new javax.swing.JLabel();
        jLapID11 = new javax.swing.JLabel();
        jLapName11 = new javax.swing.JLabel();
        jLapID12 = new javax.swing.JLabel();
        jLapImg12 = new javax.swing.JLabel();
        jLapName12 = new javax.swing.JLabel();
        jLapSpin12 = new javax.swing.JSpinner();
        jLapImg13 = new javax.swing.JLabel();
        jLapImg14 = new javax.swing.JLabel();
        jLapImg16 = new javax.swing.JLabel();
        jLapImg15 = new javax.swing.JLabel();
        jLapID13 = new javax.swing.JLabel();
        jLapID14 = new javax.swing.JLabel();
        jLapID16 = new javax.swing.JLabel();
        jLapID15 = new javax.swing.JLabel();
        jLapName13 = new javax.swing.JLabel();
        jLapName14 = new javax.swing.JLabel();
        jLapName15 = new javax.swing.JLabel();
        jLapName16 = new javax.swing.JLabel();
        jLapSpin13 = new javax.swing.JSpinner();
        jLapSpin14 = new javax.swing.JSpinner();
        jLapSpin15 = new javax.swing.JSpinner();
        jLapSpin16 = new javax.swing.JSpinner();
        jLapImg17 = new javax.swing.JLabel();
        jLapImg18 = new javax.swing.JLabel();
        jLapImg20 = new javax.swing.JLabel();
        jLapImg19 = new javax.swing.JLabel();
        jLapID17 = new javax.swing.JLabel();
        jLapID18 = new javax.swing.JLabel();
        jLapName17 = new javax.swing.JLabel();
        jLapName18 = new javax.swing.JLabel();
        jLapID19 = new javax.swing.JLabel();
        jLapID20 = new javax.swing.JLabel();
        jLapName19 = new javax.swing.JLabel();
        jLapName20 = new javax.swing.JLabel();
        jLapSpin17 = new javax.swing.JSpinner();
        jLapSpin18 = new javax.swing.JSpinner();
        jLapSpin19 = new javax.swing.JSpinner();
        jLapSpin20 = new javax.swing.JSpinner();
        jLapImg21 = new javax.swing.JLabel();
        jLapID21 = new javax.swing.JLabel();
        jLapName21 = new javax.swing.JLabel();
        jLapImg22 = new javax.swing.JLabel();
        jLapID22 = new javax.swing.JLabel();
        jLapName22 = new javax.swing.JLabel();
        jLapSpin22 = new javax.swing.JSpinner();
        jLapSpin21 = new javax.swing.JSpinner();
        jLapImg24 = new javax.swing.JLabel();
        jLapName24 = new javax.swing.JLabel();
        jLapName23 = new javax.swing.JLabel();
        jLapID23 = new javax.swing.JLabel();
        jLapSpin23 = new javax.swing.JSpinner();
        jLapID24 = new javax.swing.JLabel();
        jLapImg23 = new javax.swing.JLabel();
        jLapSpin24 = new javax.swing.JSpinner();
        jLapSpin26 = new javax.swing.JSpinner();
        jLapImg26 = new javax.swing.JLabel();
        jLapName25 = new javax.swing.JLabel();
        jLapSpin25 = new javax.swing.JSpinner();
        jLapID26 = new javax.swing.JLabel();
        jLapID25 = new javax.swing.JLabel();
        jLapImg25 = new javax.swing.JLabel();
        jLapName26 = new javax.swing.JLabel();
        jLapSpin28 = new javax.swing.JSpinner();
        jLapImg28 = new javax.swing.JLabel();
        jLapName27 = new javax.swing.JLabel();
        jLapSpin27 = new javax.swing.JSpinner();
        jLapID28 = new javax.swing.JLabel();
        jLapID27 = new javax.swing.JLabel();
        jLapImg27 = new javax.swing.JLabel();
        jLapName28 = new javax.swing.JLabel();
        jLapSpin30 = new javax.swing.JSpinner();
        jLapImg30 = new javax.swing.JLabel();
        jLapName29 = new javax.swing.JLabel();
        jLapSpin29 = new javax.swing.JSpinner();
        jLapID30 = new javax.swing.JLabel();
        jLapID29 = new javax.swing.JLabel();
        jLapImg29 = new javax.swing.JLabel();
        jLapName30 = new javax.swing.JLabel();
        jLapSpin32 = new javax.swing.JSpinner();
        jLapImg32 = new javax.swing.JLabel();
        jLapName31 = new javax.swing.JLabel();
        jLapSpin31 = new javax.swing.JSpinner();
        jLapID32 = new javax.swing.JLabel();
        jLapID31 = new javax.swing.JLabel();
        jLapImg31 = new javax.swing.JLabel();
        jLapName32 = new javax.swing.JLabel();
        jLapImg33 = new javax.swing.JLabel();
        jLapID33 = new javax.swing.JLabel();
        jLapName33 = new javax.swing.JLabel();
        jLapSpin33 = new javax.swing.JSpinner();
        jLapImg34 = new javax.swing.JLabel();
        jLapID34 = new javax.swing.JLabel();
        jLapName34 = new javax.swing.JLabel();
        jLapSpin34 = new javax.swing.JSpinner();
        jLapImg35 = new javax.swing.JLabel();
        jLapID35 = new javax.swing.JLabel();
        jLapName35 = new javax.swing.JLabel();
        jLapSpin35 = new javax.swing.JSpinner();
        jLapImg36 = new javax.swing.JLabel();
        jLapID36 = new javax.swing.JLabel();
        jLapName36 = new javax.swing.JLabel();
        jLapSpin36 = new javax.swing.JSpinner();
        jLapImg38 = new javax.swing.JLabel();
        jLapImg37 = new javax.swing.JLabel();
        jLapID37 = new javax.swing.JLabel();
        jLapName37 = new javax.swing.JLabel();
        jLapSpin37 = new javax.swing.JSpinner();
        jLapName38 = new javax.swing.JLabel();
        jLapID38 = new javax.swing.JLabel();
        jLapSpin38 = new javax.swing.JSpinner();
        jLapImg39 = new javax.swing.JLabel();
        jLapImg40 = new javax.swing.JLabel();
        jLapID39 = new javax.swing.JLabel();
        jLapName39 = new javax.swing.JLabel();
        jLapSpin39 = new javax.swing.JSpinner();
        jLapName40 = new javax.swing.JLabel();
        jLapID40 = new javax.swing.JLabel();
        jLapSpin40 = new javax.swing.JSpinner();
        jSPMainDishes = new javax.swing.JScrollPane();
        jPFMainDishes = new javax.swing.JPanel();
        jLMDImg4 = new javax.swing.JLabel();
        jLMDImg2 = new javax.swing.JLabel();
        jLMDImg3 = new javax.swing.JLabel();
        jLMDImg1 = new javax.swing.JLabel();
        jLMNID1 = new javax.swing.JLabel();
        jLMNID2 = new javax.swing.JLabel();
        jLMNID4 = new javax.swing.JLabel();
        jLMNID3 = new javax.swing.JLabel();
        jLMNName4 = new javax.swing.JLabel();
        jLMNName3 = new javax.swing.JLabel();
        jLMNName2 = new javax.swing.JLabel();
        jLMNName1 = new javax.swing.JLabel();
        jLMNSpin1 = new javax.swing.JSpinner();
        jLMNSpin2 = new javax.swing.JSpinner();
        jLMNSpin3 = new javax.swing.JSpinner();
        jLMNSpin4 = new javax.swing.JSpinner();
        jLMNSpin7 = new javax.swing.JSpinner();
        jLMNName7 = new javax.swing.JLabel();
        jLMNID7 = new javax.swing.JLabel();
        jLMDImg7 = new javax.swing.JLabel();
        jLMDImg5 = new javax.swing.JLabel();
        jLMNID8 = new javax.swing.JLabel();
        jLMNName8 = new javax.swing.JLabel();
        jLMNSpin8 = new javax.swing.JSpinner();
        jLMNSpin6 = new javax.swing.JSpinner();
        jLMNSpin5 = new javax.swing.JSpinner();
        jLMNName5 = new javax.swing.JLabel();
        jLMNID5 = new javax.swing.JLabel();
        jLMDImg8 = new javax.swing.JLabel();
        jLMDImg6 = new javax.swing.JLabel();
        jLMNID6 = new javax.swing.JLabel();
        jLMNName6 = new javax.swing.JLabel();
        jLMNSpin11 = new javax.swing.JSpinner();
        jLMNSpin10 = new javax.swing.JSpinner();
        jLMNName10 = new javax.swing.JLabel();
        jLMNID10 = new javax.swing.JLabel();
        jLMDImg10 = new javax.swing.JLabel();
        jLMDImg9 = new javax.swing.JLabel();
        jLMNID9 = new javax.swing.JLabel();
        jLMNName9 = new javax.swing.JLabel();
        jLMNSpin9 = new javax.swing.JSpinner();
        jLMDImg11 = new javax.swing.JLabel();
        jLMNID11 = new javax.swing.JLabel();
        jLMNName11 = new javax.swing.JLabel();
        jLMNID12 = new javax.swing.JLabel();
        jLMDImg12 = new javax.swing.JLabel();
        jLMNName12 = new javax.swing.JLabel();
        jLMNSpin12 = new javax.swing.JSpinner();
        jLMDImg13 = new javax.swing.JLabel();
        jLMDImg14 = new javax.swing.JLabel();
        jLMDImg16 = new javax.swing.JLabel();
        jLMDImg15 = new javax.swing.JLabel();
        jLMNID13 = new javax.swing.JLabel();
        jLMNID14 = new javax.swing.JLabel();
        jLMNID16 = new javax.swing.JLabel();
        jLMNID15 = new javax.swing.JLabel();
        jLMNName13 = new javax.swing.JLabel();
        jLMNName14 = new javax.swing.JLabel();
        jLMNName15 = new javax.swing.JLabel();
        jLMNName16 = new javax.swing.JLabel();
        jLMNSpin13 = new javax.swing.JSpinner();
        jLMNSpin14 = new javax.swing.JSpinner();
        jLMNSpin15 = new javax.swing.JSpinner();
        jLMNSpin16 = new javax.swing.JSpinner();
        jLMDImg17 = new javax.swing.JLabel();
        jLMDImg18 = new javax.swing.JLabel();
        jLMDImg20 = new javax.swing.JLabel();
        jLMDImg19 = new javax.swing.JLabel();
        jLMNID17 = new javax.swing.JLabel();
        jLMNID18 = new javax.swing.JLabel();
        jLMNName17 = new javax.swing.JLabel();
        jLMNName18 = new javax.swing.JLabel();
        jLMNID19 = new javax.swing.JLabel();
        jLMNID20 = new javax.swing.JLabel();
        jLMNName19 = new javax.swing.JLabel();
        jLMNName20 = new javax.swing.JLabel();
        jLMNSpin17 = new javax.swing.JSpinner();
        jLMNSpin18 = new javax.swing.JSpinner();
        jLMNSpin19 = new javax.swing.JSpinner();
        jLMNSpin20 = new javax.swing.JSpinner();
        jLMDImg21 = new javax.swing.JLabel();
        jLMNID21 = new javax.swing.JLabel();
        jLMNName21 = new javax.swing.JLabel();
        jLMDImg22 = new javax.swing.JLabel();
        jLMNID22 = new javax.swing.JLabel();
        jLMNName22 = new javax.swing.JLabel();
        jLMNSpin22 = new javax.swing.JSpinner();
        jLMNSpin21 = new javax.swing.JSpinner();
        jLMDImg24 = new javax.swing.JLabel();
        jLMNName24 = new javax.swing.JLabel();
        jLMNName23 = new javax.swing.JLabel();
        jLMNID23 = new javax.swing.JLabel();
        jLMNSpin23 = new javax.swing.JSpinner();
        jLMNID24 = new javax.swing.JLabel();
        jLMDImg23 = new javax.swing.JLabel();
        jLMNSpin24 = new javax.swing.JSpinner();
        jLMNSpin26 = new javax.swing.JSpinner();
        jLMDImg26 = new javax.swing.JLabel();
        jLMNName25 = new javax.swing.JLabel();
        jLMNSpin25 = new javax.swing.JSpinner();
        jLMNID26 = new javax.swing.JLabel();
        jLMNID25 = new javax.swing.JLabel();
        jLMDImg25 = new javax.swing.JLabel();
        jLMNName26 = new javax.swing.JLabel();
        jLMNSpin28 = new javax.swing.JSpinner();
        jLMDImg28 = new javax.swing.JLabel();
        jLMNName27 = new javax.swing.JLabel();
        jLMNSpin27 = new javax.swing.JSpinner();
        jLMNID28 = new javax.swing.JLabel();
        jLMNID27 = new javax.swing.JLabel();
        jLMDImg27 = new javax.swing.JLabel();
        jLMNName28 = new javax.swing.JLabel();
        jLMNSpin30 = new javax.swing.JSpinner();
        jLMDImg30 = new javax.swing.JLabel();
        jLMNName29 = new javax.swing.JLabel();
        jLMNSpin29 = new javax.swing.JSpinner();
        jLMNID30 = new javax.swing.JLabel();
        jLMNID29 = new javax.swing.JLabel();
        jLMDImg29 = new javax.swing.JLabel();
        jLMNName30 = new javax.swing.JLabel();
        jLMNSpin32 = new javax.swing.JSpinner();
        jLMDImg32 = new javax.swing.JLabel();
        jLMNName31 = new javax.swing.JLabel();
        jLMNSpin31 = new javax.swing.JSpinner();
        jLMNID32 = new javax.swing.JLabel();
        jLMNID31 = new javax.swing.JLabel();
        jLMDImg31 = new javax.swing.JLabel();
        jLMNName32 = new javax.swing.JLabel();
        jLMDImg33 = new javax.swing.JLabel();
        jLMNID33 = new javax.swing.JLabel();
        jLMNName33 = new javax.swing.JLabel();
        jLMNSpin33 = new javax.swing.JSpinner();
        jLMDImg34 = new javax.swing.JLabel();
        jLMNID34 = new javax.swing.JLabel();
        jLMNName34 = new javax.swing.JLabel();
        jLMNSpin34 = new javax.swing.JSpinner();
        jLMDImg35 = new javax.swing.JLabel();
        jLMNID35 = new javax.swing.JLabel();
        jLMNName35 = new javax.swing.JLabel();
        jLMNSpin35 = new javax.swing.JSpinner();
        jLMDImg36 = new javax.swing.JLabel();
        jLMNID36 = new javax.swing.JLabel();
        jLMNName36 = new javax.swing.JLabel();
        jLMNSpin36 = new javax.swing.JSpinner();
        jLMDImg38 = new javax.swing.JLabel();
        jLMDImg37 = new javax.swing.JLabel();
        jLMNID37 = new javax.swing.JLabel();
        jLMNName37 = new javax.swing.JLabel();
        jLMNSpin37 = new javax.swing.JSpinner();
        jLMNName38 = new javax.swing.JLabel();
        jLMNID38 = new javax.swing.JLabel();
        jLMNSpin38 = new javax.swing.JSpinner();
        jLMDImg39 = new javax.swing.JLabel();
        jLMDImg40 = new javax.swing.JLabel();
        jLMNID39 = new javax.swing.JLabel();
        jLMNName39 = new javax.swing.JLabel();
        jLMNSpin39 = new javax.swing.JSpinner();
        jLMNName40 = new javax.swing.JLabel();
        jLMNID40 = new javax.swing.JLabel();
        jLMNSpin40 = new javax.swing.JSpinner();
        JSPDesert = new javax.swing.JScrollPane();
        jPFDesert = new javax.swing.JPanel();
        jLDSImg4 = new javax.swing.JLabel();
        jLDSImg2 = new javax.swing.JLabel();
        jLDSImg3 = new javax.swing.JLabel();
        jLDSImg1 = new javax.swing.JLabel();
        jLDSID1 = new javax.swing.JLabel();
        jLDSID2 = new javax.swing.JLabel();
        jLDSID4 = new javax.swing.JLabel();
        jLDSID3 = new javax.swing.JLabel();
        jLDSName4 = new javax.swing.JLabel();
        jLDSName3 = new javax.swing.JLabel();
        jLDSName2 = new javax.swing.JLabel();
        jLDSName1 = new javax.swing.JLabel();
        jLDSpin1 = new javax.swing.JSpinner();
        jLDSpin2 = new javax.swing.JSpinner();
        jLDSpin3 = new javax.swing.JSpinner();
        jLDSpin4 = new javax.swing.JSpinner();
        jLDSpin7 = new javax.swing.JSpinner();
        jLDSName7 = new javax.swing.JLabel();
        jLDSID7 = new javax.swing.JLabel();
        jLDSImg7 = new javax.swing.JLabel();
        jLDSImg5 = new javax.swing.JLabel();
        jLDSID8 = new javax.swing.JLabel();
        jLDSName8 = new javax.swing.JLabel();
        jLDSpin8 = new javax.swing.JSpinner();
        jLDSpin6 = new javax.swing.JSpinner();
        jLDSpin5 = new javax.swing.JSpinner();
        jLDSName5 = new javax.swing.JLabel();
        jLDSID5 = new javax.swing.JLabel();
        jLDSImg8 = new javax.swing.JLabel();
        jLDSImg6 = new javax.swing.JLabel();
        jLDSID6 = new javax.swing.JLabel();
        jLDSName6 = new javax.swing.JLabel();
        jLDSpin11 = new javax.swing.JSpinner();
        jLDSpin10 = new javax.swing.JSpinner();
        jLDSName10 = new javax.swing.JLabel();
        jLDSID10 = new javax.swing.JLabel();
        jLDSImg10 = new javax.swing.JLabel();
        jLDSImg9 = new javax.swing.JLabel();
        jLDSID9 = new javax.swing.JLabel();
        jLDSName9 = new javax.swing.JLabel();
        jLDSpin9 = new javax.swing.JSpinner();
        jLDSImg11 = new javax.swing.JLabel();
        jLDSID11 = new javax.swing.JLabel();
        jLDSName11 = new javax.swing.JLabel();
        jLDSID12 = new javax.swing.JLabel();
        jLDSImg12 = new javax.swing.JLabel();
        jLDSName12 = new javax.swing.JLabel();
        jLDSpin12 = new javax.swing.JSpinner();
        jLDSImg13 = new javax.swing.JLabel();
        jLDSImg14 = new javax.swing.JLabel();
        jLDSImg16 = new javax.swing.JLabel();
        jLDSImg15 = new javax.swing.JLabel();
        jLDSID13 = new javax.swing.JLabel();
        jLDSID14 = new javax.swing.JLabel();
        jLDSID16 = new javax.swing.JLabel();
        jLDSID15 = new javax.swing.JLabel();
        jLDSName13 = new javax.swing.JLabel();
        jLDSName14 = new javax.swing.JLabel();
        jLDSName15 = new javax.swing.JLabel();
        jLDSName16 = new javax.swing.JLabel();
        jLDSpin13 = new javax.swing.JSpinner();
        jLDSpin14 = new javax.swing.JSpinner();
        jLDSpin15 = new javax.swing.JSpinner();
        jLDSpin16 = new javax.swing.JSpinner();
        jLDSImg17 = new javax.swing.JLabel();
        jLDSImg18 = new javax.swing.JLabel();
        jLDSImg20 = new javax.swing.JLabel();
        jLDSImg19 = new javax.swing.JLabel();
        jLDSID17 = new javax.swing.JLabel();
        jLDSID18 = new javax.swing.JLabel();
        jLDSName17 = new javax.swing.JLabel();
        jLDSName18 = new javax.swing.JLabel();
        jLDSID19 = new javax.swing.JLabel();
        jLDSID20 = new javax.swing.JLabel();
        jLDSName19 = new javax.swing.JLabel();
        jLDSName20 = new javax.swing.JLabel();
        jLDSpin17 = new javax.swing.JSpinner();
        jLDSpin18 = new javax.swing.JSpinner();
        jLDSpin19 = new javax.swing.JSpinner();
        jLDSpin20 = new javax.swing.JSpinner();
        jLDSImg21 = new javax.swing.JLabel();
        jLDSID21 = new javax.swing.JLabel();
        jLDSName21 = new javax.swing.JLabel();
        jLDSImg22 = new javax.swing.JLabel();
        jLDSID22 = new javax.swing.JLabel();
        jLDSName22 = new javax.swing.JLabel();
        jLDSpin22 = new javax.swing.JSpinner();
        jLDSpin21 = new javax.swing.JSpinner();
        jLDSImg24 = new javax.swing.JLabel();
        jLDSName24 = new javax.swing.JLabel();
        jLDSName23 = new javax.swing.JLabel();
        jLDSID23 = new javax.swing.JLabel();
        jLDSpin23 = new javax.swing.JSpinner();
        jLDSID24 = new javax.swing.JLabel();
        jLDSImg23 = new javax.swing.JLabel();
        jLDSpin24 = new javax.swing.JSpinner();
        jLDSpin26 = new javax.swing.JSpinner();
        jLDSImg26 = new javax.swing.JLabel();
        jLDSName25 = new javax.swing.JLabel();
        jLDSpin25 = new javax.swing.JSpinner();
        jLDSID26 = new javax.swing.JLabel();
        jLDSID25 = new javax.swing.JLabel();
        jLDSImg25 = new javax.swing.JLabel();
        jLDSName26 = new javax.swing.JLabel();
        jLDSpin28 = new javax.swing.JSpinner();
        jLDSImg28 = new javax.swing.JLabel();
        jLDSName27 = new javax.swing.JLabel();
        jLDSpin27 = new javax.swing.JSpinner();
        jLDSID28 = new javax.swing.JLabel();
        jLDSID27 = new javax.swing.JLabel();
        jLDSImg27 = new javax.swing.JLabel();
        jLDSName28 = new javax.swing.JLabel();
        jLDSpin30 = new javax.swing.JSpinner();
        jLDSImg30 = new javax.swing.JLabel();
        jLDSName29 = new javax.swing.JLabel();
        jLDSpin29 = new javax.swing.JSpinner();
        jLDSID30 = new javax.swing.JLabel();
        jLDSID29 = new javax.swing.JLabel();
        jLDSImg29 = new javax.swing.JLabel();
        jLDSName30 = new javax.swing.JLabel();
        jLDSpin32 = new javax.swing.JSpinner();
        jLDSImg32 = new javax.swing.JLabel();
        jLDSName31 = new javax.swing.JLabel();
        jLDSpin31 = new javax.swing.JSpinner();
        jLDSID32 = new javax.swing.JLabel();
        jLDSID31 = new javax.swing.JLabel();
        jLDSImg31 = new javax.swing.JLabel();
        jLDSName32 = new javax.swing.JLabel();
        jLDSImg33 = new javax.swing.JLabel();
        jLDSID33 = new javax.swing.JLabel();
        jLDSName33 = new javax.swing.JLabel();
        jLDSpin33 = new javax.swing.JSpinner();
        jLDSImg34 = new javax.swing.JLabel();
        jLDSID34 = new javax.swing.JLabel();
        jLDSName34 = new javax.swing.JLabel();
        jLDSpin34 = new javax.swing.JSpinner();
        jLDSImg35 = new javax.swing.JLabel();
        jLDSID35 = new javax.swing.JLabel();
        jLDSName35 = new javax.swing.JLabel();
        jLDSpin35 = new javax.swing.JSpinner();
        jLDSImg36 = new javax.swing.JLabel();
        jLDSID36 = new javax.swing.JLabel();
        jLDSName36 = new javax.swing.JLabel();
        jLDSpin36 = new javax.swing.JSpinner();
        jLDSImg38 = new javax.swing.JLabel();
        jLDSImg37 = new javax.swing.JLabel();
        jLDSID37 = new javax.swing.JLabel();
        jLDSName37 = new javax.swing.JLabel();
        jLDSpin37 = new javax.swing.JSpinner();
        jLDSName38 = new javax.swing.JLabel();
        jLDSID38 = new javax.swing.JLabel();
        jLDSpin38 = new javax.swing.JSpinner();
        jLDSImg39 = new javax.swing.JLabel();
        jLDSImg40 = new javax.swing.JLabel();
        jLDSID39 = new javax.swing.JLabel();
        jLDSName39 = new javax.swing.JLabel();
        jLDSpin39 = new javax.swing.JSpinner();
        jLDSName40 = new javax.swing.JLabel();
        jLDSID40 = new javax.swing.JLabel();
        jLDSpin40 = new javax.swing.JSpinner();
        JFXemTinhTrang = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTSuDungNL = new javax.swing.JTable();
        jLToDate = new javax.swing.JLabel();
        jLFroMDate = new javax.swing.JLabel();
        jDCdateEnd = new com.toedter.calendar.JDateChooser();
        jLXemTTtxt = new javax.swing.JLabel();
        jCBtTnlieu = new javax.swing.JComboBox<>();
        jDCdateStart = new com.toedter.calendar.JDateChooser();
        jBtnXemTT = new javax.swing.JButton();
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
        jDChangePSMNG = new javax.swing.JDialog();
        jPNewPSBG = new javax.swing.JPanel();
        jBnNewPS = new javax.swing.JButton();
        jLNewPSTitle = new javax.swing.JLabel();
        jTNewPS = new javax.swing.JPasswordField();
        jLNewPS = new javax.swing.JLabel();
        jLNewPSAlert = new javax.swing.JLabel();
        jBnNewPSOut = new javax.swing.JButton();
        jPMain = new javax.swing.JPanel();
        jPLogin = new javax.swing.JPanel();
        jLLoginTitle = new javax.swing.JLabel();
        jLUserName = new javax.swing.JLabel();
        jTUserNametxt = new javax.swing.JTextField();
        jBnLogin = new javax.swing.JButton();
        jLPassword = new javax.swing.JLabel();
        jPPasswordtxt = new javax.swing.JPasswordField();
        jLLoginAlet = new javax.swing.JLabel();

        jDQuanLy.setLocation(new java.awt.Point(0, 0));
        jDQuanLy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jDQuanLyComponentHidden(evt);
            }
        });

        jPManagerControl.setBackground(new java.awt.Color(255, 237, 58));
        jPManagerControl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbManageEmp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbManageEmp.setText("Manage Employee");
        jbManageEmp.setPreferredSize(new java.awt.Dimension(111, 33));
        jbManageEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbManageEmpActionPerformed(evt);
            }
        });
        jPManagerControl.add(jbManageEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, 30));

        jbVieCustomer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbVieCustomer.setText("View Customer");
        jbVieCustomer.setMaximumSize(new java.awt.Dimension(111, 33));
        jbVieCustomer.setMinimumSize(new java.awt.Dimension(111, 33));
        jbVieCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVieCustomerActionPerformed(evt);
            }
        });
        jPManagerControl.add(jbVieCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 140, -1));

        jbManageFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbManageFood.setText("Manage Food");
        jbManageFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbManageFoodActionPerformed(evt);
            }
        });
        jPManagerControl.add(jbManageFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 140, -1));

        BtThucDonTuan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtThucDonTuan.setText("WeekMenus");
        BtThucDonTuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtThucDonTuanActionPerformed(evt);
            }
        });
        jPManagerControl.add(BtThucDonTuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 140, -1));

        Btn5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn5.setText("Status");
        Btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5ActionPerformed(evt);
            }
        });
        jPManagerControl.add(Btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 140, -1));

        Btn6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn6.setText("Change Password");
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

        jBnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBnDangXuat.setText("Logout");
        jBnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnDangXuatActionPerformed(evt);
            }
        });
        jPManagerControl.add(jBnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 140, 40));

        JFEmpManage.setBackground(new java.awt.Color(255, 237, 58));
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

        jBEmpSearchBy1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBEmpSearchBy1.setText("Default");
        jBEmpSearchBy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEmpSearchBy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFEmpManageLayout = new javax.swing.GroupLayout(JFEmpManage);
        JFEmpManage.setLayout(JFEmpManageLayout);
        JFEmpManageLayout.setHorizontalGroup(
            JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollShowNhanVien)
            .addGroup(JFEmpManageLayout.createSequentialGroup()
                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(JFEmpManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(ClearAllEmpTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(SaveEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(DeeleteEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(AddNewEmpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jBEmpSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBEmpSearchBy1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(JFEmpManageLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLEmpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
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
                    .addComponent(jRBtnEmpUserName)
                    .addComponent(jBEmpSearchBy1))
                .addGap(18, 18, 18)
                .addComponent(jScrollShowNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        JFCustomerView.setBackground(new java.awt.Color(255, 237, 58));
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
                        .addComponent(CustomerLowPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
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

        JFFoodManage.setBackground(new java.awt.Color(255, 237, 58));
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
        jBFDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFDeleteActionPerformed(evt);
            }
        });

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
        jBSearchingFood.setText("Seach");
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

        jbSortByType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbSortByType.setText("Type");
        jbSortByType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSortByTypeActionPerformed(evt);
            }
        });

        jbShowDefaultFood.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbShowDefaultFood.setText("Default");
        jbShowDefaultFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbShowDefaultFoodActionPerformed(evt);
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
                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addComponent(jLSeachingFood1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBSortFoodID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jbSortByName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSortByType, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(jbShowDefaultFood, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLFImgLink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollShowFoodToManage, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFFoodManageLayout.setVerticalGroup(
            JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFFoodManageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLEmpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jbShowDefaultFood))
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
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JFFoodManageLayout.createSequentialGroup()
                                .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBFImg)
                                    .addGroup(JFFoodManageLayout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(JFFoodManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLSeachingFood1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jBSortFoodID)
                                                .addComponent(jbSortByName))
                                            .addComponent(jbSortByType))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLFImgLink, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollShowFoodToManage, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        JFNewFoodListWeek.setBackground(new java.awt.Color(255, 237, 58));
        JFNewFoodListWeek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JFNewFoodListWeek.setPreferredSize(new java.awt.Dimension(1685, 0));

        jTThucDonTuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FoodID", "Name", "Type", "Quantity"
            }
        ));
        jScrollPane5.setViewportView(jTThucDonTuan);

        jLThucDonTuan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLThucDonTuan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLThucDonTuan.setText("Menus From Date: --/--/----");

        jDCTaoThucDonTuan.setDateFormatString("dd/MM/yyyy");

        jLNgayBatDauTD.setText("Date Start:");

        jBTaoThucDonTuan.setText("Create");
        jBTaoThucDonTuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTaoThucDonTuanActionPerformed(evt);
            }
        });

        TimNgayBatDauTD.setText("Find menus from date:");

        jDCTimthucDonTuan.setDateFormatString("dd/MM/yyyy");

        jBTimKieMThucDonTuan.setText("Search");
        jBTimKieMThucDonTuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTimKieMThucDonTuanActionPerformed(evt);
            }
        });

        jTDisplayType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDisplayTypeMouseClicked(evt);
            }
        });

        jPFAppetizer.setBackground(new java.awt.Color(255, 237, 58));

        jLapImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg4.setText("jLabel7");

        jLapImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg2.setText("jLabel7");

        jLapImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg3.setText("jLabel7");

        jLapImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg1.setText("jLabel7");

        jLapID1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID1.setText("FoodID: Fxx");

        jLapID2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID2.setText("FoodID: Fxx");

        jLapID4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID4.setText("FoodID: Fxx");

        jLapID3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID3.setText("FoodID: Fxx");

        jLapName4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName4.setText("FoodID: Fxx");

        jLapName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName3.setText("FoodID: Fxx");

        jLapName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName2.setText("FoodID: Fxx");

        jLapName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName1.setText("FoodID: Fxx");

        jLapSpin1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        jLapSpin1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLapSpin2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName7.setText("FoodID: Fxx");

        jLapID7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID7.setText("FoodID: Fxx");

        jLapImg7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg7.setText("jLabel7");

        jLapImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg5.setText("jLabel7");

        jLapID8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID8.setText("FoodID: Fxx");

        jLapName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName8.setText("FoodID: Fxx");

        jLapSpin8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName5.setText("FoodID: Fxx");

        jLapID5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID5.setText("FoodID: Fxx");

        jLapImg8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg8.setText("jLabel7");

        jLapImg6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg6.setText("jLabel7");

        jLapID6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID6.setText("FoodID: Fxx");

        jLapName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName6.setText("FoodID: Fxx");

        jLapSpin11.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin10.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapName10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName10.setText("FoodID: Fxx");

        jLapID10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID10.setText("FoodID: Fxx");

        jLapImg10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg10.setText("jLabel7");

        jLapImg9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg9.setText("jLabel7");

        jLapID9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID9.setText("FoodID: Fxx");

        jLapName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName9.setText("FoodID: Fxx");

        jLapSpin9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg11.setText("jLabel7");

        jLapID11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID11.setText("FoodID: Fxx");

        jLapName11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName11.setText("FoodID: Fxx");

        jLapID12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID12.setText("FoodID: Fxx");

        jLapImg12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg12.setText("jLabel7");

        jLapName12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName12.setText("FoodID: Fxx");

        jLapSpin12.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg13.setText("jLabel7");

        jLapImg14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg14.setText("jLabel7");

        jLapImg16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg16.setText("jLabel7");

        jLapImg15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg15.setText("jLabel7");

        jLapID13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID13.setText("FoodID: Fxx");

        jLapID14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID14.setText("FoodID: Fxx");

        jLapID16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID16.setText("FoodID: Fxx");

        jLapID15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID15.setText("FoodID: Fxx");

        jLapName13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName13.setText("FoodID: Fxx");

        jLapName14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName14.setText("FoodID: Fxx");

        jLapName15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName15.setText("FoodID: Fxx");

        jLapName16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName16.setText("FoodID: Fxx");

        jLapSpin13.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin15.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg17.setText("jLabel7");

        jLapImg18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg18.setText("jLabel7");

        jLapImg20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg20.setText("jLabel7");

        jLapImg19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg19.setText("jLabel7");

        jLapID17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID17.setText("FoodID: Fxx");

        jLapID18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID18.setText("FoodID: Fxx");

        jLapName17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName17.setText("FoodID: Fxx");

        jLapName18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName18.setText("FoodID: Fxx");

        jLapID19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID19.setText("FoodID: Fxx");

        jLapID20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID20.setText("FoodID: Fxx");

        jLapName19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName19.setText("FoodID: Fxx");

        jLapName20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName20.setText("FoodID: Fxxa");

        jLapSpin17.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin18.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin19.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin20.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg21.setText("jLabel7");

        jLapID21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID21.setText("FoodID: Fxx");

        jLapName21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName21.setText("FoodID: Fxx");

        jLapImg22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg22.setText("jLabel7");

        jLapID22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID22.setText("FoodID: Fxx");

        jLapName22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName22.setText("FoodID: Fxx");

        jLapSpin22.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin21.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg24.setText("jLabel7");

        jLapName24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName24.setText("FoodID: Fxx");

        jLapName23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName23.setText("FoodID: Fxx");

        jLapID23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID23.setText("FoodID: Fxx");

        jLapSpin23.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapID24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID24.setText("FoodID: Fxx");

        jLapImg23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg23.setText("jLabel7");

        jLapSpin24.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapSpin26.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg26.setText("jLabel7");

        jLapName25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName25.setText("FoodID: Fxx");

        jLapSpin25.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapID26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID26.setText("FoodID: Fxx");

        jLapID25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID25.setText("FoodID: Fxx");

        jLapImg25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg25.setText("jLabel7");

        jLapName26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName26.setText("FoodID: Fxx");

        jLapSpin28.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg28.setText("jLabel7");

        jLapName27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName27.setText("FoodID: Fxx");

        jLapSpin27.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapID28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID28.setText("FoodID: Fxx");

        jLapID27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID27.setText("FoodID: Fxx");

        jLapImg27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg27.setText("jLabel7");

        jLapName28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName28.setText("FoodID: Fxx");

        jLapSpin30.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg30.setText("jLabel7");

        jLapName29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName29.setText("FoodID: Fxx");

        jLapSpin29.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapID30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID30.setText("FoodID: Fxx");

        jLapID29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID29.setText("FoodID: Fxx");

        jLapImg29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg29.setText("jLabel7");

        jLapName30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName30.setText("FoodID: Fxx");

        jLapSpin32.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg32.setText("jLabel7");

        jLapName31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName31.setText("FoodID: Fxx");

        jLapSpin31.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapID32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID32.setText("FoodID: Fxx");

        jLapID31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID31.setText("FoodID: Fxx");

        jLapImg31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg31.setText("jLabel7");

        jLapName32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName32.setText("FoodID: Fxx");

        jLapImg33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg33.setText("jLabel7");

        jLapID33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID33.setText("FoodID: Fxx");

        jLapName33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName33.setText("FoodID: Fxx");

        jLapSpin33.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg34.setText("jLabel7");

        jLapID34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID34.setText("FoodID: Fxx");

        jLapName34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName34.setText("FoodID: Fxx");

        jLapSpin34.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg35.setText("jLabel7");

        jLapID35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID35.setText("FoodID: Fxx");

        jLapName35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName35.setText("FoodID: Fxx");

        jLapSpin35.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg36.setText("jLabel7");

        jLapID36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID36.setText("FoodID: Fxx");

        jLapName36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName36.setText("FoodID: Fxx");

        jLapSpin36.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg38.setText("jLabel7");

        jLapImg37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg37.setText("jLabel7");

        jLapID37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID37.setText("FoodID: Fxx");

        jLapName37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName37.setText("FoodID: Fxx");

        jLapSpin37.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapName38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName38.setText("FoodID: Fxx");

        jLapID38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID38.setText("FoodID: Fxx");

        jLapSpin38.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapImg39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg39.setText("jLabel7");

        jLapImg40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLapImg40.setText("jLabel7");

        jLapID39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID39.setText("FoodID: Fxx");

        jLapName39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName39.setText("FoodID: Fxx");

        jLapSpin39.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLapName40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapName40.setText("FoodID: Fxx");

        jLapID40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLapID40.setText("FoodID: Fxx");

        jLapSpin40.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        javax.swing.GroupLayout jPFAppetizerLayout = new javax.swing.GroupLayout(jPFAppetizer);
        jPFAppetizer.setLayout(jPFAppetizerLayout);
        jPFAppetizerLayout.setHorizontalGroup(
            jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLapImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLapName1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLapImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLapID2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLapName2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLapSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapID3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapID4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLapSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLapName3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLapName4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLapSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFAppetizerLayout.createSequentialGroup()
                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                .addComponent(jLapImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLapImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                .addComponent(jLapID25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLapID26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLapSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLapName25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLapName26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLapSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                    .addComponent(jLapImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLapImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                    .addComponent(jLapID29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLapID30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLapSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapName29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLapName30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                        .addComponent(jLapImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLapImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                        .addComponent(jLapID33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLapID34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLapSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLapName33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(42, 42, 42)
                                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLapName34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLapSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                            .addComponent(jLapImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLapImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                            .addComponent(jLapID37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLapID38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLapSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLapName37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(42, 42, 42)
                                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLapName38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLapSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                                .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLapSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLapName17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLapName18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLapSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapID39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapID40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapID14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLapImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addComponent(jLapImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLapImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLapSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLapID7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapName7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLapImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLapID11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapName11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLapSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLapName8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapID8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLapSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLapImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLapName12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLapID12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLapSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapID15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapID16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapID17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapID18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFAppetizerLayout.createSequentialGroup()
                                        .addComponent(jLapID19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLapID20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFAppetizerLayout.createSequentialGroup()
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapName19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLapName20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLapSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPFAppetizerLayout.setVerticalGroup(
            jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFAppetizerLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapID1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapName1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapID5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapName5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapID9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapName9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapID13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapName13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapID17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapID20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapName17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapName20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLapSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLapSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFAppetizerLayout.createSequentialGroup()
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapID39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapID40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapName39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapName40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFAppetizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLapSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLapSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSPAppetizer.setViewportView(jPFAppetizer);

        jTDisplayType.addTab("Appetizer", jSPAppetizer);

        jPFMainDishes.setBackground(new java.awt.Color(255, 237, 58));

        jLMDImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg4.setText("jLabel7");

        jLMDImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg2.setText("jLabel7");

        jLMDImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg3.setText("jLabel7");

        jLMDImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg1.setText("jLabel7");

        jLMNID1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID1.setText("FoodID: Fxx");

        jLMNID2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID2.setText("FoodID: Fxx");

        jLMNID4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID4.setText("FoodID: Fxx");

        jLMNID3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID3.setText("FoodID: Fxx");

        jLMNName4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName4.setText("FoodID: Fxx");

        jLMNName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName3.setText("FoodID: Fxx");

        jLMNName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName2.setText("FoodID: Fxx");

        jLMNName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName1.setText("FoodID: Fxx");

        jLMNSpin1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        jLMNSpin1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLMNSpin2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName7.setText("FoodID: Fxx");

        jLMNID7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID7.setText("FoodID: Fxx");

        jLMDImg7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg7.setText("jLabel7");

        jLMDImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg5.setText("jLabel7");

        jLMNID8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID8.setText("FoodID: Fxx");

        jLMNName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName8.setText("FoodID: Fxx");

        jLMNSpin8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName5.setText("FoodID: Fxx");

        jLMNID5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID5.setText("FoodID: Fxx");

        jLMDImg8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg8.setText("jLabel7");

        jLMDImg6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg6.setText("jLabel7");

        jLMNID6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID6.setText("FoodID: Fxx");

        jLMNName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName6.setText("FoodID: Fxx");

        jLMNSpin11.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin10.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNName10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName10.setText("FoodID: Fxx");

        jLMNID10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID10.setText("FoodID: Fxx");

        jLMDImg10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg10.setText("jLabel7");

        jLMDImg9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg9.setText("jLabel7");

        jLMNID9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID9.setText("FoodID: Fxx");

        jLMNName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName9.setText("FoodID: Fxx");

        jLMNSpin9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg11.setText("jLabel7");

        jLMNID11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID11.setText("FoodID: Fxx");

        jLMNName11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName11.setText("FoodID: Fxx");

        jLMNID12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID12.setText("FoodID: Fxx");

        jLMDImg12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg12.setText("jLabel7");

        jLMNName12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName12.setText("FoodID: Fxx");

        jLMNSpin12.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg13.setText("jLabel7");

        jLMDImg14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg14.setText("jLabel7");

        jLMDImg16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg16.setText("jLabel7");

        jLMDImg15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg15.setText("jLabel7");

        jLMNID13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID13.setText("FoodID: Fxx");

        jLMNID14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID14.setText("FoodID: Fxx");

        jLMNID16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID16.setText("FoodID: Fxx");

        jLMNID15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID15.setText("FoodID: Fxx");

        jLMNName13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName13.setText("FoodID: Fxx");

        jLMNName14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName14.setText("FoodID: Fxx");

        jLMNName15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName15.setText("FoodID: Fxx");

        jLMNName16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName16.setText("FoodID: Fxx");

        jLMNSpin13.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin15.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg17.setText("jLabel7");

        jLMDImg18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg18.setText("jLabel7");

        jLMDImg20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg20.setText("jLabel7");

        jLMDImg19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg19.setText("jLabel7");

        jLMNID17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID17.setText("FoodID: Fxx");

        jLMNID18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID18.setText("FoodID: Fxx");

        jLMNName17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName17.setText("FoodID: Fxx");

        jLMNName18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName18.setText("FoodID: Fxx");

        jLMNID19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID19.setText("FoodID: Fxx");

        jLMNID20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID20.setText("FoodID: Fxx");

        jLMNName19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName19.setText("FoodID: Fxx");

        jLMNName20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName20.setText("FoodID: Fxxa");

        jLMNSpin17.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin18.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin19.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin20.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg21.setText("jLabel7");

        jLMNID21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID21.setText("FoodID: Fxx");

        jLMNName21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName21.setText("FoodID: Fxx");

        jLMDImg22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg22.setText("jLabel7");

        jLMNID22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID22.setText("FoodID: Fxx");

        jLMNName22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName22.setText("FoodID: Fxx");

        jLMNSpin22.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin21.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg24.setText("jLabel7");

        jLMNName24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName24.setText("FoodID: Fxx");

        jLMNName23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName23.setText("FoodID: Fxx");

        jLMNID23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID23.setText("FoodID: Fxx");

        jLMNSpin23.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNID24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID24.setText("FoodID: Fxx");

        jLMDImg23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg23.setText("jLabel7");

        jLMNSpin24.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNSpin26.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg26.setText("jLabel7");

        jLMNName25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName25.setText("FoodID: Fxx");

        jLMNSpin25.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNID26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID26.setText("FoodID: Fxx");

        jLMNID25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID25.setText("FoodID: Fxx");

        jLMDImg25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg25.setText("jLabel7");

        jLMNName26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName26.setText("FoodID: Fxx");

        jLMNSpin28.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg28.setText("jLabel7");

        jLMNName27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName27.setText("FoodID: Fxx");

        jLMNSpin27.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNID28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID28.setText("FoodID: Fxx");

        jLMNID27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID27.setText("FoodID: Fxx");

        jLMDImg27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg27.setText("jLabel7");

        jLMNName28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName28.setText("FoodID: Fxx");

        jLMNSpin30.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg30.setText("jLabel7");

        jLMNName29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName29.setText("FoodID: Fxx");

        jLMNSpin29.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNID30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID30.setText("FoodID: Fxx");

        jLMNID29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID29.setText("FoodID: Fxx");

        jLMDImg29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg29.setText("jLabel7");

        jLMNName30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName30.setText("FoodID: Fxx");

        jLMNSpin32.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg32.setText("jLabel7");

        jLMNName31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName31.setText("FoodID: Fxx");

        jLMNSpin31.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNID32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID32.setText("FoodID: Fxx");

        jLMNID31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID31.setText("FoodID: Fxx");

        jLMDImg31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg31.setText("jLabel7");

        jLMNName32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName32.setText("FoodID: Fxx");

        jLMDImg33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg33.setText("jLabel7");

        jLMNID33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID33.setText("FoodID: Fxx");

        jLMNName33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName33.setText("FoodID: Fxx");

        jLMNSpin33.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg34.setText("jLabel7");

        jLMNID34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID34.setText("FoodID: Fxx");

        jLMNName34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName34.setText("FoodID: Fxx");

        jLMNSpin34.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg35.setText("jLabel7");

        jLMNID35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID35.setText("FoodID: Fxx");

        jLMNName35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName35.setText("FoodID: Fxx");

        jLMNSpin35.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg36.setText("jLabel7");

        jLMNID36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID36.setText("FoodID: Fxx");

        jLMNName36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName36.setText("FoodID: Fxx");

        jLMNSpin36.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg38.setText("jLabel7");

        jLMDImg37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg37.setText("jLabel7");

        jLMNID37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID37.setText("FoodID: Fxx");

        jLMNName37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName37.setText("FoodID: Fxx");

        jLMNSpin37.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNName38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName38.setText("FoodID: Fxx");

        jLMNID38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID38.setText("FoodID: Fxx");

        jLMNSpin38.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMDImg39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg39.setText("jLabel7");

        jLMDImg40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLMDImg40.setText("jLabel7");

        jLMNID39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID39.setText("FoodID: Fxx");

        jLMNName39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName39.setText("FoodID: Fxx");

        jLMNSpin39.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLMNName40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNName40.setText("FoodID: Fxx");

        jLMNID40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMNID40.setText("FoodID: Fxx");

        jLMNSpin40.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        javax.swing.GroupLayout jPFMainDishesLayout = new javax.swing.GroupLayout(jPFMainDishes);
        jPFMainDishes.setLayout(jPFMainDishesLayout);
        jPFMainDishesLayout.setHorizontalGroup(
            jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMDImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLMNName1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLMDImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLMNID2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLMNName2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLMNSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMDImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMDImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMNID3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMNID4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLMNSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLMNName3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLMNName4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLMNSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFMainDishesLayout.createSequentialGroup()
                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                .addComponent(jLMDImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLMDImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                .addComponent(jLMNID25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLMNID26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLMNSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLMNName25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLMNName26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLMNSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                    .addComponent(jLMDImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLMDImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                    .addComponent(jLMNID29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLMNID30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLMNSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNName29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLMNName30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                        .addComponent(jLMDImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLMDImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                        .addComponent(jLMNID33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLMNID34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLMNSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLMNName33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(42, 42, 42)
                                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLMNName34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLMNSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                            .addComponent(jLMDImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLMDImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                            .addComponent(jLMNID37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLMNID38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLMNSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLMNName37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(42, 42, 42)
                                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLMNName38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLMNSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                                .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLMNSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLMNName17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLMNName18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLMNSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMNID39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMNID40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMDImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMDImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMDImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMDImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMDImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNID14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLMDImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addComponent(jLMDImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLMDImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLMNSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLMNID7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNName7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLMDImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLMNID11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNName11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLMNSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLMNName8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNID8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLMNSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLMDImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLMNName12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLMNID12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLMNSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMDImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMDImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMNID15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMNID16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMDImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMDImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMNID17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMNID18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMDImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMDImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFMainDishesLayout.createSequentialGroup()
                                        .addComponent(jLMNID19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLMNID20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFMainDishesLayout.createSequentialGroup()
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNName19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLMNName20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLMNSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPFMainDishesLayout.setVerticalGroup(
            jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFMainDishesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMDImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNID1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNName1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMDImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNID5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNName5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMDImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNID9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNName9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMDImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNID13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNName13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMDImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMDImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNID17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNID20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNName17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNName20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMNSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMNSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFMainDishesLayout.createSequentialGroup()
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMDImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMDImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNID39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNID40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNName39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNName40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFMainDishesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLMNSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLMNSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSPMainDishes.setViewportView(jPFMainDishes);

        jTDisplayType.addTab("MainDishes", jSPMainDishes);

        jPFDesert.setBackground(new java.awt.Color(255, 237, 58));

        jLDSImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg4.setText("jLabel7");

        jLDSImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg2.setText("jLabel7");

        jLDSImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg3.setText("jLabel7");

        jLDSImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg1.setText("jLabel7");

        jLDSID1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID1.setText("FoodID: Fxx");

        jLDSID2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID2.setText("FoodID: Fxx");

        jLDSID4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID4.setText("FoodID: Fxx");

        jLDSID3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID3.setText("FoodID: Fxx");

        jLDSName4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName4.setText("FoodID: Fxx");

        jLDSName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName3.setText("FoodID: Fxx");

        jLDSName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName2.setText("FoodID: Fxx");

        jLDSName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName1.setText("FoodID: Fxx");

        jLDSpin1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        jLDSpin1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLDSpin2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName7.setText("FoodID: Fxx");

        jLDSID7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID7.setText("FoodID: Fxx");

        jLDSImg7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg7.setText("jLabel7");

        jLDSImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg5.setText("jLabel7");

        jLDSID8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID8.setText("FoodID: Fxx");

        jLDSName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName8.setText("FoodID: Fxx");

        jLDSpin8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName5.setText("FoodID: Fxx");

        jLDSID5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID5.setText("FoodID: Fxx");

        jLDSImg8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg8.setText("jLabel7");

        jLDSImg6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg6.setText("jLabel7");

        jLDSID6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID6.setText("FoodID: Fxx");

        jLDSName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName6.setText("FoodID: Fxx");

        jLDSpin11.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin10.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSName10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName10.setText("FoodID: Fxx");

        jLDSID10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID10.setText("FoodID: Fxx");

        jLDSImg10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg10.setText("jLabel7");

        jLDSImg9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg9.setText("jLabel7");

        jLDSID9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID9.setText("FoodID: Fxx");

        jLDSName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName9.setText("FoodID: Fxx");

        jLDSpin9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg11.setText("jLabel7");

        jLDSID11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID11.setText("FoodID: Fxx");

        jLDSName11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName11.setText("FoodID: Fxx");

        jLDSID12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID12.setText("FoodID: Fxx");

        jLDSImg12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg12.setText("jLabel7");

        jLDSName12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName12.setText("FoodID: Fxx");

        jLDSpin12.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg13.setText("jLabel7");

        jLDSImg14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg14.setText("jLabel7");

        jLDSImg16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg16.setText("jLabel7");

        jLDSImg15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg15.setText("jLabel7");

        jLDSID13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID13.setText("FoodID: Fxx");

        jLDSID14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID14.setText("FoodID: Fxx");

        jLDSID16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID16.setText("FoodID: Fxx");

        jLDSID15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID15.setText("FoodID: Fxx");

        jLDSName13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName13.setText("FoodID: Fxx");

        jLDSName14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName14.setText("FoodID: Fxx");

        jLDSName15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName15.setText("FoodID: Fxx");

        jLDSName16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName16.setText("FoodID: Fxx");

        jLDSpin13.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin15.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg17.setText("jLabel7");

        jLDSImg18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg18.setText("jLabel7");

        jLDSImg20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg20.setText("jLabel7");

        jLDSImg19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg19.setText("jLabel7");

        jLDSID17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID17.setText("FoodID: Fxx");

        jLDSID18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID18.setText("FoodID: Fxx");

        jLDSName17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName17.setText("FoodID: Fxx");

        jLDSName18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName18.setText("FoodID: Fxx");

        jLDSID19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID19.setText("FoodID: Fxx");

        jLDSID20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID20.setText("FoodID: Fxx");

        jLDSName19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName19.setText("FoodID: Fxx");

        jLDSName20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName20.setText("FoodID: Fxxa");

        jLDSpin17.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin18.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin19.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin20.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg21.setText("jLabel7");

        jLDSID21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID21.setText("FoodID: Fxx");

        jLDSName21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName21.setText("FoodID: Fxx");

        jLDSImg22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg22.setText("jLabel7");

        jLDSID22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID22.setText("FoodID: Fxx");

        jLDSName22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName22.setText("FoodID: Fxx");

        jLDSpin22.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin21.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg24.setText("jLabel7");

        jLDSName24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName24.setText("FoodID: Fxx");

        jLDSName23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName23.setText("FoodID: Fxx");

        jLDSID23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID23.setText("FoodID: Fxx");

        jLDSpin23.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSID24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID24.setText("FoodID: Fxx");

        jLDSImg23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg23.setText("jLabel7");

        jLDSpin24.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSpin26.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg26.setText("jLabel7");

        jLDSName25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName25.setText("FoodID: Fxx");

        jLDSpin25.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSID26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID26.setText("FoodID: Fxx");

        jLDSID25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID25.setText("FoodID: Fxx");

        jLDSImg25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg25.setText("jLabel7");

        jLDSName26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName26.setText("FoodID: Fxx");

        jLDSpin28.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg28.setText("jLabel7");

        jLDSName27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName27.setText("FoodID: Fxx");

        jLDSpin27.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSID28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID28.setText("FoodID: Fxx");

        jLDSID27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID27.setText("FoodID: Fxx");

        jLDSImg27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg27.setText("jLabel7");

        jLDSName28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName28.setText("FoodID: Fxx");

        jLDSpin30.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg30.setText("jLabel7");

        jLDSName29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName29.setText("FoodID: Fxx");

        jLDSpin29.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSID30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID30.setText("FoodID: Fxx");

        jLDSID29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID29.setText("FoodID: Fxx");

        jLDSImg29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg29.setText("jLabel7");

        jLDSName30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName30.setText("FoodID: Fxx");

        jLDSpin32.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg32.setText("jLabel7");

        jLDSName31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName31.setText("FoodID: Fxx");

        jLDSpin31.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSID32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID32.setText("FoodID: Fxx");

        jLDSID31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID31.setText("FoodID: Fxx");

        jLDSImg31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg31.setText("jLabel7");

        jLDSName32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName32.setText("FoodID: Fxx");

        jLDSImg33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg33.setText("jLabel7");

        jLDSID33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID33.setText("FoodID: Fxx");

        jLDSName33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName33.setText("FoodID: Fxx");

        jLDSpin33.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg34.setText("jLabel7");

        jLDSID34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID34.setText("FoodID: Fxx");

        jLDSName34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName34.setText("FoodID: Fxx");

        jLDSpin34.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg35.setText("jLabel7");

        jLDSID35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID35.setText("FoodID: Fxx");

        jLDSName35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName35.setText("FoodID: Fxx");

        jLDSpin35.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg36.setText("jLabel7");

        jLDSID36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID36.setText("FoodID: Fxx");

        jLDSName36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName36.setText("FoodID: Fxx");

        jLDSpin36.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg38.setText("jLabel7");

        jLDSImg37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg37.setText("jLabel7");

        jLDSID37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID37.setText("FoodID: Fxx");

        jLDSName37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName37.setText("FoodID: Fxx");

        jLDSpin37.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSName38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName38.setText("FoodID: Fxx");

        jLDSID38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID38.setText("FoodID: Fxx");

        jLDSpin38.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSImg39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg39.setText("jLabel7");

        jLDSImg40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/default-image.jpg"))); // NOI18N
        jLDSImg40.setText("jLabel7");

        jLDSID39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID39.setText("FoodID: Fxx");

        jLDSName39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName39.setText("FoodID: Fxx");

        jLDSpin39.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jLDSName40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSName40.setText("FoodID: Fxx");

        jLDSID40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDSID40.setText("FoodID: Fxx");

        jLDSpin40.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        javax.swing.GroupLayout jPFDesertLayout = new javax.swing.GroupLayout(jPFDesert);
        jPFDesert.setLayout(jPFDesertLayout);
        jPFDesertLayout.setHorizontalGroup(
            jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFDesertLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLDSImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLDSName1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLDSImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLDSID2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLDSName2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLDSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSID3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSID4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLDSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLDSName3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLDSName4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLDSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFDesertLayout.createSequentialGroup()
                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                                .addComponent(jLDSImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLDSImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                                .addComponent(jLDSID25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLDSID26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLDSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLDSName25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLDSName26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLDSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFDesertLayout.createSequentialGroup()
                                                    .addComponent(jLDSImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLDSImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPFDesertLayout.createSequentialGroup()
                                                    .addComponent(jLDSID29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLDSID30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLDSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSName29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLDSName30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                                        .addComponent(jLDSImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLDSImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                                        .addComponent(jLDSID33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(42, 42, 42)
                                                        .addComponent(jLDSID34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPFDesertLayout.createSequentialGroup()
                                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLDSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLDSName33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(42, 42, 42)
                                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLDSName34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLDSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                                            .addComponent(jLDSImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLDSImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                                            .addComponent(jLDSID37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(42, 42, 42)
                                                            .addComponent(jLDSID38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLDSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLDSName37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(42, 42, 42)
                                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLDSName38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLDSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                                .addGroup(jPFDesertLayout.createSequentialGroup()
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLDSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLDSName17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLDSName18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLDSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSID39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSID40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSID14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLDSImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addComponent(jLDSImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)
                                            .addComponent(jLDSImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPFDesertLayout.createSequentialGroup()
                                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLDSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLDSID7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSName7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLDSImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLDSID11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSName11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLDSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(42, 42, 42)
                                            .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLDSName8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSID8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLDSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLDSImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLDSName12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLDSID12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLDSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSID15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSID16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPFDesertLayout.createSequentialGroup()
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSID17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSID18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFDesertLayout.createSequentialGroup()
                                        .addComponent(jLDSID19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLDSID20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFDesertLayout.createSequentialGroup()
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSName19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLDSName20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLDSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPFDesertLayout.setVerticalGroup(
            jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFDesertLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSID1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSName1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSpin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSImg8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSID5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSName5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSpin5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSID9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSName9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSpin9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSID13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSName13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSpin13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSImg20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSID17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSID20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSName17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSName20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDSpin17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDSpin20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg36, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFDesertLayout.createSequentialGroup()
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSImg39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSImg40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSID39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSID40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSName39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSName40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDSpin39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDSpin40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JSPDesert.setViewportView(jPFDesert);

        jTDisplayType.addTab("Desert", JSPDesert);

        javax.swing.GroupLayout FoodTypeDPLayout = new javax.swing.GroupLayout(FoodTypeDP);
        FoodTypeDP.setLayout(FoodTypeDPLayout);
        FoodTypeDPLayout.setHorizontalGroup(
            FoodTypeDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTDisplayType, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        FoodTypeDPLayout.setVerticalGroup(
            FoodTypeDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTDisplayType, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JFNewFoodListWeekLayout = new javax.swing.GroupLayout(JFNewFoodListWeek);
        JFNewFoodListWeek.setLayout(JFNewFoodListWeekLayout);
        JFNewFoodListWeekLayout.setHorizontalGroup(
            JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(FoodTypeDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JFNewFoodListWeekLayout.createSequentialGroup()
                .addGroup(JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFNewFoodListWeekLayout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLThucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFNewFoodListWeekLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFNewFoodListWeekLayout.createSequentialGroup()
                                .addComponent(jLNgayBatDauTD, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDCTaoThucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBTaoThucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JFNewFoodListWeekLayout.createSequentialGroup()
                                .addComponent(TimNgayBatDauTD, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDCTimthucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBTimKieMThucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFNewFoodListWeekLayout.setVerticalGroup(
            JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFNewFoodListWeekLayout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(FoodTypeDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNgayBatDauTD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDCTaoThucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTaoThucDonTuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFNewFoodListWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimNgayBatDauTD)
                    .addComponent(jDCTimthucDonTuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTimKieMThucDonTuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLThucDonTuan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JFXemTinhTrang.setBackground(new java.awt.Color(255, 237, 58));
        JFXemTinhTrang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JFXemTinhTrang.setPreferredSize(new java.awt.Dimension(1685, 0));

        jTSuDungNL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Name", "Quantity"
            }
        ));
        jScrollPane6.setViewportView(jTSuDungNL);

        jLToDate.setText("To:");

        jLFroMDate.setText("From:");

        jDCdateEnd.setDateFormatString("dd/MM/yyyy");

        jLXemTTtxt.setText("View Status of:");

        jCBtTnlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingredients used", "Food used", "export to bar", "export to kitchen" }));

        jDCdateStart.setDateFormatString("dd/MM/yyyy");

        jBtnXemTT.setText("View");
        jBtnXemTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXemTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFXemTinhTrangLayout = new javax.swing.GroupLayout(JFXemTinhTrang);
        JFXemTinhTrang.setLayout(JFXemTinhTrangLayout);
        JFXemTinhTrangLayout.setHorizontalGroup(
            JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFXemTinhTrangLayout.createSequentialGroup()
                .addGroup(JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JFXemTinhTrangLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLXemTTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFXemTinhTrangLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLFroMDate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDCdateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFXemTinhTrangLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDCdateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jBtnXemTT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(213, 213, 213))
                    .addGroup(JFXemTinhTrangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBtTnlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        JFXemTinhTrangLayout.setVerticalGroup(
            JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFXemTinhTrangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBtTnlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLXemTTtxt))
                .addGap(18, 18, 18)
                .addGroup(JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JFXemTinhTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDCdateEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLFroMDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLToDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDCdateStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnXemTT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(JFNewFoodListWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JFXemTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDQuanLyLayout.setVerticalGroup(
            jDQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JFXemTinhTrang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addComponent(JFCustomerView, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addComponent(JFEmpManage, javax.swing.GroupLayout.PREFERRED_SIZE, 822, Short.MAX_VALUE)
            .addComponent(jPManagerControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDQuanLyLayout.createSequentialGroup()
                .addComponent(JFFoodManage, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(JFNewFoodListWeek, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jDkhoLayout = new javax.swing.GroupLayout(jDkho.getContentPane());
        jDkho.getContentPane().setLayout(jDkhoLayout);
        jDkhoLayout.setHorizontalGroup(
            jDkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDkhoLayout.setVerticalGroup(
            jDkhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        jPNewEmpAlert.setBackground(new java.awt.Color(255, 205, 15));

        jTNewEmpAlertTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTNewEmpAlertTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTNewEmpAlertTitle.setText("Text");

        jTNewEmpAlertDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTNewEmpAlertDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTNewEmpAlertDescription.setText("Text");

        jBNewEmpAlertBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBNewEmpAlertBtn.setText("jButton");
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

        jDChangePSMNG.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jDChangePSMNGComponentHidden(evt);
            }
        });

        jPNewPSBG.setBackground(new java.awt.Color(255, 237, 58));

        jBnNewPS.setText("Cập nhật");
        jBnNewPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnNewPSActionPerformed(evt);
            }
        });

        jLNewPSTitle.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLNewPSTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNewPSTitle.setText("Đổi mật khẩu");

        jLNewPS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNewPS.setText("Mật khẩu muốn đổi mới:");

        jBnNewPSOut.setText("Thoát");
        jBnNewPSOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnNewPSOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPNewPSBGLayout = new javax.swing.GroupLayout(jPNewPSBG);
        jPNewPSBG.setLayout(jPNewPSBGLayout);
        jPNewPSBGLayout.setHorizontalGroup(
            jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNewPSBGLayout.createSequentialGroup()
                .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNewPSBGLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLNewPSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNewPSBGLayout.createSequentialGroup()
                        .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPNewPSBGLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNewPSBGLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLNewPSAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPNewPSBGLayout.createSequentialGroup()
                                .addComponent(jBnNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBnNewPSOut, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPNewPSBGLayout.setVerticalGroup(
            jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNewPSBGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLNewPSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNewPSAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPNewPSBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBnNewPS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBnNewPSOut, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDChangePSMNGLayout = new javax.swing.GroupLayout(jDChangePSMNG.getContentPane());
        jDChangePSMNG.getContentPane().setLayout(jDChangePSMNGLayout);
        jDChangePSMNGLayout.setHorizontalGroup(
            jDChangePSMNGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPNewPSBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDChangePSMNGLayout.setVerticalGroup(
            jDChangePSMNGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDChangePSMNGLayout.createSequentialGroup()
                .addComponent(jPNewPSBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPMain.setBackground(new java.awt.Color(153, 255, 153));

        jPLogin.setBackground(new java.awt.Color(255, 153, 102));

        jLLoginTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLLoginTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLLoginTitle.setText("Login Title");

        jLUserName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLUserName.setText("UserName:");

        jTUserNametxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTUserNametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTUserNametxtActionPerformed(evt);
            }
        });

        jBnLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBnLogin.setText("Login");
        jBnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnLoginActionPerformed(evt);
            }
        });

        jLPassword.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLPassword.setText("Password:");

        jLLoginAlet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLLoginAlet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPLoginLayout = new javax.swing.GroupLayout(jPLogin);
        jPLogin.setLayout(jPLoginLayout);
        jPLoginLayout.setHorizontalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLoginLayout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addGroup(jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLLoginTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jTUserNametxt)
                    .addComponent(jLPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPPasswordtxt))
                .addGap(26, 26, 26))
            .addGroup(jPLoginLayout.createSequentialGroup()
                .addGroup(jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPLoginLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jBnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPLoginLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLLoginAlet, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPLoginLayout.setVerticalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLLoginTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTUserNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPPasswordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLLoginAlet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPMainLayout = new javax.swing.GroupLayout(jPMain);
        jPMain.setLayout(jPMainLayout);
        jPMainLayout.setHorizontalGroup(
            jPMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPMainLayout.createSequentialGroup()
                .addContainerGap(316, Short.MAX_VALUE)
                .addComponent(jPLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPMainLayout.setVerticalGroup(
            jPMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMainLayout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .addComponent(jPLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTUserNametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTUserNametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTUserNametxtActionPerformed

    private void jBnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnLoginActionPerformed
        try {
            String ql = "q";
            String bep = "bep";
            String kho = "kho";
            String thuNgan = "thungan";
            String bar = "bar";
            boolean tk = false;
            boolean mk = false;
            int m = 0;
            
            java.sql.Connection con = MyConnection.getConnection();
            String sql = "select UserName, Password, DepartmentID from Employee where UserName = ?";
            PreparedStatement pm = con.prepareCall(sql);
            pm.setString(1, jTUserNametxt.getText());
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {

                if (jTUserNametxt.getText().equals(rs.getString("UserName"))) {
                    jLLoginAlet.setText("");
                    tk = true;
                    m++;
                }
                if (jPPasswordtxt.getText().equals(rs.getString("Password"))) {
                    jLLoginAlet.setText("");
                    mk = true;
                    m++;
                }
                switch(rs.getString("DepartmentID")){
                    case "DP01":
                        m+=1;
                        break;
                    case "DP02":
                        m+=2;
                        break;
                    case "DP03":
                        m+=3;
                        break;
                    case "DP04":
                        m+=4;
                        break;
                    case "DP05":
                        m+=5;
                        break;
                }
            }
            
           
            if (m==3) {
                jDQuanLy.setTitle("Manager");
                jDQuanLy.setSize(1030, 730);
                jDQuanLy.setLocationRelativeTo(this);
                jDQuanLy.setVisible(true);
                JFEmpManage.setVisible(true);
                ShowAllEmpDate();
                JFCustomerView.setVisible(false);
                JFFoodManage.setVisible(false);
                JFNewFoodListWeek.setVisible(false);
                JFXemTinhTrang.setVisible(false);
                this.setVisible(false);
                jTNewPS.setText("");
                jLNewPSAlert.setText("");
            } else if (m==4) {
                jDBep.setTitle("Kitchen");
                jDBep.setSize(1030, 730);
                jDBep.setLocationRelativeTo(this);
                jDBep.setVisible(true);
            } else if (m==5) {
                jDkho.setTitle("WareHouse");
                jDkho.setSize(1030, 730);
                jDkho.setLocationRelativeTo(this);
                jDkho.setVisible(true);
            } else if (m==6) {
                jDThuNgan.setTitle("Cashier");
                jDThuNgan.setSize(1030, 730);
                jDThuNgan.setLocationRelativeTo(this);
                jDThuNgan.setVisible(true);
            } else if (m==7) {
                jDBar.setTitle("Bar");
                jDBar.setSize(1030, 730);
                jDBar.setLocationRelativeTo(this);
                jDBar.setVisible(true);
            } else {
                if (tk == false) {
                    jLLoginAlet.setText("Tài khoản hoặc mật khẩu không đúng");
                }

                if (mk == false) {
                    jLLoginAlet.setText("Tài khoản hoặc mật khẩu không đúng");
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jBnLoginActionPerformed

    private void jbManageEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbManageEmpActionPerformed
        JFEmpManage.setVisible(true);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JFNewFoodListWeek.setVisible(false);
        JFXemTinhTrang.setVisible(false);
        ShowAllEmpDate();
    }//GEN-LAST:event_jbManageEmpActionPerformed

    private void jbVieCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVieCustomerActionPerformed
        ShowAllCustomerViewManage();
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(true);
        JFFoodManage.setVisible(false);
        JFNewFoodListWeek.setVisible(false);
        JFXemTinhTrang.setVisible(false);

    }//GEN-LAST:event_jbVieCustomerActionPerformed

    private void jbManageFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbManageFoodActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(true);
        JFNewFoodListWeek.setVisible(false);
        JFXemTinhTrang.setVisible(false);
        ShowAllFood();

    }//GEN-LAST:event_jbManageFoodActionPerformed

    private void BtThucDonTuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtThucDonTuanActionPerformed
            
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JFNewFoodListWeek.setVisible(true);
        JFXemTinhTrang.setVisible(false);
        
        hienFoodAp();
        hienFoodMD();
        hienFoodDS();
            
    }//GEN-LAST:event_BtThucDonTuanActionPerformed

    private void Btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5ActionPerformed
        JFEmpManage.setVisible(false);
        JFCustomerView.setVisible(false);
        JFFoodManage.setVisible(false);
        JFNewFoodListWeek.setVisible(false);
        JFXemTinhTrang.setVisible(true);

    }//GEN-LAST:event_Btn5ActionPerformed

    private void Btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn6ActionPerformed
        jDChangePSMNG.setVisible(true);
        jDChangePSMNG.setSize(500, 260);
        jDChangePSMNG.setLocationRelativeTo(this);
        jDChangePSMNG.setTitle("Đổi mật khẩu quản lý");

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
            String EmpDelete1 = "delete List from List as a join FoodlistNextDays as b on a.FLDate=b.FLDate join Employee as c on b.EmployeeID=c.EmployeeID  where c.EmployeeID = ?";
            PreparedStatement pmEmpDelete1 = con.prepareStatement(EmpDelete1);
            pmEmpDelete1.setString(1, txtEmpID.getText());
            pmEmpDelete1.executeUpdate();

            pmEmpDelete1.close();
            String EmpDelet2e = "delete FoodlistNextDays from FoodlistNextDays as a join Employee as b on a.EmployeeID=b.EmployeeID  where b.EmployeeID = ?";
            PreparedStatement pmEmpDelete2 = con.prepareStatement(EmpDelet2e);
            pmEmpDelete2.setString(1, txtEmpID.getText());
            pmEmpDelete2.executeUpdate();

            pmEmpDelete2.close();
            String EmpDelete3 = "delete DrinkExportDetails from DrinkExportDetails as a join DrinkExport as b on a.DrinkExportID=b.DrinkExportID join Employee as c on b.EmployeeID=c.EmployeeID where c.EmployeeID = ?";
            PreparedStatement pmEmpDelete3 = con.prepareStatement(EmpDelete3);
            pmEmpDelete3.setString(1, txtEmpID.getText());
            pmEmpDelete3.executeUpdate();

            pmEmpDelete3.close();
            String EmpDelete4 = "delete DrinkExport from DrinkExport as a join Employee as b on a.EmployeeID=b.EmployeeID where b.EmployeeID = ?";
            PreparedStatement pmEmpDelete4 = con.prepareStatement(EmpDelete4);
            pmEmpDelete4.setString(1, txtEmpID.getText());
            pmEmpDelete4.executeUpdate();

            pmEmpDelete4.close();
            String EmpDelete5 = "delete Ticket from Ticket as a join Bill as b on a.BillID=b.BillID join Employee as c on b.EmployeeID=c.EmployeeID where c.EmployeeID = ?";
            PreparedStatement pmEmpDelete5 = con.prepareStatement(EmpDelete5);
            pmEmpDelete5.setString(1, txtEmpID.getText());
            pmEmpDelete5.executeUpdate();

            pmEmpDelete5.close();
            String EmpDelete6 = "delete Bill from Bill as a join Employee as b on a.EmployeeID=b.EmployeeID where b.EmployeeID= ?";
            PreparedStatement pmEmpDelete6 = con.prepareStatement(EmpDelete6);
            pmEmpDelete6.setString(1, txtEmpID.getText());
            pmEmpDelete6.executeUpdate();

            pmEmpDelete6.close();
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
            con.close();
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
        jLFImgLink.setText("file:"+model.getValueAt(selectedRowIndex, 4).toString());

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
        jTUserNametxt.setText("");
        jPPasswordtxt.setText("");
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
                con.close();
                JOptionPane.showMessageDialog(this, "Data insert success");
            } catch (ClassNotFoundException | SQLException ex) {
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
                jDNewEmpAlert.setTitle("cant save");
                jTNewEmpAlertTitle.setText("Can not save");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (F[0-9][0-9])");
                return;
            }

            String queryFid = "select FoodID from Food where FoodID=?";
            PreparedStatement pm1 = con.prepareCall(queryFid);
            pm1.setString(1, jTFID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (jTFID.getText().equals(rsEmpID.getString("FoodID")) == false) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant save");
                    jTNewEmpAlertTitle.setText("Can not save");
                    jTNewEmpAlertDescription.setText("ID khong ton tai");
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
                    jDNewEmpAlert.setTitle("cant Save");
                    jTNewEmpAlertTitle.setText("Can not Save");
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
            String insertNewFood = "update Food set FoodName=?, FDescription=?, FoodTypeID=?, Picture=? where FoodID =?";
            PreparedStatement pm = con.prepareCall(insertNewFood);
            pm.setString(1, jTFName.getText());
            pm.setString(2, jTADescription.getText());
            pm.setString(3, FTye);
            pm.setString(4, sublinkimg);
            pm.setString(5, jTFID.getText());
            pm.executeUpdate();

            con.close();
            pm.close();

            ShowAllFood();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
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

    private void jbSortByTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSortByTypeActionPerformed
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
    }//GEN-LAST:event_jbSortByTypeActionPerformed

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
            //System.out.println(ex);

//            EmpAlertDialog();
//                jDNewEmpAlert.setTitle("cant Searching");
//                jTNewEmpAlertTitle.setText("Can not Searching");
//                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
        }
    }//GEN-LAST:event_jBSearchingFoodActionPerformed

    private void jbShowDefaultFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbShowDefaultFoodActionPerformed
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
    }//GEN-LAST:event_jbShowDefaultFoodActionPerformed

    private void jBFDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFDeleteActionPerformed
        try {
            java.sql.Connection con = MyConnection.getConnection();

            //check usertname
            if (jTFID.getText().equals("")) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Delete");
                jTNewEmpAlertTitle.setText("Can not Delete");
                jTNewEmpAlertDescription.setText("Khong duoc bo trong ID");
                return;
            }

            String empid = jTFID.getText();
            if (empid.matches("F[0-9][0-9]") == false) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Delete");
                jTNewEmpAlertTitle.setText("Can not Delete");
                jTNewEmpAlertDescription.setText("ID khong dung dinh dang (F[0-9][0-9])");
                return;
            }

            String queryEmpID = "select FoodID from Food where FoodID=?";
            PreparedStatement pm1 = con.prepareCall(queryEmpID);
            pm1.setString(1, jTFID.getText());
            ResultSet rsEmpID = pm1.executeQuery();
            while (rsEmpID.next()) {
                if (jTFID.getText().equals(rsEmpID.getString("FoodID")) == false) {
                    EmpAlertDialog();
                    jDNewEmpAlert.setTitle("cant Delete");
                    jTNewEmpAlertTitle.setText("Can not Delete");
                    jTNewEmpAlertDescription.setText("ID Khong ton tai");
                    return;
                }
            }
            String fodListDelete1 = "delete from IngRequest where FoodID = ?";
            PreparedStatement pmfodLítDelete1 = con.prepareStatement(fodListDelete1);
            pmfodLítDelete1.setString(1, jTFID.getText());
            pmfodLítDelete1.executeUpdate();

            pmfodLítDelete1.close();
            
            String fodListDelete = "delete from List where FoodID = ?";
            PreparedStatement pmfodLítDelete = con.prepareStatement(fodListDelete);
            pmfodLítDelete.setString(1, jTFID.getText());
            pmfodLítDelete.executeUpdate();

            pmfodLítDelete.close();
            
            String EmpDelete = "delete from Food where FoodID = ?";
            PreparedStatement pmEmpDelete = con.prepareStatement(EmpDelete);
            pmEmpDelete.setString(1, jTFID.getText());
            pmEmpDelete.executeUpdate();

            pmEmpDelete.close();
            con.close();

            ShowAllFood();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBFDeleteActionPerformed

    private void jBEmpSearchBy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEmpSearchBy1ActionPerformed
        ShowAllEmpDate();
    }//GEN-LAST:event_jBEmpSearchBy1ActionPerformed

    private void jBTaoThucDonTuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTaoThucDonTuanActionPerformed
        try {
            jDCTaoThucDonTuan.getDate().toString();
        } catch (NullPointerException ex) {
            EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not Add");
                jTNewEmpAlertDescription.setText("Hãy chọn tuần tạo");
            return;
        }
        
        LocalDateTime ldt = jDCTaoThucDonTuan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DayOfWeek dow = ldt.getDayOfWeek();
        if (dow.name().equals("MONDAY") == false) {
            EmpAlertDialog();
            jDNewEmpAlert.setTitle("cant Add");
            jTNewEmpAlertTitle.setText("Can not Add");
            jTNewEmpAlertDescription.setText("Hãy chọn ngày đầu tuần");
            return;
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String dt = format.format(jDCTaoThucDonTuan.getDate());
            String dt2 = "";
            Connection con = MyConnection.getConnection();
            String queryfw = "select FLDate from FoodlistNextDays as a join Employee as b on a.EmployeeID = b.EmployeeID where FLDate=? and a.EmployeeID=?";
            PreparedStatement pm1 = con.prepareCall(queryfw);
            pm1.setString(1, dt);
            pm1.setString(2, "MN");
            ResultSet rsqueryfw = pm1.executeQuery();
            while (rsqueryfw.next()) {
                dt2 = rsqueryfw.getString("FLDate");
            }
            
            if(dt.equals(dt2)){
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Thực đơn của ngày này đã tồn tại");
                return;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    //mon khai vi
            
            
            List<JLabel> jLapID= new ArrayList<>();
            jLapID.add(jLapID1);jLapID.add(jLapID2);jLapID.add(jLapID3);jLapID.add(jLapID4);jLapID.add(jLapID5);jLapID.add(jLapID6);jLapID.add(jLapID7);jLapID.add(jLapID8);jLapID.add(jLapID9);jLapID.add(jLapID10);
            jLapID.add(jLapID11);jLapID.add(jLapID12);jLapID.add(jLapID13);jLapID.add(jLapID14);jLapID.add(jLapID15);jLapID.add(jLapID16);jLapID.add(jLapID17);jLapID.add(jLapID18);jLapID.add(jLapID19);jLapID.add(jLapID20);
            jLapID.add(jLapID21);jLapID.add(jLapID22);jLapID.add(jLapID23);jLapID.add(jLapID24);jLapID.add(jLapID25);jLapID.add(jLapID26);jLapID.add(jLapID27);jLapID.add(jLapID28);jLapID.add(jLapID29);jLapID.add(jLapID30);
            jLapID.add(jLapID31);jLapID.add(jLapID32);jLapID.add(jLapID33);jLapID.add(jLapID34);jLapID.add(jLapID35);jLapID.add(jLapID36);jLapID.add(jLapID37);jLapID.add(jLapID38);jLapID.add(jLapID39);jLapID.add(jLapID40);
            
            
            List<JSpinner> jLapSpin= new ArrayList<>();
            jLapSpin.add(jLapSpin1);jLapSpin.add(jLapSpin2);jLapSpin.add(jLapSpin3);jLapSpin.add(jLapSpin4);jLapSpin.add(jLapSpin5);jLapSpin.add(jLapSpin6);jLapSpin.add(jLapSpin7);jLapSpin.add(jLapSpin8);jLapSpin.add(jLapSpin9);jLapSpin.add(jLapSpin10);
            jLapSpin.add(jLapSpin11);jLapSpin.add(jLapSpin12);jLapSpin.add(jLapSpin13);jLapSpin.add(jLapSpin14);jLapSpin.add(jLapSpin15);jLapSpin.add(jLapSpin16);jLapSpin.add(jLapSpin17);jLapSpin.add(jLapSpin18);jLapSpin.add(jLapSpin19);jLapSpin.add(jLapSpin20);
            jLapSpin.add(jLapSpin21);jLapSpin.add(jLapSpin22);jLapSpin.add(jLapSpin23);jLapSpin.add(jLapSpin24);jLapSpin.add(jLapSpin25);jLapSpin.add(jLapSpin26);jLapSpin.add(jLapSpin27);jLapSpin.add(jLapSpin28);jLapSpin.add(jLapSpin29);jLapSpin.add(jLapSpin30);
            jLapSpin.add(jLapSpin31);jLapSpin.add(jLapSpin32);jLapSpin.add(jLapSpin33);jLapSpin.add(jLapSpin34);jLapSpin.add(jLapSpin35);jLapSpin.add(jLapSpin36);jLapSpin.add(jLapSpin37);jLapSpin.add(jLapSpin38);jLapSpin.add(jLapSpin39);jLapSpin.add(jLapSpin40);

    //mon chinh
           
            
            List<JLabel> jLMNID= new ArrayList<>();
            jLMNID.add(jLMNID1);jLMNID.add(jLMNID2);jLMNID.add(jLMNID3);jLMNID.add(jLMNID4);jLMNID.add(jLMNID5);jLMNID.add(jLMNID6);jLMNID.add(jLMNID7);jLMNID.add(jLMNID8);jLMNID.add(jLMNID9);jLMNID.add(jLMNID10);
            jLMNID.add(jLMNID11);jLMNID.add(jLMNID12);jLMNID.add(jLMNID13);jLMNID.add(jLMNID14);jLMNID.add(jLMNID15);jLMNID.add(jLMNID16);jLMNID.add(jLMNID17);jLMNID.add(jLMNID18);jLMNID.add(jLMNID19);jLMNID.add(jLMNID20);
            jLMNID.add(jLMNID21);jLMNID.add(jLMNID22);jLMNID.add(jLMNID23);jLMNID.add(jLMNID24);jLMNID.add(jLMNID25);jLMNID.add(jLMNID26);jLMNID.add(jLMNID27);jLMNID.add(jLMNID28);jLMNID.add(jLMNID29);jLMNID.add(jLMNID30);
            jLMNID.add(jLMNID31);jLMNID.add(jLMNID32);jLMNID.add(jLMNID33);jLMNID.add(jLMNID34);jLMNID.add(jLMNID35);jLMNID.add(jLMNID36);jLMNID.add(jLMNID37);jLMNID.add(jLMNID38);jLMNID.add(jLMNID39);jLMNID.add(jLMNID40);

            
            List<JSpinner> jLMNSpin= new ArrayList<>();
            jLMNSpin.add(jLMNSpin1);jLMNSpin.add(jLMNSpin2);jLMNSpin.add(jLMNSpin3);jLMNSpin.add(jLMNSpin4);jLMNSpin.add(jLMNSpin5);jLMNSpin.add(jLMNSpin6);jLMNSpin.add(jLMNSpin7);jLMNSpin.add(jLMNSpin8);jLMNSpin.add(jLMNSpin9);jLMNSpin.add(jLMNSpin10);
            jLMNSpin.add(jLMNSpin11);jLMNSpin.add(jLMNSpin12);jLMNSpin.add(jLMNSpin13);jLMNSpin.add(jLMNSpin14);jLMNSpin.add(jLMNSpin15);jLMNSpin.add(jLMNSpin16);jLMNSpin.add(jLMNSpin17);jLMNSpin.add(jLMNSpin18);jLMNSpin.add(jLMNSpin19);jLMNSpin.add(jLMNSpin20);
            jLMNSpin.add(jLMNSpin21);jLMNSpin.add(jLMNSpin22);jLMNSpin.add(jLMNSpin23);jLMNSpin.add(jLMNSpin24);jLMNSpin.add(jLMNSpin25);jLMNSpin.add(jLMNSpin26);jLMNSpin.add(jLMNSpin27);jLMNSpin.add(jLMNSpin28);jLMNSpin.add(jLMNSpin29);jLMNSpin.add(jLMNSpin30);
            jLMNSpin.add(jLMNSpin31);jLMNSpin.add(jLMNSpin32);jLMNSpin.add(jLMNSpin33);jLMNSpin.add(jLMNSpin34);jLMNSpin.add(jLMNSpin35);jLMNSpin.add(jLMNSpin36);jLMNSpin.add(jLMNSpin37);jLMNSpin.add(jLMNSpin38);jLMNSpin.add(jLMNSpin39);jLMNSpin.add(jLMNSpin40);
            
    //mon tang mieng
            
            
            List<JLabel> jLDSID= new ArrayList<>();
            jLDSID.add(jLDSID1);jLDSID.add(jLDSID2);jLDSID.add(jLDSID3);jLDSID.add(jLDSID4);jLDSID.add(jLDSID5);jLDSID.add(jLDSID6);jLDSID.add(jLDSID7);jLDSID.add(jLDSID8);jLDSID.add(jLDSID9);jLDSID.add(jLDSID10);
            jLDSID.add(jLDSID11);jLDSID.add(jLDSID12);jLDSID.add(jLDSID13);jLDSID.add(jLDSID14);jLDSID.add(jLDSID15);jLDSID.add(jLDSID16);jLDSID.add(jLDSID17);jLDSID.add(jLDSID18);jLDSID.add(jLDSID19);jLDSID.add(jLDSID20);
            jLDSID.add(jLDSID21);jLDSID.add(jLDSID22);jLDSID.add(jLDSID23);jLDSID.add(jLDSID24);jLDSID.add(jLDSID25);jLDSID.add(jLDSID26);jLDSID.add(jLDSID27);jLDSID.add(jLDSID28);jLDSID.add(jLDSID29);jLDSID.add(jLDSID30);
            jLDSID.add(jLDSID31);jLDSID.add(jLDSID32);jLDSID.add(jLDSID33);jLDSID.add(jLDSID34);jLDSID.add(jLDSID35);jLDSID.add(jLDSID36);jLDSID.add(jLDSID37);jLDSID.add(jLDSID38);jLDSID.add(jLDSID39);jLDSID.add(jLDSID40);
            
            
            List<JSpinner> jDSSpin= new ArrayList<>();
            jDSSpin.add(jLDSpin1);jDSSpin.add(jLDSpin2);jDSSpin.add(jLDSpin3);jDSSpin.add(jLDSpin4);jDSSpin.add(jLDSpin5);jDSSpin.add(jLDSpin6);jDSSpin.add(jLDSpin7);jDSSpin.add(jLDSpin8);jDSSpin.add(jLDSpin9);jDSSpin.add(jLDSpin10);
            jDSSpin.add(jLDSpin11);jDSSpin.add(jLDSpin12);jDSSpin.add(jLDSpin13);jDSSpin.add(jLDSpin14);jDSSpin.add(jLDSpin15);jDSSpin.add(jLDSpin16);jDSSpin.add(jLDSpin17);jDSSpin.add(jLDSpin18);jDSSpin.add(jLDSpin19);jDSSpin.add(jLDSpin20);
            jDSSpin.add(jLDSpin21);jDSSpin.add(jLDSpin22);jDSSpin.add(jLDSpin23);jDSSpin.add(jLDSpin24);jDSSpin.add(jLDSpin25);jDSSpin.add(jLDSpin26);jDSSpin.add(jLDSpin27);jDSSpin.add(jLDSpin28);jDSSpin.add(jLDSpin29);jDSSpin.add(jLDSpin30);
            jDSSpin.add(jLDSpin31);jDSSpin.add(jLDSpin32);jDSSpin.add(jLDSpin33);jDSSpin.add(jLDSpin34);jDSSpin.add(jLDSpin35);jDSSpin.add(jLDSpin36);jDSSpin.add(jLDSpin37);jDSSpin.add(jLDSpin38);jDSSpin.add(jLDSpin39);jDSSpin.add(jLDSpin40);
    
            
            
            try {
            Connection con = MyConnection.getConnection();
                
            String fldate = "insert into FoodlistNextDays values(?,?)";
                PreparedStatement pmfldate = con.prepareStatement(fldate);
                pmfldate.setString(1, format.format(jDCTaoThucDonTuan.getDate()));
                pmfldate.setString(2, "MN");
                pmfldate.executeUpdate();

                pmfldate.close();
            }catch(ClassNotFoundException | SQLException e){
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("cant Add");
                jTNewEmpAlertTitle.setText("Can not add");
                jTNewEmpAlertDescription.setText("Thực đơn ngày này đã tồn tại!");
                return;
            }
            try {
            Connection con = MyConnection.getConnection();
            for(int i = 0; i < 40; i++){
                if(jLapID.get(i).isVisible()){
                    String FWadd = "insert into List values(?,?,?)";
                    PreparedStatement pmFWadd = con.prepareStatement(FWadd);
                    pmFWadd.setString(1, jLapID.get(i).getText());
                    pmFWadd.setString(2, format.format(jDCTaoThucDonTuan.getDate()));
                    pmFWadd.setString(3, jLapSpin.get(i).getValue().toString());
                    pmFWadd.executeUpdate();
                    pmFWadd.close();
                    
                    String FWadd1 = "insert into List values(?,?,?)";
                    PreparedStatement pmFWadd1 = con.prepareStatement(FWadd1);
                    pmFWadd1.setString(1, jLMNID.get(i).getText());
                    pmFWadd1.setString(2, format.format(jDCTaoThucDonTuan.getDate()));
                    pmFWadd1.setString(3, jLMNSpin.get(i).getValue().toString());
                    pmFWadd1.executeUpdate();
                    pmFWadd1.close();
                    
                    String FWadd2 = "insert into List values(?,?,?)";
                    PreparedStatement pmFWadd2 = con.prepareStatement(FWadd2);
                    pmFWadd2.setString(1, jLDSID.get(i).getText());
                    pmFWadd2.setString(2, format.format(jDCTaoThucDonTuan.getDate()));
                    pmFWadd2.setString(3, jDSSpin.get(i).getValue().toString());
                    pmFWadd2.executeUpdate();
                    pmFWadd2.close();
                    
                }else{
                    continue;
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
                EmpAlertDialog();
                jDNewEmpAlert.setTitle("Add success");
                jTNewEmpAlertTitle.setText("Add success");
                jTNewEmpAlertDescription.setText("Thêm thực đơn tuần thành công");
                return;
        }
    }//GEN-LAST:event_jBTaoThucDonTuanActionPerformed

    private void jBTimKieMThucDonTuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKieMThucDonTuanActionPerformed

        try {
            jDCTimthucDonTuan.getDate().toString();
        } catch (NullPointerException ex) {
            jLThucDonTuan.setText("Thực đơn tuần từ: --/--/----");
            return;
        }
        LocalDateTime ldt = jDCTimthucDonTuan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        DayOfWeek dow = ldt.getDayOfWeek();
        if (dow.name().equals("MONDAY") == false) {
            EmpAlertDialog();
            jDNewEmpAlert.setTitle("cant Search");
            jTNewEmpAlertTitle.setText("Can not Search");
            jTNewEmpAlertDescription.setText("Hãy chọn ngày đầu tuần");
        } else {
            Vector vctdata = new Vector();
            Vector vcthead = new Vector();
            vcthead.add("FoodID");
            vcthead.add("Name");
            vcthead.add("Type");
            vcthead.add("Quantity");
            try {
                String find = "select a.FoodID, FoodName, FTypeName, Quantity from Food as a join List as b on a.FoodID = b.FoodID join FoodType as c on a.FoodTypeID = c.FoodTypeID join FoodlistNextDays as d on b.FLDate=d.FLDate join Employee as e on d.EmployeeID=e.EmployeeID where b.FLDate =? and e.EmployeeID=? order by FoodID";
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                Connection con = MyConnection.getConnection();
                PreparedStatement pm = con.prepareCall(find);
                String datefm = format.format(jDCTimthucDonTuan.getDate());
                pm.setString(1, datefm);
                pm.setString(2, "MN");
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {
                    Vector vt = new Vector();
                    vt.add(rs.getString(1));
                    vt.add(rs.getString(2));
                    vt.add(rs.getString(3));
                    vt.add(rs.getString(4));

                    vctdata.add(vt);
                }
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                String thucdondautuanfm = format2.format(jDCTimthucDonTuan.getDate());
                jLThucDonTuan.setText("Thực đơn tuần từ: "+thucdondautuanfm);
                jTThucDonTuan.setModel(new DefaultTableModel(vctdata, vcthead));
            } catch (ClassNotFoundException | SQLException ex) {
                //System.out.println(ex);

//            EmpAlertDialog();
//                jDNewEmpAlert.setTitle("cant Searching");
//                jTNewEmpAlertTitle.setText("Can not Searching");
//                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
            }
        }
    }//GEN-LAST:event_jBTimKieMThucDonTuanActionPerformed

    private void jTDisplayTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDisplayTypeMouseClicked

    }//GEN-LAST:event_jTDisplayTypeMouseClicked

    private void jBtnXemTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXemTTActionPerformed
//        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = "";
        String date2 = "";
          
         
        try {
            date1 = format.format(jDCdateStart.getDate());
        } catch (NullPointerException ex) {
            return;
        }
        try {
            date2 = format.format(jDCdateEnd.getDate());
        } catch (NullPointerException ex) {
            return;
        }
        
        int result = date1.compareTo(date2);
        if(result > 0){
            EmpAlertDialog();
            jDNewEmpAlert.setTitle("cant Searching");
            jTNewEmpAlertTitle.setText("Can not Searching");
            jTNewEmpAlertDescription.setText("khoảng thời gian không hợp lệ");
            return;
        } 
        LocalDate start = LocalDate.parse(date1);
        LocalDate end = LocalDate.parse(date2);
        List<LocalDate> totalDates = new ArrayList<>();
        
        String selectItem = jCBtTnlieu.getSelectedItem().toString();
        switch(selectItem){
            case "Ingredients used":
                try {
                while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
                }

                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("Date");
                vcthead.add("Ingredients Name");
                vcthead.add("Quantity"); 
               // vcthead.add("Drinks Name");
                //vcthead.add("Quantity");

                //Thêm Where 
                    Connection con = MyConnection.getConnection();
                    for(int i = 0; i < totalDates.size(); i++){
                        String find = "select IRDate, FIName, sum(quantity) as Quantity from IngRequest as a join FoodIngredients as b on a.IngredientsID = b.IngredientsID where IRDate = ? group by IRDate, FIName";
                        String time = totalDates.get(i).toString();

                        PreparedStatement pm = con.prepareCall(find); 
                        pm.setString(1, time);
                        ResultSet rs = pm.executeQuery();
                    
                        while(rs.next()){
                            Vector vt = new Vector();
                            vt.add(rs.getString(1));
                            vt.add(rs.getString(2));
                            vt.add(rs.getString(3));
                           //vt.add(rs.getString(4));
                            //vt.add(rs.getString(5));

                            vctdata.add(vt);
                        }  
                    }
                    jTSuDungNL.setModel(new DefaultTableModel(vctdata, vcthead));
                    con.close();
                } catch (ClassNotFoundException | SQLException ex) {
                     System.out.println(ex);

    //            EmpAlertDialog();
    //                jDNewEmpAlert.setTitle("cant Searching");
    //                jTNewEmpAlertTitle.setText("Can not Searching");
    //                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
                }
                break;
                case "Food used":
                try {
                while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
                }

                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("Date");
                vcthead.add("FoodName");
                vcthead.add("Quantity"); 
               // vcthead.add("Drinks Name");
                //vcthead.add("Quantity");

                //Thêm Where 
                    Connection con = MyConnection.getConnection();
                    for(int i = 0; i < totalDates.size(); i++){
                        String find = "select a.FLDate, FoodName , sum(quantity) as Quantity from List as a join FoodlistNextDays as b on a.FLDate = b.FLDate join Food as c on a.FoodID = c.FoodID join Employee as d on d.EmployeeID=b.EmployeeID where b.FLDate = ? and DepartmentID = ? group by a.FLDate, FoodName";
                        String time = totalDates.get(i).toString();

                        PreparedStatement pm = con.prepareCall(find); 
                        pm.setString(1, time);
                        // mã bộ phận bếp!
                        pm.setString(2, "DP02");
                        ResultSet rs = pm.executeQuery();
                    
                        while(rs.next()){
                            Vector vt = new Vector();
                            vt.add(rs.getString(1));
                            vt.add(rs.getString(2));
                            vt.add(rs.getString(3));
                           //vt.add(rs.getString(4));
                            //vt.add(rs.getString(5));

                            vctdata.add(vt);
                        }  
                    }
                    jTSuDungNL.setModel(new DefaultTableModel(vctdata, vcthead));
                    con.close();
                } catch (ClassNotFoundException | SQLException ex) {
                     System.out.println(ex);

    //            EmpAlertDialog();
    //                jDNewEmpAlert.setTitle("cant Searching");
    //                jTNewEmpAlertTitle.setText("Can not Searching");
    //                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
                }
                break;
            case "export to bar":
                try {
                while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
                }

                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("Date");
                vcthead.add("DrinksName");
                vcthead.add("Quantity"); 
               // vcthead.add("Drinks Name");
                //vcthead.add("Quantity");

                //Thêm Where 
                    Connection con = MyConnection.getConnection();
                    for(int i = 0; i < totalDates.size(); i++){
                        String find = "select EDate, DrinkName, sum(quantity) as Quantity from DrinkExport as a join DrinkExportDetails as b on a.DrinkExportID = b.DrinkExportID join Drinks as c on b.DrinksID = c.DrinksID where a.EDate = ? group by a.EDate, DrinkName";
                        String time = totalDates.get(i).toString();

                        PreparedStatement pm = con.prepareCall(find); 
                        pm.setString(1, time);
                        // mã bộ phận bếp!
                        ResultSet rs = pm.executeQuery();
                    
                        while(rs.next()){
                            Vector vt = new Vector();
                            vt.add(rs.getString(1));
                            vt.add(rs.getString(2));
                            vt.add(rs.getString(3));
                           //vt.add(rs.getString(4));
                            //vt.add(rs.getString(5));

                            vctdata.add(vt);
                        }  
                    }
                    jTSuDungNL.setModel(new DefaultTableModel(vctdata, vcthead));
                    con.close();
                } catch (ClassNotFoundException | SQLException ex) {
                     System.out.println(ex);

    //            EmpAlertDialog();
    //                jDNewEmpAlert.setTitle("cant Searching");
    //                jTNewEmpAlertTitle.setText("Can not Searching");
    //                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
                }
                break;
            case "export to kitchen":
                try {
                while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
                }

                Vector vctdata = new Vector();
                Vector vcthead = new Vector();
                vcthead.add("Date");
                vcthead.add("DrinksName");
                vcthead.add("Quantity"); 
               // vcthead.add("Drinks Name");
                //vcthead.add("Quantity");

                //Thêm Where 
                    Connection con = MyConnection.getConnection();
                    for(int i = 0; i < totalDates.size(); i++){
                        String find = "select d.IRDate, FIName, d.Quantity from List as a join FoodlistNextDays as b on a.FLDate=b.FLDate join Food as c on a.FoodID=c.FoodID join IngRequest as d on c.FoodID=d.FoodID join FoodIngredients as e on d.IngredientsID=e.IngredientsID join Employee as f on b.EmployeeID=f.EmployeeID where b.FLDate = ? and DepartmentID= ?";
                        String time = totalDates.get(i).toString();

                        PreparedStatement pm = con.prepareCall(find); 
                        pm.setString(1, time);
                        pm.setString(2, "DP02");
                        // mã bộ phận bếp!
                        ResultSet rs = pm.executeQuery();
                    
                        while(rs.next()){
                            Vector vt = new Vector();
                            vt.add(rs.getString(1));
                            vt.add(rs.getString(2));
                            vt.add(rs.getString(3));
                           //vt.add(rs.getString(4));
                            //vt.add(rs.getString(5));

                            vctdata.add(vt);
                        }  
                    }
                    jTSuDungNL.setModel(new DefaultTableModel(vctdata, vcthead));
                    con.close();
                } catch (ClassNotFoundException | SQLException ex) {
                     System.out.println(ex);

    //            EmpAlertDialog();
    //                jDNewEmpAlert.setTitle("cant Searching");
    //                jTNewEmpAlertTitle.setText("Can not Searching");
    //                jTNewEmpAlertDescription.setText("Vui long chon cach tim");
                }
                break;
        }
         
    }//GEN-LAST:event_jBtnXemTTActionPerformed

    private void jBnNewPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnNewPSActionPerformed
        try {
            Connection con = MyConnection.getConnection();
            if(jTNewPS.getText().equals("")){
                jLNewPSAlert.setText("Hãy điền mật khẩu mới");
                return;
            }else{
                jLNewPSAlert.setText("");
            }
            String sql = "Update Employee set Password = ? where EmployeeID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jTNewPS.getText());
            ps.setString(2, "MN");
            ps.executeUpdate();
            
            jLNewPSAlert.setText("Cập nhật thành công!");
            con.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jBnNewPSActionPerformed

    private void jDChangePSMNGComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDChangePSMNGComponentHidden
        jTNewPS.setText("");
        jLNewPSAlert.setText("");
    }//GEN-LAST:event_jDChangePSMNGComponentHidden

    private void jBnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnDangXuatActionPerformed
        jDQuanLy.setVisible(false);
        jPMain.setVisible(true);
        jTUserNametxt.setText("");
        jPPasswordtxt.setText("");
    }//GEN-LAST:event_jBnDangXuatActionPerformed

    private void jBnNewPSOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnNewPSOutActionPerformed
         jDChangePSMNG.setVisible(false);
         jTNewPS.setText("");
         jLNewPSAlert.setText("");
    }//GEN-LAST:event_jBnNewPSOutActionPerformed
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
    private javax.swing.JButton BtThucDonTuan;
    private javax.swing.JButton Btn14;
    private javax.swing.JButton Btn21;
    private javax.swing.JButton Btn22;
    private javax.swing.JButton Btn23;
    private javax.swing.JButton Btn24;
    private javax.swing.JButton Btn25;
    private javax.swing.JButton Btn26;
    private javax.swing.JButton Btn27;
    private javax.swing.JButton Btn28;
    private javax.swing.JButton Btn35;
    private javax.swing.JButton Btn36;
    private javax.swing.JButton Btn43;
    private javax.swing.JButton Btn44;
    private javax.swing.JButton Btn45;
    private javax.swing.JButton Btn46;
    private javax.swing.JButton Btn47;
    private javax.swing.JButton Btn48;
    private javax.swing.JButton Btn5;
    private javax.swing.JButton Btn6;
    private javax.swing.JButton Btn7;
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
    private javax.swing.JLabel EID3;
    private javax.swing.JLabel EID5;
    private javax.swing.JLabel EID7;
    private javax.swing.JLabel EName;
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
    private javax.swing.JPanel FoodTypeDP;
    private javax.swing.JPanel JF12;
    private javax.swing.JPanel JF13;
    private javax.swing.JPanel JF14;
    private javax.swing.JPanel JF15;
    private javax.swing.JPanel JF16;
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
    private javax.swing.JPanel JFCustomerView;
    private javax.swing.JPanel JFEmpManage;
    private javax.swing.JPanel JFFoodManage;
    private javax.swing.JPanel JFNewFoodListWeek;
    private javax.swing.JPanel JFXemTinhTrang;
    private javax.swing.JScrollPane JSPDesert;
    private javax.swing.JButton SaveEmpBtn;
    private javax.swing.JLabel TimNgayBatDauTD;
    private javax.swing.JLabel Title;
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
    private javax.swing.JButton jBEmpSearchBy1;
    private javax.swing.JButton jBFDelete;
    private javax.swing.JButton jBFImg;
    private javax.swing.JButton jBFoodClear;
    private javax.swing.JButton jBNewEmpAlertBtn;
    private javax.swing.JButton jBSaveFood;
    private javax.swing.JButton jBSearchingFood;
    private javax.swing.JButton jBSortFoodID;
    private javax.swing.JButton jBTaoThucDonTuan;
    private javax.swing.JButton jBTimKieMThucDonTuan;
    private javax.swing.JButton jBnDangXuat;
    private javax.swing.JButton jBnLogin;
    private javax.swing.JButton jBnNewPS;
    private javax.swing.JButton jBnNewPSOut;
    private javax.swing.JButton jBtnXemTT;
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
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton60;
    private javax.swing.JComboBox<String> jCBFoodType;
    private javax.swing.JComboBox<String> jCBtTnlieu;
    private javax.swing.JDialog jDBar;
    private javax.swing.JDialog jDBep;
    private com.toedter.calendar.JDateChooser jDCTaoThucDonTuan;
    private com.toedter.calendar.JDateChooser jDCTimthucDonTuan;
    private com.toedter.calendar.JDateChooser jDCdateEnd;
    private com.toedter.calendar.JDateChooser jDCdateStart;
    private javax.swing.JDialog jDChangePSMNG;
    private javax.swing.JDialog jDNewEmpAlert;
    private javax.swing.JDialog jDQuanLy;
    private javax.swing.JDialog jDThuNgan;
    private javax.swing.JDialog jDkho;
    private javax.swing.JLabel jLCustomerBirthday;
    private javax.swing.JLabel jLCustomerId;
    private javax.swing.JLabel jLCustomerIdenNum;
    private javax.swing.JLabel jLCustomerPhone;
    private javax.swing.JLabel jLCustomerPoint;
    private javax.swing.JLabel jLDSID1;
    private javax.swing.JLabel jLDSID10;
    private javax.swing.JLabel jLDSID11;
    private javax.swing.JLabel jLDSID12;
    private javax.swing.JLabel jLDSID13;
    private javax.swing.JLabel jLDSID14;
    private javax.swing.JLabel jLDSID15;
    private javax.swing.JLabel jLDSID16;
    private javax.swing.JLabel jLDSID17;
    private javax.swing.JLabel jLDSID18;
    private javax.swing.JLabel jLDSID19;
    private javax.swing.JLabel jLDSID2;
    private javax.swing.JLabel jLDSID20;
    private javax.swing.JLabel jLDSID21;
    private javax.swing.JLabel jLDSID22;
    private javax.swing.JLabel jLDSID23;
    private javax.swing.JLabel jLDSID24;
    private javax.swing.JLabel jLDSID25;
    private javax.swing.JLabel jLDSID26;
    private javax.swing.JLabel jLDSID27;
    private javax.swing.JLabel jLDSID28;
    private javax.swing.JLabel jLDSID29;
    private javax.swing.JLabel jLDSID3;
    private javax.swing.JLabel jLDSID30;
    private javax.swing.JLabel jLDSID31;
    private javax.swing.JLabel jLDSID32;
    private javax.swing.JLabel jLDSID33;
    private javax.swing.JLabel jLDSID34;
    private javax.swing.JLabel jLDSID35;
    private javax.swing.JLabel jLDSID36;
    private javax.swing.JLabel jLDSID37;
    private javax.swing.JLabel jLDSID38;
    private javax.swing.JLabel jLDSID39;
    private javax.swing.JLabel jLDSID4;
    private javax.swing.JLabel jLDSID40;
    private javax.swing.JLabel jLDSID5;
    private javax.swing.JLabel jLDSID6;
    private javax.swing.JLabel jLDSID7;
    private javax.swing.JLabel jLDSID8;
    private javax.swing.JLabel jLDSID9;
    private javax.swing.JLabel jLDSImg1;
    private javax.swing.JLabel jLDSImg10;
    private javax.swing.JLabel jLDSImg11;
    private javax.swing.JLabel jLDSImg12;
    private javax.swing.JLabel jLDSImg13;
    private javax.swing.JLabel jLDSImg14;
    private javax.swing.JLabel jLDSImg15;
    private javax.swing.JLabel jLDSImg16;
    private javax.swing.JLabel jLDSImg17;
    private javax.swing.JLabel jLDSImg18;
    private javax.swing.JLabel jLDSImg19;
    private javax.swing.JLabel jLDSImg2;
    private javax.swing.JLabel jLDSImg20;
    private javax.swing.JLabel jLDSImg21;
    private javax.swing.JLabel jLDSImg22;
    private javax.swing.JLabel jLDSImg23;
    private javax.swing.JLabel jLDSImg24;
    private javax.swing.JLabel jLDSImg25;
    private javax.swing.JLabel jLDSImg26;
    private javax.swing.JLabel jLDSImg27;
    private javax.swing.JLabel jLDSImg28;
    private javax.swing.JLabel jLDSImg29;
    private javax.swing.JLabel jLDSImg3;
    private javax.swing.JLabel jLDSImg30;
    private javax.swing.JLabel jLDSImg31;
    private javax.swing.JLabel jLDSImg32;
    private javax.swing.JLabel jLDSImg33;
    private javax.swing.JLabel jLDSImg34;
    private javax.swing.JLabel jLDSImg35;
    private javax.swing.JLabel jLDSImg36;
    private javax.swing.JLabel jLDSImg37;
    private javax.swing.JLabel jLDSImg38;
    private javax.swing.JLabel jLDSImg39;
    private javax.swing.JLabel jLDSImg4;
    private javax.swing.JLabel jLDSImg40;
    private javax.swing.JLabel jLDSImg5;
    private javax.swing.JLabel jLDSImg6;
    private javax.swing.JLabel jLDSImg7;
    private javax.swing.JLabel jLDSImg8;
    private javax.swing.JLabel jLDSImg9;
    private javax.swing.JLabel jLDSName1;
    private javax.swing.JLabel jLDSName10;
    private javax.swing.JLabel jLDSName11;
    private javax.swing.JLabel jLDSName12;
    private javax.swing.JLabel jLDSName13;
    private javax.swing.JLabel jLDSName14;
    private javax.swing.JLabel jLDSName15;
    private javax.swing.JLabel jLDSName16;
    private javax.swing.JLabel jLDSName17;
    private javax.swing.JLabel jLDSName18;
    private javax.swing.JLabel jLDSName19;
    private javax.swing.JLabel jLDSName2;
    private javax.swing.JLabel jLDSName20;
    private javax.swing.JLabel jLDSName21;
    private javax.swing.JLabel jLDSName22;
    private javax.swing.JLabel jLDSName23;
    private javax.swing.JLabel jLDSName24;
    private javax.swing.JLabel jLDSName25;
    private javax.swing.JLabel jLDSName26;
    private javax.swing.JLabel jLDSName27;
    private javax.swing.JLabel jLDSName28;
    private javax.swing.JLabel jLDSName29;
    private javax.swing.JLabel jLDSName3;
    private javax.swing.JLabel jLDSName30;
    private javax.swing.JLabel jLDSName31;
    private javax.swing.JLabel jLDSName32;
    private javax.swing.JLabel jLDSName33;
    private javax.swing.JLabel jLDSName34;
    private javax.swing.JLabel jLDSName35;
    private javax.swing.JLabel jLDSName36;
    private javax.swing.JLabel jLDSName37;
    private javax.swing.JLabel jLDSName38;
    private javax.swing.JLabel jLDSName39;
    private javax.swing.JLabel jLDSName4;
    private javax.swing.JLabel jLDSName40;
    private javax.swing.JLabel jLDSName5;
    private javax.swing.JLabel jLDSName6;
    private javax.swing.JLabel jLDSName7;
    private javax.swing.JLabel jLDSName8;
    private javax.swing.JLabel jLDSName9;
    private javax.swing.JSpinner jLDSpin1;
    private javax.swing.JSpinner jLDSpin10;
    private javax.swing.JSpinner jLDSpin11;
    private javax.swing.JSpinner jLDSpin12;
    private javax.swing.JSpinner jLDSpin13;
    private javax.swing.JSpinner jLDSpin14;
    private javax.swing.JSpinner jLDSpin15;
    private javax.swing.JSpinner jLDSpin16;
    private javax.swing.JSpinner jLDSpin17;
    private javax.swing.JSpinner jLDSpin18;
    private javax.swing.JSpinner jLDSpin19;
    private javax.swing.JSpinner jLDSpin2;
    private javax.swing.JSpinner jLDSpin20;
    private javax.swing.JSpinner jLDSpin21;
    private javax.swing.JSpinner jLDSpin22;
    private javax.swing.JSpinner jLDSpin23;
    private javax.swing.JSpinner jLDSpin24;
    private javax.swing.JSpinner jLDSpin25;
    private javax.swing.JSpinner jLDSpin26;
    private javax.swing.JSpinner jLDSpin27;
    private javax.swing.JSpinner jLDSpin28;
    private javax.swing.JSpinner jLDSpin29;
    private javax.swing.JSpinner jLDSpin3;
    private javax.swing.JSpinner jLDSpin30;
    private javax.swing.JSpinner jLDSpin31;
    private javax.swing.JSpinner jLDSpin32;
    private javax.swing.JSpinner jLDSpin33;
    private javax.swing.JSpinner jLDSpin34;
    private javax.swing.JSpinner jLDSpin35;
    private javax.swing.JSpinner jLDSpin36;
    private javax.swing.JSpinner jLDSpin37;
    private javax.swing.JSpinner jLDSpin38;
    private javax.swing.JSpinner jLDSpin39;
    private javax.swing.JSpinner jLDSpin4;
    private javax.swing.JSpinner jLDSpin40;
    private javax.swing.JSpinner jLDSpin5;
    private javax.swing.JSpinner jLDSpin6;
    private javax.swing.JSpinner jLDSpin7;
    private javax.swing.JSpinner jLDSpin8;
    private javax.swing.JSpinner jLDSpin9;
    private javax.swing.JLabel jLEmpTitle;
    private javax.swing.JLabel jLEmpTitle1;
    private javax.swing.JLabel jLEmpTitle2;
    private javax.swing.JLabel jLFDescription;
    private javax.swing.JLabel jLFID;
    private javax.swing.JLabel jLFImgLink;
    private javax.swing.JLabel jLFName;
    private javax.swing.JLabel jLFType;
    private javax.swing.JLabel jLFoodImg;
    private javax.swing.JLabel jLFroMDate;
    private javax.swing.JLabel jLFullName;
    private javax.swing.JLabel jLLoginAlet;
    private javax.swing.JLabel jLLoginTitle;
    private javax.swing.JLabel jLMDImg1;
    private javax.swing.JLabel jLMDImg10;
    private javax.swing.JLabel jLMDImg11;
    private javax.swing.JLabel jLMDImg12;
    private javax.swing.JLabel jLMDImg13;
    private javax.swing.JLabel jLMDImg14;
    private javax.swing.JLabel jLMDImg15;
    private javax.swing.JLabel jLMDImg16;
    private javax.swing.JLabel jLMDImg17;
    private javax.swing.JLabel jLMDImg18;
    private javax.swing.JLabel jLMDImg19;
    private javax.swing.JLabel jLMDImg2;
    private javax.swing.JLabel jLMDImg20;
    private javax.swing.JLabel jLMDImg21;
    private javax.swing.JLabel jLMDImg22;
    private javax.swing.JLabel jLMDImg23;
    private javax.swing.JLabel jLMDImg24;
    private javax.swing.JLabel jLMDImg25;
    private javax.swing.JLabel jLMDImg26;
    private javax.swing.JLabel jLMDImg27;
    private javax.swing.JLabel jLMDImg28;
    private javax.swing.JLabel jLMDImg29;
    private javax.swing.JLabel jLMDImg3;
    private javax.swing.JLabel jLMDImg30;
    private javax.swing.JLabel jLMDImg31;
    private javax.swing.JLabel jLMDImg32;
    private javax.swing.JLabel jLMDImg33;
    private javax.swing.JLabel jLMDImg34;
    private javax.swing.JLabel jLMDImg35;
    private javax.swing.JLabel jLMDImg36;
    private javax.swing.JLabel jLMDImg37;
    private javax.swing.JLabel jLMDImg38;
    private javax.swing.JLabel jLMDImg39;
    private javax.swing.JLabel jLMDImg4;
    private javax.swing.JLabel jLMDImg40;
    private javax.swing.JLabel jLMDImg5;
    private javax.swing.JLabel jLMDImg6;
    private javax.swing.JLabel jLMDImg7;
    private javax.swing.JLabel jLMDImg8;
    private javax.swing.JLabel jLMDImg9;
    private javax.swing.JLabel jLMNID1;
    private javax.swing.JLabel jLMNID10;
    private javax.swing.JLabel jLMNID11;
    private javax.swing.JLabel jLMNID12;
    private javax.swing.JLabel jLMNID13;
    private javax.swing.JLabel jLMNID14;
    private javax.swing.JLabel jLMNID15;
    private javax.swing.JLabel jLMNID16;
    private javax.swing.JLabel jLMNID17;
    private javax.swing.JLabel jLMNID18;
    private javax.swing.JLabel jLMNID19;
    private javax.swing.JLabel jLMNID2;
    private javax.swing.JLabel jLMNID20;
    private javax.swing.JLabel jLMNID21;
    private javax.swing.JLabel jLMNID22;
    private javax.swing.JLabel jLMNID23;
    private javax.swing.JLabel jLMNID24;
    private javax.swing.JLabel jLMNID25;
    private javax.swing.JLabel jLMNID26;
    private javax.swing.JLabel jLMNID27;
    private javax.swing.JLabel jLMNID28;
    private javax.swing.JLabel jLMNID29;
    private javax.swing.JLabel jLMNID3;
    private javax.swing.JLabel jLMNID30;
    private javax.swing.JLabel jLMNID31;
    private javax.swing.JLabel jLMNID32;
    private javax.swing.JLabel jLMNID33;
    private javax.swing.JLabel jLMNID34;
    private javax.swing.JLabel jLMNID35;
    private javax.swing.JLabel jLMNID36;
    private javax.swing.JLabel jLMNID37;
    private javax.swing.JLabel jLMNID38;
    private javax.swing.JLabel jLMNID39;
    private javax.swing.JLabel jLMNID4;
    private javax.swing.JLabel jLMNID40;
    private javax.swing.JLabel jLMNID5;
    private javax.swing.JLabel jLMNID6;
    private javax.swing.JLabel jLMNID7;
    private javax.swing.JLabel jLMNID8;
    private javax.swing.JLabel jLMNID9;
    private javax.swing.JLabel jLMNName1;
    private javax.swing.JLabel jLMNName10;
    private javax.swing.JLabel jLMNName11;
    private javax.swing.JLabel jLMNName12;
    private javax.swing.JLabel jLMNName13;
    private javax.swing.JLabel jLMNName14;
    private javax.swing.JLabel jLMNName15;
    private javax.swing.JLabel jLMNName16;
    private javax.swing.JLabel jLMNName17;
    private javax.swing.JLabel jLMNName18;
    private javax.swing.JLabel jLMNName19;
    private javax.swing.JLabel jLMNName2;
    private javax.swing.JLabel jLMNName20;
    private javax.swing.JLabel jLMNName21;
    private javax.swing.JLabel jLMNName22;
    private javax.swing.JLabel jLMNName23;
    private javax.swing.JLabel jLMNName24;
    private javax.swing.JLabel jLMNName25;
    private javax.swing.JLabel jLMNName26;
    private javax.swing.JLabel jLMNName27;
    private javax.swing.JLabel jLMNName28;
    private javax.swing.JLabel jLMNName29;
    private javax.swing.JLabel jLMNName3;
    private javax.swing.JLabel jLMNName30;
    private javax.swing.JLabel jLMNName31;
    private javax.swing.JLabel jLMNName32;
    private javax.swing.JLabel jLMNName33;
    private javax.swing.JLabel jLMNName34;
    private javax.swing.JLabel jLMNName35;
    private javax.swing.JLabel jLMNName36;
    private javax.swing.JLabel jLMNName37;
    private javax.swing.JLabel jLMNName38;
    private javax.swing.JLabel jLMNName39;
    private javax.swing.JLabel jLMNName4;
    private javax.swing.JLabel jLMNName40;
    private javax.swing.JLabel jLMNName5;
    private javax.swing.JLabel jLMNName6;
    private javax.swing.JLabel jLMNName7;
    private javax.swing.JLabel jLMNName8;
    private javax.swing.JLabel jLMNName9;
    private javax.swing.JSpinner jLMNSpin1;
    private javax.swing.JSpinner jLMNSpin10;
    private javax.swing.JSpinner jLMNSpin11;
    private javax.swing.JSpinner jLMNSpin12;
    private javax.swing.JSpinner jLMNSpin13;
    private javax.swing.JSpinner jLMNSpin14;
    private javax.swing.JSpinner jLMNSpin15;
    private javax.swing.JSpinner jLMNSpin16;
    private javax.swing.JSpinner jLMNSpin17;
    private javax.swing.JSpinner jLMNSpin18;
    private javax.swing.JSpinner jLMNSpin19;
    private javax.swing.JSpinner jLMNSpin2;
    private javax.swing.JSpinner jLMNSpin20;
    private javax.swing.JSpinner jLMNSpin21;
    private javax.swing.JSpinner jLMNSpin22;
    private javax.swing.JSpinner jLMNSpin23;
    private javax.swing.JSpinner jLMNSpin24;
    private javax.swing.JSpinner jLMNSpin25;
    private javax.swing.JSpinner jLMNSpin26;
    private javax.swing.JSpinner jLMNSpin27;
    private javax.swing.JSpinner jLMNSpin28;
    private javax.swing.JSpinner jLMNSpin29;
    private javax.swing.JSpinner jLMNSpin3;
    private javax.swing.JSpinner jLMNSpin30;
    private javax.swing.JSpinner jLMNSpin31;
    private javax.swing.JSpinner jLMNSpin32;
    private javax.swing.JSpinner jLMNSpin33;
    private javax.swing.JSpinner jLMNSpin34;
    private javax.swing.JSpinner jLMNSpin35;
    private javax.swing.JSpinner jLMNSpin36;
    private javax.swing.JSpinner jLMNSpin37;
    private javax.swing.JSpinner jLMNSpin38;
    private javax.swing.JSpinner jLMNSpin39;
    private javax.swing.JSpinner jLMNSpin4;
    private javax.swing.JSpinner jLMNSpin40;
    private javax.swing.JSpinner jLMNSpin5;
    private javax.swing.JSpinner jLMNSpin6;
    private javax.swing.JSpinner jLMNSpin7;
    private javax.swing.JSpinner jLMNSpin8;
    private javax.swing.JSpinner jLMNSpin9;
    private javax.swing.JLabel jLNewPS;
    private javax.swing.JLabel jLNewPSAlert;
    private javax.swing.JLabel jLNewPSTitle;
    private javax.swing.JLabel jLNgayBatDauTD;
    private javax.swing.JLabel jLPassword;
    private javax.swing.JLabel jLSeachingFood;
    private javax.swing.JLabel jLSeachingFood1;
    private javax.swing.JLabel jLSearchingEmp;
    private javax.swing.JLabel jLThucDonTuan;
    private javax.swing.JLabel jLToDate;
    private javax.swing.JLabel jLUserName;
    private javax.swing.JLabel jLXemTTtxt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLapID1;
    private javax.swing.JLabel jLapID10;
    private javax.swing.JLabel jLapID11;
    private javax.swing.JLabel jLapID12;
    private javax.swing.JLabel jLapID13;
    private javax.swing.JLabel jLapID14;
    private javax.swing.JLabel jLapID15;
    private javax.swing.JLabel jLapID16;
    private javax.swing.JLabel jLapID17;
    private javax.swing.JLabel jLapID18;
    private javax.swing.JLabel jLapID19;
    private javax.swing.JLabel jLapID2;
    private javax.swing.JLabel jLapID20;
    private javax.swing.JLabel jLapID21;
    private javax.swing.JLabel jLapID22;
    private javax.swing.JLabel jLapID23;
    private javax.swing.JLabel jLapID24;
    private javax.swing.JLabel jLapID25;
    private javax.swing.JLabel jLapID26;
    private javax.swing.JLabel jLapID27;
    private javax.swing.JLabel jLapID28;
    private javax.swing.JLabel jLapID29;
    private javax.swing.JLabel jLapID3;
    private javax.swing.JLabel jLapID30;
    private javax.swing.JLabel jLapID31;
    private javax.swing.JLabel jLapID32;
    private javax.swing.JLabel jLapID33;
    private javax.swing.JLabel jLapID34;
    private javax.swing.JLabel jLapID35;
    private javax.swing.JLabel jLapID36;
    private javax.swing.JLabel jLapID37;
    private javax.swing.JLabel jLapID38;
    private javax.swing.JLabel jLapID39;
    private javax.swing.JLabel jLapID4;
    private javax.swing.JLabel jLapID40;
    private javax.swing.JLabel jLapID5;
    private javax.swing.JLabel jLapID6;
    private javax.swing.JLabel jLapID7;
    private javax.swing.JLabel jLapID8;
    private javax.swing.JLabel jLapID9;
    private javax.swing.JLabel jLapImg1;
    private javax.swing.JLabel jLapImg10;
    private javax.swing.JLabel jLapImg11;
    private javax.swing.JLabel jLapImg12;
    private javax.swing.JLabel jLapImg13;
    private javax.swing.JLabel jLapImg14;
    private javax.swing.JLabel jLapImg15;
    private javax.swing.JLabel jLapImg16;
    private javax.swing.JLabel jLapImg17;
    private javax.swing.JLabel jLapImg18;
    private javax.swing.JLabel jLapImg19;
    private javax.swing.JLabel jLapImg2;
    private javax.swing.JLabel jLapImg20;
    private javax.swing.JLabel jLapImg21;
    private javax.swing.JLabel jLapImg22;
    private javax.swing.JLabel jLapImg23;
    private javax.swing.JLabel jLapImg24;
    private javax.swing.JLabel jLapImg25;
    private javax.swing.JLabel jLapImg26;
    private javax.swing.JLabel jLapImg27;
    private javax.swing.JLabel jLapImg28;
    private javax.swing.JLabel jLapImg29;
    private javax.swing.JLabel jLapImg3;
    private javax.swing.JLabel jLapImg30;
    private javax.swing.JLabel jLapImg31;
    private javax.swing.JLabel jLapImg32;
    private javax.swing.JLabel jLapImg33;
    private javax.swing.JLabel jLapImg34;
    private javax.swing.JLabel jLapImg35;
    private javax.swing.JLabel jLapImg36;
    private javax.swing.JLabel jLapImg37;
    private javax.swing.JLabel jLapImg38;
    private javax.swing.JLabel jLapImg39;
    private javax.swing.JLabel jLapImg4;
    private javax.swing.JLabel jLapImg40;
    private javax.swing.JLabel jLapImg5;
    private javax.swing.JLabel jLapImg6;
    private javax.swing.JLabel jLapImg7;
    private javax.swing.JLabel jLapImg8;
    private javax.swing.JLabel jLapImg9;
    private javax.swing.JLabel jLapName1;
    private javax.swing.JLabel jLapName10;
    private javax.swing.JLabel jLapName11;
    private javax.swing.JLabel jLapName12;
    private javax.swing.JLabel jLapName13;
    private javax.swing.JLabel jLapName14;
    private javax.swing.JLabel jLapName15;
    private javax.swing.JLabel jLapName16;
    private javax.swing.JLabel jLapName17;
    private javax.swing.JLabel jLapName18;
    private javax.swing.JLabel jLapName19;
    private javax.swing.JLabel jLapName2;
    private javax.swing.JLabel jLapName20;
    private javax.swing.JLabel jLapName21;
    private javax.swing.JLabel jLapName22;
    private javax.swing.JLabel jLapName23;
    private javax.swing.JLabel jLapName24;
    private javax.swing.JLabel jLapName25;
    private javax.swing.JLabel jLapName26;
    private javax.swing.JLabel jLapName27;
    private javax.swing.JLabel jLapName28;
    private javax.swing.JLabel jLapName29;
    private javax.swing.JLabel jLapName3;
    private javax.swing.JLabel jLapName30;
    private javax.swing.JLabel jLapName31;
    private javax.swing.JLabel jLapName32;
    private javax.swing.JLabel jLapName33;
    private javax.swing.JLabel jLapName34;
    private javax.swing.JLabel jLapName35;
    private javax.swing.JLabel jLapName36;
    private javax.swing.JLabel jLapName37;
    private javax.swing.JLabel jLapName38;
    private javax.swing.JLabel jLapName39;
    private javax.swing.JLabel jLapName4;
    private javax.swing.JLabel jLapName40;
    private javax.swing.JLabel jLapName5;
    private javax.swing.JLabel jLapName6;
    private javax.swing.JLabel jLapName7;
    private javax.swing.JLabel jLapName8;
    private javax.swing.JLabel jLapName9;
    private javax.swing.JSpinner jLapSpin1;
    private javax.swing.JSpinner jLapSpin10;
    private javax.swing.JSpinner jLapSpin11;
    private javax.swing.JSpinner jLapSpin12;
    private javax.swing.JSpinner jLapSpin13;
    private javax.swing.JSpinner jLapSpin14;
    private javax.swing.JSpinner jLapSpin15;
    private javax.swing.JSpinner jLapSpin16;
    private javax.swing.JSpinner jLapSpin17;
    private javax.swing.JSpinner jLapSpin18;
    private javax.swing.JSpinner jLapSpin19;
    private javax.swing.JSpinner jLapSpin2;
    private javax.swing.JSpinner jLapSpin20;
    private javax.swing.JSpinner jLapSpin21;
    private javax.swing.JSpinner jLapSpin22;
    private javax.swing.JSpinner jLapSpin23;
    private javax.swing.JSpinner jLapSpin24;
    private javax.swing.JSpinner jLapSpin25;
    private javax.swing.JSpinner jLapSpin26;
    private javax.swing.JSpinner jLapSpin27;
    private javax.swing.JSpinner jLapSpin28;
    private javax.swing.JSpinner jLapSpin29;
    private javax.swing.JSpinner jLapSpin3;
    private javax.swing.JSpinner jLapSpin30;
    private javax.swing.JSpinner jLapSpin31;
    private javax.swing.JSpinner jLapSpin32;
    private javax.swing.JSpinner jLapSpin33;
    private javax.swing.JSpinner jLapSpin34;
    private javax.swing.JSpinner jLapSpin35;
    private javax.swing.JSpinner jLapSpin36;
    private javax.swing.JSpinner jLapSpin37;
    private javax.swing.JSpinner jLapSpin38;
    private javax.swing.JSpinner jLapSpin39;
    private javax.swing.JSpinner jLapSpin4;
    private javax.swing.JSpinner jLapSpin40;
    private javax.swing.JSpinner jLapSpin5;
    private javax.swing.JSpinner jLapSpin6;
    private javax.swing.JSpinner jLapSpin7;
    private javax.swing.JSpinner jLapSpin8;
    private javax.swing.JSpinner jLapSpin9;
    private javax.swing.JLabel jLshowCustomerBirthday;
    private javax.swing.JLabel jLshowCustomerId;
    private javax.swing.JLabel jLshowCustomerIdenNum;
    private javax.swing.JLabel jLshowCustomerPhone;
    private javax.swing.JLabel jLshowCustomerPoint;
    private javax.swing.JLabel jLshowFullName;
    private javax.swing.JPanel jPFAppetizer;
    private javax.swing.JPanel jPFDesert;
    private javax.swing.JPanel jPFMainDishes;
    private javax.swing.JPanel jPLogin;
    private javax.swing.JPanel jPMain;
    private javax.swing.JPanel jPManagerControl;
    private javax.swing.JPanel jPNewEmpAlert;
    private javax.swing.JPanel jPNewPSBG;
    private javax.swing.JPasswordField jPPasswordtxt;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRBtnEmpDepartments;
    private javax.swing.JRadioButton jRBtnEmpID;
    private javax.swing.JRadioButton jRBtnEmpUserName;
    private javax.swing.JRadioButton jRSeachingFoodID;
    private javax.swing.JRadioButton jRSeachingFoodName;
    private javax.swing.JScrollPane jSPAppetizer;
    private javax.swing.JScrollPane jSPMainDishes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollShowFoodToManage;
    private javax.swing.JScrollPane jScrollShowKhachHang;
    private javax.swing.JScrollPane jScrollShowNhanVien;
    private javax.swing.JScrollPane jShowCustomer;
    private javax.swing.JTextArea jTADescription;
    private javax.swing.JTabbedPane jTDisplayType;
    private javax.swing.JTextField jTFID;
    private javax.swing.JTextField jTFName;
    private javax.swing.JLabel jTNewEmpAlertDescription;
    private javax.swing.JLabel jTNewEmpAlertTitle;
    private javax.swing.JPasswordField jTNewPS;
    private javax.swing.JTextField jTSeachingFood;
    private javax.swing.JTextField jTSearchingByEmp;
    private javax.swing.JTable jTShowFoodToManage;
    private javax.swing.JTable jTSuDungNL;
    private javax.swing.JTable jTThucDonTuan;
    private javax.swing.JTextField jTUserNametxt;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableCS;
    private javax.swing.JTable jTableShowKhachHang;
    private javax.swing.JTable jTableShowNhanVien;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton jbManageEmp;
    private javax.swing.JButton jbManageFood;
    private javax.swing.JButton jbShowDefaultFood;
    private javax.swing.JButton jbSortByName;
    private javax.swing.JButton jbSortByType;
    private javax.swing.JButton jbVieCustomer;
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
