/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class Main {

    /**
     * Main method of the class
     * @param args Context
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
    /**
     * Map the current ResponseEntity with the given number
     * @param number Number client want to process
     * @return  The square of the number
     */
    @RequestMapping(value="")
    public ResponseEntity<?> getSquare(@RequestParam("number") int number){
        return new ResponseEntity<>(number*number,HttpStatus.OK);
    }

  

}