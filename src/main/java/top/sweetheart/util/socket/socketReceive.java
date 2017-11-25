package top.sweetheart.util.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;



import top.sweetheart.util.byteUtle;


public class socketReceive extends Thread{
	private Socket socket;
	private InputStream inStream;
	private byte[] receiveBys = null;
	private int index = 0;


	public socketReceive(Socket socket) throws UnknownHostException,IOException{
		this.socket = socket;
	}
	
	
	public void close() throws IOException{
		if(socket.isInputShutdown()) socket.shutdownInput();
		if(inStream != null) inStream.close();
		if(socket != null) socket.close();
	}
	
	@Override
	public void run(){
		try {
			this.inStream=this.socket.getInputStream();
			byte[] re = new byte[index];
			int len = -1;
			while((len = inStream.read(re)) != -1){
				ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
				outSteam.write(re,0,len);
				outSteam.close();
				byte[] receive = outSteam.toByteArray();
				packFiltering(receive);
			 }
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("socket:closed");
		}
		
	}
	
	byte[] allBys = null;
	int size = 0;
	int parentSize = 0;
	boolean flag = true;
	
		
	public void packFiltering(byte[] bys){
		//socketEncryption.encryption(bys);
		CHeader header = new CHeader();
		if(bys != null){
			System.out.println("date:"+new Date()+"len:"+byteUtle.byteToHex(bys));
			if(flag){
				header.add(bys);
				System.out.println(header.getSubcmd_());
				if(header.getDatasize_() == 0){
					if(Fil(header)){
						receiveBys = bys;
					}
					return;
				}else{//粘包操作
					parentSize = bys.length;
					size = header.getDatasize_()+parentSize;
					allBys = new byte[size];
					byteUtle.add(allBys, bys, 0);
					flag = false;
				}
			}else{
				byteUtle.add(allBys, bys, parentSize);
				parentSize += bys.length;
				if((size-parentSize) <= 0 ){
					if(Fil(header)){
						receiveBys = allBys;
					}
					flag = true;
					allBys = null;
					size = 0;
					parentSize = 0;
					return;
				}
			}
		}
	}
	
	public boolean Fil(CHeader header){
		if(header.getCmd_() == Es.CMD_LOBBY_ENTER ||             //进入大厅协议
		   header.getCmd_() == Es.CMD_LOBBY_GET_VERCODE ||       //客户端请求发送短信验证码或验证短信验证码
		   header.getCmd_() == Es.CMD_LOBBY_USER_REGISTER||      //玩家通过APP注册账号
		   header.getCmd_() == Es.CMD_LOBBY_LOGIN||              //登录大厅协议，用户和密码在payload中
		   header.getCmd_() == Es.CMD_LOBBY_GET_USER_INFO ||     //客户端请求用户详细信息
		   header.getCmd_() == Es.CMD_LOBBY_CHARGE               //用户充值
 	   ){
			return true;
		}
		return true;
	}
	
	
	public byte[] getReceive() throws InterruptedException{
		int i = 1500;//超时等待啊1.5秒
		while(receiveBys == null){
			Thread.sleep(10);
			i-=10;
			if(i <= 0){
				return null;
			}
		}	
		byte[] re = receiveBys;
		receiveBys = null;
		return re;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
