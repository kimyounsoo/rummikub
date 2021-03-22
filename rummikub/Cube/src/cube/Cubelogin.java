package cube;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Cubelogin extends JFrame implements ActionListener, Runnable {
	private JLabel id, pw;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton loginB, joinB, findid, findpw, outB;
	private ImageIcon backicon = new ImageIcon("img\\loginbackground.png");
	// private JScrollPane scrollPane;
	private Font font = new Font("Arial", Font.BOLD, 20);

	private static RumiDAO dao = new RumiDAO();
	// server/client
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private UserDTO usrdto = new UserDTO();

	public JLabel getId() {
		return id;
	}

	public void setId(JLabel id) {
		this.id = id;
	}

	public JLabel getPw() {
		return pw;
	}

	public void setPw(JLabel pw) {
		this.pw = pw;
	}

	public JTextField getIdT() {
		return idT;
	}

	public void setIdT(JTextField idT) {
		this.idT = idT;
	}

	public JPasswordField getPwT() {
		return pwT;
	}

	public void setPwT(JPasswordField pwT) {
		this.pwT = pwT;
	}

	public JButton getLoginB() {
		return loginB;
	}

	public void setLoginB(JButton loginB) {
		this.loginB = loginB;
	}

	public JButton getJoinB() {
		return joinB;
	}

	public void setJoinB(JButton joinB) {
		this.joinB = joinB;
	}

	public JButton getFindid() {
		return findid;
	}

	public void setFindid(JButton findid) {
		this.findid = findid;
	}

	public JButton getFindpw() {
		return findpw;
	}

	public void setFindpw(JButton findpw) {
		this.findpw = findpw;
	}

	public JButton getOutB() {
		return outB;
	}

	public void setOutB(JButton outB) {
		this.outB = outB;
	}

	public ImageIcon getBackicon() {
		return backicon;
	}

	public void setBackicon(ImageIcon backicon) {
		this.backicon = backicon;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public static RumiDAO getDao() {
		return dao;
	}

	public static void setDao(RumiDAO dao) {
		Cubelogin.dao = dao;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public UserDTO getUsrdto() {
		return usrdto;
	}

	public void setUsrdto(UserDTO usrdto) {
		this.usrdto = usrdto;
	}

	public Cubelogin() {
		// 윈도우 창 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		id = new JLabel("ID	   :  ");
		pw = new JLabel("PW :  ");

		idT = new JTextField(30);
		pwT = new JPasswordField(30);

		loginB = new JButton(new ImageIcon("img\\Login.png"));
		loginB.setBorderPainted(false); // 버튼 테두리 설정
		loginB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		loginB.setFocusPainted(false); // 포커스 표시 설정

		joinB = new JButton(new ImageIcon("img\\Join.png"));
		joinB.setBorderPainted(false); // 버튼 테두리 설정
		joinB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		joinB.setFocusPainted(false); // 포커스 표시 설정

		outB = new JButton(new ImageIcon("img\\회원탈퇴.png"));
		outB.setBorderPainted(false); // 버튼 테두리 설정
		outB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		outB.setFocusPainted(false); // 포커스 표시 설정

		id.setForeground(Color.WHITE);
		pw.setForeground(Color.WHITE);
		id.setFont(font);
		pw.setFont(font);

		findid = new JButton("Forgot ID?");
		findid.setBorderPainted(false); // 버튼 테두리 설정
		findid.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		findid.setFocusPainted(false); // 포커스 표시 설정
		findid.setForeground(Color.WHITE);

		findpw = new JButton("Forgot PW?");
		findpw.setBorderPainted(false); // 버튼 테두리 설정
		findpw.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		findpw.setFocusPainted(false); // 포커스 표시 설정
		findpw.setForeground(Color.WHITE);

		// setBounds(x,y,텍스트가로,텍스트세로)
		// Login 글자

		loginB.setFont(loginB.getFont().deriveFont(18.0f));
		joinB.setFont(joinB.getFont().deriveFont(18.0f));
		outB.setFont(outB.getFont().deriveFont(18.0f));
		// Email,pw 글자크기
		id.setFont(id.getFont().deriveFont(18.0f));
		pw.setFont(pw.getFont().deriveFont(18.0f));
		findid.setFont(findid.getFont().deriveFont(16.0f));
		findpw.setFont(findpw.getFont().deriveFont(16.0f));

		// 배경화면
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(backicon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);

			}
		};

		JPanel p1 = new JPanel();

		p1.add(id);
		p1.add(idT);
		p1.setBounds(20, 100, 500, 50);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(pw);
		p2.add(pwT);
		p2.setBounds(20, 150, 500, 50);
		p2.setOpaque(false);

		JPanel p3 = new JPanel();
		p3.add(loginB);
		p3.setBounds(5, 200, 210, 60);
		p3.setOpaque(false);

		JPanel p4 = new JPanel();
		p4.add(joinB);
		p4.setBounds(150, 200, 180, 60);
		p4.setOpaque(false);

		JPanel p5 = new JPanel();
		p5.add(findid);
		p5.setBounds(39, 250, 140, 60);
		p5.setOpaque(false);

		JPanel p6 = new JPanel();
		p6.add(findpw);
		p6.setBounds(171, 250, 140, 60);
		p6.setOpaque(false);

		JPanel p7 = new JPanel();
		p7.add(outB);
		p7.setBounds(380, 270, 140, 60);
		p7.setOpaque(false);

		// scrollPane = new JScrollPane(panel);
		// panel.setLayout(null);

		Container c = this.getContentPane();
		// c.add(login);
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		c.add(p5);
		c.add(p6);
		c.add(p7);
		c.add(panel);

		setIconImage(iconimg);
		setTitle("Rummikub Login");
		setBounds(600, 300, 575, 375);
		setVisible(true);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Cubelogin.this, "정말로 종료하시겠습니까", "종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					dao.delArticle();
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		loginB.addActionListener(this);
		joinB.addActionListener(this);
		findid.addActionListener(this);
		findpw.addActionListener(this);
		outB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginB) {
			String id = idT.getText();
			String pw = pwT.getText();
			int check;

			usrdto.setId(id);
			usrdto.setPw(pw);

			int result = dao.loginArticle(usrdto);

			// 로그인 유저 리스트에서 겹치는유저가 있으면 이미 접속중이라고 알림창 뜨기
			int checkuser = dao.checkuserArticle(usrdto);

			if (result == 1) {
				if (checkuser > 0) {
					JOptionPane.showMessageDialog(null, "이미 접속중인 아이디입니다");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "로그인 성공! \n대기실로 이동합니다");

					// db에 접속 유저 넣음
					dao.userArticle(usrdto);

					// 서버에 접속(id보내기)
					// service();

					// 로비갈때도 같은ip로 서버 접속
					new Cubelobby(usrdto).service();

					dispose();
				}
			} else if (result == 0) {
				check = JOptionPane.showConfirmDialog(null, "비밀번호가 틀렸습니다.\n다시 입력하세요.", "비밀번호 오류",
						JOptionPane.PLAIN_MESSAGE);
				if (check == JOptionPane.YES_OPTION) {
					return;
				}
				// System.out.println("비밀번호 불일치"); // 알림창 만들기

			} else if (result == -1) {
				check = JOptionPane.showConfirmDialog(null, "없는 아이디 입니다.", "아이디 오류", JOptionPane.PLAIN_MESSAGE);
				if (check == JOptionPane.YES_OPTION) {
					return;
				}

				// System.out.println("아이디가 없음"); // 알림창 만들기
			} else
				System.out.println("데이터 베이스 오류");

		} else if (e.getSource() == joinB) {
			new Cubejoin().event();

		} else if (e.getSource() == findid) {
			new Findid();

		} else if (e.getSource() == findpw) {
			new Findpw();
		} else if (e.getSource() == outB) {
			new OutUsr();

		}

	}

//	public void service() {
//		// 아이피 입력해서 서버 접속하는 메소드
//		String serverIP = "192.168.0.127";
//
//		// id를 보내야함
//		String id = usrdto.getId();
//	
//		// 소켓 생성
//		try {
//			socket = new Socket(serverIP, 9500);
//
//			ois = new ObjectInputStream(socket.getInputStream());
//			oos = new ObjectOutputStream(socket.getOutputStream());
//
//			usrdto.setCommand(Info.USER);
//			// usrdto.setId(id);
//			oos.writeObject(usrdto);
//			oos.flush();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Thread t = new Thread(this);
//
//		t.start();
//	}

	public static void main(String[] args) {
		new Cubelogin();

	}

	@Override
	public void run() {
		// 로그인 창에 서버로부터 받아오는게 없음

	}
}
