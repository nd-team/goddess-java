package com.bjike.goddess.organize.action.organize;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.*;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.excel.PositionWorkDetailsImport2;
import com.bjike.goddess.organize.to.PositionWorkDetailsTO;
import com.bjike.goddess.organize.vo.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("positionworkdetails")
public class PositionWorkDetailsAction extends BaseFileAction {
    @Autowired
    private PositionWorkDetailsAPI positionWorkDetailsAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private AngleAPI angleAPI;
    @Autowired
    private DimensionAPI dimensionAPI;
    @Autowired
    private InstructionClassifyAPI instructionClassifyAPI;
    @Autowired
    private PositionInstructionAPI positionInstructionAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;

    /**
     * 添加岗位工作明细信息
     *
     * @param to 岗位工作明细传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PositionWorkDetailsTO to, BindingResult result) throws ActException {
        try {
            positionWorkDetailsAPI.save(to);
            return ActResult.initialize("SAVE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改岗位工作明细信息
     *
     * @param to 岗位工作明细传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PositionWorkDetailsTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            positionWorkDetailsAPI.update(to);
            return ActResult.initialize("UPDATE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位工作明细数据id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            positionWorkDetailsAPI.delete(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 岗位工作明细数据传输
     * @return class PositionWorkDetailsBO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PositionWorkDetailsDTO dto) throws ActException {
        try {
            return ActResult.initialize(positionWorkDetailsAPI.maps(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(PositionWorkDetailsDTO dto) throws ActException {
        try {
            return ActResult.initialize(positionWorkDetailsAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询岗位工作明细
     *
     * @param id 岗位工作明细数据id
     * @return class PositionWorkDetailsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailsAPI.findById(id), PositionWorkDetailsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取部门
     *
     * @version v1
     */
    @GetMapping("v1/getDepartment")
    public Result getDepartment() throws ActException {
        try {
            return ActResult.initialize(positionDetailUserAPI.getAllDepartment());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 获取岗位
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getPosition")
//    public Result getPosition() throws ActException {
//        try {
//            List<OpinionBO> opinionBOs = positionDetailAPI.findThawOpinion();
//            List<String> list = new ArrayList<>(0);
//            if (null != opinionBOs && opinionBOs.size() > 0) {
//                list = opinionBOs.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
//            }
//            return ActResult.initialize(list);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 获取角度
     *
     * @version v1
     */
    @GetMapping("v1/getAngle")
    public Result getAngle() throws ActException {
        try {
            List<AngleBO> angleBOs = angleAPI.findStatus();
            List<String> list = new ArrayList<>(0);
            if (null != angleBOs && angleBOs.size() > 0) {
                list = angleBOs.stream().map(AngleBO::getName).distinct().collect(Collectors.toList());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取维度
     *
     * @version v1
     */
    @GetMapping("v1/getDimension")
    public Result getDimension() throws ActException {
        try {
            List<DimensionBO> dimensionBOs = dimensionAPI.findStatus();
            List<String> list = new ArrayList<>(0);
            if (null != dimensionBOs && dimensionBOs.size() > 0) {
                list = dimensionBOs.stream().map(DimensionBO::getName).distinct().collect(Collectors.toList());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取分类
     *
     * @version v1
     */
    @GetMapping("v1/getClassification")
    public Result getClassification() throws ActException {
        try {
            List<InstructionClassifyBO> instructionClassifyBOs = instructionClassifyAPI.findStatus();
            List<String> list = new ArrayList<>(0);
            if (null != instructionClassifyBOs && instructionClassifyBOs.size() > 0) {
                list = instructionClassifyBOs.stream().map(InstructionClassifyBO::getName).distinct().collect(Collectors.toList());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目阶段
     *
     * @version v1
     */
    @GetMapping("v1/getProjectStage")
    public Result getProjectStage() throws ActException {
        try {
            List<InstructionClassifyBO> instructionClassifyBOs = instructionClassifyAPI.findStatus();
            List<String> list = new ArrayList<>(0);
            if (null != instructionClassifyBOs && instructionClassifyBOs.size() > 0) {
                list = instructionClassifyBOs.stream().map(InstructionClassifyBO::getName).distinct().collect(Collectors.toList());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取功能(流程)
     *
     * @version v1
     */
    @GetMapping("v1/getFunctions")
    public Result getFunctions() throws ActException {
        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://systematic.issp.bjike.com:8080/featurelist/v1/getFeatureName");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51654/featurelist/v1/getFeatureName");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActResult.initialize(list);
    }

    /**
     * 获取操作类型
     *
     * @version v1
     */
    @GetMapping("v1/findWorkPermission")
    public Result findWorkPermission() throws ActException {
        try {
            return ActResult.initialize(positionInstructionAPI.findWorkPermission());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取姓名（在岗人员）
     *
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getName() throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.findMainUser());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取代理人
     *
     * @version v1
     */
    @GetMapping("v1/findAgentUser")
    public Result findAgentUser() throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.findAgentUser());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取岗位
     *
     * @version v1
     */
    @GetMapping("v1/getPosition")
    public Result getPosition() throws ActException {
        try {
            List<String> list = positionUserDetailAPI.getPosition();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理周汇总
     *
     * @return class ManagerVO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(String startTime, String endTime) throws ActException {
        try {
            List<ManagerBO> managerBOList = positionWorkDetailsAPI.weekCollect(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(managerBOList, ManagerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理月汇总
     *
     * @return class ManagerVO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(String month) throws ActException {
        try {
            List<ManagerBO> managerBOList = positionWorkDetailsAPI.monthCollect(month);
            return ActResult.initialize(BeanTransform.copyProperties(managerBOList, ManagerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理累计汇总
     *
     * @return class ManagerVO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect() throws ActException {
        try {
            List<ManagerBO> managerBOList = positionWorkDetailsAPI.totalCollect();
            return ActResult.initialize(BeanTransform.copyProperties(managerBOList, ManagerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理日汇总柱状图
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String day, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = positionWorkDetailsAPI.figureShowDay(day);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理周汇总柱状图
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = positionWorkDetailsAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 组织结构管理月汇总柱状图
     *
     * @param month 月份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(String month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = positionWorkDetailsAPI.figureShowMonth(month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构管理累计汇总柱状图
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/all")
    public Result figureShowAll() throws ActException {
        try {
            OptionBO optionBO = positionWorkDetailsAPI.figureShowAll();
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织结构首页图形化
     *
     * @version v1
     */
    @GetMapping("v1/figureShow")
    public Result figureShow() throws ActException {
        try {
            OptionAnnularBO bo = positionWorkDetailsAPI.figureShow();
            return ActResult.initialize(BeanTransform.copyProperties(bo, OptionAnnularVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出岗位工作明细信息
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位工作明细信息.xlsx";
            super.writeOutFile(response, positionWorkDetailsAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<PositionWorkDetailsImport2> tos = ExcelUtil.mergeExcelToClazz(is, PositionWorkDetailsImport2.class, excel);
//            List<PositionWorkDetailsImport> tocs = new ArrayList<>();
//            Set<Integer> seqNum = new HashSet<>();
//            for (PositionWorkDetailsImport positionWorkDetailsImport:tos){
//                seqNum.add(positionWorkDetailsImport.getSeqNum());
//            }

            positionWorkDetailsAPI.importExcel(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板岗位工作明细
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位工作明细导入模板.xlsx";
            super.writeOutFile(response, positionWorkDetailsAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}