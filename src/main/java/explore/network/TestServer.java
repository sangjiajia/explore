package explore.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class TestServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
		
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(5566));
		
		Selector selector = Selector.open();
		
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		NioWorker worker1= new NioWorker();
		new Thread(new NioBoss(selector,worker1)).start();
		new Thread(worker1).start();
	}

}
