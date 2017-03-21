package com.dev.nutclass.constants;

/**
 * Created by Administrator on 2017/1/12.
 */
public class UrlConst {
    public static final int SUCCESS_CODE = 1;
    public static final String BASE_URL = "https://dev.kobiko.cn/api/Index/";
    public static final String BASE_HOST_URL_NEW = "http://new.kobiko.cn/Api/Appapi/";
    //获取京东分类
    public static final String KJD_CAT_URL=BASE_HOST_URL_NEW+"jd_kepler_list?gcate=%s";

    /**
     *用户订单页
     * */
    public static final String USER_ORDER_URL=BASE_URL+"individualOrderList";

    /**
     * 用户订单详情页
     * */
    public static final String USER_ORDER_DETAIL_URL = BASE_URL+"orderDetailsList";
    /**
     * 测试接口
     * */
    public static final String COURSE_LIST_URL = "http://dev.kobiko.cn/api/index/getCourseList";
    public static final String COURSE_DETAIL_URL = "http://dev.kobiko.cn/api/index/getCourseDetailsList";
    public static final String SCHOOL_DETAIL_URL = "https://dev.kobiko.cn/api/Index/getSchoolDetailsList?schoolId=123";

    //校区列表页接口
    public static  final String SCHOOL_LIST_URL="http://dev.kobiko.cn/api/index/schoolList?current_page=5";

    //正课列表接口
    public static final  String SCHOOL_TO_COURSE_LIST_URL ="http://dev.kobiko.cn/api/index/schoolDetailSonNormalGoodsList?goods_type=1";
    //首页接口
    public static final String HOME_LIST_URL="https://dev.kobiko.cn/api/Index/getHome";
    //收藏课程接口
    public static final String COLLECT_COURSE_LIST_URL="https://dev.kobiko.cn/api/Index/getCollectionGoodsList";
    //收藏机构接口
    public static  final String COLLECT_SCHOOL_LIST_URL="https://dev.kobiko.cn/api/Index/getCollectionSchoolList";

    //其他校区接口
    public  static  final  String OTHER_SCHOOL_LIST_URL ="https://dev.kobiko.cn/api/Index/getOrderSchoolList";
    //课程评论列表接口
    public static  final String COURSE_COMMENT_LIST_URL=BASE_URL+"getGoodsCommentsList";
    //课程评论详情接口
    public static final String COURSE_COMMENT_DETAIL_URL = BASE_URL+"getGoodsCommentsListDetails";

    /**
     * 注册
     * */
    //请求验证码接口
    public static  final String REQ_VERIFY_CODE_URL = "https://dev.kobiko.cn/api/Index/get_mobile_captcha";
    //校验验证码接口
    public static final  String CHECK_VERIFY_CODE_URL = BASE_URL+"checkVerificationCode";
    //设置密码接口
    public static final String SET_PWD_URL=BASE_URL+"userRegister";
    /**
     * 登录
     * */
    public static final String ACCOUNT_PWS_LOGIN_URL= BASE_URL+"userLogin";
    //三方登录接口
    public static  final String THIRD_LOGIN_URL =BASE_URL+"getOtherInfo";
    //绑定手机号
    public static final String BIND_PHONE_URL = BASE_URL+"bindingPhone";
    //个人中心页编辑用户信息
    public static final String EDIT_USER_INFO_URL= BASE_URL+"userInfoEdit";
    //浏览记录接口
    public static final String LOOK_HISTORY_URL=BASE_URL+"userLookLog";
    //修改密码接口
    public static  final String CHANGE_PWD_URL = BASE_URL+"userPasswordEdit";

    //点赞接口
    public static final String CLICK_PRAISE_URL = BASE_URL+"comment_click_praise";
    //收藏课程
    public static  final String COLLECT_COURSE_URL = BASE_URL+"addCollectionGoods";
    //收藏校区
    public static final String COLLECT_SCHOOL_URL = BASE_URL+"addCollectionSchool";
    //删除收藏课程
    public static final String DELETE_COURSE_RUL = BASE_URL+"delCollectionGoods";
    //删除收藏校区
    public static final String DELETE_SCHOOL_RUL= BASE_URL+"delCollectionSchool";
}
