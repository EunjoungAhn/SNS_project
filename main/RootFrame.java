package frame.main;

import javax.swing.JFrame;

import javax.swing.JPanel;

import frame.intro.LogInFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class RootFrame extends JFrame {

	private static JPanel basePanel = new JPanel();;	//교체될 패널들이 들어갈 root패널
	private static final long serialVersionUID = 1L;	//defaultSerialVersion

	/**
	 * @return
	 * @wbp.parser.entryPoint
	 */

	public RootFrame() {

		setSize(600, 900);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0, 51, 102));

		basePanel.setBounds(52, 80, 492, 650);
		basePanel.setLayout(null);

		//로그인 완료후 초기에 보여줄 홈 패널
		HomePanel hp = new HomePanel();
		basePanel.add(hp);
		getContentPane().add(basePanel);

		JLabel lblNewLabel = new JLabel("사용자들이 올린 게시물");
		lblNewLabel.setBounds(181, 24, 167, 26);
		basePanel.add(lblNewLabel);
		
		

		//로그아웃버튼
		JButton logOutBtn = new JButton("로그아웃");
		logOutBtn.setBounds(55, 10, 97, 23);
		logOutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInFrame.showLogInFrame();//로그인프레임 호출
				setPanel(null);	//로그아웃후에도 기존 패널이 남아있는것을 방지
				setVisible(false);
			}
		});
		getContentPane().add(logOutBtn);
		
		JLabel titleLb = new JLabel("InstaBook");
		titleLb.setForeground(Color.WHITE);
		titleLb.setFont(new Font("굴림", Font.BOLD, 30));
		titleLb.setBounds(230, 10, 200, 50);
		getContentPane().add(titleLb);

		//프로그램 종료버튼
		JButton exitBtn = new JButton("종료");
		exitBtn.setBounds(450, 10, 97, 23);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(exitBtn);

		// 홈 버튼
		JButton root_home_btn = new JButton("Home");
		root_home_btn.setBounds(51, 774, 157, 59);
		root_home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePanel hp = new HomePanel();
				setPanel(hp);
			}
		});
		getContentPane().add(root_home_btn);

		// 포스트 버튼
		JButton root_post_btn = new JButton("게시물");
		root_post_btn.setBounds(220, 774, 157, 59);
		root_post_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PostPanel postPn = new PostPanel();
				setPanel(postPn);
			}
		});
		getContentPane().add(root_post_btn);

		// 프로필 버튼
		JButton root_profile_btn = new JButton("My Profile");
		root_profile_btn.setBounds(389, 774, 157, 59);
		root_profile_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfilePanel profilePn = new ProfilePanel();
				setPanel(profilePn);
			}
		});
		getContentPane().add(root_profile_btn);

		// 프레임 초기 위치 가운데로
		setLocationRelativeTo(null);

		// 프레임 사이즈 변경 안되게 고정
		setResizable(false);
		setVisible(true);

	}

	//각 패널에서 RootFrame의 패널을 교체할때 사용
	public static void setPanel(JPanel jpanel) {
		if (jpanel == null) {	//null -> 종료시
			basePanel.removeAll();
			basePanel.repaint();
		} else {	//베이스패널에서 모두 지운 후 새로 만들어진 패널을 추가
			basePanel.removeAll();
			basePanel.add(jpanel);
			basePanel.repaint();
		}

	}
}
