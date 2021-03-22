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
		// 윈도우 창 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconimg = toolkit.getImage("img\\logo.png");

		System.out.println("Rumm() 생성자");

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

		// 버튼
		up = new JButton("등록");
		re = new JButton("취소");
		skip = new JButton("스킵");
		ze = new JButton("정렬");
		gamestart = new JButton("START!");

		up = new JButton(new ImageIcon("img\\registerbtn.png"));
		up.setBorderPainted(false); // 버튼 테두리 설정
		up.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		up.setFocusPainted(false); // 포커스 표시 설정

		re = new JButton(new ImageIcon("img\\cancelbtn.png"));
		re.setBorderPainted(false); // 버튼 테두리 설정
		re.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		re.setFocusPainted(false); // 포커스 표시 설정

		skip = new JButton(new ImageIcon("img\\skipbtn.png"));
		skip.setBorderPainted(false); // 버튼 테두리 설정
		skip.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		skip.setFocusPainted(false); // 포커스 표시 설정

		ze = new JButton(new ImageIcon("img\\sortbtn.png"));
		ze.setBorderPainted(false); // 버튼 테두리 설정
		ze.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		ze.setFocusPainted(false); // 포커스 표시 설정

		gamestart = new JButton(new ImageIcon("img\\startbtn.png"));
		gamestart.setBorderPainted(false); // 버튼 테두리 설정
		gamestart.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		gamestart.setFocusPainted(false); // 포커스 표시 설정

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

		// 모든 레코드를 꺼내서 JList에 뿌리기 -> 실시간으로 적용되야함
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
				int result = JOptionPane.showConfirmDialog(Cubegame.this, "정말로 종료하시겠습니까", "종료",
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
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed()");

		//////////// 게임 스타트버튼
		if (e.getSource() == gamestart) {
			System.out.println("startBtn");
			gamestart.setVisible(false);

			startGame();
			backUp();
			printCard();
			cardcard();

			//////////// 등록 버튼
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

			//////////// 취소 버튼
		} else if (e.getSource() == re) {
			initialize();
			printCard();

			//////////// 스킵 버튼
		} else if (e.getSource() == skip) {
			System.out.println("skipBtn");

			int newCardIndex = newCard();
			userList.get(turn).getUserSlotList().add(cardList.get(newCardIndex));
			printCard();

			//////////// 정렬 버튼
		} else if (e.getSource() == ze) {
			System.out.println("zeBtn");
			sort(userList.get(turn).getUserSlotList());
			printCard();
		}

		//////////// 유저슬롯 눌렀을 경우

		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			if (e.getSource() == userSlotBtn[i] && userSlotPick == 0 && gameSlotPick == 0) {

				System.out.println("userSlotBtn" + i + " pick");
				cardBuffer = userList.get(turn).getUserSlotList().get(i).getIndex();
				deleteCardBuffer = i;
				userSlotPick++;
				System.out.println("userSlotPick=" + userSlotPick);

			} // if
		} // for

		//////////// 게임슬롯 눌렀을 경우, 옮기려고? 놓으려고?
		for (int i = 0; i < 180; i++) {

			if (e.getSource() == gameSlotBtn[i]) {
				// 옮기려고, 카드버퍼에 슬롯 내용 임시 저장
				if (userSlotPick == 0 && gameSlotPick == 0 && gameSlot[i] != -1) {
					System.out.println("gameSlotBtn" + i + " pick");
					cardBuffer = gameSlot[i];
					deleteCardBuffer = i;
					gameSlotPick++;
					System.out.println("gameSlotPick=" + gameSlotPick);

					// 놓으려고, 카드버퍼 내용 꺼내오기
				} else if (userSlotPick == 1 || gameSlotPick == 1) {
					// 해당게임슬롯이 비었을때만 작동하도록
					if (gameSlot[i] == -1) {
						System.out.println("gameSlotBtn" + i + " put");
						gameSlot[i] = cardBuffer;
						// 원래 있던 위치는 아이콘 지워주기
						if (gameSlotPick == 1) {
							gameSlot[deleteCardBuffer] = -1;
						} else if (userSlotPick == 1) {
							userList.get(turn).getUserSlotList().remove(deleteCardBuffer);
							userSlotBtn[userList.get(turn).getUserSlotList().size()].setIcon(pic[106]);// 이거 지우면 잔상 남음
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

		// 게임슬롯 인덱스 초기화
		for (int i = 0; i < 180; i++) {
			gameSlot[i] = -1;
		}

		// 임시슬롯 인덱스 초기화
		for (int i = 0; i < 180; i++) {
			tempSlot[i] = -1;
		}

		// 카드 색중복 배열 초기화
		for (int i = 0; i < 4; i++) {
			colorArr[i] = 0;
		}

		// 버튼 아이콘 생성
		for (int i = 0; i < 108; i++) {
			pic[i] = new ImageIcon("img/" + i + ".png");
		}

		// 할당된 카드 표기용 배열 초기화
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

		// 유저 생성 및 카드 분배
		for (int i = 0; i < userNum; i++) {
			userList.add(new UserDTO("아이디" + i, i));

			// 13장의 무작위 카드 생성, 유저 카드 목록 및 슬롯에 추가
			for (int j = 0; j < 13; j++) {
				int newCardIndex = newCard();
				userList.get(i).getUserSlotList().add(cardList.get(newCardIndex));
			} // for j
		} // for i

		// 카드 치트키
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
	 * 새 아이디어 1. 조커검사 조커를 만나면 먼저 좌로 null 만날때까지, 만나면 우로 만날때까지 행의 크기를 잡고 조커값을 계산 2.
	 * 일반검사 같은색이면, 배열[13]으로 검사체적, 다른색이면, 배열[4]로 검사체적 설정 3. 다음줄로 넘어가는 문제 for문 안에 if문을
	 * 넣어서 줄끝(20단위)에 걸리면 break; 하고 계산해서 카드 3장이하라면 false 4. 좌, 우측으로 남은 칸수는 gameSlot
	 * 배열수의 일의자리와 십의자리를 보면 유추할 수 있음. 5. 3칸이상 검사를 진행하고 나서 20단위의 수가 중간에 있는지 검사하면 단절된
	 * 배열인지 검사가능
	 * 
	 * 
	 *
	 **************/

	public boolean confirm() { // ?2?3?
		System.out.println("confirm()");

		System.out.println("배치 검사 ==============================================");

		for (int i = 0; i < 180; i++) {

			while ((gameSlot[i] != -1)) {

				if (i % 20 == 19) {
					System.out.println("카드가 있는 슬롯:" + i);
					checkBuffer++;
					i++;
					break;
				}
				System.out.println("카드가 있는 슬롯:" + i);
				checkBuffer++;
				i++;

				// 일단 19가 들어오면 카운팅해주고
				// 그러고 내보내
				// 그러고 나면 20이 들어오겠지 그건 그냥 받으면 되
				// 그러니까 %20=19 맞는거 아니야?
				// 그럼 0도 들어올 수 있음, 근데 38이 못 들어오네.
				// break는 건너뛰는 느낌이 아니라 while문을 나와서 검사 받고 다시와 느낌
				// 그 사이에 ++이 되면 안되는데 나가면 for문에서 ++ 시킴 아 이게 무제였네 ㅅㅂ

			} // while

			if (checkBuffer > 0 && checkBuffer < 3) {
				System.out.println("3장 이하 배치, 초기화, checkBuffer=" + checkBuffer + ", i=" + i);
				return false;
			} // if

			System.out.println("빈 슬롯:" + i);
			checkBuffer = 0;
		} // for

		System.out.println("배치 검사 통과");

		System.out.println("조커 검사 ================================================");
		/*
		 * 조커 관련 1. 조거 두장이 만나면? 아 존나 싫다....
		 * 
		 */
		for (int i = 0; i < 180; i++) {

			if (gameSlot[i] == 104) {
				System.out.println("조커 등장 " + i + "번째 칸");

				// 조커의 상대위치 구하기
				// 좌우로 null이나 줄의 끝을 만나는 위치 확인
				// jokerLeft,Right는 본인자리 포함한 크기
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

				// 같은 색 조건들.
				// 1.
				if (jokerLeft > 1 && jokerRight > 1
						&& cardList.get(gameSlot[i - 1]).getColor() == cardList.get(gameSlot[i + 1]).getColor()) {
					System.out.println("조커 좌우로 카드가 있고, 둘의 색이 같다면");
					if (cardList.get(gameSlot[i + 1]).getNum() - cardList.get(gameSlot[i - 1]).getNum() == 2) {
						System.out.println("둘의 수 차이가 2라면, 색은 같은색, 수는 좌측보다 1 크게 설정");
						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i - 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i - 1]).getNum()) + 1);

					} else {
						System.out.println("숫자가 맞지 않아 초기화");
						return false;
					}

					// 2. 무조건 좌로 두칸이상 있음. 미만이면 배치검사에서 걸러짐
				} else if (jokerLeft > 1 && jokerRight == 1
						&& cardList.get(gameSlot[i - 1]).getColor() == cardList.get(gameSlot[i - 2]).getColor()) {
					System.out.println("조커 좌측에만 카드가 있고, 좌측 두 카드의 색이 같다면");

					if (cardList.get(gameSlot[i - 1]).getNum() == ((cardList.get(gameSlot[i - 2]).getNum()) + 1)
							&& cardList.get(gameSlot[i - 1]).getNum() != 13) {
						System.out.println("두 카드의 차가 1이고 좌측카드가 13이 아닐 때, 색은 같게, 수는 좌측보다 1 크게 설정");

						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i - 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i - 1]).getNum()) + 1);
					} else {
						System.out.println("숫자가 맞지 않아 초기화");
						return false;
					}
					// 3.무조건 우로 두칸 이상 있음. 아니면 배치검사에서 걸러짐
				} else if (jokerLeft == 1 && jokerRight > 1
						&& cardList.get(gameSlot[i + 1]).getColor() == cardList.get(gameSlot[i + 2]).getColor()) {
					System.out.println("조커 우측에만 카드가 있고, 우측 두 카드의 색이 같다면");

					if (((cardList.get(gameSlot[i + 1]).getNum()) + 1) == cardList.get(gameSlot[i + 2]).getNum()
							&& (cardList.get(gameSlot[i + 1]).getNum() != 1)) {
						System.out.println("두 카드의 차가 1이고 우측카드가 0이 아닐 때, 색은 같게, 수는 우측보다 1 작게 설정");
						cardList.get(gameSlot[i]).setColor(cardList.get(gameSlot[i + 1]).getColor());
						cardList.get(gameSlot[i]).setNum((cardList.get(gameSlot[i + 1]).getNum()) - 1);
					} else {
						System.out.println("숫자가 맞지 않아 초기화");
						return false;
					}

					// 4. 같은 수 조건.
					// 위의 if문들 벗어났으면 무조건 같은수, 다른색 조합
					// 같은수 배열은 무조건 조커포함 4칸
					// 왼쪽에서부터 숫자 잡고, 색깔 중복 확인하면서 진행

				} else {
					System.out.println("같은색 조건에 맞지 않으므로, 같은 수 조건으로 검사");
					if (compareInt == 0 && jokerLeft > 1) {
						compareInt = cardList.get(gameSlot[i - jokerLeft + 1]).getNum();

						System.out.println(
								"아직 기준이 없으므로, 기준 수를 " + cardList.get(gameSlot[i - jokerLeft + 1]).getNum() + "로 설정");

					} else if (compareInt == 0 && jokerRight > 1) {

						compareInt = cardList.get(gameSlot[i + jokerRight - 1]).getNum();

						System.out.println(
								"아직 기준이 없으므로, 기준 수를 " + cardList.get(gameSlot[i + jokerRight - 1]).getNum() + "로 설정");

					}

					// 왼쪽부터 쭉 불러서 검사
					for (int j = 1; j < jokerLeft; j++) {
						if (colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] == 0
								&& compareInt == cardList.get(gameSlot[i - jokerLeft + 1]).getNum()) {
							colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] = 1;
							// 코드 확인용
							System.out.print("i:" + i + "j" + j + "일 때의 colorArr: ");
							for (int k = 0; k < 4; k++) {
								System.out.print(colorArr[k]);
							}
						} else if (colorArr[cardList.get(gameSlot[i - jokerLeft + j]).getColor().ordinal()] != 0) {
							System.out.println("조커슬롯 좌측, 색 중복으로 초기화. i=" + i);
							return false;
						}
					}
					// 오른쪽도 검사
					for (int j = 1; j < jokerRight; j++) {
						if (colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] == 0
								&& compareInt == cardList.get(gameSlot[i + jokerRight - 1]).getNum()) {
							colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] = 1;
						} else if (colorArr[cardList.get(gameSlot[i + jokerRight - j]).getColor().ordinal()] != 0) {
							System.out.println("조커슬롯 우측, 색 중복으로 초기화. i=" + i);
							return false;
						}
					}
					// 빈 색깔과 수를 조커에 적용
					for (int j = 0; j < 4; j++) {
						if (colorArr[j] == 0) {
							cardList.get(gameSlot[i]).setColor(counterOrdinal(j));
							cardList.get(gameSlot[i]).setNum(compareInt);
							break;
						}
					}
					// 조커 색이 비었으면 초기화
					if (cardList.get(gameSlot[i]).getColor() == null) {
						System.out.println("색 중복으로 인한 초기화");
						return false;
					}

				} // if(조커 조건 설정 : 같은색 조건 3+같은수 조건1)

				System.out.println("해당 조커 검사 완료, 다음 검사를 위한 초기화");
				System.out.println(
						"조커색=" + cardList.get(gameSlot[i]).getColor() + ", 조커수=" + cardList.get(gameSlot[i]).getNum());
				for (int k = 0; k < 4; k++) {
					colorArr[k] = 0;
					System.out.print(colorArr[k]);
				} // for

				compareInt = 0;
				compareColor = null;
				jokerLeft = 0;
				jokerRight = 0;
				System.out.println("jokerLeft:" + jokerLeft + "jokerRight:" + jokerRight);
			} // if(조커등장)
		} // for

		System.out.println("규칙 검사 ================================================");

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.println("i=" + i + ", j=" + j + ", j+(i*20)=" + (j + (i * 20)) + ", ((j+(i*20))+1)="
						+ ((j + (i * 20)) + 1));

				if ((j + (i * 20)) % 20 == 0) {
					System.out.println("줄 변경으로 인한 비교인자 초기화");
					compareColor = null;
					compareInt = 0;
					for (int k = 0; k < 4; k++) {
						colorArr[k] = 0;
						System.out.println(colorArr[k]);
					} // for
					System.out.println("compareInt:" + compareInt + ", compareColor:" + compareColor);

				}

				if (gameSlot[(j + (i * 20))] != -1 && gameSlot[((j + (i * 20)) + 1)] != -1) {
					System.out.println("자신과 우측이 비어있지 않고");

					if (cardList.get(gameSlot[(j + (i * 20))]).getColor() == cardList
							.get(gameSlot[((j + (i * 20)) + 1)]).getColor()) {
						System.out.println("색이같고");

						if (cardList.get(gameSlot[(j + (i * 20))])
								.getNum() == (cardList.get(gameSlot[((j + (i * 20)) + 1)]).getNum()) - 1) {
							System.out.println("숫자가 1씩 크고");

							if (compareColor == null && compareInt == 0) {
								compareColor = cardList.get(gameSlot[(j + (i * 20))]).getColor();
								System.out.println("기준이 아직 없다." + "기준색을 " + compareColor + "로 설정");

							} else if (compareColor != null
									&& compareColor == cardList.get(gameSlot[(j + (i * 20))]).getColor()) {
								System.out.println("기준색이 있고, 그와 같다. 기준색은" + compareColor);
							} else {
								System.out.println("기준색과 맞지 않는다.");
								return false;
							} // if

						} else {
							System.out.println("숫자가 1씩 커지지 않았거나 그 외의 상황. 초기화");
							return false;
						} // if

					} else if (cardList.get(gameSlot[(j + (i * 20))]).getColor() != cardList
							.get(gameSlot[((j + (i * 20)) + 1)]).getColor()) {
						System.out.println("색이 다르고");

						if (cardList.get(gameSlot[(j + (i * 20))])
								.getNum() == (cardList.get(gameSlot[((j + (i * 20)) + 1)]).getNum())) {
							System.out.println("숫자가 같다.");

							if (compareColor == null && compareInt == 0) {
								compareInt = cardList.get(gameSlot[(j + (i * 20))]).getNum();
								colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] = 1;
								System.out.println("기준이 아직 없다." + "기준수는 " + compareInt + "로 설정, "
										+ cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()
										+ "번 색깔 배열 1로 셋팅");
								// 코드 확인용
								for (int z = 0; z < 4; z++) {
									System.out.print(colorArr[z]);
								}
								System.out.println();

							} else if (compareInt != -1
									&& cardList.get(gameSlot[(j + (i * 20))]).getNum() == compareInt) {
								System.out.println("기준수가 있고, 그에 맞다. 기준수는" + compareInt);

								if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 0) {
									colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] = 1;
									System.out.println(
											"색이 중복되지 않는다." + cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()
													+ "번 색깔 배열 1로 셋팅");
									if (colorArr[0] == 1 && colorArr[1] == 1 && colorArr[2] == 1 && colorArr[3] == 1
											&& gameSlot[i + 1] != -1) {
										System.out.println("색상중복으로 인한 초기화");
										return false;
									}

									// 코드 확인용
									for (int z = 0; z < 4; z++) {
										System.out.print(colorArr[z]);
									}
									System.out.println();

								} else if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 1) {
									System.out.println("해당 색(" + cardList.get(gameSlot[(j + (i * 20))]).getColor()
											+ ")이 이미 존재하므로 초기화");

									// 코드 확인용
									for (int z = 0; z < 4; z++) {
										System.out.print(colorArr[z]);
									}
									System.out.println();
									return false;

								} else {
									System.out.println("기준수에 맞지 않아 초기화");
									return false;
								}
							} else {
								System.out.println("숫자가 1씩 증가하지 않아 초기화");
								return false;
							} // if

						} else {
							System.out.println("숫자가 다르거나 그 외의 상황으로 초기화");
							return false;
						} // else
					}
				} else if (gameSlot[(j + (i * 20))] != -1 && gameSlot[((j + (i * 20)) + 1)] == -1) {
					System.out.println("자신은 차있으나 우측은 비었다면");
					if (colorArr[cardList.get(gameSlot[(j + (i * 20))]).getColor().ordinal()] == 1) {
						System.out.println("색깔이 중복이므로 초기화 (숫자의 경우, 비교 불필요)");
						return false;
					} // if
					System.out.println("색깔 중복 확인 통과");
					System.out.println("다음 검사를 위해 비교인자들 초기화");
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

//        1. 두칸모두 차있는 경우 : 카드배치의 시작이거나 중간부이다. 검사하면 된다.
//        2. 두칸모두 빈 경우 : 카드배치가 없는 경우이다. skip
//        3. 앞칸만 차있는 경우 : 카드배치의 마지막부인 상황이다. 색깔 중복만 검사, 통과하면 비교인자 초기화 (숫자는 이전턴에 검사가 이루어짐)
//        4. 뒷칸만 차있는 경우 : 카드배치가 시작되기 바로 직전이다. 하지만 바로 다음턴에 뒷칸과 묶여 검사할 예정이므로 skip
		System.out.println("규칙검사 통과");
		return true;
	}// confirm

	public void printCard() {

		System.out.println("printCard()");

		// 게임슬롯 출력
		for (int i = 0; i < 180; i++) {
			if (gameSlot[i] != -1) {
				gameSlotBtn[i].setIcon(pic[gameSlot[i]]);

			} else if (gameSlot[i] == -1) {
				gameSlotBtn[i].setIcon(pic[107]);

			} // if
		} // for

//		유저슬롯 출력
		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			userSlotBtn[i].setIcon(pic[userList.get(turn).getUserSlotList().get(i).getIndex()]);

		}
	}

	public void backUp() {
		System.out.println("backUp()");

		// 게임슬롯 백업
		for (int i = 0; i < 180; i++) {
			tempSlot[i] = gameSlot[i];
		}

		// 유저슬롯 백업
		dtoBuffer.getUserSlotList().clear();
		for (int i = 0; i < userList.get(turn).getUserSlotList().size(); i++) {
			dtoBuffer.getUserSlotList().add(userList.get(turn).getUserSlotList().get(i));
		}
	}// backUp

	public void initialize() {
		System.out.println("initialize()");

		// 게임슬롯 초기화
		for (int i = 0; i < 180; i++) {
			gameSlot[i] = tempSlot[i];
		}

		// 유저슬롯 초기화
		userList.get(turn).getUserSlotList().clear();
		for (int i = 0; i < dtoBuffer.getUserSlotList().size(); i++) {
			userList.get(turn).getUserSlotList().add(dtoBuffer.getUserSlotList().get(i));
		}

		// 검사도구 변수들 초기화
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
		int newCardIndex = ((int) (Math.random() * 104)); // 0~104사이의 난수
		while (usedCardIndex[newCardIndex] == 1) { // 이미 분배된 카드라면 다시 난수로 받음
			newCardIndex = ((int) (Math.random() * 104));
		} // while
			// 난수로 얻은 인덱스번호의 카드DTO를 전체카드어레이에서 꺼내 유저DTO의 유저카드어레이에 추가하는 구문

		usedCardIndex[newCardIndex] = 1; // 분배된 카드를 표시해둠
		return newCardIndex;
	}// newCard

	public void cardcard() {
		System.out.println("카드 잘 뽑혔나 확인용 메소드");
		// 카드 생성 확인용 코드
		for (int i = 0; i < cardList.size(); i++) {
			System.out.print(cardList.get(i).getColor() + " ");
			System.out.print(cardList.get(i).getNum() + " ");
			System.out.println(cardList.get(i).getIndex());
		} // for
		System.out.println("카드는 총 " + cardList.size() + "장");

	}

}// public class
