package x.snowroller;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Connection from : " + client.getInetAddress());
                executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            readRequest(inputFromClient);

            var outputToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outputToClient);

            inputFromClient.close();
            outputToClient.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(PrintWriter outputToClient) {
        //Return Json information
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("Martin", 43, true));
//        persons.add(new Person("Kalle", 23, false));
//        persons.add(new Person("Anna", 11, true));

        var persons = List.of(new Person("Martin", 43, true),new Person("Martin", 43, true),new Person("Martin", 43, true));

        Gson gson = new Gson();

        String json = gson.toJson(persons);
        System.out.println(json);


        outputToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
        outputToClient.flush();
    }

    private static void readRequest(BufferedReader inputFromClient) throws IOException {
        while (true) {
            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            System.out.println(line);
        }
    }
}
