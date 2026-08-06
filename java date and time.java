import java.net.*;
import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
    
    Thread server = new Thread(() -> {
        try {
            ServerSocket ss = new ServerSocket(5000);
            Socket s = ss.accept();
            
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println("Server Recieved: " + dis.readUTF());
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    });
    
    server.start();
    Thread.sleep(1000);
    Socket s = new Socket("localhost", 5000);
    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    dos.writeUTF(new Date().toString());
    dos.close();
    s.close();
    }
}