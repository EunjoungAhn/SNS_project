package frame.main.profile;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import db.UserDAO;
import db.UserVO;
import frame.intro.LogInFrame;
import frame.main.ProfilePanel;
import frame.main.RootFrame;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;

public class UserInfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;	//defaultSerialVersion
	private JPasswordField oldPw_updateInfo;			//기존 패스워드 (확인되야 수정가능)
	private JPasswordField newPw_updateInfo;			//변경할 패스워드
	private JTextField name_updateInfo;					//닉네임
	private JTextField birth_updateInfo;				//출생일
	private String mGender;								//성별


	public UserInfoDialog(String id, String name, String gender, String birth) {

		mGender = "";	// 수정 버튼 클릭시 성별 체크박스 상태에 따라 값이 들어올 변수  

		//다이얼로그 설정
		setSize(400, 600);
		setLocationRelativeTo(null); // 중앙에 배치
		getContentPane().setLayout(null);

		// 취소버튼
		JButton cancelBtn = new JButton("cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.out.println("cancelBtn");
			}
		});
		cancelBtn.setBounds(12, 501, 174, 50);
		getContentPane().add(cancelBtn);

		// ID라벨
		JLabel idLabel_updateInfo = new JLabel("ID");
		idLabel_updateInfo.setFont(new Font("굴림", Font.BOLD, 20));
		idLabel_updateInfo.setBounds(57, 55, 31, 24);
		getContentPane().add(idLabel_updateInfo);

		// 기존 비밀번호 라벨
		JLabel oldpwLabel_updateInfo = new JLabel("Existing Password");
		oldpwLabel_updateInfo.setFont(new Font("굴림", Font.BOLD, 20));
		oldpwLabel_updateInfo.setBounds(57, 104, 181, 24);
		getContentPane().add(oldpwLabel_updateInfo);

		// 비밀번호 확인 라벨
		JLabel pwCheckLb = new JLabel("비밀번호 틀림");
		pwCheckLb.setForeground(Color.RED);
		pwCheckLb.setBounds(237, 111, 86, 15);
		getContentPane().add(pwCheckLb);

		// 기존비밀번호 패스워드 입력
		oldPw_updateInfo = new JPasswordField();
		oldPw_updateInfo.setColumns(10);
		oldPw_updateInfo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String id = LogInFrame.userId;
				String pw = String.valueOf(oldPw_updateInfo.getPassword());
				String dbPw = UserDAO.logIn(id);
				if (pw.equals(dbPw)) {
					pwCheckLb.setText("비밀번호 맞음");
				} else {
					pwCheckLb.setText("비밀번호 틀림");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				// 포커스 됐을때
				// 아무것도 하지 않음
			}
		});
		oldPw_updateInfo.setBounds(57, 132, 266, 34);
		getContentPane().add(oldPw_updateInfo);

		// 새 비밀번호 라벨
		JLabel newpwLabel_updateInfo = new JLabel("New Password");
		newpwLabel_updateInfo.setFont(new Font("굴림", Font.BOLD, 20));
		newpwLabel_updateInfo.setBounds(57, 176, 168, 24);
		getContentPane().add(newpwLabel_updateInfo);

		// 새 비밀번호 패스워드 필드
		newPw_updateInfo = new JPasswordField();
		newPw_updateInfo.setColumns(10);
		newPw_updateInfo.setBounds(57, 203, 266, 33);
		getContentPane().add(newPw_updateInfo);

		// 닉네임 라벨
		JLabel nameLb_signUp = new JLabel("Name");
		nameLb_signUp.setFont(new Font("굴림", Font.BOLD, 20));
		nameLb_signUp.setBounds(57, 246, 54, 24);
		getContentPane().add(nameLb_signUp);

		// 닉네임 텍스트 필드
		name_updateInfo = new JTextField();
		name_updateInfo.setText(name);
		name_updateInfo.setColumns(10);
		name_updateInfo.setBounds(57, 274, 266, 34);
		getContentPane().add(name_updateInfo);

		// 성별 라벨
		JLabel genderLabel_updateInfo = new JLabel("Gender");
		genderLabel_updateInfo.setFont(new Font("굴림", Font.BOLD, 20));
		genderLabel_updateInfo.setBounds(57, 318, 103, 24);
		getContentPane().add(genderLabel_updateInfo);

		// 체크박스 '남'
		JCheckBox chckbxMale_updateInfo = new JCheckBox("Male");
		chckbxMale_updateInfo.setFont(new Font("굴림", Font.PLAIN, 15));
		chckbxMale_updateInfo.setBounds(149, 318, 57, 27);
		chckbxMale_updateInfo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 체크박스 '남' 선택시 전역변수 mGender에 m대입
				// 그룹으로 묶이면 무조건 하나 선택 하기때문에 여성은 코딩안해도됨
				if (e.getStateChange() == 1) {
					mGender = "m";
					System.out.println("남");
				} else {
					mGender = "f";
					System.out.println("여");
				}

			}
		});
		getContentPane().add(chckbxMale_updateInfo);

		// 체크박스 '여'
		JCheckBox chckbxFemale_updateInfo = new JCheckBox("Female");
		chckbxFemale_updateInfo.setFont(new Font("굴림", Font.PLAIN, 15));
		chckbxFemale_updateInfo.setBounds(210, 319, 71, 27);
		chckbxFemale_updateInfo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					mGender = "f";
					System.out.println("여");
				} else {
					mGender = "m";
					System.out.println("남");
				}
			}
		});
		getContentPane().add(chckbxFemale_updateInfo);

		// 체크박스 그룹
		ButtonGroup checkBoxGroup = new ButtonGroup();
		checkBoxGroup.add(chckbxMale_updateInfo);
		checkBoxGroup.add(chckbxFemale_updateInfo);

		// 출생일 라벨
		JLabel birthLabel_updateInfo = new JLabel("Date of birth");
		birthLabel_updateInfo.setFont(new Font("굴림", Font.BOLD, 20));
		birthLabel_updateInfo.setBounds(57, 368, 134, 34);
		getContentPane().add(birthLabel_updateInfo);

		// 출생일 텍스트 필드
		birth_updateInfo = new JTextField();
		birth_updateInfo.setFont(new Font("Gulim", Font.PLAIN, 12));
		birth_updateInfo.setText(birth);
		birth_updateInfo.setColumns(8);
		birth_updateInfo.setBounds(57, 403, 266, 34);
		getContentPane().add(birth_updateInfo);

		// 아이디 라벨
		JLabel idLb = new JLabel(id);
		idLb.setForeground(Color.DARK_GRAY);
		idLb.setFont(new Font("굴림", Font.PLAIN, 15));
		idLb.setBounds(82, 48, 236, 43);
		getContentPane().add(idLb);

		// 저장 버튼
		JButton okBtn = new JButton("save");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pwCheck = pwCheckLb.getText();
				if (pwCheck.equals("비밀번호 맞음")) {

					// 새 패스워드에서 입력값 얻어오기
					String pwStr = String.valueOf(newPw_updateInfo.getPassword());

					// 이름, 생일 얻어오기
					String name = name_updateInfo.getText().toString();
					String birth = birth_updateInfo.getText().toString();

					// VO에 담기
					UserVO userDataSet = new UserVO();
					userDataSet.setId_user(id);
					userDataSet.setPw_user(pwStr);
					userDataSet.setNickname_user(name);
					userDataSet.setGender_user(mGender);
					userDataSet.setBirth_user(birth);

					// 회원정보 수정 호출
					boolean result = UserDAO.updateUserInfo(userDataSet);
					if (result) {
						JOptionPane.showMessageDialog(null, "회원정보 수정 성공!");
						setVisible(false);
						ProfilePanel profilePn = new ProfilePanel();
						RootFrame.setPanel(profilePn);

					} else {
						JOptionPane.showMessageDialog(null, "회원정보 수정 실패!");
					}
					System.out.println("saveBtn");

				} else {
					JOptionPane.showMessageDialog(null,"비밀번호를 확인하세요!");
				}
			}//actionPerformed
		});//addActionListener
		okBtn.setBounds(198, 501, 174, 50);
		getContentPane().add(okBtn);

		// 맨위 제목 라벨
		JLabel lblNewLabel = new JLabel("회원정보 수정");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel.setBounds(137, 10, 116, 24);
		getContentPane().add(lblNewLabel);
	}
}
