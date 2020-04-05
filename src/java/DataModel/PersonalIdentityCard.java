/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "PersonalIdentityCard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalIdentityCard.findAll", query = "SELECT p FROM PersonalIdentityCard p"),
    @NamedQuery(name = "PersonalIdentityCard.findByDateOfBirth", query = "SELECT p FROM PersonalIdentityCard p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "PersonalIdentityCard.findByPersonalNo", query = "SELECT p FROM PersonalIdentityCard p WHERE p.personalNo = :personalNo"),
    @NamedQuery(name = "PersonalIdentityCard.findByCardNo", query = "SELECT p FROM PersonalIdentityCard p WHERE p.cardNo = :cardNo"),
    @NamedQuery(name = "PersonalIdentityCard.findByDateOfExpiry", query = "SELECT p FROM PersonalIdentityCard p WHERE p.dateOfExpiry = :dateOfExpiry"),
    @NamedQuery(name = "PersonalIdentityCard.findByDateOfIssue", query = "SELECT p FROM PersonalIdentityCard p WHERE p.dateOfIssue = :dateOfIssue")})
public class PersonalIdentityCard implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Lob
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Basic(optional = false)
    @Column(name = "personalNo")
    private long personalNo;
    @Id
    @Basic(optional = false)
    @Column(name = "cardNo")
    private Integer cardNo;
    @Basic(optional = false)
    @Column(name = "dateOfExpiry")
    private LocalDate dateOfExpiry;
    @Basic(optional = false)
    @Column(name = "dateOfIssue")
    private LocalDate dateOfIssue;
    @Basic(optional = false)
    @Lob
    @Column(name = "authority")
    private String authority;
    @Basic(optional = false)
    @Lob
    @Column(name = "footer")
    private String footer;

    public PersonalIdentityCard() {
    }

    public PersonalIdentityCard(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public PersonalIdentityCard(Integer cardNo, String surname, String givenName, String nationality, String sex, LocalDate dateOfBirth, long personalNo, LocalDate dateOfExpiry, LocalDate dateOfIssue, String authority, String footer) {
        this.cardNo = cardNo;
        this.surname = surname;
        this.givenName = givenName;
        this.nationality = nationality;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.personalNo = personalNo;
        this.dateOfExpiry = dateOfExpiry;
        this.dateOfIssue = dateOfIssue;
        this.authority = authority;
        this.footer = footer;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(long personalNo) {
        this.personalNo = personalNo;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
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
        hash += (cardNo != null ? cardNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalIdentityCard)) {
            return false;
        }
        PersonalIdentityCard other = (PersonalIdentityCard) object;
        if ((this.cardNo == null && other.cardNo != null) || (this.cardNo != null && !this.cardNo.equals(other.cardNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.PersonalIdentityCard[ cardNo=" + cardNo + " ]";
    }
    
}
