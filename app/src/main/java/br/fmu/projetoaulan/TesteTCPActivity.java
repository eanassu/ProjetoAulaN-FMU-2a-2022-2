package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TesteTCPActivity extends AppCompatActivity {
    private String SERVER = "10.180.202.157";
    private static int PORT_TCP = 9009;
    private String msgTCP = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_tcpactivity);
        msgTCP = "texto teste TCP";
        ThreadTCP tcp = new ThreadTCP();
        tcp.start();
    }

    class ThreadTCP extends Thread {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String msgResponse = "";
        private String error = "";
        public void run() {
            msgTCP = "enviando mensagem TCP";
            try {
                socket = new Socket(SERVER, PORT_TCP);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                out.writeUTF(msgTCP);
                msgResponse = in.readUTF();
                TesteTCPActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Recebido: " + msgResponse);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}