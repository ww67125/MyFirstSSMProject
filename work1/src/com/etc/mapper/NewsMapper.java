package com.etc.mapper;

import com.etc.entity.News;
import com.etc.entity.NewsPage;
import com.etc.entity.NewsType;

import java.util.List;

public interface NewsMapper {
    List<NewsType> findNewsTypeList();
    //添加新闻
    boolean insertNews(News news);
    //查询新闻列表
    //List<News> findNewsList();
    List<News> findNewsList(NewsPage newsPage);

    //更新下载次数
    boolean updateDownloadCount(int newsid);

    //查询总记录数
    int findTotalCount();

}
