package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBBO;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUIB;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUIBTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
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
 * 单个项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectMultipleUISerImpl extends ServiceImpl<SingleProjectMultipleUI, SingleProjectMultipleUIDTO> implements SingleProjectMultipleUISer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SingleProjectMultipleUIBSer singleProjectMultipleUIBSer;

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
    private Boolean guildPermission() throws SerException {
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
     * 分页查询单个项目多个界面
     *
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectMultipleUIBO> list(SingleProjectMultipleUIDTO dto) throws SerException {
        checkPermission();
        List<SingleProjectMultipleUI> listA = super.findByPage(dto);

        List<SingleProjectMultipleUIBO> listBO = BeanTransform.copyProperties(listA, SingleProjectMultipleUIBO.class);
        if (listBO != null) {
            for (SingleProjectMultipleUIBO aBo : listBO) {

                SingleProjectMultipleUIBDTO bDto = new SingleProjectMultipleUIBDTO();
                bDto.getConditions().add(Restrict.eq("singleProjectMultipleUI.id", aBo.getId()));
                List<SingleProjectMultipleUIB> listB = singleProjectMultipleUIBSer.findByCis(bDto);


                List<SingleProjectMultipleUIBBO> uibboList = BeanTransform.copyProperties(listB, SingleProjectMultipleUIBBO.class);
                aBo.setSingleProjectMultipleUIBBOS(uibboList);

            }
        }
        return listBO;
    }

    /**
     * 单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(SingleProjectMultipleUITO to) throws SerException {
        checkPermission();
        SingleProjectMultipleUI entity = BeanTransform.copyProperties(to, SingleProjectMultipleUI.class, true);
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        List<SingleProjectMultipleUIBTO> uibto = to.getSingleProjectMultipleUIBTOS();

        List<String> interfaceSelect = new ArrayList<>();
        List<Double> profit = new ArrayList<>();

        for (SingleProjectMultipleUIBTO bto : uibto) {
            SingleProjectMultipleUIB uib = BeanTransform.copyProperties(bto, SingleProjectMultipleUIB.class, true);
            uib.setSingleProjectMultipleUI(entity);
            interfaceSelect.add(bto.getInterfaceSelect().toString());
            profit.add(bto.getProfit());

            uib.setCreateTime(LocalDateTime.now());
            uib.setModifyTime(LocalDateTime.now());


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

            SingleProjectMultipleUIDTO dto = new SingleProjectMultipleUIDTO();
            dto.getConditions().add(Restrict.eq("id", entity.getId()));
            List<SingleProjectMultipleUI> multipleUIList = super.findByCis(dto);

            SingleProjectMultipleUIBDTO dtoB = new SingleProjectMultipleUIBDTO();
            dtoB.getConditions().add(Restrict.eq("singleProjectMultipleUI.id", entity.getId()));
            List<SingleProjectMultipleUIB> multipleUIBS = singleProjectMultipleUIBSer.findByCis(dtoB);
            for (SingleProjectMultipleUIB singleProjectMultipleUIB : multipleUIBS) {
                if (singleProjectMultipleUIB.getInterfaceSelect().equals(bto.getInterfaceSelect())) {
                    throw new SerException("界面不能有两个一样的对应同个项目名称");
                }
            }
            for (SingleProjectMultipleUI ui : multipleUIList) {
                StringBuilder sb = new StringBuilder();
                sb.append(interfaceSelect).append("=").
                        append(result);
                String contrast = sb.toString().replaceAll("\\[", "");
                contrast = contrast.replaceAll("]", "");
                contrast = contrast.replace(",", ":");

                ui.setInterfaceProfitContrast(contrast);
            }


            singleProjectMultipleUIBSer.save(uib);
        }
    }


//    public static void main(String[] args) {
//
//        List<Double> profit = new ArrayList<>();
//        profit.add(1d);profit.add(0.55d);profit.add(0d);
//
//        List<Integer> numbers = new ArrayList<>(profit.size());
//        for (Double number : profit){
//            numbers.add(Double.valueOf(number * 100).intValue());
//        }
//
//
//        Integer divisor = getMaxDivisor(numbers);
//
//        System.out.println(divisor);
//
//        StringBuilder result = new StringBuilder();
//
//        for(Integer number : numbers){
//            result.append(number/divisor).append(":");
//        }
//        result.setLength(result.length()-1);
//        System.out.println(result.toString());
//
//
//    }


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
     * 更新单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(SingleProjectMultipleUITO to) throws SerException {
        checkPermission();
        SingleProjectMultipleUI ui = super.findById(to.getId());
        BeanTransform.copyProperties(to, ui, true);
        super.update(ui);
        //查询B表并删除
        SingleProjectMultipleUIBDTO dto = new SingleProjectMultipleUIBDTO();
        dto.getConditions().add(Restrict.eq("singleProjectMultipleUI.id", ui.getId()));
        List<SingleProjectMultipleUIB> bList = singleProjectMultipleUIBSer.findByCis(dto);
        if (bList != null && bList.size() > 0) {
            singleProjectMultipleUIBSer.remove(bList);
        }

        List<String> interfaceSelect = new ArrayList<>();
        List<Double> profit = new ArrayList<>();
        //修改B表
        List<SingleProjectMultipleUIBTO> uibtoList = to.getSingleProjectMultipleUIBTOS();
        if (uibtoList != null) {
            for (SingleProjectMultipleUIBTO bto : uibtoList) {
                SingleProjectMultipleUIB uib = BeanTransform.copyProperties(bto, SingleProjectMultipleUIB.class, true);
                uib.setSingleProjectMultipleUI(ui);
                interfaceSelect.add(bto.getInterfaceSelect().toString());
                profit.add(bto.getProfit());

                uib.setModifyTime(LocalDateTime.now());



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
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }

                SingleProjectMultipleUIDTO dtoA = new SingleProjectMultipleUIDTO();
                dto.getConditions().add(Restrict.eq("id", ui.getId()));
                List<SingleProjectMultipleUI> multipleUIList = super.findByCis(dtoA);

                SingleProjectMultipleUIBDTO dtoB = new SingleProjectMultipleUIBDTO();
                dto.getConditions().add(Restrict.eq("singleProjectMultipleUI.id", ui.getId()));
                List<SingleProjectMultipleUIB> listB = singleProjectMultipleUIBSer.findByCis(dtoB);
                for (SingleProjectMultipleUIB singleProjectMultipleUIB : listB) {
                    if (singleProjectMultipleUIB.getInterfaceSelect().equals(bto.getInterfaceSelect())) {
                        throw new SerException("界面不能有两个一样的对应同个项目名称");
                    }
                }
                for (SingleProjectMultipleUI uiA : multipleUIList) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(interfaceSelect).append("=").
                            append(result);
                    String contrast = sb.toString().replaceAll("\\[", "");
                    contrast = contrast.replaceAll("]", "");
                    contrast = contrast.replace(",", ":");

                    uiA.setInterfaceProfitContrast(contrast);
                }


                singleProjectMultipleUIBSer.update(uib);
            }

        }


    }


    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        singleProjectMultipleUIBSer.remove(id);
        SingleProjectMultipleUIBDTO bDto = new SingleProjectMultipleUIBDTO();
        List<SingleProjectMultipleUIB> bList = singleProjectMultipleUIBSer.findByCis(bDto);
        List<SingleProjectMultipleUI> aList = super.findAll();
        Set<String> aids = new HashSet<>();
        for (SingleProjectMultipleUIB b : bList) {
            aids.add(b.getSingleProjectMultipleUI().getId());
        }
        for (SingleProjectMultipleUI a : aList) {
            if (!aids.contains(a.getId())) {
                super.remove(a.getId());
            }
        }
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guildPermission();
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
                flag = guildPermission();
                break;
            case ADD:
                flag = guildPermission();
                break;
            case EDIT:
                flag = guildPermission();
                break;
            case DELETE:
                flag = guildPermission();
                break;
            case CONGEL:
                flag = guildPermission();
                break;
            case THAW:
                flag = guildPermission();
                break;
            case COLLECT:
                flag = guildPermission();
                break;
            case SEE:
                flag = guildPermission();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public SingleProjectMultipleUIBO getOne(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        SingleProjectMultipleUI projectBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(projectBasicInfo, SingleProjectMultipleUIBO.class);
    }
}