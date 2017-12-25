package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUI;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUIB;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUIBTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 多项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectSingleUISerImpl extends ServiceImpl<MultipleProjectSingleUI, MultipleProjectSingleUIDTO> implements MultipleProjectSingleUISer {

    @Autowired
    private MultipleProjectSingleUIBSer multipleProjectSingleUIBSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;


    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBBO> list(MultipleProjectSingleUIBDTO dto) throws SerException {
        checkPermission();
        List<MultipleProjectSingleUIB> listB = multipleProjectSingleUIBSer.findByPage(dto);
        List<MultipleProjectSingleUIBBO> listBBO = BeanTransform.copyProperties(listB, MultipleProjectSingleUIBBO.class);
        if (listBBO != null) {
            for (MultipleProjectSingleUIBBO bbo : listBBO) {
                MultipleProjectSingleUIDTO dtoA = new MultipleProjectSingleUIDTO();
                dtoA.getConditions().add(Restrict.eq("multipleProjectSingleUIB.id", bbo.getId()));
                List<MultipleProjectSingleUI> listA = super.findByCis(dtoA);
                List<MultipleProjectSingleUIBO> listABO = BeanTransform.copyProperties(listA, MultipleProjectSingleUIBO.class);

                bbo.setMultipleProjectSingleUIBOS(listABO);
            }
        }

        return listBBO;
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目单个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(MultipleProjectSingleUIBTO to) throws SerException {
        checkPermission();
        MultipleProjectSingleUIB entityB = BeanTransform.copyProperties(to, MultipleProjectSingleUIB.class, true);
        entityB.setCreateTime(LocalDateTime.now());
        entityB.setModifyTime(LocalDateTime.now());
        multipleProjectSingleUIBSer.save(entityB);
        List<String> projectName = new ArrayList<>();
        List<Double> profit = new ArrayList<>();

        List<MultipleProjectSingleUITO> multipleProjectSingleUITOS = to.getMultipleProjectSingleUITOS();
        Double sum = multipleProjectSingleUITOS.stream().filter(bto->null!=bto).distinct().mapToDouble(bto->bto.getWorkload()).sum();

        for (MultipleProjectSingleUITO m : multipleProjectSingleUITOS) {
            MultipleProjectSingleUI entityA = BeanTransform.copyProperties(m, MultipleProjectSingleUI.class, true);
            entityA.setMultipleProjectSingleUIB(entityB);
            entityA.setModifyTime(LocalDateTime.now());
            entityA.setCreateTime(LocalDateTime.now());
            projectName.add(m.getProjectName().toString());
            profit.add(m.getProfit());
            Double constant = m.getWorkload() / sum;
            entityA.setProjectRatio((constant * 100)+"%");
            List<Integer> numbers = new ArrayList<>();
            if (profit != null && profit.size() > 0) {
                for (Double number : profit) {
                    numbers.add(Double.valueOf(number * 100).intValue());
                }
            }
            Integer divisor = getMaxDivisor(numbers);

            StringBuilder result = new StringBuilder();

            for (Integer number : numbers) {
                result.append(number / divisor).append(":");
            }
            result.setLength(result.length() - 1);

            MultipleProjectSingleUIDTO dto = new MultipleProjectSingleUIDTO();
            dto.getConditions().add(Restrict.eq("multipleProjectSingleUIB.id", entityB.getId()));
            List<MultipleProjectSingleUI> multipleUIS = super.findByCis(dto);

            for (MultipleProjectSingleUI multipleProjectSingleUI : multipleUIS) {

                if (multipleProjectSingleUI.getProjectName().equals(m.getProjectName())) {
                    throw new SerException("项目名称不能有两个一样的对应同个界面");
                }
            }
            MultipleProjectSingleUIBDTO dtoB = new MultipleProjectSingleUIBDTO();
            dtoB.getConditions().add(Restrict.eq("id", entityB.getId()));
            List<MultipleProjectSingleUIB> multipleUIBS = multipleProjectSingleUIBSer.findByCis(dtoB);

            for (MultipleProjectSingleUIB ui : multipleUIBS) {
                StringBuilder sb = new StringBuilder();
                sb.append(projectName).append("=").
                        append(result);
                String contrast = sb.toString().replaceAll("\\[", "");
                contrast = contrast.replaceAll("]", "");
                contrast = contrast.replace(",", ":");

                ui.setProjectProfitContrast(contrast);

            }

            super.save(entityA);
        }
    }

    public static int getMaxDivisor(List<Integer> array) {
        int minN = getMin(array);
        for (int j = minN; j >= 2; j--) {
            int count = 0;
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % j == 0) {
                    count++;
                }
            }
            if (count == array.size()) {
                return j;
            }
        }
        return 1;// 无最大公约数
    }

    public static int getMin(List<Integer> a) {
        if (a.size() < 1) {
            return -1;
        }
        int min = a.get(0);
        for (int i = 0; i < a.size(); i++) {
            if (min > a.get(i)) {
                min = a.get(i);
            }
        }
        return min;
    }

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MultipleProjectSingleUIBTO to) throws SerException {
        checkPermission();
        MultipleProjectSingleUIB entityB = multipleProjectSingleUIBSer.findById(to.getId());
        BeanTransform.copyProperties(to, entityB, true);
        multipleProjectSingleUIBSer.update(entityB);
        //查询A表并删除
        MultipleProjectSingleUIDTO dto = new MultipleProjectSingleUIDTO();
        dto.getConditions().add(Restrict.eq("multipleProjectSingleUIB.id", entityB.getId()));
        List<MultipleProjectSingleUI> aList = super.findByCis(dto);

        if (aList != null && aList.size() > 0) {
            super.remove(aList);
        }
        List<String> projectName = new ArrayList<>();
        List<Double> profit = new ArrayList<>();
        //修改A表
        List<MultipleProjectSingleUITO> multipleProjectSingleUITOS = to.getMultipleProjectSingleUITOS();
        Double sum = multipleProjectSingleUITOS.stream().filter(bto->null!=bto).distinct().mapToDouble(bto->bto.getWorkload()).sum();

        if (multipleProjectSingleUITOS != null) {
            for (MultipleProjectSingleUITO m : multipleProjectSingleUITOS) {
                MultipleProjectSingleUI entityA = BeanTransform.copyProperties(m, MultipleProjectSingleUI.class, true);
                entityA.setMultipleProjectSingleUIB(entityB);

                projectName.add(m.getProjectName().toString());
                profit.add(m.getProfit());
                Double constant = m.getWorkload() / sum;
                entityA.setProjectRatio((constant * 100)+"%");

                List<Integer> numbers = new ArrayList<>();
                if (profit != null && profit.size() > 0) {
                    for (Double number : profit) {
                        numbers.add(Double.valueOf(number * 100).intValue());
                    }
                }
                Integer divisor = getMaxDivisor(numbers);

                StringBuilder result = new StringBuilder();

                for (Integer number : numbers) {
                    result.append(number / divisor).append(":");
                }
                result.setLength(result.length() - 1);
                for (MultipleProjectSingleUI multipleProjectSingleUI : aList) {
                    if (multipleProjectSingleUI.getProjectName().equals(m.getProjectName())) {
                        throw new SerException("项目名称不能有两个一样的对应同个界面");
                    }
                }
                MultipleProjectSingleUIBDTO dtoB = new MultipleProjectSingleUIBDTO();
                dtoB.getConditions().add(Restrict.eq("id", entityB.getId()));
                List<MultipleProjectSingleUIB> multipleUIBS = multipleProjectSingleUIBSer.findByCis(dtoB);
                for (MultipleProjectSingleUIB ui : multipleUIBS) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(projectName).append("=").
                            append(result);
                    String contrast = sb.toString().replaceAll("\\[", "");
                    contrast = contrast.replaceAll("]", "");
                    contrast = contrast.replace(",", ":");

                    ui.setProjectProfitContrast(contrast);
                }

                super.update(entityA);
            }
        }

    }

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
        MultipleProjectSingleUIDTO dtoA = new MultipleProjectSingleUIDTO();
        List<MultipleProjectSingleUI> entityAList = super.findByCis(dtoA);
        List<MultipleProjectSingleUIB> entityBList = multipleProjectSingleUIBSer.findAll();


        Set<String> aids = new HashSet<>();
        for (MultipleProjectSingleUI a : entityAList) {
            aids.add(a.getMultipleProjectSingleUIB().getId());
        }
        for (MultipleProjectSingleUIB b : entityBList) {
            if (!aids.contains(b.getId())) {
                multipleProjectSingleUIBSer.remove(b.getId());
            }
        }
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public MultipleProjectSingleUIBO getOne(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        MultipleProjectSingleUI projectBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(projectBasicInfo, MultipleProjectSingleUIBO.class);
    }
}