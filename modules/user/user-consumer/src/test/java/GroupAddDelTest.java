import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.Application;
import com.bjike.goddess.user.vo.rbac.GroupVO;
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
public class GroupAddDelTest {

    private String token;
    private String id;
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", token);
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        ResponseEntity<ActResult> responseEntity = restTemplate.exchange("/group/v1/delete/" + id, HttpMethod.DELETE, formEntity, ActResult.class);
        Assert.assertEquals(responseEntity.getBody().getCode(), 0);
    }

    @Test
    public void addNormal() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", token);
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("name", "组six six six");
        multiValueMap.add("description", "描述");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        String result = restTemplate.postForObject("/group/v1/add", formEntity, String.class);
        ActResult rs = JSON.parseObject(result, ActResult.class);
        GroupVO groupVO = JSON.parseObject(rs.getData().toString(), GroupVO.class);
        id = groupVO.getId();
        Assert.assertEquals(rs.getCode(), 0);
    }

    @Test
    public void addAbnormal() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", token);
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("description", "描述");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        String result = restTemplate.postForObject("/group/v1/add", formEntity, String.class);
        ActResult rs = JSON.parseObject(result, ActResult.class);
        Assert.assertEquals(rs.getCode(), 2);
    }

    @Test
    public void editAbnormal() throws Exception {
        addNormal();
        HttpHeaders headers = new HttpHeaders();
        headers.set("userToken", token);
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("id", id);
        multiValueMap.add("name", "update组");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        ResponseEntity<ActResult> result = restTemplate.exchange("/group/v1/edit", HttpMethod.PUT, formEntity, ActResult.class);
        Assert.assertEquals(result.getBody().getCode(), 0);
    }

}
