package buildfile;


import generateTemplet.*;
import generateTemplet.configBuild.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-11 16:39]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Build {

    private static Map<String, String> CUS = new HashMap<>(4);
    private static String className = null;
    private static String packageName = null;
    private static List<Model> models = new ArrayList<>(); //保存类属性列表

    static {
        CUS.put("模块名", "");//user
        CUS.put("类名", "");
        CUS.put("作者", "");
        CUS.put("描述", "");

    }

    public static void main(String[] args) throws IOException {

        //创建请运行此方法
        create("create");

        //删除请运行此方法
//        delete();

    }

    public static  void delete()throws IOException {
        create("delete");
    }

    public static void create(String createOrDelete) throws IOException {
        //获取输入文件路径
        String inputPath = System.getProperty("user.dir") + "/modules/template/template-provider/src/test/java/buildfile/input.txt";
        System.err.println(inputPath);
        String fieldsString = null;
        File file = new File(inputPath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> stream = reader.lines();
        List<String> lines = stream.collect(Collectors.toList());

        //创建model java 文件
        fieldsString = createDeails(lines);
        String deails[] = fieldsString.split(";");
        createField(deails);
        ModelCreate.createModel(CUS, models,createOrDelete);

        //创建bo java 文件
        BoCreate.createModel(CUS, models,createOrDelete);

        //创建dto java 文件
        DtoCreate.createModel(CUS, models,createOrDelete);

        //创建to java文件
        ToCreate.createModel(CUS, models,createOrDelete);

        //创建vo Java文件
        VoCreate.createModel(CUS, models,createOrDelete);

        //创建api interface 文件
        ApiCreate.createModel(CUS, models,createOrDelete);

        //创建ser interface 文件
        ServiceCreate.createModel(CUS, models,createOrDelete);

        //创建api build.gradle 配置文件
//        ApiBuildCreate.createConfig(CUS,createOrDelete);
        //创建api setting.gradle 配置文件
//        ApiSettingCreate.createConfig(CUS,createOrDelete);

        if( createOrDelete.equals("create")){
            System.out.println(CUS.get("模块名") + "-api src创建成功----------");
        }else if( createOrDelete.equals("delete")){
            System.out.println(CUS.get("模块名") + "-api src删除成功----------");
        }

        //创建consumer action Java 文件
//        ActionCreate.createModel(CUS, models,createOrDelete);

        //创建package-info.java
//        PackageInfoCreate.createModel(CUS,models,createOrDelete);
        //创建consumer build.gradle 文件
//        ConsumerBuildCreate.createConfig(CUS,createOrDelete);
        //创建consumer settings.gradle 文件
//        ConsumerSettingCreate.createConfig(CUS,createOrDelete);

        if( createOrDelete.equals("create")){
            System.out.println(CUS.get("模块名") + "-consumer src创建成功----------");
        }else if( createOrDelete.equals("delete")){
            System.out.println(CUS.get("模块名") + "-consumer src删除成功----------");
        }
        //创建provider api
        ProviderApiCreate.createModel(CUS, models,createOrDelete);

        //创建dao interface文件
        DaoCreate.createModel(CUS, models,createOrDelete);

        //创建provider service java文件
        ProviderServiceCreate.createModel(CUS, models,createOrDelete);

        //创建provider build.gradle 文件
//        ProviderBuildCreate.createConfig(CUS,createOrDelete);
        //创建provider settings.gradle 文件
//        ProviderSettingCreate.createConfig(CUS,createOrDelete);

        if( createOrDelete.equals("create")){
            System.out.println(CUS.get("模块名") + "-provider src创建成功----------");
        }else if( createOrDelete.equals("delete")){
            System.out.println(CUS.get("模块名") + "-provider src删除成功----------");
        }
    }

    /**
     * 构建类描述
     */
    private static String createDeails(List<String> lines) {
        for (String line : lines) {
            String[] ls = line.split(":");
            if (ls.length == 2) {
                switch (ls[0]) {
                    case "模块名":
                        CUS.put("模块名", line.split(":")[1]);
                        break;
                    case "类名":
                        CUS.put("类名", line.split(":")[1]);
                        break;
                    case "作者":
                        CUS.put("作者", line.split(":")[1]);
                        break;
                    case "描述":
                        CUS.put("描述", line.split(":")[1]);
                        break;
                    default:
                        break;
                }
            }
        }

        StringBuilder fieldsString = new StringBuilder();
        boolean fieldBegin = false;
        for (String line : lines) {
            if ("(".equals(line)) {
                fieldBegin = true;
                continue;
            }
            if (")".equals(line)) {
                break;
            }
            if (fieldBegin) {
                fieldsString.append(line);
            }
        }

        String strTmp = CUS.toString();
        String desc[] = strTmp.substring(1, strTmp.toString().length() - 1).split(",");
        for (int i = 0; i < desc.length; i++) {
            switch (i) {
                case 0:
                    packageName = desc[i].split("=")[1];
                    String[] packageNames = packageName.split("\\.");
                    int length = packageNames.length;
                    if (0 < length) {
                        packageName = "";
                        for (int j = 0; j < length; j++) {
                            packageName = packageName + "/" + packageNames[j];
                        }
                    } else {
                        packageName = "/" + packageName;
                    }
                    break;
                case 1:
                    break;
                case 2:
                    className = desc[i].split("=")[1];
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }

        return fieldsString.toString();
    }

    /**
     * 构建属性
     */
    private static void createField(String[] deails) {
        Model m = null;
        models.removeAll(models);
        for (String str : deails) {
            m = new Model();
            String type = str.split(" ")[0];
            String name = str.split(" ")[1];
            String[] annotation = str.split(" ");
            if (annotation.length > 2) {
                m.setAnnotation(annotation[2]);
            }
            m.setSwapCaseName(name.substring(0, 1).toUpperCase() + name.substring(1));
            m.setType(type);
            m.setFieldName(name);
            models.add(m);
        }
        System.out.println("");
    }
}
