package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUIB;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUIBTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;
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
 * 多项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectMultipleUISerImpl extends ServiceImpl<MultipleProjectMultipleUI, MultipleProjectMultipleUIDTO> implements MultipleProjectMultipleUISer {

    @Autowired
    private MultipleProjectMultipleUIBSer multipleProjectMultipleUIBSer;
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
     * 核对查看权限（部门级别）
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
     * 分页查询多项目多个界面
     *
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectMultipleUIBO> list(MultipleProjectMultipleUIDTO dto) throws SerException {
        checkPermission();

        List<MultipleProjectMultipleUI> entityA = super.findByCis(dto);
        List<MultipleProjectMultipleUIBO> listBO = BeanTransform.copyProperties(entityA, MultipleProjectMultipleUIBO.class);
        List<String> projectName = new ArrayList<>();
        List<String> interfaceSelect = new ArrayList<>();
        List<Double> profit = new ArrayList<>();
        if (listBO != null) {
            for (MultipleProjectMultipleUIBO aBO : listBO) {
                projectName.add(aBO.getProjectName());
                MultipleProjectMultipleUIBDTO bDto = new MultipleProjectMultipleUIBDTO();
                bDto.getConditions().add(Restrict.eq("multipleProjectMultipleUI.id", aBO.getId()));
                List<MultipleProjectMultipleUIB> entityB = multipleProjectMultipleUIBSer.findByCis(bDto);

                List<MultipleProjectMultipleUIBBO> listBBO = BeanTransform.copyProperties(entityB, MultipleProjectMultipleUIBBO.class);
                aBO.setMultipleProjectMultipleUIBBOS(listBBO);

                int sum = listBBO.stream().filter(bto -> null != bto).distinct().mapToInt(bto -> bto.getWorkload()).sum();


                for (MultipleProjectMultipleUIBBO multipleProjectMultipleUIBBO : listBBO) {
                    interfaceSelect.add(multipleProjectMultipleUIBBO.getInterfaceSelect().toString());
                    profit.add(multipleProjectMultipleUIBBO.getProfit());
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
                    for(MultipleProjectMultipleUIB multipleProjectMultipleUIB:entityB) {
                        Double constant = Double.valueOf(multipleProjectMultipleUIB.getWorkload() / sum);

                        StringBuilder sb = new StringBuilder();
                        sb.append(interfaceSelect).append("=").
                                append(result);
                        String contrast = sb.toString().replaceAll("\\[", "");
                        contrast = contrast.replaceAll("]", "");
                        contrast = contrast.replace(",", ":");

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(projectName).append("=").
                                append(result);

                        String contrasts = stringBuilder.toString().replaceAll("\\[", "");
                        contrasts = contrasts.replaceAll("]", "");
                        contrasts = contrasts.replace(",", ":");

                        aBO.setProjectProfitContrast(contrasts);
                        aBO.setInterfaceProfitContrast(contrast);
                        aBO.setProjectRatio((constant*100)+"%");
                    }
                }
            }
        }
        return listBO;
    }

    /**
     * 保存多项目多个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(MultipleProjectMultipleUITO to) throws SerException {
        checkPermission();//权限检测
        MultipleProjectMultipleUI entityA = BeanTransform.copyProperties(to, MultipleProjectMultipleUI.class, true);
        entityA.setCreateTime(LocalDateTime.now());
        super.save(entityA);
        List<MultipleProjectMultipleUIBTO> multipleProjectMultipleUIBTOS = to.getMultipleProjectMultipleUIBTOS();

        for (MultipleProjectMultipleUIBTO bTO : multipleProjectMultipleUIBTOS) {
            MultipleProjectMultipleUIB entityB = BeanTransform.copyProperties(bTO, MultipleProjectMultipleUIB.class, true);
            entityB.setMultipleProjectMultipleUI(entityA);
            entityB.setCreateTime(LocalDateTime.now());

//            List<String> interfaceSelect = new ArrayList<>();
//            List<Double> profit = new ArrayList<>();
//            interfaceSelect.add(bTO.getInterfaceSelect().toString());
//            profit.add(bTO.getProfit());
//            Double constant = Double.valueOf(bTO.getWorkload() / sum);
//
//            entityA.setProjectRatio(String.valueOf(constant));
//            List<Integer> numbers = new ArrayList<>();
//            if (profit != null && profit.size() > 0) {
//                for (Double number : profit) {
//                    numbers.add(Double.valueOf(number * 100).intValue());
//                }
//            }
//            Integer divisor = getMaxDivisor(numbers);
//
//            StringBuilder result = new StringBuilder();
//
//            for (Integer number : numbers) {
//                result.append(number / divisor).append(":");
//            }
//            result.setLength(result.length() - 1);
//
//            MultipleProjectMultipleUIDTO dto = new MultipleProjectMultipleUIDTO();
//            dto.getConditions().add(Restrict.eq("id", entityA.getId()));
//            List<MultipleProjectMultipleUI> multipleUIList = super.findByCis(dto);
//
            MultipleProjectMultipleUIBDTO dtoB = new MultipleProjectMultipleUIBDTO();
            dtoB.getConditions().add(Restrict.eq("multipleProjectMultipleUI.id", entityA.getId()));
            List<MultipleProjectMultipleUIB> multipleUIBS = multipleProjectMultipleUIBSer.findByCis(dtoB);
            for (MultipleProjectMultipleUIB multipleProjectMultipleUIB : multipleUIBS) {
                if (multipleProjectMultipleUIB.getInterfaceSelect().equals(bTO.getInterfaceSelect())) {
                    throw new SerException("界面不能有两个一样的对应同个项目名称");
                }
            }
//            for (MultipleProjectMultipleUI ui : multipleUIList) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(interfaceSelect).append("=").
//                        append(result);
//                String contrast = sb.toString().replaceAll("\\[", "");
//                contrast = contrast.replaceAll("]", "");
//                contrast = contrast.replace(",", ":");
//
//                ui.setInterfaceProfitContrast(contrast);
//            }

            multipleProjectMultipleUIBSer.save(entityB);
        }
    }

    /**
     * 更新多项目多个界面
     *
     * @param to 多项目多个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MultipleProjectMultipleUITO to) throws SerException {
        checkPermission();//权限检测
        MultipleProjectMultipleUI entityA = super.findById(to.getId());
        BeanTransform.copyProperties(to, entityA, true);
        //查询B表并删除
        MultipleProjectMultipleUIBDTO dto = new MultipleProjectMultipleUIBDTO();
        dto.getConditions().add(Restrict.eq("multipleProjectMultipleUIB.id", entityA.getId()));
        List<MultipleProjectMultipleUIB> bList = multipleProjectMultipleUIBSer.findByCis(dto);
        if (bList != null && bList.size() > 0) {
            multipleProjectMultipleUIBSer.remove(bList);
        }
        //修改B表
        List<MultipleProjectMultipleUIBTO> multipleProjectSingleUITOS = to.getMultipleProjectMultipleUIBTOS();
        if (multipleProjectSingleUITOS != null) {
            for (MultipleProjectMultipleUIBTO m : multipleProjectSingleUITOS) {
                MultipleProjectMultipleUIB entityB = BeanTransform.copyProperties(m, MultipleProjectMultipleUIB.class, true);
                entityB.setMultipleProjectMultipleUI(entityA);
                multipleProjectMultipleUIBSer.update(entityB);
            }
        }
    }


    /**
     * 根据id删除多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        multipleProjectMultipleUIBSer.remove(id);
        MultipleProjectMultipleUIBDTO bDto = new MultipleProjectMultipleUIBDTO();
        List<MultipleProjectMultipleUIB> bList = multipleProjectMultipleUIBSer.findByCis(bDto);
        List<MultipleProjectMultipleUI> aList = super.findAll();
        Set<String> aids = new HashSet<>();
        for (MultipleProjectMultipleUIB b : bList) {
            aids.add(b.getMultipleProjectMultipleUI().getId());
        }
        for (MultipleProjectMultipleUI a : aList) {
            if (!aids.contains(a.getId())) {
                super.remove(a.getId());
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
    public MultipleProjectMultipleUIBO getOne(String id) throws SerException {
        checkPermission();//权限检测
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        MultipleProjectMultipleUI projectBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(projectBasicInfo, MultipleProjectMultipleUIBO.class);
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

}