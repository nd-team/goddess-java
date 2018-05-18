select title , if(titleType=1 or titleType=2,sum(content) ,count(content))as value,tid,titleType from (
select a.title,a.content,a.titleType,tid  from 	goddess_taskallotment.taskallotment_customtitle a ,(
select a.* from(
select tc.title,tc.tasknode_id ,t.id as tid from goddess_taskallotment.taskallotment_project  p,
goddess_taskallotment.taskallotment_table t,goddess_taskallotment.taskallotment_tasknode tn,goddess_taskallotment.taskallotment_customtitle tc
  where p.id ='66db57eb-b595-45c2-b2ed-e876d3433ffa'
 and t.id in('a919024d-e68a-4f1e-8dca-c9b9130a2cfa','fff')
 and tn.table_id = t.id
 and tc.tasknode_id=tn.id group by tc.tasknode_id,title)a,
(
	select title as title,tid from(
	select tc.title,tc.tasknode_id ,t.id as tid from goddess_taskallotment.taskallotment_project  p,
	goddess_taskallotment.taskallotment_table t,goddess_taskallotment.taskallotment_tasknode tn,goddess_taskallotment.taskallotment_customtitle tc
  	where p.id ='66db57eb-b595-45c2-b2ed-e876d3433ffa'
 	and t.id in('a919024d-e68a-4f1e-8dca-c9b9130a2cfa','fff')
 	and tn.table_id = t.id
 	and tc.tasknode_id=tn.id group by tc.tasknode_id,tc.title,t.id )  a group by tid,title having count(title)>1
 )b
  where
 a.title=b.title ) b where a.title=b.title and a.tasknode_id=b.tasknode_id) a group by title,tid,titleType