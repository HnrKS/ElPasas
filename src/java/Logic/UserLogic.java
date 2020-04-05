/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import DataModel.User;
import java.time.LocalDateTime;
import java.util.Base64;
import javax.persistence.EntityManager;

public final class UserLogic {
    public static User logIn(String creds) throws Exception{
        LocalDateTime currentTime = LocalDateTime.now();
        User user = Validation.getUserByAuthentication(creds);
        if (user.getAuthenticationValidUntil().compareTo(currentTime)<0){
            throw new Exception ("Need to revalidate via e-government");
        }
        String secret = Validation.generateSuperMegaSecretToken();
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        EntityManager em = Validation.getEntityManager();
        em.getTransaction().begin();
        user.setAuthorizationToken(secret);
        user.setAuthorizationValidUntil(currentTime.plusMinutes(5));
        em.merge(user);
        em.getTransaction().commit();
        em.close();
        return user;
        
    }
    public static void logOut(String token) throws Exception{
        User user = Validation.getUserByAuthorization(token);
        EntityManager em = Validation.getEntityManager();
        em.getTransaction().begin();
        user.setAuthorizationToken(null);
        user.setAuthorizationValidUntil(null);
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
}
