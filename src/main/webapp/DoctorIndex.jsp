<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>
<%@ page import="serviceListener.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/DoctorIndex.css">
<script type="text/javascript" src="./js/DoctorIndex.js"></script>
<title>首页-医生</title>
</head>
<body>
<%
/** 
 * @author Stallion-X
 */
if (request.getAttribute("fromlogin")!=null) {
	if ((boolean)request.getAttribute("fromlogin")==true) {
		request.setAttribute("fromlogin", false);
		%>
		<script type="text/javascript">location.href="DoctorServlet"</script>
		<%
	}
	
}
List <AppointmentBean> list = null;
if (request.getAttribute("allAppo")!=null) {
	list = (List <AppointmentBean>)request.getAttribute("allAppo");
}
if (request.getAttribute("callpnum")!=null) {
	String pnum = (String)request.getAttribute("callpnum");
	application.setAttribute("callpnum", pnum);
	application.setAttribute("callstate", "1");
}
/*if (application.getAttribute("callreturnstate")!=null) {
	if (application.getAttribute("callreturnstate").equals("1")) {
		application.setAttribute("callreturnstate", "0");*/
		%>
		<%-- <jsp:forward page="DoctorServlet">
		<jsp:param name="action" value="complete"/>
		</jsp:forward> --%>
		<%
/*	}
}*/
%>
<div class="left" id="leftbox">
<div class="logobox">
<h1>
<img src="./img/logo.png">
</h1>
</div>
<div class="ulbox">
<ul class="left_ul">
<li>
<a href="DoctorServlet?action=exp" >叫号</a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=7" >已过号</a>
</li>
<li>
<a onclick="hangon()">挂起</a>
</li>
<li>
<a onclick="transfer()">转移队列</a>
</li>
<li>
<a href="DoctorServlet?action=browseMExam" >医技检查信息</a>
</li>
<li>
<a>资讯通知</a>
</li>
</ul>
</div>
<svg version="1.1" id="blob" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
			<path id="blob-path" d="M60,500H0V0h60c0,0,20,172,20,250S60,900,60,500z"/>
		</svg>
</div>
<div class="right">
<ul class="right_ul">
<li>
<a href="DoctorServlet" >全部<span class="timer count-title" id="count-number" data-to="${requestScope.stats[0]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=1" >普通<span class="timer count-title" id="count-number" data-to="${requestScope.stats[1]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=2" >优先<span class="timer count-title" id="count-number" data-to="${requestScope.stats[2]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=3" >急诊<span class="timer count-title" id="count-number" data-to="${requestScope.stats[3]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=4" >绿色通道<span class="timer count-title" id="count-number" data-to="${requestScope.stats[4]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=5" >诊结<span class="timer count-title" id="count-number" data-to="${requestScope.stats[5]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li>
<a href="DoctorServlet?action=classify&cid=6" >暂停<span class="timer count-title" id="count-number" data-to="${requestScope.stats[6]}" data-speed="40" style="color:#40404070;">0</span></a>
</li>
<li class="account">
<a href="" id="acc">医生</a>
<ul id="acmenu">
<li>
<a href="PersonalCenter.jsp?from=fromD" >个人中心</a>
</li>
<li>
<a href="index.html" onclick="exitmain()">退出</a>
</li>
</ul>
</li>
</ul>
<div class="mid">
<img src="./img/vline.png"/>
<span id="midt">${cookie.dcon.value}科室</span>
<%
if (request.getParameter("cid")==null&&request.getAttribute("allM")==null) {
	%>
	<form id="searchfrm" style="display:inline;" action="DoctorServlet">
	<input type="hidden" id="frm1action" name="action" value="search">
	<input type="text" placeholder="请输入查询内容" id="search" onkeydown="enterdown()" style="cursor:auto;">
	</form>
	<form id="frm" style="display:inline;" action="DoctorServlet" onsubmit="processfrm(<%=list.size()%>)" method="post">
	<input type="hidden" id="frmaction" name="action" value="">
	<input type="hidden" id="frmanum" name="anum" value="">
	<input type="hidden" id="frmastate" name="astate" value="">
	<input type="submit" value="修改状态" id="b1" onclick="editstate()">
	<input type="submit" value="优先" id="b2" onclick="btn2()">
	<input type="button" value="导入" id="b3" onclick="btn3(<%=list.size()%>)">
	<input type="submit" value="删除" id="b4" onclick="btn4()">
	<input type="submit" value="推后" id="b5" onclick="btn5()">
	</form>
	<%
}
%>
</div>
<div class="tab">
<table>
<%
if (request.getParameter("tableflag")!=null) {
	if (request.getParameter("tableflag").equals("1")) {
		List <MedicalTechBean> Mlist = (List <MedicalTechBean>)request.getAttribute("allM");
		%>
		<tr class="hd">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>ID</td><td>姓名</td><td>项目</td><td>检查时间</td><td>状态</td><td align="center">操作</td>
		<tr/>
		<%
		for (int i=0;i<Mlist.size();i++) {
			pageContext.setAttribute("M", Mlist.get(i));
			if ((i+1)%2!=0) {
				%>
				<tr class="cont">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>${M.mtenum}</td><td>${M.pname}</td>
				<td>${M.mtname}</td><td>${M.mtetime}</td>
				<td><%
				switch (Mlist.get(i).getMtstate()) {
				case 1:
					%>
					等待
					<%
					break;
				case 2:
					%>
					正在检查
					<%
					break;
				case 3:
					%>
					检查完毕
					<%
					break;
				case 4:
					%>
					暂停
					<%
					break;
				case 5:
					%>
					错过
					<%
					break;
				}
				%></td>
				<%
				if (i==0) {
					%>
					<td><a href="DoctorServlet?action=compmtb&pnum=${M.pnum}" class="op1">结束检查</a></td>
					<%
				}
				else {
					%>
					<td><a></a></td>
					<%
				}
				%>
				<td><a href="DoctorServlet?action=passMedi&mtenum=${M.mtenum}" class="op2">过号</a></td>
				<td><a></a></td>
				<td><a></a></td>
				</tr>
				<%
			}
			else {
				%>
				<tr class="cont" style="background-color:rgb(247,247,247);">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>${M.mtenum}</td><td>${M.pname}</td>
				<td>${M.mtname}</td><td>${M.mtetime}</td>
				<td><%
				switch (Mlist.get(i).getMtstate()) {
				case 1:
					%>
					等待
					<%
					break;
				case 2:
					%>
					正在检查
					<%
					break;
				case 3:
					%>
					检查完毕
					<%
					break;
				case 4:
					%>
					暂停
					<%
					break;
				case 5:
					%>
					错过
					<%
					break;
				}
				%></td>
				<%
				if (i==0) {
					%>
					<td><a href="DoctorServlet?action=compmtb&pnum=${M.pnum}" class="op1">结束检查</a></td>
					<%
				}
				else {
					%>
					<td><a></a></td>
					<%
				}
				%>
				<td><a href="DoctorServlet?action=passMedi&mtenum=${M.mtenum}" class="op2">过号</a></td>
				<td><a></a></td>
				<td><a></a></td>
				</tr>
				<%	
			}
		}
	}
	else if (request.getParameter("tableflag").equals("2")) {
		%>
		<tr class="hd">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>ID</td><td>姓名</td><td>科室</td><td>诊室</td><td>医生</td><td>队列</td><td>状态</td><td>类型</td><td>时间</td><td align="center">操作</td>
		<tr/>
		<%
	}
}
else {
	%>
	<thead>
	<tr class="hd">
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>ID</td><td>姓名</td><td>科室</td><td>诊室</td><td>医生</td><td>队列</td><td>状态</td><td>类型</td><td>预约时间</td><td align="center" colspan="4">操作</td>
	<tr/>
	</thead>
	<tbody>
	<%
	for (int i=0;i<list.size();i++) {
		pageContext.setAttribute("appo",list.get(i));
		if ((i+1)%2!=0) {
			%>
			<tr class="cont">
			<td>&nbsp;&nbsp;&nbsp;<input type="radio" id="ck<%=i+1%>">&nbsp;&nbsp;&nbsp;</td><td id="realanum<%=i+1%>">${appo.anum}</td><td class="infotd bottom" onmouseover="mouseoverp(${appo.pnum})">${appo.pname}<div class="tooltip"><p class="tip${appo.pnum}"></p></div></td>
			<td>${appo.ddepartment}</td><td>${appo.dcon}</td>
			<td class="infotd bottom" onmouseover="mouseoverd(${appo.dnum})">${appo.dname}<div class="tooltip"><p class="tip${appo.dnum}"></p></div></td>
			<td><%
			switch (list.get(i).getAtype()) {
			case 1:
				%>
				普通
				<%
				break;
			case 2:
				%>
				优先
				<%
				break;
			case 3:
				%>
				急诊
				<%
				break;
			case 4:
				%>
				绿色通道
				<%
				break;
			}
			%></td>
			<td><%
			switch (list.get(i).getAstate()) {
			case 1:
				%>
				等待
				<%
				break;
			case 2:
				%>
				正在就诊
				<%
				break;
			case 3:
				%>
				诊结
				<%
				break;
			case 4:
				%>
				暂停
				<%
				break;
			case 5:
				%>
				错过
				<%
				break;
			}
			%></td>
			<td>${appo.dtitle}</td><td>${appo.yytime}</td>
			<%
			if (i==0&&request.getParameter("action")!=null) {
				if (request.getParameter("action").equals("exp")) {
					%>
					<td><a href="DoctorServlet?action=call&pnum=<%=list.get(i).getPnum() %>" class="op1">呼叫</a></td>
					<%
				}
				else {
					%>
					<td><a></a></td>
					<%
				}
			}
			else {
				%>
				<td><a></a></td>
				<%
			}
			%>
			<td><a href="DoctorServlet?action=passAppo&anum=<%=list.get(i).getAnum() %>" class="op2">过号</a></td>
			<td><a class="op2" onclick="newdrug()">开药</a></td>
			<td><a class="op2" onclick="newmexam()">开检查单</a></td>
			</tr>
			<%
		}
		else {
			%>
			<tr class="cont" style="background-color:rgb(247,247,247);">
			<td>&nbsp;&nbsp;&nbsp;<input type="radio" id="ck<%=i+1%>">&nbsp;&nbsp;&nbsp;</td><td id="realanum<%=i+1%>">${appo.anum}</td><td class="infotd bottom" onmouseover="mouseoverp(${appo.pnum})">${appo.pname}<div class="tooltip"><p class="tip${appo.pnum}"></p></div></td>
			<td>${appo.ddepartment}</td><td>${appo.dcon}</td>
			<td class="infotd bottom" onmouseover="mouseoverd(${appo.dnum})">${appo.dname}<div class="tooltip"><p class="tip${appo.dnum}"></p></div></td>
			<td><%
			switch (list.get(i).getAtype()) {
			case 1:
				%>
				普通
				<%
				break;
			case 2:
				%>
				优先
				<%
				break;
			case 3:
				%>
				急诊
				<%
				break;
			case 4:
				%>
				绿色通道
				<%
				break;
			}
			%></td>
			<td><%
			switch (list.get(i).getAstate()) {
			case 1:
				%>
				等待
				<%
				break;
			case 2:
				%>
				正在就诊
				<%
				break;
			case 3:
				%>
				诊结
				<%
				break;
			case 4:
				%>
				暂停
				<%
				break;
			case 5:
				%>
				错过
				<%
				break;
			}
			%></td>
			<td>${appo.dtitle}</td><td>${appo.yytime}</td>
			<td><a></a></td>
			<td><a href="DoctorServlet?action=passAppo&anum=<%=list.get(i).getAnum() %>" class="op2">过号</a></td>
			<td><a class="op2" onclick="newdrug()">开药</a></td>
			<td><a class="op2" onclick="newmexam()">开检查单</a></td>
			</tr>
			<%	
		}
	}
}
%>
</tbody>
</table>
</div>
<div class="bkg">
<div id="inputedit">
<form action="DoctorServlet" method="post">
<span style="text-align:center;font-size:20px;">加号</span><br/>
<input type="hidden" name="action" value="addAppo">
<input type="hidden" id="frm2anum" name="anum" value="">
患者编号：<input type="text" id="frmpnum" name="pnum"><br/>
所在科室：<input type="text" id="frmddepartment" name="ddepartment"><br/>
医生编号：<input type="text" id="frmdnum" name="dnum"><br/>
预约时间：<input type="text" id="frmyytime" name="yytime"><br/>
就诊时间：<input type="text" id="frmjztime" name="jztime"><br/>
就诊诊室：<input type="text" id="frmdcon" name="dcon"><br/>
就诊状态：<input type="text" id="frm2astate" name="astate"><br/>
队列类型：<input type="text" id="frmatype" name="atype"><br/>
<button>确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="canceledit()">取消</button>
</form>
</div>
</div>
<div class="bkg">
<div id="inputnewdrug">
<form action="DoctorServlet" method="get">
<span style="text-align:center;font-size:20px;">开药</span><br/>
<input type="hidden" name="action" value="newDrug">
<input type="hidden" id="frmtmnum" name="tmnum" value="">
患者编号：<input type="text" id="frm2pnum" name="pnum"><br/>
药品编号：<input type="text" id="frmdrnum" name="drnum"><br/>
取药数量：<input type="text" id="frmdrquantity" name="drquantity"><br/>
取药状态：<input type="text" id="frmtmstate" name="tmstate">
<p style="margin:0px;text-align:center;">医嘱：</p><textarea id="frmpmdad" name="pmdad" style="margin:10px 0px 10px;height:100px;"></textarea><br/>
<button>确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="cancelnewdrug()">取消</button>
</form>
</div>
</div>
<div class="bkg">
<div id="inputnewmexam">
<form action="DoctorServlet" method="post">
<span style="text-align:center;font-size:20px;">开检查单</span><br/>
<input type="hidden" name="action" value="newMExam">
<input type="hidden" id="frmmtenum" name="mtenum" value="">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;患者编号：<input type="text" id="frm3pnum" name="pnum"><br/>
医技项目编号：<input type="text" id="frmmtnum" name="mtnum"><br/>
医技检查时间：<input type="text" id="frmmtetime" name="mtetime"><br/>
医技检查状态：<input type="text" id="frmmtstate" name="mtstate"><br/>
<button>确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="cancelnewmexam()">取消</button>
</form>
</div>
</div>
<div class="bkg" style="cursor:pointer;">
<div id="hangon">
<span>已暂停接诊</span>
</div>
</div>
<div class="bkg">
<div id="transfer">
<form action="DoctorServlet" method="post">
<span style="text-align:center;font-size:20px;">转移队列</span><br/>
<input type="hidden" name="action" value="transferD">
转移医生编号：<input type="text" name="dnumold"><br/>
接收医生编号：<input type="text" name="dnumnew"><br/>
<button>确认</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="canceltransfer()">取消</button>
</form>
</div>
</div>
</div>
<script type="text/javascript" src="./js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./js/jqnum.js"></script>
<script type="text/javascript" src="./js/leftblob.js"></script>
</body>
</html>
<%
Cookie cookies[] = request.getCookies();
String dcon = null;
if (cookies!=null) {
	for (int i=0;i<cookies.length;i++) {
		if (cookies[i].getName().equals("dcon")) {
			dcon = cookies[i].getValue();
		}
	}
}
UserInfoList ulist= UserInfoList.getInstance();
UserInfoTrace ut = new UserInfoTrace();
request.setCharacterEncoding("UTF-8");
ut.setUser(dcon+"号诊室");
session.setAttribute("list", ut);
ulist.addUserInfo(ut.getUser());
session.setMaxInactiveInterval(30);
%>