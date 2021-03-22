package cube;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;



public class Findid extends JFrame implements ActionListener {

	// ���̵� ã���Ҷ� �ʿ��� ����
	private JLabel birth, email;
	private JTextField birthT, emailT;
	private JButton findidB;

	private RumiDAO dao = new RumiDAO();
	private UserDTO dto = new UserDTO();

	private ImageIcon backicon = new ImageIcon("img\\loginbackground.png");
	private Font font = new Font("�޸յձ�������",Font.PLAIN, 15);

	public Findid() {
		// ������ â ������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		birth = new JLabel("������� : ");
		birth.setForeground(Color.white);
		birth.setFont(font);
		//birth.setFont(birth.getFont().deriveFont(15.0f));

		email = new JLabel("�̸��� : ");
		email.setForeground(Color.white);
		//email.setFont(email.getFont().deriveFont(15.0f));
		email.setFont(font);
		
		birthT = new JTextField("YYMMDD���� �Է��ϼ���", 20);
		emailT = new JTextField(20);

		findidB = new JButton(new ImageIcon("img\\ForgotID.png"));
		findidB.setBorderPainted(false);
		findidB.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		findidB.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		birth.setBounds(250, 1, 100, 50);
		email.setBounds(350, 100, 100, 300);

		// ���ȭ��
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backicon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel p1 = new JPanel();
		p1.add(birth);
		p1.add(birthT);
		p1.setBounds(10, 100, 350, 50);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(email);
		p2.add(emailT);
		p2.setBounds(10, 150, 350, 50);
		p2.setOpaque(false);

		JPanel p3 = new JPanel();
		p3.add(findidB);
		p3.setBounds(50, 210, 280, 100);
		p3.setOpaque(false);

		Container c = this.getContentPane();
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(panel);

		setBounds(600, 300, 390, 330);
		setVisible(true);
		setResizable(false);
		setIconImage(iconimg);
		setTitle("ForgorID?");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Findid.this, "������ �����Ͻðڽ��ϱ�", "����", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION)
					dispose();
				else if (result == JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});

		findidB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findidB) {
			String birth = birthT.getText();
			String email = emailT.getText();
		//	String emailad = dto.getEmailad();

			dto.setBirth(birth);
			dto.setEmail(email);
			//dto.setEmailad(emailad);

			// String id=dao.findidArticle(dto);
			// �˸�â���� id�˷��ֱ�
			dao.findidArticle(dto);

		}
	}
}
