package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSysLogOperateOutBean extends BaseOutBean{
	private String userSystemos;

	private String sid;

	private String sessionId;

	private String operateType;

	private String httpParams;

	private String ipAddress;

	private String httpType;

	private String funcUrl;

	private String remark;

	private String operateResult;

	private String operateDate;

	private String macAddress;

	private String userBrower;

	private String userId;

	private String funcId;

	public String getUserSystemos(){return userSystemos;}
	public void setUserSystemos(String userSystemos){this.userSystemos=userSystemos;}

	public String getSid(){return sid;}
	public void setSid(String sid){this.sid=sid;}

	public String getSessionId(){return sessionId;}
	public void setSessionId(String sessionId){this.sessionId=sessionId;}

	public String getOperateType(){return operateType;}
	public void setOperateType(String operateType){this.operateType=operateType;}

	public String getHttpParams(){return httpParams;}
	public void setHttpParams(String httpParams){this.httpParams=httpParams;}

	public String getIpAddress(){return ipAddress;}
	public void setIpAddress(String ipAddress){this.ipAddress=ipAddress;}

	public String getHttpType(){return httpType;}
	public void setHttpType(String httpType){this.httpType=httpType;}

	public String getFuncUrl(){return funcUrl;}
	public void setFuncUrl(String funcUrl){this.funcUrl=funcUrl;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getOperateResult(){return operateResult;}
	public void setOperateResult(String operateResult){this.operateResult=operateResult;}

	public String getOperateDate(){return operateDate;}
	public void setOperateDate(String operateDate){this.operateDate=operateDate;}

	public String getMacAddress(){return macAddress;}
	public void setMacAddress(String macAddress){this.macAddress=macAddress;}

	public String getUserBrower(){return userBrower;}
	public void setUserBrower(String userBrower){this.userBrower=userBrower;}

	public String getUserId(){return userId;}
	public void setUserId(String userId){this.userId=userId;}

	public String getFuncId(){return funcId;}
	public void setFuncId(String funcId){this.funcId=funcId;}

}