package cube;

import java.awt.*;

import java.awt.event.*;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Cubelobby extends JFrame implements ActionListener, Runnable {

	private JTextArea output;
	private JTextField input;
	private JScrollPane scroll;

	private JList<UserDTO> user;
	private JList<RoomDTO> room;

	private JButton loadB, sendB, createB;
	private DefaultListModel<UserDTO> usrmodel;
	private DefaultListModel<RoomDTO> roommodel;
	private JLabel LogoL, manual, userListL, roomListL;

	private ImageIcon backicon = new ImageIcon("img\\backgroundz.png");

	// private static RumiDAO dao = new RumiDAO();

	private List<UserDTO> dtousrList; // 접속 유저 리스트
	private List<RoomDTO> dtoroomList;
	private UserDTO usrdto = new UserDTO();
	private RoomDTO roomdto = new RoomDTO();

	// server/client
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public List<UserDTO> getDtousrList() {
		return dtousrList;
	}

	public JTextArea getOutput() {
		return output;
	}

	public JTextField getInput() {
		return input;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public JList<UserDTO> getUser() {
		return user;
	}

	public JList<RoomDTO> getRoom() {
		return room;
	}

	public JButton getLoadB() {
		return loadB;
	}

	public JButton getSendB() {
		return sendB;
	}

	public JButton getCreateB() {
		return createB;
	}

	public DefaultListModel<UserDTO> getUsrmodel() {
		return usrmodel;
	}

	public DefaultListModel<RoomDTO> getRoommodel() {
		return roommodel;
	}

	public JLabel getLogoL() {
		return LogoL;
	}

	public JLabel getManual() {
		return manual;
	}

	public JLabel getUserListL() {
		return userListL;
	}

	public JLabel getRoomListL() {
		return roomListL;
	}

	public ImageIcon getBackicon() {
		return backicon;
	}

	public List<RoomDTO> getDtoroomList() {
		return dtoroomList;
	}

	public UserDTO getUsrdto() {
		return usrdto;
	}

	public RoomDTO getRoomdto() {
		return roomdto;
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public Cubelobby(UserDTO dto) {
		usrdto = dto;

		// 윈도우 창 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		// 로그인 접속자 리스트
		usrmodel = new DefaultListModel<UserDTO>();
		user = new JList<UserDTO>(usrmodel);
		user.setBounds(20, 310, 190, 190);
		user.setOpaque(false);

		roommodel = new DefaultListModel<RoomDTO>();
		room = new JList<RoomDTO>(roommodel);
		room.setBounds(13, 50, 100, 100); // ????
		room.setOpaque(false);

		loadB = new JButton(new ImageIcon("img\\EnterRoom.png"));
		loadB.setOpaque(false);
		loadB.setBorderPainted(false);
		loadB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		loadB.setFocusPainted(false); // 포커스 표시 설정

		createB = new JButton(new ImageIcon("img\\MakingRoom.png"));
		createB.setOpaque(false);
		createB.setBorderPainted(false);
		createB.setContentAreaFilled(false);
		createB.setFocusPainted(false);

		createB.setFont(new Font("맑은고딕", Font.BOLD, 28));
		loadB.setFont(new Font("맑은고딕", Font.BOLD, 25));

		// 로고입히기용 라벨
		LogoL = new JLabel(new ImageIcon("img\\logo.png"));

		// 룰 입히기용 라벨
		manual = new JLabel(new ImageIcon("img\\설명서.png"));

		// 유저리스트 라벨
		userListL = new JLabel(new ImageIcon("img\\roomlist.png"));

		roomListL = new JLabel(new ImageIcon("img\\list.png"));
		// 채팅
		output = new JTextArea("", 12, 23);
		scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		output.setEditable(false);

		input = new JTextField(14);

		sendB = new JButton(new ImageIcon("img\\확인.png"));
		sendB.setBorderPainted(false);
		sendB.setContentAreaFilled(false);
		sendB.setFocusPainted(false);

		// 배경화면
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(backicon.getImage(), -225, -27, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		JPanel p1 = new JPanel();
		p1.add(output);
		p1.setBounds(220, 20, 300, 250);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(input);
		p2.add(sendB);
		p2.setBounds(230, 240, 300, 35);
		p2.setOpaque(false);

		JPanel p6 = new JPanel();
		p6.add(createB);
		p6.setOpaque(false);
		p6.setBounds(270, 300, 240, 100);

		JPanel p3 = new JPanel();
		p3.add(loadB);
		p3.setBounds(270, 390, 240, 100);
		p3.setOpaque(false);

		JPanel p4 = new JPanel();
		p4.add(LogoL);
		p4.setBounds(525, 5, 170, 170);
		p4.setOpaque(false);

		JPanel p5 = new JPanel();
		p5.add(manual);
		p5.setBounds(540, 180, 170, 230);
		p5.setOpaque(false);

		JPanel userlist = new JPanel();
		userlist.add(userListL);
		userlist.setBounds(2, 280, 260, 350);
		userlist.setOpaque(false);

		JPanel roomlist = new JPanel();
		roomlist.add(roomListL);
		roomlist.setOpaque(false);
		roomlist.setBounds(10, 30, 220, 220);

		Container c = this.getContentPane();
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p6);
		c.add(p4);
		c.add(p5);
		c.add(user);
		c.add(room);
		c.add(userlist);
		c.add(roomlist);
		c.add(panel);

		setBounds(400, 150, 750, 550);
		setVisible(true);
		setResizable(false);
		setTitle("Lobby");
		setIconImage(iconimg);

		// 모든 레코드를 꺼내서 JList에 뿌리기 -> 실시간으로 적용되야함
		RumiDAO dao = RumiDAO.getInstance();
		usrmodel.removeAllElements();
		dtousrList = dao.getGameList();
		for (UserDTO usrdto : dtousrList) {
			usrmodel.addElement(usrdto);

		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				if (ois == null || oos == null)
					System.exit(0);
				int result = JOptionPane.showConfirmDialog(Cubelobby.this, "정말로 종료하시겠습니까", "종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					usrdto.setCommand(Info.EXIT);

					dao.delArticle();
				} else if (result == JOptionPane.NO_OPTION) {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					// dispose();
				}

				try {

					oos.writeObject(usrdto);
					oos.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
				System.exit(0);
			}

		});

		loadB.addActionListener(this);
		sendB.addActionListener(this);

		createB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadB) {
			RumiDAO dao = new RumiDAO();
			dao.initcardArticle();
			dao.insertcardArticle();
			dao.allcardArticle();
			Cubegame game = new Cubegame(usrdto);
			game.event();
			dispose();
		} else if (e.getSource() == sendB) {
			// 서버로 보내는쪽
			String msg = input.getText();

			usrdto.setCommand(Info.SEND);
			usrdto.setMessage(msg);

			try {
				oos.writeObject(usrdto);
				oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			input.setText("");
		} else if (e.getSource() == createB) {
			create();
			Cubegame game = new Cubegame(usrdto);
			game.event();
			// dispose();
		}
	}

	public void create() {

		String roomname = JOptionPane.showInputDialog(null, "방 이름을 입력하세요");
		if (roomname.equals("")) {
			JOptionPane.showMessageDialog(null, "방 이름을 입력해주세요");
		}

		String roompw = JOptionPane.showInputDialog(null, "방 비밀번호를 입력하세요");
		if (roompw.equals("")) {
			JOptionPane.showMessageDialog(null, "방 비밀번호를 입력해주세요");
		}

		// 첫번째로 만든 방만 db에 들어감 -> repaint필요
		// 방 리스트 -> 모든 레코드를 꺼내서 JList에 뿌리기
		RumiDAO dao = RumiDAO.getInstance();
		dtousrList.add(usrdto);
		int seq = dao.getRoomSeq();
		roomdto.setSeq(seq);
		roomdto.setP1(usrdto.getId());
		roomdto.setRoom_name(roomname);
		roomdto.setRoom_pw(roompw);

		dao.createArticle(roomdto);

		roommodel.removeAllElements();
		dtoroomList = dao.getRoomList();
		for (RoomDTO roomdto : dtoroomList) {
			roommodel.addElement(roomdto);

		}
		System.out.println("dao 완료");

	}

	public void service() {// 아이피 입력해서 서버 접속하는 메소드
		String serverIP = "192.168.0.127";

		// dto 에서 아이디 꺼내오기
		dtousrList.add(usrdto);
		String id = usrdto.getId();
		JOptionPane.showMessageDialog(null, id + "님 환영합니다.", "Message", JOptionPane.INFORMATION_MESSAGE);

		// 소켓생성
		try {
			socket = new Socket(serverIP, 9500);

			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);

		} catch (IOException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		}

		try {
			// 서버로 아이디 보내기
			// UserDTO dto = new UserDTO(); // 서버로 dto보내기 위해 dto 생성
			usrdto.setCommand(Info.JOIN);
			usrdto.setId(id);
			oos.writeObject(usrdto);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 스레드 생성
		Thread t = new Thread(this);
		// 스레드 시작 - 스레드 실행(run())
		t.start();

	}// service()

	@Override
	public void run() { // 서버한테 받는 메소드 -> 스레드 도는중 ?
		usrdto = null;
		while (true) {
			try {
				usrdto = (UserDTO) ois.readObject();

				if (usrdto.getCommand() == Info.USER) { // 조건 걸어줘야됌
					// 모든 레코드를 꺼내서 JList에 뿌리기
					RumiDAO dao = RumiDAO.getInstance();
					dtousrList = dao.getGameList();
					for (UserDTO dto : dtousrList) {
						usrmodel.addElement(dto);

					}
				} else if (usrdto.getCommand() == Info.EXIT) {

					ois.close();
					oos.close();
					socket.close();

					System.exit(0);
				} else if (usrdto.getCommand() == Info.SEND) {
					output.append(usrdto.getMessage() + "\n");

					int pos = output.getText().length();
					output.setCaretPosition(pos);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
