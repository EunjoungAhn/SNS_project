package frame.intro;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.UserDAO;
import frame.main.RootFrame;
import javax.swing.ImageIcon;

// 로그인화면
public class LogInFrame {

	public static JFrame frame;
	// signUpPanel에서 접근 가능하게 하기 위해 public static으로 선언

	// 여기서 먼저 로그인 패널을 끼워 놓기 때문에
	// 회원가입 버튼 누를 때 새로운 SignUpPanel생성해서 끼우고 거기서(signUp클래스) back버튼 누를때는 그냥
	// visibility만 조정해줌

	public static JPanel logInPanel;
	public static String userId = "";

	

	// 프로그램 시작점
	public static void main(String[] args) {

		showLogInFrame();

	}

	public static void showLogInFrame() {

		// 프레임 사이즈
		final int WIDTH = 600;
		final int HEIGHT = 900;

		frame = new JFrame("InstaBook");
		frame.getContentPane().setBackground(new Color(0, 51, 102));
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().setLayout(null);

		// 로그인패널 객체
		logInPanel = new JPanel();
		logInPanel.setBackground(new Color(0, 51, 102));

		// id라벨
		JLabel idLabel_login = new JLabel("ID");
		idLabel_login.setForeground(Color.WHITE);
		idLabel_login.setFont(new Font("굴림", Font.BOLD, 20));
		idLabel_login.setBounds(100, 399, 45, 42);

		// id 입력
		JTextField IdtextField_logIn;
		IdtextField_logIn = new JTextField();
		IdtextField_logIn.setBounds(157, 402, 268, 42);
		IdtextField_logIn.setColumns(10);

		// 패스워드 라벨
		JLabel pwLabel_login = new JLabel("PW");
		pwLabel_login.setForeground(Color.WHITE);
		pwLabel_login.setFont(new Font("굴림", Font.BOLD, 20));
		pwLabel_login.setBounds(100, 451, 45, 42);

		// 패스워드 입력
		JPasswordField pwField_logIn;
		pwField_logIn = new JPasswordField();
		pwField_logIn.setColumns(10);
		pwField_logIn.setBounds(157, 454, 268, 42);

		// 회원가입 버튼
		JButton signUp_logIn = new JButton("SIGN UP");
		signUp_logIn.setFont(new Font("굴림", Font.BOLD, 15));
		signUp_logIn.setForeground(Color.WHITE);
		signUp_logIn.setBackground(new Color(0, 51, 102));
		signUp_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 회원가입 패널 띄우기
				SignUpPanel signUpPanel = new SignUpPanel();
				JPanel signUp = signUpPanel.showSignUpPanel();
				signUp.setVisible(true);
				frame.getContentPane().add(signUp);
				logInPanel.setVisible(false);
			}
		});
		signUp_logIn.setBounds(221, 506, 113, 42);
		signUp_logIn.setBorderPainted(false);

		// 종료 버튼
		JButton exitBtn_logIn = new JButton("EXIT");
		exitBtn_logIn.setForeground(Color.WHITE);
		exitBtn_logIn.setBackground(new Color(25, 25, 112));
		exitBtn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn_logIn.setBounds(100, 689, 173, 42);
		exitBtn_logIn.setBorderPainted(false);

		// 로그인 버튼
		JButton logInBtn_logIn = new JButton("LOG IN");
		logInBtn_logIn.setForeground(Color.WHITE);
		logInBtn_logIn.setBackground(new Color(25, 25, 112));
		logInBtn_logIn.setBorderPainted(false);
		logInBtn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validity = false;
				String inputId = IdtextField_logIn.getText();
				char[] inputPw = pwField_logIn.getPassword();
				String inputPwStr = "";
				for (int i = 0; i < inputPw.length; i++) {
					inputPwStr += inputPw[i];
				}
				// 아이디 입력 없을때
				if (inputId == null || inputId == "" || inputId.length() == 0) {
					JOptionPane.showMessageDialog(frame, "아이디를 입력하세요");
				} else {
					// 아이디 입력 있을때
					if (inputPwStr.length() == 0 || inputPwStr.equals(null) || inputPwStr == "") {
						// 비밀번호 입력 없을때
						JOptionPane.showMessageDialog(frame, "패스워드를 입력하세요");

					} else {
						// 아이디 비밀번호 다 입력 했을때
						boolean idCheck = UserDAO.idcheck(inputId); // 아이디 존재하는지 먼저 확인
						if (!idCheck) {// 테이블에 아이디가 없음
							JOptionPane.showMessageDialog(frame, "아이디를 확인해 주세요");
						} else {// 테이블에 아이디가 존재
							String dbPw = UserDAO.logIn(inputId); // DB에서 입력한 id에 맞는 비밀번호를 검색

							if (inputPwStr.equals(dbPw)) {
								validity = true;
								userId = inputId;
							} else {
								JOptionPane.showMessageDialog(frame, "비밀번호를 확인해 주세요");
							}

							// 최종결과
							if (validity) {
								RootFrame root = new RootFrame();
								root.setVisible(true);
								frame.setVisible(false);
								
								System.out.println("로그인  성공");
							} else {
								System.out.println("로그인 오류");
							}

						}// 테이블에 아이디가 존재

					}//else 아이디 비밀번호 다 입력 했을때

				}//else 아이디 입력 있을때

			}//actionPerformed
		});
		logInBtn_logIn.setBounds(285, 689, 179, 42);

		// 타이틀 라벨
		JLabel title_logIn = new JLabel("InstaBook");
		title_logIn.setForeground(Color.WHITE);
		title_logIn.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 50));
		title_logIn.setHorizontalAlignment(SwingConstants.CENTER);
		title_logIn.setBounds(171, 10, 237, 82);
		

		// 로그인 패널
		logInPanel.setBounds(12, 10, 560, 841);
		logInPanel.add(idLabel_login);
		logInPanel.add(IdtextField_logIn);
		logInPanel.add(pwLabel_login);
		logInPanel.add(pwField_logIn);
		logInPanel.add(signUp_logIn);
		logInPanel.add(exitBtn_logIn);
		logInPanel.add(logInBtn_logIn);
		logInPanel.add(title_logIn);
		frame.getContentPane().add(logInPanel);
		logInPanel.setLayout(null);
		
		//로그인화면 로고
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/instabookLogo_small.png")));
		logoLabel.setBounds(188, 116, 200, 194);
		logInPanel.add(logoLabel);

		frame.setLocationRelativeTo(null); // 프레임 가운데에서 띄우기
		frame.setResizable(false); // 프레임 사이즈 변경 안되게 고정
		frame.setVisible(true);
	}
}
