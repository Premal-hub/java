import java.net.*;        // For UDP networking classes
import java.util.Scanner; // For user input

public class UDPClient {
    public static void main(String[] args) {
        try {

            // Create UDP socket (no port needed for client)
            DatagramSocket ds = new DatagramSocket();

            Scanner sc = new Scanner(System.in);

            // Ask user to enter a number
            System.out.print("Enter a number: ");
            String num = sc.nextLine();  // Read input as string

            // Convert number into bytes to send
            byte[] sendData = num.getBytes();

            // Server IP address
            InetAddress ip = InetAddress.getByName("localhost");

            // Create packet containing number → send to port 5000
            DatagramPacket dp = new DatagramPacket(sendData, sendData.length, ip, 5000);

            // Send packet to server
            ds.send(dp);

            // Buffer to receive factorial result
            byte[] recData = new byte[1024];

            // Packet to receive server's reply
            DatagramPacket rp = new DatagramPacket(recData, recData.length);

            // Wait and receive reply from server
            ds.receive(rp);

            // Convert bytes to string
            String result = new String(rp.getData()).trim();

            // Display factorial result
            System.out.println("Factorial from server: " + result);

            // Close socket
            ds.close();

        } catch (Exception e) {
            e.printStackTrace(); // Print error if any
        }
    }
}
