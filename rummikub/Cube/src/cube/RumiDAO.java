package cube;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

//enum Color1 {RED, BLUE, YELLOW, BLACK, JOKER}

public class RumiDAO {
	private Connection conn;
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	private ResultSet rs;
	private int[] usedCardIndex = new int[105];

	private static RumiDAO instance;

	public RumiDAO() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static RumiDAO getInstance() {
		if (instance == null) {
			synchronized (RumiDAO.class) {
				instance = new RumiDAO(); // 처음 실행할 때 딱 한번만 실행

			}
		}
		return instance;
	}

	public int getRoomSeq() {
		int seq = 0;
		String sql = "select seq_room.nextval from dual";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // sequnce가 없다고 나옴
			rs.next();

			seq = rs.getInt(1); // 몇번째 column에 있는지 가져와서 seq에 넣는다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return seq;
	}

	public int getUsrSeq() {
		int seq = 0;
		String sql = "select seq_gamelist.nextval from dual";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();

			seq = rs.getInt(1); // 몇번째 column에 있는지 가져와서 seq에 넣는다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return seq;
	}

	public List<RoomDTO> getPlayerList() {
		List<RoomDTO> dtoList = new ArrayList<>();
		RoomDTO dto = new RoomDTO();
		String sql = "select room_maker,p2,p3,p4 from room where room_name like '" + dto.getRoom_name()
				+ "' order by seq";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				dto.setSeq(rs.getInt("seq"));
				dto.setP2(rs.getString("p2"));
				dto.setP3(rs.getString("p3"));
				dto.setP4(rs.getString("p4"));

				dto.setP1(rs.getString("room_maker"));
				dtoList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dtoList;
	}

	public List<RoomDTO> getRoomList() {
		List<RoomDTO> dtoList = new ArrayList<>();
		String sql = "select * from room order by seq";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setRoom_name(rs.getString("room_name"));
				// dto.setP1(rs.getString("p1"));
				dtoList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dtoList;
	}

	public List<UserDTO> getGameList() {
		List<UserDTO> dtoList = new ArrayList<>();
		String sql = "select * from gamelist order by seq";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("username"));

				dtoList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dtoList;
	}

	// 회원가입 회원등록
	public void joinArticle(UserDTO dto) {
		String sql = "insert into userinfo values('" + dto.getId() + "'" + ",'" + dto.getPw() + "', '" + dto.getEmail()
				+ dto.getEmailad() + "', 1000,'" + dto.getBirth() + "'," + dto.getGender() + ")";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dto.getId());
//			pstmt.setString(2, dto.getPw());
//			pstmt.setString(3, dto.getEmail()+dto.getEmailad());
//			pstmt.setString(4, dto.getBirth());
//			pstmt.setInt(5, dto.getGender());

			System.out.println(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 로그인
	public int loginArticle(UserDTO dto) {
		String sql = "select pw from userinfo where name='" + dto.getId() + "'";
		int result = -3;
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(dto.getPw())) {
					return result = 1; // 로그인 성공

				} else
					return result = 0; // 비밀번호 불일치

			}
			return result = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result; // 그냥 -3이 리턴됐을 경우 디비 오류
	}

	// 유저 접속 확인
	public int checkuserArticle(UserDTO dto) {
		String sql = "select username from gamelist where username='" + dto.getId() + "'";
		getConnection();
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1; // 입력 아이디로 접속한 유저가 있음
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// 아이디 중복체크
	public int checkidArticle(UserDTO dto) {
		String sql = "select count(name) as cnt from userinfo where name='" + dto.getId() + "'";
		getConnection();
		int result = 0; // default값을 사용가능한 아이디(즉, 없는아이디)

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next() == true) {
				// cnt가 1이면 중복아이디가 있음
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	// 로그인 성공해서 로비에 들어온 접속 유저 추가
	public void userArticle(UserDTO dto) {
		String sql = "insert into gamelist values('" + dto.getId() + "'," + dto.getSeq() + ")";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 아이디 찾기
	public void findidArticle(UserDTO dto) {
		String id = "";
		String sql = "select name from userinfo where birth like '" + dto.getBirth() + "' and email like '"
				+ dto.getEmail() + "'";
		getConnection();
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("name");
				JOptionPane.showMessageDialog(null, "아이디는 " + id + " 입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "아이디를 찾지 못했습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// return id;
	}

	// 비밀번호 찾기
	public void findpwArticle(UserDTO dto) {
		String pw = "";
		String sql = "select pw from userinfo where birth like '" + dto.getBirth() + "' and email like '"
				+ dto.getEmail() + "'";
		getConnection();
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pw = rs.getString("pw");
				JOptionPane.showMessageDialog(null, "비밀번호는 " + pw + " 입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 찾지 못했습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// return pw;
	}

	// 회원 탈퇴 -> 아이디, 비밀번호 맞으면 실행시키기
	public void deleteacntArticle(UserDTO dto) {
		String sql = "delete userinfo where name='" + dto.getId() + "'";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 게임 종료시 접속 유저 리스트 레코드들 삭제
	// 게임 종료시 방리스트 레코드를 삭제
	public void delArticle() {
		String sql = "delete gamelist";
		String sql2 = "delete room";
		String sql3 = "delete card";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);

			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 방 만들기
	public void createArticle(RoomDTO dto) {
//		int port;
//		port++;
		String sql = "insert into room values('" + dto.getPort() + "','" + dto.getRoom_name() + "','" + dto.getRoom_pw()
				+ "','" + dto.getP1() + "','" + dto.getP2() + "','" + dto.getP3() + "','" + dto.getP4() + "',"
				+ dto.getPcnt() + "," + dto.getSeq() + ")";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 방 들어가기 -> list에 선택된 방정보를 가지고 게임 입장 pcnt가 4가 넘지않도록 (4인이상 접속시 이미 꽉찬 방, 비밀번호 입력
	// 확인)
	public void enterArticle(RoomDTO dto) {
		String sql = "update room set p2='" + dto.getP2() + "', p3='" + dto.getP3() + "', p4='" + dto.getP4();
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 카드 테이블 초기화 -> player구분하려고 만듬
	public void initcardArticle() {
		String sql = "insert into card values(0,0,0,0,0,0,0,0,0,0,0,0,0,'p1')";
		String sql2 = "insert into card values(0,0,0,0,0,0,0,0,0,0,0,0,0,'p2')";
		String sql3 = "insert into card values(0,0,0,0,0,0,0,0,0,0,0,0,0,'p3')";
		String sql4 = "insert into card values(0,0,0,0,0,0,0,0,0,0,0,0,0,'p4')";

		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);

			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt4.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// card index 배분
	public void insertcardArticle() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				int index = newCard();
				String sql = "update card set c" + j + "=" + index + " where name='p" + i + "'";
				getConnection();

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}

	}

	// 모든 플레이어의 카드 인덱스 가져오기
	public void allcardArticle() {
		String sql = "select c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13 from card";
//		int[][] card = new int[4][13];

		Vector<String> card = new Vector<String>();

		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				card.addElement(Integer.toString(rs.getInt("c1")));
				card.addElement(Integer.toString(rs.getInt("c2")));
				card.addElement(Integer.toString(rs.getInt("c3")));
				card.addElement(Integer.toString(rs.getInt("c4")));
				card.addElement(Integer.toString(rs.getInt("c5")));
				card.addElement(Integer.toString(rs.getInt("c6")));
				card.addElement(Integer.toString(rs.getInt("c7")));
				card.addElement(Integer.toString(rs.getInt("c8")));
				card.addElement(Integer.toString(rs.getInt("c9")));
				card.addElement(Integer.toString(rs.getInt("c10")));
				card.addElement(Integer.toString(rs.getInt("c11")));
				card.addElement(Integer.toString(rs.getInt("c12")));
				card.addElement(Integer.toString(rs.getInt("c13")));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : card) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------");
		
		UserDTO userDTO = new UserDTO();
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		System.out.println(card.size());
		
		String[] str=new String[card.size()];
		str=(String[])card.toArray(str);
		
		
		
//		for (int i = 0; i < 4; i++) {
//			userList.add(userDTO);	//user 4명만 받을거임
//			for (int j = 0; j < 13; j++) {
//				userList.get(i).getUserSlotList()
//						.add(userDTO.getCardList().get(str));
//			}
//		}

		System.out.println(userDTO.getCardList().size());
	}

	public int newCard() {
		System.out.println("newCard()");
		int newCardIndex = ((int) (Math.random() * 104)); // 0~104사이의 난수
		while (usedCardIndex[newCardIndex] == 1) { // 이미 분배된 카드라면 다시 난수로 받음
			newCardIndex = ((int) (Math.random() * 104));
		} // while
			// 난수로 얻은 인덱스번호의 카드DTO를 전체카드어레이에서 꺼내 유저DTO의 유저카드어레이에 추가하는 구문

		usedCardIndex[newCardIndex] = 1; // 분배된 카드를 표시해둠
		return newCardIndex;
	}// newCard

}

//////////////////////////////////////////
