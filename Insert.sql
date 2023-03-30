insert into doctor values('1001','张三','男',45,'13333333333','主任医师','主任','xx医科大学','脑科','1203');
insert into doctor values('1002','李四','女',33,'13000000000','主治医师','科长','xx医科大学','妇产科','504');
insert into doctor values('1003','王五','男',60,'15555555555','主任医师','院长','xx医科大学','外科','1103');
insert into doctor values('1004','王六','男',60,'15555444555','专家','副院长','xx医科大学','内科','1103');

insert into patient values('2001','博文','男',23,'323112199901012314','13223453321','123456','胃疼',null);
insert into patient values('2002','小美','女',12,'321123201012212344','15423432111','abcd1234','发烧',null);
insert into patient values('2003','吴庆','男',82,'321123194001212125','15249877485','ABCabc123','高血压',null);

insert into drug values('3001','吗丁啉',32.59,'非处方药');
insert into drug values('3002','安痛定',0.86,'处方药');
insert into drug values('3003','贝那普利',12.30,'处方药');

insert into medicaltec values('4001','血常规',103.40,'医技楼203');
insert into medicaltec values('4002','B超',88.30,'医技楼430');
insert into medicaltec values('4003','CT',320.34,'医技楼103');

insert into appointment values(1,'2001','脑科','1001','2004-09-12 11:06:08','2004-09-12 11:06:08','诊室一',1,1);
insert into appointment values(2,'2002','妇产科','1002','2004-09-12 11:06:08','2004-09-12 11:06:08','诊室一',2,3);
insert into appointment values(3,'2003','内科','1004','2004-09-12 11:06:08','2004-09-12 11:06:08','诊室一',3,4);
insert into appointment values(4,'2003','外科','1003','2004-09-12 11:06:08','2004-09-12 11:06:08','诊室一',4,4);
insert into takemedicine values(1,'2001','3001',5,1);
insert into medicaltecexam values(1,'2001','4001','2004-09-12 11:06:08',1);