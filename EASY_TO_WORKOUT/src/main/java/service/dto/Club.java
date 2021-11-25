package service.dto;

public class Club {
	
	private int clubId = 0; // 모임 id (PK)
	private String signUp = null; // 가입 방법 (0-초대가입/1-자유가입)
	private String openCycle = null; // 오픈 주기 (0-정기적/1-일시적)
	private String clubIntro = null; // 모임 소개
	private String clubName = null; // 모임 이름
	private String clubMaster = null; // 모임 개설
	private int countClub = 0; // 회원 수
	
	public int getClubId() { return clubId; }
	public void setClubId(int clubId) { this.clubId = clubId; }
	
	public String getSignUp() { return signUp; }
	public void setSignUp(String signUp) { this.signUp = signUp; }
	
	public String getOpenCycle() { return openCycle; }
	public void setOpenCycle(String openCycle) { this.openCycle = openCycle; }
	
	public String getClubIntro() { return clubIntro; }
	public void setClubIntro(String clubIntro) { this.clubIntro = clubIntro; }
	
	public String getClubName() { return clubName; }
	public void setClubName(String clubName) { this.clubName = clubName; }
	
	public String getClubMaster() { return clubMaster; }
	public void setClubMaster(String clubMaster) { this.clubMaster = clubMaster; }
	
	public int getCountClub() {	return countClub;	}
	public void setCountClub(int countClub) {	this.countClub = countClub;	}
	
}
 