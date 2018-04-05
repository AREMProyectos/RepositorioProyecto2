package co.edu.escuelaing.arem.awsserver;

import co.edu.escuelaing.arem.awsserver.webapplication.WebApplication;
import co.edu.escuelaing.arem.awsserver.webapplication.Square;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Juan David
 */
public class WebServerThread implements Runnable {

    private ServerSocket serverSocket;
    private WebApplication instance;

    /**
     * Constructor of Web Server Thread
     *
     * @param serverSocket The server socket you want to connect
     * @param gc The bean we want to use to do some necessary process
     */
    WebServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Run the initial method of the class that will be executed when the thread
     * start
     */
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

    /**
     * Starts the socket of the client, show the current status and process the
     * given values of the page.
     *
     * @throws IOException
     */
    private void init() throws IOException {
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
            if (inputLine.startsWith("GET")) {
                System.out.println(inputLine);
                String query = inputLine.split(" ")[1];
                System.out.println(query);
                if (query.equals("/") || query.equals("/index.html")) {
                    File indexFile = new File(WebServerThread.class.getResource("/index.html").getFile());
                    String output = null;
                    try {
                        output = FileUtils.readFileToString(indexFile, StandardCharsets.UTF_8);
                    } catch (IOException ex) {
                        Logger.getLogger(WebServerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n\r\n" + output;
                    out.println(outputLine);
                } else if (query.contains("response")) {
                    System.out.println(query.split("/")[2]);
                    try {
                        Object instance;
                        instance = Class.forName("co.edu.escuelaing.arem.awsserver.webapplication." + query.split("/")[2].split("\\?")[0]).newInstance();
                        WebApplication instanceApi = (WebApplication) instance;
                        File responseFile = new File(WebServerThread.class.getResource("/response.html").getFile());
                        String output = null;
                        try {
                            output = FileUtils.readFileToString(responseFile, StandardCharsets.UTF_8);
                        } catch (IOException ex) {
                            Logger.getLogger(WebServerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String response = output.replace("{square}", instanceApi.getResult(query.split("/")[2].split("\\?")[1].split("=")[1]));
                        outputLine = "HTTP/1.1 200 OK\r\n"
                                + "Content-Type: text/html\r\n\r\n" + response;
                        out.println(outputLine);

                    } catch (Exception ex) {
                        Logger.getLogger(WebServerThread.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }

            if (!in.ready()) {
                break;
            }

            if (inputLine.equals("")) {
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
    }

}
