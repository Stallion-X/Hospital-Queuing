<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.*"%>
<%@page import="java.util.*"%>
<%@ page import="serviceListener.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页-患者</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css" />
<link rel="stylesheet" href="css/patient_index.css" type="text/css" />
<script language="javascript" type="text/javascript" src="js/Kunyilibrary.js"></script>
<script language="javascript" type="text/javascript" src="js/Kunyi.Common.js"></script>
<script type="text/javascript">
function queueon() {
	document.getElementById("bkg").animate(
	        [{
	           visibility:'hidden',
	           opacity:'0'
	        },
	        {
	           visibility:'visible',
	           opacity:'1'
	        }
	        ],
	        { 
	            duration: 400,
	            easing:'linear',
	        }
	       );
		document.getElementById("bkg").style.visibility="visible";
		document.getElementById("queueon").style.visibility="visible";
		document.getElementById("bkg").onclick = function() {
			document.getElementById("bkg").animate(
	        [{
	           visibility:'visible',
	           opacity:'1'
	        },
	        {
	           visibility:'hidden',
	           opacity:'0'
	        }
	        ],
	        { 
	            duration: 400,
	            easing:'linear', 
	        }
	       );
			document.getElementById("bkg").style.visibility="hidden";
			document.getElementById("queueon").style.visibility="hidden";
			xmlhttp.open("GET","DoctorServlet?action=complete",true);
			xmlhttp.send();
		};
}
function signin() {
	document.getElementById("bkg2").animate(
	        [{
	           visibility:'hidden',
	           opacity:'0'
	        },
	        {
	           visibility:'visible',
	           opacity:'1'
	        }
	        ],
	        { 
	            duration: 400,
	            easing:'linear',
	        }
	       );
		document.getElementById("bkg2").style.visibility="visible";
		document.getElementById("sign").style.visibility="visible";
		document.getElementById("bkg2").onclick = function() {
			document.getElementById("bkg2").animate(
	        [{
	           visibility:'visible',
	           opacity:'1'
	        },
	        {
	           visibility:'hidden',
	           opacity:'0'
	        }
	        ],
	        { 
	            duration: 400,
	            easing:'linear', 
	        }
	       );
			document.getElementById("bkg2").style.visibility="hidden";
			document.getElementById("sign").style.visibility="hidden";
		};
}
</script>
</head>
<%
Cookie cookies[] = request.getCookies();
String pnum = null;
String ppsd = null;
if (cookies!=null) {
	for (int i=0;i<cookies.length;i++) {
		if (cookies[i].getName().equals("pnum")) {
			pnum = cookies[i].getValue();
		}
		if (cookies[i].getName().equals("ppsd")) {
			ppsd = cookies[i].getValue();
		}
	}
}
if (request.getAttribute("selected")!=null) {
	session.setAttribute("selected", request.getAttribute("selected"));
}
%>
<script type="text/javascript">
var xmlhttp;
if (window.XMLHttpRequest) {
    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
}
else {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
setInterval(function() {
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			if (xmlhttp.responseText=="1") {
				location.href="patient_index.jsp?action=fresh";
			}
        }
	}
	xmlhttp.open("GET","PatientServlet?action=fresh&pnum=<%=pnum%>",true);
	xmlhttp.send();
}, 100);
function setCookie(c_name, value, expiredays){
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}
window.onload = function() {
	document.getElementById("Nav_AMenu7").onclick = function() {
		setCookie('pnum','',-1);
		setCookie('ppsd','',-1);
	};
};
</script>
<%
/* String ok = request.getParameter("ok");
if(ok == null){
	response.sendRedirect("PatientServlet?action=Init");
} */
%>
<body>
	<script language="javascript" type="text/javascript" src="js/Kunyi.Init.js"></script>
	<nav>
		<div class="navBase">
			<div class="quickMenuBase" data-type="1">
				<div class="quickMenu01">医院挂号主页</div>
				<div class="quickMenus">
					<ul class="firstUl">
						<li class="first"><a href="PatientServlet?action=toReg&pmenu=8" 
						class="firstA"><em>挂号</em><i
								class="style01"></i></a>
						</li>
						<li class="first"><a class="firstA" style="cursor:pointer;"><em onclick="signin()">签到</em><i
								class="style03"></i><span></span></a>
								</li>
						<li class="first"><a href="PatientServlet?action=toAppo&pmenu=9&pnum=<%=pnum %>" class="firstA"><em>退号</em><i
								class="style05"></i><span></span></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="nav Inav">
				<ul>
					<li class="PMenu"><a href="PatientServlet?action=Init&pmenu=1" class="PAMenu">首页</a>
					    </li>
					<li class="PMenu"><a href="PatientServlet?action=appoList&pmenu=2&pnum=<%=pnum %>" id="Nav_AMenu0" class="PAMenu" title="科室候诊队列">科室候诊队列</a>
					   </li>
					<li class="PMenu"><a href="PatientServlet?action=mExamList&pmenu=3" id="Nav_AMenu1" class="PAMenu" title="医技检查队列">医技检查队列</a>
					    </li>
					<li class="PMenu"><a href="PatientServlet?action=TMB&pmenu=4" id="Nav_AMenu2" class="PAMenu" title="取药排队队列">取药排队队列</a>
				        </li>
					<li class="PMenu"><a href="PatientServlet?action=toAppo&pmenu=5&pnum=<%=pnum %>" id="Nav_AMenu3" class="PAMenu"
						title="就诊单">就诊单</a>
						</li>
					<li class="PMenu"><a href="PatientServlet?action=toMExam&pmenu=6&pnum=<%=pnum %>" id="Nav_AMenu4" class="PAMenu" 
					    title="检查单">检查单</a>
					    </li>
					<li class="PMenu"><a href="PatientServlet?action=toDrug&pmenu=7&pnum=<%=pnum %>" id="Nav_AMenu5" class="PAMenu"
						title="取药单">取药单</a>
						</li>
					<li class="PMenu"><a href="PersonalCenter.jsp?from=fromP" id="Nav_AMenu6" class="PAMenu"
						title="个人中心">个人中心</a>
						</li>
					<li class="PMenu"><a href="index.html" id="Nav_AMenu7" class="PAMenu"
						title="退出" onclick="exitmain()">退出</a>
						</li>
				</ul>
			</div>
		</div>
	</nav>
	<%
	if (pnum!=null) {
		%>
		<div id="account"><a href="" id="acc">欢迎您，<%=pnum %>号病人</a></div>
		<%
	}
	%>
	<section class="doctorsBase BaseMark">
		<div class="toolsBase">
			<div class="title">
				<a>专家介绍</a>
			</div>
			<div class="tools">
				<form method="get" class="search" action=""
					onSubmit="return Kunyi.CheckSearchFrom()">
					<input type="hidden" name="Type" value="2" /><input type="text" name="SearchWords"
						placeholder="请输入医生名字" class="animated" value="" autocomplete="off" maxlength="28" /><input
						class="btn" type="submit" value="" />
				</form>
				<a href="javascript:;" class="Aprev"></a>
				<a href="javascript:;" class="Anext"></a>
			</div>
		</div>
		<%
		if (request.getAttribute("pmenu")!=null) {
			switch ((String)request.getAttribute("pmenu")) {
			case "1":
				%>
				<div class="contents">
			    <ul data-num="0">
			    <c:forEach items="${requestScope.Doctor_list}" var="list">
			    <li><a href="" title="医生头像" target="_blank" class="img"><img
						alt="头像" title="头像" src="pic/dpic/${list.dnum}.jpg" width="120" height="162" /></a> <a
					href="" title="医生姓名" target="_blank" class="name"> ${list.dname}</a> <a
					href="" title="所在科室" target="_blank" class="office">${list.ddepartment}</a> <a
					href="" title="职称" target="_blank" class="post">${list.dtitle}</a> <a
				    title="院校" target="_blank" class="btn more">${list.dedu}</a> <a href="PatientServlet?action=toReg&pmenu=8&selected=${list.dnum}"
					target="" class="btn date">预约</a></li>
			    </c:forEach>
				</ul>
				<div class="hackR"></div>
				<div class="hackL"></div>
				</div>
				<%
				break;
			case "2"://患者候诊队列
				%>
				<a href="PatientServlet?action=appoListOnly&pmenu=2&pnum=<%=pnum %>">只看我的</a>
				<table class = "table1">
				<tr>
										<th>预约编号</th>
										<th>患者编号</th>
										<th>所在科室</th>
										<th>医生编号</th>
										<th>预约时间</th>
										<th>就诊时间</th>
										<th>就诊诊室</th>
										<th>就诊状态</th>
										<th>队列类型</th>
				</tr>
				<%
				List <AppointmentBean> Appo_list = (List<AppointmentBean>)request.getAttribute("Appo_list");
						for (int i=0;i<Appo_list.size();i++) {
				%>
					<tr>
					<td><%=Appo_list.get(i).getAnum() %></td><td><%=Appo_list.get(i).getPnum() %></td><td><%=Appo_list.get(i).getDdepartment() %></td>
					<td><%=Appo_list.get(i).getDnum() %></td><td><%=Appo_list.get(i).getYytime() %></td><td><%=Appo_list.get(i).getJztime() %></td>
					<td><%=Appo_list.get(i).getDcon() %></td><td><%=Appo_list.get(i).getAstate() %></td><td><%=Appo_list.get(i).getAtype() %></td>
					</tr>
					<%
				}
				%>
				</table>
				<%
				break;
			case "3"://医技检查队列
				%>
				<table class = "table1">
				<tr>
				<th>医技检查编号</th><th>患者编号</th><th>医技项目编号</th><th>医技检查时间</th><th>医技检查状态</th>
				</tr>
				<%
				List <MedicalTechBean> MTB_list = (List<MedicalTechBean>)request.getAttribute("MTB_list");
				for (int i=0;i<MTB_list.size();i++) {
					%>
					<tr>
					<td><%=MTB_list.get(i).getMtenum() %></td><td><%=MTB_list.get(i).getPnum() %></td><td><%=MTB_list.get(i).getMtnum() %></td><td><%=MTB_list.get(i).getMtetime() %></td><td><%=MTB_list.get(i).getMtstate() %></td>
					</tr>
					<%
				}
				%>
				</table>
				<%
				break;
			case "4"://取药队列
				%>
				<table class = "table1">
				<tr>
				<th>取药编号</th><th>患者编号</th><th>药品编号</th><th>取药数量</th><th>取药状态</th>
				</tr>
				<%
				List <TakeMedicineBean> TMB_list = (List<TakeMedicineBean>)request.getAttribute("TMB_list");
				for (int i=0;i<TMB_list.size();i++) {
					%>
					<tr>
					<td><%=TMB_list.get(i).getTmnum() %></td><td><%=TMB_list.get(i).getPnum() %></td><td><%=TMB_list.get(i).getDrnum() %></td><td><%=TMB_list.get(i).getDrquantity() %></td><td><%=TMB_list.get(i).getTmstate() %></td>
					</tr>
					<%
				}
				%>
				</table>
				<%
				break;
			case "5"://就诊单
				%>
				<div class="table1">
				<h2>就诊单</h2>
				<form action="PatientServlet">
				<input type="hidden" name="action" value="Appo"/>
				<input type="hidden" name="pmenu" value="5"/>
				<span>预约编号：</span><input id = "NewBase_Y1" type="text" name="anum" value="${requestScope.latestAppo.anum }" readonly/><br/>
				<span>患者编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.pname }" readonly/><br/>
				<span>所在科室：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.ddepartment }" readonly/><br/>
				<span>医生编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.dname }" readonly/><br/>
				<span>预约时间：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.yytime }" readonly/><br/>
				<span>就诊时间：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.jztime }" readonly/><br/>
				<span>就诊诊室：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.dcon }" readonly/><br/>
				<span>就诊状态：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.astate }" readonly/><br/>
				<span>队列类型：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.atype }" readonly/><br/>
				<button id = "NewBase_Y2">确认完成</button>
				</form>
				</div>
				<%
				break;
			case "6"://检查单
				%>
				<div class="table1">
				<h2>检查单</h2>
				<form action="PatientServlet">
				<input type="hidden" name="action" value="MExam"/>
				<input type="hidden" name="pmenu" value="6"/>
				<input type="hidden" name="pnum" value="<%=pnum%>"/>
				<span>医技检查编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestMTB.mtenum }" readonly/><br/>
				<span>患者编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestMTB.pnum }" readonly/><br/>
				<span>医技项目编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestMTB.mtnum }" readonly/><br/>
				<span>医技检查时间：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestMTB.mtetime }" readonly/><br/>
				<span>医技检查状态：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestMTB.mtstate }" readonly/><br/>
				<button id = "NewBase_Y2">确认完成</button>
				</form>
				</div>
				<%
				break;
			case "7"://取药单
				%>
				<div class="table1">
				<h2>取药单</h2>
				<form action="PatientServlet">
				<input type="hidden" name="action" value="Drug"/>
				<input type="hidden" name="pmenu" value="7"/>
				<input type="hidden" name="pnum" value="<%=pnum%>"/>
				<span>取药编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestTMB.tmnum }" readonly/><br/>
				<span>患者编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestTMB.pnum }" readonly/><br/>
				<span>药品编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestTMB.drnum }" readonly/><br/>
				<span>取药数量：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestTMB.drquantity }" readonly/><br/>
				<span>取药状态：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestTMB.tmstate }" readonly/><br/>
				<button id = "NewBase_Y2">确认完成</button>
				</form>
				</div>
				<%
				break;
			case "8"://挂号
				%>
				<div class="table1">
				<h2>挂号</h2>
				<form action="PatientServlet">
				<input type = "hidden" name = "action" value = "toReg">
				<input type = "hidden" name = "pmenu" value = "8">
	
				<span>患者编号：</span><input id = "NewBase_Y1" type="text" name = "pnum" value="<%=pnum %>" /><br/>
				<span>所在科室：</span><input id = "NewBase_Y1" type="text" name = "ddepartment" value="" /><br/>
				<span>医生编号：</span><input id = "NewBase_Y1" type="text" name = "dnum" value="${sessionScope.selected }" /><br/>
				<span>预约时间：</span><input id = "NewBase_Y1" type="text" name = "yytime" value="" /><br/>
				<span>就诊时间：</span><input id = "NewBase_Y1" type="text" name = "jztime" value="" /><br/>
				<span>就诊诊室：</span><input id = "NewBase_Y1" type="text" name = "docn" value="" /><br/>
				<input type="hidden" name = "astate" value="1" /><br/>
				<input type="hidden" name = "atype" value="1" /><br/>
				<button id = "NewBase_Y2">挂号</button>
				</form>
				</div>
				<%
				break;
			case "9"://退号
				%>
				<div class="table1">
				<h2>退号</h2>
				<form action="PatientServlet">
				<input type="hidden" name="action" value="Del"/>
				<input type="hidden" name="pmenu" value="9"/>
				<span>预约编号：</span><input id = "NewBase_Y1" type="text" name="anum" value="${requestScope.latestAppo.anum }" readonly/><br/>
				<span>患者编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.pname }" readonly/><br/>
				<span>所在科室：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.ddepartment }" readonly/><br/>
				<span>医生编号：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.dname }" readonly/><br/>
				<span>预约时间：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.yytime }" readonly/><br/>
				<span>就诊时间：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.jztime }" readonly/><br/>
				<span>就诊诊室：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.dcon }" readonly/><br/>
				<span>就诊状态：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.astate }" readonly/><br/>
				<span>队列类型：</span><input id = "NewBase_Y1" type="text" value="${requestScope.latestAppo.atype }" readonly/><br/>
				<button id = "NewBase_Y2">确认退号</button>
				</form>
				</div>
				<%
				break;
			}
		}
		else {
			%>
			<div class="contents">
			    <ul data-num="0">
			    <c:forEach items="${requestScope.Doctor_list}" var="list">
			    <li><a href="" title="医生头像" target="_blank" class="img"><img
						alt="头像" title="头像" src="pic/dpic/${list.dnum}.jpg" width="120" height="162" /></a> <a
					href="" title="医生姓名" target="_blank" class="name"> ${list.dname}</a> <a
					href="" title="所在科室" target="_blank" class="office">${list.ddepartment}</a> <a
					href="" title="职称" target="_blank" class="post">${list.dtitle}</a> <a
					title="院校" target="_blank" class="btn more">${list.dedu}</a> <a href="PatientServlet?action=toReg&pmenu=8&selected=${list.dnum}"
					target="" class="btn date">预约</a></li>
			    </c:forEach>
				</ul>
				<div class="hackR"></div>
				<div class="hackL"></div>
				</div>
			<%
		}
		%>
		
	</section>

	<section class="officesBase BaseMark">
		<div class="toolsBase">
			<div class="title">
				<em>重点专科</em> <span>Key Specialty</span>
			</div>
			<div class="tools">
				<a href="javascript:;" class="Aprev"></a>
				<a href="javascript:;" class="Anext"></a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="contents" style="left:-30px;">
			<ul data-num="0">
				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="呼吸内科" src="pic/pic_007.png" width="80" height="80" />
					</div> <a href="" title="呼吸内科" class="office">呼吸内科</a>
					<p>我科始建于上世纪70年代初，目前为南昌航空大学重点专科建设项目单位，南昌航空大学重点……</p> <a href="" title="呼吸内科"
					class="btn more">查看详细</a> <a href="" title="呼吸内科医生" class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="神经内科" src="pic/pic_009.png" width="80" height="80" />
					</div> <a href="" title="神经内科" class="office">神经内科</a>
					<p>医院神经内科始建于1954年，经过半个多世纪的发展，目前已发展为全……</p> <a href="" title="神经内科"
					class="btn more">查看详细</a> <a href="" title="神经内科医生"
					class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="内分泌科" src="pic/pic_008.png" width="80" height="80" />
					</div> <a href="" title="内分泌科" class="office">内分泌科</a>
					<p>医院标题内分泌代谢科，是国家重点专科、南昌航空大学重点专科、南昌航空大学糖尿病防治……</p> <a href="" title="内分泌科"
					class="btn more">查看详细</a> <a href="" title="内分泌科医生"
					class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="神经内科康馨病房" src="pic/pic_012.png" width="80" height="80" />
					</div> <a href="" title="神经内科康馨病房" class="office">神经内科康馨病房</a>
					<p>医院标题神经内科康馨病房为国家临床重点专科神经内科干部保健病房，成立于……</p> <a href=""
					title="神经内科康馨病房" class="btn more">查看详细</a> <a href=""
					title="神经内科康馨病房医生" class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="心内科" src="pic/pic_010.png" width="80" height="80" />
					</div> <a href="" title="心内科" class="office">心内科</a>
					<p>医院标题心血管内科是国家级临床重点专科、南昌航空大学临床重点专科、南昌航空大学心血管……</p> <a href="" title="心内科"
					class="btn more">查看详细</a> <a href="" title="心内科医生"
					class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="肾病风湿免疫科" src="pic/pic_013.png" width="80" height="80" />
					</div> <a href="" title="肾病风湿免疫科" class="office">肾病风湿免疫科</a>
					<p>医院标题肾病风湿免疫科成立于1985年，由肾病专业及风湿病专业组成，是……</p> <a href=""
					title="肾病风湿免疫科" class="btn more">查看详细</a> <a href=""
					title="肾病风湿免疫科医生" class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="呼吸内科康馨病房" src="pic/pic_012.png" width="80" height="80" />
					</div> <a href="" title="呼吸内科康馨病房" class="office">呼吸内科康馨病房</a>
					<p>医院标题呼吸与危重症医学科康馨病房为国家临床重点专科呼吸与危重症医学科……</p> <a href=""
					title="呼吸内科康馨病房" class="btn more">查看详细</a> <a href=""
					title="呼吸内科康馨病房医生" class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="心外科" src="pic/pic_010.png" width="80" height="80" />
					</div> <a href="" title="心外科" class="office">心外科</a>
					<p>医院标题、南昌航空大学心血管病医院心血管外科是国家临床重点专科之一，是省内综……</p> <a href="" title="心外科"
					class="btn more">查看详细</a> <a href="" title="心外科医生"
					class="btn doctor">科室专家</a></li>

				<li><i class="ico01"></i>
					<div class="img animated">
						<img alt="山西省立眼科医院" src="pic/pic_011.png" width="80" height="80" />
					</div> <a href="" title="山西省立眼科医院" class="office">山西省立眼科医院</a>
					<p>南昌航空大学科研究所于1962年南昌航空大学批准正式成立，是国内最早集眼科临……</p> <a href=""
					title="山西省立眼科医院" class="btn more">查看详细</a> <a href=""
					title="山西省立眼科医院医生" class="btn doctor">科室专家</a></li>

			</ul>
			<div class="hackR"></div>
			<div class="hackL"></div>
		</div>
		<div class="hiddenLineR"></div>
		<div class="hiddenLineL"></div>
	</section>
	<div id="bkg" style="cursor:pointer;">
		<div id="queueon">
		<span>排队已经轮到您，请您尽快前往预约医生处</span>
		</div>
	</div>
	<div id="bkg2" style="cursor:pointer;">
		<div id="sign">
		<span>签到完成</span>
		</div>
	</div>
	<%
if (pnum!=null&&application.getAttribute("callpnum")!=null&&application.getAttribute("callstate")!=null) {
	if (application.getAttribute("callpnum").equals(pnum)&&application.getAttribute("callstate").equals("1")) {
		application.setAttribute("callreturnstate", "1");
		application.removeAttribute("callstate");
		application.setAttribute("callstate","0");
		%>
<!-- 		<audio id="au" src="8.mp3"></audio> -->
		<script type="text/javascript">
		queueon();
		var content = "请<%=pnum%>号病人尽快前往预定医生处就医";
        const synth = window.speechSynthesis;
        const msg = new SpeechSynthesisUtterance();
        msg.text = content;     // 文字内容
        msg.lang = "zh-CN";  // 使用的语言:中文
        msg.volume = 1;      // 声音音量：0-1
        msg.rate = 1.5;        // 语速：0-10
        msg.pitch = 0.8;       // 音高：0-1
        synth.speak(msg);    // 播放
/*         document.getElementById("au").play(); */
		</script>
		<%
	}
}
%>
    <%
    UserInfoList list= UserInfoList.getInstance();
	UserInfoTrace ut = new UserInfoTrace();
	request.setCharacterEncoding("UTF-8");
	ut.setUser(pnum+"号病人");
	session.setAttribute("list", ut);
	list.addUserInfo(ut.getUser());
	session.setMaxInactiveInterval(30);
    %>
</body>
</html>