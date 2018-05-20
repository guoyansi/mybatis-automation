package bean;

import java.io.Serializable;

public class TdSysDeptPeopleBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private String sid;

	private String peopleName;

	private String createTime;

	private String deptId;

	private String createId;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getPeopleName(){return peopleName;}
	public void setPeopleName(String peopleName){this.peopleName=peopleName;}

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getDeptId(){return deptId;}
	public void setDeptId(String deptId){this.deptId=deptId;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

}