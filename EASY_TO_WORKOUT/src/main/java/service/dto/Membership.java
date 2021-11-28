package service.dto;

import java.sql.Timestamp;

public class Membership {
	private int clubId = 0;
	private String memberId = null;
	private Timestamp subDate = null;
	
	public int getClubId() {	return clubId;	}
	public void setClubId(int clubId) {	this.clubId = clubId;	}
	
	public String getMemberId() {	return memberId;	}
	public void setMemberId(String memberId) {	this.memberId = memberId;	}
	
	public Timestamp getSubDate() {	return subDate;	}
	public void setSubDate(Timestamp subDate) {	this.subDate = subDate;	}
	
}
