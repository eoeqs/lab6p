package me.lab6.server.network;

import com.google.common.primitives.Bytes;
import me.lab6.common.utility.ChunkOrganizer;
import me.lab6.common.network.Request;
import me.lab6.common.network.Response;
import me.lab6.server.io.ServerConsole;
import me.lab6.server.managers.CommandManager;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPServer {

    private final static int packageSize = 1024;
    private final static int dataSize = 1023;
    private final DatagramSocket socket;
    private final InetSocketAddress address;
    private final CommandManager commandManager;
    private final ServerConsole console;

    public UDPServer(InetAddress address, int port, CommandManager commandManager, ServerConsole console) throws SocketException {
        this.address = new InetSocketAddress(address, port);
        this.commandManager = commandManager;
        this.console = console;
        socket = new DatagramSocket(getAddress());
        socket.setReuseAddress(true);
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public Pair<Byte[], SocketAddress> receiveData() throws IOException {
        boolean received = false;
        byte[] result = new byte[0];
        SocketAddress address = null;
        while (!received) {
            byte[] data = new byte[packageSize];
            DatagramPacket packet = new DatagramPacket(data, packageSize);
            socket.receive(packet);
            address = packet.getSocketAddress();
            if (data[data.length - 1] == 1) {
                received = true;
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }
        return new ImmutablePair<>(ArrayUtils.toObject(result), address);
    }

    public void sendData(byte[] data, SocketAddress address) throws IOException {
        byte[][] chunks = ChunkOrganizer.divideIntoChunks(data, dataSize);
        for (int i = 0; i < chunks.length; i++) {
            byte[] chunk = chunks[i];
            if (i == chunks.length - 1) {
                byte[] lastChunk = Bytes.concat(chunk, new byte[]{1});
                DatagramPacket packet = new DatagramPacket(lastChunk, packageSize, address);
                socket.send(packet);
            } else {
                DatagramPacket packet = new DatagramPacket(
                        ByteBuffer.allocate(packageSize).put(chunk).array(), packageSize, address);
                socket.send(packet);
            }
        }
    }

    public void connect(SocketAddress address) throws SocketException {
        socket.connect(address);
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void close() {
        socket.close();
    }

    public void run() {
        System.out.println("Server started.");
        while (true) {
            Pair<Byte[], SocketAddress> pair;
            try {
                pair = receiveData();
            } catch (Exception e) {
                disconnect();
                continue;
            }
            Byte[] clientData = pair.getKey();
            SocketAddress address = pair.getValue();
            try {
                connect(address);
                System.out.println("Connected to " + address);
            } catch (Exception e) {
                System.out.println("FAILED to connect: " + e.getMessage());
            }
            Request request;
            try {
                request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(clientData));
            } catch (SerializationException e) {
                System.out.println("Failed to deserialize received data.");
                disconnect();
                continue;
            }
            Response response = null;
            try {
                response = commandManager.handleRequest(request);
            } catch (Exception e) {
                System.out.println("Failed to execute command: " + e.getMessage());
            }
            byte[] data = SerializationUtils.serialize(response);
            try {
                sendData(data, address);
            } catch (Exception e) {
                System.out.println("Failed to send response due to an IO error.");
            }
            disconnect();
            if (console.handleServerInput()) {
                break;
            }
        }
        close();
    }

}
