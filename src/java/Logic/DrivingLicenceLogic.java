
package Logic;

import DataModel.DrivingLicence;
import DataModel.LicenceImages;
import DataModel.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class DrivingLicenceLogic {
    public static DrivingLicence getDocument(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        DrivingLicence drivingLicence = em.find(DrivingLicence.class, user.getMapping().getDrivingLicenceNo());
        em.close();
        if(drivingLicence==null) throw new Exception("Not Found");
        return drivingLicence;
    }
    public static String getToken(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        String token = user.getUserTokens().getDrivingLicenceToken();
        if(token.length()==0 || user.getUserTokens().getDrivingLicenceValidUntil().compareTo(LocalDateTime.now())<0){
            em.getTransaction().begin();
            token=Validation.generateSuperMegaSecretToken();
            user.getUserTokens().setDrivingLicenceToken(token);
            em.merge(user);
            em.getTransaction().commit();
        }
        em.close();
        return token;
    }
    public static LicenceImages getImages(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LicenceImages> query = cb.createQuery(LicenceImages.class);
        Root<LicenceImages> imagesRoot = query.from(LicenceImages.class);
        query.where(cb.equal(imagesRoot.get("drivingLicenceNo"), user.getMapping().getDrivingLicenceNo()));
        LicenceImages images = em.createQuery(query).getSingleResult();
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
        DrivingLicence drivingLicence = em.find(DrivingLicence.class, user.getMapping().getDrivingLicenceNo());
        if(drivingLicence.getBdateOfExpiry().compareTo(LocalDate.now().minusDays(45))<0){
            em.close();
            throw new Exception("You can only request extensions 45 or less days before the document expires");
        }
        em.getTransaction().begin();
        drivingLicence.setBdateOfExpiry(LocalDate.now().plusYears(10));
        em.merge(drivingLicence);
        em.getTransaction().commit();
        em.close();
    }
    public static void updatePhoto(String authorizationToken, int userId, byte[] image) throws Exception{
        LicenceImages images = getImages(authorizationToken, userId);
        images.setImage(image);
        images.setSmallImage(ImageLogic.getSmallImage(image));
    }
}
