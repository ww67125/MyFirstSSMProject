package com.etc.service;

import com.etc.entity.News;
import com.etc.entity.NewsPage;
import com.etc.entity.NewsType;
import com.etc.entity.User;
import com.etc.mapper.NewsMapper;
import com.etc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public List<NewsType> getNewsTypeList() {
        return newsMapper.findNewsTypeList();
    }
    //添加新闻
    @Transactional
    public boolean addNews(News news, User user, int addNewsScore) {

        try{

            if(newsMapper.insertNews(news)){    //插入成功

                //给用户加分
                user.setScore(user.getScore() + addNewsScore);

                if(userMapper.updateUser(user)){   //加分成功

                    return true;
                }
            }

            return false;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }
    //查询新闻列表 --分页
    @Transactional
    public NewsPage getNewsPage(int currentPage, int pageSize) {

        //创建并填充分页Bean
        NewsPage newsPage = new NewsPage();

        //分页大小
        newsPage.setPageSize(pageSize);

        //当前页
        newsPage.setCurrentPage(currentPage);

        //记录偏移量
        int offset = (currentPage-1) * pageSize;
        newsPage.setOffset(offset);

        //查询数据列表
        List<News> newsList = newsMapper.findNewsList(newsPage);
        newsPage.setNewsList(newsList);

        //总记录
        int totalCount = newsMapper.findTotalCount();
        newsPage.setTotalCount(totalCount);

        //总页数
        int totalPage = (totalCount % pageSize==0) ? totalCount / pageSize :  totalCount / pageSize + 1;
        newsPage.setTotalPage(totalPage);

        //显示5个数字页码链接
        int pageNumber = 5;

        //起始页码
        int beginPage = currentPage - pageNumber / 2;

        //结束页码
        int endPage = currentPage + pageNumber / 2;

        if(beginPage < 1){
            beginPage = 1;
            endPage = pageNumber;
        }

        if(endPage > totalPage){
            endPage = totalPage;
            beginPage = totalPage - pageNumber + 1;
        }

        if(totalPage < pageNumber){
            beginPage = 1;
            endPage = totalPage;
        }

        newsPage.setBeginPage(beginPage);
        newsPage.setEndPage(endPage);

        return newsPage;
    }


    //新闻附件下载
    @Transactional
    public boolean download(User user, News news) {

        //给用户减分
        user.setScore(user.getScore() - news.getDownloadscore());

        if(userMapper.updateUser(user)){

            if(newsMapper.updateDownloadCount(news.getNewsid())){
                return true;
            }
        }

        return false;
    }
}
