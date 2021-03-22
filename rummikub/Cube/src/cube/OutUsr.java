package cube;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;



public class OutUsr extends JFrame implements ActionListener {

	// 탈퇴할 아이디랑 비밀번호 입력
	private JLabel id, pw;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton outB, cancelB;

	private RumiDAO dao = new RumiDAO();
	private UserDTO dto = new UserDTO();

	private ImageIcon backicon = new ImageIcon("img\\loginbackground.png");
	private Font font = new Font("휴먼둥근헤드라인", Font.PLAIN, 15);

	public OutUsr() {
		// 윈도우 창 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		id = new JLabel("ID  : ");
		id.setForeground(Color.white);
		id.setFont(font);
	
		id.setBounds(250, 1, 100, 50);

		pw = new JLabel("PW : ");
		pw.setForeground(Color.white);
	
		pw.setFont(font);
		pw.setBounds(350, 100, 100, 300);

		idT = new JTextField(20);
		pwT = new JPasswordField(20);

		outB = new JButton(new ImageIcon("img\\확인.png"));
		outB.setBorderPainted(false); // 버튼 테두리 설정
		outB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		outB.setFocusPainted(false); // 포커스 표시 설정

		cancelB = new JButton(new ImageIcon("img\\취소.png"));
		cancelB.setBorderPainted(false); // 버튼 테두리 설정
		cancelB.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		cancelB.setFocusPainted(false); // 포커스 표시 설정

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
		p1.setBounds(10, 100, 350, 50);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(pw);
		p2.add(pwT);
		p2.setBounds(10, 150, 350, 50);
		p2.setOpaque(false);

		JPanel p3 = new JPanel();
		p3.add(outB);
		p3.add(cancelB);
		p3.setBounds(50, 200, 280, 150);
		p3.setOpaque(false);

		Container c = this.getContentPane();
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(panel);

		setBounds(600, 300, 390, 320);
		setVisible(true);
		setResizable(false);
		setIconImage(iconimg);
		setTitle("Delete Account");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(OutUsr.this, "정말로 종료하시겠습니까", "종료", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION)
					dispose();
				if (result == JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});

		outB.addActionListener(this);
		cancelB.addActionListener(this);
	}

	public int inputcheck() {
		int cnt = 0;
		// 입력을 했는지 확인
		if (idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "ID을 입력하세요");
			cnt++;
		}
		if (pwT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "PW를 입력하세요");
			cnt++;
		}
		return cnt;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == outB) {

			String id = idT.getText();
			String pw = pwT.getText();

			//id와 pw를 입력했는지
			int check = inputcheck();
			if (check > 0) {//입력안되어있음
				return;
			} else if (check == 0) {
				dto.setId(id);
				dto.setPw(pw);

				// 아이디와 비밀번호가 맞는지 확인
				int corr = dao.loginArticle(dto);
				if (corr == 1) {
					// 맞는 id와pw면 계정 삭제 완료
					dao.deleteacntArticle(dto);
					JOptionPane.showConfirmDialog(null, id+"님의 계정이 삭제되었습니다.");
					dispose();
				} else if (corr == 0) {
					// id와 pw가 다름
					 JOptionPane.showMessageDialog(this, "ID와 PW가 맞지 않습니다.\n다시 입력해주세요","오류",JOptionPane.INFORMATION_MESSAGE);
					 
					return;

				}
			}

		}else if(e.getSource()==cancelB) {
			dispose();
		}
	}
}
