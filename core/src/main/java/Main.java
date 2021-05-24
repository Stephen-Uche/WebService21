import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(5050)) {

            while (true) {
                Socket client = socket.accept();
                System.out.println(client.getInetAddress());
                var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

                while(true){
                    var line = inputFromClient.readLine();
                    if ( line == null || line.isEmpty()) {
                        break;
                    }
                    System.out.println(line);
                }
                var outputToClient = new PrintWriter(client.getOutputStream());
                outputToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
                outputToClient.flush();
                inputFromClient.close();
                outputToClient.close();
                client.close();
            }
//            String input = "";
//            while(input != null) {
//                input = inputFromClient.readLine();
//                System.out.println(input);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
