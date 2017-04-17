## RESTFul 规范
**URI规范**

1.不用大写

2.用中杠 - 不用下杠 _

3.参数列表要encode

4.URI中的名词表示资源集合，使用复数形式。

**资源集合 vs 单个资源**

URI表示资源的两种方式：资源集合、单个资源。

资源集合：

```
/zoos //所有动物园
/zoos/1/animals //id为1的动物园中的所有动物

```

单个资源：
```
/zoos/1 //id为1的动物园
/zoos/1;2;3 //id为1，2，3的动物园

```

**避免层级过深的URI**

```
/ 在url中表达层级，用于 按实体关联关系进行对象导航 ，一般根据id导航。

过深的导航容易导致url膨胀，不易维护，如 GET /zoos/1/areas/3/animals/4 ，尽量使用查询参数代替路径中的实体导航，如 GET /animals?zoo=1&area=3 ；
```

**HTTP方法**

通过标准HTTP方法对资源CRUD：

GET：查询
```
GET /zoos
GET /zoos/1
GET /zoos/1/employees
```

POST：创建单个资源。 POST一般向“资源集合”型uri发起

```
POST /animals  //新增动物
POST /zoos/1/employees //为id为1的动物园雇佣员工
```

PUT：更新单个资源（全量），客户端提供完整的更新后的资源。与之对应的是 PATCH，PATCH 负责部分更新，客户端提供要更新的那些字段。 PUT/PATCH一般向“单个资源”型uri发起
```
PUT /animals/1
PUT /zoos/1
```

DELETE：删除

```
DELETE /zoos/1/employees/2
DELETE /zoos/1/employees/2;4;5
DELETE /zoos/1/animals  //删除id为1的动物园内的所有动物
```
经常使用的、复杂的查询标签化，降低维护成本。

如：
```
GET /trades?status=closed&sort=created,desc
```

快捷方式：

```
GET /trades/recently-closed
```

**异步任务**

对耗时的异步任务，服务器端接受客户端传递的参数后，应返回创建成功的任务资源，其中包含了任务的执行状态。客户端可以轮训该任务获得最新的执行进度。

```
{"from":0,"to":1,"text":"abc"}

返回：
{"taskId":3,"createBy":"Anonymous","status":"running"}

GET /task/3
{"taskId":3,"createBy":"Anonymous","status":"success"}
```
如果任务的执行状态包括较多信息，可以把“执行状态”抽象成 组合资源 ，客户端查询该状态资源了解任务的执行情况。
```
提交任务：
POST /batch-publish-msg
[{"from":0,"to":1,"text":"abc"},{},{}...]

返回：
{"taskId":3,"createBy":"Anonymous"}

GET /task/3/status
{"progress":"50%","total":18,"success":8,"fail":1}
```