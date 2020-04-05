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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "StudentIdentityCard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentIdentityCard.findAll", query = "SELECT s FROM StudentIdentityCard s"),
    @NamedQuery(name = "StudentIdentityCard.findByStudentId", query = "SELECT s FROM StudentIdentityCard s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "StudentIdentityCard.findByPersonalNo", query = "SELECT s FROM StudentIdentityCard s WHERE s.personalNo = :personalNo"),
    @NamedQuery(name = "StudentIdentityCard.findByValidFrom", query = "SELECT s FROM StudentIdentityCard s WHERE s.validFrom = :validFrom"),
    @NamedQuery(name = "StudentIdentityCard.findByValidUntil", query = "SELECT s FROM StudentIdentityCard s WHERE s.validUntil = :validUntil"),
    @NamedQuery(name = "StudentIdentityCard.findBySpd", query = "SELECT s FROM StudentIdentityCard s WHERE s.spd = :spd")})
public class StudentIdentityCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studentId")
    private String studentId;
    @Basic(optional = false)
    @Lob
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "personalNo")
    private long personalNo;
    @Basic(optional = false)
    @Lob
    @Column(name = "studiesAt")
    private String studiesAt;
    @Basic(optional = false)
    @Column(name = "validFrom")
    private LocalDate validFrom;
    @Basic(optional = false)
    @Column(name = "validUntil")
    private LocalDate validUntil;
    @Basic(optional = false)
    @Column(name = "spd")
    private int spd;
    @Basic(optional = false)
    @Lob
    @Column(name = "barcode")
    private String barcode;

    public StudentIdentityCard() {
    }

    public StudentIdentityCard(String studentId) {
        this.studentId = studentId;
    }

    public StudentIdentityCard(String studentId, String name, String surname, long personalNo, String studiesAt, LocalDate validFrom, LocalDate validUntil, int spd, String barcode) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.personalNo = personalNo;
        this.studiesAt = studiesAt;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.spd = spd;
        this.barcode = barcode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(long personalNo) {
        this.personalNo = personalNo;
    }

    public String getStudiesAt() {
        return studiesAt;
    }

    public void setStudiesAt(String studiesAt) {
        this.studiesAt = studiesAt;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentIdentityCard)) {
            return false;
        }
        StudentIdentityCard other = (StudentIdentityCard) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.StudentIdentityCard[ studentId=" + studentId + " ]";
    }
    
}
