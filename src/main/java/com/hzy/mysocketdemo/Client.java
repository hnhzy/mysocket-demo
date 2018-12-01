package com.hzy.mysocketdemo;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            //1、创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("localhost", 9191);

            //2、获取输出流，向服务器端发送消息
//            OutputStream os=socket.getOutputStream();//字节输出流
//            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
//            pw.write("用户名：admin11;密码：12344");
//            pw.flush();
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            User user = new User("admin", "123");
            oos.writeObject(user);
            oos.flush();
            socket.shutdownOutput();//关闭输出流

            //3、获取输入流，并读取服务器的响应信息
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务端说：" + info);
            }
            socket.shutdownInput();//关闭输入流

            //4、关闭相关资源
            br.close();
            isr.close();
            is.close();
            oos.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
