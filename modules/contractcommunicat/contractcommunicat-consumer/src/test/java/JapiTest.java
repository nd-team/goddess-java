import com.dounine.japi.JapiClient;
import com.dounine.japi.JapiClientStorage;
import com.dounine.japi.JapiClientTransfer;
import com.dounine.japi.core.IProject;
import com.dounine.japi.core.impl.ProjectImpl;

/**
 * @Author: [Jason]
 * @Date: [17-3-22 下午2:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class JapiTest {

    public static void main(String[] args) {
//        JapiClient.delete(true);
        JapiClient.setPrefixPath("/media/data4/jzx/goddess-java/");//路径前缀
        JapiClient.setpostfixPath("/src/main/java");

        JapiClient.setProjectJavaPath("modules/contractcommunicat/contractcommunicat-consumer");//主项目位置
        JapiClient.setActionReletivePath("com/bjike/goddess/contractcommunicat/action");//主项目action位置
        JapiClient.setIncludeProjectJavaPath(new String[]{//关联项目
                "modules/contractcommunicat/contractcommunicat-api",
                "modules/market/market-api",
                "modules/businessproject/businessproject-api",
                "modules/user/user-api",
                "modules/organize/organize-api",
                "modules/storage/storage-api",
                "common/common-api"
        });
//        JapiClient.saveHistory(false);
        JapiClient.setFlushServer(true);
        JapiClient.setIncludePackages(new String[]{"com.bjike.goddess"});//可以准确快速搜索

        IProject project = ProjectImpl.init();
        JapiClientStorage japiClientStorage = JapiClientStorage.getInstance();
        japiClientStorage.setProject(project);
        japiClientStorage.autoSaveToDisk();
        new JapiClientTransfer().autoTransfer(japiClientStorage);
    }
}
