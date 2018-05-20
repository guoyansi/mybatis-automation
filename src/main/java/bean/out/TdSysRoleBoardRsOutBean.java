package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysRoleBoardRsOutBean extends BaseOutBean{
	private String sid;

	private String remark;

	private String createDate;

	private String boardId;

	private String createId;

	private String sortIndex;

	private String roleId;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getBoardId(){return boardId;}
	public void setBoardId(String boardId){this.boardId=boardId;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getSortIndex(){return sortIndex;}
	public void setSortIndex(String sortIndex){this.sortIndex=sortIndex;}

	public String getRoleId(){return roleId;}
	public void setRoleId(String roleId){this.roleId=roleId;}

}