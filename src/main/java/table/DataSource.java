package table;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import table.bean.FieldBean;
import table.dao.ITableDao;

/**
 * 获取表数据
 * @author guoyansi
 *
 */
public class DataSource {
	private SqlSessionFactory sqlSessionFactory;
	private Reader reader;
	private SqlSession session;
	
	public DataSource(String jdbc) throws IOException {
		this.reader = Resources.getResourceAsReader(jdbc);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}
	public void openSession(){
		session=sqlSessionFactory.openSession();
	}
	public void commit(){
		session.commit();
	}
	public void closeSession(){
		session.close();
	}
	public <T> T getDao(Class<T> cs){
		return session.getMapper(cs);
	}
	
	public void start(String sourceBean,String beanPackage,String sourceDao,String daoPackage,String sourceMapper,String mapperPackage,boolean isSpringDao,String[] tables,String[] exceptTables,String dbName,boolean isCamelField) throws Exception{
		if((!"mysql".equals(dbName))&&(!"oracle".equals(dbName))){
			throw new Exception("无效的dbName");
		}
		openSession();
		try {
			ITableDao tableDao=this.getDao(ITableDao.class);
			//表名list
			List<String> list=null;
			if("mysql".equals(dbName)){
				list=tableDao.getMySqlTableList();
			}else if("oracle".equals(dbName)){
				list=tableDao.getOracleTableList();
			}
			for(String tableName:list){
				String myTableName=null;
				boolean isExcepted=false;
				if(tables!=null&&tables.length!=0){
					for(String t:tables){
						if(tableName.equals(t)){
							myTableName=t;
							break;
						}
					}
				}
				if(exceptTables!=null&&exceptTables.length!=0){//排除table
					for(String t:exceptTables){
						if(myTableName.equals(t)){
							isExcepted=true;
							break;
						}
					}
					if(isExcepted){
						continue;
					}
				}/*else{
					myTableName=tableName;
				}*/
				if(myTableName==null){
					continue;
				}
				System.out.println("tableName:"+myTableName);
				//字段list
				List<FieldBean> fileds=null;
				if("mysql".equals(dbName)){
					fileds=tableDao.getMySqlFiledList(myTableName);
				}else if("oracle".equals(dbName)){
					fileds=tableDao.getOracleFiledList(myTableName);
				}
				create(sourceBean,beanPackage,sourceDao,daoPackage,sourceMapper,mapperPackage,tableName,fileds,isSpringDao,dbName,isCamelField);
				System.out.println("执行结束:"+myTableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession();
			System.out.println("执行结束");
		}
	}
	
	private void create(String sourceBean,String beanPackage,String sourceDao,String daoPackage,String sourceMapper,String mapperPackage,String tableName,List<FieldBean> fileds,boolean isSpringDao,String dbName,boolean isCamelField) throws Exception{
		CreateFile cf=new CreateFile();
		//inbean
		cf.createJavaBean(sourceBean, beanPackage, tableName, fileds,"in",isCamelField,dbName);
		//outben
		cf.createJavaBean(sourceBean, beanPackage, tableName, fileds,"out",isCamelField,dbName);
		//bean
		cf.createJavaBean(sourceBean, beanPackage, tableName, fileds,null,isCamelField,dbName);
		cf.createJavaDao(sourceDao, daoPackage, beanPackage, tableName, fileds,isSpringDao);
		cf.createMapper(sourceMapper, mapperPackage,daoPackage,beanPackage, tableName, fileds,dbName,isCamelField);
	}
}
