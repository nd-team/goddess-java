package com.bjike.goddess.customer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ike on 17-5-12.
 */
public class PermissionConfig {
    private static List<String> descList = new ArrayList<>();
    private static List<String> idList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        generatePerssion();
    }

    //解析permission.propertity文档
    public static void generatePerssion() throws IOException {
        //获取输入文件路径
        String inputPath = System.getProperty("user.dir") + "/modules/customer/customer-provider/src/main/permission/permission.properties";
        System.err.println(inputPath);
        String fieldsString = null;
        File file = new File(inputPath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> stream = reader.lines();
        List<String> lines = stream.collect(Collectors.toList());
        System.out.println(lines);
        //[#描述, describe=客户添加权限, #对应id, id=1]
        System.out.println(lines.size());
        createDeails(lines);


    }

    /**
     * 构建类描述
     */
    private static void createDeails(List<String> lines) {
        descList = new ArrayList<>();
        idList = new ArrayList<>();
        int index = 0;
        StringBuffer descSb = new StringBuffer("");
        StringBuffer idSb = new StringBuffer("");
        for (int i=0;i< lines.size() ; i++) {
            String line = lines.get(i);
            if( line.startsWith("ids=")){
                index = -1;
            }
            if(!line.startsWith("#")){
//                System.out.println( line);

                if(!line.startsWith("ids=")&& index!=-1){
                    descSb.append( line.replace(";\\",";"));
                }
                if(index==-1){
                    idSb.append( line.replace(";\\",";"));
                }
            }

//            String[] ls = line.split("=");
//            if (ls.length == 2) {
//                switch (ls[0]) {
//                    case "describe=":
////                        CUS.put("模块名", line.split(":")[1]);
////                        descList
//                        break;
//                    case "ids=":
////                        CUS.put("类名", line.split(":")[1]);
//                        break;
//                    default:
//                        break;
//                }
//            }
        }
        System.out.println( descSb);
        System.out.println( idSb);
    }
}
