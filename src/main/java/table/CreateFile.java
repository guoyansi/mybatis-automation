package table;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import table.bean.FiledBean;

public class CreateFile {
	public static void main(String[] args) {
		try {
			//System.out.println(CreateFile.class.getPackage());
			//createFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void createJavaDao(String source,String daoPackage,String beanPackage,String tableName,List<FiledBean> fileds) throws IOException{
		//File file=new File("src/t/createjava/IGysDao.java");
		String url=daoPackage.replace(".", "/");
		
		File file=new File(source+"/"+url+"/I"+firstUpperCase(tableName)+"Dao.java");
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaDaoContent(w,daoPackage,beanPackage,tableName,fileds);
		w.flush();
		w.close();
	}
	
	public static void createJavaBean(String sourcefolder,String targetPackage,String tableName,List<FiledBean> fileds) throws Exception{
		//File file=new File("src/t/createjava/IGysDao.java");
		String url=targetPackage.replace(".", "/");
		
		File file=new File(sourcefolder+"/"+url+"/"+firstUpperCase(tableName)+"Bean.java");
		if(file.exists()){
			file.delete();
		}
		BufferedWriter w=new BufferedWriter(new FileWriter(file, true));
		writeJavaBeanContent(w,targetPackage,tableName,fileds);
		w.flush();
		w.close();
	}
	
	private static void writeJavaBeanContent(BufferedWriter w,String targetPackage,String tableName,List<FiledBean> fileds) throws Exception{
		w.write("package "+targetPackage+";\n");
		w.write("\n");
		w.write("public class "+firstUpperCase(tableName)+"Bean {\n");
		w.write("\n");
		List<FiledBean> filedList=new ArrayList<FiledBean>();
		for(FiledBean f:fileds){
			String sqlType=f.getType().toLowerCase();
			String javaType=null;
			if(sqlType.indexOf("tinyint")!=-1
					||sqlType.indexOf("smallint")!=-1
					||sqlType.indexOf("mediumint")!=-1
					||sqlType.indexOf("int")!=-1
					||sqlType.indexOf("bigint")!=-1
					||sqlType.indexOf("year")!=-1
					){
				javaType="Integer";
			}else if(sqlType.indexOf("text")!=-1
					||sqlType.indexOf("varchar")!=-1
					||sqlType.indexOf("char")!=-1){
				javaType="String";
			}else if(sqlType.indexOf("double")!=-1
					||sqlType.indexOf("decimal")!=-1){
				javaType="Double";
			}else if(sqlType.indexOf("float")!=-1){
				javaType="Float";
			}else{
				throw new Exception("暂无配置"+sqlType+"类型的数据");
			}
			w.write("	private "+javaType+" "+f.getField()+";\n");
			w.write("\n");
			FiledBean bean=new FiledBean();
			bean.setType(javaType);
			bean.setField(f.getField());
			filedList.add(bean);
		}
		for(FiledBean f:filedList){
			//get
			w.write("	public "+f.getType()+" get"+firstUpperCase(f.getField())+"(){return "+f.getField()+";}\n");
			//set
			w.write("	public void set"+firstUpperCase(f.getField())+"("+f.getType()+" "+f.getField()+"){this."+f.getField()+"="+f.getField()+";}\n");
			w.write("\n");
		}
		
		w.write("}");
	}
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	private static String firstUpperCase(String str){
		String fst=str.substring(0, 1).toUpperCase();
		String se=str.substring(1);
		return fst+se;
	}
	
	
	/**
	 * package com.gys.sm.fun.table.dao;

		import org.springframework.stereotype.Repository;
		
		import com.gys.sm.fun.table.bean.GysBean;
		import com.gys.sm.item.inte.ITable;
		
		@Repository
		public interface IGysDao extends ITable<GysBean> {
			
		}

	 * @param w
	 * @throws IOException
	 */
	private static void writeJavaDaoContent(BufferedWriter w,String daoPackage,String beanPackage,String tableName,List<FiledBean> fileds) throws IOException{
		w.write("package "+daoPackage+";\n");
		w.write("\n");
		w.write("import org.springframework.stereotype.Repository;\n");
		w.write("import "+ beanPackage+"."+firstUpperCase(tableName)+"Bean;\n");
		w.write("import "+CreateFile.class.getPackage().getName()+".ITable;\n");
		w.write("\n");
		w.write("@Repository \n");
		w.write("public interface I"+ firstUpperCase(tableName)+"Dao extends ITable<"+firstUpperCase(tableName)+"Bean>{\n");
		w.write("\n");
		w.write("}");
	}
	
	/**
	 * 创建mapper文件
	 * @param sourcefolder
	 * @param interDaoUrl
	 * @param mapperUrl
	 * @param c
	 * @throws IOException
	 */
	public static void createMapper(String sourcefolder,String interDaoUrl,String mapperUrl,Class c) throws IOException{
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=mapperUrl.replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(sourcefolder+"/"+url+"/"+c.getSimpleName()+"-mapper.xml"),format);
		Document document=mapperDocument(interDaoUrl,c);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	private static Document mapperDocument(String interDaoUrl,Class c){
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",interDaoUrl+".I"+c.getSimpleName()+"Dao");//com.gys.sm.fun.table.dao.IGysDao
		selectDocument(root,c);
		selectOne(root,c);
		selectCountDocument(root,c);
		updateDocument(root,c);
		//不获取主键
		insertDocument(root,c,false);
		//获取主键
		insertDocument(root,c,true);
		deleteDocument(root,c,false);
		deleteDocument(root,c,true);
		return document;
	}
	/**
	 * selectList
	 * @param root
	 * @param c
	 */
	private static void selectDocument(Element root,Class c){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectList");
		select.addAttribute("parameterType",c.getName());
		select.addAttribute("resultType", c.getName());
		select.addText("\n\t\t");
		String tableName=getTableName(c);
		Field[] fsArr=c.getDeclaredFields();
		StringBuffer fieldBf=new StringBuffer();
		boolean b=false;
		for(Field f:fsArr){
			if(b){
				fieldBf.append(",");
			}else{
				b=true;
			}
			fieldBf.append(f.getName());
		}
		
		select.addText("select "+fieldBf.toString()+" from "+tableName);
		Element where=select.addElement("where");
		where.addText("${where}");
		Element orderByIf=select.addElement("if");
		orderByIf.addAttribute("test","orderBy!=null");
		orderByIf.addText("order by ${orderBy}");
		Element limitIf=select.addElement("if");
		limitIf.addAttribute("test","limit==true");
		limitIf.addText("LIMIT #{pageStart},#{pageSize}");
	}
	
	/**
	 * selectOne
	 * @param root
	 * @param c
	 */
	private static void selectOne(Element root,Class c){
		Element select=root.addElement("select");
		select.addAttribute("id", "selectOne");
		select.addAttribute("parameterType",c.getName());
		select.addAttribute("resultType", c.getName());
		select.addText("\n\t\t");
		String tableName=getTableName(c);
		Field[] fsArr=c.getDeclaredFields();
		StringBuffer fieldBf=new StringBuffer();
		boolean b=false;
		for(Field f:fsArr){
			if(b){
				fieldBf.append(",");
			}else{
				b=true;
			}
			fieldBf.append(f.getName());
		}
		
		select.addText("select "+fieldBf.toString()+" from "+tableName);
		Element where=select.addElement("where");
		where.addText("${where}");
	}
	/**
	 * 获取count
	 * @param root
	 * @param c
	 */
	private static void selectCountDocument(Element root,Class c){
		Element select=root.addElement("select");
		select.addAttribute("id", "count");
		select.addAttribute("parameterType",c.getName());
		select.addAttribute("resultType", "int");
		select.addText("\n\t\t");
		String tableName=getTableName(c);
		
		select.addText("select count(*) from "+tableName);
		Element where=select.addElement("where");
		where.addText("${where}");
	}
	private static void updateDocument(Element root,Class c){
		Element update=root.addElement("update");
		update.addAttribute("id", "update");
		update.addAttribute("parameterType", c.getName());
		String tableName=getTableName(c);
		update.addText("\n\t\t");
		update.addText("update "+tableName);
		Element setIf=update.addElement("if");
		setIf.addAttribute("test", "set==null");
		Element setTag=setIf.addElement("set");
		Field[] fsArr=c.getDeclaredFields();
		StringBuffer fieldBf=new StringBuffer();
		for(Field f:fsArr){
			fieldBf.append("\n\t\t\t");
			fieldBf.append(f.getName());
			fieldBf.append("=");
			fieldBf.append("#{");
			fieldBf.append(f.getName());
			fieldBf.append("}");
			fieldBf.append(",");
		}
		setTag.addText(fieldBf.toString());
		Element setIf1=update.addElement("if");
		setIf1.addAttribute("test", "set!=null");
		Element setTag1=setIf1.addElement("set");
		setTag1.addText("${set}");
		Element whereTag=update.addElement("where");
		whereTag.addText("${where}");
	}
	private static void insertDocument(Element root,Class c,boolean getId){
		Field[] fsArr=c.getDeclaredFields();
		Element insert=root.addElement("insert");
		
		if(getId){
			insert.addAttribute("id", "insertGetId");
			insert.addAttribute("keyProperty", fsArr[0].getName());
			insert.addAttribute("useGeneratedKeys", "true");
		}else{
			insert.addAttribute("id", "insert");
		}
		insert.addAttribute("parameterType", c.getName());
		String tableName=getTableName(c);
		
		StringBuffer fieldBfK=new StringBuffer();
		StringBuffer fieldBfV=new StringBuffer();
		int len=fsArr.length-1;
		for(int i=0;i<=len;i++){
			fieldBfK.append(fsArr[i].getName());
			fieldBfV.append("#{");
			fieldBfV.append(fsArr[i].getName());
			fieldBfV.append("}");
			if(i!=len){
				fieldBfK.append(",");
				fieldBfV.append(",");
			}
		}
		
		insert.addText("\n\t\t");
		insert.addText("insert "+tableName);
		insert.addText("\n\t\t(");
		insert.addText(fieldBfK.toString());
		insert.addText(")");
		insert.addText("\n\t\t values");
		insert.addText("\n\t\t(");
		insert.addText(fieldBfV.toString());
		insert.addText(")");
		insert.addText("\r\t");
	}
	
	private static void deleteDocument(Element root,Class c,boolean byId){
		Element insert=root.addElement("delete");
		if(byId){
			insert.addAttribute("id", "deleteById");
			insert.addAttribute("parameterType","object");
		}else{
			insert.addAttribute("id", "delete");
			insert.addAttribute("parameterType", c.getName());
		}
		String tableName=getTableName(c);
		insert.addText("\n\t\t");
		insert.addText("DELETE FROM "+tableName);
		Element where=insert.addElement("where");
		if(byId){
			Field[] fsArr=c.getDeclaredFields();
			where.addText(fsArr[0].getName()+"=#{"+fsArr[0].getName()+"}");
		}else{
			where.addText("${where}");
		}
		
	}
	
	
	/**
	 * 根据实体类获取表明
	 * @param c
	 * @return
	 */
	private static String getTableName(Class c){
		String cName=c.getSimpleName();
		String tableName=cName.substring(0,cName.length()-4);
		return tableName.toLowerCase();
	}
}
