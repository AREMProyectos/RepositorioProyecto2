package co.edu.escuelaing.arem.awsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan David
 */
public class WebServerThread implements Runnable{
    
    private ServerSocket serverSocket;

    WebServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                init();
            }
        } catch (IOException ex) {
            Logger.getLogger(WebServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() throws IOException{
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            
            if (!in.ready()) {
                break;
            }
            
            if (inputLine.equals("")) break;
        }
        
        out.close();
        in.close();
        clientSocket.close();
    }
    
}
