package generateTemplet;

import buildfile.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
        String author = cus.get("作者");
        String desc = cus.get("描述")+"业务接口";
        LocalDate date = LocalDate.now();

        StringBuilder sb = new StringBuilder("");

        //包描述
        sb.append( "/**\n" )
                .append(  " * "+desc+"\n" )
                .append(  " * Created by "+author+" on "+ date+".\n" )
                .append( " */\n");
        sb.append("package com.bjike.goddess."+packageName+".action."+packageName+";\n");

///home/ike/java/goddess-java/staffentry/staffentry-consumer/src/main/java/com/bjike/goddess/staffentry/action/staffentry/package-info.java
        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-consumer/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/action/"+packageName+"/")
                ;

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
