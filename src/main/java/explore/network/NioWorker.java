package explore.network;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioWorker implements Runnable{

	private Selector selector;
	
	
	public NioWorker() throws IOException{
		selector=Selector.open();
	}
	
	public void run() {
		
		for(;;){
			try {
				selector.select(500);
				if(selector.selectedKeys().isEmpty()){
					continue;
				}
				for(Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();iterator.hasNext();){
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					if(selectionKey.isReadable()){
						System.out.println("来数据了");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
	public void register(SocketChannel socketChannel) throws ClosedChannelException{
		socketChannel.register(selector, SelectionKey.OP_READ);
	}
}
