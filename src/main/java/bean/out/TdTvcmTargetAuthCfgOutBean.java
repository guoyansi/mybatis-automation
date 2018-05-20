package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdTvcmTargetAuthCfgOutBean extends BaseOutBean{
	private String createUser;

	private String createDate;

	private String userId;

	private String idxDimId;

	public String getCreateUser(){return createUser;}
	public void setCreateUser(String createUser){this.createUser=createUser;}

	public String getCreateDate(){return createDate;}
	public void setCreateDate(String createDate){this.createDate=createDate;}

	public String getUserId(){return userId;}
	public void setUserId(String userId){this.userId=userId;}

	public String getIdxDimId(){return idxDimId;}
	public void setIdxDimId(String idxDimId){this.idxDimId=idxDimId;}

}