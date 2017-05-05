import com.dounine.japi.JapiClient;
import com.dounine.japi.JapiClientStorage;
import com.dounine.japi.JapiClientTransfer;
import com.dounine.japi.core.IProject;
import com.dounine.japi.core.impl.ProjectImpl;

import java.io.IOException;

public class JapiTest {

	public static void main(String[] args) throws IOException {
<<<<<<< HEAD
		JapiClient.setPrefixPath("/home/ike/github/goddess-java/");//路径前缀
=======
		JapiClient.setPrefixPath("/home/ike/goddess-java/");//路径前缀
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
		JapiClient.setpostfixPath("/src/main/java");
		JapiClient.setProjectJavaPath("modules/secure/secure-consumer");//主项目位置
		JapiClient.setActionReletivePath("com/bjike/goddess/secure/action");//主项目action位置
		JapiClient.setIncludeProjectJavaPath(new String[]{//关联项目
				"modules/secure/secure-api",
				"common/common-api",
				"modules/dimission-api",
				"modules/message-api"
		});
		JapiClient.setFlushServer(true);
		JapiClient.setIncludePackages(new String[]{"com.bjike.goddess"});//可以准确快速搜索
		IProject project = ProjectImpl.init();
		JapiClientStorage japiClientStorage = JapiClientStorage.getInstance();
		japiClientStorage.setProject(project);
		japiClientStorage.autoSaveToDisk();
		new JapiClientTransfer().autoTransfer(japiClientStorage);
	}

}
