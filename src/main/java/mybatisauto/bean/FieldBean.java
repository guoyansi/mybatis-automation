package mybatisauto.bean;

/**
 * @author guoyansi
 *
 */
public class FieldBean {
	//字段名称 sql
	private String sqlName;
	//字段名称 bean
	private String beanName;
	//字段数据类型
	private String sqlType;
	//java数据类型
	private String javaType;
	//是否主键
	private Boolean isKey;
	//是否自增
	private Boolean isAutoAdd;
	//精确度（数字的总位数，包括小数点的位数）
	//private Integer precision;
	//小数位数
	//private Integer dataScale;
	//默认值
	private String defaults;
	
	
	
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
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public Boolean getIsKey() {
		return isKey;
	}
	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}
	public Boolean getIsAutoAdd() {
		return isAutoAdd;
	}
	public void setIsAutoAdd(Boolean isAutoAdd) {
		this.isAutoAdd = isAutoAdd;
	}
	
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	
	
}
