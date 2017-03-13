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
 * @Date: [2017-03-13 10:39]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProviderServiceCreate {
    public static void createModel(Map<String, String> cus, List<Model> models) {

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        String author = cus.get("作者");
        String desc = cus.get("描述")+"业务实现";
        LocalDateTime date = LocalDateTime.now();


        StringBuilder sb = new StringBuilder("");
        sb.append("package com.bjike.goddess."+packageName+".service;\n\n")
                .append("import com.bjike.goddess.common.jpa.service.ServiceImpl;\n")
                .append("import com.bjike.goddess."+packageName+".dto."+className+"DTO;\n")
                .append("import com.bjike.goddess."+packageName+".entity."+className+";\n")
                .append("import org.springframework.cache.annotation.CacheConfig;\n")
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


        sb.append("@CacheConfig(cacheNames =\""+className.substring(0,1).toLowerCase()+className.substring(1)+"SerCache\")\n")
        .append("@Service\n");
        //类创建
        sb.append("public class "+className+"Impl extends ServiceImpl<"+className+", "+className+"DTO> implements "+className+"Ser { \n\n");


        //拼接类完成
        sb.append(" }");

        //文件创建路径
        StringBuffer  filePath = new StringBuffer( System.getProperty("user.dir") + "/" )
                .append(packageName.toLowerCase()+"/")
                .append( packageName.toLowerCase()+"-provider/src/main/java/com/bjike/goddess/")
                .append( packageName.toLowerCase()+"/service/")
                ;

        //文件创建
        File file = new File( filePath.toString() );
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            System.out.println("//不存在");
            file .mkdir();
        }
        filePath.append( className+"Impl.java" );
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


