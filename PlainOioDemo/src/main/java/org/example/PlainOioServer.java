package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Yang Lianhuan
 * @version 1.0.0
 * @since 2023/6/12
 **/
public class PlainOioServer {

    /**
     * @description start a server
     *
     * @param port port
     * @author Yang Lianhuan
     * @since 2023/6/12
     */
    public void start(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                System.out.println("waite client to connect");
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(() -> {
                    InputStream in;
                    OutputStream out;
                    try {
                        in = clientSocket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        System.out.println("read from client: " + br.readLine());
                        out = clientSocket.getOutputStream();
                        out.write("Hi! Oio Client\r\n".getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        }
                        catch (IOException ex) {
                            System.out.println("client close error!");
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.start(8888);
    }
}
