package mybatisauto.db;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.FieldBean;
import mybatisauto.bean.JdbcBean;
import mybatisauto.bean.TableBean;

public class MySqlDataBase extends DataBase{

	private JdbcBean jdbc;
	
	public MySqlDataBase() {
	}
	public MySqlDataBase(JdbcBean jdbc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.jdbc=jdbc;
	}

	@Override
	protected List<TableBean> getTableList(Statement stmt,List<String> tables,List<String> exceptTable) throws Exception {
		String[] arr=this.jdbc.getUrl().split("/");
		String dbName=arr[arr.length-1];
		String key="tables_in_"+dbName;//返回字段名
		String sql=getTableSql(key,tables,exceptTable);
		System.out.println(sql);
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
	
	@Override
	protected List<FieldBean> getFieldList(Statement stmt,TableBean table,Boolean isCamel) throws Exception {
		ResultSet rs=stmt.executeQuery("desc "+table.getSqlName());
		List<FieldBean> fs=new ArrayList<FieldBean>();
		FieldBean f=null;
		while(rs.next()){
			f=new FieldBean();
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
			System.out.println("==>>"+rs.getString("default"));
			f.setDefaults(rs.getString("default"));
			fs.add(f);
		}
		return fs;
	}
	
	
	
	@Override
	protected String getJavaType(String sqlType) throws Exception {
		String t=super.getJavaType(sqlType);
		if(t!=null){
			return t;
		}
		if(sqlType.indexOf("bigint")!=-1){
			t="Long";
		}else if(sqlType.indexOf("tinyint")!=-1
				||sqlType.indexOf("smallint")!=-1
				||sqlType.indexOf("mediumint")!=-1
				||sqlType.indexOf("int")!=-1
				||sqlType.indexOf("year")!=-1
				){
			t="Integer";
		}else if(sqlType.indexOf("text")!=-1){
			t="String";
		}else if(sqlType.indexOf("double")!=-1
				||sqlType.indexOf("decimal")!=-1){
			t="Double";
		}else if(sqlType.indexOf("float")!=-1){
			t="Float";
		} 
		
		if(t==null){
			throw new Exception("暂无配置"+sqlType+"类型的数据");
		}
		return t;
	}
	@Override
	protected String getTableSql(String key,List<String> tables,List<String> exceptTable){
		StringBuffer sb=new StringBuffer();
		sb.append("show tables ");
		StringBuffer targetWhere=null;
		StringBuffer exceptWhere=null;
		if(tables.size()!=0){
			targetWhere=getSqlWhere(key,tables,true);
		}
		if(exceptTable.size()!=0){
			exceptWhere=getSqlWhere(key,exceptTable,false);
		}
		if(targetWhere!=null&&exceptWhere!=null){
			sb.append("where ");
			sb.append(targetWhere.toString());
			sb.append(" and ");
			sb.append(exceptWhere.toString());
		}else if(targetWhere !=null){
			sb.append("where ");
			sb.append(targetWhere.toString());
		}else if(exceptWhere!=null){
			sb.append("where ");
			sb.append(exceptWhere.toString());
		}
		return sb.toString();
	}
	
	private StringBuffer getSqlWhere(String key,List<String> list,boolean isTarget){
		StringBuffer sb=new StringBuffer();
		sb.append(key);
		if(isTarget){//目标表
			sb.append(" in (");
		}else{//排除表
			sb.append(" not in (");
		}
		int len=list.size();
		for(int i=0;i<len;i++){
			if(i!=0){
				sb.append(",");
			}
			sb.append("'");
			sb.append(list.get(i));
			sb.append("'");
		}
		sb.append(")");
		return sb;
	}
	@Override
	public void createMapper(AutoConfig config, TableBean table,List<FieldBean> fs) throws Exception {
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=config.getMapperPackage().replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(config.getSourceMapper()+"/"+url+"/"+table.getXmlMapperName()+config.getXmlMapperSuffix()+".xml"),format);
		Document document=mapperDocument(config,table,fs);
		xmlWriter.write(document);
		xmlWriter.close();
		
	}
	
	private Document mapperDocument(AutoConfig config, TableBean table,List<FieldBean> fs){
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",config.getDaoPackage() +"."+table.getIdaoName());
		//selectList
		selectListDocument(root,config,table,fs);
		//selectOne
		selectOneDocument(root,config, table,fs);
		//getCount
		getCountDocument(root, config, table, fs);
		//update
		updateDocument(root, config, table, fs);
		//insert
		insertDocument(root, config, table, fs);
		//delete
		deleteDocument(root, config, table, fs);
		
		if(table.getHaveKey()){
			//selectOneById
			selectOneByIdDocument(root, config, table, fs);
			//updateById
			updateByIdDocument(root, config, table, fs);
			//insertGetId
			insertGetIdDocument(root, config, table, fs);
			//deleteById
			deleteByIdDocument(root, config, table, fs);
		}
		return document;
	}
	/**
	 * selectList
	 * @param root
	 * @param c
	 */
	private void selectListDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectList");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", config.getBeanPackage()+".out."+table.getBeanOutName());
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fs){
			i++;
			sb.append("\n\t\t\t"+f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
			if(i<fs.size()){
				sb.append(",");
			}
		}
		
		select.addText("select (@i:=@i+1) rowNum,"+sb.toString()+"\n\t\t from "+table.getSqlName()+", (SELECT @i:=0) as i");
		
		Element whereTag=select.addElement("where");
		for(FieldBean f:fs){
			Element ifTag=whereTag.addElement("if");
			ifTag.addAttribute("test", f.getBeanName()+"!=null");
			ifTag.addText("and "+f.getSqlName()+"=#{"+f.getBeanName()+"}");
		}
		Element whereIf=whereTag.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		whereIf.addText("${sqlWhere}");
		
		
		Element orderByIf=select.addElement("if");
		orderByIf.addAttribute("test","sqlOrderBy!=null");
		orderByIf.addText("order by ${sqlOrderBy}");
		Element limitIf=select.addElement("if");
		limitIf.addAttribute("test","sqlLimit==true");
		limitIf.addText("LIMIT #{sqlStartIndex},#{sqlPageSize}");
	}
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectOne");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", config.getBeanPackage()+".out."+table.getBeanOutName());
		StringBuffer sb=new StringBuffer();
		int i=0;
		for(FieldBean f:fs){
			i++;
			sb.append("\n\t\t\t"+f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
			if(i<fs.size()){
				sb.append(",");
			}
		}
		
		select.addText("\n\t\t select "+sb.toString()+"\n\t\t from "+table.getSqlName());
		
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private void selectOneByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectOneById");
		select.addAttribute("parameterType","object");
		select.addAttribute("resultType", config.getBeanPackage() +"."+table.getBeanName());
		select.addText("\n\t\t");
		StringBuffer sb=new StringBuffer();
		int i=0;
		String key="";
		for(FieldBean f:fs){
			i++;
			sb.append("\n\t\t\t"+f.getSqlName());
			sb.append(" as ");
			sb.append(f.getBeanName());
			if(i<fs.size()){
				sb.append(",");
			}
			if(f.getIsKey()){
				key=f.getSqlName();
			}
			
		}
		
		select.addText("select "+sb.toString()+"\n\t\t\tfrom "+table.getSqlName());
		
		Element whereTag=select.addElement("where");
		whereTag.addText(key+"=#{_parameter}");
	}
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	private void getCountDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element select=root.addElement("select");
		select.addAttribute("id", "getCount");
		select.addAttribute("parameterType",config.getBeanPackage()+".in."+table.getBeanInName());
		select.addAttribute("resultType", "int");
		select.addText("\n\t\t");
		
		select.addText("select count(*) from "+table.getSqlName());
		Element whereIf=select.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	private void updateDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element update=root.addElement("update");
		update.addAttribute("id", "update");
		update.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
		update.addText("\n\t\t");
		update.addText("update "+table.getSqlName());
		Element setTag=update.addElement("set");
		for(FieldBean f:fs){
			if(f.getIsKey()){
				continue;
			}
			Element ifTag=setTag.addElement("if");
			ifTag.addAttribute("test", f.getBeanName()+"==null");
			ifTag.addText(f.getSqlName()+"=null,");
			Element ifTag1=setTag.addElement("if");
			ifTag1.addAttribute("test", f.getBeanName()+"!=null");
			ifTag1.addText(f.getSqlName()+"=#{"+f.getBeanName()+"},");
		}
		Element whereIf=update.addElement("if");
		whereIf.addAttribute("test", "sqlWhere!=null");
		Element whereTag=whereIf.addElement("where");
		whereTag.addText("${sqlWhere}");
	}
	
	
	private void updateByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element update=root.addElement("update");
		update.addAttribute("id", "updateById");
		update.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
		update.addText("\n\t\t");
		update.addText("update "+table.getSqlName());
		Element setTag=update.addElement("set");
		String key=null;
		for(FieldBean f:fs){
			if(f.getIsKey()){
				key=f.getSqlName();
				continue;
			}
			Element ifTag=setTag.addElement("if");
			ifTag.addAttribute("test", f.getSqlName()+"==null");
			ifTag.addText(f.getSqlName()+"=null,");
			Element ifTag1=setTag.addElement("if");
			ifTag1.addAttribute("test", f.getSqlName()+"!=null");
			ifTag1.addText(f.getSqlName()+"=#{"+f.getBeanName()+"},");
		}
		update.addText("\n			where "+key+"=#{_parameter}");
	}
	
	private void insertDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("insert");

		insert.addAttribute("id", "insert");
		insert.addAttribute("parameterType", config.getBeanPackage()+"."+table.getBeanName());
		
		insert.addText("\n\t\t");
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
		for(FieldBean f:fs){
			if(f.getIsKey()&&f.getIsAutoAdd()){
				continue;
			}
			
			Element ifTag1=trim1.addElement("if");
			ifTag1.addAttribute("test", f.getBeanName()+"!=null");
			ifTag1.addText(f.getSqlName()+",");
		
			Element ifTag2=trim2.addElement("if");
			ifTag2.addAttribute("test", f.getBeanName()+"!=null");
			ifTag2.addText("#{"+f.getBeanName()+"},");
		}
	}

	private void insertGetIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("insert");
		String key=null;
		//String keyType=null;
		for(FieldBean f:fs){
			if(f.getIsKey()){
				key=f.getBeanName();
				break;
			}
		}
		insert.addAttribute("id", "insertGetId");
		insert.addAttribute("keyProperty",key);
		insert.addAttribute("useGeneratedKeys", "true");
		
		insert.addAttribute("parameterType", config.getBeanPackage()+"."+table.getBeanName());
		
		insert.addText("\n\t\t");
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
		for(FieldBean f:fs){
			if(f.getIsKey()&&f.getIsAutoAdd()){
				continue;
			}
			Element ifTag1=trim1.addElement("if");
			ifTag1.addAttribute("test", f.getBeanName()+"!=null");
			ifTag1.addText(f.getSqlName()+",");
			
			Element ifTag2=trim2.addElement("if");
			ifTag2.addAttribute("test", f.getBeanName()+"!=null");
			ifTag2.addText("#{"+f.getBeanName()+"},");
		}
	}
	private void deleteDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("delete");
		insert.addAttribute("id", "delete");
		insert.addAttribute("parameterType",config.getBeanPackage()+"."+table.getBeanName());
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+table.getSqlName());
		Element whereIf=insert.addElement("if");
		whereIf.addAttribute("test", "sqlWhere !=null");
		Element where=whereIf.addElement("where");
		where.addText("${sqlWhere}");
	}
	
	private void deleteByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs){
		Element insert=root.addElement("delete");
		insert.addAttribute("id", "deleteById");
		insert.addAttribute("parameterType","object");
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+table.getSqlName());
		Element whereIf=insert.addElement("if");
		whereIf.addAttribute("test", "sqlWhere !=null");
		Element where=whereIf.addElement("where");
			String key="";
			for(FieldBean f:fs){
				if(f.getIsKey()){
					key=f.getSqlName();
					break;
				}
			}
			where.addText(key+"=#{_parameter}");
	}
}
