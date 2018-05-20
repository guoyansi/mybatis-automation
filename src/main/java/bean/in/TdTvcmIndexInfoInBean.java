package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdTvcmIndexInfoInBean extends BaseInBean{
	private String idxId;

	private String createTime;

	private String idxDesc;

	private String boardId;

	private String createUser;

	private String orgLvlName;

	private String targetDate;

	private String orgLvlCode;

	private String idxName;

	public String getIdxId(){return idxId;}
	public void setIdxId(String idxId){this.idxId=idxId;}

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getIdxDesc(){return idxDesc;}
	public void setIdxDesc(String idxDesc){this.idxDesc=idxDesc;}

	public String getBoardId(){return boardId;}
	public void setBoardId(String boardId){this.boardId=boardId;}

	public String getCreateUser(){return createUser;}
	public void setCreateUser(String createUser){this.createUser=createUser;}

	public String getOrgLvlName(){return orgLvlName;}
	public void setOrgLvlName(String orgLvlName){this.orgLvlName=orgLvlName;}

	public String getTargetDate(){return targetDate;}
	public void setTargetDate(String targetDate){this.targetDate=targetDate;}

	public String getOrgLvlCode(){return orgLvlCode;}
	public void setOrgLvlCode(String orgLvlCode){this.orgLvlCode=orgLvlCode;}

	public String getIdxName(){return idxName;}
	public void setIdxName(String idxName){this.idxName=idxName;}

}