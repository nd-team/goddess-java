package com.bjike.goddess.announcement.action.announcement;

import com.bjike.goddess.announcement.api.AnnouncementAPI;
import com.bjike.goddess.announcement.bo.AnnouncementBO;
import com.bjike.goddess.announcement.dto.AnnouncementDTO;
import com.bjike.goddess.announcement.to.AnnouncementTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.announcement.vo.AnnouncementVO;
import com.bjike.goddess.announcement.vo.SonPermissionObject;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.vo.CommonalityVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 公告
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("announcement")
public class AnnouncementAct extends BaseFileAction {
    @Autowired
    private AnnouncementAPI announcementAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = announcementAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = announcementAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 公告数据传输
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AnnouncementDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AnnouncementBO> list = announcementAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 公告传输对象
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AnnouncementTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AnnouncementBO bo = announcementAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 公告id
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/announcement/{id}")
    public Result announcement(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AnnouncementBO bo = announcementAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 公告传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AnnouncementTO to, BindingResult result) throws ActException {
        try {
            announcementAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 公告id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            announcementAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数(可按分类查找该分类的总记录数)
     *
     * @param dto 公告数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AnnouncementDTO dto) throws ActException {
        try {
            return ActResult.initialize(announcementAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/announcement/announcement/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/announcement/announcement/" + id;
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
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前用户必读且未读的公告列表
     *
     * @param dto 公告数据传输
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/requiredReads")
    public Result requiredReads(AnnouncementDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AnnouncementBO> list = announcementAPI.requiredReads(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取公告
     *
     * @param id id
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/read/{id}")
    public Result read(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AnnouncementBO bo = announcementAPI.read(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有编号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allNumbers")
    public Result allNumbers() throws ActException {
        try {
            return ActResult.initialize(announcementAPI.allNumbers());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有分类
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allClass")
    public Result allClass() throws ActException {
        try {
            return ActResult.initialize(announcementAPI.allClass());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有作者
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allAuthors")
    public Result allAuthors() throws ActException {
        try {
            return ActResult.initialize(announcementAPI.allAuthors());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param dto 公告数据传输
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(AnnouncementDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AnnouncementBO> list = announcementAPI.search(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过分类查找
     *
     * @param classify 公告数据传输
     * @return class AnnouncementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listByClass/{classify}")
    public Result listByClass(@PathVariable String classify, HttpServletRequest request) throws ActException {
        try {
            List<AnnouncementBO> list = announcementAPI.listByClass(classify);
            return ActResult.initialize(BeanTransform.copyProperties(list, AnnouncementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            announcementAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            announcementAPI.thaw(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 必读且未读公告总条数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/requiredCount")
    public Result requiredCount() throws ActException {
        try {
            return ActResult.initialize(announcementAPI.requiredCount());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 查看有无必读公告
//     *
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/checkRequired")
//    public Result checkRequired() throws ActException {
//        try {
//            return ActResult.initialize(announcementAPI.checkRequired());
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 查看该分类有无未读公告
     *
     * @param classify 分类
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkByClass/{classify}")
    public Result checkByClass(@PathVariable String classify) throws ActException {
        try {
            return ActResult.initialize(announcementAPI.checkByClass(classify));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有公共邮箱
     *
     * @return class CommonalityVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findMails")
    public Result findMails(HttpServletRequest request) throws ActException {
        try {
            List<CommonalityBO> list = commonalityAPI.findThaw();
            return ActResult.initialize(BeanTransform.copyProperties(list, CommonalityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有未冻结用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allUsers")
    public Result allUsers(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = announcementAPI.allUsers();
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}