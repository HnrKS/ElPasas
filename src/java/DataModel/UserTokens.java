/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "UserTokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTokens.findAll", query = "SELECT u FROM UserTokens u"),
    @NamedQuery(name = "UserTokens.findByUserId", query = "SELECT u FROM UserTokens u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserTokens.findByPassportValidUntil", query = "SELECT u FROM UserTokens u WHERE u.passportValidUntil = :passportValidUntil"),
    @NamedQuery(name = "UserTokens.findByPersonalIdentityCardValidUntil", query = "SELECT u FROM UserTokens u WHERE u.personalIdentityCardValidUntil = :personalIdentityCardValidUntil"),
    @NamedQuery(name = "UserTokens.findByStudentIdentityCardValidUntil", query = "SELECT u FROM UserTokens u WHERE u.studentIdentityCardValidUntil = :studentIdentityCardValidUntil")})
public class UserTokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;
    @Lob
    @Column(name = "passportToken")
    private String passportToken;
    @Column(name = "passportValidUntil")
    private LocalDateTime passportValidUntil;
    @Lob
    @Column(name = "personalIdentityCardToken")
    private String personalIdentityCardToken;
    @Column(name = "personalIdentityCardValidUntil")
    private LocalDateTime personalIdentityCardValidUntil;
    @Lob
    @Column(name = "studentIdentityCardToken")
    private String studentIdentityCardToken;
    @Column(name = "studentIdentityCardValidUntil")
    private LocalDateTime studentIdentityCardValidUntil;
    @Lob
    @Column(name = "drivingLicenceToken")
    private String drivingLicenceToken;
    @Column(name = "drivingLicenceValidUntil")
    private LocalDateTime drivingLicenceValidUntil;

    public String getDrivingLicenceToken() {
        return drivingLicenceToken;
    }

    public void setDrivingLicenceToken(String drivingLicenceToken) {
        this.drivingLicenceToken = drivingLicenceToken;
    }

    public LocalDateTime getDrivingLicenceValidUntil() {
        return drivingLicenceValidUntil;
    }

    public void setDrivingLicenceValidUntil(LocalDateTime drivingLicenceValidUntil) {
        this.drivingLicenceValidUntil = drivingLicenceValidUntil;
    }
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public UserTokens() {
    }

    public UserTokens(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassportToken() {
        return passportToken;
    }

    public void setPassportToken(String passportToken) {
        this.passportToken = passportToken;
    }

    public LocalDateTime getPassportValidUntil() {
        return passportValidUntil;
    }

    public void setPassportValidUntil(LocalDateTime passportValidUntil) {
        this.passportValidUntil = passportValidUntil;
    }

    public String getPersonalIdentityCardToken() {
        return personalIdentityCardToken;
    }

    public void setPersonalIdentityCardToken(String personalIdentityCardToken) {
        this.personalIdentityCardToken = personalIdentityCardToken;
    }

    public LocalDateTime getPersonalIdentityCardValidUntil() {
        return personalIdentityCardValidUntil;
    }

    public void setPersonalIdentityCardValidUntil(LocalDateTime personalIdentityCardValidUntil) {
        this.personalIdentityCardValidUntil = personalIdentityCardValidUntil;
    }

    public String getStudentIdentityCardToken() {
        return studentIdentityCardToken;
    }

    public void setStudentIdentityCardToken(String studentIdentityCardToken) {
        this.studentIdentityCardToken = studentIdentityCardToken;
    }

    public LocalDateTime getStudentIdentityCardValidUntil() {
        return studentIdentityCardValidUntil;
    }

    public void setStudentIdentityCardValidUntil(LocalDateTime studentIdentityCardValidUntil) {
        this.studentIdentityCardValidUntil = studentIdentityCardValidUntil;
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
        if (!(object instanceof UserTokens)) {
            return false;
        }
        UserTokens other = (UserTokens) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.UserTokens[ userId=" + userId + " ]";
    }
    
}
