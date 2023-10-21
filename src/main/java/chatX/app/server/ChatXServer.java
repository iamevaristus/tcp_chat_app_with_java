package chatX.app.server;

import chatX.app.user.ChatXUser;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatXServer {
    public static List<ChatXUser> chatXUsers = new ArrayList<>();
    public static int port = 5454;
    public static String leaveChatKeyword = "bye";
    private ServerSocket serverSocket;

    @FunctionalInterface
    public interface ChatXServerService {
        void close();
    }

    ChatXServerService xServerService = () -> {
        if(!serverSocket.isClosed() || serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("ChatXServer has closed.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    };

    public ChatXServer(ServerSocket newServerSocket) {
        this.serverSocket = newServerSocket;
        System.out.println("ChatXServer: Server started and running on port " + serverSocket.getLocalPort() + ".");
        System.out.println("ChatXServer: Server is up and listening...\n");
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ChatXUser xUser = new ChatXUser(socket);
                System.out.println("ChatXServer: A new XUser just connected to the ChatXServer.");
                System.out.println(
                        "ChatXServer: XUser Details[ \n Port: " + xUser.getSocket().getPort() + "\n"
                                + " Username: " + xUser.getUsername() + "\n"
                                + " UserId: " + xUser.getUserId() + "\n"
                                + " IP: " + xUser.getSocket().getInetAddress() + "\n]\n"
                );
                chatXUsers.add(xUser);
                xUser.start();
            }
        } catch (Exception exception) {
            xServerService.close();
            System.err.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        try(ServerSocket socket = new ServerSocket(port)) {
            new ChatXServer(socket);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
