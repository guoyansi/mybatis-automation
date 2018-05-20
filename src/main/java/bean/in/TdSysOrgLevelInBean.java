package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdSysOrgLevelInBean extends BaseInBean{
	private String remark;

	private String createDate;

	private String upOrgLevelId;

	private String editId;

	private String sid;

	private String orgLevelName;

	private String createId;

	private String editDate;

	private String orgLevelCode;

	private String adminScope;

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getUpOrgLevelId(){return upOrgLevelId;}
	public void setUpOrgLevelId(String upOrgLevelId){this.upOrgLevelId=upOrgLevelId;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getOrgLevelName(){return orgLevelName;}
	public void setOrgLevelName(String orgLevelName){this.orgLevelName=orgLevelName;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

	public String getOrgLevelCode(){return orgLevelCode;}
	public void setOrgLevelCode(String orgLevelCode){this.orgLevelCode=orgLevelCode;}

	public String getAdminScope(){return adminScope;}
	public void setAdminScope(String adminScope){this.adminScope=adminScope;}

}