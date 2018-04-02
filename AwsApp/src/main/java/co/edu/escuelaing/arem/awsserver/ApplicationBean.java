/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.awsserver;

import co.edu.escuelaing.arem.awsserver.webapplication.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2110805
 */
@Service
public class ApplicationBean {
    @Autowired
    WebApplication api;
    
    public void getMessage(){
        api.getResult("2");
    }
}
