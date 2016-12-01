package com.mufan.message.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by aswe on 2016/9/13.
 */
//@Component
public class MessageServer {
    public void starAcceptIOTMessageServer() {
        new acceptIOTMessageServer().start();
    }

    private class acceptIOTMessageServer extends Thread {
        public void run() {
            ServerSocket server;
            try {
                int port = 12345;
                server = new ServerSocket(port);
                System.out.println("消息监听启动:" + port + "    启动监控:" + port);
                for (; ; ) {
                    Socket skt = server.accept();
                    new ReceiveIOTMessage(skt).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReceiveIOTMessage extends Thread {
        private MessageResolveService messageResolveService = new MessageResolveServiceImpl();
        private Socket skt;

        public ReceiveIOTMessage(Socket skt) {
            this.skt = skt;
        }

        public void run() {
            try {
//                InputStreamReader is = new InputStreamReader(skt.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
//                for (; ; ) {
                   /* byte b[] = new byte[1024];
                    int len = 0;
                    int temp = 0;
                    while ((temp = is.read()) != -1) {
                        b[len] = (byte) temp;
                        len++;
                    }
                    if (len != 0) {
                        String message = new String(b, 0, len).replaceAll("\\\\s*|\\t|\\r|\\n", "");
                        System.out.println("消息：" + message);
                        messageResolveService.parserMessage(message);
                    }*/

                    for (;;) {
                        String s = br.readLine();
                        if (s == null || "".equals(s)){
                            break;
                        }else {
                            String message = s.replaceAll("\\\\s*|\\t|\\r|\\n", "");
                            System.out.println(message);
                            messageResolveService.parserMessage(message);
                        }

                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*private class ReceiveIOTMessage extends Thread {

        private MessageResolveService messageResolveService = new MessageResolveServiceImpl();
        private Socket skt;

        public ReceiveIOTMessage(Socket skt) {
            this.skt = skt;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                for (;;) {
                    String s = br.readLine();
                    if (s == null || "".equals(s)){
                        break;
                    }else{
                        System.out.println("消息："+s+"\t"+skt.getInetAddress()+"\t"+s.length());
                    }
                }
                InputStreamReader is = new InputStreamReader(skt.getInputStream());
                for (; ; ) {
                    byte b[] = new byte[1024];
                    int len = 0;
                    int temp = 0;
                    while ((temp = is.read()) != -1) {
                        b[len] = (byte) temp;
                        len++;
                    }
                    if (len != 0) {
                        String old = new String(b,0,len);
                        String message = new String(b, 0, len).replaceAll("\\\\s*|\\t|\\r|\\n", "");
                        System.out.println("消息：" + message);
                        messageResolveService.parserMessage(message);

                        String msg[] = message.split(",");
                        for(String m:msg){
                            System.out.println(m);
                        }
//                        System.out.println(new Date(Long.parseLong(msg[msg.length - 1]) * 1000));

                        StringBuffer unicode = new StringBuffer();
                        for (int i = 0; i < old.length(); i++) {
                            // 取出每一个字符
                            char c = old.charAt(i);
                            // 转换为unicode
//                            unicode.append("\\u" + String.format("%4s", Integer.toHexString(c)).replace(" ","0"));
                            unicode.append("\\u" +  Integer.toHexString(c & 0xffff));
                        }
                        System.out.println(unicode.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    /*public static void main(String[] args) {
        new MessageServer().starAcceptIOTMessageServer();
    }*/
}
