/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import DataModel.TransmissionMessagesModels.Informational;
import DataModel.TransmissionMessagesModels.LogInMessage;
import DataModel.User;
import Logic.UserLogic;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody String creds){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "application/json");
            User user = UserLogic.logIn(creds);
            responseHeaders.set("X-Expires-After", user.getAuthorizationValidUntil().toString());
            return new ResponseEntity<>(new LogInMessage(user).toString(), responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Incorrect").toString(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        HttpHeaders responseHeaders = new HttpHeaders();
        try{
            responseHeaders.set("Content-Type", "application/json");
            UserLogic.logOut(token);
            return new ResponseEntity<>(new Informational("Success").toString(), responseHeaders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Informational("Error").toString(), responseHeaders, HttpStatus.FORBIDDEN);
        }
    }
    
}
