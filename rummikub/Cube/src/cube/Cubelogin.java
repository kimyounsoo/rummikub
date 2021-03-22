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
		// ������ â ������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		id = new JLabel("ID	   :  ");
		pw = new JLabel("PW :  ");

		idT = new JTextField(30);
		pwT = new JPasswordField(30);

		loginB = new JButton(new ImageIcon("img\\Login.png"));
		loginB.setBorderPainted(false); // ��ư �׵θ� ����
		loginB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		loginB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		joinB = new JButton(new ImageIcon("img\\Join.png"));
		joinB.setBorderPainted(false); // ��ư �׵θ� ����
		joinB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		joinB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		outB = new JButton(new ImageIcon("img\\ȸ��Ż��.png"));
		outB.setBorderPainted(false); // ��ư �׵θ� ����
		outB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		outB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		id.setForeground(Color.WHITE);
		pw.setForeground(Color.WHITE);
		id.setFont(font);
		pw.setFont(font);

		findid = new JButton("Forgot ID?");
		findid.setBorderPainted(false); // ��ư �׵θ� ����
		findid.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		findid.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		findid.setForeground(Color.WHITE);

		findpw = new JButton("Forgot PW?");
		findpw.setBorderPainted(false); // ��ư �׵θ� ����
		findpw.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		findpw.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		findpw.setForeground(Color.WHITE);

		// setBounds(x,y,�ؽ�Ʈ����,�ؽ�Ʈ����)
		// Login ����

		loginB.setFont(loginB.getFont().deriveFont(18.0f));
		joinB.setFont(joinB.getFont().deriveFont(18.0f));
		outB.setFont(outB.getFont().deriveFont(18.0f));
		// Email,pw ����ũ��
		id.setFont(id.getFont().deriveFont(18.0f));
		pw.setFont(pw.getFont().deriveFont(18.0f));
		findid.setFont(findid.getFont().deriveFont(16.0f));
		findpw.setFont(findpw.getFont().deriveFont(16.0f));

		// ���ȭ��
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
				int result = JOptionPane.showConfirmDialog(Cubelogin.this, "������ �����Ͻðڽ��ϱ�", "����",
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

			// �α��� ���� ����Ʈ���� ��ġ�������� ������ �̹� �������̶�� �˸�â �߱�
			int checkuser = dao.checkuserArticle(usrdto);

			if (result == 1) {
				if (checkuser > 0) {
					JOptionPane.showMessageDialog(null, "�̹� �������� ���̵��Դϴ�");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "�α��� ����! \n���Ƿ� �̵��մϴ�");

					// db�� ���� ���� ����
					dao.userArticle(usrdto);

					// ������ ����(id������)
					// service();

					// �κ񰥶��� ����ip�� ���� ����
					new Cubelobby(usrdto).service();

					dispose();
				}
			} else if (result == 0) {
				check = JOptionPane.showConfirmDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.\n�ٽ� �Է��ϼ���.", "��й�ȣ ����",
						JOptionPane.PLAIN_MESSAGE);
				if (check == JOptionPane.YES_OPTION) {
					return;
				}
				// System.out.println("��й�ȣ ����ġ"); // �˸�â �����

			} else if (result == -1) {
				check = JOptionPane.showConfirmDialog(null, "���� ���̵� �Դϴ�.", "���̵� ����", JOptionPane.PLAIN_MESSAGE);
				if (check == JOptionPane.YES_OPTION) {
					return;
				}

				// System.out.println("���̵� ����"); // �˸�â �����
			} else
				System.out.println("������ ���̽� ����");

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
//		// ������ �Է��ؼ� ���� �����ϴ� �޼ҵ�
//		String serverIP = "192.168.0.127";
//
//		// id�� ��������
//		String id = usrdto.getId();
//	
//		// ���� ����
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
		// �α��� â�� �����κ��� �޾ƿ��°� ����

	}
}
