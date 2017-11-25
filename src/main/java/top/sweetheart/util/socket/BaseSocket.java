package top.sweetheart.util.socket;

import java.io.UnsupportedEncodingException;

public interface BaseSocket {
	public int toSize() throws UnsupportedEncodingException;
	public byte[] toByte() throws UnsupportedEncodingException;
	public void add(byte[] bys) throws UnsupportedEncodingException;

}
