package x.snowroller;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
            var url = readRequest(inputFromClient);

            var outputToClient = client.getOutputStream();
            //Routing
            if( url.equals("/cat.png"))
                sendImageResponse(outputToClient);
            else
                sendJsonResponse(outputToClient);


            inputFromClient.close();
            outputToClient.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendImageResponse(OutputStream outputToClient) throws IOException {

        String header = "";
        byte[] data = new byte[0];
        File f = new File("cat.png");
        if (!(f.exists() && !f.isDirectory())) {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        }
        else
        {
            try(FileInputStream fileInputStream = new FileInputStream(f)){
                data = new byte[(int) f.length()];
                fileInputStream.read(data);
                header = "HTTP/1.1 200 OK\r\nContent-Type: image/png\r\nContent-length: " + data.length +"\r\n\r\n";
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }

    private static void sendJsonResponse(OutputStream outputToClient) throws IOException {
        //Return Json information
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("Martin", 43, true));
//        persons.add(new Person("Kalle", 23, false));
//        persons.add(new Person("Anna", 11, true));

        var persons = List.of(new Person("Martin", 43, true),new Person("Martin", 43, true),new Person("Martin", 43, true));

        Gson gson = new Gson();

        String json = gson.toJson(persons);
        System.out.println(json);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length +"\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);
        //outputToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
        outputToClient.flush();
    }

    private static String readRequest(BufferedReader inputFromClient) throws IOException {
        var url ="";

        while (true) {
            var line = inputFromClient.readLine();
            if( line.startsWith("GET"))
                url = line.split(" ")[1];
            if (line == null || line.isEmpty()) {
                break;
            }
            System.out.println(line);
        }
        return url;
    }
}
