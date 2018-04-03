
package co.edu.escuelaing.arem.awsserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Juan David
 */
public class WebServer {

    /**
     * Main method of the Web Server
     * @param args The context of the class
     */
    public static void main(String[] args) {
        Integer environmentPort = null;
        ServerSocket serverSocket = null;
        try {
            environmentPort = 8080;
            //environmentPort = new Integer(System.getenv("PORT"));
            serverSocket = new ServerSocket(environmentPort);
        } catch (IOException e) {
            //Falta agregar el logger
            System.err.println("Could not listen on port:" + environmentPort);
            System.exit(1);
        }
        
         ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
         ApplicationBean gc = ac.getBean(ApplicationBeanImpl.class);
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Runnable server = new WebServerThread(serverSocket,gc);
            executor.execute(server);
        } 
    }
}