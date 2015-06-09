package com.flyou.henucenter.utils;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：NewsUrl
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-19 下午11:04:50
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class NewsUrl {
  public static final String TAG = "NewsUrl";
  /*
   * 新闻客户端各个板块的Url
   */

  public final static String BASE_SCHOOL_NEWS = "http://news.henu.edu.cn/html/xxxw/";
  public static int COUNT_SCHOOL_NEWS = 1;
  public final static String END_SCHOOL_NEWS = ".html";

  // 学校新闻 = BASE_SCHOOL_NEWS+COUNT_SCHOOL_NEWS+END_SCHOOL_NEWS

  public final static String BASE_DEPARTMENT_NEWS = "http://news.henu.edu.cn/news/submore/page/";
  public static int COUNT_DEPARTMENT_NEWS = 1;
  public final static String END_DEPARTMENT_NEWS = "/totalpage/152/class/6/parentid/6";
  // 院系新闻=BASE_DEPARTMENT_NEWS+COUNT_DEPARTMENT_NEWS+END_DEPARTMENT_NEWS

  public final static String BASE_SChOOL_MESSAGE = "http://news.henu.edu.cn/html/gg/xxgg/";
  public static int COUNT_SChOOL_MESSAGE = 1;
  public final static String END_SChOOL_MESSAGE = ".html";
  // 学校公告

  public final static String BASE_EDU_MESSAGE = "http://news.henu.edu.cn/html/gg/xsgg/";
  public static int COUNT_EDU_MESSAGE = 1;
  public final static String END_EDU_MESSAGE = ".html";
  // 学术公告

  public final static String BASE_MEDIA_NEWS = "http://news.henu.edu.cn/news/submore/page/";
  public static int COUNT_MEDIA_NEWS = 1;
  public final static String END_MEDIA_NEWS = "/totalpage/310/class/16/parentid/16";
  // 媒体河大

  // --------------------------------------------------------------------------------------------------------------
  // ------------------------------------------ 以下是各个院系的的新闻url
  // ---------------------------------
  // --------------------------------------------------------------------------------------------------------------

  // 新闻唯一标识id为0
  public static final String BASE_JKY = "http://jykx.henu.edu.cn/html/xwzx/";
  public static int COUNT_JKY = 1;
  public final static String END__JKY = ".html";
  // 教科院

  // 新闻唯一标识id为1
  public static final String BASE_MINSHENG = "http://minsheng.henu.edu.cn/a/minshengxinwenwang/xueyuanxinwen/list_26_";
  public static int COUNT_MINSHENG = 1;
  public final static String END__MINSHENG = ".html";
  // 民生学院

  // 新闻唯一标识id为2
  public static final String BASE_FA = "http://fxy.henu.edu.cn/index.php/former/article_list/16/";
  public static int COUNT_FA = 1;

  // 法学院

  // 新闻唯一标识id为3
  public static final String BASE_WENXUE = "http://wxy.henu.edu.cn/a/xinwengonggao/xueyuanxinwen/list_12_";
  public static int COUNT_WENXUE = 1;
  public final static String END__WENXUE = ".html";
  // 文学院

  // 新闻唯一标识id为4
  public static final String BASE_YAO = "http://yxy.henu.edu.cn/";
  public static int COUNT_YAO = 1;
  public final static String END__YAO = ".html";
  // 药学院

  // 新闻唯一标识id为5
  public static final String BASE_YI = "http://yixue.henu.edu.cn/news/news.php?lang=cn&class1=2&class2=4&page=";
  public static int COUNT_YI = 1;

  // 医学院

  // 新闻唯一标识id为8
  public static final String BASE_HUANJING = "http://cep.henu.edu.cn/main/list.php?pType=%E7%BB%BC%E5%90%88%E6%96%B0%E9%97%BB&type=%E5%AD%A6%E9%99%A2%E6%96%B0%E9%97%BB&page=";
  public static int COUNT_HUANJING = 1;
  // 环境规划学院

  // 新闻唯一标识id为9
  public static final String BASE_COMPUTER = "http://cs.henu.edu.cn/index.php/former/article_list/39/";
  public static int COUNT_COMPUTER = 1;

  // 计算机学院

  // 新闻唯一标识id为121
  public static final String BASE_OYA = "http://oy.henu.edu.cn/html/xwzx/xyxw/";
  public static int COUNT_OYA = 1;
  public final static String END_OYA = ".html";
  // 欧亚国际学院

  // 新闻唯一标识id为12
  public static final String BASE_LIFE = "http://www.bio.henu.edu.cn/a/xinwengonggao/xueyuanxinwen/list_3_";
  public static int COUNT_LIFE = 1;

  public final static String END_LIFE = ".html";
  // 生命科学学院

  // 新闻唯一标识id为13
  public static final String BASE_ZHEXUE = "http://zg.henu.edu.cn/index.php/former/article_list/32/";
  public static int COUNT_ZHEXUE = 1;

  // 哲管院

  // ………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………
  
  
  
  
  
  // 获得校园新闻的url
  public static String getSchoolNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_SCHOOL_NEWS + COUNT_SCHOOL_NEWS + END_SCHOOL_NEWS;
    } else {

      path = BASE_SCHOOL_NEWS + count + END_SCHOOL_NEWS;
    }

    return path;
  }

  // 获得学校公告的url
  public static String getSchoolMessageUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_SChOOL_MESSAGE + COUNT_SChOOL_MESSAGE + END_SChOOL_MESSAGE;
    } else {

      path = BASE_SChOOL_MESSAGE + count + END_SChOOL_MESSAGE;
    }

    return path;
  }

  // 获得院系新闻的url
  public static String getDepartmentNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_DEPARTMENT_NEWS + count + END_DEPARTMENT_NEWS;
    }

    return path;
  }

  // 获得学术公告的url
  public static String getEduNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_EDU_MESSAGE + COUNT_EDU_MESSAGE + END_EDU_MESSAGE;
    } else {

      path = BASE_EDU_MESSAGE + count + END_EDU_MESSAGE;
    }

    return path;
  }

  // 获得媒体河大的url
  public static String getMediaNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_MEDIA_NEWS + count + END_MEDIA_NEWS;
    }

    return path;
  }

  // 获得文学院的url
  public static String getWENXUEUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_WENXUE + count + END__WENXUE;
    }

    return path;
  }

  // 获得民生学院的url
  public static String getMINSHENGUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_MINSHENG + count + END__MINSHENG;
    }

    return path;
  }

  // 获得哲管学院的url
  public static String getZHEXUEUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_ZHEXUE;
    } else {

      path = BASE_ZHEXUE + (count * 5 - 5);
    }

    return path;
  }

  // 获得教科院的url
  public static String getJKYUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_JKY;
    } else {

      path = BASE_JKY + count + END__JKY;
    }

    return path;
  }

  // 获得哲管学院的url
  public static String getFAUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_FA;
    } else {

      path = BASE_FA + (count * 10 - 10);
    }

    return path;
  }

  // 获得药学院的url
  public static String getYAOUrl(int count) {

    String path;

    path = BASE_YAO;

    return path;
  }

  // 获得医学院的url
  public static String getYIUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_YI + COUNT_YI;
    } else {

      path = BASE_YI + count;
    }

    return path;
  }

  // 获得环境规划学院的url
  public static String getHUANJINGUrl(int count) {

    String path;
    if (count <= 0 || count > 3) {
      path = BASE_HUANJING + COUNT_HUANJING;
    } else {

      path = BASE_HUANJING + count;
    }

    return path;
  }

  // 获得生命科学学院学院的url

  public static String getLIFEUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_LIFE + COUNT_LIFE;
    } else {

      path = BASE_LIFE + count + END_LIFE;
    }

    return path;
  }

  // 获得计算机学院的url
  public static String getCOMPUTERUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_COMPUTER;
    } else {

      path = BASE_COMPUTER + (count * 10 - 10);
    }

    return path;
  }
  
  
  // 获得欧亚国际学院的url
  public static String getOYAUrl(int count) {

    String path;
    if (count <= 0||count>=8) {
      path = BASE_OYA;
    } else {

      path = BASE_OYA +count+END__YAO;
    }

    return path;
  }
}
