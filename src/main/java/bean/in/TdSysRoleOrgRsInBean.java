package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdSysRoleOrgRsInBean extends BaseInBean{
	private String orgId;

	private String createDate;

	private String roleId;

	private String sid;

	private String createId;

	public String getOrgId(){return orgId;}
	public void setOrgId(String orgId){this.orgId=orgId;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getRoleId(){return roleId;}
	public void setRoleId(String roleId){this.roleId=roleId;}

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

}