
package Logic;

import DataModel.PersonalIdentityCard;
import DataModel.IdentityCardImages;
import DataModel.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class IdentityCardLogic {
    public static PersonalIdentityCard getDocument(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        PersonalIdentityCard identityCard = em.find(PersonalIdentityCard.class, user.getMapping().getIdentityCardNo());
        em.close();
        if(identityCard==null) throw new Exception("Not Found");
        return identityCard;
    }
    public static String getToken(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        String token = user.getUserTokens().getPersonalIdentityCardToken();
        if(token.length()==0 || user.getUserTokens().getPersonalIdentityCardValidUntil().compareTo(LocalDateTime.now())<0){
            em.getTransaction().begin();
            token=Validation.generateSuperMegaSecretToken();
            user.getUserTokens().setPersonalIdentityCardToken(token);
            em.merge(user);
            em.getTransaction().commit();
        }
        em.close();
        return token;
    }
    public static IdentityCardImages getImages(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IdentityCardImages> query = cb.createQuery(IdentityCardImages.class);
        Root<IdentityCardImages> imagesRoot = query.from(IdentityCardImages.class);
        query.where(cb.equal(imagesRoot.get("identityCardNo"), user.getMapping().getIdentityCardNo()));
        IdentityCardImages images = em.createQuery(query).getSingleResult();
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
        PersonalIdentityCard identityCard = em.find(PersonalIdentityCard.class, user.getMapping().getIdentityCardNo());
        if(identityCard.getDateOfExpiry().compareTo(LocalDate.now().minusDays(45))<0){
            em.close();
            throw new Exception("You can only request extensions 45 or less days before the document expires");
        }
        em.getTransaction().begin();
        identityCard.setDateOfExpiry(LocalDate.now().plusYears(10));
        em.merge(identityCard);
        em.getTransaction().commit();
        em.close();
    }
    public static void updatePhoto(String authorizationToken, int userId, byte[] image) throws Exception{
        IdentityCardImages images = getImages(authorizationToken, userId);
        images.setImage(image);
        images.setSmallImage(ImageLogic.getSmallImage(image));
    }
}
