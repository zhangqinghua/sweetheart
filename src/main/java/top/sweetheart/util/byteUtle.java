package top.sweetheart.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import top.sweetheart.util.socket.Es;



public class byteUtle {

	public static void add(byte[] data,byte[] addData,int index){
		System.arraycopy(addData, 0, data, index, addData.length);
	}
	
	public static String getType(Object obj){
		return obj.getClass().getSimpleName().toString();
	}
	
	public static int getIndex(Object obj){
		return Integer.parseInt(Es.mapSize.get(getType(obj)).toString());
	}
	
	public static int getCount(Class c){
		Field[] fields = c.getDeclaredFields();
		int count = 0;
		for(Field field:fields){
			String type = field.getType().getSimpleName().toString();
			count += Integer.parseInt(Es.mapSize.get(type)+"");
		}
		return count;

	}
	
	public static String byteToHex(byte[] b){
		StringBuilder sb = new StringBuilder("");
		if(b == null || b.length <= 0){
			return null;
		}
		for(int i = 0; i <b.length ; i++){
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if(hv.length() < 2){
				sb.append(0);
			}
			sb.append(hv+" ");
		}
		return sb.toString();
	}
	
	public static byte[] slip(byte[] data,int index,int size){
		byte[] bys = new byte[size];
		int j = 0;
		for(int i = index;i < index+size;i++ ){
			bys[j] = data[i];
			j++;
		}
		return bys;
	} 
	
	public static int OnInt(byte[] bys,int index,Object obj){
		byte[] data = slip(bys, index, getIndex(obj));
		return minByteConveter.byteToInt(data) ;
	}
	public static int OnInt1(byte[] bys,int index,int obj){
		byte[] data = slip(bys, index, obj);
		return minByteConveter.byteToInt(data) ;
	}
	public static double OnDouble(byte[] bys,int index,Object obj){
		byte[] data = slip(bys, index, getIndex(obj));
		return minByteConveter.byetToDouble(data) ;
	}
	public static String OnString(byte[] bys,int index,int str) throws UnsupportedEncodingException{
		byte[] data = slip(bys, index,str);
		return minByteConveter.bytesToString1(data).replace(" ", "") ;
	}
	public static short OnShort(byte[] bys,int index,int str) throws UnsupportedEncodingException{
		byte[] data = slip(bys, index,str);
		return minByteConveter.byteToShort(data) ;
	}
	

	
	public static void main(String[] args){
		double i =1;
		int[] a = new int[2];
		System.out.println(getType(a));
	}
	

}
