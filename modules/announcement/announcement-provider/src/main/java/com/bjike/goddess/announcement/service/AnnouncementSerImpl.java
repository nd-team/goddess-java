package com.bjike.goddess.announcement.service;

import com.bjike.goddess.announcement.bo.AnnouncementBO;
import com.bjike.goddess.announcement.dto.AnnouncementDTO;
import com.bjike.goddess.announcement.dto.AnnouncementUserDTO;
import com.bjike.goddess.announcement.entity.Announcement;
import com.bjike.goddess.announcement.entity.AnnouncementUser;
import com.bjike.goddess.announcement.enums.GuideAddrStatus;
import com.bjike.goddess.announcement.enums.Status;
import com.bjike.goddess.announcement.push.Push;
import com.bjike.goddess.announcement.to.AnnouncementTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.announcement.utils.ChineseCharToEn;
import com.bjike.goddess.announcement.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.push.api.PushUserInfoAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 公告业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "announcementSerCache")
@Service
public class AnnouncementSerImpl extends ServiceImpl<Announcement, AnnouncementDTO> implements AnnouncementSer {
    public static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementSerImpl.class);
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private AnnouncementUserSer announcementUserSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ClassSer classSer;
    @Autowired
    private PushUserInfoAPI pushUserInfoAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("announcement");
        obj.setDescribesion("公告");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = classSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("class");
        obj.setDescribesion("公告分类");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AnnouncementBO save(AnnouncementTO to) throws SerException {
        checkAddIdentity();
        Announcement entity = BeanTransform.copyProperties(to, Announcement.class, true);
        entity.setStatus(Status.NORMAL);
        String classify = entity.getClassify().substring(0, 2);   //获取前两位
        LocalDate now = LocalDate.now();
        String date = DateUtil.dateToString(now);
        date = date.replaceAll("-", "");
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.eq("classify", entity.getClassify()));
        dto.getConditions().add(Restrict.eq("publishDate", now));
        entity.setPublishDate(now);
        long num = count(dto) + 1;
        int length = String.valueOf(num).length();
        String number = null;
        switch (length) {
            case 1:
                number = "000" + num;
                break;
            case 2:
                number = "00" + num;
                break;
            case 3:
                number = "0" + num;
                break;
            case 4:
                number = num + "";
                break;
            default:
                throw new SerException("公告条数过多");
        }
        entity.setNumber(ChineseCharToEn.getAllFirstLetter(classify) + "-" + date + "-" + number);
        StringBuilder sb = new StringBuilder();
        String[] recipients = to.getRecipients();
        String[] mails = to.getMails();
        if (recipients != null) {
            String ticker = "公告消息推送";
            String title = to.getTitle();
            String text = null;
            if (to.getRequired()) {
                text = "您有一条标题为" + title + "的必读公告，请注意查收";
            } else {
                text = "您有一条标题为" + title + "的公告，请注意查收";
            }
            for (int i = 0; i < recipients.length; i++) {
                String deviceToken = null;
                try {
                    deviceToken = pushUserInfoAPI.getToken(recipients[i]);
                } catch (Exception e) {
                    if (e.getMessage().indexOf("Forbid consumer") != -1) {
                        LOGGER.error("push服务不可用!");
                    }
                }
                try {
                    if (null != deviceToken) {
                        if (44 == deviceToken.length()) {
                            Push.androidUnicast(deviceToken, ticker, title, text);   //安卓消息推送
                        } else if (64 == deviceToken.length()) {
                            Push.iosUnicast(deviceToken, text);   //ios消息推送
                        }
                    }
                } catch (Exception e) {
                    throw new SerException(e.getMessage());
                }
                if (i != recipients.length - 1) {
                    sb.append(recipients[i] + "、");
                } else {
                    sb.append(recipients[i]);
                }
            }
        }
        List<String> users = null;
        String title = entity.getTitle();
        String content = entity.getPublishContent();
        if (to.getAll()) {    //是否发送全部
            users = new ArrayList<>();
            List<UserBO> allUser = allUsers();
            for (int i = 0; i < allUser.size(); i++) {
                users.add(allUser.get(i).getUsername());
                if (i != allUser.size() - 1) {
                    sb.append(allUser.get(i).getUsername() + "、");
                } else {
                    sb.append(allUser.get(i).getUsername());
                }
            }
            if (entity.getSend()) {
                sendAll(title, content);
            }
        } else {
            if (entity.getSend()) {   //发邮件
                if (recipients != null) {
                    List<String> stringList = internalContactsAPI.getEmails(recipients);
                    if (null == stringList) {
                        stringList = new ArrayList<>();
                    }
                    if (mails != null) {
                        stringList.addAll(Arrays.asList(mails));
                    }
                    if (!stringList.isEmpty()) {
                        String[] strings = new String[stringList.size()];
                        strings = stringList.toArray(strings);
                        sendMail(strings, title, content);
                    }
                }
            }
        }
        entity.setRecipient(sb.toString());
        super.save(entity);
        List<String> stringList = new ArrayList<>();
        if (null != recipients) {
            stringList.addAll(Arrays.asList(recipients));
        }
        if (null != users && !users.isEmpty()) {     //全部发送情况
            stringList.addAll(users);
        }
        if (!stringList.isEmpty()) {
            List<String> list = stringList.stream().distinct().collect(Collectors.toList());
            saveUser(entity, list);
        }
        return BeanTransform.copyProperties(entity, AnnouncementBO.class);
    }

    private void saveUser(Announcement announcement, List<String> list) throws SerException {
        for (String recipient : list) {
            AnnouncementUser announcementUser = new AnnouncementUser();
            announcementUser.setName(recipient);
            announcementUser.setAnnouncementId(announcement.getId());
            announcementUserSer.save(announcementUser);
        }
    }

    private void sendMail(String[] receivers, String title, String content) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle(title);
        messageTO.setContent(content);
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }

    private void sendAll(String title, String content) throws SerException {
        List<CommonalityBO> allMailBOs = commonalityAPI.findThaw();
        List<String> emails = new ArrayList<>();
        if (null != allMailBOs) {
            emails = allMailBOs.stream().map(CommonalityBO::getEmail).distinct().collect(Collectors.toList());
        }
        List<UserBO> allUser = allUsers();
        List<String> names = allUser.stream().map(UserBO::getUsername).collect(Collectors.toList());
        if (!names.isEmpty()) {
            String[] strings = new String[names.size()];
            strings = names.toArray(strings);
            List<String> mails = internalContactsAPI.getEmails(strings);
            if (null != mails) {
                emails.addAll(mails);
            }
        }
        if (!emails.isEmpty()) {
            String[] receivers = new String[emails.size()];     //邮箱
            receivers = emails.toArray(receivers);
            sendMail(receivers, title, content);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(AnnouncementTO to) throws SerException {
        checkAddIdentity();
        Announcement entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String number = entity.getNumber();
        Status status = entity.getStatus();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Announcement.class, true);
        entity.setCreateTime(a);
        entity.setNumber(number);
        entity.setStatus(status);
        StringBuilder sb = new StringBuilder();
        String[] recipients = to.getRecipients();
        String[] mails = to.getMails();
        if (recipients != null) {
            for (int i = 0; i < recipients.length; i++) {
                if (i != recipients.length - 1) {
                    sb.append(recipients[i] + "、");
                } else {
                    sb.append(recipients[i]);
                }
            }
        }
        List<String> users = null;
        String title = entity.getTitle();
        String content = entity.getPublishContent();
        if (to.getAll()) {    //是否发送全部
            users = new ArrayList<>();
            List<UserBO> allUser = allUsers();
            for (int i = 0; i < allUser.size(); i++) {
                users.add(allUser.get(i).getUsername());
                if (i != allUser.size() - 1) {
                    sb.append(allUser.get(i).getUsername() + "、");
                } else {
                    sb.append(allUser.get(i).getUsername());
                }
            }
            if (entity.getSend()) {
                sendAll(title, content);
            }
        } else {
            if (entity.getSend()) {   //发邮件
                if (recipients != null) {
                    List<String> stringList = internalContactsAPI.getEmails(recipients);
                    if (null == stringList) {
                        stringList = new ArrayList<>();
                    }
                    if (mails != null) {
                        stringList.addAll(Arrays.asList(mails));
                    }
                    if (!stringList.isEmpty()) {
                        String[] strings = new String[stringList.size()];
                        strings = stringList.toArray(strings);
                        sendMail(strings, title, content);
                    }
                }
            }
        }
        entity.setRecipient(sb.toString());
        entity.setPublishDate(LocalDate.now());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        AnnouncementUserDTO userDTO = new AnnouncementUserDTO();
        userDTO.getConditions().add(Restrict.eq("announcementId", entity.getId()));
        List<AnnouncementUser> announcementUsers = announcementUserSer.findByCis(userDTO);
        if (!announcementUsers.isEmpty()) {
            announcementUserSer.remove(announcementUsers);
        }
        List<String> stringList = new ArrayList<>();
        if (null != recipients) {
            stringList.addAll(Arrays.asList(recipients));
        }
        if (null != users && !users.isEmpty()) {     //全部发送情况
            stringList.addAll(users);
        }
        if (!stringList.isEmpty()) {
            List<String> list = stringList.stream().distinct().collect(Collectors.toList());
            saveUser(entity, list);
        }
    }

//    @Transactional(rollbackFor = SerException.class)
//    public void checkAnnouncement(String[] users, Announcement announcement) throws SerException {
//        for (String s : users) {
//            String userId = userAPI.findByUsername(s).getId();
//            AnnouncementUser a = announcementUserSer.find(announcement.getId(), userId);
//            if (a == null) {
//                AnnouncementUser announcementUser = new AnnouncementUser();
//                announcementUser.setUserId(userId);
//                announcementUser.setAnnouncement(announcement);
//                announcementUserSer.save(announcementUser);
//            }
//        }
//    }

    @Override
    public List<AnnouncementBO> list(AnnouncementDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        String[] numbers = dto.getNumbers();
        String[] classifys = dto.getClassifys();
        String[] authors = dto.getAuthors();
        String publishDate = dto.getPublishDate();
        if (numbers != null) {
            dto.getConditions().add(Restrict.in("number", numbers));
        }
        if (classifys != null) {
            dto.getConditions().add(Restrict.in("classify", classifys));
        }
        if (authors != null) {
            dto.getConditions().add(Restrict.in("author", authors));
        }
        if (publishDate != null) {
            LocalDate time = null;
            try {
                time = DateUtil.parseDate(publishDate);
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            dto.getConditions().add(Restrict.eq("publishDate", time));
        }
        List<Announcement> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AnnouncementBO.class);
    }

    @Override
    public List<AnnouncementBO> currentList(AnnouncementDTO dto) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getConditions().add(Restrict.eq("status", Status.NORMAL));
        dto.getSorts().add("publishDate=desc");
        List<Announcement> like = super.findByCis(dto);
        List<AnnouncementBO> boList = new ArrayList<>();
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list = Arrays.asList(strings);
            if ((list != null) && (list.contains(userName))) {
                AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                Boolean b = announcementUserSer.check(a.getId(), userName);
                if (null != b && b) {
                    bo.setHaveRead(true);
                } else {
                    bo.setHaveRead(false);
                }
                boList.add(bo);
            }
        }
        int limit = dto.getLimit();
        int start = dto.getPage() * limit;
        List<AnnouncementBO> announcementBOS = boList.stream().skip(start).limit(limit).collect(Collectors.toList());
        return announcementBOS;
    }

    @Override
    public Long currentListCount(AnnouncementDTO dto) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getConditions().add(Restrict.eq("status", Status.NORMAL));
        List<Announcement> like = super.findByCis(dto);
        List<AnnouncementBO> boList = new ArrayList<>();
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list = Arrays.asList(strings);
            if ((list != null) && (list.contains(userName))) {
                AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                boList.add(bo);
            }
        }
        long num = boList.size();
        return num;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Announcement entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        AnnouncementUserDTO userDTO = new AnnouncementUserDTO();
        userDTO.getConditions().add(Restrict.eq("announcementId", entity.getId()));
        List<AnnouncementUser> announcementUsers = announcementUserSer.findByCis(userDTO);
        announcementUserSer.remove(announcementUsers);
        super.remove(id);
    }

    @Override
    public AnnouncementBO findByID(String id) throws SerException {
        Announcement entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, AnnouncementBO.class);
    }

    @Override
    public Long count(AnnouncementDTO dto) throws SerException {
        String classify = dto.getClassify();
        if (classify != null) {
            dto.getConditions().add(Restrict.eq("classify", classify));
        }
        String[] numbers = dto.getNumbers();
        String[] classifys = dto.getClassifys();
        String[] authors = dto.getAuthors();
        String publishDate = dto.getPublishDate();
        if (numbers != null) {
            dto.getConditions().add(Restrict.in("number", numbers));
        }
        if (classifys != null) {
            dto.getConditions().add(Restrict.in("classify", classifys));
        }
        if (authors != null) {
            dto.getConditions().add(Restrict.in("author", authors));
        }
        if (publishDate != null) {
            LocalDate time = null;
            try {
                time = DateUtil.parseDate(publishDate);
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            dto.getConditions().add(Restrict.eq("publishDate", time));
        }
        return super.count(dto);
    }

    @Override
    //获取当前用户必读且未读的公告
    public List<AnnouncementBO> requiredReads(AnnouncementDTO dto) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getConditions().add(Restrict.eq("required", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("status", Status.NORMAL));
        List<Announcement> like = super.findByCis(dto);
        List<AnnouncementBO> boList = new ArrayList<>();
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list = Arrays.asList(strings);
            if ((list != null) && (list.contains(userName))) {
                Boolean b = announcementUserSer.check(a.getId(), userName);
                if (null != b && !b) {
                    AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                    bo.setHaveRead(false);
                    boList.add(bo);
                }
            }
        }
        int limit = dto.getLimit();
        int start = dto.getPage() * limit;
        List<AnnouncementBO> announcementBOS = boList.stream().skip(start).limit(limit).collect(Collectors.toList());
        return announcementBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnouncementBO read(String id) throws SerException {
        UserBO userBO = userAPI.currentUser();
        announcementUserSer.read(id, userBO.getUsername());
        return findByID(id);
    }

    @Override
    //有必读返回true
    public boolean checkRequired() throws SerException {
        List<AnnouncementBO> list = requiredReads(new AnnouncementDTO());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    //查看该分类有无未读公告
    public boolean checkByClass(String classify) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.eq("classify", classify));
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getSorts().add("createTime=desc");
        List<Announcement> like = super.findByCis(dto);
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list1 = Arrays.asList(strings);
            if ((list1 != null) && (list1.contains(userName))) {
                Boolean b = announcementUserSer.check(a.getId(), userName);
                if (null != b && !b) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Set<String> allNumbers() throws SerException {
        List<Announcement> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Announcement a : list) {
            set.add(a.getNumber());
        }
        return set;
    }

    @Override
    public Set<String> allClass() throws SerException {
        List<Announcement> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Announcement a : list) {
            set.add(a.getClassify());
        }
        return set;
    }

    @Override
    public Set<String> allAuthors() throws SerException {
        List<Announcement> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Announcement a : list) {
            set.add(a.getAuthor());
        }
        return set;
    }

    @Override
    public List<AnnouncementBO> search(AnnouncementDTO dto) throws SerException {
        String[] numbers = dto.getNumbers();
        String[] classifys = dto.getClassifys();
        String[] authors = dto.getAuthors();
        String publishDate = dto.getPublishDate();
        if (numbers != null) {
            dto.getConditions().add(Restrict.in("number", numbers));
        }
        if (classifys != null) {
            dto.getConditions().add(Restrict.in("classify", classifys));
        }
        if (authors != null) {
            dto.getConditions().add(Restrict.in("author", authors));
        }
        if (publishDate != null) {
            LocalDate time = null;
            try {
                time = DateUtil.parseDate(publishDate);
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            dto.getConditions().add(Restrict.eq("publishDate", time));
        }
        return BeanTransform.copyProperties(super.findByCis(dto), AnnouncementBO.class);
    }

    @Override
    public List<AnnouncementBO> listByClass(String classify) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.eq("classify", classify));
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getSorts().add("createTime=desc");
        List<Announcement> like = super.findByCis(dto);
        List<AnnouncementBO> boList = new ArrayList<>();
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list1 = Arrays.asList(strings);
            if ((list1 != null) && (list1.contains(userName))) {
                Boolean b = announcementUserSer.check(a.getId(), userName);
                if (null != b) {
                    if (!b) {
                        AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                        bo.setHaveRead(false);
                        boList.add(bo);
                    } else {
                        AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                        bo.setHaveRead(true);
                        boList.add(bo);
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public AnnouncementBO addPerson(AnnouncementTO to) throws SerException {
        Announcement entity = BeanTransform.copyProperties(to, Announcement.class, true);
        entity.setStatus(Status.NORMAL);
        String classify = entity.getClassify().substring(0, 2);   //获取前两位
        LocalDate now = LocalDate.now();
        String date = DateUtil.dateToString(now);
        date = date.replaceAll("-", "");
        entity.setClassify("个人消息");
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.eq("classify", "个人消息"));
        dto.getConditions().add(Restrict.eq("publishDate", now));
        long num = count(dto) + 1;
        int length = String.valueOf(num).length();
        String number = null;
        switch (length) {
            case 1:
                number = "000" + num;
                break;
            case 2:
                number = "00" + num;
                break;
            case 3:
                number = "0" + num;
                break;
            case 4:
                number = num + "";
                break;
            default:
                throw new SerException("公告条数过多");
        }
        entity.setNumber(ChineseCharToEn.getAllFirstLetter(classify) + "-" + date + "-" + number);
        StringBuilder sb = new StringBuilder();
        String[] recipients = to.getRecipients();
        if (recipients != null) {
            for (int i = 0; i < recipients.length; i++) {
                if (i != recipients.length - 1) {
                    sb.append(recipients[i] + "、");
                } else {
                    sb.append(recipients[i]);
                }
            }
        }
        entity.setRecipient(sb.toString());
        super.save(entity);
        List<String> stringList = new ArrayList<>();
        if (null != recipients) {
            stringList.addAll(Arrays.asList(recipients));
        }
        if (!stringList.isEmpty()) {
            List<String> list = stringList.stream().distinct().collect(Collectors.toList());
            saveUser(entity, list);
        }
        return BeanTransform.copyProperties(entity, AnnouncementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        checkAddIdentity();
        Announcement entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setStatus(Status.FREEZE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void thaw(String id) throws SerException {
        checkAddIdentity();
        Announcement entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setStatus(Status.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public Long requiredCount() throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.like("recipient", userName));
        dto.getConditions().add(Restrict.eq("required", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("status", Status.NORMAL));
        List<Announcement> like = super.findByCis(dto);
        List<AnnouncementBO> boList = new ArrayList<>();
        for (Announcement a : like) {
            String recipient = a.getRecipient();
            String[] strings = recipient.split("、");
            List<String> list = Arrays.asList(strings);
            if ((list != null) && (list.contains(userName))) {
                Boolean b = announcementUserSer.check(a.getId(), userName);
                if (null != b && !b) {
                    AnnouncementBO bo = BeanTransform.copyProperties(a, AnnouncementBO.class);
                    bo.setHaveRead(false);
                    boList.add(bo);
                }
            }
        }
        long num = boList.size();
        return num;
    }

    @Override
    //获取所有未冻结用户
    public List<UserBO> allUsers() throws SerException {
        return positionDetailUserAPI.findUserListInOrgan();
    }


    @Override
    public List<AnnouncementBO> phoneList() throws SerException {
        String name = userAPI.currentUser().getUsername();
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.getConditions().add(Restrict.eq("status", Status.NORMAL));
        dto.getSorts().add("publishDate=desc");
        List<Announcement> list = super.findByCis(dto);
        List<Announcement> list1 = list.stream().filter(announcement -> announcement.getRecipient().contains(name)).collect(Collectors.toList());
        return BeanTransform.copyProperties(list1, AnnouncementBO.class);
    }
}