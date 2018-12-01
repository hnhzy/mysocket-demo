package com.hzy.mysocketdemo;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //1、创建一个服务器的socket，即ServerSocket,指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(9191);
            Socket socket = null;
            //记录客户端连接数
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            //循环监听等待客户端的连接
            while (true) {
                //2、Socket调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //未设置优先级可能会导致运行时速度非常慢，可降低优先级
                serverThread.setPriority(4);//设置线程优先级，范围为[1,10],默认为5
                //启动线程
                serverThread.start();

                count++;//统计客户端数量
                System.out.println("客户端连接数：" + count);
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("客户端连接地址：" + inetAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
