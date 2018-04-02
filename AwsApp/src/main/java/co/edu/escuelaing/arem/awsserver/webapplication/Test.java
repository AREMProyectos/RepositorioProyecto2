/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.awsserver.webapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2110805
 */
@Service
public class Test {
    @Autowired
    private static WebApplication web = null;
    public static void main(String[] args){
        System.out.println(web.getResult("2"));
    }
}
