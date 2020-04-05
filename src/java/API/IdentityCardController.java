
package API;

import DataModel.TransmissionMessagesModels.Informational;
import Logic.IdentityCardLogic;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identityCard/{userId}")
public class IdentityCardController {
    int userId;
    Gson gson = new Gson();
    @RequestMapping(value="/", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ResponseEntity<String> returnData(@PathVariable int userId, @RequestHeader("Authorization") String token){
        try{
            return new ResponseEntity(gson.toJson(IdentityCardLogic.getDocument(token, userId)), HttpStatus.OK);
            } catch (Exception e) {
            return new ResponseEntity<>(new Informational("Unauthorized").toString(), HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/image/small", method=RequestMethod.GET, produces="image/jpeg")
    @ResponseBody
    public String returnSmallImage(@PathVariable int userId){
        return String.valueOf(userId);
    }
    @RequestMapping(value="/qr", method=RequestMethod.GET, produces="text/plain")
    @ResponseBody
    public ResponseEntity<String> returnQr(@PathVariable int userId, @RequestHeader("Authorization") String token){
        try{
            return new ResponseEntity<>(IdentityCardLogic.getToken(token, userId), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Unauthorized").toString(), HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/nfc", method=RequestMethod.GET, produces="text/plain")
    @ResponseBody
    public ResponseEntity<String> returnNfc(@PathVariable int userId, @RequestHeader("Authorization") String token){
        try{
            return new ResponseEntity<>(IdentityCardLogic.getToken(token, userId), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Unauthorized").toString(), HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/image", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> returnImage(@PathVariable int userId, @RequestHeader("Authorization") String token){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "image/jpeg");
            byte[] image = IdentityCardLogic.getImage(token, userId);
            return new ResponseEntity<>(image, responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(new Informational("Unauthorized").toString().getBytes(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/smallImage", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> returnSmallImage(@PathVariable int userId, @RequestHeader("Authorization") String token){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "image/jpeg");
            byte[] image = IdentityCardLogic.getSmallImage(token, userId);
            return new ResponseEntity<>(image, responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(new Informational("Unauthorized").toString().getBytes(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/signature", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> returnSignature(@PathVariable int userId, @RequestHeader("Authorization") String token){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "image/jpeg");
            byte[] image = IdentityCardLogic.getSignature(token, userId);
            return new ResponseEntity<>(image, responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(new Informational("Unauthorized").toString().getBytes(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/image", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> uploadImage(@PathVariable int userId, @RequestHeader("Authorization") String token, @RequestBody byte[] image, @RequestHeader("Content-Type") String contentType){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try{
            
            IdentityCardLogic.requestExtension(token, userId);
            return new ResponseEntity<>(new Informational("Success").toString(), responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Unauthorized").toString(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/validate", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> revalidation(@PathVariable int userId, @RequestHeader("Authorization") String token){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "application/json");
            IdentityCardLogic.requestExtension(token, userId);
            return new ResponseEntity<>(new Informational("Success").toString(), responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Unauthorized").toString(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
}
