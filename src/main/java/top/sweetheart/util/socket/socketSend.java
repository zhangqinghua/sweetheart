package top.sweetheart.util.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import top.sweetheart.util.byteUtle;



public class socketSend {
	private Socket socket;
	private OutputStream outStream = null;
	private int index = 0;

	public socketSend(Socket socket) throws IOException {
		this.socket = socket;
		this.outStream = this.socket.getOutputStream();
	}
	
	public void close() throws IOException{
		if(outStream != null) outStream.close();
		if(socket.isOutputShutdown()) socket.shutdownOutput();
	}
	
	
	public void send(byte[] sendBys){
		try {
			byte[] send = sendBys;
			//socketEncryption.encryption(send);
			System.out.println("send:"+byteUtle.byteToHex(send));
			outStream.write(send);
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
