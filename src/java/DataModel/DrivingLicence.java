/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "DrivingLicence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrivingLicence.findAll", query = "SELECT d FROM DrivingLicence d"),
    @NamedQuery(name = "DrivingLicence.findByDateOfBirth", query = "SELECT d FROM DrivingLicence d WHERE d._3_dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "DrivingLicence.findByAdateOfIssue", query = "SELECT d FROM DrivingLicence d WHERE d._4a_dateOfIssue = :adateOfIssue"),
    @NamedQuery(name = "DrivingLicence.findByBdateOfExpiry", query = "SELECT d FROM DrivingLicence d WHERE d._4b_dateOfExpiry = :bdateOfExpiry"),
    @NamedQuery(name = "DrivingLicence.findByDpersonalNo", query = "SELECT d FROM DrivingLicence d WHERE d.4d_personalNo = :dpersonalNo"),
    @NamedQuery(name = "DrivingLicence.findByLicenceNo", query = "SELECT d FROM DrivingLicence d WHERE d.5_licenceNo = :licenceNo")})
public class DrivingLicence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Lob
    @Column(name = "1_surname")
    private String _1_surname;
    @Basic(optional = false)
    @Lob
    @Column(name = "2_name")
    private String _2_name;
    @Basic(optional = false)
    @Column(name = "3_dateOfBirth")
    private LocalDate _3_dateOfBirth;
    @Basic(optional = false)
    @Column(name = "4a_dateOfIssue")
    private LocalDate _4a_dateOfIssue;
    @Basic(optional = false)
    @Column(name = "4b_dateOfExpiry")
    private LocalDate _4b_dateOfExpiry;
    @Basic(optional = false)
    @Lob
    @Column(name = "4c_issuedBy")
    private String _4c_issuedBy;
    @Basic(optional = false)
    @Column(name = "4d_personalNo")
    private long _4d_personalNo;
    @Id
    @Basic(optional = false)
    @Column(name = "5_licenceNo")
    private Integer _5_licenceNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenceNo")
    private Collection<Category> categoryCollection;

    public DrivingLicence() {
    }

    public DrivingLicence(Integer _5_licenceNo) {
        this._5_licenceNo = _5_licenceNo;
    }

    public DrivingLicence(Integer _5_licenceNo, String _1_surname, String _2_name, LocalDate _3_dateOfBirth, LocalDate _4a_dateOfIssue, LocalDate _4b_dateOfExpiry, String _4c_issuedBy, long _4d_personalNo) {
        this._5_licenceNo = _5_licenceNo;
        this._1_surname = _1_surname;
        this._2_name = _2_name;
        this._3_dateOfBirth = _3_dateOfBirth;
        this._4a_dateOfIssue = _4a_dateOfIssue;
        this._4b_dateOfExpiry = _4b_dateOfExpiry;
        this._4c_issuedBy = _4c_issuedBy;
        this._4d_personalNo = _4d_personalNo;
    }

    public String getSurname() {
        return _1_surname;
    }

    public void setSurname(String _1_surname) {
        this._1_surname = _1_surname;
    }

    public String getName() {
        return _2_name;
    }

    public void setName(String _2_name) {
        this._2_name = _2_name;
    }

    public LocalDate getDateOfBirth() {
        return _3_dateOfBirth;
    }

    public void setDateOfBirth(LocalDate _3_dateOfBirth) {
        this._3_dateOfBirth = _3_dateOfBirth;
    }

    public LocalDate getAdateOfIssue() {
        return _4a_dateOfIssue;
    }

    public void setAdateOfIssue(LocalDate _4a_dateOfIssue) {
        this._4a_dateOfIssue = _4a_dateOfIssue;
    }

    public LocalDate getBdateOfExpiry() {
        return _4b_dateOfExpiry;
    }

    public void setBdateOfExpiry(LocalDate _4b_dateOfExpiry) {
        this._4b_dateOfExpiry = _4b_dateOfExpiry;
    }

    public String getCissuedBy() {
        return _4c_issuedBy;
    }

    public void setCissuedBy(String _4c_issuedBy) {
        this._4c_issuedBy = _4c_issuedBy;
    }

    public long getDpersonalNo() {
        return _4d_personalNo;
    }

    public void setDpersonalNo(long _4d_personalNo) {
        this._4d_personalNo = _4d_personalNo;
    }

    public Integer getLicenceNo() {
        return _5_licenceNo;
    }

    public void setLicenceNo(Integer _5_licenceNo) {
        this._5_licenceNo = _5_licenceNo;
    }

    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (_5_licenceNo != null ? _5_licenceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DrivingLicence)) {
            return false;
        }
        DrivingLicence other = (DrivingLicence) object;
        if ((this._5_licenceNo == null && other._5_licenceNo != null) || (this._5_licenceNo != null && !this._5_licenceNo.equals(other._5_licenceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.DrivingLicence[ _5_licenceNo=" + _5_licenceNo + " ]";
    }
    
}
