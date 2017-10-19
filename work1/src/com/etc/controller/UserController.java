package com.etc.controller;

import com.etc.entity.User;
import com.etc.entity.UserVO;
import com.etc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class UserController {
    @Value("${loginscore}")
    private int loginscore;
    @Value("${initscore}")
    private int initscore;
    @Value("${maxFileSize}")
    private int maxFileSize;
    @Value("${allowedFileList}")
    private String allowedFileList;
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public String login(User user, HttpSession session, HttpServletResponse response) throws IOException {
        user.setScore(loginscore);
        user = userService.login(user);
        session.setAttribute("user",user);
        if(user!=null){    //登录成功

            return "redirect:/index.jsp";    //进入主页
        }else{

            //设置响应的输出类型
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.print("<script>alert('错误');location='login.jsp'</script>");
            return null;
//            return "redirect:/login.jsp";    //返回登录页面
        }
    }
    @RequestMapping("register")
    public String register(@Valid User user, BindingResult result, String valCode, MultipartFile file, HttpSession session, HttpServletResponse response, HttpServletRequest request ) throws IOException {
        user.setScore(initscore);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //服务器端数据校验---JSR303检验框架
        if(result.hasErrors()){

            String errorMessages = "";

            List<FieldError> fieldErrors = result.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                errorMessages += fieldError.getDefaultMessage() + "\\n";
            }

            out.println("<script>alert('" + errorMessages + "');history.back()</script>");

            return null;
        }//从session属性范围中取出正确的验证码
        String valCodeInSession = (String)session.getAttribute("valCodeInSession");

        //判断输入的验证码是否正确
        if(!valCode.equalsIgnoreCase(valCodeInSession)){

            out.println("<script>alert('验证码不正确，请重新输入');history.back();</script>");

            return null;
        }
        //****************自定义头像上传****************

        if("0.gif".equals(user.getPhoto())){    //选择了自定义头像

            //获取原始文件名
            String originalFilename = file.getOriginalFilename();

            //判断文件是否为空
            if(file.getSize()==0){
                out.println("<script>alert('自定义头像不能为空');history.back()</script>");
                return null;
            }

            //判断文件的大小
            if(file.getSize() > maxFileSize * 1024){
                out.println("<script>alert('文件大小不能超过" + maxFileSize + "k');history.back()</script>");
                return null;
            }

            //获取文件的扩展名
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //判断文件的类型
            if(!allowedFileList.contains(fileExtension)){
                out.println("<script>alert('只能上传" + allowedFileList + "文件');history.back()</script>");
                return null;
            }

            //获取ServletContext对象
            ServletContext servletContext = request.getServletContext();

            //准备上传文件的存储路径
            String serverFilePath = servletContext.getRealPath("/photo");
            //System.out.println("serverFilePath=" + serverFilePath);

            File filePath = new File(serverFilePath);
            if(!filePath.exists()){
                filePath.mkdir();
            }

            //文件改名
            String serverFileName = StringUtil.convertFilename(originalFilename);

            //构造目标文件对象
            File dest = new File(serverFilePath + "/" + serverFileName);

            //文件拷贝
            file.transferTo(dest);

            //更新头像的文件名
            user.setPhoto(serverFileName);
        }

        if (userService.register(user)){

            session.setAttribute("user",user);
            return "redirect:/result.jsp";
        }else {

            out.print("<script>alert('检查格式是否正确');history.back()</script>");
            return null;
        }

    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }
    @RequestMapping("userlist")
    public String allUser(HttpSession session){
        session.setAttribute("userlist",userService.allUser());
        return "userlist";
    }
    @RequestMapping("finduserlist")
    public String findUserList(UserVO userVO, Model model){
        List<User> list=userService.getUserList(userVO);
        model.addAttribute("userlist",list);
        model.addAttribute("user",userVO);
        return "userlist";
    }
    @RequestMapping("delete")
    public void delete(int userid, HttpServletResponse response) throws Exception {

        //设置响应的输出类型
        response.setContentType("text/html;charSet=utf-8");

        //获取out对象
        PrintWriter out = response.getWriter();

        //调用业务方法
        if(userService.deleteUser(userid)){
            out.println("<script>alert('用户删除成功');location='finduserlist'</script>");
        }else{
            out.println("<script>alert('用户删除失败');history.back()</script>");
        }

    }
    //检查用户名是否存在
    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(String username) throws Exception {

        System.out.println("username=" + username);
        if(userService.checkUsername(username)){   //用户名存在
            return "no";   //不可用
        }else{
            return "yes";  //可用
        }

    }
    //检查验证码输入是否正确
    @RequestMapping("/checkValCode")
    @ResponseBody
    public String checkValCode(String valCode, HttpSession session) throws Exception {

        //从session属性范围中取出正确的验证码
        String valCodeInSession = (String)session.getAttribute("valCodeInSession");

        //判断输入的验证码是否正确
        if(!valCode.equalsIgnoreCase(valCodeInSession)){
            return "no";
        }else{
            return "yes";
        }
    }

}
