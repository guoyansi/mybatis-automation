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
	public void closeSession(){
		session.close();
	}
	public <T> T getDao(Class<T> cs){
		return session.getMapper(cs);
	}
	
	public void start(String sourceBean,String beanPackage,String sourceDao,String daoPackage,String sourceMapper,String mapperPackage,boolean isSpringDao) throws Exception{
		openSession();
		try {
			ITableDao tableDao=this.getDao(ITableDao.class);
			//ITableDao tableDao=session.getMapper(ITableDao.class);
			//表名list
			List<String> list=tableDao.getTableList();
			for(String tableName:list){
				System.out.println("tableName:"+tableName);
				//字段list
				List<FieldBean> fileds=tableDao.getFiledList(tableName);
				create(sourceBean,beanPackage,sourceDao,daoPackage,sourceMapper,mapperPackage,tableName,fileds,isSpringDao);
				/*for(FieldBean f:fileds){
					System.out.println(f.getField()+"=="+f.getType());
				}*/
				System.out.println("执行结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession();
		}
	}
	
	private void create(String sourceBean,String beanPackage,String sourceDao,String daoPackage,String sourceMapper,String mapperPackage,String tableName,List<FieldBean> fileds,boolean isSpringDao) throws Exception{
		CreateFile cf=new CreateFile();
		cf.createJavaBean(sourceBean, beanPackage, tableName, fileds);
		cf.createJavaDao(sourceDao, daoPackage, beanPackage, tableName, fileds,isSpringDao);
		cf.createMapper(sourceMapper, mapperPackage,daoPackage,beanPackage, tableName, fileds);
	}
}
