package com.dev.nutclass.constants;

/**
 * Created by Administrator on 2017/1/12.
 */
public class UrlConst {
    public static final int SUCCESS_CODE = 1;
    public static final String BASE_HOST_URL_NEW = "http://new.kobiko.cn/Api/Appapi/";
    //获取京东分类
    public static final String KJD_CAT_URL=BASE_HOST_URL_NEW+"jd_kepler_list?gcate=%s";




    /**
     * 测试接口
     * */
    public static final String COURSE_LIST_URL = "http://dev.kobiko.cn/api/index/getCourseList";
    public static final String COURSE_DETAIL_URL = "http://dev.kobiko.cn/api/index/getCourseDetailsList?goodsId=1";
    public static final String SCHOOL_DETAIL_URL = "https://dev.kobiko.cn/api/Index/getSchoolDetailsList?schoolId=123";

    //校区列表页接口
    public static  final String SCHOOL_LIST_URL="http://dev.kobiko.cn/api/index/schoolList?current_page=5";

    //正课列表接口
    public static final  String SCHOOL_TO_COURSE_LIST_URL ="http://dev.kobiko.cn/api/index/schoolDetailSonNormalGoodsList?goods_type=1";
}
