package com.WebSocketRpc.domain.ports;

public class CannotAddSession extends RuntimeException {
    public CannotAddSession(String message) {
        super(message);
    }
}
