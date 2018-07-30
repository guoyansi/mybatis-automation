package mybatisauto.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

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
	 * 查询table
	 * @param stmt
	 * @param sql
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected List<TableBean> executeQueryTable(Statement stmt,String sql,String key) throws Exception{
		ResultSet rs=stmt.executeQuery(sql);
		List<TableBean> ts=new ArrayList<TableBean>();
		while(rs.next()){
			String name=rs.getString(key);
			ts.add(this.setTableBeanName(name));
		}
		if(rs!=null){
			rs.close();
		}
		return ts;
	}
	
	/**
	 * 获取字段list
	 * @param table
	 * @return
	 * @throws Exception
	 */
	protected abstract List<FieldBean> getFieldList(Statement stmt,TableBean table,Boolean isCamel) throws Exception;
	
	/**
	 * 初始化字段值
	 * @throws Exception
	 */
	protected FieldBean initFieldBean(ResultSet rs,TableBean table,Boolean isCamel) throws Exception{
		FieldBean f=new FieldBean();
		String type=rs.getString("Type");
		String name=rs.getString("Field");
		f.setSqlName(name);
		f.setBeanName(this.setFieldBeanName(isCamel, name));
		f.setSqlType(type);
		f.setJavaType(this.getJavaType(type));
		boolean iskey=this.checkKey(rs.getString("Key"));
		f.setIsKey(iskey);
		//表示table 是否有主键
		if(iskey){
			table.setHaveKey(true);
		}
		f.setIsAutoAdd(this.checkAutoAdd(rs.getString("Extra")));
		f.setDefaults(rs.getString("default"));
		this.setFieldSqlName(f.getBeanName(), f);
		return f;
	}
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
		}else if (sqlType.indexOf("date")!=-1) {
			return "String";
		}else if(sqlType.indexOf("timestamp")!=-1){
			return "Long";
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
	
	protected void setFieldSqlName(String beanName,FieldBean f){
		String name=firstUpperCase(beanName);
		//Like
		f.setSqlLikeName("sql"+name+"Like");
		//LeftLike
		f.setSqlLeftLikeName("sql"+name+"LeftLike");
		//RightLike
		f.setSqlRightLikeName("sql"+name+"RightLike");
		//D
		f.setSqlDName("sql"+name+"D");
		//Dd
		f.setSqlDdName("sql"+name+"Dd");
		//X
		f.setSqlXName("sql"+name+"X");
		//Xd
		f.setSqlXdName("sql"+name+"Xd");
		//in
		//f.setSqlInName("sql"+name+"In");
		//inList
		f.setSqlInListName("sql"+name+"InList");
		
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
	public void createBean(AutoConfig config, TableBean table, List<FieldBean> fs,DataBase db) throws Exception {
		createOriBean(config, table, fs,db);
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
	private void createOriBean(AutoConfig config, TableBean table, List<FieldBean> fs,DataBase db) throws Exception {
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
		w.write("\n");
		if(db instanceof OracleDataBase){
			w.write("	private String sqlSeq;\n");
			w.write("\n");
			// get
			w.write("	public String getSqlSeq(){return sqlSeq;}\n");
			// set
			w.write("	public void setSqlSeq(String sqlSeq){this.sqlSeq=sqlSeq;}\n");
			w.write("\n");
		}
		w.write("}");
		w.flush();
		w.close();
	}

	private void createInBean(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		File file = createBeanBefore(config,"/in", table.getBeanInName());
		BufferedWriter w = new BufferedWriter(new FileWriter(file, true));
		w.write("package " + config.getBeanPackage() + ".in;\n");
		w.write("\n");
		w.write("import java.util.List;\n");
		w.write("import " + BaseInBean.class.getName() + ";\n");
		
		w.write("\n");
		w.write("public class " + table.getBeanInName()	+ " extends BaseInBean{\n");
		w.write("	private static final long serialVersionUID = 1L;\n");
		w.write("\n"); 
		writeField(w,fs);
		for(FieldBean f:fs){
			w.write("	//  "+f.getBeanName()+"====================\n");
			w.write("	private Boolean " +f.getSqlLikeName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlLeftLikeName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlRightLikeName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlDName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlDdName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlXName() +"=false;\n");
			w.write("\n");			
			w.write("	private Boolean " +f.getSqlXdName() +"=false;\n");
			w.write("\n");
			/*w.write("	private Boolean " +f.getSqlInName() +"=false;\n");
			w.write("\n");*/
			w.write("	private List<"+f.getJavaType()+"> " +f.getSqlInListName() +";\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlLikeName())+ "(){return " +f.getSqlLikeName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlLikeName())+ "(Boolean " + f.getSqlLikeName() + "){this."+f.getSqlLikeName() + "=" + f.getSqlLikeName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlLeftLikeName())+ "(){return " +f.getSqlLeftLikeName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlLeftLikeName())+ "(Boolean " + f.getSqlLeftLikeName() + "){this."+f.getSqlLeftLikeName() + "=" + f.getSqlLeftLikeName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlRightLikeName())+ "(){return " +f.getSqlRightLikeName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlRightLikeName())+ "(Boolean " + f.getSqlRightLikeName() + "){this."+f.getSqlRightLikeName() + "=" + f.getSqlLeftLikeName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlDName())+ "(){return " +f.getSqlDName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlDName())+ "(Boolean " + f.getSqlDName() + "){this."+f.getSqlDName() + "=" + f.getSqlDName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlDdName())+ "(){return " +f.getSqlDdName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlDdName())+ "(Boolean " + f.getSqlDdName() + "){this."+f.getSqlDdName() + "=" + f.getSqlDdName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlXName())+ "(){return " +f.getSqlXName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlXName())+ "(Boolean " + f.getSqlXName() + "){this."+f.getSqlXName() + "=" + f.getSqlXName()+ ";}\n");
			w.write("\n");
			
			// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlXdName())+ "(){return " +f.getSqlXdName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlXdName())+ "(Boolean " + f.getSqlXdName() + "){this."+f.getSqlXdName() + "=" + f.getSqlXdName()+ ";}\n");
			w.write("\n");
			
			/*// get
			w.write("	public Boolean get"+ firstUpperCase(f.getSqlInName())+ "(){return " +f.getSqlInName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlInName())+ "(Boolean " + f.getSqlInName() + "){this."+f.getSqlInName() + "=" + f.getSqlInName()+ ";}\n");
			w.write("\n");*/
			
			// get
			w.write("	public List<"+f.getJavaType()+"> get"+ firstUpperCase(f.getSqlInListName())+ "(){return " +f.getSqlInListName()+";}\n");
			// set
			w.write("	public void set" + firstUpperCase(f.getSqlInListName())+ "(List<"+f.getJavaType()+"> " + f.getSqlInListName() + "){this."+f.getSqlInListName() + "=" + f.getSqlInListName()+ ";}\n");
			w.write("\n");
			
		}
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
		w.write("	private static final long serialVersionUID = 1L;\n");
		w.write("\n");
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
		w.write("\n");
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
		
		public abstract void selectListDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception;

		private Element _resultMapDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs,String name,String url)throws Exception{
			Element tag=root.addElement("resultMap");
			tag.addAttribute("id",name+"Map");
			tag.addAttribute("type", config.getBeanPackage()+url+"."+name);
			for(FieldBean f:fs) {
				Element tag1=null;
				if(f.getIsKey()) {//是主键
					tag1=tag.addElement("id");
				}else {
					tag1=tag.addElement("result");
				}
				tag1.addAttribute("column", f.getSqlName());
				tag1.addAttribute("property", f.getBeanName());
			}
			return tag;
		}
		public void resultMapDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception{
			_resultMapDocument(root,config,table,fs,table.getBeanName(),"");
		}
		
		public void resultOutMapDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception{
			Element tag=_resultMapDocument(root,config,table,fs,table.getBeanOutName(),".out");
			Element rnTag=tag.addElement("result");
			rnTag.addAttribute("column","rn");
			rnTag.addAttribute("property","rn");
		}
		
		//selectList标签
		protected Element getSelectListTag(Element root,AutoConfig config,TableBean table) throws Exception{
			Element select=root.addElement("select");
			select.addAttribute("id", "selectList");
			select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
			select.addAttribute("resultMap", table.getBeanOutName()+"Map");
			select.addText("\n\t\t");
			return select;
		}
		/**
		 * selectList时字段
		 * @param fs
		 * @return
		 * @throws Exception
		 */
		protected StringBuffer getFieldsSB(List<FieldBean> fs) throws Exception{
			StringBuffer sb=new StringBuffer();
			int i=0;
			for(FieldBean f:fs){
				i++;
				sb.append("\n\t\t\t"+f.getSqlName());
				//sb.append(" as ");
				//sb.append(f.getBeanName());
				if(i<fs.size()){
					sb.append(",");
				}
			}
			return sb;
		}
		
		protected abstract void getSelectWhere(Element select,List<FieldBean> fs);
		
		/**
		 * 获取selectList  where的公共部分
		 * @param select
		 * @return
		 */
		/*protected void getSelectWhere(Element select,List<FieldBean> fs){
			Element whereTag=select.addElement("where");
			for(FieldBean f:fs){
				Element chooseTag=whereTag.addElement("choose");
				Element whenTagLike=chooseTag.addElement("when");
				
				//Like
				whenTagLike.addAttribute("test", f.getSqlLikeName()+"==true");
				whenTagLike.addText("and "+f.getSqlName()+ " like concat(concat('%',#{"+f.getBeanName()+"}),'%')");
				
				//LeftLike
				Element whenTagLeftLike=chooseTag.addElement("when");
				whenTagLeftLike.addAttribute("test",f.getSqlLeftLikeName()+"==true");
				whenTagLeftLike.addText("and "+f.getSqlName()+" like concat(#{"+f.getBeanName()+"},'%')");
				
				//RightLike
				Element whenTagRightLike=chooseTag.addElement("when");
				whenTagRightLike.addAttribute("test", f.getSqlRightLikeName()+"==true");
				whenTagRightLike.addText("and "+f.getSqlName()+" like concat('%',#{"+f.getBeanName()+"})");
				
				//D
				Element whenTagD=chooseTag.addElement("when");
				whenTagD.addAttribute("test", f.getSqlDName()+"==true");
				whenTagD.addText("and "+f.getSqlName()+">#{"+f.getBeanName()+"}");
				
				//X
				Element whenTagX=chooseTag.addElement("when");
				whenTagX.addAttribute("test", f.getSqlXName()+"==true");
				whenTagX.addText("and "+f.getSqlName()+"<#{"+f.getBeanName()+"}");
				//Dd
				Element whenTagDd=chooseTag.addElement("when");
				whenTagDd.addAttribute("test", f.getSqlDdName()+"==true");
				whenTagDd.addText("and "+f.getSqlName()+">= #{"+f.getBeanName()+"}");
				
				//Xd
				Element whenTagXd=chooseTag.addElement("when");
				whenTagXd.addAttribute("test", f.getSqlXdName()+"==true");
				whenTagXd.addText("and "+f.getSqlName()+">= #{"+f.getBeanName()+"}");
				
				//in
				Element whenTagIn=chooseTag.addElement("when");
				whenTagIn.addAttribute("test",f.getSqlInListName()+"!=null");
				Element foreachTag=whenTagIn.addElement("foreach");
				foreachTag.addAttribute("collection", f.getSqlInListName());
				foreachTag.addAttribute("item", "a");
				foreachTag.addAttribute("open", f.getSqlName()+" in (");
				foreachTag.addAttribute("close", ")");
				foreachTag.addAttribute("separator", ",");
				foreachTag.addText("#{a}");
				
				Element whenTag=chooseTag.addElement("when");
				whenTag.addAttribute("test", f.getBeanName()+"!=null and "+f.getBeanName()+"!=''");
				whenTag.addText("and "+f.getSqlName()+"=#{"+f.getBeanName()+"}");
			}
			Element whereIf=whereTag.addElement("if");
			whereIf.addAttribute("test", "sqlWhere!=null");
			whereIf.addText("${sqlWhere}");
		}*/
		
		protected abstract void getSelectUpdateWhere(Element update,List<FieldBean> fs);
		/**
		 * 获取selectList  where的公共部分
		 * @param select
		 * @return
		 */
		/*protected void getSelectUpdateWhere(Element update,List<FieldBean> fs){
			Element whereTag=update.addElement("where");
			String prev="sqlWhereBean.";
			for(FieldBean f:fs){
				Element chooseTag=whereTag.addElement("choose");
				Element whenTagLike=chooseTag.addElement("when");
				
				//Like
				whenTagLike.addAttribute("test", prev+f.getSqlLikeName()+"==true");
				whenTagLike.addText("and "+f.getSqlName()+ " like concat(concat('%',#{"+prev+f.getBeanName()+"}),'%')");
				
				//LeftLike
				Element whenTagLeftLike=chooseTag.addElement("when");
				whenTagLeftLike.addAttribute("test", prev+f.getSqlLeftLikeName()+"==true");
				whenTagLeftLike.addText("and "+f.getSqlName()+" like concat(#{"+prev+f.getBeanName()+"},'%')");
				
				//RightLike
				Element whenTagRightLike=chooseTag.addElement("when");
				whenTagRightLike.addAttribute("test", prev+f.getSqlRightLikeName()+"==true");
				whenTagRightLike.addText("and "+f.getSqlName()+" like concat('%',#{"+prev+f.getBeanName()+"})");
				
				//D
				Element whenTagD=chooseTag.addElement("when");
				whenTagD.addAttribute("test", prev+f.getSqlDName()+"==true");
				whenTagD.addText("and "+f.getSqlName()+">#{"+prev+f.getBeanName()+"}");
				
				//X
				Element whenTagX=chooseTag.addElement("when");
				whenTagX.addAttribute("test",prev+f.getSqlXName()+"==true");
				whenTagX.addText("and "+f.getSqlName()+"<#{"+prev+f.getBeanName()+"}");
				//Dd
				Element whenTagDd=chooseTag.addElement("when");
				whenTagDd.addAttribute("test",prev+f.getSqlDdName()+"==true");
				whenTagDd.addText("and "+f.getSqlName()+">= #{"+prev+f.getBeanName()+"}");
				
				//Xd
				Element whenTagXd=chooseTag.addElement("when");
				whenTagXd.addAttribute("test",prev+f.getSqlXdName()+"==true");
				whenTagXd.addText("and "+f.getSqlName()+">= #{"+prev+f.getBeanName()+"}");
				
				//in
				Element whenTagIn=chooseTag.addElement("when");
				whenTagIn.addAttribute("test", prev+f.getSqlInListName()+"!=null");
				Element foreachTag=whenTagIn.addElement("foreach");
				foreachTag.addAttribute("collection", prev+f.getSqlInListName());
				foreachTag.addAttribute("item", "a");
				foreachTag.addAttribute("open", f.getSqlName()+" in (");
				foreachTag.addAttribute("close", ")");
				foreachTag.addAttribute("separator", ",");
				foreachTag.addText("#{a}");
				
				Element whenTag=chooseTag.addElement("when");
				whenTag.addAttribute("test", prev+f.getBeanName()+"!=null and "+prev+f.getBeanName()+"!=''");
				whenTag.addText("and "+f.getSqlName()+"=#{"+prev+f.getBeanName()+"}");
			}
			Element whereIf=whereTag.addElement("if");
			whereIf.addAttribute("test", prev+"sqlWhere!=null");
			whereIf.addText("${"+prev+"sqlWhere}");
		}*/
		
		protected void getSelectListOrderBy(Element select) throws Exception{
			Element orderByIf=select.addElement("if");
			orderByIf.addAttribute("test","sqlOrderBy!=null");
			orderByIf.addText("order by ${sqlOrderBy}");
			
			Element orderByDescIf=select.addElement("if");
			orderByDescIf.addAttribute("test","sqlOrderByDesc!=null");
			orderByDescIf.addText("order by ${sqlOrderByDesc} desc");
			
			Element orderByAscIf=select.addElement("if");
			orderByAscIf.addAttribute("test","sqlOrderByAsc!=null");
			orderByAscIf.addText("order by ${sqlOrderByAsc}");
		}
		//selectOne
		public void selectOneDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element select=root.addElement("select");
			select.addAttribute("id", "selectOne");
			select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
			select.addAttribute("resultMap", table.getBeanName()+"Map");
			StringBuffer sb=new StringBuffer();
			int i=0;
			for(FieldBean f:fs){
				i++;
				sb.append("\n\t\t\t"+f.getSqlName());
				//sb.append(" as ");
				//sb.append(f.getBeanName());
				if(i<fs.size()){
					sb.append(",");
				}
			}
			select.addText("\n\t\t select "+sb.toString()+"\n\t\t from "+table.getSqlName());
			this.getSelectWhere(select, fs);
		}
		protected void selectOneByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception{
			Element select=root.addElement("select");
			select.addAttribute("id", "selectOneById");
			select.addAttribute("parameterType","object");
			select.addAttribute("resultMap",table.getBeanName()+"Map");
			select.addText("\n\t\t");
			StringBuffer sb=getFieldsSB(fs);
			
			select.addText("select "+sb.toString()+" from "+table.getSqlName());
			
			Element whereTag=select.addElement("where");
			String key="";
			for(FieldBean f:fs) {
				 if(f.getIsKey()) {
					 key=f.getSqlName();
					 break;
				 }
			}
			whereTag.addText(key+"=#{_parameter}");
		}
		public void getCountDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element select=root.addElement("select");
			select.addAttribute("id", "getCount");
			select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
			select.addAttribute("resultType", "int");
			select.addText("\n\t\t");
			
			select.addText("select count(*) from "+table.getSqlName());
			getSelectWhere(select, fs);
		}
		protected void updateDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception{
			Element update=root.addElement("update");
			update.addAttribute("id", "update");
			//update.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
			update.addText("\n\t\t");
			update.addText("update "+table.getSqlName());
			Element setTag=update.addElement("set");
			for(FieldBean f:fs){
				if(f.getIsKey()){
					continue;
				}
				Element ifTag1=setTag.addElement("if");
				ifTag1.addAttribute("test", "sqlValueBean."+f.getBeanName()+"!=null");
				ifTag1.addText(f.getSqlName()+"=#{sqlValueBean."+f.getBeanName()+"},");
			}
			getSelectUpdateWhere(update,fs);
		}
		
		
		protected void updateByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element update=root.addElement("update");
			update.addAttribute("id", "updateById");
			update.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
			update.addText("\n\t\t");
			update.addText("update "+table.getSqlName());
			Element setTag=update.addElement("set");
			FieldBean keyF=null;
			for(FieldBean f:fs){
				if(f.getIsKey()){
					keyF=f;
					continue;
				}
				Element ifTag1=setTag.addElement("if");
				ifTag1.addAttribute("test", f.getSqlName()+"!=null");
				ifTag1.addText(f.getSqlName()+"=#{"+f.getBeanName()+"},");
			}
			update.addText("\n			where "+keyF.getSqlName()+"=#{"+keyF.getBeanName()+"}");
		}
		//返回主键字段名
		protected  FieldBean insertSql(Element insert,TableBean table,List<FieldBean> fs) throws Exception{
			insert.addText("insert into "+table.getSqlName());
			
			Element trim1=insert.addElement("trim");
			trim1.addAttribute("prefix", "(");
			trim1.addAttribute("suffix", ")");
			trim1.addAttribute("suffixOverrides", ",");
			
			insert.addText("\n 			values");
			
			Element trim2=insert.addElement("trim");
			trim2.addAttribute("prefix", "(");
			trim2.addAttribute("suffix", ")");
			trim2.addAttribute("suffixOverrides", ",");
			FieldBean key=null;
			for(FieldBean f:fs){
				if(f.getIsKey()&&f.getIsAutoAdd()){
					continue;
				}else if(f.getIsKey()){
					key=f;
					
					trim1.addText("\n			"+f.getSqlName()+",");
					
					Element ifseq=trim2.addElement("if");
					ifseq.addAttribute("test","sqlSeq==null");
					ifseq.addText("#{"+f.getBeanName()+"},");
					Element ifseq1=trim2.addElement("if");
					ifseq1.addAttribute("test","sqlSeq!=null");
					ifseq1.addText("\n				${sqlSeq}.Nextval,\n			");
					
				}else{
					Element ifTag1=trim1.addElement("if");
					ifTag1.addAttribute("test", f.getBeanName()+"!=null");
					ifTag1.addText(f.getSqlName()+",");
					
					Element ifTag2=trim2.addElement("if");
					ifTag2.addAttribute("test", f.getBeanName()+"!=null");
					ifTag2.addText("#{"+f.getBeanName()+"},");
				}
			}
			return key;
		}
		protected void insertDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element insert=root.addElement("insert");

			insert.addAttribute("id", "insert");
			insert.addAttribute("parameterType", config.getBeanPackage()+"."+table.getBeanName());
			insert.addText("\n\t\t");
			this.insertSql(insert, table, fs);
		}
		protected abstract void insertGetIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception;
		
		protected void deleteDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element insert=root.addElement("delete");
			insert.addAttribute("id", "delete");
			insert.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
			insert.addText("\n\t\t");
			insert.addText("DELETE FROM "+table.getSqlName());
			getSelectWhere(insert, fs);
		}
		protected void deleteByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
			Element insert=root.addElement("delete");
			insert.addAttribute("id", "deleteById");
			insert.addAttribute("parameterType","object");
			insert.addText("\n\t\t");
			insert.addText("DELETE FROM "+table.getSqlName());
			Element where=insert.addElement("where");
			String key="";
			for(FieldBean f:fs){
				if(f.getIsKey()){
					key=f.getSqlName();
					break;
				}
			}
				where.addText(key+"=#{_parameter}");
		}
		
		
		/**
		 * 批量插入
		 * @throws Exception
		 */
		protected abstract void batchInsert(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception;
		
		protected abstract FieldBean batchInsertSql(Element root,AutoConfig config,TableBean table,List<FieldBean> fs)throws Exception;
	
		protected abstract void batchInsertGetId(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception;
}
