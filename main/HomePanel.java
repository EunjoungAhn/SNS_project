package frame.main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import db.PostDAO;
import db.PostVO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class HomePanel extends JPanel {
	PostDAO postDao = new PostDAO();
	ArrayList<PostVO> imgList;
	String userPost;
	JPanel homeImgPanel;
	static String postId;

	private static final long serialVersionUID = 1L;

	public HomePanel() {

		setBounds(50, 60, 492, 650);
		setLayout(null);
		
		// 스크롤패널 생성
		JScrollPane scrollPn = new JScrollPane();
		scrollPn.setBounds(12, 43, 390, 470);
		
		// 수평 스크롤 안쓰게함
		scrollPn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 처음에 안보였다가 사진수가 늘어나면 스크롤 보이게 설정
		scrollPn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPn);
		
		// 스크롤 패널에 담기위한 패널 생성
		JPanel borderLayoutPn = new JPanel();
		
		// 스크롤패널에 패널 넣기
		scrollPn.setViewportView(borderLayoutPn);
		borderLayoutPn.setLayout(new BorderLayout(1, 1));
		
		// 간격 조정
		JPanel columPn = new JPanel();
		borderLayoutPn.add(columPn, BorderLayout.CENTER);
		columPn.setLayout(new GridLayout(3, 0, 20, 20));
		
		// 해쉬태그 검색 입력창
		JTextField search_textField = new JTextField();
		search_textField.setBounds(32, 12, 234, 21);
		add(search_textField);
		search_textField.setColumns(10);
		
		// 해쉬태그 검색 버튼
		JButton search_btn = new JButton("검색");
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hashTag = search_textField.getText();
				
				// 기존 데이터 지우고 검색 결과만 출력설정
				columPn.removeAll();
				columPn.repaint();

				try {
					ArrayList<PostVO> list = postDao.searchHash(hashTag);
					for (int i = 0; i < list.size(); i++) {
						PostVO bag = list.get(i);
						
						// 버튼에 id_post값을 담아서 생성
						Btn btn = new Btn(bag.getId_post());
						
						// btn으로 가져온 이미지 데이터를 호출하여 새로 저장
						String homeImg = bag.getImg_post();
						
						// 이미지 출력과 간격 조정
						columPn.setLayout(new GridLayout(0, 3));
						homeImgPanel = new JPanel();
						homeImgPanel.setPreferredSize(new Dimension(100, 115));
						columPn.add(homeImgPanel);
						
						// 이미지 레이아웃 셋팅
						FlowLayout flow = new FlowLayout();
						homeImgPanel.setLayout(flow);
						homeImgPanel.add(btn);
						
						// 이미지 세팅
						ImageIcon img1 = new ImageIcon(homeImg);
						Image pic = img1.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
						Image picCh = pic.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
						ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
						
						// 버튼 테두리 없애기
						btn.setBorderPainted(false);
						
						// 버튼 배경색 없애기
						btn.setContentAreaFilled(false);
						
						// 다른 사용자의 게시물(이미지) 버튼
						btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								postId = btn.postId;
								
								// 다른 사용자의 게시물 보기
								LookPanel lookPn = new LookPanel(postId);
								RootFrame.setPanel(lookPn);
							}
						});
						btn.setIcon(iconCh);
						
						// 스크롤패널 버튼적용
						homeImgPanel.add(btn);
						
					} // 검색버튼 클릭 이벤트
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		search_btn.setBounds(278, 10, 97, 23);
		add(search_btn);
		
		// 로그인시 메인화면에 다른 사용자의 이미지 불러와서 보여주기
		try {
			postDao = new PostDAO();
			
			// DB에있는 데이터 컨테이너에 담아 가져오기
			imgList = postDao.all();
			
			// 컨테이너에 담아온 가방 하나씩 꺼내기
			for (int i = 0; i < imgList.size(); i++) {
				PostVO bag = imgList.get(i);
				
				// 가방을 id_post를 btn에 적용
				Btn btn = new Btn(bag.getId_post());
				
				// 이미지 꺼내기
				String homeImg = bag.getImg_post();
				
				// 이미지 출력과 간격 조정
				columPn.setLayout(new GridLayout(0, 3));
				homeImgPanel = new JPanel();
				homeImgPanel.setPreferredSize(new Dimension(100, 115));
				columPn.add(homeImgPanel);
				
				// 이미지 레이아웃 셋팅
				FlowLayout flow = new FlowLayout();
				homeImgPanel.setLayout(flow);
				homeImgPanel.add(btn);
				
				// 이미지 적용
				ImageIcon img1 = new ImageIcon(homeImg);
				Image pic = img1.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
				Image picCh = pic.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
				ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
				
				// 버튼 테두리 없애기
				btn.setBorderPainted(false);
				
				// 버튼 배경색 없애기
				btn.setContentAreaFilled(false);
				
				// 다른 사용자의 게시물(이미지) 버튼
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						postId = btn.postId;
						
						// 다른 사용자의 게시물 보기
						LookPanel lookPn = new LookPanel(postId);
						RootFrame.setPanel(lookPn);
					}
				});
				btn.setIcon(iconCh);
				
				// 스크롤패널 버튼적용
				homeImgPanel.add(btn);
				
			} // 모든 게시물을 버튼으로 생성
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setVisible(true);
	}

}
