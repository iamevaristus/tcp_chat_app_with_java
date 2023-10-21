package chatX.app.user;

public interface ChatXUserService {
    /**
     * This will broadcast any message received to other XUsers.
     * @param message String representation of the message to be sent.
     */
    void broadcastMessage(String message);

    /**
     * This controls how the XUser leaves the chat.
     * @param disconnect Boolean to state if the chat was disconnected not that the XUser left.
     */
    void leaveChat(boolean disconnect);

    /**
     * This will close every resource the XUser is using.
     */
    void closeAllResources();
}
