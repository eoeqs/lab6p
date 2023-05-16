package me.lab6.server;


import me.lab6.common.Request;
import me.lab6.common.exceptions.ExitException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class UDPDatagramServer {
    private final InetSocketAddress addr;
    private ServerConsole serverConsole;
    private byte[] buffer;
    private final DatagramSocket datagramSocket;


    public UDPDatagramServer(InetSocketAddress addr, ServerConsole serverConsole) throws SocketException {
        this.addr = addr;
        this.datagramSocket = new DatagramSocket(26566);
        this.datagramSocket.setReuseAddress(true);
        this.serverConsole = serverConsole;
    }

    public void run() throws IOException {
        datagramSocket.setSoTimeout(500);
        while (true) {
            try {
                serverConsole.checkServerConsole();
                if (checkForMessage()) {
                    Object message = getMessage();
                    if (message instanceof Request) System.out.println(((Request) message).command());
                }
            } catch (ExitException e) {
                System.exit(0);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Object getMessage() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object msg = ois.readObject();
        return msg;
    }

    private boolean checkForMessage() throws IOException {
        try {
            var arr = new byte[Integer.BYTES];
            datagramSocket.receive(new DatagramPacket(arr,4));
            buffer = new byte[ByteBuffer.wrap(arr).getInt()];
            datagramSocket.receive(new DatagramPacket(buffer, ByteBuffer.wrap(arr).getInt()));
            return true;
        }catch (SocketTimeoutException e){
            return false;
        }
    }
}
