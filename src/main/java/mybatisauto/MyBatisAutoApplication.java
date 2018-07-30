package mybatisauto;

import java.util.ArrayList;
import java.util.List;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.JdbcBean;
import mybatisauto.db.DataSource;

public class MyBatisAutoApplication {
	public static void main(String[] args) throws Exception{
		JdbcBean jdbc=new JdbcBean("jdbc:mysql://127.0.0.1:3306/test", "root", "gys");
		//JdbcBean jdbc=new JdbcBean("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "boards", "boards");
		//JdbcBean jdbc=new JdbcBean("jdbc:oracle:thin:@10.3.7.2:1521:orcl", "emseub", "eubtest");
		AutoConfig config=new AutoConfig();
		//bean 源文件
		config.setSourceBean("src/main/java");
		//bean 包
		config.setBeanPackage("bean");//com.gys.bean
		//dao原文件
		config.setSourceDao("src/main/java");
		//dao 包
		config.setDaoPackage("dao");// daoPackage="dao";//com.gys.dao
		//mapper源
		config.setSourceMapper("src/main/java");
		//mapper 包
		config.setMapperPackage("mapper");//mapper.gys
		//是否生成springdao
		config.setIsSpringDao(false);
		//是否驼峰命名字段
		config.setIsCamelField(true);
		List<String> talbes=new ArrayList<String>();
		talbes.add("gys");
		//talbes.add("TB_BAL_SH_SELF_SCHEDULE");
		config.setTables(talbes);
		DataSource ds=new DataSource(jdbc);
		ds.start(config);
	}
}
