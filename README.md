<https://149.104.20.115:10659/HospitalTest/>
# Hospital-Queuing
A school assignment(experiment) for JSP, a simple hospital queuing Web system made by pure JSP,HTML,CSS,Javascript without any framework.

# 医院排队叫号系统
学校去年的Web、软件工程、数据库课程群大作业，医院排队叫号系统。

需求：系统应用于医院各门诊科室，可有效地解决病人就诊/检查/取药时排队无序、医生工作量不平衡、就诊环境嘈杂等问题。主要功能：首先从 HIS 接收相应患者单据（挂号单、医技检查单或取药单），根据患者的签到情况、医生的排班信息以及患者优先级信息生成排队队列，从而可以实现医生叫号、选呼、重呼、过号等队列业务操作。此外，系统还需要提供相应信息的查询、统计及分析功能。

实际只实现了一部分。

# ⚠上古模式开发⚠
未使用任何框架，前后端不分离，前端纯原生HTML+CSS+Javascript，后端JSP，Servlets，经典MVC模式，数据库默认H2，原生JDBC连接，未采用连接池，有一部分请求用Ajax。

初学Web成果，十分简陋，连界面都是纯HTML手撸，一个人手撸了半个月，还是不太完善，放在这就当纪念一下了，有空可能还会来完善一下。

数据库配置在`/src/main/java/db/ConnBean.java（H2）`和`MsSQLConnBean.java（SQL Server）` 默认数据库为H2，需要在`D:\h2db2`目录下配置好H2数据库，导入初始化SQL（Database.sql和Insert.sql），或者可在`ConnBean.java`中`private String database="D:/h2db2/hospital";`处自行更改路径

编码从Eclipse默认的GBK改为了UTF-8

管理员密钥为服务器每次启动时随机生成，访问`RandomAdminKey.jsp`获取

部署必须采用Tomcat 10及以上（包名已经更改为jakarta，除非你手动改回javax） 默认项目结构也是Eclipse

如果想用IDEA打开需要在Project Structure-Module里面自行Import Module，建议把Dependencies storage format改为.iml，然后删掉没用的包，修改JDK路径，添加Tomcat 10 Library，再把WEB-INF/lib下的包都添加一遍，添加Facets-选择Web，去掉Descriptor，修改Web Resource Directory为webapp目录，再右下角Create Artifact，Fix几次就ok了

由于改IDEA项目结构有点麻烦，放了一个改好的能直接跑的IDEA项目配置文件上来，但是Tomcat服务器的目录可能需要在Run-Edit Configurations处自己修改
