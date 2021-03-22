package cube;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class Cubejoin extends JFrame implements ActionListener {
	private JLabel email, pw, id, rpw, gender, birth, code;
	private JTextField emailT, idT, birthT, emailcodeT;
	private JButton okB, cancelB, overlapB, emailcodeB, checkcodeB;
	private JPasswordField pwT, rpwT;
	private JRadioButton male, female;
	private ButtonGroup gender1;
	private JComboBox combo;
	private ImageIcon backicon = new ImageIcon("img\\loginbackground.png");
	private Font font = new Font("�޸յձ�������", Font.PLAIN, 15);
	// �̸��� �ؽ�Ʈ�ʵ忡 �Է��� ���� ������
	static String mailSend;
	// �����ڵ� ����
	static String checkcode = randomNum();
	// ���� �Է� �ڵ�
	static String check;
	// �����ڵ� �߽Ÿ���
	final String user = "kysz880904@gmail.com";
	// �߽Ÿ��� ��й�ȣ
	final String password = "puppy190407";

	String mailCombo;

	private UserDTO dto = new UserDTO();
	private RumiDAO dao = new RumiDAO();

	public Cubejoin() {
		// ������ â ������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		// ��
		email = new JLabel("E@mail : ");
		pw = new JLabel("��й�ȣ : ");
		id = new JLabel("ID : ");
		rpw = new JLabel("��й�ȣ ��Ȯ�� : ");
		gender = new JLabel("����     :");
		birth = new JLabel("������� : ");
		code = new JLabel("���� �ڵ� : ");

		// �� ����
		email.setForeground(Color.white);
		pw.setForeground(Color.white);
		id.setForeground(Color.white);
		rpw.setForeground(Color.white);
		gender.setForeground(Color.white);
		birth.setForeground(Color.white);
		code.setForeground(Color.white);

		// ��ư
		// ��ư�� �̹��� �ֱ�
		okB = new JButton(new ImageIcon("img\\Ȯ��.png"));
		okB.setBorderPainted(false); // ��ư �׵θ� ����
		okB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		okB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		cancelB = new JButton(new ImageIcon("img\\���.png"));
		cancelB.setBorderPainted(false); // ��ư �׵θ� ����
		cancelB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		cancelB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		overlapB = new JButton(new ImageIcon("img\\�ߺ�üũ.png"));
		overlapB.setBorderPainted(false); // ��ư �׵θ� ����
		overlapB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		overlapB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		emailcodeB = new JButton(new ImageIcon("img\\�����ڵ�ޱ�.png"));
		emailcodeB.setBorderPainted(false); // ��ư �׵θ� ����
		emailcodeB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		emailcodeB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		checkcodeB = new JButton(new ImageIcon("img\\�����Ϸ�.png"));
		checkcodeB.setBorderPainted(false); // ��ư �׵θ� ����
		checkcodeB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		checkcodeB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		gender1 = new ButtonGroup();
		male = new JRadioButton("����");
		female = new JRadioButton("����");
		male.setOpaque(false);
		female.setOpaque(false);
		male.setForeground(Color.white);
		female.setForeground(Color.white);

		// �ؽ�Ʈ����
		emailT = new JTextField(10);
		emailcodeT = new JTextField(15);
		String mail[] = { "@naver.com", "@gmail.com", "@nate.com", "@daum.net" };
		combo = new JComboBox<String>(mail);

		pwT = new JPasswordField(16);
		idT = new JTextField(13);
		rpwT = new JPasswordField(15);
		birthT = new JTextField("YYMMDD���� �Է��ϼ���", 15);

		// birthT.setCaretColor(Color.gray);

		// ���� ũ��
		birth.setFont(birth.getFont().deriveFont(20.0f));
		email.setFont(email.getFont().deriveFont(20.0f));
		pw.setFont(pw.getFont().deriveFont(18.0f));
		code.setFont(code.getFont().deriveFont(20.0f));
		id.setFont(pw.getFont().deriveFont(20.0f));
		rpw.setFont(pw.getFont().deriveFont(18.0f));
		gender.setFont(pw.getFont().deriveFont(20.0f));
		male.setFont(pw.getFont().deriveFont(20.0f));
		female.setFont(pw.getFont().deriveFont(20.0f));

		birth.setFont(font);
		email.setFont(font);
		pw.setFont(font);
		code.setFont(font);
		id.setFont(font);
		rpw.setFont(font);
		gender.setFont(font);
		male.setFont(font);
		female.setFont(font);

		// ���ȭ��
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backicon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		// �г�
		JPanel p1 = new JPanel();
		p1.add(id);
		p1.add(idT);
		p1.add(overlapB);
		p1.setBounds(20, 70, 300, 50);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(pw);
		p2.add(pwT);
		p2.setBounds(8, 120, 300, 50);
		p2.setOpaque(false);

		JPanel p3 = new JPanel();
		p3.add(rpw);
		p3.add(rpwT);
		p3.setBounds(6, 160, 350, 60);
		p3.setOpaque(false);

		JPanel p4 = new JPanel();
		p4.add(email);
		p4.add(emailT);
		p4.add(combo);
		p4.add(emailcodeB);
		p4.setBounds((-19), 192, 510, 50);
		p4.setOpaque(false);

		JPanel p5 = new JPanel();
		p5.add(code);
		p5.add(emailcodeT);
		p5.add(checkcodeB);
		p5.setBounds(9, 236, 400, 50);
		p5.setOpaque(false);

		JPanel p6 = new JPanel();
		p6.add(birth);
		p6.add(birthT);
		p6.setBounds(8, 290, 300, 50);
		p6.setOpaque(false);

		JPanel p7 = new JPanel();
		p7.add(gender);
		gender1.add(male);
		gender1.add(female);
		p7.add(male);
		p7.add(female);
		p7.setBounds(11, 335, 250, 50);
		p7.setOpaque(false);

		JPanel p8 = new JPanel();
		p8.add(okB);
		p8.setBounds(110, 375, 60, 50);
		p8.setOpaque(false);

		JPanel p9 = new JPanel();
		p9.add(cancelB);
		p9.setBounds(250, 375, 60, 50);
		p9.setOpaque(false);

		// �����̳�
		Container c = this.getContentPane();
		// c.add(join);
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		c.add(p5);
		c.add(p6);
		c.add(p7);
		c.add(p8);
		c.add(p9);
		//c.add(p10);

		c.add(panel);

		setIconImage(iconimg);

		setBounds(700, 200, 450, 500);
		setVisible(true);
		setResizable(false);

		setTitle("Join");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Cubejoin.this, "������ �����Ͻðڽ��ϱ�", "����",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					dispose();

				} else if (result == JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
	}

	public int checkjoin(String pw, String rpw, String birth) {
		int cnt = 0;
		// �Է��� �ߴ��� Ȯ��
		if (idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵��� �Է��ϼ���");
			cnt++;
		}
		if (pwT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
			cnt++;
		}
		if (rpwT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "��Ȯ�� ��й�ȣ�� �Է��ϼ���");
			cnt++;
		}
		if (emailT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "�̸����� �Է��ϼ���");
			cnt++;
		}
		if (emailcodeT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "������ȣ�� �Է����ּ���");
			cnt++;
		}
		if (birth.length() != 6) {
			JOptionPane.showMessageDialog(this, "��������� 6�ڸ� �Է����ּ���");
			cnt++;
		}
		if ((male.isSelected() == false) && (female.isSelected() == false)) {
			JOptionPane.showMessageDialog(this, "������ �������ּ���");
			cnt++;
		}

		// �ߺ��˻� �ߴ��� Ȯ��
		if (overlapB.isEnabled()) {

			JOptionPane.showMessageDialog(this, "���̵� �ߺ��˻縦 ���ּ���");
			cnt++;
		}

		return cnt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okB) { // ȸ����Ϲ�ư
			String pw = pwT.getText();
			String rpw = rpwT.getText();
			String birth = birthT.getText();

			int cnt = checkjoin(pw, rpw, birth);
			if (cnt > 0) {
				return;
			}

			else if (cnt == 0) {
				if (pwT.getText() != null) {
					if (pwT.getText().equals(rpwT.getText())) {
						// ��й�ȣ�� null�� �ƴϰ� ��Ȯ�� ��й�ȣ�� ��ġ �Ұ�� ���ԿϷ�

						joinArticle();
						JOptionPane.showConfirmDialog(null, "������ �Ϸ�Ǿ����ϴ�", "�˸�", JOptionPane.PLAIN_MESSAGE);
						dispose();

					} else if (!pwT.getText().equals(rpwT.getText())) {

						JOptionPane.showConfirmDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�", "����", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}

			}
		} // ��Ϲ�ư

		else if (e.getSource() == cancelB) { // ��ҹ�ư
			dispose();
		} else if (e.getSource() == overlapB) { // �ߺ�üũ ��ư
			checkidArticle();

			// �ߺ��˻� �Ϸ��� ��ư ��Ȱ��ȭ
			overlapB.setEnabled(false);

		} else if (e.getSource() == emailcodeB) { // �̸��� ������ȣ �ޱ� ��ư
			mailSend = emailT.getText(); // ���� �ؽ�Ʈ �ʵ� ������
			mailCombo = (String) this.combo.getSelectedItem();

			JOptionPane.showMessageDialog(this, "�����ڵ� �߼� �Ϸ�\n������ Ȯ�����ּ���");

			if (mailSend.equals("")) {
				JOptionPane.showMessageDialog(null, "�����ּҸ� �Է��ϼ���");
			} else {
				gmailSend();
			}
		} else if (e.getSource() == checkcodeB) { // ���� �Ϸ� ��ư

			checkCode();
			if (check.equals(checkcode)) {
				// ���� �Ϸ�������� ��ư ��Ȱ��ȭ
				checkcodeB.setEnabled(false);
			}

		}
	}

	// �����ڵ� �̸��� ������
	public void gmailSend() {
		// smtp ���� ������ ����
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			// ������ �����ּ�
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailSend + mailCombo)); // ������

			// Subject
			message.setSubject("���ť�� �����ڵ� �Դϴ�"); // ���� ������ �Է�

			// Text
			message.setText("�����ڵ� 7�ڸ��� �Է� ���ּ���\n\n" + checkcode); // ���ϳ����� �Է�

			// send the message
			Transport.send(message);// ����
			System.out.println("message sent successfully...");

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// ������ȣ(�����߻�)
	public static String randomNum() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i <= 6; i++) {
			int n = (int) (Math.random() * 10);
			buffer.append(n);
		}

		return buffer.toString();
	}

	public void checkCode() {
		System.out.println(checkcode);
		check = emailcodeT.getText();
		if (check == null)
			JOptionPane.showMessageDialog(null, "�����ڵ带 �Է��ϼ���.", "Message", JOptionPane.INFORMATION_MESSAGE);

		if (checkcode.equals(check)) {
			JOptionPane.showMessageDialog(null, "�����ڵ尡 ��ġ�մϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
			if (check == null || check.equals(""))
				return;

		} else if (check.equals("")) {
			JOptionPane.showMessageDialog(null, "�����ڵ带 �Է��ϼ���.", "Message", JOptionPane.INFORMATION_MESSAGE);
			if (check == null || check.equals(""))
				return;
		} else {
			JOptionPane.showMessageDialog(null, "�����ڵ尡 Ʋ�Ƚ��ϴ�.\n���� ������ Ȯ�����ּ���", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			if (!check.equals(checkcode))
				return;

		}
	}

	private void joinArticle() {
		String id = idT.getText();
		String pw = pwT.getText();
		String birth = birthT.getText();
		String email = emailT.getText();
		String emailad = (String) this.combo.getSelectedItem();

		int gender = 0;
		if (male.isSelected())
			gender = 0;
		else if (female.isSelected())
			gender = 1;

		dto.setId(id);
		dto.setPw(pw);
		dto.setBirth(birth);
		dto.setEmail(email);
		dto.setEmailad(emailad);
		dto.setGender(gender);

		dao.joinArticle(dto);

		System.out.println("��ϿϷ�");
	}

	private void checkidArticle() {
		String id = idT.getText();
		dto.setId(id);

		int result = dao.checkidArticle(dto);

		if (result == 0) {
			JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�");
		} else if (result > 0) {
			JOptionPane.showMessageDialog(this, "�ߺ� ���̵� �ֽ��ϴ�.\n�ٸ� ���̵� �Է����ּ���");
		}
	}

	public void event() {
		okB.addActionListener(this);
		cancelB.addActionListener(this);
		overlapB.addActionListener(this);
		emailcodeB.addActionListener(this);
		checkcodeB.addActionListener(this);

	}

}
