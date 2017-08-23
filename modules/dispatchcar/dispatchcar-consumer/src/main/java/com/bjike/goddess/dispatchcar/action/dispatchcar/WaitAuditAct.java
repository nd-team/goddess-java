package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等待审核
 *
 * @Author: [Jason]
 * @Date: [17-4-14 上午10:43]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("waitaudit")
public class WaitAuditAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DispatchCarInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.WAITAUDIT));
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看附件
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findfiles/{id}")
    public Result findFiles(@PathVariable String id,HttpServletRequest request) throws ActException {
        // 17-4-14 查看附件
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/siginmanage/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询出车记录
     *
     * @param id 出车记录id
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findDetail(id), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DispatchCarInfoDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.WAITAUDIT));
            Long count = dispatchCarInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金核对意见
     *
     * @param id             出车记录id
     * @param fundModuleSugg 意见
     * @version v1
     */
    @GetMapping("v1/fundsugg")
    public Result fundSugg(@RequestParam String id, @RequestParam String fundModuleSugg) throws ActException {
        try {
            dispatchCarInfoAPI.fundSugg(id, fundModuleSugg);
            return new ActResult("核对成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算核对意见
     *
     * @param id               出车记录id
     * @param budgetModuleSugg 意见
     * @version v1
     */
    @GetMapping("v1/budgetsugg")
    public Result budgetSugg(@RequestParam String id, @RequestParam String budgetModuleSugg) throws ActException {
        try {
            dispatchCarInfoAPI.budgetSugg(id, budgetModuleSugg);
            return new ActResult("核对成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目负责人或任务下发人审核
     *
     * @param id            出车记录id
     * @param principalSugg 审核意见
     * @param auditResult   审核结果
     * @version v1
     */
    @PostMapping("v1/principalsugg")
    public Result principalSugg(@RequestParam String id, @RequestParam String principalSugg, @RequestParam Boolean auditResult) throws ActException {
        try {
            dispatchCarInfoAPI.principalSugg(id, principalSugg, auditResult);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
