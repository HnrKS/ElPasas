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
@Table(name = "IdentityCardImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdentityCardImages.findAll", query = "SELECT i FROM IdentityCardImages i"),
    @NamedQuery(name = "IdentityCardImages.findByIdentityCardNo", query = "SELECT i FROM IdentityCardImages i WHERE i.identityCardNo = :identityCardNo"),
    @NamedQuery(name = "IdentityCardImages.findBySmallImage", query = "SELECT i FROM IdentityCardImages i WHERE i.smallImage = :smallImage")})
public class IdentityCardImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identityCardNo")
    private Integer identityCardNo;
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

    public IdentityCardImages() {
    }

    public IdentityCardImages(Integer identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public IdentityCardImages(Integer identityCardNo, byte[] image, byte[] smallImage, byte[] signature) {
        this.identityCardNo = identityCardNo;
        this.image = image;
        this.smallImage = smallImage;
        this.signature = signature;
    }

    public Integer getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(Integer identityCardNo) {
        this.identityCardNo = identityCardNo;
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
        hash += (identityCardNo != null ? identityCardNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdentityCardImages)) {
            return false;
        }
        IdentityCardImages other = (IdentityCardImages) object;
        if ((this.identityCardNo == null && other.identityCardNo != null) || (this.identityCardNo != null && !this.identityCardNo.equals(other.identityCardNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.IdentityCardImages[ identityCardNo=" + identityCardNo + " ]";
    }
    
}
