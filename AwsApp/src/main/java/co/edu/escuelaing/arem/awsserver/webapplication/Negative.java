/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.awsserver.webapplication;

import co.edu.escuelaing.arem.awsserver.webapplication.WebApplication;

/**
 *
 * @author 2114928
 */
public class Negative implements WebApplication {

    public Negative() {
    }

    @Override
    public String getResult(String number) {
        return ":( Not supported yet ";   
    }
    
}
