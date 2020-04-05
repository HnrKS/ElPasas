/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "Mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapping.findAll", query = "SELECT m FROM Mapping m"),
    @NamedQuery(name = "Mapping.findByUserId", query = "SELECT m FROM Mapping m WHERE m.userId = :userId"),
    @NamedQuery(name = "Mapping.findByPassportNo", query = "SELECT m FROM Mapping m WHERE m.passportNo = :passportNo"),
    @NamedQuery(name = "Mapping.findByIdentityCardNo", query = "SELECT m FROM Mapping m WHERE m.identityCardNo = :identityCardNo"),
    @NamedQuery(name = "Mapping.findByDrivingLicenceNo", query = "SELECT m FROM Mapping m WHERE m.drivingLicenceNo = :drivingLicenceNo"),
    @NamedQuery(name = "Mapping.findByStudentIdentityCardNo", query = "SELECT m FROM Mapping m WHERE m.studentIdentityCardNo = :studentIdentityCardNo")})
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "passportNo")
    private Integer passportNo;
    @Column(name = "identityCardNo")
    private Integer identityCardNo;
    @Column(name = "drivingLicenceNo")
    private Integer drivingLicenceNo;
    @Column(name = "studentIdentityCardNo")
    private String studentIdentityCardNo;
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Mapping() {
    }

    public Mapping(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Integer passportNo) {
        this.passportNo = passportNo;
    }

    public Integer getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(Integer identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public Integer getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(Integer drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public String getStudentIdentityCardNo() {
        return studentIdentityCardNo;
    }

    public void setStudentIdentityCardNo(String studentIdentityCardNo) {
        this.studentIdentityCardNo = studentIdentityCardNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mapping)) {
            return false;
        }
        Mapping other = (Mapping) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.Mapping[ userId=" + userId + " ]";
    }
    
}
