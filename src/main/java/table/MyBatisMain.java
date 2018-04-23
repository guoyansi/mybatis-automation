package table;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.ABean;
import table.bean.FiledBean;
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
	
	private static String source="src/main/java";
	private static String beanPackage="bean";//com.gys.bean
	private static String daoPackage="dao";//com.gys.dao
	
	public static void main(String[] args) {
			SqlSession session=sqlSessionFactory.openSession();
			try {
				ITableDao tableDao=session.getMapper(ITableDao.class);
				List<String> list=tableDao.getTableList();
				for(String s:list){
					System.out.println("tableName:"+s);
					List<FiledBean> fileds=tableDao.getFiledList(s);
					CreateFile.createJavaBean(source, beanPackage, s, fileds);
					CreateFile.createJavaDao(source, daoPackage, beanPackage, s, fileds);
					for(FiledBean f:fileds){
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
