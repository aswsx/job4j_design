package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 * Класс - реализация простого сокета
 *
 * @author Alex Gutorov
 * @version 1.1
 * При получении запроса, содержащего Hello выводится в консоль Hello
 * При получении запроса, содержащего Exit выполняется остановка сервера
 * При получении любого другого запроса выводится в консоль What
 */

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    private static final String HELLO = "Hello";

    public static void main(String[] args) {
        try (var server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                var socket = server.accept();
                try (var out = socket.getOutputStream();
                     var in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!(str.isEmpty())) {
                        if (str.contains("Exit")) {
                            server.close();
                        } else if (str.startsWith("GET") && !str.contains(HELLO)) {
                            out.write("What".getBytes());
                        } else if (str.startsWith("GET") && str.contains(HELLO)) {
                            out.write(HELLO.getBytes());
                        }
                        str = in.readLine();
                    }
                } catch (IOException e) {
                    LOG.error("OutputStreamError", e);
                }
            }
        } catch (IOException e) {
            LOG.error("serverSocketError", e);
        }
    }
}
