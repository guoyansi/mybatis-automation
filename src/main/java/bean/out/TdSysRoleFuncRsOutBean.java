package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysRoleFuncRsOutBean extends BaseOutBean{
	private String sid;

	private String remark;

	private String createDate;

	private String roleId;

	private String funId;

	private String createId;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getRoleId(){return roleId;}
	public void setRoleId(String roleId){this.roleId=roleId;}

	public String getFunId(){return funId;}
	public void setFunId(String funId){this.funId=funId;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

}