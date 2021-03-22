package cube;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Cubegame extends JFrame implements ActionListener {
	private JButton up, re, skip, ze, gamestart;
	private JList<UserDTO> user;
	private List<UserDTO> dtousrList;
	private DefaultListModel<UserDTO> model;
	private JPanel gameSlotPanel;
	private JPanel userSlotPanel;
	private JButton[] gameSlotBtn = new JButton[180];
	private JButton[] userSlotBtn = new JButton[40];
	private JLabel userListL;
	private ImageIcon backicon = new ImageIcon("img\\cubegameback.png");
	private JLabel logoL;
////////////////////////////////////////////////////////////////////////////// 
	private boolean checkResult;
	private int cardBuffer;
	private int gameSlotPick = 0;
	private int userSlotPick = 0;
	private int deleteCardBuffer;
	private ImageIcon pic[] = new ImageIcon[108];
	private ArrayList<CardDTO> cardList = new ArrayList<CardDTO>();
	private ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
	private UserDTO dtoBuffer = new UserDTO();
	private int[] gameSlot = new int[180];
	private int[] tempSlot = new int[180];
	private int turn = 0; // ?4?
	private int userNum = 4;
	private int[] usedCardIndex = new int[106];
	private Color1 compareColor = null;
	private int compareInt = 0;
	private int[] colorArr = new int[4];
	private int checkBuffer;
	private int jokerLeft = 0;
	private int jokerRight = 0;

	// private UserDTO usrdto = new UserDTO();

	public Cubegame(UserDTO dto) {
		// usrdto=dto;
		// ������ â ������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		System.out.println("Rumm() ������");

		model = new DefaultListModel<UserDTO>();
		user = new JList<UserDTO>(model);
		user.setBounds(950, 5, 200, 200);
		user.setOpaque(false);

		logoL = new JLabel(new ImageIcon("img\\logo.png"));
		userListL = new JLabel(new ImageIcon("img\\list.png"));

		gameSlotPanel = new JPanel();
		gameSlotPanel.setLayout(new GridLayout(9, 20));
		gameSlotPanel.setBounds(0, 0, 930, 550);
		for (int i = 0; i < 180; i++) {
			gameSlotBtn[i] = new JButton("");
			gameSlotPanel.add(gameSlotBtn[i]);
			gameSlotBtn[i].setOpaque(false);

		}
		userSlotPanel = new JPanel();
		userSlotPanel.setLayout(new GridLayout(2, 20));
		userSlotPanel.setBounds(0, 550, 930, 122);
		userSlotPanel.setBackground(new Color(220, 255, 255));
		for (int i = 0; i < 40; i++) {
			userSlotPanel.add(userSlotBtn[i] = new JButton()).setBackground(new Color(102, 51, 0));

		}
		model = new DefaultListModel<UserDTO>();

		// ��ư
		up = new JButton("���");
		re = new JButton("���");
		skip = new JButton("��ŵ");
		ze = new JButton("����");
		gamestart = new JButton("START!");

		up = new JButton(new ImageIcon("img\\registerbtn.png"));
		up.setBorderPainted(false); // ��ư �׵θ� ����
		up.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		up.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		re = new JButton(new ImageIcon("img\\cancelbtn.png"));
		re.setBorderPainted(false); // ��ư �׵θ� ����
		re.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		re.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		skip = new JButton(new ImageIcon("img\\skipbtn.png"));
		skip.setBorderPainted(false); // ��ư �׵θ� ����
		skip.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		skip.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		ze = new JButton(new ImageIcon("img\\sortbtn.png"));
		ze.setBorderPainted(false); // ��ư �׵θ� ����
		ze.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		ze.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		gamestart = new JButton(new ImageIcon("img\\startbtn.png"));
		gamestart.setBorderPainted(false); // ��ư �׵θ� ����
		gamestart.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		gamestart.setFocusPainted(false); // ��Ŀ�� ǥ�� ����

		JPanel p1 = new JPanel();
		p1.add(up);
		p1.setBounds(960, 450, 80, 50);
		p1.setOpaque(false);

		JPanel p2 = new JPanel();
		p2.add(re);
		p2.setBounds(1080, 450, 80, 50);
		p2.setOpaque(false);

		JPanel p3 = new JPanel();
		p3.add(skip);
		p3.setBounds(960, 550, 80, 50);
		p3.setOpaque(false);

		JPanel p4 = new JPanel();
		p4.add(ze);
		p4.setBounds(1080, 550, 80, 50);
		p4.setOpaque(false);

		JPanel p5 = new JPanel();
		p5.add(gamestart);
		p5.setBounds(975, 370, 170, 50);
		p5.setOpaque(false);

		JPanel p6 = new JPanel();
		p6.add(logoL);
		p6.setBounds(972, 200, 170, 170);
		p6.setOpaque(false);

		JPanel list = new JPanel();
		list.add(userListL);
		list.setBounds(947, 10, 220, 200);
		list.setOpaque(false);

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(backicon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);

			}
		};
		Container c = this.getContentPane();
		c.add(user);
		c.add(gameSlotPanel);
		c.add(userSlotPanel);
	
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		c.add(p5);
		c.add(p6);
		
		c.add(list);
		c.add(panel);

		// ��� ���ڵ带 ������ JList�� �Ѹ��� -> �ǽð����� ����Ǿ���
		RumiDAO dao = RumiDAO.getInstance();
		dtousrList = dao.getGameList();
		//dtousrList.add(dto);
		for (UserDTO usrdto : dtousrList) {
			model.addElement(usrdto);

		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//RumiDAO dao = new RumiDAO();
				int result = JOptionPane.showConfirmDialog(Cubegame.this, "������ �����Ͻðڽ��ϱ�", "����",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {

					dao.delArticle();
					System.exit(0);

				} else if (result == JOptionPane.NO_OPTION) {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					// dispose();
				}

			}

		});

		setBounds(200, 100, 1200, 720);
		setVisible(true);
		setResizable(false);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Rummikub Game");
		setIconImage(iconimg);
		//setLayout(null);
	}// ������

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed()");

		//////////// ���� ��ŸƮ��ư
		if (e.getSource() == gamestart) {
			System.out.println("startBtn");
			gamestart.setVisible(false);

			startGame();
			backUp();
			printCard();
			cardcard();

			//////////// ��� ��ư
		} else if (e.getSource() == up) {
			System.out.println("upBtn");

			checkResult = confirm();

			if (checkResult == false) {
				initialize();
				printCard();
			} else if (checkResult == true) {
				backUp();
				printCard();
			}

			//////////// ��� ��ư
		} else if (e.getSource() == re) {
			initialize();
			printCard();

			//////////// ��ŵ ��ư
		} else if (e.getSource() == skip) {
			System.out.println("skipBtn");

			int newCardIndex = newCard();
			userList.get(turn).getUserSlotList().add(cardList.get(newCardIndex));
			printCard();

			//////////// ���� ��ư
		} else if (e.getSource() == ze) {
			System.out.println("zeBtn");
			sort(userList.get(turn).getUserSlotList());
			printCard();
		}

		//////////// �������� ������ ���

		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			if (e.getSource() == userSlotBtn[i] && userSlotPick == 0 && gameSlotPick == 0) {

				System.out.println("userSlotBtn" + i + " pick");
				cardBuffer = userList.get(turn).getUserSlotList().get(i).getIndex();
				deleteCardBuffer = i;
				userSlotPick++;
				System.out.println("userSlotPick=" + userSlotPick);

			} // if
		} // for

		//////////// ���ӽ��� ������ ���, �ű����? ��������?
		for (int i = 0; i < 180; i++) {

			if (e.getSource() == gameSlotBtn[i]) {
				// �ű����, ī����ۿ� ���� ���� �ӽ� ����
				if (userSlotPick == 0 && gameSlotPick == 0 && gameSlot[i] != -1) {
					System.out.println("gameSlotBtn" + i + " pick");
					cardBuffer = gameSlot[i];
					deleteCardBuffer = i;
					gameSlotPick++;
					System.out.println("gameSlotPick=" + gameSlotPick);

					// ��������, ī����� ���� ��������
				} else if (userSlotPick == 1 || gameSlotPick == 1) {
					// �ش���ӽ����� ��������� �۵��ϵ���
					if (gameSlot[i] == -1) {
						System.out.println("gameSlotBtn" + i + " put");
						gameSlot[i] = cardBuffer;
						// ���� �ִ� ��ġ�� ������ �����ֱ�
						if (gameSlotPick == 1) {
							gameSlot[deleteCardBuffer] = -1;
						} else if (userSlotPick == 1) {
							userList.get(turn).getUserSlotList().remove(deleteCardBuffer);
							userSlotBtn[userList.get(turn).getUserSlotList().size()].setIcon(pic[106]);// �̰� ����� �ܻ� ����
						} // if

						printCard();

						gameSlotPick = 0;
						userSlotPick = 0;
					}

				} // if
			} // if
		} // for

	}// actionListener

	public void startGame() {
		System.out.println("startGame()");
		int initialIndex = 0;

		// ���ӽ��� �ε��� �ʱ�ȭ
		for (int i = 0; i < 180; i++) {
			gameSlot[i] = -1;
		}

		// �ӽý��� �ε��� �ʱ�ȭ
		for (int i = 0; i < 180; i++) {
			tempSlot[i] = -1;
		}

		// ī�� ���ߺ� �迭 �ʱ�ȭ
		for (int i = 0; i < 4; i++) {
			colorArr[i] = 0;
		}

		// ��ư ������ ����
		for (int i = 0; i < 108; i++) {
			pic[i] = new ImageIcon("img/" + i + ".png");
		}

		// �Ҵ�� ī�� ǥ��� �迭 �ʱ�ȭ
		for (int i = 0; i < 106; i++) {
			usedCardIndex[i] = 0;
		} // for

		for (int i = 1; i < 14; i++) {
			cardList.add(new CardDTO(i, Color1.RED, initialIndex));
			initialIndex++;
			cardList.add(new CardDTO(i, Color1.RED, initialIndex));
			initialIndex++;
		} // for i

		for (int i = 1; i < 14; i++) {
			cardList.add(new CardDTO(i, Color1.BLUE, initialIndex));
			initialIndex++;
			cardList.add(new CardDTO(i, Color1.BLUE, initialIndex));
			initialIndex++;
		} // for i

		for (int i = 1; i < 14; i++) {
			cardList.add(new CardDTO(i, Color1.YELLOW, initialIndex));
			initialIndex++;
			cardList.add(new CardDTO(i, Color1.YELLOW, initialIndex));
			initialIndex++;
		} // for i

		for (int i = 1; i < 14; i++) {
			cardList.add(new CardDTO(i, Color1.BLACK, initialIndex));
			initialIndex++;
			cardList.add(new CardDTO(i, Color1.BLACK, initialIndex));
			initialIndex++;
		} // for i

		cardList.add(new CardDTO(15, Color1.JOKER, initialIndex));
		initialIndex++;

		// ���� ���� �� ī�� �й�
		for (int i = 0; i < userNum; i++) {
			userList.add(new UserDTO("���̵�" + i, i));

			// 13���� ������ ī�� ����, ���� ī�� ��� �� ���Կ� �߰�
			for (int j = 0; j < 13; j++) {
				int newCardIndex = newCard();
				userList.get(i).getUserSlotList().add(cardList.get(newCardIndex));
			} // for j
		} // for i

		// ī�� ġƮŰ
		userList.get(turn).getUserSlotList().clear();

		for (int i = 0; i < 10; i = i + 1) {
			userList.get(turn).getUserSlotList().add(cardList.get(i));
		}
		for (int i = 26; i < 36; i = i + 1) {
			userList.get(turn).getUserSlotList().add(cardList.get(i));
		}
		for (int i = 52; i < 62; i = i + 1) {
			userList.get(turn).getUserSlotList().add(cardList.get(i));
		}
		for (int i = 78; i < 87; i = i + 1) {
			userList.get(turn).getUserSlotList().add(cardList.get(i));
		}
		userList.get(turn).getUserSlotList().add(cardList.get(104));

	}// startGame

	/*******
	 * �� ���̵�� 1. ��Ŀ�˻� ��Ŀ�� ������ ���� �·� null ����������, ������ ��� ���������� ���� ũ�⸦ ��� ��Ŀ���� ��� 2.
	 * �Ϲݰ˻� �������̸�, �迭[13]���� �˻�ü��, �ٸ����̸�, �迭[4]�� �˻�ü�� ���� 3. �����ٷ� �Ѿ�� ���� for�� �ȿ� if����
	 * �־ �ٳ�(20����)�� �ɸ��� break; �ϰ� ����ؼ� ī�� 3�����϶�� false 4. ��, �������� ���� ĭ���� gameSlot
	 * �迭���� �����ڸ��� �����ڸ��� ���� ������ �� ����. 5. 3ĭ�̻� �˻縦 �����ϰ� ���� 20������ ���� �߰��� �ִ��� �˻��ϸ� ������
	 * �迭���� �˻簡��
	 * 
	 * 
	 *
	 **************/

	public boolean confirm() { // ?2?3?
		System.out.println("confirm()");

		System.out.println("��ġ �˻� ==============================================");

		for (int i = 0; i < 180; i++) {

			while ((gameSlot[i] != -1)) {

				if (i % 20 == 19) {
					System.out.println("ī�尡 �ִ� ����:" + i);
					checkBuffer++;
					i++;
					break;
				}
				System.out.println("ī�尡 �ִ� ����:" + i);
				checkBuffer++;
				i++;

				// �ϴ� 19�� ������ ī�������ְ�
				// �׷��� ������
				// �׷��� ���� 20�� �������� �װ� �׳� ������ ��
				// �׷��ϱ� %20=19 �´°� �ƴϾ�?
				// �׷� 0�� ���� �� ����, �ٵ� 38�� �� ������.
				// break�� �ǳʶٴ� ������ �ƴ϶� while���� ���ͼ� �˻� �ް� �ٽÿ� ����
				// �� ���̿� ++�� �Ǹ� �ȵǴµ� ������ for������ ++ ��Ŵ �� �̰� �������� ����

			} // while

			if (checkBuffer > 0 && checkBuffer < 3) {
				System.out.println("3�� ���� ��ġ, �ʱ�ȭ, checkBuffer=" + checkBuffer + ", i=" + i);
				return false;
			} // if

			System.out.println("�� ����:" + i);
			checkBuffer = 0;
		} // for

		System.out.println("��ġ �˻� ���");

		System.out.println("��Ŀ �˻� ================================================");
		/*
		 * ��Ŀ ���� 1. ���� ������ ������? �� ���� �ȴ�....
		 * 
		 */
		for (int i = 0; i < 180; i++) {

			if (gameSlot[i] == 104) {
				System.out.println("��Ŀ ���� " + i + "��° ĭ");

				// ��Ŀ�� �����ġ ���ϱ�
				// �¿�� null�̳� ���� ���� ������ ��ġ Ȯ��
				// jokerLeft,Right�� �����ڸ� ������ ũ��
				while (gameSlot[i - jokerLeft] != -1) {
					if (i - jokerLeft % 20 == 0) {
						break;
					}
					jokerLeft++;
				}
				while (gameSlot[i + jokerRight] != -1) {
					if (i + jokerRight % 20 == 19) {
						break;
					}
					jokerRight++;
				}
				System.out.println("jokerLeft=" + jokerLeft + ", jokerRight=" + jokerRight);

				// ���� �� ���ǵ�.
				// 1.
				if (jokerLeft > 1 && jokerRight > 1
						&& cardList.get(gameSlot[i - 1]).getColor() == cardList.get(gameSlot[i + 1]).getColor()) {
					System.out.println("��Ŀ �¿�� ī�尡 �ְ�, ���� ���� ���ٸ�");
					if (cardList.get(gameSlot[i + 1]).getNum() - cardList.get(gameSlot[i - 1]).getNum() == 2) {
						System.out.println("���� �� ���̰� 2���, ���� ������, ���� �������� 1 ũ�� ����");
						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i - 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i - 1]).getNum()) + 1);

					} else {
						System.out.println("���ڰ� ���� �ʾ� �ʱ�ȭ");
						return false;
					}

					// 2. ������ �·� ��ĭ�̻� ����. �̸��̸� ��ġ�˻翡�� �ɷ���
				} else if (jokerLeft > 1 && jokerRight == 1
						&& cardList.get(gameSlot[i - 1]).getColor() == cardList.get(gameSlot[i - 2]).getColor()) {
					System.out.println("��Ŀ �������� ī�尡 �ְ�, ���� �� ī���� ���� ���ٸ�");

					if (cardList.get(gameSlot[i - 1]).getNum() == ((cardList.get(gameSlot[i - 2]).getNum()) + 1)
							&& cardList.get(gameSlot[i - 1]).getNum() != 13) {
						System.out.println("�� ī���� ���� 1�̰� ����ī�尡 13�� �ƴ� ��, ���� ����, ���� �������� 1 ũ�� ����");

						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i - 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i - 1]).getNum()) + 1);
					} else {
						System.out.println("���ڰ� ���� �ʾ� �ʱ�ȭ");
						return false;
					}
					// 3.������ ��� ��ĭ �̻� ����. �ƴϸ� ��ġ�˻翡�� �ɷ���
				} else if (jokerLeft == 1 && jokerRight > 1
						&& cardList.get(gameSlot[i + 1]).getColor() == cardList.get(gameSlot[i + 2]).getColor()) {
					System.out.println("��Ŀ �������� ī�尡 �ְ�, ���� �� ī���� ���� ���ٸ�");

					if (((cardList.get(gameSlot[i + 1]).getNum()) + 1) == cardList.get(gameSlot[i + 2]).getNum()
							&& (cardList.get(gameSlot[i + 1]).getNum() != 1)) {
						System.out.println("�� ī���� ���� 1�̰� ����ī�尡 0�� �ƴ� ��, ���� ����, ���� �������� 1 �۰� ����");
						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i + 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i + 1]).getNum()) - 1);
					} else {
						System.out.println("���ڰ� ���� �ʾ� �ʱ�ȭ");
						return false;
					}

					// 4. ���� �� ����.
					// ���� if���� ������� ������ ������, �ٸ��� ����
					// ������ �迭�� ������ ��Ŀ���� 4ĭ
					// ���ʿ������� ���� ���, ���� �ߺ� Ȯ���ϸ鼭 ����

				} else {
					System.out.println("������ ���ǿ� ���� �����Ƿ�, ���� �� �������� �˻�");
					if (compareInt == 0 && jokerLeft > 1) {
						compareInt = cardList.get(gameSlot[i - jokerLeft + 1]).getNum();

						System.out.println(
								"���� ������ �����Ƿ�, ���� ���� " + cardList.get(gameSlot[i - jokerLeft + 1]).getNum() + "�� ����");

					} else if (compareInt == 0 && jokerRight > 1) {

						compareInt = cardList.get(gameSlot[i + jokerRight - 1]).getNum();

						System.out.println(
								"���� ������ �����Ƿ�, ���� ���� " + cardList.get(gameSlot[i + jokerRight - 1]).getNum() + "�� ����");

					}

					// ���ʺ��� �� �ҷ��� �˻�
					for (int j = 1; j < jokerLeft; j++) {
						if (colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] == 0
								&& compareInt == cardList.get(gameSlot[i - jokerLeft + 1]).getNum()) {
							colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] = 1;
							// �ڵ� Ȯ�ο�
							System.out.print("i:" + i + "j" + j + "�� ���� colorArr: ");
							for (int k = 0; k < 4; k++) {
								System.out.print(colorArr[k]);
							}
						} else if (colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] != 0) {
							System.out.println("��Ŀ���� ����, �� �ߺ����� �ʱ�ȭ. i=" + i);
							return false;
						}
					}
					// �����ʵ� �˻�
					for (int j = 1; j < jokerRight; j++) {
						if (colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] == 0
								&& compareInt == cardList.get(gameSlot[i + jokerRight - 1]).getNum()) {
							colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] = 1;
						} else if (colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] != 0) {
							System.out.println("��Ŀ���� ����, �� �ߺ����� �ʱ�ȭ. i=" + i);
							return false;
						}
					}
					// �� ����� ���� ��Ŀ�� ����
					for (int j = 0; j < 4; j++) {
						if (colorArr[j] == 0) {
							cardList.get(gameSlot[i]).setColor(counterOrdinal(j));
							cardList.get(gameSlot[i]).setNum(compareInt);
							break;
						}
					}
					// ��Ŀ ���� ������� �ʱ�ȭ
					if (cardList.get(gameSlot[i]).getColor() == null) {
						System.out.println("�� �ߺ����� ���� �ʱ�ȭ");
						return false;
					}

				} // if(��Ŀ ���� ���� : ������ ���� 3+������ ����1)

				System.out.println("�ش� ��Ŀ �˻� �Ϸ�, ���� �˻縦 ���� �ʱ�ȭ");
				System.out.println(
						"��Ŀ��=" + cardList.get(gameSlot[i]).getColor() + ", ��Ŀ��=" + cardList.get(gameSlot[i]).getNum());
				for (int k = 0; k < 4; k++) {
					colorArr[k] = 0;
					System.out.print(colorArr[k]);
				} // for

				compareInt = 0;
				compareColor = null;
				jokerLeft = 0;
				jokerRight = 0;
				System.out.println("jokerLeft:" + jokerLeft + "jokerRight:" + jokerRight);
			} // if(��Ŀ����)
		} // for

		System.out.println("��Ģ �˻� ================================================");

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.println("i=" + i + ", j=" + j + ", j+(i*20)=" + (j + (i * 20)) + ", ((j+(i*20))+1)="
						+ ((j + (i * 20)) + 1));

				if ((j + (i * 20)) % 20 == 0) {
					System.out.println("�� �������� ���� ������ �ʱ�ȭ");
					compareColor = null;
					compareInt = 0;
					for (int k = 0; k < 4; k++) {
						colorArr[k] = 0;
						System.out.println(colorArr[k]);
					} // for
					System.out.println("compareInt:" + compareInt + ", compareColor:" + compareColor);

				}

				if (gameSlot[(j + (i * 20))] != -1 && gameSlot[((j + (i * 20)) + 1)] != -1) {
					System.out.println("�ڽŰ� ������ ������� �ʰ�");

					if (cardList.get(gameSlot[(j + (i * 20))]).getColor() == cardList
							.get(gameSlot[((j + (i * 20)) + 1)]).getColor()) {
						System.out.println("���̰���");

						if (cardList.get(gameSlot[(j + (i * 20))])
								.getNum() == (cardList.get(gameSlot[((j + (i * 20)) + 1)]).getNum()) - 1) {
							System.out.println("���ڰ� 1�� ũ��");

							if (compareColor == null && compareInt == 0) {
								compareColor = cardList.get(gameSlot[(j + (i * 20))]).getColor();
								System.out.println("������ ���� ����." + "���ػ��� " + compareColor + "�� ����");

							} else if (compareColor != null
									&& compareColor == cardList.get(gameSlot[(j + (i * 20))]).getColor()) {
								System.out.println("���ػ��� �ְ�, �׿� ����. ���ػ���" + compareColor);
							} else {
								System.out.println("���ػ��� ���� �ʴ´�.");
								return false;
							} // if

						} else {
							System.out.println("���ڰ� 1�� Ŀ���� �ʾҰų� �� ���� ��Ȳ. �ʱ�ȭ");
							return false;
						} // if

					} else if (cardList.get(gameSlot[(j + (i * 20))]).getColor() != cardList
							.get(gameSlot[((j + (i * 20)) + 1)]).getColor()) {
						System.out.println("���� �ٸ���");

						if (cardList.get(gameSlot[(j + (i * 20))])
								.getNum() == (cardList.get(gameSlot[((j + (i * 20)) + 1)]).getNum())) {
							System.out.println("���ڰ� ����.");

							if (compareColor == null && compareInt == 0) {
								compareInt = cardList.get(gameSlot[(j + (i * 20))]).getNum();
								colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] = 1;
								System.out.println("������ ���� ����." + "���ؼ��� " + compareInt + "�� ����, "
										+ cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()
										+ "�� ���� �迭 1�� ����");
								// �ڵ� Ȯ�ο�
								for (int z = 0; z < 4; z++) {
									System.out.print(colorArr[z]);
								}
								System.out.println();

							} else if (compareInt != -1
									&& cardList.get(gameSlot[(j + (i * 20))]).getNum() == compareInt) {
								System.out.println("���ؼ��� �ְ�, �׿� �´�. ���ؼ���" + compareInt);

								if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 0) {
									colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] = 1;
									System.out.println(
											"���� �ߺ����� �ʴ´�." + cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()
													+ "�� ���� �迭 1�� ����");
									if (colorArr[0] == 1 && colorArr[1] == 1 && colorArr[2] == 1 && colorArr[3] == 1
											&& gameSlot[i + 1] != -1) {
										System.out.println("�����ߺ����� ���� �ʱ�ȭ");
										return false;
									}

									// �ڵ� Ȯ�ο�
									for (int z = 0; z < 4; z++) {
										System.out.print(colorArr[z]);
									}
									System.out.println();

								} else if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 1) {
									System.out.println("�ش� ��(" + cardList.get(gameSlot[(j + (i * 20))]).getColor()
											+ ")�� �̹� �����ϹǷ� �ʱ�ȭ");

									// �ڵ� Ȯ�ο�
									for (int z = 0; z < 4; z++) {
										System.out.print(colorArr[z]);
									}
									System.out.println();
									return false;

								} else {
									System.out.println("���ؼ��� ���� �ʾ� �ʱ�ȭ");
									return false;
								}
							} else {
								System.out.println("���ڰ� 1�� �������� �ʾ� �ʱ�ȭ");
								return false;
							} // if

						} else {
							System.out.println("���ڰ� �ٸ��ų� �� ���� ��Ȳ���� �ʱ�ȭ");
							return false;
						} // else
					}
				} else if (gameSlot[(j + (i * 20))] != -1 && gameSlot[((j + (i * 20)) + 1)] == -1) {
					System.out.println("�ڽ��� �������� ������ ����ٸ�");
					if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 1) {
						System.out.println("������ �ߺ��̹Ƿ� �ʱ�ȭ (������ ���, �� ���ʿ�)");
						return false;
					} // if
					System.out.println("���� �ߺ� Ȯ�� ���");
					System.out.println("���� �˻縦 ���� �����ڵ� �ʱ�ȭ");
					compareColor = null;
					compareInt = 0;
					for (int k = 0; k < 4; k++) {
						colorArr[k] = 0;
						System.out.println(colorArr[k]);
					} // for
					System.out.println("compareInt:" + compareInt + ", compareColor:" + compareColor);
				} // else
			} // for j
		} // for i

//        1. ��ĭ��� ���ִ� ��� : ī���ġ�� �����̰ų� �߰����̴�. �˻��ϸ� �ȴ�.
//        2. ��ĭ��� �� ��� : ī���ġ�� ���� ����̴�. skip
//        3. ��ĭ�� ���ִ� ��� : ī���ġ�� ���������� ��Ȳ�̴�. ���� �ߺ��� �˻�, ����ϸ� ������ �ʱ�ȭ (���ڴ� �����Ͽ� �˻簡 �̷����)
//        4. ��ĭ�� ���ִ� ��� : ī���ġ�� ���۵Ǳ� �ٷ� �����̴�. ������ �ٷ� �����Ͽ� ��ĭ�� ���� �˻��� �����̹Ƿ� skip
		System.out.println("��Ģ�˻� ���");
		return true;
	}// confirm

	public void printCard() {

		System.out.println("printCard()");

		// ���ӽ��� ���
		for (int i = 0; i < 180; i++) {
			if (gameSlot[i] != -1) {
				gameSlotBtn[i].setIcon(pic[gameSlot[i]]);

			} else if (gameSlot[i] == -1) {
				gameSlotBtn[i].setIcon(pic[107]);

			} // if
		} // for

//		�������� ���
		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			userSlotBtn[i].setIcon(pic[userList.get(turn).getUserSlotList().get(i).getIndex()]);

		}
	}

	public void backUp() {
		System.out.println("backUp()");

		// ���ӽ��� ���
		for (int i = 0; i < 180; i++) {
			tempSlot[i] = gameSlot[i];
		}

		// �������� ���
		dtoBuffer.getUserSlotList().clear();
		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			dtoBuffer.getUserSlotList().add(userList.get(turn).getUserSlotList().get(i));
		}
	}// backUp

	public void initialize() {
		System.out.println("initialize()");

		// ���ӽ��� �ʱ�ȭ
		for (int i = 0; i < 180; i++) {
			gameSlot[i] = tempSlot[i];
		}

		// �������� �ʱ�ȭ
		userList.get(turn).getUserSlotList().clear();
		for (int i = 0; i < dtoBuffer.getUserSlotList().size(); i++) {
			userList.get(turn).getUserSlotList().add(dtoBuffer.getUserSlotList().get(i));
		}

		// �˻絵�� ������ �ʱ�ȭ
		compareColor = null;
		compareInt = 0;
		for (int i = 0; i < 4; i++) {
			colorArr[i] = 0;
		}
	}// initialize

	public Color1 counterOrdinal(int ordinal) {
		Color1 color = null;
		if (ordinal == 0) {
			color = Color1.RED;
		} else if (ordinal == 1) {
			color = Color1.BLUE;
		} else if (ordinal == 2) {
			color = Color1.YELLOW;
		} else if (ordinal == 3) {
			color = Color1.BLACK;
		}

		return color;
	}

	public void event() {
		System.out.println("event()");
		gamestart.addActionListener(this);
		up.addActionListener(this);
		ze.addActionListener(this);
		skip.addActionListener(this);
		re.addActionListener(this);

		for (int i = 0; i < 180; i++) {
			gameSlotBtn[i].addActionListener(this);

		} // for
		for (int i = 0; i < 40; i++) {
			userSlotBtn[i].addActionListener(this);
		} // for

	}// event

	public void sort(ArrayList<CardDTO> userSlotList) {
		System.out.println("sort()");
		Collections.sort(userSlotList);

	}

	public int newCard() {
		System.out.println("newCard()");
		int newCardIndex = ((int) (Math.random() * 104)); // 0~104������ ����
		while (usedCardIndex[newCardIndex] == 1) { // �̹� �й�� ī���� �ٽ� ������ ����
			newCardIndex = ((int) (Math.random() * 104));
		} // while
			// ������ ���� �ε�����ȣ�� ī��DTO�� ��üī���̿��� ���� ����DTO�� ����ī���̿� �߰��ϴ� ����

		usedCardIndex[newCardIndex] = 1; // �й�� ī�带 ǥ���ص�
		return newCardIndex;
	}// newCard

	public void cardcard() {
		System.out.println("ī�� �� ������ Ȯ�ο� �޼ҵ�");
		// ī�� ���� Ȯ�ο� �ڵ�
		for (int i = 0; i < cardList.size(); i++) {
			System.out.print(cardList.get(i).getColor() + " ");
			System.out.print(cardList.get(i).getNum() + " ");
			System.out.println(cardList.get(i).getIndex());
		} // for
		System.out.println("ī��� �� " + cardList.size() + "��");

	}

}// public class
