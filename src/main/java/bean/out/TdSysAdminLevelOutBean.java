package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysAdminLevelOutBean extends BaseOutBean{
	private String remark;

	private String createDate;

	private String editId;

	private String sid;

	private String createId;

	private String adminRoleId;

	private String roleId;

	private String editDate;

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getAdminRoleId(){return adminRoleId;}
	public void setAdminRoleId(String adminRoleId){this.adminRoleId=adminRoleId;}

	public String getRoleId(){return roleId;}
	public void setRoleId(String roleId){this.roleId=roleId;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

}