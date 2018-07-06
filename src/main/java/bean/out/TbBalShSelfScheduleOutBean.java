package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TbBalShSelfScheduleOutBean extends BaseOutBean{
	private static final long serialVersionUID = 1L;
	private String balSts;

	private String planSettingOrgCode;

	private String mailType;

	private String planSettingStaffName;

	private String flightNum;

	private String balType;

	private String remark;

	private String balBatchSeq;

	private String startDate;

	private String planSettingStaffCode;

	private String agentName;

	private String planSettingDate;

	private String endDate;

	public String getBalSts(){return balSts;}
	public void setBalSts(String balSts){this.balSts=balSts;}

	public String getPlanSettingOrgCode(){return planSettingOrgCode;}
	public void setPlanSettingOrgCode(String planSettingOrgCode){this.planSettingOrgCode=planSettingOrgCode;}

	public String getMailType(){return mailType;}
	public void setMailType(String mailType){this.mailType=mailType;}

	public String getPlanSettingStaffName(){return planSettingStaffName;}
	public void setPlanSettingStaffName(String planSettingStaffName){this.planSettingStaffName=planSettingStaffName;}

	public String getFlightNum(){return flightNum;}
	public void setFlightNum(String flightNum){this.flightNum=flightNum;}

	public String getBalType(){return balType;}
	public void setBalType(String balType){this.balType=balType;}

	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark=remark;}

	public String getBalBatchSeq(){return balBatchSeq;}
	public void setBalBatchSeq(String balBatchSeq){this.balBatchSeq=balBatchSeq;}

	public String getStartDate(){return startDate;}
	public void setStartDate(String startDate){this.startDate=startDate;}

	public String getPlanSettingStaffCode(){return planSettingStaffCode;}
	public void setPlanSettingStaffCode(String planSettingStaffCode){this.planSettingStaffCode=planSettingStaffCode;}

	public String getAgentName(){return agentName;}
	public void setAgentName(String agentName){this.agentName=agentName;}

	public String getPlanSettingDate(){return planSettingDate;}
	public void setPlanSettingDate(String planSettingDate){this.planSettingDate=planSettingDate;}

	public String getEndDate(){return endDate;}
	public void setEndDate(String endDate){this.endDate=endDate;}


}