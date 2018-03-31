
package co.edu.escuelaing.arem.awsapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class WebServer {

    //private static final int CONCURRENT_THREADS = 4;
    
    public static void main(String[] args) {
        Integer environmentPort = null;
        ServerSocket serverSocket = null;
        try {
            environmentPort = 8080;
            //environmentPort = new Integer(System.getenv("PORT"));
            //System.out.println("PORT: " + port);
            serverSocket = new ServerSocket(environmentPort);
        } catch (IOException e) {
            //Falta agregar el logger
            System.err.println("Could not listen on port:" + environmentPort);
            System.exit(1);
        }
        
        //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //HTMLBuilder htmlBuilder = ac.getBean(HTMLBuilderImpl.class);
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Runnable server = new WebServerThread(serverSocket);
            executor.execute(server);
        } 
    }
}