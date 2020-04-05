/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel.TransmissionMessagesModels;

/**
 *
 * @author root
 */
public class Informational {
    String message;

    public Informational(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"message\":\"" + message + "\"}";
    }
    
}
