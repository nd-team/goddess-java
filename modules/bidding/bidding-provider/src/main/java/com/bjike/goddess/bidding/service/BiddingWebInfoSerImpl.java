package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 招投标网站信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.331 ]
 * @Description: [ 招投标网站信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingWebInfoSerImpl extends ServiceImpl<BiddingWebInfo, BiddingWebInfoDTO> implements BiddingWebInfoSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        biddingWebInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(biddingWebInfoDTO);
        return count;
    }

    @Override
    public BiddingWebInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BiddingWebInfo biddingWebInfo = super.findById(id);
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Override
    public List<BiddingWebInfoBO> findListBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        checkSeeIdentity();
        biddingWebInfoDTO.getSorts().add("createTime=desc");
        List<BiddingWebInfo> biddingWebInfos = super.findByCis(biddingWebInfoDTO, true);
        List<BiddingWebInfoBO> biddingWebInfoBOS = BeanTransform.copyProperties(biddingWebInfos, BiddingWebInfoBO.class);
        return biddingWebInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO insertBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        checkAddIdentity();
        BiddingWebInfo biddingWebInfo = BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfo.class, true);
        biddingWebInfo.setId(biddingWebInfoTO.getId());
        super.save(biddingWebInfo);
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO editBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(biddingWebInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        BiddingWebInfo biddingWebInfo = super.findById(biddingWebInfoTO.getId());
        BeanTransform.copyProperties(biddingWebInfoTO, biddingWebInfo, true);
        biddingWebInfo.setModifyTime(LocalDateTime.now());
        super.update(biddingWebInfo);
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingWebInfo(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public void congel(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        try {
            BiddingWebInfo biddingWebInfo = super.findById(id);
            biddingWebInfo.setModifyTime(LocalDateTime.now());
            biddingWebInfo.setStatus(Status.CONGEAL);
            super.update(biddingWebInfo);
        } catch (SerException e) {
            throw new SerException("冻结出现错误，冻结失败" + e.getMessage());
        }
    }

    @Override
    public void thaw(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        try {
            BiddingWebInfo biddingWebInfo = super.findById(id);
            biddingWebInfo.setModifyTime(LocalDateTime.now());
            biddingWebInfo.setStatus(Status.THAW);
            super.update(biddingWebInfo);
        } catch (SerException e) {
            throw new SerException("解冻出现错误，解冻失败" + e.getMessage());
        }
    }

    @Override
    public List<String> getWebName() throws SerException {
        String[] fields = new String[]{"webName"};
        List<BiddingWebInfoBO> biddingWebInfoBOS = super.findBySql("select distinct webName from bidding_biddingwebinfo group by webName order by webName asc ", BiddingWebInfoBO.class, fields);

        List<String> webNameList = biddingWebInfoBOS.stream().map(BiddingWebInfoBO::getWebName)
                .filter(webName -> (webName != null || !"".equals(webName.trim()))).distinct().collect(Collectors.toList());


        return webNameList;
    }

    @Override
    public List<String> getUrl() throws SerException {
        String[] fields = new String[]{"url"};
        List<BiddingWebInfoBO> biddingWebInfoBOS = super.findBySql("select distinct url from bidding_biddingwebinfo group by url order by url asc ", BiddingWebInfoBO.class, fields);

        List<String> urlList = biddingWebInfoBOS.stream().map(BiddingWebInfoBO::getUrl)
                .filter(url -> (url != null || !"".equals(url.trim()))).distinct().collect(Collectors.toList());


        return urlList;
    }

    @Override
    public BiddingWebInfoBO getWebInfo(String webName) throws SerException {
        if (StringUtils.isNotBlank(webName)) {
            BiddingWebInfoDTO dto = new BiddingWebInfoDTO();
            dto.getConditions().add(Restrict.eq("webName", webName));
            BiddingWebInfo biddingWebInfo = super.findOne(dto);
            BiddingWebInfoBO bo = BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
            return bo;
        }
        return null;
    }

    @Override
    public String info(String url, String key) throws SerException {
        try {

            //创建连接
//            URL u = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//            //得到输入流 也就是读取源代码
//            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            while ((line = bf.readLine()) != null) {
//                sb.append(line);
//            }
            HttpGet request = new HttpGet(url);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            //进行源代码的匹配是否包含‘<’,'>'
            StringBuilder sb=new StringBuilder();
            sb.append(content);
            if (content.contains(key)) {
                if (content.contains("<script") || content.contains("/script>")) {
                    int index = sb.indexOf("<script");
                    int nextIndex = sb.indexOf("/script>");
                    while ((index >= 0 && nextIndex >= 0)&& (nextIndex > index)) {
                        if (index < nextIndex) {
                            sb.delete(index, nextIndex + 1);
                        }
                        index = sb.indexOf("<script");
                        nextIndex = sb.indexOf("/script>");
                    }
                }
                if (sb.toString().contains("<") || sb.toString().contains(">")) {
                    int index = sb.indexOf("<");
                    int nextIndex = sb.indexOf(">");
                    while ((index >= 0 && nextIndex >= 0) && (nextIndex > index)) {
                        //删除‘<’,'>'标签
                        sb.delete(index, nextIndex + 1);
                        index = sb.indexOf("<");
                        nextIndex = sb.indexOf(">");
                    }
                }
                Pattern pattern=Pattern.compile("[\u4E00-\u9FA5|\\！|\\,|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
                Matcher matcher=pattern.matcher(sb.toString());
                StringBuilder result=new StringBuilder();
                while (matcher.find()){
                    result.append(matcher.group());
                }
                return result.toString();
            }
            return null;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }
}