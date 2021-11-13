package persistence.dao;

import java.util.List;
import service.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> getMemberList();
	public MemberDTO getMemberById(String id);
	public int insertMember(MemberDTO member);
	public int updateMember(MemberDTO member);
	public int deleteMember(String id);
	
}
