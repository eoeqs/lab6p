package me.lab6.client.network;

import me.lab6.common.Encoder;
import me.lab6.common.Request;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPClient {
    private final DatagramChannel client;
    private final InetSocketAddress addr;

    public UDPClient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port);
        this.client = DatagramChannel.open().bind(null).connect(addr);
        this.client.configureBlocking(false);
        System.out.println("DatagramChannel подключен к " + addr);
    }

    public void sendMessage(Request request) {
        try {
            ByteBuffer buffer = Encoder.encode(request);
            while (buffer.hasRemaining()) {
                client.send(buffer, addr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
