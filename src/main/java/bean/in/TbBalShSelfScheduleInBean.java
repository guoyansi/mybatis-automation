package bean.in;

import java.util.List;
import mybatisauto.bean.BaseInBean;

public class TbBalShSelfScheduleInBean extends BaseInBean{
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


	//  balSts====================
	private Boolean sqlBalStsLike=false;

	private Boolean sqlBalStsLeftLike=false;

	private Boolean sqlBalStsRightLike=false;

	private Boolean sqlBalStsD=false;

	private Boolean sqlBalStsDd=false;

	private Boolean sqlBalStsX=false;

	private Boolean sqlBalStsXd=false;

	private List<String> sqlBalStsInList;

	public Boolean getSqlBalStsLike(){return sqlBalStsLike;}
	public void setSqlBalStsLike(Boolean sqlBalStsLike){this.sqlBalStsLike=sqlBalStsLike;}

	public Boolean getSqlBalStsLeftLike(){return sqlBalStsLeftLike;}
	public void setSqlBalStsLeftLike(Boolean sqlBalStsLeftLike){this.sqlBalStsLeftLike=sqlBalStsLeftLike;}

	public Boolean getSqlBalStsRightLike(){return sqlBalStsRightLike;}
	public void setSqlBalStsRightLike(Boolean sqlBalStsRightLike){this.sqlBalStsRightLike=sqlBalStsLeftLike;}

	public Boolean getSqlBalStsD(){return sqlBalStsD;}
	public void setSqlBalStsD(Boolean sqlBalStsD){this.sqlBalStsD=sqlBalStsD;}

	public Boolean getSqlBalStsDd(){return sqlBalStsDd;}
	public void setSqlBalStsDd(Boolean sqlBalStsDd){this.sqlBalStsDd=sqlBalStsDd;}

	public Boolean getSqlBalStsX(){return sqlBalStsX;}
	public void setSqlBalStsX(Boolean sqlBalStsX){this.sqlBalStsX=sqlBalStsX;}

	public Boolean getSqlBalStsXd(){return sqlBalStsXd;}
	public void setSqlBalStsXd(Boolean sqlBalStsXd){this.sqlBalStsXd=sqlBalStsXd;}

	public List<String> getSqlBalStsInList(){return sqlBalStsInList;}
	public void setSqlBalStsInList(List<String> sqlBalStsInList){this.sqlBalStsInList=sqlBalStsInList;}

	//  planSettingOrgCode====================
	private Boolean sqlPlanSettingOrgCodeLike=false;

	private Boolean sqlPlanSettingOrgCodeLeftLike=false;

	private Boolean sqlPlanSettingOrgCodeRightLike=false;

	private Boolean sqlPlanSettingOrgCodeD=false;

	private Boolean sqlPlanSettingOrgCodeDd=false;

	private Boolean sqlPlanSettingOrgCodeX=false;

	private Boolean sqlPlanSettingOrgCodeXd=false;

	private List<String> sqlPlanSettingOrgCodeInList;

	public Boolean getSqlPlanSettingOrgCodeLike(){return sqlPlanSettingOrgCodeLike;}
	public void setSqlPlanSettingOrgCodeLike(Boolean sqlPlanSettingOrgCodeLike){this.sqlPlanSettingOrgCodeLike=sqlPlanSettingOrgCodeLike;}

	public Boolean getSqlPlanSettingOrgCodeLeftLike(){return sqlPlanSettingOrgCodeLeftLike;}
	public void setSqlPlanSettingOrgCodeLeftLike(Boolean sqlPlanSettingOrgCodeLeftLike){this.sqlPlanSettingOrgCodeLeftLike=sqlPlanSettingOrgCodeLeftLike;}

	public Boolean getSqlPlanSettingOrgCodeRightLike(){return sqlPlanSettingOrgCodeRightLike;}
	public void setSqlPlanSettingOrgCodeRightLike(Boolean sqlPlanSettingOrgCodeRightLike){this.sqlPlanSettingOrgCodeRightLike=sqlPlanSettingOrgCodeLeftLike;}

	public Boolean getSqlPlanSettingOrgCodeD(){return sqlPlanSettingOrgCodeD;}
	public void setSqlPlanSettingOrgCodeD(Boolean sqlPlanSettingOrgCodeD){this.sqlPlanSettingOrgCodeD=sqlPlanSettingOrgCodeD;}

	public Boolean getSqlPlanSettingOrgCodeDd(){return sqlPlanSettingOrgCodeDd;}
	public void setSqlPlanSettingOrgCodeDd(Boolean sqlPlanSettingOrgCodeDd){this.sqlPlanSettingOrgCodeDd=sqlPlanSettingOrgCodeDd;}

	public Boolean getSqlPlanSettingOrgCodeX(){return sqlPlanSettingOrgCodeX;}
	public void setSqlPlanSettingOrgCodeX(Boolean sqlPlanSettingOrgCodeX){this.sqlPlanSettingOrgCodeX=sqlPlanSettingOrgCodeX;}

	public Boolean getSqlPlanSettingOrgCodeXd(){return sqlPlanSettingOrgCodeXd;}
	public void setSqlPlanSettingOrgCodeXd(Boolean sqlPlanSettingOrgCodeXd){this.sqlPlanSettingOrgCodeXd=sqlPlanSettingOrgCodeXd;}

	public List<String> getSqlPlanSettingOrgCodeInList(){return sqlPlanSettingOrgCodeInList;}
	public void setSqlPlanSettingOrgCodeInList(List<String> sqlPlanSettingOrgCodeInList){this.sqlPlanSettingOrgCodeInList=sqlPlanSettingOrgCodeInList;}

	//  mailType====================
	private Boolean sqlMailTypeLike=false;

	private Boolean sqlMailTypeLeftLike=false;

	private Boolean sqlMailTypeRightLike=false;

	private Boolean sqlMailTypeD=false;

	private Boolean sqlMailTypeDd=false;

	private Boolean sqlMailTypeX=false;

	private Boolean sqlMailTypeXd=false;

	private List<String> sqlMailTypeInList;

	public Boolean getSqlMailTypeLike(){return sqlMailTypeLike;}
	public void setSqlMailTypeLike(Boolean sqlMailTypeLike){this.sqlMailTypeLike=sqlMailTypeLike;}

	public Boolean getSqlMailTypeLeftLike(){return sqlMailTypeLeftLike;}
	public void setSqlMailTypeLeftLike(Boolean sqlMailTypeLeftLike){this.sqlMailTypeLeftLike=sqlMailTypeLeftLike;}

	public Boolean getSqlMailTypeRightLike(){return sqlMailTypeRightLike;}
	public void setSqlMailTypeRightLike(Boolean sqlMailTypeRightLike){this.sqlMailTypeRightLike=sqlMailTypeLeftLike;}

	public Boolean getSqlMailTypeD(){return sqlMailTypeD;}
	public void setSqlMailTypeD(Boolean sqlMailTypeD){this.sqlMailTypeD=sqlMailTypeD;}

	public Boolean getSqlMailTypeDd(){return sqlMailTypeDd;}
	public void setSqlMailTypeDd(Boolean sqlMailTypeDd){this.sqlMailTypeDd=sqlMailTypeDd;}

	public Boolean getSqlMailTypeX(){return sqlMailTypeX;}
	public void setSqlMailTypeX(Boolean sqlMailTypeX){this.sqlMailTypeX=sqlMailTypeX;}

	public Boolean getSqlMailTypeXd(){return sqlMailTypeXd;}
	public void setSqlMailTypeXd(Boolean sqlMailTypeXd){this.sqlMailTypeXd=sqlMailTypeXd;}

	public List<String> getSqlMailTypeInList(){return sqlMailTypeInList;}
	public void setSqlMailTypeInList(List<String> sqlMailTypeInList){this.sqlMailTypeInList=sqlMailTypeInList;}

	//  planSettingStaffName====================
	private Boolean sqlPlanSettingStaffNameLike=false;

	private Boolean sqlPlanSettingStaffNameLeftLike=false;

	private Boolean sqlPlanSettingStaffNameRightLike=false;

	private Boolean sqlPlanSettingStaffNameD=false;

	private Boolean sqlPlanSettingStaffNameDd=false;

	private Boolean sqlPlanSettingStaffNameX=false;

	private Boolean sqlPlanSettingStaffNameXd=false;

	private List<String> sqlPlanSettingStaffNameInList;

	public Boolean getSqlPlanSettingStaffNameLike(){return sqlPlanSettingStaffNameLike;}
	public void setSqlPlanSettingStaffNameLike(Boolean sqlPlanSettingStaffNameLike){this.sqlPlanSettingStaffNameLike=sqlPlanSettingStaffNameLike;}

	public Boolean getSqlPlanSettingStaffNameLeftLike(){return sqlPlanSettingStaffNameLeftLike;}
	public void setSqlPlanSettingStaffNameLeftLike(Boolean sqlPlanSettingStaffNameLeftLike){this.sqlPlanSettingStaffNameLeftLike=sqlPlanSettingStaffNameLeftLike;}

	public Boolean getSqlPlanSettingStaffNameRightLike(){return sqlPlanSettingStaffNameRightLike;}
	public void setSqlPlanSettingStaffNameRightLike(Boolean sqlPlanSettingStaffNameRightLike){this.sqlPlanSettingStaffNameRightLike=sqlPlanSettingStaffNameLeftLike;}

	public Boolean getSqlPlanSettingStaffNameD(){return sqlPlanSettingStaffNameD;}
	public void setSqlPlanSettingStaffNameD(Boolean sqlPlanSettingStaffNameD){this.sqlPlanSettingStaffNameD=sqlPlanSettingStaffNameD;}

	public Boolean getSqlPlanSettingStaffNameDd(){return sqlPlanSettingStaffNameDd;}
	public void setSqlPlanSettingStaffNameDd(Boolean sqlPlanSettingStaffNameDd){this.sqlPlanSettingStaffNameDd=sqlPlanSettingStaffNameDd;}

	public Boolean getSqlPlanSettingStaffNameX(){return sqlPlanSettingStaffNameX;}
	public void setSqlPlanSettingStaffNameX(Boolean sqlPlanSettingStaffNameX){this.sqlPlanSettingStaffNameX=sqlPlanSettingStaffNameX;}

	public Boolean getSqlPlanSettingStaffNameXd(){return sqlPlanSettingStaffNameXd;}
	public void setSqlPlanSettingStaffNameXd(Boolean sqlPlanSettingStaffNameXd){this.sqlPlanSettingStaffNameXd=sqlPlanSettingStaffNameXd;}

	public List<String> getSqlPlanSettingStaffNameInList(){return sqlPlanSettingStaffNameInList;}
	public void setSqlPlanSettingStaffNameInList(List<String> sqlPlanSettingStaffNameInList){this.sqlPlanSettingStaffNameInList=sqlPlanSettingStaffNameInList;}

	//  flightNum====================
	private Boolean sqlFlightNumLike=false;

	private Boolean sqlFlightNumLeftLike=false;

	private Boolean sqlFlightNumRightLike=false;

	private Boolean sqlFlightNumD=false;

	private Boolean sqlFlightNumDd=false;

	private Boolean sqlFlightNumX=false;

	private Boolean sqlFlightNumXd=false;

	private List<String> sqlFlightNumInList;

	public Boolean getSqlFlightNumLike(){return sqlFlightNumLike;}
	public void setSqlFlightNumLike(Boolean sqlFlightNumLike){this.sqlFlightNumLike=sqlFlightNumLike;}

	public Boolean getSqlFlightNumLeftLike(){return sqlFlightNumLeftLike;}
	public void setSqlFlightNumLeftLike(Boolean sqlFlightNumLeftLike){this.sqlFlightNumLeftLike=sqlFlightNumLeftLike;}

	public Boolean getSqlFlightNumRightLike(){return sqlFlightNumRightLike;}
	public void setSqlFlightNumRightLike(Boolean sqlFlightNumRightLike){this.sqlFlightNumRightLike=sqlFlightNumLeftLike;}

	public Boolean getSqlFlightNumD(){return sqlFlightNumD;}
	public void setSqlFlightNumD(Boolean sqlFlightNumD){this.sqlFlightNumD=sqlFlightNumD;}

	public Boolean getSqlFlightNumDd(){return sqlFlightNumDd;}
	public void setSqlFlightNumDd(Boolean sqlFlightNumDd){this.sqlFlightNumDd=sqlFlightNumDd;}

	public Boolean getSqlFlightNumX(){return sqlFlightNumX;}
	public void setSqlFlightNumX(Boolean sqlFlightNumX){this.sqlFlightNumX=sqlFlightNumX;}

	public Boolean getSqlFlightNumXd(){return sqlFlightNumXd;}
	public void setSqlFlightNumXd(Boolean sqlFlightNumXd){this.sqlFlightNumXd=sqlFlightNumXd;}

	public List<String> getSqlFlightNumInList(){return sqlFlightNumInList;}
	public void setSqlFlightNumInList(List<String> sqlFlightNumInList){this.sqlFlightNumInList=sqlFlightNumInList;}

	//  balType====================
	private Boolean sqlBalTypeLike=false;

	private Boolean sqlBalTypeLeftLike=false;

	private Boolean sqlBalTypeRightLike=false;

	private Boolean sqlBalTypeD=false;

	private Boolean sqlBalTypeDd=false;

	private Boolean sqlBalTypeX=false;

	private Boolean sqlBalTypeXd=false;

	private List<String> sqlBalTypeInList;

	public Boolean getSqlBalTypeLike(){return sqlBalTypeLike;}
	public void setSqlBalTypeLike(Boolean sqlBalTypeLike){this.sqlBalTypeLike=sqlBalTypeLike;}

	public Boolean getSqlBalTypeLeftLike(){return sqlBalTypeLeftLike;}
	public void setSqlBalTypeLeftLike(Boolean sqlBalTypeLeftLike){this.sqlBalTypeLeftLike=sqlBalTypeLeftLike;}

	public Boolean getSqlBalTypeRightLike(){return sqlBalTypeRightLike;}
	public void setSqlBalTypeRightLike(Boolean sqlBalTypeRightLike){this.sqlBalTypeRightLike=sqlBalTypeLeftLike;}

	public Boolean getSqlBalTypeD(){return sqlBalTypeD;}
	public void setSqlBalTypeD(Boolean sqlBalTypeD){this.sqlBalTypeD=sqlBalTypeD;}

	public Boolean getSqlBalTypeDd(){return sqlBalTypeDd;}
	public void setSqlBalTypeDd(Boolean sqlBalTypeDd){this.sqlBalTypeDd=sqlBalTypeDd;}

	public Boolean getSqlBalTypeX(){return sqlBalTypeX;}
	public void setSqlBalTypeX(Boolean sqlBalTypeX){this.sqlBalTypeX=sqlBalTypeX;}

	public Boolean getSqlBalTypeXd(){return sqlBalTypeXd;}
	public void setSqlBalTypeXd(Boolean sqlBalTypeXd){this.sqlBalTypeXd=sqlBalTypeXd;}

	public List<String> getSqlBalTypeInList(){return sqlBalTypeInList;}
	public void setSqlBalTypeInList(List<String> sqlBalTypeInList){this.sqlBalTypeInList=sqlBalTypeInList;}

	//  remark====================
	private Boolean sqlRemarkLike=false;

	private Boolean sqlRemarkLeftLike=false;

	private Boolean sqlRemarkRightLike=false;

	private Boolean sqlRemarkD=false;

	private Boolean sqlRemarkDd=false;

	private Boolean sqlRemarkX=false;

	private Boolean sqlRemarkXd=false;

	private List<String> sqlRemarkInList;

	public Boolean getSqlRemarkLike(){return sqlRemarkLike;}
	public void setSqlRemarkLike(Boolean sqlRemarkLike){this.sqlRemarkLike=sqlRemarkLike;}

	public Boolean getSqlRemarkLeftLike(){return sqlRemarkLeftLike;}
	public void setSqlRemarkLeftLike(Boolean sqlRemarkLeftLike){this.sqlRemarkLeftLike=sqlRemarkLeftLike;}

	public Boolean getSqlRemarkRightLike(){return sqlRemarkRightLike;}
	public void setSqlRemarkRightLike(Boolean sqlRemarkRightLike){this.sqlRemarkRightLike=sqlRemarkLeftLike;}

	public Boolean getSqlRemarkD(){return sqlRemarkD;}
	public void setSqlRemarkD(Boolean sqlRemarkD){this.sqlRemarkD=sqlRemarkD;}

	public Boolean getSqlRemarkDd(){return sqlRemarkDd;}
	public void setSqlRemarkDd(Boolean sqlRemarkDd){this.sqlRemarkDd=sqlRemarkDd;}

	public Boolean getSqlRemarkX(){return sqlRemarkX;}
	public void setSqlRemarkX(Boolean sqlRemarkX){this.sqlRemarkX=sqlRemarkX;}

	public Boolean getSqlRemarkXd(){return sqlRemarkXd;}
	public void setSqlRemarkXd(Boolean sqlRemarkXd){this.sqlRemarkXd=sqlRemarkXd;}

	public List<String> getSqlRemarkInList(){return sqlRemarkInList;}
	public void setSqlRemarkInList(List<String> sqlRemarkInList){this.sqlRemarkInList=sqlRemarkInList;}

	//  balBatchSeq====================
	private Boolean sqlBalBatchSeqLike=false;

	private Boolean sqlBalBatchSeqLeftLike=false;

	private Boolean sqlBalBatchSeqRightLike=false;

	private Boolean sqlBalBatchSeqD=false;

	private Boolean sqlBalBatchSeqDd=false;

	private Boolean sqlBalBatchSeqX=false;

	private Boolean sqlBalBatchSeqXd=false;

	private List<String> sqlBalBatchSeqInList;

	public Boolean getSqlBalBatchSeqLike(){return sqlBalBatchSeqLike;}
	public void setSqlBalBatchSeqLike(Boolean sqlBalBatchSeqLike){this.sqlBalBatchSeqLike=sqlBalBatchSeqLike;}

	public Boolean getSqlBalBatchSeqLeftLike(){return sqlBalBatchSeqLeftLike;}
	public void setSqlBalBatchSeqLeftLike(Boolean sqlBalBatchSeqLeftLike){this.sqlBalBatchSeqLeftLike=sqlBalBatchSeqLeftLike;}

	public Boolean getSqlBalBatchSeqRightLike(){return sqlBalBatchSeqRightLike;}
	public void setSqlBalBatchSeqRightLike(Boolean sqlBalBatchSeqRightLike){this.sqlBalBatchSeqRightLike=sqlBalBatchSeqLeftLike;}

	public Boolean getSqlBalBatchSeqD(){return sqlBalBatchSeqD;}
	public void setSqlBalBatchSeqD(Boolean sqlBalBatchSeqD){this.sqlBalBatchSeqD=sqlBalBatchSeqD;}

	public Boolean getSqlBalBatchSeqDd(){return sqlBalBatchSeqDd;}
	public void setSqlBalBatchSeqDd(Boolean sqlBalBatchSeqDd){this.sqlBalBatchSeqDd=sqlBalBatchSeqDd;}

	public Boolean getSqlBalBatchSeqX(){return sqlBalBatchSeqX;}
	public void setSqlBalBatchSeqX(Boolean sqlBalBatchSeqX){this.sqlBalBatchSeqX=sqlBalBatchSeqX;}

	public Boolean getSqlBalBatchSeqXd(){return sqlBalBatchSeqXd;}
	public void setSqlBalBatchSeqXd(Boolean sqlBalBatchSeqXd){this.sqlBalBatchSeqXd=sqlBalBatchSeqXd;}

	public List<String> getSqlBalBatchSeqInList(){return sqlBalBatchSeqInList;}
	public void setSqlBalBatchSeqInList(List<String> sqlBalBatchSeqInList){this.sqlBalBatchSeqInList=sqlBalBatchSeqInList;}

	//  startDate====================
	private Boolean sqlStartDateLike=false;

	private Boolean sqlStartDateLeftLike=false;

	private Boolean sqlStartDateRightLike=false;

	private Boolean sqlStartDateD=false;

	private Boolean sqlStartDateDd=false;

	private Boolean sqlStartDateX=false;

	private Boolean sqlStartDateXd=false;

	private List<String> sqlStartDateInList;

	public Boolean getSqlStartDateLike(){return sqlStartDateLike;}
	public void setSqlStartDateLike(Boolean sqlStartDateLike){this.sqlStartDateLike=sqlStartDateLike;}

	public Boolean getSqlStartDateLeftLike(){return sqlStartDateLeftLike;}
	public void setSqlStartDateLeftLike(Boolean sqlStartDateLeftLike){this.sqlStartDateLeftLike=sqlStartDateLeftLike;}

	public Boolean getSqlStartDateRightLike(){return sqlStartDateRightLike;}
	public void setSqlStartDateRightLike(Boolean sqlStartDateRightLike){this.sqlStartDateRightLike=sqlStartDateLeftLike;}

	public Boolean getSqlStartDateD(){return sqlStartDateD;}
	public void setSqlStartDateD(Boolean sqlStartDateD){this.sqlStartDateD=sqlStartDateD;}

	public Boolean getSqlStartDateDd(){return sqlStartDateDd;}
	public void setSqlStartDateDd(Boolean sqlStartDateDd){this.sqlStartDateDd=sqlStartDateDd;}

	public Boolean getSqlStartDateX(){return sqlStartDateX;}
	public void setSqlStartDateX(Boolean sqlStartDateX){this.sqlStartDateX=sqlStartDateX;}

	public Boolean getSqlStartDateXd(){return sqlStartDateXd;}
	public void setSqlStartDateXd(Boolean sqlStartDateXd){this.sqlStartDateXd=sqlStartDateXd;}

	public List<String> getSqlStartDateInList(){return sqlStartDateInList;}
	public void setSqlStartDateInList(List<String> sqlStartDateInList){this.sqlStartDateInList=sqlStartDateInList;}

	//  planSettingStaffCode====================
	private Boolean sqlPlanSettingStaffCodeLike=false;

	private Boolean sqlPlanSettingStaffCodeLeftLike=false;

	private Boolean sqlPlanSettingStaffCodeRightLike=false;

	private Boolean sqlPlanSettingStaffCodeD=false;

	private Boolean sqlPlanSettingStaffCodeDd=false;

	private Boolean sqlPlanSettingStaffCodeX=false;

	private Boolean sqlPlanSettingStaffCodeXd=false;

	private List<String> sqlPlanSettingStaffCodeInList;

	public Boolean getSqlPlanSettingStaffCodeLike(){return sqlPlanSettingStaffCodeLike;}
	public void setSqlPlanSettingStaffCodeLike(Boolean sqlPlanSettingStaffCodeLike){this.sqlPlanSettingStaffCodeLike=sqlPlanSettingStaffCodeLike;}

	public Boolean getSqlPlanSettingStaffCodeLeftLike(){return sqlPlanSettingStaffCodeLeftLike;}
	public void setSqlPlanSettingStaffCodeLeftLike(Boolean sqlPlanSettingStaffCodeLeftLike){this.sqlPlanSettingStaffCodeLeftLike=sqlPlanSettingStaffCodeLeftLike;}

	public Boolean getSqlPlanSettingStaffCodeRightLike(){return sqlPlanSettingStaffCodeRightLike;}
	public void setSqlPlanSettingStaffCodeRightLike(Boolean sqlPlanSettingStaffCodeRightLike){this.sqlPlanSettingStaffCodeRightLike=sqlPlanSettingStaffCodeLeftLike;}

	public Boolean getSqlPlanSettingStaffCodeD(){return sqlPlanSettingStaffCodeD;}
	public void setSqlPlanSettingStaffCodeD(Boolean sqlPlanSettingStaffCodeD){this.sqlPlanSettingStaffCodeD=sqlPlanSettingStaffCodeD;}

	public Boolean getSqlPlanSettingStaffCodeDd(){return sqlPlanSettingStaffCodeDd;}
	public void setSqlPlanSettingStaffCodeDd(Boolean sqlPlanSettingStaffCodeDd){this.sqlPlanSettingStaffCodeDd=sqlPlanSettingStaffCodeDd;}

	public Boolean getSqlPlanSettingStaffCodeX(){return sqlPlanSettingStaffCodeX;}
	public void setSqlPlanSettingStaffCodeX(Boolean sqlPlanSettingStaffCodeX){this.sqlPlanSettingStaffCodeX=sqlPlanSettingStaffCodeX;}

	public Boolean getSqlPlanSettingStaffCodeXd(){return sqlPlanSettingStaffCodeXd;}
	public void setSqlPlanSettingStaffCodeXd(Boolean sqlPlanSettingStaffCodeXd){this.sqlPlanSettingStaffCodeXd=sqlPlanSettingStaffCodeXd;}

	public List<String> getSqlPlanSettingStaffCodeInList(){return sqlPlanSettingStaffCodeInList;}
	public void setSqlPlanSettingStaffCodeInList(List<String> sqlPlanSettingStaffCodeInList){this.sqlPlanSettingStaffCodeInList=sqlPlanSettingStaffCodeInList;}

	//  agentName====================
	private Boolean sqlAgentNameLike=false;

	private Boolean sqlAgentNameLeftLike=false;

	private Boolean sqlAgentNameRightLike=false;

	private Boolean sqlAgentNameD=false;

	private Boolean sqlAgentNameDd=false;

	private Boolean sqlAgentNameX=false;

	private Boolean sqlAgentNameXd=false;

	private List<String> sqlAgentNameInList;

	public Boolean getSqlAgentNameLike(){return sqlAgentNameLike;}
	public void setSqlAgentNameLike(Boolean sqlAgentNameLike){this.sqlAgentNameLike=sqlAgentNameLike;}

	public Boolean getSqlAgentNameLeftLike(){return sqlAgentNameLeftLike;}
	public void setSqlAgentNameLeftLike(Boolean sqlAgentNameLeftLike){this.sqlAgentNameLeftLike=sqlAgentNameLeftLike;}

	public Boolean getSqlAgentNameRightLike(){return sqlAgentNameRightLike;}
	public void setSqlAgentNameRightLike(Boolean sqlAgentNameRightLike){this.sqlAgentNameRightLike=sqlAgentNameLeftLike;}

	public Boolean getSqlAgentNameD(){return sqlAgentNameD;}
	public void setSqlAgentNameD(Boolean sqlAgentNameD){this.sqlAgentNameD=sqlAgentNameD;}

	public Boolean getSqlAgentNameDd(){return sqlAgentNameDd;}
	public void setSqlAgentNameDd(Boolean sqlAgentNameDd){this.sqlAgentNameDd=sqlAgentNameDd;}

	public Boolean getSqlAgentNameX(){return sqlAgentNameX;}
	public void setSqlAgentNameX(Boolean sqlAgentNameX){this.sqlAgentNameX=sqlAgentNameX;}

	public Boolean getSqlAgentNameXd(){return sqlAgentNameXd;}
	public void setSqlAgentNameXd(Boolean sqlAgentNameXd){this.sqlAgentNameXd=sqlAgentNameXd;}

	public List<String> getSqlAgentNameInList(){return sqlAgentNameInList;}
	public void setSqlAgentNameInList(List<String> sqlAgentNameInList){this.sqlAgentNameInList=sqlAgentNameInList;}

	//  planSettingDate====================
	private Boolean sqlPlanSettingDateLike=false;

	private Boolean sqlPlanSettingDateLeftLike=false;

	private Boolean sqlPlanSettingDateRightLike=false;

	private Boolean sqlPlanSettingDateD=false;

	private Boolean sqlPlanSettingDateDd=false;

	private Boolean sqlPlanSettingDateX=false;

	private Boolean sqlPlanSettingDateXd=false;

	private List<String> sqlPlanSettingDateInList;

	public Boolean getSqlPlanSettingDateLike(){return sqlPlanSettingDateLike;}
	public void setSqlPlanSettingDateLike(Boolean sqlPlanSettingDateLike){this.sqlPlanSettingDateLike=sqlPlanSettingDateLike;}

	public Boolean getSqlPlanSettingDateLeftLike(){return sqlPlanSettingDateLeftLike;}
	public void setSqlPlanSettingDateLeftLike(Boolean sqlPlanSettingDateLeftLike){this.sqlPlanSettingDateLeftLike=sqlPlanSettingDateLeftLike;}

	public Boolean getSqlPlanSettingDateRightLike(){return sqlPlanSettingDateRightLike;}
	public void setSqlPlanSettingDateRightLike(Boolean sqlPlanSettingDateRightLike){this.sqlPlanSettingDateRightLike=sqlPlanSettingDateLeftLike;}

	public Boolean getSqlPlanSettingDateD(){return sqlPlanSettingDateD;}
	public void setSqlPlanSettingDateD(Boolean sqlPlanSettingDateD){this.sqlPlanSettingDateD=sqlPlanSettingDateD;}

	public Boolean getSqlPlanSettingDateDd(){return sqlPlanSettingDateDd;}
	public void setSqlPlanSettingDateDd(Boolean sqlPlanSettingDateDd){this.sqlPlanSettingDateDd=sqlPlanSettingDateDd;}

	public Boolean getSqlPlanSettingDateX(){return sqlPlanSettingDateX;}
	public void setSqlPlanSettingDateX(Boolean sqlPlanSettingDateX){this.sqlPlanSettingDateX=sqlPlanSettingDateX;}

	public Boolean getSqlPlanSettingDateXd(){return sqlPlanSettingDateXd;}
	public void setSqlPlanSettingDateXd(Boolean sqlPlanSettingDateXd){this.sqlPlanSettingDateXd=sqlPlanSettingDateXd;}

	public List<String> getSqlPlanSettingDateInList(){return sqlPlanSettingDateInList;}
	public void setSqlPlanSettingDateInList(List<String> sqlPlanSettingDateInList){this.sqlPlanSettingDateInList=sqlPlanSettingDateInList;}

	//  endDate====================
	private Boolean sqlEndDateLike=false;

	private Boolean sqlEndDateLeftLike=false;

	private Boolean sqlEndDateRightLike=false;

	private Boolean sqlEndDateD=false;

	private Boolean sqlEndDateDd=false;

	private Boolean sqlEndDateX=false;

	private Boolean sqlEndDateXd=false;

	private List<String> sqlEndDateInList;

	public Boolean getSqlEndDateLike(){return sqlEndDateLike;}
	public void setSqlEndDateLike(Boolean sqlEndDateLike){this.sqlEndDateLike=sqlEndDateLike;}

	public Boolean getSqlEndDateLeftLike(){return sqlEndDateLeftLike;}
	public void setSqlEndDateLeftLike(Boolean sqlEndDateLeftLike){this.sqlEndDateLeftLike=sqlEndDateLeftLike;}

	public Boolean getSqlEndDateRightLike(){return sqlEndDateRightLike;}
	public void setSqlEndDateRightLike(Boolean sqlEndDateRightLike){this.sqlEndDateRightLike=sqlEndDateLeftLike;}

	public Boolean getSqlEndDateD(){return sqlEndDateD;}
	public void setSqlEndDateD(Boolean sqlEndDateD){this.sqlEndDateD=sqlEndDateD;}

	public Boolean getSqlEndDateDd(){return sqlEndDateDd;}
	public void setSqlEndDateDd(Boolean sqlEndDateDd){this.sqlEndDateDd=sqlEndDateDd;}

	public Boolean getSqlEndDateX(){return sqlEndDateX;}
	public void setSqlEndDateX(Boolean sqlEndDateX){this.sqlEndDateX=sqlEndDateX;}

	public Boolean getSqlEndDateXd(){return sqlEndDateXd;}
	public void setSqlEndDateXd(Boolean sqlEndDateXd){this.sqlEndDateXd=sqlEndDateXd;}

	public List<String> getSqlEndDateInList(){return sqlEndDateInList;}
	public void setSqlEndDateInList(List<String> sqlEndDateInList){this.sqlEndDateInList=sqlEndDateInList;}

}