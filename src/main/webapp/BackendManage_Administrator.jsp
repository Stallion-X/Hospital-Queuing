<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="beans.*" %>
 <%@page import="java.util.*" %>
 <%@ page import="serviceListener.*" %>
<!DOCTYPE html>
<html >
<head>
<link rel="stylesheet" type="text/css" href="./css/BackendManage_css.css">
 <script type="text/javascript" src="./js/BackendManage_js.js"></script>
    <meta charset="UTF-8">
    <title>后台管理系统</title>

</head>
<%	
	String ok = request.getParameter("ok");
	if(ok == null){
		response.sendRedirect("BackendManage_Main?action=Initialization");
	}
	
	List <BackendManage_UserBean> list0 = (List<BackendManage_UserBean>)request.getAttribute("allUsers0");
	
	List <BackendManage_UserBean> list1 = (List<BackendManage_UserBean>)request.getAttribute("allUsers1");
	List <BackendManage_UserBean> list2 = (List<BackendManage_UserBean>)request.getAttribute("allUsers2");
	List <BackendManage_UserBean> list3 = (List<BackendManage_UserBean>)request.getAttribute("allUsers3");
	List <BackendManage_UserBean> list4 = (List<BackendManage_UserBean>)request.getAttribute("allUsers4");
	List <BackendManage_UserBean> list5 = (List<BackendManage_UserBean>)request.getAttribute("allUsers5");
	List <BackendManage_UserBean> list6 = (List<BackendManage_UserBean>)request.getAttribute("allUsers6");
	List <BackendManage_UserBean> list7 = (List<BackendManage_UserBean>)request.getAttribute("allUsers7");
	
	
	
	String delete = request.getParameter("delete");
	String add = request.getParameter("add");

	if(ok != null){
		%>
		<body>
    <div class="BackendManage_2_body1">
        <div class="BackendManage_2_body1_1">
            <a href="">
                医院管理平台
            </a>
        </div>
    </div>

    <div class="w">
        <div class="BackendManage_2_body2">
            <div class="BackendManage_2_body2_1">
                <ul>
                    <li>
                        <div class="BackendManage_2_body2_1_1">运行情况</div>
                        <div class="BackendManage_2_body2_1_2" style=" display: block; ">
                            <div class="BackendManage_2_body2_1_2_1">
                                <div class="BackendManage_2_body2_1_2_1_1">
                                    <table>
                                        <tr style="border-bottom:3px solid #edede1">挂号队列</tr>
                                        <tr>
                                            <td style="border-left:0">已问诊人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(0) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0">未问诊人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(1) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0">问诊总人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(2) %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="BackendManage_2_body2_1_2_1_1">
                                    <table>
                                        <tr style="border-bottom:3px solid #edede1">取药队列</tr>
                                        <tr>
                                            <td style="border-left:0">已取药人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(3) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0">未取药人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(4) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0"> 取药总人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(5) %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="BackendManage_2_body2_1_2_1_1">
                                    <table>
                                        <tr style="border-bottom:3px solid #edede1">检查队列</tr>
                                        <tr>
                                            <td style="border-left:0">已检查人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(6) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0">未检查人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(7) %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-left:0">检查总人数</td>
                                            <td style="border-right:0">
                                                <%=list0.get(8) %>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">挂号排队队列</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>预约编号</th>
                                        <th>患者编号</th>
                                        <th>患者联系方式</th>
                                        <th>医生编号</th>
                                        <th>预约时间</th>
                                        <th>就诊时间</th>
                                        <th>就诊诊室</th>
                                        <th>就诊状态</th>
                                        <th>队列种类</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list3.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=""+list3.get(i).getAnum() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getPnum() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getPtel() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getDnum() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getYytime() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getJztime() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getDcon() %>
                                            </td>
                                            <td>
                                                <%=list3.get(i).getAstate() %>
                                            </td>                                                          <td>
                                                <%=list3.get(i).getAtype() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>

                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on3_1()">删除队列元素</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on3_2()">增加队列元素</div>
                            </div>
                        </div>
                        
                        <div class="BackendManage_2_body2_1_3" id="from3">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    预约编号:<input type="text" name="anum"><br> 患者编号:
                                    <input type="text" name="pnum"><br> 患者联系方式:
                                    <input type="text" name="ptel"><br> 医生编号:
                                    <input type="text" name="dnum"><br> 预约时间:
                                    <input type="text" name="yytime"><br> 就诊时间:
                                    <input type="text" name="jztime"><br> 就诊诊室:
                                    <input type="text" name="dcon"><br> 就诊状态:
                                    <input type="text" name="astate"><br> 队列种类:
                                    <input type="text" name="atype">
                                    <input type="submit" value="取消" onclick="on3_3();return false;">
                                    <input type="submit" value="提交">
                                    <input type="hidden" name="table_no" value="3">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">取药排队队列</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>取药编号</th>
                                        <th>患者编号</th>
                                        <th>药品编号</th>
                                        <th>取药数量</th>
                                        <th>取药状态</th>
                                        <th>患者联系方式</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list5.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=list5.get(i).getTmnum() %>
                                            </td>
                                            <td>
                                                <%=list5.get(i).getPnum() %>
                                            </td>
                                            <td>
                                                <%=list5.get(i).getDrnum() %>
                                            </td>
                                            <td>
                                                <%=list5.get(i).getDrquantity() %>
                                            </td>
                                            <td>
                                                <%=list5.get(i).getTmstate() %>
                                            </td>
                                            <td>
                                                <%=list5.get(i).getPtel() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on5_1()">删除队列元素</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on5_2()">增加队列元素</div>
                            </div>
                        </div>
                        <div class="BackendManage_2_body2_1_3" id="from5">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    取药编号:<input type="text" name="tmnum"><br> 患者编号:
                                    <input type="text" name="pnum"><br> 药品编号:
                                    <input type="text" name="drnum"><br> 取药数量:
                                    <input type="text" name="drquantity"><br> 取药状态:
                                    <input type="text" name="tmstate"><br> 患者联系方式:
                                    <input type="text" name="ptel"><br>
                                    <input type="submit" value="取消" onclick="on5_3();return false;">
                                    <input type="submit" value="提交">
                                     <input type="hidden" name="table_no" value="5">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">医技排队队列</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>医技检查编号</th>
                                        <th>患者编号</th>
                                        <th>医技项目编号</th>
                                        <th>医技检查时间</th>
                                        <th>医技检查状态</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list7.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=list7.get(i).getMtenum() %>
                                            </td>
                                            <td>
                                                <%=list7.get(i).getPnum() %>
                                            </td>
                                            <td>
                                                <%=list7.get(i).getMtnum() %>
                                            </td>
                                            <td>
                                                <%=list7.get(i).getMtetime() %>
                                            </td>
               
                                            <td>
                                                <%=list7.get(i).getMtstate() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on7_1()">删除队列元素</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on7_2()">增加队列元素</div>
                            </div>
                        </div>
                        <div class="BackendManage_2_body2_1_3" id="from7">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    医技检查编号:<input type="text" name="mtenum"><br> 患者编号:
                                    <input type="text" name="ptel"><br> 医技项目编号:
                                    <input type="text" name="mtnum"><br> 医技检查时间:
                                    <input type="text" name="mtetime"><br> 医技检查状态:
                                    <input type="text" name="mtstate"><br>
                                    <input type="submit" value="取消" onclick="on7_3();return false;">
                                    <input type="submit" value="提交">
                                    <input type="hidden" name="table_no" value="7">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">医生管理</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>医生编号</th>
                                        <th>医生姓名</th>
                                        <th>医生性别</th>
                                        <th>医生年龄</th>
                                        <th>医生联系方式</th>
                                        <th>职称</th>
                                        <th>任职职务</th>
                                        <th>毕业院校</th>
                                        <th>所在科室</th>
                                        <th>诊室编号</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list1.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=list1.get(i).getDnum() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDname() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDsex() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDage() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDtel() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDtitle() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDpost() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDedu() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDdepartment() %>
                                            </td>
                                            <td>
                                                <%=list1.get(i).getDcon() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on1_1()">删除医生</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on1_2()">增加医生</div>
                            </div>
                        </div>
                        <div class="BackendManage_2_body2_1_3" id="from1">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    医生编号:<input type="text" name="dnum"><br> 医生姓名:
                                    <input type="text" name="dname"><br> 医生性别:
                                    <input type="text" name="dsex"><br> 医生年龄:
                                    <input type="text" name="dage"><br> 医生联系方式:
                                    <input type="text" name="dtel"><br> 职称:
                                    <input type="text" name="dtitle"><br> 任职职务:
                                    <input type="text" name="dpost"><br> 毕业院校:
                                    <input type="text" name="dedu"><br> 所在科室:
                                    <input type="text" name="ddepartment"><br> 诊室编号:
                                    <input type="text" name="dcon"><br>
                                    <input type="submit" value="取消" onclick="on1_3();return false;">
                                    <input type="submit" value="提交">
                                    <input type="hidden" name="table_no" value="1">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">患者管理</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>患者编号</th>
                                        <th>患者姓名</th>
                                        <th>患者性别</th>
                                        <th>患者年龄</th>
                                        <th>身份证号</th>
                                        <th>患者联系方式</th>
                                        <th>患者密码</th>
                                        <th>患者基本信息</th>
                                        <th>医嘱</th>
                                        <%
                                for(int i = 0; i < list2.size(); i++){
                                	%>
                                            <tr>
                                                <td>
                                                    <%=list2.get(i).getPnum() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPname() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPsex() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPage() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPid() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPtel() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPpsd() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPinfor() %>
                                                </td>
                                                <td>
                                                    <%=list2.get(i).getPmdad() %>
                                                </td>
                                            </tr>
                                            <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on2_1()">删除患者</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on2_2()">增加患者</div>
                            </div>
                        </div>
                        <div class="BackendManage_2_body2_1_3" id="from2">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    患者编号:<input type="text" name="pnum"><br> 患者姓名:
                                    <input type="text" name="pname"><br> 患者性别:
                                    <input type="text" name="psex"><br> 患者年龄:
                                    <input type="text" name="page"><br> 身份证号:
                                    <input type="text" name="pid"><br> 患者联系方式:
                                    <input type="text" name="ppsd"><br>患者密码:
                                    <input type="text" name="ptel"><br>患者基本信息:
                                    <input type="text" name="pinfor"><br> 医嘱:
                                    <input type="text" name="pmdad"><br>
                                    <input type="submit" value="取消" onclick="on2_3();return false;">
                                    <input type="submit" value="提交">   
                                     <input type="hidden" name="table_no" value="2">
                                </form>
                            </div>
                        </div>

                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">药品管理</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>药品编号</th>
                                        <th>药品名称</th>
                                        <th>药品价格</th>
                                        <th>药品类型</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list4.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=list4.get(i).getDrnum() %>
                                            </td>
                                            <td>
                                                <%=list4.get(i).getDrname() %>
                                            </td>
                                            <td>
                                                <%=list4.get(i).getDrprice() %>
                                            </td>
                                            <td>
                                                <%=list4.get(i).getDrtype() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on4_1()">删除药品</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on4_2()">增加药品</div>
                            </div>
                        </div>
                        <div class="BackendManage_2_body2_1_3" id="from4">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    药品编号:<input type="text" name="drnum"><br> 药品名称:
                                    <input type="text" name="drname"><br> 药品价格:
                                    <input type="text" name="drprice"><br> 药品类型:
                                    <input type="text" name="drtype"><br>
                                    <input type="submit" value="取消" onclick="on4_3();return false;">
                                    <input type="submit" value="提交">
                                    <input type="hidden" name="table_no" value="4">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="BackendManage_2_body2_1_1">医技管理</div>
                        <div class="BackendManage_2_body2_1_2">
                            <div class="BackendManage_2_body2_1_2_1">
                                <table>
                                    <tr>
                                        <th>医技项目编号</th>
                                        <th>医技项目名称</th>
                                        <th>医技项目价格</th>
                                        <th>医技项目所在地点</th>
                                    </tr>
                                    <%
                                for(int i = 0; i < list6.size(); i++){
                                	%>
                                        <tr>
                                            <td>
                                                <%=list6.get(i).getMtnum() %>
                                            </td>
                                            <td>
                                                <%=list6.get(i).getMtname() %>
                                            </td>
                                            <td>
                                                <%=list6.get(i).getMtprice() %>
                                            </td>
                                            <td>
                                                <%=list6.get(i).getMtplace() %>
                                            </td>
                                        </tr>
                                        <% 
                                }
                                %>
                                </table>
                                <div class="BackendManage_2_body2_1_2_1_3" onclick="on6_1()">删除医技</div>
                                <div class="BackendManage_2_body2_1_2_1_4" onclick="on6_2()">增加医技
                                </div>
                            </div>
                        </div>
                        
                        <div class="BackendManage_2_body2_1_3" id="from6">
                            <div class="BackendManage_2_body2_1_3_1">
                                <form action="BackendManage_Main?action=add" method="post">
                                    医技项目编号:<input type="text" name="mtnum"><br> 医技项目名称:
                                    <input type="text" name="mtname"><br> 医技项目价格:
                                    <input type="text" name="mtprice"><br> 医技项目所在地点:
                                    <input type="text" name="mtplace"><br>
                                    <input type="submit" value="取消" onclick="on6_3();return false;">
                                    <input type="submit" value="提交">
                                    <input type="hidden" name="table_no" value="6">
                                </form>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
        <div class="BackendManage_2_body3">
            <div class="BackendManage_2_body3_1">
                <a href=""> 分诊信息刷新</a>
            </div>
        </div>
    </div>
    <%
    UserInfoList list= UserInfoList.getInstance();
	UserInfoTrace ut = new UserInfoTrace();
	request.setCharacterEncoding("UTF-8");
	ut.setUser("Administrator");
	session.setAttribute("list", ut);
	list.addUserInfo(ut.getUser());
	session.setMaxInactiveInterval(30);
    %>
    <textarea rows="10" cols="34"><%
		Vector vector = list.getList();
		if (vector != null && vector.size() > 0) {
			for (int i = 0; i < vector.size(); i++) {
				out.println(vector.elementAt(i));
			}
		}
	%>
	</textarea>
</body>
		<%
	}
	%>	


</html>