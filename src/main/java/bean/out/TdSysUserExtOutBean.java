package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysUserExtOutBean extends BaseOutBean{
	private String sid;

	private String remark;

	private String userExtId;

	private String createDate;

	private String createId;

	private String userId;

	private String userExtValue;

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getUserExtId(){return userExtId;}
	public void setUserExtId(String userExtId){this.userExtId=userExtId;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getCreateId(){return createId;}
	public void setCreateId(String createId){this.createId=createId;}

	public String getUserId(){return userId;}
	public void setUserId(String userId){this.userId=userId;}

	public String getUserExtValue(){return userExtValue;}
	public void setUserExtValue(String userExtValue){this.userExtValue=userExtValue;}

}