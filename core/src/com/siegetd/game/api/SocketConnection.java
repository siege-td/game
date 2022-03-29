package com.siegetd.game.api;

import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketConnection {

    private static final String SOCKET_URI = "ws://localhost:8877";

    private final Socket socket;

    public SocketConnection() throws URISyntaxException {
        this.socket = IO.socket(URI.create(SOCKET_URI));
        this.socket.connect();
    }

    public Socket getSocket() {
        return socket;
    }
}
