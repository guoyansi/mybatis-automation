package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysBoardOutBean extends BaseOutBean{
	private String sid;

	private String createDate;

	private String boardName;

	private String onlineTime;

	private String editDate;

	private String boardType;

	private String remark;

	private String boardUrl;

	private String editId;

	private String createId;

	private String boardCode;

	private String boardIcon;

	private String boardDesc;

	private String orgLevelCode;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getBoardName(){return boardName;}
	public void setBoardName(String boardName){this.boardName=boardName;}

	public String getOnlineTime(){return onlineTime;}
	public void setOnlineTime(String onlineTime){this.onlineTime=onlineTime;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

	public String getBoardType(){return boardType;}
	public void setBoardType(String boardType){this.boardType=boardType;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getBoardUrl(){return boardUrl;}
	public void setBoardUrl(String boardUrl){this.boardUrl=boardUrl;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getBoardCode(){return boardCode;}
	public void setBoardCode(String boardCode){this.boardCode=boardCode;}

	public String getBoardIcon(){return boardIcon;}
	public void setBoardIcon(String boardIcon){this.boardIcon=boardIcon;}

	public String getBoardDesc(){return boardDesc;}
	public void setBoardDesc(String boardDesc){this.boardDesc=boardDesc;}

	public String getOrgLevelCode(){return orgLevelCode;}
	public void setOrgLevelCode(String orgLevelCode){this.orgLevelCode=orgLevelCode;}

}