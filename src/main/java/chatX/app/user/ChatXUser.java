package chatX.app.user;

import chatX.app.server.ChatXServer;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
public class ChatXUser extends Thread implements ChatXUserService {
    private String username;
    private UUID userId;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ChatXUser(Socket socket) {
        this.socket = socket;
        this.userId = UUID.randomUUID();
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            username = reader.readLine();
            broadcastMessage("ChatX: Hi, " + username + " just joined the chat.");
        } catch (Exception exception) {
            leaveChat(true);
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String message = reader.readLine();
                if(message.toLowerCase().contains(ChatXServer.leaveChatKeyword)) {
                    leaveChat(false);
                    break;
                }
                broadcastMessage(message);
            } catch (Exception exception) {
                leaveChat(true);
                break;
            }
        }
    }


    @Override
    public void broadcastMessage(String message) {
        ChatXServer.chatXUsers.forEach(chatXUser -> {
            try {
                if(chatXUser.getUserId() != this.getUserId()) {
                    chatXUser.getWriter().write(message);
                    chatXUser.getWriter().newLine();
                    chatXUser.getWriter().flush();
                }
            } catch (Exception e) {
                closeAllResources();
            }
        });
    }

    @Override
    public void leaveChat(boolean disconnect) {
        ChatXServer.chatXUsers.remove(this);
        String greet;
        if(ChatXServer.chatXUsers.size() >= 2) {
            greet = " guys";
        } else {
            greet = "";
        }
        if(disconnect) {
            broadcastMessage("ChatX: Hi" + greet + ", " + username + " was disconnected from the chat.");
        } else {
            broadcastMessage("ChatX: Hi" + greet + ", " + username + " just left the chat.");
        }
        closeAllResources();
    }

    @Override
    public void closeAllResources() {
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
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
