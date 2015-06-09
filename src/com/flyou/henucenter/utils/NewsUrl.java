package com.flyou.henucenter.utils;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�NewsUrl
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-19 ����11:04:50
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class NewsUrl {
  public static final String TAG = "NewsUrl";
  /*
   * ���ſͻ��˸�������Url
   */

  public final static String BASE_SCHOOL_NEWS = "http://news.henu.edu.cn/html/xxxw/";
  public static int COUNT_SCHOOL_NEWS = 1;
  public final static String END_SCHOOL_NEWS = ".html";

  // ѧУ���� = BASE_SCHOOL_NEWS+COUNT_SCHOOL_NEWS+END_SCHOOL_NEWS

  public final static String BASE_DEPARTMENT_NEWS = "http://news.henu.edu.cn/news/submore/page/";
  public static int COUNT_DEPARTMENT_NEWS = 1;
  public final static String END_DEPARTMENT_NEWS = "/totalpage/152/class/6/parentid/6";
  // Ժϵ����=BASE_DEPARTMENT_NEWS+COUNT_DEPARTMENT_NEWS+END_DEPARTMENT_NEWS

  public final static String BASE_SChOOL_MESSAGE = "http://news.henu.edu.cn/html/gg/xxgg/";
  public static int COUNT_SChOOL_MESSAGE = 1;
  public final static String END_SChOOL_MESSAGE = ".html";
  // ѧУ����

  public final static String BASE_EDU_MESSAGE = "http://news.henu.edu.cn/html/gg/xsgg/";
  public static int COUNT_EDU_MESSAGE = 1;
  public final static String END_EDU_MESSAGE = ".html";
  // ѧ������

  public final static String BASE_MEDIA_NEWS = "http://news.henu.edu.cn/news/submore/page/";
  public static int COUNT_MEDIA_NEWS = 1;
  public final static String END_MEDIA_NEWS = "/totalpage/310/class/16/parentid/16";
  // ý��Ӵ�

  // --------------------------------------------------------------------------------------------------------------
  // ------------------------------------------ �����Ǹ���Ժϵ�ĵ�����url
  // ---------------------------------
  // --------------------------------------------------------------------------------------------------------------

  // ����Ψһ��ʶidΪ0
  public static final String BASE_JKY = "http://jykx.henu.edu.cn/html/xwzx/";
  public static int COUNT_JKY = 1;
  public final static String END__JKY = ".html";
  // �̿�Ժ

  // ����Ψһ��ʶidΪ1
  public static final String BASE_MINSHENG = "http://minsheng.henu.edu.cn/a/minshengxinwenwang/xueyuanxinwen/list_26_";
  public static int COUNT_MINSHENG = 1;
  public final static String END__MINSHENG = ".html";
  // ����ѧԺ

  // ����Ψһ��ʶidΪ2
  public static final String BASE_FA = "http://fxy.henu.edu.cn/index.php/former/article_list/16/";
  public static int COUNT_FA = 1;

  // ��ѧԺ

  // ����Ψһ��ʶidΪ3
  public static final String BASE_WENXUE = "http://wxy.henu.edu.cn/a/xinwengonggao/xueyuanxinwen/list_12_";
  public static int COUNT_WENXUE = 1;
  public final static String END__WENXUE = ".html";
  // ��ѧԺ

  // ����Ψһ��ʶidΪ4
  public static final String BASE_YAO = "http://yxy.henu.edu.cn/";
  public static int COUNT_YAO = 1;
  public final static String END__YAO = ".html";
  // ҩѧԺ

  // ����Ψһ��ʶidΪ5
  public static final String BASE_YI = "http://yixue.henu.edu.cn/news/news.php?lang=cn&class1=2&class2=4&page=";
  public static int COUNT_YI = 1;

  // ҽѧԺ

  // ����Ψһ��ʶidΪ8
  public static final String BASE_HUANJING = "http://cep.henu.edu.cn/main/list.php?pType=%E7%BB%BC%E5%90%88%E6%96%B0%E9%97%BB&type=%E5%AD%A6%E9%99%A2%E6%96%B0%E9%97%BB&page=";
  public static int COUNT_HUANJING = 1;
  // �����滮ѧԺ

  // ����Ψһ��ʶidΪ9
  public static final String BASE_COMPUTER = "http://cs.henu.edu.cn/index.php/former/article_list/39/";
  public static int COUNT_COMPUTER = 1;

  // �����ѧԺ

  // ����Ψһ��ʶidΪ121
  public static final String BASE_OYA = "http://oy.henu.edu.cn/html/xwzx/xyxw/";
  public static int COUNT_OYA = 1;
  public final static String END_OYA = ".html";
  // ŷ�ǹ���ѧԺ

  // ����Ψһ��ʶidΪ12
  public static final String BASE_LIFE = "http://www.bio.henu.edu.cn/a/xinwengonggao/xueyuanxinwen/list_3_";
  public static int COUNT_LIFE = 1;

  public final static String END_LIFE = ".html";
  // ������ѧѧԺ

  // ����Ψһ��ʶidΪ13
  public static final String BASE_ZHEXUE = "http://zg.henu.edu.cn/index.php/former/article_list/32/";
  public static int COUNT_ZHEXUE = 1;

  // �ܹ�Ժ

  // ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
  
  
  
  
  
  // ���У԰���ŵ�url
  public static String getSchoolNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_SCHOOL_NEWS + COUNT_SCHOOL_NEWS + END_SCHOOL_NEWS;
    } else {

      path = BASE_SCHOOL_NEWS + count + END_SCHOOL_NEWS;
    }

    return path;
  }

  // ���ѧУ�����url
  public static String getSchoolMessageUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_SChOOL_MESSAGE + COUNT_SChOOL_MESSAGE + END_SChOOL_MESSAGE;
    } else {

      path = BASE_SChOOL_MESSAGE + count + END_SChOOL_MESSAGE;
    }

    return path;
  }

  // ���Ժϵ���ŵ�url
  public static String getDepartmentNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_DEPARTMENT_NEWS + count + END_DEPARTMENT_NEWS;
    }

    return path;
  }

  // ���ѧ�������url
  public static String getEduNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = BASE_EDU_MESSAGE + COUNT_EDU_MESSAGE + END_EDU_MESSAGE;
    } else {

      path = BASE_EDU_MESSAGE + count + END_EDU_MESSAGE;
    }

    return path;
  }

  // ���ý��Ӵ��url
  public static String getMediaNewsUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_MEDIA_NEWS + count + END_MEDIA_NEWS;
    }

    return path;
  }

  // �����ѧԺ��url
  public static String getWENXUEUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_WENXUE + count + END__WENXUE;
    }

    return path;
  }

  // �������ѧԺ��url
  public static String getMINSHENGUrl(int count) {

    String path;
    if (count < 0) {
      path = "";
    } else {

      path = BASE_MINSHENG + count + END__MINSHENG;
    }

    return path;
  }

  // ����ܹ�ѧԺ��url
  public static String getZHEXUEUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_ZHEXUE;
    } else {

      path = BASE_ZHEXUE + (count * 5 - 5);
    }

    return path;
  }

  // ��ý̿�Ժ��url
  public static String getJKYUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_JKY;
    } else {

      path = BASE_JKY + count + END__JKY;
    }

    return path;
  }

  // ����ܹ�ѧԺ��url
  public static String getFAUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_FA;
    } else {

      path = BASE_FA + (count * 10 - 10);
    }

    return path;
  }

  // ���ҩѧԺ��url
  public static String getYAOUrl(int count) {

    String path;

    path = BASE_YAO;

    return path;
  }

  // ���ҽѧԺ��url
  public static String getYIUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_YI + COUNT_YI;
    } else {

      path = BASE_YI + count;
    }

    return path;
  }

  // ��û����滮ѧԺ��url
  public static String getHUANJINGUrl(int count) {

    String path;
    if (count <= 0 || count > 3) {
      path = BASE_HUANJING + COUNT_HUANJING;
    } else {

      path = BASE_HUANJING + count;
    }

    return path;
  }

  // ���������ѧѧԺѧԺ��url

  public static String getLIFEUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_LIFE + COUNT_LIFE;
    } else {

      path = BASE_LIFE + count + END_LIFE;
    }

    return path;
  }

  // ��ü����ѧԺ��url
  public static String getCOMPUTERUrl(int count) {

    String path;
    if (count <= 0) {
      path = BASE_COMPUTER;
    } else {

      path = BASE_COMPUTER + (count * 10 - 10);
    }

    return path;
  }
  
  
  // ���ŷ�ǹ���ѧԺ��url
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
