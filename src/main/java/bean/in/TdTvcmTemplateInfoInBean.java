package bean.in;

import mybatisauto.bean.BaseInBean;

public class TdTvcmTemplateInfoInBean extends BaseInBean{
	private String createUser;

	private String teplName;

	private String createTime;

	private String dimJson;

	private String teplId;

	public String getCreateUser(){return createUser;}
	public void setCreateUser(String createUser){this.createUser=createUser;}

	public String getTeplName(){return teplName;}
	public void setTeplName(String teplName){this.teplName=teplName;}

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getDimJson(){return dimJson;}
	public void setDimJson(String dimJson){this.dimJson=dimJson;}

	public String getTeplId(){return teplId;}
	public void setTeplId(String teplId){this.teplId=teplId;}

}