/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huongrungbuffet;

import java.sql.Date;

/**
 *
 * @author Dell
 */
class Employee {
    private String EmployeeID, FullName, Title, Phone, Eaddress, DepartmentID, Username, Password, IdentificationNumber;
    private Date birthday;


    public Employee() {
    }

    public Employee(String EmployeeID, String FullName, String Title, String Phone, String Eaddress, String DepartmentID, String Username, String Password, String IdentificationNumber, Date birthday) {
        this.EmployeeID = EmployeeID;
        this.FullName = FullName;
        this.Title = Title;
        this.Phone = Phone;
        this.Eaddress = Eaddress;
        this.DepartmentID = DepartmentID;
        this.Username = Username;
        this.Password = Password;
        this.IdentificationNumber = IdentificationNumber;
        this.birthday = birthday;
    }

        public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEaddress() {
        return Eaddress;
    }

    public void setEaddress(String Eaddress) {
        this.Eaddress = Eaddress;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(String IdentificationNumber) {
        this.IdentificationNumber = IdentificationNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
}
