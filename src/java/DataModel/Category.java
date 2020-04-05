/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "Category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByDateOfIssue", query = "SELECT c FROM Category c WHERE c.dateOfIssue = :dateOfIssue"),
    @NamedQuery(name = "Category.findByDateOfExpiry", query = "SELECT c FROM Category c WHERE c.dateOfExpiry = :dateOfExpiry"),
    @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Lob
    @Column(name = "9_name")
    private String _9_name;
    @Basic(optional = false)
    @Column(name = "10_dateOfIssue")
    @Temporal(TemporalType.DATE)
    private Date _10_dateOfIssue;
    @Basic(optional = false)
    @Column(name = "11_dateOfExpiry")
    @Temporal(TemporalType.DATE)
    private Date _11_dateOfExpiry;
    @Basic(optional = false)
    @Lob
    @Column(name = "12_code")
    private String _12_code;
    @Id
    @Basic(optional = false)
    @Column(name = "categoryId")
    private Integer categoryId;
    @JoinColumn(name = "5_licenceNo", referencedColumnName = "5_licenceNo")
    @ManyToOne(optional = false)
    private DrivingLicence licenceNo;

    public Category() {
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category(Integer categoryId, String _9_name, Date _10_dateOfIssue, Date _11_dateOfExpiry, String code) {
        this.categoryId = categoryId;
        this._9_name = _9_name;
        this._10_dateOfIssue = _10_dateOfIssue;
        this._11_dateOfExpiry = _11_dateOfExpiry;
        this._12_code = code;
    }

    public String getName() {
        return _9_name;
    }

    public void setName(String _9_name) {
        this._9_name = _9_name;
    }

    public Date getDateOfIssue() {
        return _10_dateOfIssue;
    }

    public void setDateOfIssue(Date _10_dateOfIssue) {
        this._10_dateOfIssue = _10_dateOfIssue;
    }

    public Date getDateOfExpiry() {
        return _11_dateOfExpiry;
    }

    public void setDateOfExpiry(Date _11_dateOfExpiry) {
        this._11_dateOfExpiry = _11_dateOfExpiry;
    }

    public String getCode() {
        return _12_code;
    }

    public void setCode(String code) {
        this._12_code = code;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public DrivingLicence getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(DrivingLicence licenceNo) {
        this.licenceNo = licenceNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.Category[ categoryId=" + categoryId + " ]";
    }
    
}
