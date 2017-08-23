import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.Application;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.vo.rbac.GroupVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * @Author: [liguiqin]
 * @Date: [2017-06-08 17:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UpoladTest {
    private String token;
    private String path;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void dataInit() {//前期数据初始化
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("account", "lgq");
        multiValueMap.add("password", "123456");
        ActResult result = restTemplate.postForObject("/v1/login", multiValueMap, ActResult.class);
        Assert.assertEquals(result.getCode(), 0);
        token = String.valueOf(result.getData());
    }

    @Test
    public void normal() {
        Resource resource = new FileSystemResource("/home/lgq/user.xlsx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("storageToken", token);
        multiValueMap.add("files", resource);
        multiValueMap.add("path", "/test");
        ActResult result = restTemplate.postForObject("/file/v1/upload", multiValueMap, ActResult.class);
        String str = result.getData().toString();
        path =StringUtils.substringAfter(str,"path=").split(",")[0];
        Assert.assertEquals(result.getCode(), 0);
    }



    @Test
    public void abnormal() throws Exception {
        Resource resource = new FileSystemResource("/home/lgq/user.xlsx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("storageToken", token);
        multiValueMap.add("files", resource);
        ActResult result = restTemplate.postForObject("/file/v1/upload", multiValueMap, ActResult.class);
        Assert.assertEquals(result.getCode(), 2);
    }

    @Test
    public void dataClear() {//测试数据清空
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("path", "/");
        multiValueMap.add("storageToken", "ass");
        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", "1212");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        ResponseEntity<ActResult> responseEntity = restTemplate.exchange("/file/v1/delete",HttpMethod.DELETE, formEntity, ActResult.class);
        Assert.assertEquals(responseEntity.getBody().getCode(), 0);
    }


}
