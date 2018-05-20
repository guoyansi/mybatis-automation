package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdTvcmDimentionInfoOutBean extends BaseOutBean{
	private String createTime;

	private String tableName;

	private String colName;

	private String tableDesc;

	private String dimId;

	private String dimName;

	private String createUser;

	private String colCode;

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName=tableName;}

	public String getColName(){return colName;}
	public void setColName(String colName){this.colName=colName;}

	public String getTableDesc(){return tableDesc;}
	public void setTableDesc(String tableDesc){this.tableDesc=tableDesc;}

	public String getDimId(){return dimId;}
	public void setDimId(String dimId){this.dimId=dimId;}

	public String getDimName(){return dimName;}
	public void setDimName(String dimName){this.dimName=dimName;}

	public String getCreateUser(){return createUser;}
	public void setCreateUser(String createUser){this.createUser=createUser;}

	public String getColCode(){return colCode;}
	public void setColCode(String colCode){this.colCode=colCode;}

}