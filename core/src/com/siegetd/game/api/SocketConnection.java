package com.siegetd.game.api;

import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketConnection {

    private static final String SOCKET_URI = "ws://localhost:8877";

    private final Socket socket;

    private static SocketConnection instance;

    private SocketConnection() {
        this.socket = IO.socket(URI.create(SOCKET_URI));
        this.socket.connect();
    }

    public static SocketConnection getInstance() throws URISyntaxException {
        if (instance == null) {
            instance = new SocketConnection();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

}
