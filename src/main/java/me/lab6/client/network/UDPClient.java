package me.lab6.client.network;

import com.google.common.primitives.Bytes;
import me.lab6.common.ChunkOrganizer;
import me.lab6.common.Request;
import me.lab6.common.Response;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPClient {

    private final static int packageSize = 1024;
    private final static int dataSize = 1023;
    private final DatagramChannel client;
    private final InetSocketAddress addr;

    public UDPClient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port);
        this.client = DatagramChannel.open().bind(null).connect(addr);
        this.client.configureBlocking(false);
    }

    public Response communicateWithServer(Request request) throws IOException {
        byte[] data = SerializationUtils.serialize(request);
        byte[] responseBytes = sendAndReceiveData(data);
        return SerializationUtils.deserialize(responseBytes);
    }

    private void sendData(byte[] data) throws IOException {
        byte[][] chunks = ChunkOrganizer.divideIntoChunks(data, dataSize);
        for (int i = 0; i < chunks.length; i++) {
            byte[] chunk = chunks[i];
            if (i == chunks.length - 1) {
                byte[] lastChunk = Bytes.concat(chunk, new byte[]{1});
                client.send(ByteBuffer.wrap(lastChunk), addr);
            } else {
                byte[] nextChunk = Bytes.concat(chunk, new byte[]{0});
                client.send(ByteBuffer.wrap(nextChunk), addr);
            }
        }
    }

    private byte[] receivePacket() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(packageSize);
        SocketAddress address = null;
        while (address == null) {
            address = client.receive(buffer);
        }
        return buffer.array();
    }

    private byte[] receiveData() throws IOException {
        boolean received = false;
        byte[] result = new byte[0];
        while (!received) {
            byte[] data = receivePacket();
            if (data[data.length - 1] == 1) {
                received = true;
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }
        return result;
    }

    private byte[] sendAndReceiveData(byte[] data) throws IOException {
        sendData(data);
        return receiveData();
    }

}
