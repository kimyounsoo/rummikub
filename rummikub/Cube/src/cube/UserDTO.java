package cube;

import java.io.Serializable;
import java.util.ArrayList;

enum Info {
	JOIN, EXIT, SEND, GAME, USER, START;
}

public class UserDTO implements Serializable {
	private int seq;
	private String id, pw, email, emailad, birth;
	private ArrayList<CardDTO> userSlotList = new ArrayList<CardDTO>();
	private ArrayList<CardDTO> cardList = new ArrayList<CardDTO>();

	private int firstTurn = 0;
	private static int turn = -1;
	private int gender;
	private String message; // 채팅 메세지
	private Info command;
	private int[] gameSlot = new int[180];
	static int[] usedCardIndex;

	public UserDTO(String id, int turn) {
		
		this.id = id;
		this.turn = turn;
	}// 기본생성자

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CardDTO> getUserSlotList() {
		return userSlotList;
	}

//	public void setUserSlotList(ArrayList<CardDTO> userSlotList) {
//		this.userSlotList = userSlotList;
//	}

	public ArrayList<CardDTO> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<CardDTO> cardList) {
		this.cardList = cardList;
	}

	public int[] getGameSlot() {
		return gameSlot;
	}

	public void setGameSlot(int[] gameSlot) {
		this.gameSlot = gameSlot;
	}

	public static int[] getUsedCardIndex() {
		return usedCardIndex;
	}

	public static void setUsedCardIndex(int[] usedCardIndex) {
		UserDTO.usedCardIndex = usedCardIndex;
	}

	public String getId() {
		return id;
	}

	public Info getCommand() {
		return command;
	}

	public void setCommand(Info command) {
		this.command = command;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailad() {
		return emailad;
	}

	public void setEmailad(String emailad) {
		this.emailad = emailad;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public ArrayList<CardDTO> getUserSlotList() {
//		return userSlotList;
//	}

	public void setUserSlotList(ArrayList<CardDTO> userSlotList) {
		this.userSlotList = userSlotList;
	}

	public int getFirstTurn() {
		return firstTurn;
	}

	public void setFirstTurn(int firstTurn) {
		this.firstTurn = firstTurn;
	}

	public int getTurn() {
		
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id;
	}
}

//
//
//// 할당된 카드 표기용 배열 초기화
//public void cardSet() {
//
//	int initialIndex = 0;
//
//	for (int i = 0; i < 105; i++) {
//		usedCardIndex[i] = 0;
//	} // for
//
//	for (int i = 1; i < 14; i++) {
//		cardList.add(new CardDTO(i, Color1.RED, initialIndex));
//		initialIndex++;
//		cardList.add(new CardDTO(i, Color1.RED, initialIndex));
//		initialIndex++;
//	} // for i
//
//	for (int i = 1; i < 14; i++) {
//		cardList.add(new CardDTO(i, Color1.BLUE, initialIndex));
//		initialIndex++;
//		cardList.add(new CardDTO(i, Color1.BLUE, initialIndex));
//		initialIndex++;
//	} // for i
//
//	for (int i = 1; i < 14; i++) {
//		cardList.add(new CardDTO(i, Color1.YELLOW, initialIndex));
//		initialIndex++;
//		cardList.add(new CardDTO(i, Color1.YELLOW, initialIndex));
//		initialIndex++;
//	} // for i
//
//	for (int i = 1; i < 14; i++) {
//		cardList.add(new CardDTO(i, Color1.BLACK, initialIndex));
//		initialIndex++;
//		cardList.add(new CardDTO(i, Color1.BLACK, initialIndex));
//		initialIndex++;
//	} // for i
//
//	cardList.add(new CardDTO(15, Color1.JOKER, initialIndex));
//	initialIndex++;
//
//	for (int j = 0; j < 13; j++) {
//		int newCardIndex = newCard();
//		userSlotList.add(cardList.get(newCardIndex));
//	}
//}
//
//public int newCard() {
//	System.out.println("newCard()");
//	int newCardIndex = ((int) (Math.random() * 104)); // 0~104사이의 난수
//	while (usedCardIndex[newCardIndex] == 1) { // 이미 분배된 카드라면 다시 난수로 받음
//		newCardIndex = ((int) (Math.random() * 104));
//	} // while
//		// 난수로 얻은 인덱스번호의 카드DTO를 전체카드어레이에서 꺼내 유저DTO의 유저카드어레이에 추가하는 구문
//
//	usedCardIndex[newCardIndex] = 1; // 분배된 카드를 표시해둠
//	return newCardIndex;
//}// newCard
