import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-03 10:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class GroupTreeTest {

    private String token;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void dataInit() {//前期数据初始化
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("account", "IKE000002");
        multiValueMap.add("password", "123456");
        ActResult result = restTemplate.postForObject("/v1/login", multiValueMap, ActResult.class);
        Assert.assertEquals(result.getCode(), 0);
        token = String.valueOf(result.getData());
    }

    @After
    public void dataClear() {//测试数据清空
        Map<String, String> multiValueMap = new HashMap<>();//传值
        ActResult result = restTemplate.getForObject("/v1/sign-out/" + token, ActResult.class, multiValueMap);
        Assert.assertEquals(result.getCode(), 0);
    }

    @Test
    public void normal() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", token);
        HttpEntity formEntity = new HttpEntity(headers);
        ResponseEntity<ActResult> result = restTemplate.exchange("/group/v1/tree", HttpMethod.GET, formEntity, ActResult.class);
        Assert.assertEquals(result.getBody().getCode(), 0);
    }

    @Test
    public void abnormal() throws Exception {
        Map<String, String> multiValueMap = new HashMap<>();//传值
        ActResult result = restTemplate.getForObject("/group/v1/tree", ActResult.class, multiValueMap);
        Assert.assertEquals(result.getCode(), 403);
    }

}
