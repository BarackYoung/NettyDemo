package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Yang Lianhuan
 * @version 1.0.0
 * @since 2023/6/12
 **/
public class PlainOioClient {

    /**
     * @description send data to a server
     *
     * @param port port
     * @author Yang Lianhuan
     * @since 2023/6/12
     */
    public void send(int port) {
        String serverName = "127.0.0.1";
        try (Socket client = new Socket(serverName, port)) {
            System.out.println("connect success " + client.getRemoteSocketAddress());
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);
            out.write("Hi, OIO Server\r\n".getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("return from server： " + br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PlainOioClient plainOioClient = new PlainOioClient();
        plainOioClient.send(8888);
    }
}
