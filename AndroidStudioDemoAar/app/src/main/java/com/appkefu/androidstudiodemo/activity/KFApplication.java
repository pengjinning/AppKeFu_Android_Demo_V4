package com.appkefu.androidstudiodemo.activity;

import android.app.Application;

import com.appkefu.lib.interfaces.KFAPIs;

/**
 * Created by 金鹏 on 2016/10/27.
 */

public class KFApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //默认关闭调试模式
        KFAPIs.DEBUG = false;
        //第一个参数默认设置为false, 即登录普通服务器, 如果设置为true, 则登录IP服务器,
        //注意: 当第一个参数设置为true的时候, 客服端需要选择登录ip服务器 才能够会话
        //正常情况下第一个参数请设置为false
        KFAPIs.enableIPServerMode(false, this);
        //第一种登录方式，推荐
        KFAPIs.visitorLogin(this);

        //第二种登录方式，传入user_id , 注意user_id中只能包含数字、字母和下划线_,不能含有汉字
//		KFAPIs.loginWithUserID("1111111111", this);

        //第三中登录方式，首先使用user_id调用注册接口注册一个用户，一个user_id只能注册一次，不能重复注册
        //KFAPIs.registerWithUsernameAndPassword(userid, password, this);
        //注册用户之后，使用user_id调用登录接口登录
        //KFAPIs.loginWithUsernameAndPassword(userid, password, this);
    }


}




