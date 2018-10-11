package sample;

import java.net.*;
import java.io.*;
import java.lang.Thread;
public class P2PSocket {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader r;
    private BufferedWriter w;
    private boolean isConnected = false;
    boolean connected() {return isConnected;}
    public P2PSocket(int port){
        // Just wait for connector
        try{
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("Accepted a client\n");
            isConnected = true;
            r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            w = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public P2PSocket(String host, int port){
        try{
            socket = new Socket(host,port);
            System.out.println("Connected...");
            r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            w = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));}
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void sendMessage(String s) {
        try{
            w.write(s);
            w.newLine();
            w.flush();
        }
        catch(Exception e) {
            System.err.println(e.toString());
        }
    }

    public String getLine() {
        String s = "";
        try{
            while (s=="") s = r.readLine();
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
        return s;
    }

    public void close() {
        try {
            socket.close();
            serverSocket.close();}
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

}