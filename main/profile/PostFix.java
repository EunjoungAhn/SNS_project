package frame.main.profile;

import javax.swing.JPanel;

import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import db.PostDAO;
import db.PostVO;
import frame.main.RootFrame;

public class PostFix extends JPanel {

	private static final long serialVersionUID = 1L;
	private String mFilePath = null;

	public PostFix(String postId) {

		// 패널에 보여줄 데이터가 담긴 VO 객체
		PostVO postVo = PostDAO.getPostInfo(postId);
		String img = postVo.getImg_post();
		String hash = postVo.getHash_post();
		mFilePath = PostDAO.getPostInfo(postId).getImg_post();

		// 패널설정
		setLayout(null);
		setSize(500, 600);
		setBounds(50, 100, 400, 510);

		// 해쉬태그 입력
		JTextArea postFix_hash_input = new JTextArea();
		postFix_hash_input.setBounds(79, 356, 244, 35);
		postFix_hash_input.setText(hash);
		add(postFix_hash_input);

		// 이미지 업로드 위한 파일선택 버튼
		JButton imgUpdateBtn = new JButton();
		imgUpdateBtn.setBounds(70, 56, 250, 270);
		ImageIcon img2 = new ImageIcon(img);
		Image pic2 = img2.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
		Image picCh2 = pic2.getScaledInstance(255, 200, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
		ImageIcon iconCh2 = new ImageIcon(picCh2); // Image로 ImageIcon 생성
		imgUpdateBtn.setIcon(iconCh2);
		
		// 버튼 배경색 없애기
		imgUpdateBtn.setContentAreaFilled(false);
		
		// 이미지 등록 버튼
		imgUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser postFix_img_file = new JFileChooser();
				int img_file = postFix_img_file.showOpenDialog(imgUpdateBtn);
				if (img_file == JFileChooser.APPROVE_OPTION) {
					// 선택한 파일의 경로 반환
					File postFix_img_path = postFix_img_file.getSelectedFile();
					mFilePath = postFix_img_path.getPath();// 수정완료 클릭시 파일 경로 얻기위해 저장
					ImageIcon img = new ImageIcon(postFix_img_path.getPath());
					Image pic = img.getImage(); // ImageIcon을 Image로 변환.(객체를 돌려준다.)
					Image picCh = pic.getScaledInstance(270, 270, java.awt.Image.SCALE_SMOOTH);// 이미지 사이즈 조정
					ImageIcon iconCh = new ImageIcon(picCh); // Image로 ImageIcon 생성
					imgUpdateBtn.setIcon(iconCh);
				}
			}
		});
		add(imgUpdateBtn);

		// 수정완료 버튼
		JButton postFix_btn = new JButton("수정완료");
		postFix_btn.setBounds(97, 420, 191, 44);
		postFix_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String hash = postFix_hash_input.getText();
					PostDAO PostTb = new PostDAO();
					PostVO postSet = new PostVO();
					
					// userDAO의 id_user값 기준으로 임의값 입력
					postSet.setId_post(postId);
					
					// 오늘 날짜 계산 부품
					SimpleDateFormat sdf = new SimpleDateFormat("yy" + "." + "MM" + "." + "dd");
					Calendar c1 = Calendar.getInstance();
					String strToday = sdf.format(c1.getTime());
					postSet.setDate_post(strToday);
					postSet.setImg_post(mFilePath);
					postSet.setHash_post(hash);
					
					// 입력받은 값 DB에 postId 기준으로 수정
					PostTb.PostFixUpdate(postSet);

					// 이전(Detail) 화면으로 이동
					Detail detailPn = new Detail(postSet.getId_post());
					RootFrame.setPanel(detailPn);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		add(postFix_btn);

		setVisible(true);
	}
}
