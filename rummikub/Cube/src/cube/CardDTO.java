package cube;

enum Color1 {RED, BLUE, YELLOW, BLACK, JOKER}

public class CardDTO implements Comparable<CardDTO> {
	private Color1 color;
	private int num;
	private int index;
	private String pic;


	
	
	public CardDTO(int num, Color1 color, int index) {
		this.num = num;
		this.color = color;
		this.index = index;
	}

	public CardDTO(int index) {
		this.index = index;
	}


	public String getPic() {
		return pic;
	}



	public void setPic(String pic) {
		this.pic = pic;
	}



	public Color1 getColor() {
		return color;
	}



	public void setColor(Color1 color) {
		this.color = color;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	@Override
	public int compareTo(CardDTO s) {
		if (this.index < s.getIndex()) {
            return -1;
        } else if (this.index > s.getIndex()) {
            return 1;
        }
        return 0;

	}



	
	
	
	

	
}
