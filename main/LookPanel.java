package frame.main;

import javax.swing.JPanel;
import db.PostDAO;
import db.PostVO;
import db.UserDAO;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.ImageIcon;

public class LookPanel extends JPanel {

	static PostVO postVo = new PostVO();
	PostDAO postDao = new PostDAO();
	static JLabel look_mainPicLabel = new JLabel();
	private static final long serialVersionUID = 1L;

	public LookPanel(String postId) {
		// home패널에서 넘겨 받아온 postId 기준으로 데이터 꺼내기
		PostVO postVo = PostDAO.getPostInfo(postId);
		String userImg = UserDAO.getProfile(postVo.getId_user());
		String name = UserDAO.getNickname(postVo.getId_user());
		String postImg = postVo.getImg_post();
		int like = postVo.getLike_post();
		String hash = postVo.getHash_post();
		String date = postVo.getDate_post();

		setLayout(null);
		setSize(600, 900);
		setBounds(40, 40, 492, 650);
		
		// 다른 사용자의 닉네임 라벨
		JLabel look_NickName_Label = new JLabel("닉네임 : " + name);
		look_NickName_Label.setBounds(240, 32, 102, 34);
		add(look_NickName_Label);
		
		// 프로필 이미지 적용
		ImageIcon img = new ImageIcon(userImg);
		Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
		Image picCh = pic.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
		ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
		JLabel look_headPic_Label = new JLabel("프로필 사진");
		look_headPic_Label.setBounds(70, 10, 90, 90);
		look_headPic_Label.setIcon(iconCh);
		add(look_headPic_Label);
		
		// 게시일 라벨
		JLabel look_day_Label = new JLabel();
		look_day_Label.setBounds(60, 469, 183, 27);
		add(look_day_Label);
		
		// 해쉬태그 라벨
		JLabel look_hash_Label = new JLabel();
		look_hash_Label.setBounds(60, 506, 218, 60);
		add(look_hash_Label);
		
		// 좋아요 라벨
		JLabel look_like_Label = new JLabel();
		look_like_Label.setBounds(260, 473, 80, 15);
		add(look_like_Label);
		
		// 다른 사용자 이미지 라벨
		look_mainPicLabel = new JLabel();
		
		// 버튼 글자 테두리 없애기
		look_headPic_Label.setFocusable(false);
		try {
			postVo = postDao.lookRead(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon look_img = new ImageIcon(postImg);
		Image look_pic = look_img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
		Image look_picCh = look_pic.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
		ImageIcon look_iconCh = new ImageIcon(look_picCh); // Image로 ImageIcon 생성

		look_mainPicLabel.setIcon(look_iconCh);
		
		// 스크롤패널 버튼적용
		look_mainPicLabel.setBounds(60, 80, 377, 368);
		add(look_mainPicLabel);
		
		// 받아온 데이터 적용
		look_day_Label.setText("게시일 : " + date);
		look_hash_Label.setText("해쉬태그 : " + hash);
		look_like_Label.setText("좋아요 : " + String.valueOf(like));

		setVisible(true);
	}
}
