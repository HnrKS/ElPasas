
package Logic;

import DataModel.StudentIdentityCard;
import DataModel.StudentImages;
import DataModel.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class StudentLogic {
    public static StudentIdentityCard getDocument(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        StudentIdentityCard studentIdentityCard = em.find(StudentIdentityCard.class, user.getMapping().getStudentIdentityCardNo());
        em.close();
        if(studentIdentityCard==null) throw new Exception("Not Found");
        return studentIdentityCard;
    }
    public static String getToken(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        String token = user.getUserTokens().getStudentIdentityCardToken();
        if(token.length()==0 || user.getUserTokens().getStudentIdentityCardValidUntil().compareTo(LocalDateTime.now())<0){
            em.getTransaction().begin();
            token=Validation.generateSuperMegaSecretToken();
            user.getUserTokens().setStudentIdentityCardToken(token);
            em.merge(user);
            em.getTransaction().commit();
        }
        em.close();
        return token;
    }
    public static StudentImages getImages(String authorizationToken, int userId) throws Exception{
        User user = Validation.getUserByAuthorization(authorizationToken);
        if(!Validation.canUserAccessRequestedUser(user, userId)) throw new Exception("Unauthorized");
        EntityManager em = Validation.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentImages> query = cb.createQuery(StudentImages.class);
        Root<StudentImages> imagesRoot = query.from(StudentImages.class);
        query.where(cb.equal(imagesRoot.get("studentIdentityCardNo"), user.getMapping().getStudentIdentityCardNo()));
        StudentImages images = em.createQuery(query).getSingleResult();
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
        StudentIdentityCard studentIdentityCard = em.find(StudentIdentityCard.class, user.getMapping().getStudentIdentityCardNo());
        if(studentIdentityCard.getValidUntil().compareTo(LocalDate.now().minusDays(45))<0){
            em.close();
            throw new Exception("You can only request extensions 45 or less days before the document expires");
        }
        em.getTransaction().begin();
        studentIdentityCard.setValidUntil(LocalDate.now().plusYears(10));
        em.merge(studentIdentityCard);
        em.getTransaction().commit();
        em.close();
    }
    public static void updatePhoto(String authorizationToken, int userId, byte[] image) throws Exception{
        StudentImages images = getImages(authorizationToken, userId);
        images.setImage(image);
        images.setSmallImage(ImageLogic.getSmallImage(image));
    }
}
