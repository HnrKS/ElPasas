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
@Table(name = "LicenceImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicenceImages.findAll", query = "SELECT l FROM LicenceImages l"),
    @NamedQuery(name = "LicenceImages.findByLicenceNo", query = "SELECT l FROM LicenceImages l WHERE l.licenceNo = :licenceNo")})
public class LicenceImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "licenceNo")
    private Integer licenceNo;
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

    public LicenceImages() {
    }

    public LicenceImages(Integer licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LicenceImages(Integer licenceNo, byte[] image, byte[] smallImage, byte[] signature) {
        this.licenceNo = licenceNo;
        this.image = image;
        this.smallImage = smallImage;
        this.signature = signature;
    }

    public Integer getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(Integer licenceNo) {
        this.licenceNo = licenceNo;
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
        hash += (licenceNo != null ? licenceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenceImages)) {
            return false;
        }
        LicenceImages other = (LicenceImages) object;
        if ((this.licenceNo == null && other.licenceNo != null) || (this.licenceNo != null && !this.licenceNo.equals(other.licenceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataModel.LicenceImages[ licenceNo=" + licenceNo + " ]";
    }
    
}
