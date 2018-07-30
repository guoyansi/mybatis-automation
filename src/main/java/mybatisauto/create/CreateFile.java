package mybatisauto.create;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import mybatisauto.bean.AutoConfig;
import mybatisauto.bean.BaseInBean;
import mybatisauto.bean.BaseOutBean;
import mybatisauto.bean.FieldBean;
import mybatisauto.bean.TableBean;
import mybatisauto.db.DataBase;

public class CreateFile {
	
	private DataBase db;
	public CreateFile() {}

	public CreateFile(DataBase db) {
		this.db=db;
	}

	/**
	 * 创建javabean
	 * 
	 * @throws Exception
	 */
	public void createBean(AutoConfig config, TableBean table, List<FieldBean> fs,DataBase dbs) throws Exception {
		db.createBean(config, table, fs,dbs);
	}
	/**
	 * 创建javabean
	 * 
	 * @throws Exception
	 */
	public void createDao(AutoConfig config, TableBean table) throws Exception {
		db.createDao(config, table);
	}
	/**
	 * 创建javabean
	 * 
	 * @throws Exception
	 */
	public void createMapper(AutoConfig config, TableBean table, List<FieldBean> fs) throws Exception {
		db.createMapper(config, table, fs);
	}

	/**
	 * 创建mapper
	 * 
	 * @throws Exception
	 */
	/*public void createMapper() throws Exception{
		OutputFormat format = new OutputFormat("    ", true);
		//XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/t/mapper.xml"),format);
		String url=this.config.getMapperPackage().replace(".", "/");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(this.config.getSourceMapper()+"/"+url+"/"+this.table.getXmlMapperName()+this.config.getXmlMapperSuffix()+".xml"),format);
		Document document=DocumentHelper.createDocument();
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		Element root=document.addElement("mapper");
		root.addAttribute("namespace",this.config.getDaoPackage()+"."+this.table.getIdaoName());
		//selectList
		//selectListDocument(root);
		//selectOne
		//selectOneById
		//getCount
		//update
		//updateById
		//insert
		//insertGetId
		//delete
		//deleteById
		xmlWriter.write(document);
		xmlWriter.close();
	}*/
	//protected abstract void selectListDocument(Element root) throws Exception;
}
