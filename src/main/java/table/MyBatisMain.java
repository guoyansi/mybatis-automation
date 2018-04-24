package table;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.ABean;
import table.bean.FieldBean;
import table.dao.ITableDao;

public class MyBatisMain {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static{
		try {
			reader=Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//bean 源文件
	private static String sourceBean="src/main/java";
	//bean 包
	private static String beanPackage="bean";//com.gys.bean
	//dao原文件
	private static String sourceDao="src/main/java";
	//dao 包
	private static String daoPackage="dao";//com.gys.dao
	//mapper源
	private static String sourceMapper="src/main/java";
	//mapper 包
	private static String mapperPackage="mapper";//mapper.gys
	
	public static void main(String[] args) {
			SqlSession session=sqlSessionFactory.openSession();
			try {
				ITableDao tableDao=session.getMapper(ITableDao.class);
				List<String> list=tableDao.getTableList();
				for(String s:list){
					System.out.println("tableName:"+s);
					List<FieldBean> fileds=tableDao.getFiledList(s);
					CreateFile.createJavaBean(sourceBean, beanPackage, s, fileds);
					CreateFile.createJavaDao(sourceDao, daoPackage, beanPackage, s, fileds);
					CreateFile.createMapper(sourceMapper, mapperPackage,daoPackage,beanPackage, s, fileds);
					for(FieldBean f:fileds){
						System.out.println(f.getField()+"=="+f.getType());
					}
					System.out.println("执行结束");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
		
	}

}
