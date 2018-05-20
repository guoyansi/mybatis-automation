package bean;

import java.io.Serializable;

public class TdTvcmTeplDimMemberRsBean implements Serializable{

	private static final long serialVersionUID = -1L;

	private String memberName;

	private String memberValues;

	private String teplId;

	private String dimId;

	public String getMemberName(){return memberName;}
	public void setMemberName(String memberName){this.memberName=memberName;}

	public String getMemberValues(){return memberValues;}
	public void setMemberValues(String memberValues){this.memberValues=memberValues;}

	public String getTeplId(){return teplId;}
	public void setTeplId(String teplId){this.teplId=teplId;}

	public String getDimId(){return dimId;}
	public void setDimId(String dimId){this.dimId=dimId;}

}