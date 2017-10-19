package com.etc.controller;

import com.etc.entity.News;
import com.etc.entity.NewsPage;
import com.etc.entity.NewsType;
import com.etc.entity.User;
import com.etc.service.NewsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Value("${addNewsScore}")
    private int addNewsScore;
    @Value("${pageSize}")
    private int pageSize;
    //新闻添加初始化
    @RequestMapping("init")
    public String init(Model model) throws Exception {

        List<NewsType> newsTypeList = newsService.getNewsTypeList();

        model.addAttribute("newsTypeList", newsTypeList);

        return "news_add";
    }
    //添加新闻
    @RequestMapping("add")
    public String add(News news, MultipartFile file, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

        //设置响应的输出类型
        response.setContentType("text/html;charSet=utf-8");

        //获取out对象
        PrintWriter out = response.getWriter();

        //****************文件上传****************
        if(file.getSize()!=0){

            //获取原始文件名
            String originalFilename = file.getOriginalFilename();

            //获取ServletContext对象
            ServletContext servletContext = request.getServletContext();

            //准备上传文件的存储路径
            String serverFilePath = servletContext.getRealPath("/attachment");

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

            //更新附件的文件名
            news.setAttachment(serverFileName);

            //更新附件的原始文件名
            news.setTruename(originalFilename);
        }

        //****************文件上传****************

        //填充发布时间
        news.setPubtime(new Date());

        //从session属性范围中取出用户对象
        User user = (User)session.getAttribute("user");

        news.setUser(user);

        //调用业务方法
        if(newsService.addNews(news, user, addNewsScore)){
            out.println("<script>if(confirm('新闻添加成功，是否继续添加新闻')){location='init'}else{location='query'}</script>");
        }else{
            out.println("<script>alert('新闻添加失败，请检查输入是否输入正确');history.back()</script>");
        }

        return null;
    }
    //查询新闻列表
    @RequestMapping("query")
    public String query(@RequestParam(defaultValue="1")int currentPage, Model model) throws Exception {

        //调用业务层的方法
        NewsPage newsPage = newsService.getNewsPage(currentPage, pageSize);

        model.addAttribute("newsPage", newsPage);

        return "news_list";
    }

    //新闻附件下载
    @RequestMapping("download")
    public void download(News news, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        //获取servletContext对象
        ServletContext servletContext = request.getServletContext();

        //构造字节输入流
        InputStream is = servletContext.getResourceAsStream("/attachment/" + news.getAttachment());
        //InputStream is = new FileInputStream(servletContext.getRealPath("/attachment/") + "/" + news.getAttachment());

        //处理文件名的中文乱码问题
        String truename = new String(news.getTruename().getBytes("utf-8"), "iso8859-1");

        //设置文件的处理方式为下载
        response.setHeader("Content-Disposition", "attachment;filename=" + truename);

        //获取字节输出流
        ServletOutputStream os = response.getOutputStream();

        //拷贝字节流
        IOUtils.copy(is, os);

        //从session属性范围中获取当前用户对象
        User user = (User)session.getAttribute("user");

        //调用业务方法
        newsService.download(user, news);
    }

}
