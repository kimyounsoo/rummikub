package cube;

import java.io.Serializable;

enum RoomInfo {
	ROOM;
}

//로비에서 방만들기 할때 저장할 방 정보 dto라서 userdto랑 합쳐야되는 상황이면 합쳐도 됩니다.
public class RoomDTO implements Serializable{
	private int seq; // primaey key개념
	private String  room_name, room_pw, p1, p2, p3, p4;
	private int pcnt, port;
	private RoomInfo info;
	
	
	public RoomDTO() {
		port=9501;
		port++;
	}


	
	public RoomInfo getInfo() {
		return info;
	}

	public void setInfo(RoomInfo info) {
		this.info = info;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_pw() {
		return room_pw;
	}

	public void setRoom_pw(String room_pw) {
		this.room_pw = room_pw;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public int getPcnt() {
		return pcnt;
	}

	public void setPcnt(int pcnt) {
		this.pcnt = pcnt;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getSeq() + "/" + room_name;
	}
}
