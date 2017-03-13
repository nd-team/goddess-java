package GenerateTemplet;

import buildfile.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-13 10:23]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProviderApiCreate {
    public static void createModel(Map<String, String> cus, List<Model> models) {

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        String author = cus.get("作者");
        String desc = cus.get("描述")+"业务接口实现";
        LocalDateTime date = LocalDateTime.now();
        int size = 0;
        if (models != null && models.size() > 0) {
            size = models.size(); //属性字段长度
        }


        StringBuilder sb = new StringBuilder("");
        sb.append("package com.bjike.goddess."+packageName+".api;\n\n")
        .append("import org.springframework.beans.factory.annotation.Autowired;\n")
        .append("import org.springframework.stereotype.Service;\n\n");

        //类描述
        sb.append( "/**\n")
                .append("* "+desc+"\n")
                .append("* @Author:\t\t\t[ "+author+" ]\n")
                .append("* @Date:\t\t\t[  "+date+" ]\n")
                .append("* @Description:\t[ "+desc+" ]\n")
                .append("* @Version:\t\t[ v1.0.0 ]\n")
                .append("* @Copy:   \t\t[ com.bjike ]\n")
                .append("*/\n");

        sb.append("@Service(\""+className.substring(0,1).toLowerCase()+className.substring(1)+"APiImpl\")\n");
        //类创建
        sb.append("public class "+className+"APiImpl implements "+className+"API  { \n\n");


        //拼接类完成
        sb.append(" }");

        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-provider/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/api/")
                ;

        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            System.out.println("//不存在");
            file .mkdir();
        }
        filePath.append( className+"APiImpl.java" );
        file = new File( filePath.toString() );
        try {
            FileWriter writer = new FileWriter(file);
            writer.write( sb.toString() ,0 ,sb.toString().length());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

