package frame.main;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import db.PostDAO;
import db.PostVO;
import db.UserDAO;
import frame.intro.LogInFrame;
import frame.main.profile.Detail;
import frame.main.profile.UserInfoDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * @wbp.parser.entryPoint
	 */

	public ProfilePanel() {

		String id_user = LogInFrame.userId;

		// frame에서 패널 위치 설정 및 레이아웃 크기
		setLayout(null);
		setSize(500, 600);
		setBounds(0, 0, 492, 650);

		String nickName = UserDAO.getNickname(id_user);
		String gender = UserDAO.getGender(id_user);
		String birth = UserDAO.getBirth(id_user);
		String name = UserDAO.getNickname(id_user);

		JLabel IdLb = new JLabel("ID : " + id_user);
		IdLb.setBounds(244, 24, 236, 28);
		add(IdLb);

		JLabel nameLb = new JLabel("NAME : " + name);
		nameLb.setBounds(244, 50, 236, 28);
		add(nameLb);

		JLabel genderLb = new JLabel("GENDER : " + gender);
		genderLb.setBounds(244, 76, 236, 28);
		add(genderLb);

		JButton userInfoBtn = new JButton();
		userInfoBtn.setBackground(Color.WHITE);
		userInfoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원정보 수정창
				UserInfoDialog dlg = new UserInfoDialog(id_user, nickName, gender, birth);
				dlg.setVisible(true);
			}
		});
		userInfoBtn.setBounds(247, 130, 180, 35);
		userInfoBtn.setText("회원정보 수정");
		add(userInfoBtn);

		// 프로필 이미지 버튼
		JButton profileBtn_profilPn = new JButton();
		profileBtn_profilPn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser profile = new JFileChooser();
				int dialogOption = profile.showOpenDialog(profileBtn_profilPn);
				if (dialogOption == JFileChooser.APPROVE_OPTION) {
					File imgFile = profile.getSelectedFile();
					System.out.println(imgFile);
					String filePath = imgFile.getPath();
					System.out.println("DB저장전 URL : " + filePath);
					// 선택한 파일 경로 디비 저장
					System.out.println("로그인된 아이디 : " + id_user);
					boolean result = UserDAO.updateProfile(id_user, filePath);

					if (result) {
						// 방금 저장한 이미지 경로 읽고 적용
						ImageIcon img = new ImageIcon(filePath);
						Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
						Image picCh = pic.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
						ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
						profileBtn_profilPn.setIcon(iconCh);

					} else {
						JOptionPane.showMessageDialog(null, "프로필 이미지 변경실패 ");
					}
				}
			}
		});
		profileBtn_profilPn.setBounds(12, 26, 223, 153);
		add(profileBtn_profilPn);

		String imgUrl = null;
		imgUrl = UserDAO.getProfile(id_user);
		System.out.println("현재 설정된 프로필 이미지의 경로 : " + imgUrl);
		ImageIcon iconCh = null;
		if (imgUrl == null) {	//프로필 이미지 설정 안했으면 디폴드 이미지
			ImageIcon img = new ImageIcon("D:\\yangkj\\workspace\\Instabook\\src\\images\\myProfileImg.png");
			Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
			Image picCh = pic.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
			iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
		} else {
			ImageIcon img = new ImageIcon(imgUrl);
			Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
			Image picCh = pic.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
			iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
		}

		profileBtn_profilPn.setBorderPainted(false);// 버튼 테두리 없애기
		profileBtn_profilPn.setFocusPainted(false);// 버튼 글자 테두리 없애기
		profileBtn_profilPn.setContentAreaFilled(false);// 버튼 배경색 없애기
		profileBtn_profilPn.setIcon(iconCh);// 아이콘설정

		JScrollPane 스크롤패널 = new JScrollPane();
		스크롤패널.setBounds(12, 250, 468, 366);
		// 수평 스크롤 안쓰게함
		스크롤패널.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// 처음에 안보였다가 사진수가 늘어나면 스크롤 보이게 설정
		스크롤패널.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(스크롤패널);
		// 스크롤 패널에 담기위한 패널 생성
		JPanel 보더레이아웃패널 = new JPanel();
		// 스크롤패널에 패널 넣기
		스크롤패널.setViewportView(보더레이아웃패널);
		보더레이아웃패널.setLayout(new BorderLayout(1, 1));

		JLabel myPostLb = new JLabel("내가 작성한 게시물");
		myPostLb.setBounds(12, 220, 114, 28);
		add(myPostLb);

		JPanel 컬럼패널 = new JPanel();
		보더레이아웃패널.add(컬럼패널, BorderLayout.CENTER);
		컬럼패널.setLayout(new GridLayout(3, 0, 20, 20));// 3, 0, 10, 10

		// 사용자의 게시글 이미지 불러와서 보여주기
		try {
			PostDAO postDao = new PostDAO();
			ArrayList<PostVO> imgList = postDao.getUserPost(id_user);

			System.out.println("-------------------------Profile 이미지 개수:" + imgList.size() + "개");
			PostVO vo = new PostVO();
			for (int i = 0; i < imgList.size(); i++) {// 1번째 for
				vo = imgList.get(i);
				Btn btn = new Btn(vo.getId_post());
				String homeImg = vo.getImg_post();

				컬럼패널.setLayout(new GridLayout(0, 3));// 9, 0, 15, 15
				JPanel homeImgPanel = new JPanel();
				homeImgPanel.setPreferredSize(new Dimension(100, 115));
				컬럼패널.add(homeImgPanel);
				FlowLayout flow = new FlowLayout();
				homeImgPanel.setLayout(flow);

				homeImgPanel.add(btn);
				ImageIcon img1 = new ImageIcon(homeImg);
				Image pic1 = img1.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
				Image picCh1 = pic1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
				ImageIcon iconCh1 = new ImageIcon(picCh1); // Image로 ImageIcon 생성

				// 버튼 테두리 없애기
				btn.setBorderPainted(false);

				// 버튼 배경색 없애기
				btn.setContentAreaFilled(false);

				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String postId = btn.postId;
						System.out.println("----" + postId);

						Detail detail = new Detail(postId);
						System.out.println("detail 넘어갈때 postId : " + postId);
						RootFrame.setPanel(detail);

					}
				});
				btn.setIcon(iconCh1);
				// 스크롤패널 버튼적용
				homeImgPanel.add(btn);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setVisible(true);

	}
}
