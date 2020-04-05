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
@Table(name = "PassportImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PassportImages.findAll", query = "SELECT p FROM PassportImages p"),
    @NamedQuery(name = "PassportImages.findByPassportNo", query = "SELECT p FROM PassportImages p WHERE p.passportNo = :passportNo")})
public class PassportImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "passportNo")
    private Integer passportNo;
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

    public PassportImages() {
    }

    public PassportImages(Integer passportNo) {
        this.passportNo = passportNo;
    }

    public PassportImages(Integer passportNo, byte[] image, byte[] smallImage, byte[] signature) {
        this.passportNo = passportNo;
        this.image = image;
        this.smallImage = smallImage;
        this.signature = signature;
    }

    public Integer getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Integer passportNo) {
        this.passportNo = passportNo;
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
        hash += (passportNo != null ? passportNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassportImages)) {
            return false;
        }
        PassportImages other = (PassportImages) object;
        if ((this.passportNo == null && other.passportNo != null) || (this.passportNo != null && !this.passportNo.equals(other.passportNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.PassportImages[ passportNo=" + passportNo + " ]";
    }
    
}
