package Client;

import exceptions.SendException;

import java.net.*;
import java.io.*;

public class Client {
    private int serverPort = 1488, intCommand;
    private String address = "127.0.0.1";
    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private String stringCommand = "";



    public Client() {
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // ������� ������ ������� ���������� ������������� IP-�����.
            Socket socket = new Socket(ipAddress, serverPort); // ������� ����� ��������� IP-����� � ���� �������.
            // ����� ������� � �������� ������ ������, ������ ����� �������� � �������� ������ ��������.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            //System.out.println("socket timeout: " + socket.getSoTimeout());
            //socket.setSoTimeout(1000000);
            //System.out.println("socket timeout: " + socket.getSoTimeout());
            // ������������ ������ � ������ ���, ���� ����� ������������ ��������� ���������.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public String ReadUTF_UnProtected() {
        try {
            if( in.available() == 0 ) return "";
            stringCommand = in.readUTF();
            System.out.println(stringCommand);
            return stringCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String ReadUTF() {
        try {
            stringCommand = in.readUTF();
            System.out.println(stringCommand);
            return stringCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int readInt() {
        try {
            //if (in.available() == 0) return 0;
            intCommand = in.readInt();
            System.out.println(intCommand);
            return intCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void SendUTF(String s) throws SendException{
        try {
            if (s.length() <= 0)
                throw new SendException();
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendInt(int i) {
        try {
            out.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}