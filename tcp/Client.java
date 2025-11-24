import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            PrintWriter pw = new PrintWriter(
                    s.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter message to send to server: ");
            String userMsg = sc.nextLine();   // custom input

            // Send input to server
            pw.println(userMsg);

            // Read reply from server
            String msg = br.readLine();
            System.out.println("Server says: " + msg);

            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
