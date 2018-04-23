package table;

import java.util.List;

public class Main {
	//目标源
	private static String sourcefolder="src/main/java";
	//源实体类包
	private static String beanUrl="bean";//com.gys.sm.bean
	//目标dao层
	private static String interDaoUrl="dao";//com.gys.sm.fun.tables.dao
	//目标mapper
	private static String mapperUrl="mapper";//com.gys.sm.fun.tables.
	
	public static void main(String[] args) {
		
		try {
			List<Class> beanList=FindBean.get(beanUrl);
			for(Class c:beanList){
				//创建成dao层接口
				CreateFile.createJavaDao(sourcefolder, interDaoUrl, c);
				//创建mapper
				CreateFile.createMapper(sourcefolder,interDaoUrl, mapperUrl, c);
				System.out.println("单表执行已结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
