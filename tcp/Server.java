import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server started... waiting for client");

            Socket s = ss.accept();  
            System.out.println("Client connected!");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            PrintWriter pw = new PrintWriter(
                    s.getOutputStream(), true);

            // Read message received from client
            String msg = br.readLine();
            System.out.println("Client says: " + msg);

            // Reply to client
            pw.println("Hello");

            s.close();
            ss.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
