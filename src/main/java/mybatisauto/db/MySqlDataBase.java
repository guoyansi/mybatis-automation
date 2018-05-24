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
		List<TableBean> ts=this.executeQueryTable(stmt,sql,key);
		return ts;
	}
	
	@Override
	protected List<FieldBean> getFieldList(Statement stmt,TableBean table,Boolean isCamel) throws Exception {
		ResultSet rs=stmt.executeQuery("desc "+table.getSqlName());
		List<FieldBean> fs=new ArrayList<FieldBean>();
		while(rs.next()){
			fs.add(this.initFieldBean(rs,table,isCamel));
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
	
	private Document mapperDocument(AutoConfig config, TableBean table,List<FieldBean> fs) throws Exception{
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
	@Override
	public void selectListDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		Element select=this.getSelectListTag(root,config,table);
		StringBuffer sb=this.getFieldsSB(fs);
		select.addText("select (@i:=@i+1) rn,"+sb.toString()+"\n\t\t from "+table.getSqlName()+", (SELECT @i:=0) as i");
		this.getSelectWhere(select,fs);
		getSelectListOrderBy(select);
		Element limitIf=select.addElement("if");
		limitIf.addAttribute("test","sqlLimit==true");
		limitIf.addText("LIMIT #{sqlStartIndex},#{sqlPageSize}");
	}
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	@Override
	public void selectOneDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.selectOneDocument(root, config, table, fs);
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	@Override
	public void selectOneByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.selectOneByIdDocument(root, config, table, fs);
	}
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	@Override
	public void getCountDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.getCountDocument(root, config, table, fs);
	}
	@Override
	public void updateDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.updateDocument(root, config, table, fs);
	}
	
	
	@Override
	public void updateByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.updateByIdDocument(root, config, table, fs);
	}
	
	@Override
	public void insertDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.insertDocument(root, config, table, fs);
	}

	@Override
	public void insertGetIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
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
		
		this.insertSql(insert, table, fs);
	}
	@Override
	public void deleteDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.deleteDocument(root, config, table, fs);
	}
	@Override
	public void deleteByIdDocument(Element root,AutoConfig config,TableBean table,List<FieldBean> fs) throws Exception{
		super.deleteByIdDocument(root, config, table, fs);
	}
}
