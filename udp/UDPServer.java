import java.net.*;   // Import networking classes

public class UDPServer {
    public static void main(String[] args) {
        try {

            // Create UDP socket on port 5000 → server waits here
            DatagramSocket ds = new DatagramSocket(5000);

            // Create a byte array to store data coming from client
            byte[] recData = new byte[1024];

            System.out.println("UDP Server started... waiting for data");

            // Create packet to receive data (empty packet)
            DatagramPacket dp = new DatagramPacket(recData, recData.length);

            // Wait and receive data from client
            ds.receive(dp);

            // Convert received bytes to string and trim spaces
            String received = new String(dp.getData()).trim();

            // Convert string number to integer
            int num = Integer.parseInt(received);

            System.out.println("Client sent number: " + num);

            // Calculate factorial
            int fact = 1;
            for (int i = 1; i <= num; i++) {
                fact *= i;   // multiply numbers 1 to num
            }

            // Convert factorial result to string
            String result = String.valueOf(fact);

            // Convert result string into bytes
            byte[] sendData = result.getBytes();

            // Get client's IP address from received packet
            InetAddress ip = dp.getAddress();

            // Get client's port number
            int port = dp.getPort();

            // Create packet to send factorial result back to client
            DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, ip, port);

            // Send factorial result to client
            ds.send(sendPacket);

            System.out.println("Sent factorial: " + result);

            // Close socket
            ds.close();

        } catch (Exception e) {
            e.printStackTrace();  // Print error if any
        }
    }
}
