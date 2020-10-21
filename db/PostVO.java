package db;

public class PostVO {

	private String id_user; //게시물을 작성한 유저의 아이디
	String date_post;		//게시일
	String hash_post;		//게시물의 태그
	private String img_post;//이미지 경로
	private int like_post;	//좋아요 수
	private String id_post;	//게시물 아이디
	
	

	public String getId_post() {
		return id_post;
	}

	public void setId_post(String id_post) {
		this.id_post = id_post;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getDate_post() {
		return date_post;
	}

	public void setDate_post(String date_post) {
		this.date_post = date_post;
	}

	public String getHash_post() {
		return hash_post;
	}

	public void setHash_post(String hash_post) {
		this.hash_post = hash_post;
	}

	public String getImg_post() {
		return img_post;
	}

	public void setImg_post(String img_post) {
		this.img_post = img_post;
	}

	public int getLike_post() {
		return like_post;
	}

	public void setLike_post(int like_post) {
		this.like_post = like_post;
	}

}
