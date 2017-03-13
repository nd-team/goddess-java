package buildfile;

import GenerateTemplet.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //获取输入文件路径
        String inputPath = System.getProperty("user.dir") + "/staffentry/staffentry-provider/src/test/java/buildfile/input.txt";
        System.err.println(inputPath);
        String fieldsString = null;
        List<String> lines = FileUtils.readLines(new File(inputPath),
                "utf-8");

        //创建model java 文件
        fieldsString= createDeails(lines);
        String deails[] = fieldsString.split(";");
        createField(deails);
        ModelCreate.createModel(CUS, models);

        //创建bo java 文件
//        BoCreate.createModel(CUS, models);
//
//        //创建dto java 文件
//        DtoCreate.createModel(CUS,models);
//
//        //创建to java文件
//        ToCreate.createModel(CUS,models);
//
//        //创建vo Java文件
//        VoCreate.createModel(CUS,models);
//
//        //创建api interface 文件
//        ApiCreate.createModel(CUS,models);
//
//        //创建ser interface 文件
//        ServiceCreate.createModel(CUS,models);
//        System.out.println(CUS.get("模块名")+"-api src创建成功----------");
//
//        //创建consumer action Java 文件
//        ActionCreate.createModel(CUS,models);
//        System.out.println(CUS.get("模块名")+"-consumer action创建成功----------");
//
//        //创建provider api
//        ProviderApiCreate.createModel(CUS,models);
//
//        //创建dao interface文件
//        DaoCreate.createModel(CUS,models);
//
//        //创建provider service java文件
//        ProviderServiceCreate.createModel(CUS,models);
//        System.out.println(CUS.get("模块名")+"-provider src创建成功----------");

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
                    String[]packageNames = packageName.split("\\.");
                    int length=packageNames.length;
                    if(0<length){
                        packageName="";
                        for(int j=0;j< length;j++){
                            packageName=packageName+"/"+packageNames[j];
                        }
                    }else {
                        packageName="/"+packageName;
                    }
//                    modelTmp.binding("package", packageName.replace('/', '.').substring(1));
                    break;
                case 1:
//                    modelTmp.binding("author", desc[i].split("=")[1]);
                    break;
                case 2:
                    className = desc[i].split("=")[1];
//                    modelTmp.binding("clazz", className);
//                    modelTmp.binding("lowClazz", ParseTmpUtil.getLowString(className));
                    break;
                case 3:
//                    modelTmp.binding("des", desc[i].split("=")[1]);
                    break;
                default:
                    break;
            }
        }

//        modelTmp.binding("path",ParseTmpUtil.jspPath); // 绑定jsp页面需引入js的路径

        return fieldsString.toString();
    }

    /**
     * 构建属性
     */
    private static  void createField(String[] deails){
        Model m = null;
        models.removeAll(models);
        for (String str : deails) {
            m = new Model();
            String type = str.split(" ")[0];
            String name = str.split(" ")[1];
            String[] annotation = str.split(" ");
            if(annotation.length>2){
                m.setAnnotation(annotation[2]);
            }
            m.setSwapCaseName(name.substring(0, 1).toUpperCase() + name.substring(1));
            m.setType(type);
            m.setFieldName(name);
            models.add(m);
        }
        System.out.println("");
//        modelTmp.binding("list", models);
    }
}
