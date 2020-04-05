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
@Table(name = "StudentImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentImages.findAll", query = "SELECT s FROM StudentImages s"),
    @NamedQuery(name = "StudentImages.findByStudentId", query = "SELECT s FROM StudentImages s WHERE s.studentId = :studentId")})
public class StudentImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studentId")
    private String studentId;
    @Basic(optional = false)
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Basic(optional = false)
    @Lob
    @Column(name = "smallImage")
    private byte[] smallImage;
    @Basic(optional = false)
    @Lob
    @Column(name = "signature")
    private byte[] signature;

    public StudentImages() {
    }

    public StudentImages(String studentId) {
        this.studentId = studentId;
    }

    public StudentImages(String studentId, byte[] image, byte[] smallImage, byte[] signature) {
        this.studentId = studentId;
        this.image = image;
        this.smallImage = smallImage;
        this.signature = signature;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(byte[] smallImage) {
        this.smallImage = smallImage;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
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
        if (!(object instanceof StudentImages)) {
            return false;
        }
        StudentImages other = (StudentImages) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.StudentImages[ studentId=" + studentId + " ]";
    }
    
}
