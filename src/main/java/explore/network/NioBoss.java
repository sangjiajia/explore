package explore.network;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioBoss implements Runnable {

	private Selector selector;
	private NioWorker nioWorker;

	public NioBoss(Selector selector, NioWorker nioWorker) {
		this.selector = selector;
		this.nioWorker = nioWorker;
	}

	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
			try {
				selector.select(5000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				if (selectedKeys.isEmpty()) {
					continue;
				}
				for (Iterator<SelectionKey> iterator = selectedKeys.iterator(); iterator
						.hasNext();) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey
							.channel();
					for (;;) {
						SocketChannel socketChannel = serverSocketChannel
								.accept();
						if (socketChannel == null) {
							break;
						}
						nioWorker.register(socketChannel);

					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// Prevent possible consecutive immediate failures that lead to
				// excessive CPU consumption.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// Ignore.
				}

			}
		}
	}

}
