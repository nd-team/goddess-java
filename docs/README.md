##命名规范
---
##模块名规约
     modelName(模块名全部小写，应见名知意，尽量简单不宜冗长)
1.  **api(核心依赖代码)** 模块名-api : user-api

2.  **consumer(消费者)** 模块名-consumer : user-consumer

3.  **provider(生产者)** 模块名-provider : user-provider
    
 ##包名规约
 PackName(包名) : 包名全部小写，连续的单词只是简单地连接起来，
不使用下划线顶级域名.模块名.小模块名.各组件的目录名 `com.bjike.goddess.user.service`

1. - action(控制器) 以 **action** 结束 `com.bjike.goddess.user.action`

2. - service(业务逻辑) 以 **service** 结束 `com.bjike.goddess.user.service`

3. - dao(数据层) 以 **dao** 结束 `com.bjike.goddess.user.dao`

4. - entity(实体) 以 **entity** 结束 `com.bjike.goddess.user.entity`

5. - dto(数据传输) 以 **dto** 结束 `com.bjike.goddess.user.dto`

5. - vo(数据层展示传输) 以 **vo** `结束 com.bjike.goddess.user.vo`

## 类命名规约
  ClassName(类名) : 大写开头驼峰式，命名时应该使其简洁而又具有描述性 `UserDetail`

1. Service(业务逻辑层)
    - 抛出异常 **SerException**  `SerException`
    
    - 接口类以**API**结束 `UserDetailAPI`
    
    - 接口实现类以 **Ser** 结束 `UserDetailSer`

2. Dao（数据控制层）
    - 抛出异常 **RepException**  `RepException`
    
    - 接口类以大写**I** 开头 以**Rep**结束  `UserDetailRep` (无需实现类)

3. Test（测试用例）
    - 测试类以 **Test** 结束 `UserDetailTest`
    
4. Atc（控制器）
    - 控制器类以 **Atc** 结束 `UserAtc`
    
    - 抛出异常 **ActException**  `ActException`
    
    - 返回值统一使用 **Result**  `Result`

5. DTO（data transfer object 数据传输）
    - 数据传输类以 **DTO** 结束 `UserDTO`
    

6. BO（business object 业务数据传输）
    - 控制器类以 **BO** 结束 `UserBO`  
    
7. VO（view object 数据展示传输）    
    - 控制器类以 **VO** 结束 `UserVO` 
    
8. 抽象类命名使用 **Abstract** 或 **Base** 开头(如BaseDTO,BaseVO) ,异常类命名使用 **Exception** 结尾 ; 测试类
   命名以它要测试的类的名称开始,以 **Test** 结尾
   
9. 枚举类名建议带上 Enum 后缀,枚举成员名称需要全大写,单词间用下划线隔开(构造方法被默认强制是私有)
     
    
## 方法命名规约
 MethodName(方法名)：第一个单词应是动词，并且首字母小写，其它每个单词首字母大写 ,
    小写开头驼峰式`findByName()`， `openFile()`, `addAccount()`... 

1.   - 类的布尔型的判断方法一般要求方法名使用单词 **is** 做前缀 isPersistent() ）

2.   - Dao 层方法：只能以**save**（插入）,**remove**（删除）,**update**（更新）,**find**（查找）,**count**（统计）开头。

3.   - 其他层方法避免以这个5个单词开头，以免造成误解。

4.   - Service方法，根据方法的行为命名，只描述方法的意义，见名知意，避免命名上的冲突即可。

5.   - Web层方法最好是贴近web的语言，如**register**，**login**，**logout**等方法。

6.   - 接口类中的方法和属性不要加任何修饰符号 (public 也不要加 ) ,保持代码的简洁性,并加上有效的 **Javadoc** 注释。
     (Dao,Service方法)- 命名参考
        - 获取单个对象的方法用 **get** 做前缀。
        
        - 获取多个对象的方法用 **list** 做前缀。
        
        - 获取统计值的方法用 **count** 做前缀。
        
        - 插入的方法用 **save** 做前缀。
        
        - 修改的方法用 **update** 做前缀。

## 格式规约
1. -  **if** / **for** / **while** / **switch** / **do** 等保留字与左右括号之间都必须加空格。

   -  任何运算符左右必须加一个空格(运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号、三目运行符等)
   
   -  缩进采用 4 个空格(tab idea 已默认,其他编译器请另外设置)
   
   -  单行字符数限制不超过 120 个,超出需要换行,方法不超过50行以提高代码阅读性
   
   -  方法参数在定义和传入时,多个参数逗号后边必须加空格。

## OOP规约
1. 
   - 尽量使用JDK8的特性以做到简洁的处理业务(如时间或者集合的处理上都更为方便)
   
   - 所有的局部变量使用基本数据类型,所有的 **POJO** 类属性必须使用包装数据类型,RPC 方法的返回值和
        参数必须使用包装数据类型。（`Integer`、`Boolean`、`String`、`Long`、`Double`、`Float`、`Short`、`Character`）
        
   - 定义 **DO** / **DTO** / **VO** 等 **POJO** 类时,不要设定任何属性默认值。(避免数据混淆)
   
   - 构造方法里面禁止加入任何业务逻辑,如果有初始化逻辑,请放在 init 方法中。
   
   - **POJO** 类必须写 **toString** 方法。
   
   - 当一个类有多个构造方法,或者多个同名方法,这些方法应该按顺序放置在一起,便于阅读。
   
   - 关于行参,数量不能太多,一般不超过最多4~5个,超过一定数量,封装数据传输实体传输(**dto**)
   
   - 避免通过一个类的对象引用访问此类的静态变量或静态方法,无谓增加编译器解析成本,直接用类名来访问即可。
   
   - 所有的覆写方法,必须加**@ Override** 注解。
   
   - 相同参数类型,相同业务含义,才可以使用 **Java** 的可变参数,避免使用 **Object** 。
   
   - 对外暴露的接口签名,原则上不允许修改方法签名,避免对接口调用方产生影响。接口过时必须加**@ Deprecated** 注解,并清晰地说明采用的新接口或者新服务是什么。

   
2. - 约定变量:　　所谓约定变量，是指那些使用后即可抛弃的临时变量
        - 通常**i**、**j**、**k**、**m**和n代表整型变量；**c**、**d**和**e**代表字符型变量
        - 循环计数变量通常采用字母 **i**，**j**，**k** 或者 **count**。
        - 而数组应该总是用下面的方式来命名：`String[] names` 或 `Byte[] bytes`(,中括号是数组类型的一部分,数组定义如下: String[] args , 不能用 String args[] )

3. - 非常量字段：类属性名，参数名，局部变量名  首字母小写， 其它每个单词的首字母大写小写开头驼峰式`userDetail`
   - 类中布尔类型的变量,都不能加 is ,而数据库字段必须加 is _ 

4. - 杜绝完全不规范的缩写,避免望文不知义.(**AbstractClass** “缩写”命名成 `AbsClass`;**condition** “缩写”命名成 `condi` ,此类 随意缩写严重降低了代码的可阅读性。)
     除了能见名知意，命名简写尽量少用,且不允许出现中文及拼音以及JAVA中的关键字命名。
 
5. - **constantName**(常量名) ：全部大写，多个单词以下划线分开 力求语义表达完整清楚,不要嫌名字长。`CONSTANT_CASE`   

6. - 类内方法定义顺序依次是:公有方法或保护方法 > 私有方法 > getter / setter方法。
        (公有方法是类的调用者和维护者最关心的方法,首屏展示最好,不要增加业务逻辑,增加排查问题的难度)

7. - 循环体内,字符串的联接方式,使用 **StringBuilder** 的**append** 方法进行扩展。

8. - **final** 可提高程序响应效率,声明成 **final** 的情况:
      - 不需要重新赋值的变量,包括类属性、局部变量。
      
      - 对象参数前加 final ,表示不允许修改引用的指向。
      
      - 类方法确定不允许被重写。
      
9. -  类成员与方法访问控制从严(任何类、方法、参数、变量,严控访问范围。过宽泛的访问范围,不利于模块解耦。)

        - 工具类不允许有 **public** 或 **default** 构造方法。 
        
        - 类非 **static** 成员变量并且仅在本类使用,必须是 **private** 。
        
        - 类非 **static** 成员变量并且与子类共享,必须是 **protected** 。
        
        - 若是 **static** 成员变量,必须考虑是否为 **final** 。
        
        - 类成员方法只供类内部调用,必须是 **private** 。
        
        - 类成员方法只对继承类公开,那么限制为 **protected** 。
        
10. - 关于集合的使用

        - 集合初始化时,尽量指定集合初始值大小。
        
        - 使用集合转数组的方法,必须使用集合的 **toArray(T[] array)** ,传入的是类型完全一样的数组,大小就是 **list.size()** 。
        
        - 使用工具类 **Arrays.asList()** 把数组转换成集合时,不能使用其修改集合相关的方法,
             它的 **add**/ **remove** / **clear** 方法会抛出 **UnsupportedOperationException** 异常。
        `String[] str = new String[] { "a", "b" };
         List list = Arrays.asList(str);
         list.add("c"); 运行时异常(因为Arrays . asList() 体现的是适配器模式,只是转换接口后台的数据仍是数组)`
         
         - 不要在 **foreach** 循环里进行元素的 **remove** / **add** 操作。 **remove** 元素请使用 **Iterator** 方式,
               如果并发操作,需要对 **Iterator** 对象加锁。
               
         - 使用 **entrySet** 遍历 **Map** 类集合 **KV** ,而不是 **keySet** 方式进行遍历。(如果是 **JDK 8**,使用 **Map.foreach**方法。)
            `集合类 Key Value Super 说明`
            `Hashtable 不允许为 null 不允许为 null Dictionary 线程安全`
            `ConcurrentHashMap 不允许为 null 不允许为 null AbstractMap 分段锁技术`
            `TreeMap 不允许为 null 允许为 null AbstractMap 线程不安全`
            `HashMap 允许为 null 允许为 null AbstractMap 线程不安全`
            
         - 利用 **Set** 元素唯一的特性,可以快速对一个集合进行去重操作,避免使用 **List** 的 **contains** 方法进行遍历、对比、去重操作。 
             
## 控制语句
 1.   - **switch** 在一个 **switch** 块内,每个 **case** 要么通过 **break** / **return** 等来终止,要么注释说明程 序将继续执行到哪一个 **case** 为止 ; 
            在一个 **switch** 块内,都必须包含一个 **default** 语句并且 放在最后,即使它什么代码也没有。
            
      - 在 **if** / **else** / **for** / **while** / **do** 语句中必须使用大括号,即使只有一行代码,
            避免使用下面的形式:` if (condition) statements`;如果非得使用 **if()...else if()...else...** 方式表达逻辑,
           【强制】请勿超过 3 层,超过请使用状态设计模式   
 
## 异常
 1. - 不要捕获 Java 类库中定义的继承自 RuntimeException 的运行时异常类,如: IndexOutOfBoundsException /
        NullPointerException,这类异常由程序员预检查 来规避,保证程序健壮性。
    
    - 异常不要用来做流程控制,条件控制,因为异常的处理效率比条件分支低。    
   
    - 对大段代码进行 try - catch ,这是不负责任的表现。 catch 时请分清稳定代码和非稳  定代码,稳定代码指的是无论如何不会出错的代码。
          对于非稳定代码的 catch 尽可能进行区分 异常类型,再做对应的异常处理。    
   
    - 捕获异常是为了处理它,不要捕获了却什么都不处理而抛弃之,如果不想处理它,请 将该异常抛给它的调用者。最外层的业务使用者,必须处理异常,将其转化为用户可以理解的内容。
   
    - 不能在 finally 块中使用 return , finally 块中的 return 返回后方法结束执行,不会再执行 try 块中的 return 语句
       
     
   
       
 ## 其他
  1. - 在使用正则表达式时,利用好其预编译功能,可以有效加快正则匹配速度。`(不要在方法体内定义: Pattern pattern = Pattern . compile( 规则 );)`
 
  2. - 获取当前毫秒数 System . currentTimeMillis(); 而不是 new Date() . getTime();
 
  3. - 防止 NPE(NULL Pointer Exception) ,是程序员的基本修养,注意 NPE 产生的场景:
  
      - 远程调用返回对象,一律要求进行 NPE 判断。
  
      - 对于 Session 中获取的数据,建议 NPE 检查,避免空指针。
 
      - 级联调用 obj . getA() . getB() . getC(); 一连串调用,易产生 NPE 。
  
  4. - 在代码中使用“抛异常”还是“返回错误码”,对于公司外的 http / api 开放接口必须使用“错误码” ; 而应用内部推荐异常抛出 ;
          跨应用间 RPC 调用优先考虑使用 Result 方式,封 装 isSuccess 、“错误码”、“错误简短信息”
          
  5. - 避免出现重复的代码 (Don ’ t Repeat Yourself) ,即 DRY 原则。 (随意复制和粘贴代码,必然会导致代码的重复,在以后需要修改时,需要修改所有的副
        本,容易遗漏。必要时抽取共性方法,或者抽象公共类,甚至是共用模块。)
      
## 数据库 :
  1. - 表达是与否概念的字段,必须使用 is _ xxx 的方式命名,数据类型是 unsigned tinyint
  
     - 数据库字段名的修改代价很大,因为无法进行预发布,所以字段名称需要慎重考虑。
     
     - 表名、字段名必须使用小写字母或数字 ; 禁止出现数字开头,禁止两个下划线中间只出现数字。
     
     - 表名不使用复数名词。表名应该仅仅表示表里面的实体内容,不应该表示实体数量
     
     - 禁用保留字,如 desc 、 range 、 match 、 delayed 等,请参考 MySQL 官方保留字。
     
     - 小数类型为 decimal ,禁止使用 float 和 double 。(float 和 double 在存储的时候,存在精度损失的问题,很可能在值的比较时,得到不
           正确的结果)
     - 如果存储的字符串长度几乎相等,使用 char 定长字符串类型
     - varchar 是可变长字符串,不预先分配存储空间,长度不要超过 5000,如果存储长度大于此值,定义字段类型为 text ,
       独立出来一张表,用主键来对应,避免影响其它字段索 引效率。  
     - 表必备三字段: id , createTime , modifiedTime(dateTime类型) 。
     - 更新数据表记录时,必须同时更新记录对应的 modified 字段值为当前时间。   

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
 

 ## 注释规约
  1. - 类、类属性、类方法的注释必须使用 Javadoc 规范,使用/**内容*/格式,不得使用 // xxx 方式。
  
     - 所有的抽象方法 ( 包括接口中的方法 ) 必须要用 Javadoc 注释、除了返回值、参数、 异常说明外,还必须指出该方法做什么事情,实现什么功能
    
     - 所有的类都必须添加创建者信息。
     
     - 所有的枚举类型字段必须要有注释,说明每个数据项的用途。
    
     - 与其“半吊子”英文来注释,不如用中文注释把问题说清楚。专有名词与关键字保持 英文原文即可。
    
     - 注释掉的代码尽量要配合说明,而不是简单的注释掉。 
            `代码被注释掉有两种可能性:1 后续会恢复此段代码逻辑。2 永久不用。前者如果没有备注信息,
            难以知晓注释动机。后者建议直接删掉 ( 代码仓库保存了历史代码 ) `
    
     - 特殊注释标记 
        - 待办事宜 (TODO) : ( 标记人,标记时间, [ 预计处理时间 ])                           
        - 错误,不能工作 (FIXME) : ( 标记人,标记时间, [ 预计处理时间 ]) 
  
  2. - **settings.gradle**  模板配置
              file - setting - 输入 template 搜索 - 进入 file and code template -
              选择File Header 粘贴一下代码
           /**
            * @Author: [liguiqin]
            * @Date: [${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}]
            * @Description: [ ]
            * @Version: [1.0.0]
            * @Copy: [com.bjike]
            */
   
   类注释
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
         * @return class user
         * @throws SerException
         */
         User findByUsername(String username) throws SerException {
            return new User();
        }
    
    
    }