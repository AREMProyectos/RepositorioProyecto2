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
 * @author 2114928
 */
@Service
public class ApplicationBeanImpl implements ApplicationBean{
    
    @Autowired
    WebApplication api;
    /**
     * The constructor of the class
     */
    public ApplicationBeanImpl(){
    
    }
    /**
     * This method get the current number that is located in the web page
     * @param number The number that is going to be processed
     * @return Return the proccess of the web application with the given number
     */
    @Override
    public String getAPIResponse(String number){
        return api.getResult(number);
    }
}
