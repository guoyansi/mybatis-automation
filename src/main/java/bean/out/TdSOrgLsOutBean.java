package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdSOrgLsOutBean extends BaseOutBean{
	private String orgNameLs;

	private String orgCodeLs;

	private String sortIndex;

	private String orgLevel;

	private String superOrgCode;

	public String getOrgNameLs(){return orgNameLs;}
	public void setOrgNameLs(String orgNameLs){this.orgNameLs=orgNameLs;}

	public String getOrgCodeLs(){return orgCodeLs;}
	public void setOrgCodeLs(String orgCodeLs){this.orgCodeLs=orgCodeLs;}

	public String getSortIndex(){return sortIndex;}
	public void setSortIndex(String sortIndex){this.sortIndex=sortIndex;}

	public String getOrgLevel(){return orgLevel;}
	public void setOrgLevel(String orgLevel){this.orgLevel=orgLevel;}

	public String getSuperOrgCode(){return superOrgCode;}
	public void setSuperOrgCode(String superOrgCode){this.superOrgCode=superOrgCode;}

}