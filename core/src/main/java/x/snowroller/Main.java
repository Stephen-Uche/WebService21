package x.snowroller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            while (true) {
                Socket client = serverSocket.accept();
               //Starta tråd
               //Thread thread = new Thread(() -> handleConnection(client));
               //thread.start();
               executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket client) {
        try {
            System.out.println(client.getInetAddress());
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(500);
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

            while (true) {
                var line = inputFromClient.readLine();
                if (line == null || line.isEmpty()) {
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
