/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.awsserver.webapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan David
 */
@Service
public class WebApplicationSquare implements WebApplication{

    private URL apiHeroku;
    @Override
    public String getResult(String number) {
        try {
            apiHeroku = new URL("https://thawing-bastion-53139.herokuapp.com/"+number);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebApplicationSquare.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String response = "";
        try (BufferedReader reader 
                = new BufferedReader(new InputStreamReader(apiHeroku.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                response+= inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return response;
    }
}
