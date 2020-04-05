package Logic;

import DataModel.User;
import static DataModel.UserTokens_.user;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public final class Validation {
    static Gson gson = new Gson();
    static EntityManagerFactory factory = null;
    private static String extractToken(String headerValue) throws Exception{
        String[] values=headerValue.split(" ");
        if(values.length != 2 || values[1].length()==0 || !values[0].equals("Basic")){
            throw new Exception("Bad Request");
        }
        return values[1];
    }
    public static User getUserByAuthorization(String token) throws Exception{
        token = extractToken(token);
        System.out.println("getting entity manager");
        EntityManager em = getEntityManager();
        System.out.println("got entity manager");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.where(cb.equal(userRoot.get("authorizationToken"), token));
        User user = em.createQuery(query).getSingleResult();
        System.out.println("User: "+user);
        em.close();
        if(user == null) throw new Exception("User Not Found");
        System.out.println("No exception in getting user");
        return user;
    }
    public static User getUserByAuthentication(String creds) throws Exception{
        System.out.println("getting entity manager");
        EntityManager em = getEntityManager();
        System.out.println("got entity manager");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.where(cb.equal(userRoot.get("authenticationToken"), creds));
        User user = em.createQuery(query).getSingleResult();
        System.out.println("User: "+user);
                LocalDateTime currentTime = LocalDateTime.now();
        if(user.getAuthenticationValidUntil().compareTo(currentTime)<0){
            em.close();
            throw new Exception("Session expired");
        }
        em.getTransaction().begin();
        user.setAuthenticationValidUntil(currentTime.plusMinutes(5));
        em.merge(user);
        em.getTransaction().commit();
        em.close();
        if(user == null) throw new Exception("User Not Found");
        System.out.println("No exception in getting user");
        return user;
    }
    public static EntityManager getEntityManager(){
        if(factory==null){
            factory = Persistence.createEntityManagerFactory("ElPasasPU");
        }
        return factory.createEntityManager();
    }
    public static boolean canUserAccessRequestedUser(User user, int userId){
        return user.getUserId()==userId;
    }
    public static String generateSuperMegaSecretToken() {
  
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 64;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
    public static String byteToString(byte[] givenByte){
        String translated = "";
        for (int i=0; i<givenByte.length; i++){
            translated+=givenByte[i];
        }
        return translated;
    }
}
