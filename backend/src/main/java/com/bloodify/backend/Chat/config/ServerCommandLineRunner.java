package com.bloodify.backend.Chat.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
    private final SocketIOServer server;
    //TODO: set to true to enable websocket
    private boolean run = true;
    @Override
    public void run(String... args) throws Exception {

        if(run) server.start();
    }
}
