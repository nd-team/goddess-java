package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.user.enums.LoginStatus;
import com.bjike.goddess.user.enums.MemberType;
import com.bjike.goddess.user.enums.SexType;
import com.bjike.goddess.user.enums.UserType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String username; //登录用户名

    @Column(unique = true, length = 11, nullable = false)
    private String phone;//登录手机(注册验证手机)

    @Email
    private String email;//登录邮箱

    @Column(nullable = false)
    private String password; //登陆密码

    private String headSculpture;//头像

    @Column(unique = true)
    private String nickname; //昵称

    @Column(unique = true,nullable = false)
    private String employeeNumber;

    @Column(columnDefinition = "INT(1)")//指定数据库类型
    private SexType sex = SexType.NONE;//性别

    @Range(min = 0, max = 120)
    private Integer age;//年龄

    @Column(columnDefinition = "INT(1)", nullable = false)
    private MemberType memberType = MemberType.REGISTERED; //会员类型

    @Column(columnDefinition = "INT(1)", nullable = false)
    private UserType userType = UserType.CUSTOMER;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime accessTime = LocalDateTime.now(); //链接时间

    private LocalDateTime createTime ;//创建时间

    private Status status = Status.THAW;//用户状态

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") //所在部门
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;//所在用户组

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;//职位

    @Transient
    private LoginStatus loginStatus = LoginStatus.LOGINOUT; //记录用户登录状态


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
