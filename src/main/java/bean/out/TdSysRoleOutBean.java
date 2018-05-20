package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysRoleOutBean extends BaseOutBean{
	private String sid;

	private String createDate;

	private String roleStatus;

	private String parentId;

	private String roleType;

	private String roleCode;

	private String editDate;

	private String roleName;

	private String deptId;

	private String remark;

	private String editId;

	private String createId;

	private String isShow;

	private String orgLevelCode;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getRoleStatus(){return roleStatus;}
	public void setRoleStatus(String roleStatus){this.roleStatus=roleStatus;}

	public String getParentId(){return parentId;}
	public void setParentId(String parentId){this.parentId=parentId;}

	public String getRoleType(){return roleType;}
	public void setRoleType(String roleType){this.roleType=roleType;}

	public String getRoleCode(){return roleCode;}
	public void setRoleCode(String roleCode){this.roleCode=roleCode;}

	public String getEditDate(){return editDate;}
	public void setEditDate(String editDate){this.editDate=editDate;}

	public String getRoleName(){return roleName;}
	public void setRoleName(String roleName){this.roleName=roleName;}

	public String getDeptId(){return deptId;}
	public void setDeptId(String deptId){this.deptId=deptId;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getEditId(){return editId;}
	public void setEditId(String editId){this.editId=editId;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getIsShow(){return isShow;}
	public void setIsShow(String isShow){this.isShow=isShow;}

	public String getOrgLevelCode(){return orgLevelCode;}
	public void setOrgLevelCode(String orgLevelCode){this.orgLevelCode=orgLevelCode;}

}