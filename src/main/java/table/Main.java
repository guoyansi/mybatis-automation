package table;


public class Main {
	

	/**
	 * TODO 测试oracle获取  郭延思
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//bean 源文件
		String sourceBean="src/main/java";
		//bean 包
		String beanPackage="bean";//com.gys.bean
		//dao原文件
		String sourceDao="src/main/java";
		//dao 包
		String daoPackage="dao";//com.gys.dao
		//mapper源
		String sourceMapper="src/main/java";
		//mapper 包
		String mapperPackage="mapper";//mapper.gys
		//是否生成springdao
		boolean isSpringDao=false;
		//生成的bean实体类是否需要继承baseSqlBean
		boolean isExtendBaseSqlBean=true;
		//mysql，oracle
		String dbName="oracle";
		//需要自动生成代码的表名
		String[] tables={};
		//不需要自动生成代码的表名
		String[] exceptTables={};
		DataSource ds=new DataSource("table/mybatis-config.xml");
		ds.start(sourceBean, beanPackage, sourceDao, daoPackage, sourceMapper, mapperPackage,isSpringDao,tables,exceptTables,dbName,isExtendBaseSqlBean);
	}

}
