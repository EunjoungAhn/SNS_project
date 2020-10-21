package frame.main.profile;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import db.PostDAO;
import db.PostVO;
import db.UserDAO;
import frame.intro.LogInFrame;
import frame.main.ProfilePanel;
import frame.main.RootFrame;

public class Detail extends JPanel {

	private static final long serialVersionUID = 1L;

	public Detail(String postId) {
		// 로그인 된 아이디 값 확인.
		String id_user = LogInFrame.userId;
		
		// 로그인 아이디 값으로 UserDAO의 닉네임 및 프로필 이미지 가져오기
		String name = UserDAO.getNickname(id_user);
		String profileImg = UserDAO.getProfile(id_user);
		
		// postVo에 postId기준 정보값 저장
		PostVO postVo = null;
		postVo = PostDAO.getPostInfo(postId);

		setLayout(null);
		setSize(500, 600);
		setBounds(40, 40, 492, 650);
		
		// 프로필 이미지 버튼
		JButton detail_header_pic_btn = new JButton();
		ImageIcon img = new ImageIcon(profileImg);
		Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
		Image picCh = pic.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
		ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
		// 버튼 테두리 없애기
		detail_header_pic_btn.setBorderPainted(false);
		
		// 버튼 글자 테두리 없애기
		detail_header_pic_btn.setFocusPainted(false);
		
		// 버튼 배경색 없애기
		detail_header_pic_btn.setContentAreaFilled(false);
		detail_header_pic_btn.setBounds(70, 0, 90, 90);
		detail_header_pic_btn.setIcon(iconCh);
		add(detail_header_pic_btn);
		
		// 닉네임 라벨
		JLabel detail_nickname_Label = new JLabel();
		detail_nickname_Label.setBounds(230, 20, 101, 30);
		detail_nickname_Label.setText("닉네임: " + name);
		add(detail_nickname_Label);
		
		// 게시물 이미지
		ImageIcon mainimg = new ImageIcon(postVo.getImg_post());
		Image mainpic = mainimg.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
		Image mainpicCh = mainpic.getScaledInstance(255, 255, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
		ImageIcon mainiconCh = new ImageIcon(mainpicCh); // Image로 ImageIcon 생성
		JLabel detail_mainPic_Label = new JLabel();
		detail_mainPic_Label.setBounds(75, 96, 318, 254);
		detail_mainPic_Label.setIcon(mainiconCh);
		add(detail_mainPic_Label);
		
		// 게시일 라벨
		JLabel dateLb = new JLabel("게시일 : " + postVo.getDate_post());
		dateLb.setBounds(49, 361, 122, 15);
		add(dateLb);
		
		// 해쉬태그 라벨
		JLabel hashLb = new JLabel();
		hashLb.setBounds(49, 399, 167, 30);
		hashLb.setText("해쉬태그 : " + postVo.getHash_post());
		add(hashLb);
		
		// 좋아요 라벨
		JLabel likeLb = new JLabel("좋아요 : " + String.valueOf(postVo.getLike_post()));
		likeLb.setBounds(274, 396, 67, 33);
		add(likeLb);
		
		// 삭제버튼
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PostDAO.detailDelete(postId);
					ProfilePanel pp = new ProfilePanel();
					RootFrame.setPanel(pp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		deleteBtn.setBounds(74, 454, 97, 23);
		add(deleteBtn);
		
		// 수정버튼
		JButton detail_Fix_btn = new JButton("수정");
		detail_Fix_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PostFix pf = new PostFix(postId);
				RootFrame.setPanel(pf);
			}
		});
		detail_Fix_btn.setBounds(230, 454, 97, 23);
		add(detail_Fix_btn);

		setVisible(true);
	}
}
