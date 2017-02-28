##命名规范
---
##模块名规则：
     modelName(模块名全部小写，应见名知意，尽量简单不宜冗长)
1.  **api(核心依赖代码)** 模块名-api : user-api
1.  **consumer(消费者)** 模块名-consumer : user-consumer
1.  **provider(生产者)** 模块名-provider : user-provider
    
 ##包名规则：
 PackName(包名) : 包名全部小写，连续的单词只是简单地连接起来，
不使用下划线顶级域名.模块名.小模块名.各组件的目录名 `com.bjike.goddess.card.service`

1. - action(控制器) 以 **action** 结束 com.bjike.goddess.user.action
2. - com.bjike.goddess.card.service(业务逻辑) 以 **com.bjike.goddess.card.service** 结束 com.bjike.goddess.user.service
3. - dao(数据层)以 **dao** 结束 com.bjike.goddess.user.dao
4. - com.bjike.goddess.card.entity(实体)以 **com.bjike.goddess.card.entity** 结束 com.bjike.goddess.user.entity
5. - com.bjike.goddess.card.dto(数据传输)以 **com.bjike.goddess.card.dto** 结束 com.bjike.goddess.user.com.bjike.goddess.card.dto

## 类命名规则:
  ClassName(类名) : 大写开头驼峰式，命名时应该使其简洁而又具有描述性 `UserDetail`
  

1. Service(业务逻辑层)
    - 抛出异常 **SerException**  `SerException`
    - 接口类以**API**结束 `UserDetailAPI`
    - 接口实现类以 **Ser** 结束 `UserDetailSer`

2. Dao（数据控制层）
    - 抛出异常 **RepException**  `RepException`
    - 接口类以大写**I** 开头 以**Rep**结束  `UserDetailRep`
    - 接口实现类以 **RepImpl** 结束 `UserDetailRepImpl`

3. Test（测试用例）
    - 测试类以 **Test** 结束 `UserDetailTest`
4. Atc（控制器）
    - 控制器类以 **Atc** 结束 `UserAtc`
    - 抛出异常 **ActException**  `ActException`
    - 返回值统一使用 **ActResult**  `ActResult`

5. DTO（数据传输）
    - 控制器类以 **DTO** 结束 `UserDTO`
    

6. STO（业务数据传输）
    - 控制器类以 **STO** 结束 `UserSTO`    
    
## 方法命名规则:
 MethodName(方法名)：第一个单词应是动词，并且首字母小写，其它每个单词首字母大写 ,
    小写开头驼峰式`findByName()`， `openFile()`, `addAccount()`... 

1.   - 类的布尔型的判断方法一般要求方法名使用单词 **is** 做前缀 isPersistent() ）
2.   - Dao 层方法：只能以**save**（插入）,**remove**（删除）,**update**（更新）,**find**（查找）,**count**（统计）开头。
3.   - 其他层方法避免以这个5个单词开头，以免造成误解。
4.   - Service方法，根据方法的行为命名，只描述方法的意义，见名知意，避免命名上的冲突即可。
5.   - Web层方法最好是贴近web的语言，如**register**，**login**，**logout**等方法。

## 变量、常量命名规则:
 除了能见名知意，命名简写尽量少用,且不允许出现中文及拼音以及JAVA中的关键字命名。

1. - constantName(常量名) ：全部大写，多个单词以下划线分开`CONSTANT_CASE`

2. - 非常量字段：类属性名，参数名，局部变量名  首字母小写，
     其它每个单词的首字母大写小写开头驼峰式`userDetail`

3. - 约定变量:　　所谓约定变量，是指那些使用后即可抛弃的临时变量
     - 通常**i**、**j**、**k**、**m**和n代表整型变量；**c**、**d**和**e**代表字符型变量
     - 循环计数变量通常采用字母 **i**，**j**，**k** 或者 **count**。
     - 而数组应该总是用下面的方式来命名：`String[] names` 或 `Byte[] bytes`

4. 类属性及形参不允许或者尽量避免使用使用基础属性类型，应用包装类型（`Integer`、`Boolean`、`String`、`Long`、`Double`、`Float`、`Short`、`Character`）

5. 关于行参,数量不能太多,一般不超过最多4~5个,超过一定数量,封装数据传输实体传输(dto)
    
## 配置文件说明:

 全局应用配置文件，api,生产者,消费者
 1. 全局: **config.gradle** 应用使用到的jar包版本以及环境依赖版本配置
     - **settings.gradle** 各大小模块的导入
     - **.gitignore** git 提交忽略配置
     - **docs/Mapping.java** 对象关系映射配置demo
     
 2. api: 
  - **settings.gradle** 模块项目名配置
  - **build.gradle** 依赖jar包配置（任何模块api都依赖common-api）   
  
 3. consumer：
  - **settings.gradle** 模块项目名配置
  - **build.gradle** 依赖jar包配置（任何consumer模块都依赖common-consumer,且依赖自身模块api）  
  - **resources**:
    - **dubbo.properties** 配置应用发布名称,广播地址,端口及端口名,远程调用超时时间
    - **dubbo-config.xml** 配置所用到的业务接口注入
        
   
 4. provider: 
  - **settings.gradle**  模块项目名配置
  - **build.gradle** 依赖jar包配置（任何模块provider都依赖common-provider,common-jpa,且依赖自身模块api）   
  - **resources**:
      - **dubbo.properties** 配置应用发布名称,广播地址,端口及端口名,远程调用超时时间
      - **config.properties** 数据库连接等常用配置
      - **dubbo-config.xml** 配置所用到的业务以及业务依赖接口



## 注释规则:
 准确的描述类、方法、类属性、局部变量、常量的作用，简单明了，避免冗长却解释不清
  - **settings.gradle**  模板配置
        file - setting - 输入 template 搜索 - 进入 file and code template -
        选择File Header 粘贴一下代码
     /**
      * @Author: [liguiqin]
      * @Date: [${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}]
      * @Description: [ ]
      * @Version: [1.0.0]
      * @Copy: [com.bjike]
      */

    
    1：类注释
    /**
    *用户实体
    *
    * @Author: [liguiqin]
    * @Date: [2016-11-23 15:47]
    * @Description: []
    * @Version: [1.0.0]
    * @Copy: [com.bjike.goddess]
    */
    public class User(){
    
        2：类属性注释：
        /**
        *登录用户名
        */
        private String username; 
    
         /**
          *登录手机(注册验证手机)
          */
        private String phone;
    
        /**
         *登录邮箱
          */
        private String email;
    
        3：类方法注释：
        /**
         * 通过用户名查询用户
         * @param username 用户名
         * @return 用户
         * @throws SerException
         */
         User findByUsername(String username) throws SerException {
            return new User();
        }
    
    
    }