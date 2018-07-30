package mybatisauto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.FieldBean;
import mybatisauto.bean.JdbcBean;
import mybatisauto.bean.TableBean;
import mybatisauto.create.CreateFile;

public class DataSource {
	private JdbcBean jdbc;
	
	public DataSource() {}

	public DataSource(JdbcBean jdbc) {
		this.jdbc = jdbc;
	}

	public void start(AutoConfig config) throws Exception{
		checkConfig(config);
		DbMapper dbMapper=new DbMapper();
		DataBase db=dbMapper.get(jdbc);
		Connection conn=DriverManager.getConnection(jdbc.getUrl(),jdbc.getUser(),jdbc.getPassword());
		Statement stmt=conn.createStatement();
		
		List<TableBean> ts=db.getTableList(stmt,config.getTables(),config.getExceptTables());
		for(TableBean t:ts){
			System.out.println("表====："+t.getSqlName());
			List<FieldBean> fs=db.getFieldList(stmt,t,config.getIsCamelField());
			CreateFile cf=new CreateFile(db);
			cf.createBean(config,t,fs,db);
			cf.createDao(config,t);
			cf.createMapper(config,t,fs);
		}
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
		System.out.println("执行结束======");
	}
		
	private boolean isEmpty(String s){
		return (s==null||s.isEmpty());
	}
	private void checkConfig(AutoConfig c) throws Exception{
		if(isEmpty(c.getSourceBean())){
			throw new Exception("请配置bean的源目录：sourceBean");
		}
		if(isEmpty(c.getBeanPackage())){
			throw new Exception("请配置bean的包路径：beanPackage");
		}
		if(isEmpty(c.getDaoPackage())){
			throw new Exception("请配置dao的包路径：sourceDao");
		}
		if(isEmpty(c.getSourceMapper())){
			throw new Exception("请配置mapper的源目录：sourceMapper");
		}
		if(isEmpty(c.getMapperPackage())){
			throw new Exception("请配置mapper的包路径：mapperPackage");
		}
	}
}
