/**
基本项目汇总
 */
SELECT *
FROM (
SELECT p.project AS outProject,p.innerProject, IF(p.status=0, TRUE, FALSE) AS isFinish
FROM taskallotment_project p, taskallotment_table t
WHERE p.id = 'debe88f5-97ec-4623-b8dc-70ad44003948' AND p.id = t.project_id) a, (
SELECT COUNT(*) AS workerCount
FROM(
SELECT EXECUTE
FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t
WHERE p.id = 'debe88f5-97ec-4623-b8dc-70ad44003948' AND p.id =t.project_id AND n.table_id=t.id  /** and tableId**/
GROUP BY EXECUTE)a) b
/**
自定义汇总
 */

SELECT a.*,b.content AS detail
FROM (
SELECT tc.title, COUNT(tc.content) AS COUNT
FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t,taskallotment_customtitle tc
WHERE p.id = 'debe88f5-97ec-4623-b8dc-70ad44003948' AND p.id =t.project_id AND n.table_id=t.id  /** and tableId**/  AND tc.tasknode_id = n.id AND tc.title IN ('姓名','搜索')
GROUP BY tc.title) a,(
SELECT title, GROUP_CONCAT(a.content) AS content
FROM(
SELECT tc.title, tc.content
FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t,taskallotment_customtitle tc
WHERE p.id = 'debe88f5-97ec-4623-b8dc-70ad44003948' AND p.id =t.project_id AND n.table_id=t.id /** and tableId**/ AND tc.tasknode_id = n.id AND tc.title IN ('姓名','搜索'))a
GROUP BY a.title) b
WHERE a.title=b.title

