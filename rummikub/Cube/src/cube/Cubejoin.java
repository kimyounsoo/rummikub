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
	private Font font = new Font("휴먼둥근헤드라인", Font.PLAIN, 15);
	// 이메일 텍스트필드에 입력한 메일 데이터
	static String mailSend;
	// 인증코드 난수
	static String checkcode = randomNum();
	// 유저 입력 코드
	static String check;
	// 인증코드 발신메일
	final String user = "kysz880904@gmail.com";
	// 발신메일 비밀번호
	final String password = "puppy190407";

	String mailCombo;

	private UserDTO dto = new UserDTO();
	private RumiDAO dao = new RumiDAO();

	public Cubejoin() {
		// 윈도우 창 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		// 라벨
		email = new JLabel("E@mail : ");
		pw = new JLabel("비밀번호 : ");
		id = new JLabel("ID : ");
		rpw = new JLabel("비밀번호 재확인 : ");
		gender = new JLabel("성별     :");
		birth = new JLabel("생년월일 : ");
		code = new JLabel("인증 코드 : ");

		// 라벨 색깔
		email.setForeground(Color.white);
		pw.setForeground(Color.white);
		id.setForeground(Color.white);
		rpw.setForeground(Color.white);
		gender.setForeground(Color.white);
		birth.setForeground(Color.white);
		code.setForeground(Color.white);

		// 버튼
		// 버튼에 이미지 넣기
		okB = new JButton(new ImageIcon("img\\확인.png"));
		okB.setBorderPainted(false); // 버튼 테두리 설정
		okB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		okB.setFocusPainted(false); // 포커스 표시 설정

		cancelB = new JButton(new ImageIcon("img\\취소.png"));
		cancelB.setBorderPainted(false); // 버튼 테두리 설정
		cancelB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		cancelB.setFocusPainted(false); // 포커스 표시 설정

		overlapB = new JButton(new ImageIcon("img\\중복체크.png"));
		overlapB.setBorderPainted(false); // 버튼 테두리 설정
		overlapB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		overlapB.setFocusPainted(false); // 포커스 표시 설정

		emailcodeB = new JButton(new ImageIcon("img\\인증코드받기.png"));
		emailcodeB.setBorderPainted(false); // 버튼 테두리 설정
		emailcodeB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		emailcodeB.setFocusPainted(false); // 포커스 표시 설정

		checkcodeB = new JButton(new ImageIcon("img\\인증완료.png"));
		checkcodeB.setBorderPainted(false); // 버튼 테두리 설정
		checkcodeB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		checkcodeB.setFocusPainted(false); // 포커스 표시 설정

		gender1 = new ButtonGroup();
		male = new JRadioButton("남자");
		female = new JRadioButton("여자");
		male.setOpaque(false);
		female.setOpaque(false);
		male.setForeground(Color.white);
		female.setForeground(Color.white);

		// 텍스트상자
		emailT = new JTextField(10);
		emailcodeT = new JTextField(15);
		String mail[] = { "@naver.com", "@gmail.com", "@nate.com", "@daum.net" };
		combo = new JComboBox<String>(mail);

		pwT = new JPasswordField(16);
		idT = new JTextField(13);
		rpwT = new JPasswordField(15);
		birthT = new JTextField("YYMMDD으로 입력하세요", 15);

		// birthT.setCaretColor(Color.gray);

		// 글자 크기
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

		// 배경화면
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backicon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		// 패널
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

		// 컨테이너
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
				int result = JOptionPane.showConfirmDialog(Cubejoin.this, "정말로 종료하시겠습니까", "종료",
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
		// 입력을 했는지 확인
		if (idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디을 입력하세요");
			cnt++;
		}
		if (pwT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
			cnt++;
		}
		if (rpwT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "재확인 비밀번호를 입력하세요");
			cnt++;
		}
		if (emailT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이메일을 입력하세요");
			cnt++;
		}
		if (emailcodeT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "인증번호를 입력해주세요");
			cnt++;
		}
		if (birth.length() != 6) {
			JOptionPane.showMessageDialog(this, "생년월일을 6자리 입력해주세요");
			cnt++;
		}
		if ((male.isSelected() == false) && (female.isSelected() == false)) {
			JOptionPane.showMessageDialog(this, "성별을 선택해주세요");
			cnt++;
		}

		// 중복검사 했는지 확인
		if (overlapB.isEnabled()) {

			JOptionPane.showMessageDialog(this, "아이디 중복검사를 해주세요");
			cnt++;
		}

		return cnt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okB) { // 회원등록버튼
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
						// 비밀번호가 null이 아니고 재확인 비밀번호랑 일치 할경우 가입완료

						joinArticle();
						JOptionPane.showConfirmDialog(null, "가입이 완료되었습니다", "알림", JOptionPane.PLAIN_MESSAGE);
						dispose();

					} else if (!pwT.getText().equals(rpwT.getText())) {

						JOptionPane.showConfirmDialog(null, "비밀번호가 일치하지 않습니다", "에러", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}

			}
		} // 등록버튼

		else if (e.getSource() == cancelB) { // 취소버튼
			dispose();
		} else if (e.getSource() == overlapB) { // 중복체크 버튼
			checkidArticle();

			// 중복검사 완료후 버튼 비활성화
			overlapB.setEnabled(false);

		} else if (e.getSource() == emailcodeB) { // 이메일 인증번호 받기 버튼
			mailSend = emailT.getText(); // 메일 텍스트 필드 데이터
			mailCombo = (String) this.combo.getSelectedItem();

			JOptionPane.showMessageDialog(this, "인증코드 발송 완료\n메일을 확인해주세요");

			if (mailSend.equals("")) {
				JOptionPane.showMessageDialog(null, "메일주소를 입력하세요");
			} else {
				gmailSend();
			}
		} else if (e.getSource() == checkcodeB) { // 인증 완료 버튼

			checkCode();
			if (check.equals(checkcode)) {
				// 인증 완료했을경우 버튼 비활성화
				checkcodeB.setEnabled(false);
			}

		}
	}

	// 인증코드 이메일 보내기
	public void gmailSend() {
		// smtp 서버 정보를 설정
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
			// 수신자 메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailSend + mailCombo)); // 수신자

			// Subject
			message.setSubject("루미큐브 인증코드 입니다"); // 메일 제목을 입력

			// Text
			message.setText("인증코드 7자리를 입력 해주세요\n\n" + checkcode); // 메일내용을 입력

			// send the message
			Transport.send(message);// 전송
			System.out.println("message sent successfully...");

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 인증번호(난수발생)
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
			JOptionPane.showMessageDialog(null, "인증코드를 입력하세요.", "Message", JOptionPane.INFORMATION_MESSAGE);

		if (checkcode.equals(check)) {
			JOptionPane.showMessageDialog(null, "인증코드가 일치합니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
			if (check == null || check.equals(""))
				return;

		} else if (check.equals("")) {
			JOptionPane.showMessageDialog(null, "인증코드를 입력하세요.", "Message", JOptionPane.INFORMATION_MESSAGE);
			if (check == null || check.equals(""))
				return;
		} else {
			JOptionPane.showMessageDialog(null, "인증코드가 틀렸습니다.\n인증 메일을 확인해주세요", "Message",
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

		System.out.println("등록완료");
	}

	private void checkidArticle() {
		String id = idT.getText();
		dto.setId(id);

		int result = dao.checkidArticle(dto);

		if (result == 0) {
			JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다");
		} else if (result > 0) {
			JOptionPane.showMessageDialog(this, "중복 아이디가 있습니다.\n다른 아이디를 입력해주세요");
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
