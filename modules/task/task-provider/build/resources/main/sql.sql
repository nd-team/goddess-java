select a.title,a.content,a.titleType  from taskallotment_customtitle a ,(
select a.* from(
select tc.title,tc.tasknode_id from taskallotment_project  p,
taskallotment_table t,taskallotment_tasknode tn,taskallotment_customtitle tc
  where p.id ='66db57eb-b595-45c2-b2ed-e876d3433ffa'
 and t.id in('a919024d-e68a-4f1e-8dca-c9b9130a2cfa','fff')
 and tn.table_id = t.id
 and tc.tasknode_id=tn.id group by tc.tasknode_id,title)a,
(
select title   from (select tc.title,tc.tasknode_id from taskallotment_project  p,
taskallotment_table t,taskallotment_tasknode tn,taskallotment_customtitle tc
  where p.id ='66db57eb-b595-45c2-b2ed-e876d3433ffa'
 and t.id in('a919024d-e68a-4f1e-8dca-c9b9130a2cfa','fff')
 and tn.table_id = t.id
 and tc.tasknode_id=tn.id group by tc.tasknode_id,title )a group by title having count(title)>1)b
  where
 a.title=b.title ) b where a.title=b.title and a.tasknode_id=b.tasknode_id