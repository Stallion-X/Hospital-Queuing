package servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.PatientUserService;

/**
 * Servlet implementation class PersonalCenterServlet
 */
/** 
* @author Stallion-X
*/
@WebServlet("/PersonalCenterServlet")
@MultipartConfig()
public class PersonalCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalCenterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int type = -1;
		String oldpwd = null;
		String newpwd = null;
		String newpwd2 = null;
		String pnum = request.getParameter("pnum");
		String path = null;
		PatientUserService userService = new PatientUserService();
		if (request.getParameter("oldpwd")!=null&&request.getParameter("newpwd")!=null&&request.getParameter("newpwd2")!=null) {
			oldpwd = request.getParameter("oldpwd");
			newpwd = request.getParameter("newpwd");
			newpwd2 = request.getParameter("newpwd2");
			if (newpwd.equals(newpwd2)) {
				if (oldpwd.equals(userService.getPwd(pnum))) {
					userService.editPwd(pnum, newpwd);
				}
				else {
					request.setAttribute("editpwd", "0");
					path = "PersonalCenter.jsp?from=fromP";
				}
			}
			else {
				request.setAttribute("editpwd", "0");
				path = "PersonalCenter.jsp?from=fromP";
			}
		}
		if (request.getPart("file")!=null) {
			type = upload(request,response);
		}
		if (type==0) {
			path = "PersonalCenter.jsp?from=fromP";
		}
		else if (type==1) {
			path = "PersonalCenter.jsp?from=fromD";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	protected int upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = -1;
		try {
			String fname = null;
			String root = null;
			if (request.getParameter("pnum")!=null) {
				fname = request.getParameter("pnum");
				root=getServletContext().getRealPath("/pic/ppic");
	            //root="C:\\Users\\only\\eclipse-j2ee\\HospitalTest\\src\\main\\webapp\\pic\\ppic";
	            request.setAttribute("returnstate", "p");
	            request.setAttribute("returnpnum", fname);
	            type = 0;
			}
			else {
				fname = request.getParameter("dnum");
				root=getServletContext().getRealPath("/pic/dpic");
	            //root="C:\\Users\\only\\eclipse-j2ee\\HospitalTest\\src\\main\\webapp\\pic\\dpic";
	            request.setAttribute("returnstate", "d");
	            type = 1;
			}
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
             //System.out.println(name);//测试使用 
             
             //获取文件的后缀
           String str=name.substring(name.lastIndexOf("."), name.length()-1);
           //System.out.println("测试获取文件的后缀："+str);
            
             String filename=root+"\\"+fname+".jpg";
           //System.out.println("测试产生新的文件名："+filename);

            part.write(filename);
             
            request.setAttribute("info", "上传文件成功");
        } catch (Exception e) {
           e.printStackTrace();
             request.setAttribute("info", "上传文件失败");
        }
		return type;
	}
}
