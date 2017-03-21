package generateTemplet;

import buildfile.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 15:49]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PackageInfoCreate {

    public static void createModel(Map<String, String> cus, List<Model> models,String createOrDelete) {

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        className = className.substring(className.lastIndexOf("/")+1,className.length());
        String author = cus.get("作者");
        String desc = cus.get("描述")+"业务接口";
        LocalDate date = LocalDate.now();
        String time = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));

        StringBuilder sb = new StringBuilder("");

        //包描述
        sb.append( "/**\n" )
                .append(  " * "+desc+"\n" )
                .append(  " * Created by "+author+" on "+ time+".\n" )
                .append( " */\n");
        sb.append("package com.bjike.goddess."+packageName+".action."+packageName);
        //相对路径
        String packageRelativePath = cus.get("类名");
        if(packageRelativePath.lastIndexOf("/") != -1 ){
            String temp = packageRelativePath.substring( 0,packageRelativePath.lastIndexOf("/")).replaceAll("/",".");
            if(!temp.trim().equals("")){
                sb.append("."+temp  );
            }
        }
        sb.append(";\n");

        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/modules/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-consumer/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/action/"+packageName+"/")
                ;

        //相对包路径
        String relativePath = cus.get("类名");
        if(relativePath.lastIndexOf("/") != -1){
            filePath.append(relativePath.substring( 0,relativePath.lastIndexOf("/"))+"/");
        }
        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            file .mkdirs();
        }
        filePath.append( "package-info.java" );
        file = new File( filePath.toString() );

        if( createOrDelete.equals("create")){
            if( !file.exists()){
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write( sb.toString() ,0 ,sb.toString().length());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(createOrDelete.equals("delete")){
            if(file.exists()){
                file.delete();
            }
        }
    }
}
