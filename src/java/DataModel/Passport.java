/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "Passport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passport.findAll", query = "SELECT p FROM Passport p"),
    @NamedQuery(name = "Passport.findByPassportNo", query = "SELECT p FROM Passport p WHERE p.passportNo = :passportNo"),
    @NamedQuery(name = "Passport.findByDateOfBirth", query = "SELECT p FROM Passport p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Passport.findByDateOfIssue", query = "SELECT p FROM Passport p WHERE p.dateOfIssue = :dateOfIssue"),
    @NamedQuery(name = "Passport.findByDateOfExpiry", query = "SELECT p FROM Passport p WHERE p.dateOfExpiry = :dateOfExpiry"),
    @NamedQuery(name = "Passport.findByPersonalNo", query = "SELECT p FROM Passport p WHERE p.personalNo = :personalNo")})
public class Passport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "passportNo")
    private Integer passportNo;
    @Basic(optional = false)
    @Lob
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Lob
    @Column(name = "givenName")
    private String givenName;
    @Basic(optional = false)
    @Lob
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Basic(optional = false)
    @Lob
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "dateOfIssue")
    private LocalDate dateOfIssue;
    @Basic(optional = false)
    @Column(name = "dateOfExpiry")
    private LocalDate dateOfExpiry;
    @Basic(optional = false)
    @Column(name = "personalNo")
    private long personalNo;
    @Basic(optional = false)
    @Lob
    @Column(name = "placeOfBirth")
    private String placeOfBirth;
    @Basic(optional = false)
    @Lob
    @Column(name = "authority")
    private String authority;
    @Basic(optional = false)
    @Lob
    @Column(name = "footer")
    private String footer;

    public Passport() {
    }

    public Passport(Integer passportNo) {
        this.passportNo = passportNo;
    }

    

    public Integer getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Integer passportNo) {
        this.passportNo = passportNo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Passport(Integer passportNo, String surname, String givenName, String nationality, LocalDate dateOfBirth, String sex, LocalDate dateOfIssue, LocalDate dateOfExpiry, long personalNo, String placeOfBirth, String authority, String footer) {
        this.passportNo = passportNo;
        this.surname = surname;
        this.givenName = givenName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.personalNo = personalNo;
        this.placeOfBirth = placeOfBirth;
        this.authority = authority;
        this.footer = footer;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public long getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(long personalNo) {
        this.personalNo = personalNo;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passportNo != null ? passportNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passport)) {
            return false;
        }
        Passport other = (Passport) object;
        if ((this.passportNo == null && other.passportNo != null) || (this.passportNo != null && !this.passportNo.equals(other.passportNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.Passport[ passportNo=" + passportNo + " ]";
    }
    
}
