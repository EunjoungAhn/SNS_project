package frame.main;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import db.PostDAO;
import db.PostVO;
import frame.intro.LogInFrame;

public class PostPanel extends JPanel {

	public static HomePanel homePn = new HomePanel();
	public static PostPanel postPn = new PostPanel();
	public static File post_img_path;

	private static final long serialVersionUID = 1L;

	public PostPanel() {
		// 로그인 된 아이디 값 확인.
		String id_user = LogInFrame.userId;

		setLayout(null);
		setSize(500, 600);
		setVisible(true);
		setBounds(50, 100, 492, 650);
		
		// 이미지 파일을 부르기 위한 버튼
		JButton post_mainPic_btn = new JButton("이미지 추가");
		post_mainPic_btn.setBounds(68, 45, 260, 238);
		
		// 버튼 글자 테두리 없애기
		post_mainPic_btn.setFocusPainted(false);
		
		// 버튼 배경색 없애기
		post_mainPic_btn.setContentAreaFilled(false);
		add(post_mainPic_btn);

		post_mainPic_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 파일 설정
				JFileChooser post_img_file = new JFileChooser();
				int img_file = post_img_file.showOpenDialog(post_mainPic_btn);
				if (img_file == JFileChooser.APPROVE_OPTION) {
					
					// 선택한 파일의 경로 반환
					post_img_path = post_img_file.getSelectedFile();
					
					// 선택한 이미지 파일 바로 버튼에 적용 설정
					ImageIcon img = new ImageIcon(post_img_path.getPath());
					Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
					Image picCh = pic.getScaledInstance(265, 255, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
					ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
					
					// 이미지 버튼에 적용
					post_mainPic_btn.setIcon(iconCh);
					add(post_mainPic_btn);
				}
			}
		});
		
		// 해쉬태그 작성 텍스트에리어
		JTextArea post_hash_input = new JTextArea();
		post_hash_input.setBounds(68, 309, 260, 42);
		add(post_hash_input);
		
		// DB전송 입력 버튼
		JButton post_put_btn = new JButton("입력완료");
		post_put_btn.setBounds(123, 403, 142, 52);
		add(post_put_btn);

		post_put_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 게시물 값 가져오기
				PostDAO PostTb = new PostDAO();
				
				// 파일로 선택한 이미지 경로 img 변수에 저장.
				String img = post_img_path.getPath();
				String hash = post_hash_input.getText();
				try {
					PostVO postSet = new PostVO();
					postSet.setId_user(id_user);
					
					// 오늘 날짜 계산 부품
					SimpleDateFormat sdf = new SimpleDateFormat("yy" + "." + "MM" + "." + "dd");
					Calendar c1 = Calendar.getInstance();
					String strToday = sdf.format(c1.getTime());
					postSet.setDate_post(strToday);
					postSet.setHash_post(hash);
					postSet.setImg_post(img);
					postSet.setLike_post(0);
					
					// DAO를 통해 DB로 값 전달 메서드
					PostTb.createTb(postSet);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				HomePanel hp = new HomePanel();
				RootFrame.setPanel(hp);
				
				// 이미지와 텍스트에리어 초기화 시키기
				post_mainPic_btn.setIcon(null);
				post_hash_input.setText("");
			}
		});
		setVisible(true);
	}
}
