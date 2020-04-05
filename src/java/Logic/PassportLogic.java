
package Logic;

import DataModel.Passport;
import DataModel.PassportImages;
import DataModel.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class PassportLogic {
    public static Passport getDocument(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        Passport passport = em.find(Passport.class, user.getMapping().getPassportNo());
        em.close();
        if(passport==null) throw new Exception("Not Found");
        return passport;
    }
    public static String getToken(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        String token = user.getUserTokens().getPassportToken();
        if(token.length()==0 || user.getUserTokens().getPassportValidUntil().compareTo(LocalDateTime.now())<0){
            em.getTransaction().begin();
            token=Validation.generateSuperMegaSecretToken();
            user.getUserTokens().setPassportToken(token);
            em.merge(user);
            em.getTransaction().commit();
        }
        em.close();
        return token;
    }
    public static PassportImages getImages(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PassportImages> query = cb.createQuery(PassportImages.class);
        Root<PassportImages> imagesRoot = query.from(PassportImages.class);
        query.where(cb.equal(imagesRoot.get("passportNo"), user.getMapping().getPassportNo()));
        PassportImages images = em.createQuery(query).getSingleResult();
        return images;
    }
    public static byte[] getImage(String authorizationToken, int userId) throws Exception{
        return getImages(authorizationToken, userId).getImage();
    }
    public static byte[] getSmallImage(String authorizationToken, int userId) throws Exception{
        return getImages(authorizationToken, userId).getSmallImage();
    }
    public static byte[] getSignature(String authorizationToken, int userId) throws Exception{
        return getImages(authorizationToken, userId).getSignature();
    }
    public static void requestExtension(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        Passport passport = em.find(Passport.class, user.getMapping().getPassportNo());
        if(passport.getDateOfExpiry().compareTo(LocalDate.now().minusDays(45))<0){
            em.close();
            throw new Exception("You can only request extensions 45 or less days before the document expires");
        }
        em.getTransaction().begin();
        passport.setDateOfExpiry(LocalDate.now().plusYears(10));
        em.merge(passport);
        em.getTransaction().commit();
        em.close();
    }
    public static void updatePhoto(String authorizationToken, int userId, byte[] image) throws Exception{
        PassportImages images = getImages(authorizationToken, userId);
        images.setImage(image);
        images.setSmallImage(ImageLogic.getSmallImage(image));
    }
}
