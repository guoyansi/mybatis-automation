package mybatisauto.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.BaseInBean;
import mybatisauto.bean.BaseOutBean;
import mybatisauto.bean.FieldBean;
import mybatisauto.bean.TableBean;
import mybatisauto.create.IBaseMyBatisByIdDao;
import mybatisauto.create.IBaseMyBatisDao;

public abstract class DataBase {
	/**
	 * tablelist
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	protected abstract List<TableBean> getTableList(Statement stmt,List<String> tables,List<String> exceptTable)throws Exception;
	
	/**
	 * 获取字段list
	 * @param table
	 * @return
	 * @throws Exception
	 */
	protected abstract List<FieldBean> getFieldList(Statement stmt,TableBean table,Boolean isCamel) throws Exception;
	
	protected abstract String getTableSql(String key,List<String> tables,List<String> exceptTable) throws Exception;
	
	/**
	 * 获取表明对应的bean名称
	 * @param name
	 * @return
	 */
	protected String getJavaType(String sqlType) throws Exception{
		if(sqlType.indexOf("char")!=-1){
			return "String";
		}
		return null;
	}
	protected String getJavaType(String sqlType,Integer precision,Integer dataScale) throws Exception{
		if(sqlType.indexOf("char")!=-1){
			return "String";
		}
		return null;
	}
	/**
	 * 检查是否是主键
	 * @return
	 */
	protected Boolean checkKey(String pri){
		if(pri!=null&&"pri".equals(pri.toLowerCase())){
			return true;
		}
		return false;
	}
	/**
	 * 检查是否是自动增长
	 * @return
	 */
	protected Boolean checkAutoAdd(String extr){
		if(extr!=null&&"auto_increment".equals(extr.toLowerCase())){
			return true;
		}
		return false;
	}
	
	/**
	 * 设置表格对应Bean  Name
	 * @param name
	 * @return
	 */
	protected TableBean setTableBeanName(String name) {
		TableBean t=new TableBean();
		String[] arr=name.split("_");
		String[] arr1=new String[arr.length];
		StringBuffer sb=new StringBuffer();
		StringBuffer sbxml=new StringBuffer();
		String firstUp=null;
		for(int i=0;i<arr.length;i++){
			firstUp=firstUpperCase(arr[i]);
			if(i==0){
				sbxml.append(arr[i]);
			}else{
				sbxml.append(firstUp);
			}
			sb.append(firstUp);
		}
		t.setSqlName(name);
		t.setBeanName(sb.toString()+"Bean");
		t.setBeanInName(sb.toString()+"InBean");
		t.setBeanOutName(sb.toString()+"OutBean");
		t.setXmlMapperName(sbxml.toString());
		t.setIdaoName("I"+sb.toString()+"Dao");
		return t;
	}
	/**
	 * 设置字段bean name
	 * @param name
	 * @return
	 */
	protected String setFieldBeanName(Boolean isCamel,String name) {
		if(!isCamel){
			return name;
		}
		String[] arr=name.split("_");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			if(i!=0){
				arr[i]=firstUpperCase(arr[i]);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	private String firstUpperCase(String str){
		String fst=str.substring(0, 1).toUpperCase();
		String se=str.substring(1);
		return fst+se;
	}
	
	
	
	/**
	 * 创建javabean
	 * 
	 * @throws Exception
	 */
	public void createBean(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		createOriBean(config, table, fs);
		createInBean(config, table, fs);
		createOutBean(config, table, fs);
	}
	private File createBeanBefore(AutoConfig config,String type, String name) {
		String url = config.getBeanPackage().replace(".", "/");
		File dirFile = null;
		File beanFile = null;
		String path = config.getSourceBean() + "/" + url + type;
		dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		// bean
		beanFile = new File(path + "/" + name + ".java");
		if (beanFile.exists()) {
			beanFile.delete();
		}
		return beanFile;
	}
	private void createOriBean(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		File file = createBeanBefore(config,"", table.getBeanName());
		BufferedWriter w = new BufferedWriter(new FileWriter(file, true));
		w.write("package " + config.getBeanPackage() + ";\n");
		w.write("\n");
		w.write("import java.io.Serializable;\n");
		w.write("\n");
		w.write("public class " + table.getBeanName()+ " implements Serializable{\n");
		w.write("\n");
		w.write("	private static final long serialVersionUID = -1L;");
		w.write("\n");
		w.write("\n");
		writeField(w,fs);
		w.write("}");
		w.flush();
		w.close();
	}

	private void createInBean(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		File file = createBeanBefore(config,"/in", table.getBeanInName());
		BufferedWriter w = new BufferedWriter(new FileWriter(file, true));
		w.write("package " + config.getBeanPackage() + ".in;\n");
		w.write("\n");
		w.write("import " + BaseInBean.class.getName() + ";\n");
		w.write("\n");
		w.write("public class " + table.getBeanInName()	+ " extends BaseInBean{\n");
		writeField(w,fs);
		w.write("}");
		w.flush();
		w.close();
	}

	private void createOutBean(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		File file = createBeanBefore(config,"/out", table.getBeanOutName());
		BufferedWriter w = new BufferedWriter(new FileWriter(file, true));
		w.write("package " + config.getBeanPackage() + ".out;\n");
		w.write("\n");
		w.write("import " + BaseOutBean.class.getName() + ";\n");
		w.write("\n");
		w.write("public class " + table.getBeanOutName()+ " extends BaseOutBean{\n");
		writeField(w,fs);
		w.write("}");
		w.flush();
		w.close();
	}
	// 写字段
		private void writeField(BufferedWriter w,List<FieldBean> fs) throws Exception {
			for (FieldBean f : fs) {
				w.write("	private " + f.getJavaType() + " " + f.getBeanName()+ ";\n");
				w.write("\n");
			}
			for (FieldBean f : fs) {
				// get
				w.write("	public " + f.getJavaType() + " get"+ firstUpperCase(f.getBeanName()) + "(){return "+ f.getBeanName() + ";}\n");
				// set
				w.write("	public void set" + firstUpperCase(f.getBeanName()) + "("+ f.getJavaType() + " " + f.getBeanName() + "){this."+ f.getBeanName() + "=" + f.getBeanName() + ";}\n");
				w.write("\n");
			}
		}
		
		/**
		 * 创建dao层接口
		 * 
		 * @throws Exception
		 */
		public void createDao(AutoConfig config, TableBean table) throws Exception {
			String url = config.getDaoPackage().replace(".", "/");

			File file = new File(config.getSourceDao() + "/" + url + "/"+ table.getIdaoName() + ".java");
			if (file.exists()) {
				file.delete();
			}
			BufferedWriter w = new BufferedWriter(new FileWriter(file, true));

			w.write("package " + config.getDaoPackage() + ";\n");
			w.write("\n");
			if (config.getIsSpringDao()) {
				w.write("import org.springframework.stereotype.Repository;\n");
			}
			w.write("import " + config.getBeanPackage() + "."+ table.getBeanName() + ";\n");
			w.write("import " + config.getBeanPackage() + ".in."+ table.getBeanInName() + ";\n");
			w.write("import " + config.getBeanPackage() + ".out."+ table.getBeanOutName() + ";\n");
			w.write("import " + IBaseMyBatisDao.class.getName() + ";\n");
			if(table.getHaveKey()){
				w.write("import " + IBaseMyBatisByIdDao.class.getName() + ";\n");
			}
			w.write("\n");
			if (config.getIsSpringDao()) {
				w.write("@Repository \n");
			}
			w.write("public interface " + table.getIdaoName()+ " extends IBaseMyBatisDao<" + table.getBeanName()+ "," + table.getBeanInName() + ","+ table.getBeanOutName() + ">");
			if(table.getHaveKey()){
				w.write(",IBaseMyBatisByIdDao<"+table.getBeanName()+">");
			}
			w.write("{\n");
			w.write("\n");
			w.write("}");
			w.flush();
			w.close();
		}
		public abstract void createMapper(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception;
}
