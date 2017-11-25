package top.sweetheart.util.socket;

import top.sweetheart.util.byteUtle;
import top.sweetheart.util.minByteConveter;

public class CHeader implements BaseSocket{
	private int       smagic_;       // 协议头开始标记
	private int       type_;         // 协议类型:
	private int		  datasize_;	 // 附加数据字节长度
	private int       ver_;			 // 协议版本
	private int       crypt_;		 // 加密方式：
	private int       keyid_;		 // 密钥数组下标
	private int       cmd_;			 // 命令指示
	private int       subcmd_;		 // 命令附加数据，具体内容视具体命令
	private int	      uid_;			 // 用户ID，由系统分配的用户标识，有些命令需要客户端携带该信息
	private int 	  reserved1_;	 // 保留域
	private double	  reserved2_;	 // 保留域
	private int		  lobby_id_;	 // 大厅号
	private int		  room_id_;		 // 房间号
	private int		  tour_id_;		 // 比赛号
	private int       emagic_;		 // 协议头结束标记
	
	public CHeader() {
		/*this.smagic_ = 'K';
		this.type_ = 0x100;
		this.datasize_ = 0;
		this.ver_ = 0x14041721;
		this.crypt_ = 0x000FF;
		this.keyid_ = 0;
		this.cmd_ = 0x41000237;
		this.subcmd_ = 0;
		this.uid_ = 0;
		this.reserved1_ = 0;
		this.reserved2_ = 0;
		this.lobby_id_ = 0;
		this.tour_id_ = 0;
		this.room_id_ = 0;
		this.emagic_ = 'F';*/

		this.smagic_ = 'B';
		this.type_ = 0x100;
		this.datasize_ = 0;
		this.ver_ = 0x14041721;
		this.crypt_ = 0x000FF;
		this.keyid_ = 0;
		this.cmd_ = 0x40000010;
		this.subcmd_ = 0;
		this.uid_ = 0;
		this.reserved1_ = 0;
		this.reserved2_ = 0;
		this.lobby_id_ = 0;
		this.tour_id_ = 0;
		this.room_id_ = 0;
		this.emagic_ = 'S';
	}
	
	public int toSize(){
		return byteUtle.getCount(this.getClass());
	}
	public byte[] toByte() {
		int i = 0; 
		byte[] toByte = new byte[byteUtle.getCount(this.getClass())];
		byteUtle.add(toByte, minByteConveter.intToByte(smagic_), i);
		byteUtle.add(toByte, minByteConveter.intToByte(type_), i+=byteUtle.getIndex(smagic_));
		byteUtle.add(toByte, minByteConveter.intToByte(datasize_), i+=byteUtle.getIndex(type_));
		byteUtle.add(toByte, minByteConveter.intToByte(ver_), i+=byteUtle.getIndex(datasize_));
		byteUtle.add(toByte, minByteConveter.intToByte(crypt_),i+=byteUtle.getIndex(ver_));
		byteUtle.add(toByte, minByteConveter.intToByte(keyid_),i+=byteUtle.getIndex(crypt_));
		byteUtle.add(toByte, minByteConveter.intToByte(cmd_), i+=byteUtle.getIndex(keyid_));
		byteUtle.add(toByte, minByteConveter.intToByte(subcmd_), i+=byteUtle.getIndex(cmd_));
		byteUtle.add(toByte, minByteConveter.intToByte(uid_), i+=byteUtle.getIndex(subcmd_));
		byteUtle.add(toByte, minByteConveter.intToByte(reserved1_), i+=byteUtle.getIndex(uid_));
		byteUtle.add(toByte, minByteConveter.doubleToByte(reserved2_), i+=byteUtle.getIndex(reserved1_));
		byteUtle.add(toByte, minByteConveter.intToByte(lobby_id_), i+=byteUtle.getIndex(reserved2_));
		byteUtle.add(toByte, minByteConveter.intToByte(room_id_), i+=byteUtle.getIndex(lobby_id_));
		byteUtle.add(toByte, minByteConveter.intToByte(tour_id_), i+=byteUtle.getIndex(room_id_));
		byteUtle.add(toByte, minByteConveter.intToByte(emagic_), i+=byteUtle.getIndex(tour_id_));
		return toByte;
	}
	
	public void add(byte[] bys){
		int i = 0; 
		this.smagic_ =  byteUtle.OnInt(bys, i, smagic_);
		this.type_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(smagic_), type_);
		this.datasize_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(type_), datasize_);
		this.ver_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(datasize_), ver_);
		this.crypt_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(ver_), crypt_);
		this.keyid_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(crypt_), keyid_);
		this.cmd_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(keyid_), cmd_);
		this.subcmd_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(cmd_), subcmd_);
		this.uid_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(subcmd_), uid_);
		this.reserved1_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(uid_), reserved1_);
		this.reserved2_ =  byteUtle.OnDouble(bys, i+=byteUtle.getIndex(reserved1_), reserved2_);
		this.lobby_id_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(reserved2_), lobby_id_);
		this.room_id_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(lobby_id_), room_id_);
		this.tour_id_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(room_id_), tour_id_);
		this.emagic_ =  byteUtle.OnInt(bys, i+=byteUtle.getIndex(tour_id_), emagic_);
	}


	@Override
	public String toString() {
		return "CHeader [smagic_=" + smagic_ + ", type_=" + type_
				+ ", datasize_=" + datasize_ + ", ver_=" + ver_ + ", crypt_="
				+ crypt_ + ", keyid_=" + keyid_ + ", cmd_=" + cmd_
				+ ", subcmd_=" + subcmd_ + ", uid_=" + uid_ + ", reserved1_="
				+ reserved1_ + ", reserved2_=" + reserved2_ + ", lobby_id_="
				+ lobby_id_ + ", room_id_=" + room_id_ + ", tour_id_="
				+ tour_id_ + ", emagic_=" + emagic_ + "]";
	}


	
	
	public int getSmagic_() {
		return smagic_;
	}

	public void setSmagic_(int smagic_) {
		this.smagic_ = smagic_;
	}

	public int getType_() {
		return type_;
	}

	public void setType_(int type_) {
		this.type_ = type_;
	}

	public int getDatasize_() {
		return datasize_;
	}

	public void setDatasize_(int datasize_) {
		this.datasize_ = datasize_;
	}

	public int getVer_() {
		return ver_;
	}

	public void setVer_(int ver_) {
		this.ver_ = ver_;
	}

	public int getCrypt_() {
		return crypt_;
	}

	public void setCrypt_(int crypt_) {
		this.crypt_ = crypt_;
	}

	public int getKeyid_() {
		return keyid_;
	}

	public void setKeyid_(int keyid_) {
		this.keyid_ = keyid_;
	}

	public int getCmd_() {
		return cmd_;
	}

	public void setCmd_(int cmd_) {
		this.cmd_ = cmd_;
	}

	public int getSubcmd_() {
		return subcmd_;
	}

	public void setSubcmd_(int subcmd_) {
		this.subcmd_ = subcmd_;
	}

	public int getUid_() {
		return uid_;
	}

	public void setUid_(int uid_) {
		this.uid_ = uid_;
	}

	public int getReserved1_() {
		return reserved1_;
	}

	public void setReserved1_(int reserved1_) {
		this.reserved1_ = reserved1_;
	}

	public double getReserved2_() {
		return reserved2_;
	}

	public void setReserved2_(double reserved2_) {
		this.reserved2_ = reserved2_;
	}

	public int getLobby_id_() {
		return lobby_id_;
	}

	public void setLobby_id_(int lobby_id_) {
		this.lobby_id_ = lobby_id_;
	}

	public int getRoom_id_() {
		return room_id_;
	}

	public void setRoom_id_(int room_id_) {
		this.room_id_ = room_id_;
	}

	public int getTour_id_() {
		return tour_id_;
	}

	public void setTour_id_(int tour_id_) {
		this.tour_id_ = tour_id_;
	}

	public int getEmagic_() {
		return emagic_;
	}

	public void setEmagic_(int emagic_) {
		this.emagic_ = emagic_;
	}
	
	
}

