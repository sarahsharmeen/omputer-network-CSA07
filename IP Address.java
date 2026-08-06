import java.net.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Thread server = new Thread(() -> {
            try {
                DatagramSocket ds = new DatagramSocket(5000);
                byte[] b1 = new byte[1024];
                DatagramPacket dp = new DatagramPacket(b1, b1.length);
                ds.receive(dp);
                String domain = new String(dp.getData(), 0, dp.getLength());
                String ip;
                if(domain.equals("google")) ip = "142.250.183.14";
                else ip = "Not Found";
                byte[] b2 = ip.getBytes();
                DatagramPacket reply = new DatagramPacket(b2, b2.length, dp.getAddress(), dp.getPort());
                ds.send(reply);
                ds.close();
            } catch(Exception e) {}
        });
        server.start();
        Thread.sleep(1000);
        DatagramSocket client = new DatagramSocket();
        byte[] b1 = "google".getBytes();
        DatagramPacket dp = new DatagramPacket(b1, b1.length, InetAddress.getByName("localhost"), 5000);
        client.send(dp);
        byte[] b2 = new byte[1024];
        DatagramPacket reply = new DatagramPacket(b2, b2.length);
        client.receive(reply);
        System.out.println("IP Address:" + new String(reply.getData(), 0, reply.getLength()));
        client.close();
    }
}