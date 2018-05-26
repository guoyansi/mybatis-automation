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
	private Boolean isAutoAdd=false;
	//模糊查询名
	private String sqlLikeName;
	//左模糊
	private String sqlLeftLikeName;
	//右模糊
	private String sqlRightLikeName;
	//大于
	private String sqlDName;
	//大于等于
	private String sqlDdName;
	//小于
	private String sqlXName;
	//小于等于
	private String sqlXdName;
	//in查询
	//private String sqlInName;
	//in查询时，list字段名称
	private String sqlInListName;
	
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
	public String getSqlLikeName() {
		return sqlLikeName;
	}
	public void setSqlLikeName(String sqlLikeName) {
		this.sqlLikeName = sqlLikeName;
	}
	public String getSqlLeftLikeName() {
		return sqlLeftLikeName;
	}
	public void setSqlLeftLikeName(String sqlLeftLikeName) {
		this.sqlLeftLikeName = sqlLeftLikeName;
	}
	public String getSqlRightLikeName() {
		return sqlRightLikeName;
	}
	public void setSqlRightLikeName(String sqlRightLikeName) {
		this.sqlRightLikeName = sqlRightLikeName;
	}
	public String getSqlDName() {
		return sqlDName;
	}
	public void setSqlDName(String sqlDName) {
		this.sqlDName = sqlDName;
	}
	public String getSqlDdName() {
		return sqlDdName;
	}
	public void setSqlDdName(String sqlDdName) {
		this.sqlDdName = sqlDdName;
	}
	public String getSqlXName() {
		return sqlXName;
	}
	public void setSqlXName(String sqlXName) {
		this.sqlXName = sqlXName;
	}
	public String getSqlXdName() {
		return sqlXdName;
	}
	public void setSqlXdName(String sqlXdName) {
		this.sqlXdName = sqlXdName;
	}
	/*public String getSqlInName() {
		return sqlInName;
	}
	public void setSqlInName(String sqlInName) {
		this.sqlInName = sqlInName;
	}*/
	public String getSqlInListName() {
		return sqlInListName;
	}
	public void setSqlInListName(String sqlInListName) {
		this.sqlInListName = sqlInListName;
	}
	
	
}
