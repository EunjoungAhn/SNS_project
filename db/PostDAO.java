package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {

	private final static String url = "jdbc:mysql://localhost:3366/instabook";
	private final static String user = "java";
	private final static String password = "1234";
	private static Connection con;

	// home패널에서 해쉬태그 전용 검색 메서드
	public ArrayList<PostVO> searchHash(String hashTag) throws Exception {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		// 1. connector설정
		// 디비 사용을 위해 driver class를 불러온다.
		// swing --- connector설정(driver) ---- mySQL
		Class.forName("com.mysql.jdbc.Driver");
//		System.out.println("1.searchHash connector 연결 성공.!!");

		con = DriverManager.getConnection(url, user, password);
		System.out.println("2.searchHash db 연결 성공.!!");
		// 2.검색 단어를 시작으로 해당되는 모든 단어의 데이터를 가져오는 sql문 작성
		String sql = "select * from post_insta where hash_post like '" + hashTag + "%'";
		PreparedStatement ps = con.prepareStatement(sql);
//		System.out.println("3.searchHash sql 생성 성공.!!");
		//SQL전송
		ResultSet rs = ps.executeQuery();
//		System.out.println("4. sql문 전송 성공.!!");
		while (rs.next()) {
			PostVO bag = new PostVO();// 3.묶음 값을 꺼내주기 위해 객체 생성.
			System.out.println("searchHash 검색결과가 있어요.");
			// 4.쿼리를 통해 DB에서 받은 값을
			String date_post = rs.getString("date_post");
			String hash_post = rs.getString("hash_post");
			String img_post = rs.getString("img_post");
			int like_post = rs.getInt("like_post");
			String userId = rs.getString("id_user");
			String postId = rs.getString("id_post");
			// 5. PostVO 클래스를 통해 가방에 넣기
			bag.setDate_post(date_post);
			bag.setHash_post(hash_post);
			bag.setImg_post(img_post);
			bag.setLike_post(like_post);
			bag.setId_user(userId);
			bag.setId_post(postId);

			list.add(bag);
		}
		// 6.bag은 참조형 변수에 넣은 값, 주소를 전달!
		return list;
	}

	// home패널에서 모든 게시물 데이터 가져오는 메서드
	public ArrayList<PostVO> all() throws Exception {
		// 가방을 컨테이너에 담기위한 변수 설정
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		// 1. connector설정
		Class.forName("com.mysql.jdbc.Driver");
		// 2. db연결
		con = DriverManager.getConnection(url, user, password);
		// 3. sql문을 만든다.
		String sql = "select * from post_insta";
		PreparedStatement ps = con.prepareStatement(sql);
		// 4. sql문은 전송
		// select의 결과는 검색결과가 담긴 테이블(항목+내용)
		// ResultSet: 데이터베이스의 데이터 집합을 나타내는 데이터 표로, 데이터베이스에 쿼리문을 실행하여 생성된다.
		// 복수의 데이터를 가져올때 사용
		ResultSet rs = ps.executeQuery();
//		System.out.println("홈 이미지 SQL문 전송 성공.!!");
		while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
			// 각각의 모든 데이터를 모아 가방에 담고 그 과정을 반복, 항목이 끝날때까지
			PostVO bag = new PostVO();// 가방만들어서,
			String img_post = rs.getString("img_post");
			String id_user = rs.getString("id_user");
			String id_post = rs.getString("id_post");
			bag.setImg_post(img_post);
			bag.setId_user(id_user);
			bag.setId_post(id_post);
			// 컨테이너에 넣는다.
			list.add(bag);
		} // while
		return list;
	}

	// look패널에서 read하기 위한 메서드
	public PostVO lookRead(String postId) throws Exception {
		// 1. connector설정
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		// 2.id기준으로 선택 sql문
		String sql = "select * from post_insta where id_user = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, postId);
		ResultSet rs = ps.executeQuery();
		PostVO bag = new PostVO();// 3.묶음 값을 꺼내주기 위해 객체 생성.
		if (rs.next() == true) {
			System.out.println("lookRead 검색결과가 있어요.");
			// 4.쿼리를 통해 DB에서 받은 값을
			String date_post = rs.getString("date_post");
			String hash_post = rs.getString("hash_post");
			String img_post = rs.getString("img_post");
			int like_post = rs.getInt("like_post");
			// 5. PostVO 클래스를 통해 가방에 넣기
			bag.setDate_post(date_post);
			bag.setHash_post(hash_post);
			bag.setImg_post(img_post);
			bag.setLike_post(like_post);
		} else {
			System.out.println("lookRead 검색완료.");
		}
		// 6.bag은 참조형 변수에 넣은 값, 주소를 전달!
		return bag;
	}

	// look패널 id_post 기준으로 게시물의 모든 데이터 검색
	public static PostVO getPostInfo(String postId) {
		PostVO bag = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("1.searchHash connector 연결 성공.!!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("2.searchHash db 연결 성공.!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement ps = null;
		try {
			String sql = "select * from post_insta where id_post =?";
			ps = con.prepareStatement(sql);
			ps.setString(1, postId);
//			System.out.println("3.searchHash sql 생성 성공.!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs;
		try {
			rs = ps.executeQuery();
//			System.out.println("4. sql문 전송 성공.!!");
			bag = new PostVO();
			if (rs.next() == true) {
				System.out.println("게시물 검색결과가 있어요.");
				bag.setImg_post(rs.getString("img_post"));
				bag.setId_user(rs.getString("id_user"));
				bag.setDate_post(rs.getString("date_post"));
				bag.setHash_post(rs.getString("hash_post"));
				bag.setLike_post(rs.getInt("like_post"));
			} else {
				System.out.println("getPostInfo 검색결과가 없어요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bag;
	}

	// post패널에서 post하기 위한 메서드
	public void createTb(PostVO postSet) throws Exception {
		String id_user = postSet.getId_user();
		String date_post = postSet.getDate_post();
		String hash_post = postSet.getHash_post();
		String img_post = postSet.getImg_post();
		int like_post = postSet.getLike_post();

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		String sql = "insert into post_insta values (null,?,?,?,?,?)";// 어떤 값을 받을지 아직 모른다.
		PreparedStatement ps = con.prepareStatement(sql);
		// 받은 값을 DB에 바로 넣어 준다.
		ps.setString(1, id_user);
		ps.setString(2, date_post);
		ps.setString(3, hash_post);
		ps.setString(4, img_post);
		ps.setInt(5, like_post);
		ps.executeUpdate();
	}

	// PostFix패널에서 수정 완료 버튼 클릭시
	public void PostFixUpdate(PostVO postSet) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		// 사용자가 적은 수정내용을 각각의 항목에 맞추어 DB에 저장
		String sql = "update post_insta set img_post = ?, hash_post = ?, date_post = ? where id_post = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, postSet.getImg_post());
		ps.setString(2, postSet.getHash_post());
		ps.setString(3, postSet.getDate_post());
		ps.setString(4, postSet.getId_post());
		ps.executeUpdate();
	}

	// detail패널에서 삭제 처리 전담 메서드
	public static void detailDelete(String id_post) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		// DB에 있는 데이터를 삭제하기 위한 sql 명령문
		String sql = "delete from post_insta where id_post = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		// UI에서 받은 파라미터값을 받기 위한 설정.
		ps.setString(1, id_post);
		ps.executeUpdate();
	}

	// profile패널에서 사용자 게시물만 검색
	public ArrayList<PostVO> getUserPost(String id_user) throws Exception {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		// 로그인한 사용자가 작성한 모든 게시글 정보 검색 SQL
		String sql = "select * from post_insta where id_user = '" + id_user + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
//		System.out.println("사용자 이미지 SQL문 전송 성공.!!");
		while (rs.next()) {
			PostVO bag = new PostVO();
			String img = rs.getString("img_post");
			String userId = rs.getString("id_user");
			String postId = rs.getString("id_post");
			String date = rs.getString("date_post");
			int like = rs.getInt("like_post");
			String hash = rs.getString("hash_post");
			bag.setImg_post(img);
			bag.setId_user(userId);
			bag.setId_post(postId);
			bag.setDate_post(date);
			bag.setLike_post(like);
			bag.setHash_post(hash);
			list.add(bag);
		} // while
		return list;
	}

}// class