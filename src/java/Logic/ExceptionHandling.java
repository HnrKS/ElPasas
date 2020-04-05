package Logic;

import DataModel.TransmissionMessagesModels.Informational;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ExceptionHandling {
    public static ResponseEntity<String> handler(Exception e){
        String mess = e.getMessage();
        String text = new Informational(mess).toString();
        if(mess.equals("Bad Request") || mess.equals("Invalid Token")) return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);
        if(mess.equals("Unauthorized") || mess.equals("Token Expired")) return new ResponseEntity<>(text, HttpStatus.UNAUTHORIZED);
        if(mess.toLowerCase().contains("not found")) return new ResponseEntity<>(text, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);

    }
    
}
