package mybatisauto.bean;

import mybatisauto.db.DataBase;

public class JdbcBean {
	/**
	 * 路径:jdbc:mysql://localhost:3306/person
	 * 不要添加？以及后面的参数，工具需要从路径中解析出数据库的名字
	 */
	private String url;
	//用户
	private String user;
	//密码
	private String password;
	//驱动
	private String driver;
	//数据库名称(表的上级)
	//private String dbName;
	
	public JdbcBean() {}
	public JdbcBean(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
}
