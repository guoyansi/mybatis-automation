package mybatisauto.bean;

public class TableBean {
	private String sqlName;
	private String beanName;
	private String beanInName;
	private String beanOutName;
	private String xmlMapperName;
	private String idaoName;
	private Boolean haveKey=false;
	
	public TableBean() {
	}
	
	
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanInName() {
		return beanInName;
	}
	public void setBeanInName(String beanInName) {
		this.beanInName = beanInName;
	}
	public String getBeanOutName() {
		return beanOutName;
	}
	public void setBeanOutName(String beanOutName) {
		this.beanOutName = beanOutName;
	}


	public String getXmlMapperName() {
		return xmlMapperName;
	}


	public void setXmlMapperName(String xmlMapperName) {
		this.xmlMapperName = xmlMapperName;
	}


	public String getIdaoName() {
		return idaoName;
	}


	public void setIdaoName(String idaoName) {
		this.idaoName = idaoName;
	}
	public Boolean getHaveKey() {
		return haveKey;
	}


	public void setHaveKey(Boolean haveKey) {
		this.haveKey = haveKey;
	}


	
	
	
}
