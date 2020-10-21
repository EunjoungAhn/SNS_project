package frame.intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.UserDAO;
import db.UserVO;

//회원가입화면
public class SignUpPanel {

	public JPanel signUpPanel = new JPanel(); // 회원가입패널
	private JLabel idLabel_signUp = new JLabel("ID");// id라벨
	private JTextField idTextField_signUp; // id텍스트 필드
	private JLabel pwLabel_signUp = new JLabel("Password"); // 패스워드 라벨
	private JPasswordField pwField_signUp; // 패스워드필드
	private JTextField nameTextField_signUp;// 이름 텍스트필드
	private String mGender; // 성별
	private JPasswordField confPwField_signUp;// 패스워드확인
	private JLabel genderLabel_signUp = new JLabel("Gender");
	private JLabel confPLabel_signUp = new JLabel("Repeat Password");
	private JTextField birthTextField_signUp; // 출생일 텍스트필드
	private JLabel birthLabel_signUp = new JLabel("Date of birth");
	private JCheckBox chckbxMale_signUp = new JCheckBox("Male");
	private JCheckBox chckbxFemale_signUp = new JCheckBox("Female");
	private JButton signUpBtn_signUp = new JButton("SIGN UP");
	private JButton backBtn_signUp = new JButton("BACK");
	private JTextField dummy; // 아이디입력 텍스트 필드에 포커스 안가게 만들어놓은것 (안보임)
	JLabel pwCheckLb = new JLabel(""); // 비밀번호 입력과 확인란 두 입력값 다르면 사용자에게 알려줄 라벨

	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel showSignUpPanel() {
		// 입력란 힌트주기위한 폰트
		Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
		Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
		String idHint = " example@exam.com";
		String birthHint = "ex) 20201125";
		idLabel_signUp.setForeground(new Color(0, 0, 0));

		// id 라벨
		idLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		idLabel_signUp.setBounds(137, 130, 31, 24);

		// id 텍스트 입력
		idTextField_signUp = new JTextField();
		idTextField_signUp.setColumns(10);
		idTextField_signUp.setBounds(137, 158, 266, 34);
		idTextField_signUp.setText(idHint);
		idTextField_signUp.setFont(lostFont);

		// 힌트표시 리스너 오버라이딩
		idTextField_signUp.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { // 포커스 없을때

				// 입력값 없거나 그대로면 힌트 보여줌
				if (idTextField_signUp.getText().equals(idHint) || idTextField_signUp.getText().length() == 0) {
					idTextField_signUp.setText(idHint);
					idTextField_signUp.setFont(lostFont);
					idTextField_signUp.setForeground(Color.GRAY);
				} else {
					// 입력있으면 입력값 그대로 유지하고 폰트 변경
					idTextField_signUp.setText(idTextField_signUp.getText());
					idTextField_signUp.setFont(gainFont);
					idTextField_signUp.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // 포커스 됐을때

				// 힌트가 쓰여있다면 입력 받기위해 공백으로 만듬
				if (idTextField_signUp.getText().equals(idHint)) {
					idTextField_signUp.setText("");
					idTextField_signUp.setFont(gainFont);
				} else {
					// 힌트가 없으면 입력값 유지
					idTextField_signUp.setText(idTextField_signUp.getText());
					idTextField_signUp.setFont(gainFont);
				}

			}
		});
		pwLabel_signUp.setForeground(new Color(0, 0, 0));

		// 회원가입 라벨
		pwLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		pwLabel_signUp.setBounds(137, 202, 126, 24);

		// 패스워드 필드
		pwField_signUp = new JPasswordField();
		pwField_signUp.setColumns(10);
		pwField_signUp.setBounds(137, 230, 266, 34);
		signUpBtn_signUp.setFont(new Font("Dialog", Font.BOLD, 12));
		signUpBtn_signUp.setForeground(new Color(0, 0, 0));

		// 회원가입 버튼
		signUpBtn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTextField_signUp.getText().toString();
				String conPw = String.valueOf(confPwField_signUp.getPassword()); // 비밀번호확인
				String pw = String.valueOf(pwField_signUp.getPassword()); // 비밀번호
				if (conPw.equals(pw)) { // 비밀번호랑 비밀번호 확인 일치하는지 체크

					// 패스워드 String으로 변경
					String name = nameTextField_signUp.getText().toString();
					String gender = mGender;
					String birth = birthTextField_signUp.getText().toString();

					UserVO userDataSet = new UserVO();
					userDataSet.setId_user(id);
					userDataSet.setPw_user(pw);
					userDataSet.setNickname_user(name);
					userDataSet.setGender_user(gender);
					userDataSet.setBirth_user(birth);

					// 회원가입 테이블 쓰기
					boolean result = UserDAO.signUp(userDataSet);
					if (result) {
						JOptionPane.showMessageDialog(signUpPanel, "회원가입 성공!");
						signUpPanel.setVisible(false);
						LogInFrame.logInPanel.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(signUpPanel, "회원가입 실패!");
					}

				}

			}
		});
		signUpBtn_signUp.setBounds(265, 632, 126, 50);
		backBtn_signUp.setFont(new Font("Dialog", Font.BOLD, 12));
		backBtn_signUp.setForeground(new Color(0, 0, 0));

		// 뒤로가기 버튼
		backBtn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpPanel.setVisible(false);
				LogInFrame.logInPanel.setVisible(true);
			}
		});
		backBtn_signUp.setBounds(137, 632, 126, 50);

		// 비밀번호 확인 입력
		confPwField_signUp = new JPasswordField();
		confPwField_signUp.setColumns(10);
		confPwField_signUp.setBounds(137, 301, 266, 33);
		confPwField_signUp.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				String conPw = String.valueOf(confPwField_signUp.getPassword()); // 비밀번호확인
				String pw = String.valueOf(pwField_signUp.getPassword()); // 비밀번호
				if (conPw.equals(pw)) {
					pwCheckLb.setText("일치");
				} else {
					pwCheckLb.setText("불일치");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// 아무것도 안함
			}
		});

		// 성별 라벨
		genderLabel_signUp.setForeground(new Color(0, 0, 0));
		genderLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		genderLabel_signUp.setBounds(137, 431, 103, 24);
		confPLabel_signUp.setForeground(new Color(0, 0, 0));

		// 비밀번호 확인 라벨
		confPLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		confPLabel_signUp.setBounds(137, 274, 168, 24);

		// 생년월일 입력
		birthTextField_signUp = new JTextField();
		birthTextField_signUp.setColumns(8);
		birthTextField_signUp.setBounds(137, 520, 266, 34);
		birthTextField_signUp.setText(birthHint);
		birthTextField_signUp.setFont(lostFont);
		birthTextField_signUp.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { // 포커스 없을때
				// 입력값 없거나 그대로면 힌트 보여줌
				if (birthTextField_signUp.getText().equals(birthHint)
						|| birthTextField_signUp.getText().length() == 0) {
					birthTextField_signUp.setText(birthHint);
					birthTextField_signUp.setFont(lostFont);
					birthTextField_signUp.setForeground(Color.GRAY);
				} else {
					// 입력있으면 입력값 그대로 유지하고 폰트 변경
					birthTextField_signUp.setText(birthTextField_signUp.getText());
					birthTextField_signUp.setFont(gainFont);
					birthTextField_signUp.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // 포커스 됐을때
				// 힌트가 쓰여있다면 입력 받기위해 공백으로 만듬
				if (birthTextField_signUp.getText().equals(birthHint)) {
					birthTextField_signUp.setText("");
					birthTextField_signUp.setFont(gainFont);
				} else {
					// 힌트가 없으면 입력값 유지
					birthTextField_signUp.setText(birthTextField_signUp.getText());
					birthTextField_signUp.setFont(gainFont);
				}
			}
		});
		birthLabel_signUp.setForeground(new Color(0, 0, 0));

		// 생년월일 라벨
		birthLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		birthLabel_signUp.setBounds(137, 481, 134, 34);
		chckbxMale_signUp.setForeground(new Color(0, 0, 0));
		chckbxMale_signUp.setBackground(new Color(0, 51, 102));

		// 체크박스 남
		chckbxMale_signUp.setFont(new Font("Dialog", Font.BOLD, 12));
		chckbxMale_signUp.setBounds(229, 432, 57, 27);
		chckbxMale_signUp.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 체크박스 '남' 선택시 전역변수 mGender에 m대입
				// 그룹으로 묶이면 무조건 하나 선택 하기때문에 여성은 코딩안해도됨
				if (e.getStateChange() == 1) {
					mGender = "m";
//					System.out.println("남");
				} else {
					mGender = "f";
//					System.out.println("여");
				}

			}
		});
		chckbxFemale_signUp.setForeground(new Color(0, 0, 0));
		chckbxFemale_signUp.setBackground(new Color(0, 51, 102));

		// 체크박스 여
		chckbxFemale_signUp.setFont(new Font("Dialog", Font.BOLD, 12));
		chckbxFemale_signUp.setBounds(290, 432, 71, 27);

		// 체크박스 그룹
		ButtonGroup checkBoxGroup = new ButtonGroup();
		checkBoxGroup.add(chckbxMale_signUp);
		checkBoxGroup.add(chckbxFemale_signUp);

		// 타이틀 라벨
		JLabel titleLabel_signUp = new JLabel("SIGN UP");
		titleLabel_signUp.setForeground(new Color(0, 0, 0));
		titleLabel_signUp.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		titleLabel_signUp.setBounds(167, 22, 194, 67);
		signUpPanel.setBackground(new Color(0, 51, 102));

		signUpPanel.setBounds(12, 10, 560, 841);
		signUpPanel.setLayout(null);
		signUpPanel.add(idLabel_signUp);
		signUpPanel.add(idTextField_signUp);
		signUpPanel.add(pwLabel_signUp);
		signUpPanel.add(pwField_signUp);
		signUpPanel.add(signUpBtn_signUp);
		signUpPanel.add(backBtn_signUp);
		signUpPanel.add(confPwField_signUp);
		signUpPanel.add(genderLabel_signUp);
		signUpPanel.add(confPLabel_signUp);
		signUpPanel.add(birthTextField_signUp);
		signUpPanel.add(birthLabel_signUp);
		signUpPanel.add(chckbxMale_signUp);
		signUpPanel.add(chckbxFemale_signUp);
		signUpPanel.add(titleLabel_signUp);

		JLabel nameLb_signUp = new JLabel("Name");
		nameLb_signUp.setForeground(new Color(0, 0, 0));
		nameLb_signUp.setFont(new Font("Dialog", Font.BOLD, 18));
		nameLb_signUp.setBounds(137, 344, 54, 24);
		signUpPanel.add(nameLb_signUp);

		nameTextField_signUp = new JTextField();
		nameTextField_signUp.setColumns(10);
		nameTextField_signUp.setBounds(137, 372, 266, 34);
		signUpPanel.add(nameTextField_signUp);

		// 처음에 포커스 들어갈 텍스트 필드(ID로 포커스 안되게하는 용도)
		dummy = new JTextField();
		dummy.setBounds(0, 0, -1, 3);
		signUpPanel.add(dummy);
		dummy.setColumns(10);

		// 비밀번호 체크 라벨
		pwCheckLb.setForeground(Color.RED);
		pwCheckLb.setBounds(304, 280, 99, 15);
		signUpPanel.add(pwCheckLb);

		return signUpPanel;

	}
}
