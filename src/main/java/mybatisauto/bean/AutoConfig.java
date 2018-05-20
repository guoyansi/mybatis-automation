package mybatisauto.bean;

import java.util.ArrayList;
import java.util.List;

public class AutoConfig {
	//bean 源文件
	private String sourceBean;//="src/main/java";
	//bean 包
	private String beanPackage;//="bean";//com.gys.bean
	//dao原文件
	private String sourceDao;//="src/main/java";
	//dao 包
	private String daoPackage;//="dao";//com.gys.dao
	//mapper源
	private String sourceMapper;//="src/main/java";
	//mapper 包
	private String mapperPackage;//="mapper";//mapper.gys
	//是否生成springdao
	private Boolean isSpringDao=false;//=false;
	//是否驼峰命名字段
	private Boolean isCamelField=true;
	//需要自动生成代码的表名
	private List<String> tables;//={"gys","td_sys_user"};
	//不需要自动生成代码的表名
	private List<String> exceptTables;
	//xml后缀
	private String xmlMapperSuffix="Mapper";
	
	
	public String getSourceBean() {
		return sourceBean;
	}
	public void setSourceBean(String sourceBean) {
		this.sourceBean = sourceBean;
	}
	public String getBeanPackage() {
		return beanPackage;
	}
	public void setBeanPackage(String beanPackage) {
		this.beanPackage = beanPackage;
	}
	public String getSourceDao() {
		return sourceDao;
	}
	public void setSourceDao(String sourceDao) {
		this.sourceDao = sourceDao;
	}
	public String getDaoPackage() {
		return daoPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public String getSourceMapper() {
		return sourceMapper;
	}
	public void setSourceMapper(String sourceMapper) {
		this.sourceMapper = sourceMapper;
	}
	public String getMapperPackage() {
		return mapperPackage;
	}
	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}
	public Boolean getIsSpringDao() {
		return isSpringDao;
	}
	public void setIsSpringDao(Boolean isSpringDao) {
		this.isSpringDao = isSpringDao;
	}
	public Boolean getIsCamelField() {
		return isCamelField;
	}
	public void setIsCamelField(Boolean isCamelField) {
		this.isCamelField = isCamelField;
	}
	public List<String> getTables() {
		if(this.tables==null){
			return new ArrayList<String>();
		}
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public List<String> getExceptTables() {
		if(this.exceptTables==null){
			return new ArrayList<String>();
		}
		return exceptTables;
	}
	public void setExceptTables(List<String> exceptTables) {
		this.exceptTables = exceptTables;
	}
	public String getXmlMapperSuffix() {
		return xmlMapperSuffix;
	}
	public void setXmlMapperSuffix(String xmlMapperSuffix) {
		this.xmlMapperSuffix = xmlMapperSuffix;
	}
	
	
	
}
