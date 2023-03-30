/**
 * 
 */
 /** 
 * @author Stallion-X
 */
var xmlhttp;
if (window.XMLHttpRequest) {
    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
}
else {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
function getScrollTop() {
	if (typeof window.pageYOffset !== 'undefined' ) {
	return window.pageYOffset;
	}
	var d = document.documentElement;
	if (d.clientHeight) {
		return d.scrollTop;
	}
		return document.body.scrollTop;
}
var lastscroll = 30;
window.onscroll = function() {
	var box = document.getElementById("leftbox");
	var bar = document.getElementById("blob");
	scroll = getScrollTop();
	if (scroll <= 28&&scroll>0) {
		box.style.marginTop = "30px";
		bar.style.marginTop = "30px";
	}
	else {
		window.requestAnimationFrame(leftanimation);
		lastscroll = scroll;
	}
	
};
function leftanimation() {
	var box = document.getElementById("leftbox");
	var bar = document.getElementById("blob");
	box.animate(
		[{
			marginTop: lastscroll + 'px',
		},
		{
			marginTop: eval(scroll + 2) + 'px',
		}
		],
		{
			duration: 1000,
			easing: 'linear',
		}
	);
	bar.animate(
		[{
			marginTop: lastscroll + 'px',
		},
		{
			marginTop: eval(scroll + 2) + 'px',
		}
		],
		{
			duration: 1000,
			easing: 'linear',
		}
	);
	box.style.marginTop = (scroll + 2) + "px";
	bar.style.marginTop = (scroll + 2) + "px";
}
 function enterdown() {
	var event = arguments.callee.caller.arguments[0] || window.event;
	if (event.keyCode == 13) {
		var str = document.getElementById("search").value;
		var reg = /^[A-Za-z]\d{3}$/;
		if (reg.test(str)) {
			alert("匹配ID！");
		}
		else {
			alert("不匹配ID！");
		}
	}
}
function processfrm(size) {
	for (var i=0;i<size;i++) {
		var str = "ck"+(i+1);
		if (document.getElementById(str).checked==true) {
			document.getElementById("frmanum").value=document.getElementById("realanum"+(i+1)).innerHTML;
			break;
		}
	}
}
function editstate() {
	var astate = prompt("请输入要修改的状态：");
	document.getElementById("frmastate").value=astate;
	document.getElementById("frmaction").value="editAppo";
}
function btn2() {
	document.getElementById("frmaction").value="priorAppo";
}
function btn3(size) {
	for (var i=0;i<size;i++) {
		var str = "ck"+(i+1);
		document.getElementById(str).checked=false;
	}
	document.getElementById("frm2anum").value=size+1;
	document.getElementsByClassName("bkg")[0].animate(
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
	document.getElementsByClassName("bkg")[0].style.visibility="visible";
	document.getElementById("inputedit").style.visibility="visible";
/*	document.getElementById("frmpnum").value=prompt("患者编号：");
	document.getElementById("frmddepartment").value=prompt("所在科室：");
	document.getElementById("frmdnum").value=prompt("医生编号：");
	document.getElementById("frmyytime").value=prompt("预约时间：");
	document.getElementById("frmjztime").value=prompt("就诊时间：");
	document.getElementById("frmdcon").value=prompt("就诊诊室：");
	document.getElementById("frmastate").value=prompt("就诊状态：");
	document.getElementById("frmatype").value=prompt("队列类型：");*/
	
}
function btn4() {
	document.getElementById("frmaction").value="delAppo";
}
function btn5() {
	document.getElementById("frmaction").value="pushAppo";
}
function canceledit() {
	document.getElementsByClassName("bkg")[0].animate(
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
	document.getElementsByClassName("bkg")[0].style.visibility="hidden";
	document.getElementById("inputedit").style.visibility="hidden";
}
function setCookie(c_name, value, expiredays){
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}
function exitmain() {
	setCookie('dcon','',-1);
}
function hangon() {
	document.getElementsByClassName("bkg")[3].animate(
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
	document.getElementsByClassName("bkg")[3].style.visibility="visible";
	document.getElementById("hangon").style.visibility="visible";
	document.getElementsByClassName("bkg")[3].onclick = function() {
		document.getElementsByClassName("bkg")[3].animate(
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
		document.getElementsByClassName("bkg")[3].style.visibility="hidden";
		document.getElementById("hangon").style.visibility="hidden";
	};
}
function transfer() {
	document.getElementsByClassName("bkg")[4].animate(
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
	document.getElementsByClassName("bkg")[4].style.visibility="visible";
	document.getElementById("transfer").style.visibility="visible";
}
function canceltransfer() {
	document.getElementsByClassName("bkg")[4].animate(
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
		document.getElementsByClassName("bkg")[4].style.visibility="hidden";
		document.getElementById("transfer").style.visibility="hidden";
}
function newdrug() {
	document.getElementsByClassName("bkg")[1].animate(
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
	document.getElementsByClassName("bkg")[1].style.visibility="visible";
	document.getElementById("inputnewdrug").style.visibility="visible";
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
        document.getElementById("frmtmnum").value=eval(xmlhttp.responseText)+1;
        }
	}
	xmlhttp.open("GET","DoctorServlet?action=getMaxTmnum",true);
	xmlhttp.send();
}
function cancelnewdrug() {
	document.getElementsByClassName("bkg")[1].animate(
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
	document.getElementsByClassName("bkg")[1].style.visibility="hidden";
	document.getElementById("inputnewdrug").style.visibility="hidden";
}
function newmexam() {
	document.getElementsByClassName("bkg")[2].animate(
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
	document.getElementsByClassName("bkg")[2].style.visibility="visible";
	document.getElementById("inputnewmexam").style.visibility="visible";
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
        document.getElementById("frmmtenum").value=eval(xmlhttp.responseText)+1;
        }
	}
	xmlhttp.open("GET","DoctorServlet?action=getMaxMtenum",true);
	xmlhttp.send();
}
function cancelnewmexam() {
	document.getElementsByClassName("bkg")[2].animate(
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
	document.getElementsByClassName("bkg")[2].style.visibility="hidden";
	document.getElementById("inputnewmexam").style.visibility="hidden";
}
function mouseoverp(pnum) {
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			var obj = JSON.parse(xmlhttp.responseText);
			var elems = document.getElementsByClassName("tip"+pnum);
			for (var i=0;i<elems.length;i++) {
				elems[i].innerHTML = "患者姓名："+obj.pname+"<br/>"+"患者性别："+obj.psex+"<br/>"+"患者年龄："+obj.page+"<br/>"+"身份证号："+obj.pid+"<br/>"+"患者电话："+obj.ptel+"<br/>"+"基本信息："+obj.pinfor;
			}
        }
	}
	xmlhttp.open("GET","DoctorServlet?action=getPInfo&pnum="+pnum,true);
	xmlhttp.send();
}
function mouseoverd(dnum) {
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			var obj = JSON.parse(xmlhttp.responseText);
			var elems = document.getElementsByClassName("tip"+dnum);
			for (var i=0;i<elems.length;i++) {
				elems[i].innerHTML = "医生姓名："+obj.dname+"<br/>"+"医生性别："+obj.dsex+"<br/>"+"医生年龄："+obj.dage+"<br/>"+"医生电话："+obj.dtel+"<br/>"+"职称："+obj.dtitle+"<br/>"+"职务："+obj.dpost+"<br/>"+"毕业院校："+obj.dedu+"<br/>"+"科室："+obj.ddepartment;
			}
        }
	}
	xmlhttp.open("GET","DoctorServlet?action=getDInfo&dnum="+dnum,true);
	xmlhttp.send();
}
function openInfoDiv() {
	
}