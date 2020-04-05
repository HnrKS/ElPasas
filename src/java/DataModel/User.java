/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByAuthorizationValidUntil", query = "SELECT u FROM User u WHERE u.authorizationValidUntil = :authorizationValidUntil")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;
    @Lob
    @Column(name = "authenticationToken")
    private String authenticationToken;
    @Column(name = "authenticationValidUntil")
    private LocalDateTime authenticationValidUntil;
    @Lob
    @Column(name = "authorizationToken")
    private String authorizationToken;
    @Column(name = "authorizationValidUntil")
    private LocalDateTime authorizationValidUntil;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserTokens userTokens;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Mapping mapping;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public LocalDateTime getAuthorizationValidUntil() {
        return authorizationValidUntil;
    }

    public void setAuthorizationValidUntil(LocalDateTime authorizationValidUntil) {
        this.authorizationValidUntil = authorizationValidUntil;
    }

    public UserTokens getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(UserTokens userTokens) {
        this.userTokens = userTokens;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.authenticationToken, other.authenticationToken)) {
            return false;
        }
        if (!Objects.equals(this.authorizationToken, other.authorizationToken)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.authenticationValidUntil, other.authenticationValidUntil)) {
            return false;
        }
        if (!Objects.equals(this.authorizationValidUntil, other.authorizationValidUntil)) {
            return false;
        }
        if (!Objects.equals(this.userTokens, other.userTokens)) {
            return false;
        }
        if (!Objects.equals(this.mapping, other.mapping)) {
            return false;
        }
        return true;
    }

    public LocalDateTime getAuthenticationValidUntil() {
        return authenticationValidUntil;
    }

    public void setAuthenticationValidUntil(LocalDateTime authenticationValidUntil) {
        this.authenticationValidUntil = authenticationValidUntil;
    }

    

    @Override
    public String toString() {
        return "DataModel.User[ userId=" + userId + " ]";
    }
    
}
