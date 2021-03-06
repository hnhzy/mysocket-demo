package com.hzy.mysocketdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务器-基于UDP实现socket
 */
public class UDPServer {
    public static void main(String[] args) {
        try {
            //1、创建服务器端DatagramSocket，指定端口
            DatagramSocket socket = new DatagramSocket(8800);

            //2、创建数据报，用于接收客户端发送的数据
            byte[] data = new byte[1024];//创建字节数组，指定接收数据包的大小
            DatagramPacket packet = new DatagramPacket(data, data.length);

            //3、接收客户端发送的数据
            socket.receive(packet);//此方法在接收到数据报之前会一直阻塞

            //4、读取数据报
            String info = new String(data, 0, packet.getLength());
            System.out.println("我是服务器，客户端说：" + info);

            /**
             * 响应客户端的数据
             */
            //1、定义客户端的地址，端口号，数据
            InetAddress address=packet.getAddress();
            int port=packet.getPort();
            byte[] data2="欢迎您！".getBytes();
            //2、创建数据报包含数据信息
            DatagramPacket packet2=new DatagramPacket(data2,data2.length,address,port);
            //3、响应数据报
            socket.send(packet2);
            //4、关闭资源
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
