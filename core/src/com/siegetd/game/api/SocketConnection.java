package com.siegetd.game.api;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketConnection {

    private static final String SOCKET_URI = "ws://localhost:8877";

    private final Socket socket;
    
    public SocketConnection() throws URISyntaxException {
        this.socket = IO.socket(SOCKET_URI);
    }

    public Socket getSocket() {
        return socket;
    }
}
