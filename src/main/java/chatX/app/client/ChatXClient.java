package chatX.app.client;

import chatX.app.server.ChatXServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.Duration;
import java.util.Scanner;

public class ChatXClient {
    private final Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private final String username;

    public ChatXClient(Socket newSocket, String username) {
        this.socket = newSocket;
        this.username = username;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            closeResources();
        }
    }

    public void sendMessages() {
        writeMessage(username);

        while(socket.isConnected()) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            writeMessage(username + ": " + message);
            if(message.toLowerCase().contains(ChatXServer.leaveChatKeyword)) {
                closeResources();
                break;
            }
        }
    }

    public void receiveMessages() {
        new Thread(() -> {
            while(socket.isConnected()) {
                try {
                    String message = reader.readLine();
                    System.out.println(message);
                } catch (Exception e) {
                    closeResources();
                    break;
                }
            }
        }).start();
    }

    public void writeMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            closeResources();
        }
    }

    public void closeResources() {
        try {
            if(socket != null) {
                socket.close();
            }
            if(reader != null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getXUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the chat: ");
        String username = scanner.nextLine();
        if(username.isEmpty()) {
            System.err.println("Username is empty. Please enter your username.");
            return getXUsername();
        } else {
            return username;
        }
    }

    public static void welcomeIntro(String welcomeMessage) throws InterruptedException {
        for(char letter : welcomeMessage.toCharArray()) {
            new Thread(() -> System.out.print(letter)).start();
            Thread.sleep(Duration.ofMillis(200));
        }
    }

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", ChatXServer.port)) {
            welcomeIntro("Welcome to ChatX!\n");
            String username = getXUsername();
            ChatXClient xClient = new ChatXClient(socket, username);
            System.out.println("Hi" + username + " you can now start chatting...");
            xClient.receiveMessages();
            xClient.sendMessages();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
