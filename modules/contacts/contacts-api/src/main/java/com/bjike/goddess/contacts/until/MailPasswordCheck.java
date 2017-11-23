package com.bjike.goddess.contacts.until;

import org.apache.xmlbeans.impl.util.Base64;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MailPasswordCheck implements Runnable {

    private String userName;
    private String password;
    private String success = "update";

    public MailPasswordCheck(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private final static String host = "contacts.163.com";
    private final static int port = 443;
    private final static int TIMEOUT = 3;
    private final static int repeat = 3;
    private final static String path = "/.well-known/carddav";
    private final static SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    private final static String xmlData = "<?xml version=\"1.0\"?> <d:propfind xmlns:d=\"DAV:\" xmlns:cs=\"http://calendarserver.org/ns/\"> <d:prop> <d:displayname /> <d:getetag /> </d:prop> </d:propfind>";
    private final CountDownLatch runCount = new CountDownLatch(3);


    private String checkPassword() throws IOException {

        Socket socket = ssf.createSocket(host, port);
        socket.setSoTimeout(TIMEOUT * 1000);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        out.println("PROPFIND " + path + " HTTP/1.1");
        out.println("Host: " + host);
        String userCredentials = userName + ":" + password;
        String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes()));
        String authorization = "Authorization: " + basicAuth;
        out.println(authorization.trim());
        out.println("Content-Length: " + xmlData.length());
        out.println("Content-Type: text/xml");
        out.println("Depth: 1");
        out.println();
        out.println(xmlData);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        List<String> counts = new ArrayList<>(1);
        if (counts.size() == 0) {
            try {
                counts.add(in.readLine());
            } catch (SocketTimeoutException st) {
                return "timeout";
            }
        }
        in.close();
//        out.close();
//        socket.close();
        if (counts.get(0).contains("207")) {
            return "true";
        } else {
            return "false";
        }
    }


    @Override
    public void run() {
        for (int i = 0; i < repeat; i++) {
            try {
                String result = checkPassword();
                if (result.equals("true")) {
                    success = "success";
                    for(int j =0;j<=runCount.getCount()+1;j++){
                        runCount.countDown();
                    }
                    break;
                } else if (result.equals("false")) {
                    success = "update";
                    for(int j =0;j<=runCount.getCount()+1;j++){
                        runCount.countDown();
                    }
                    break;
                } else {
                    success = "timeout";
                    runCount.countDown();
                    System.out.println("repeating ...");
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSuccess() {
        try {
            runCount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return success;
    }


    public static void main(String[] args) {
        MailPasswordCheck mailPasswordCheck = new MailPasswordCheck("huanghuanlai_aj@163.com", "abc123");
        new Thread(mailPasswordCheck).start();
        System.out.println(mailPasswordCheck.getSuccess());
    }
}
