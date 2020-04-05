/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel.TransmissionMessagesModels;

import DataModel.User;

/**
 *
 * @author root
 */
public class LogInMessage {
    private String creds;
    public LogInMessage(User user){
        creds="{\"userId\":"+user.getUserId()+",\"token\":\""+user.getAuthorizationToken()+"\"}";
    }
    public String toString(){
        return creds;
    }
}
