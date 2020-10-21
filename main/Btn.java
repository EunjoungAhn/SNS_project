package frame.main;

import javax.swing.JButton;

public class Btn extends JButton{
	/**
	 * defaultSerial
	 */
	private static final long serialVersionUID = 1L;
	String postId;	//게시물 이미지 버튼 생성시 게시물을 식별할 id

	public Btn(String postId) {
		this.postId = postId;
		//버튼이 만들어질때 게시물id를 갖게함
	}

	@Override	//test
	public String toString() {
		return "Btn [postId=" + postId + "]";
	}
	
}
