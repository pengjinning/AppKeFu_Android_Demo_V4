# 微客服(Depreciated)

[![Join the chat at https://gitter.im/pengjinning/AppKeFu_Android_Demo_V4](https://badges.gitter.im/pengjinning/AppKeFu_Android_Demo_V4.svg)](https://gitter.im/pengjinning/AppKeFu_Android_Demo_V4?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
安卓客服demo 4.x 版本 - 致力于为开发者提供极致用户体验的SDK

## 请前往新平台

- [萝卜丝·云客服](https://github.com/pengjinning/bytedesk-android)

## aar 和 jar 集成方式只需要二选其一
* jar 包集成方式需要同时拷贝jar包和res资源
* aar 集成方式只需要拷贝集成aar包即可，build.gralde文件具体参考相应demo

其他:
iOS版：https://github.com/pengjinning/AppKeFu_iOS_Demo_V4

## 预览

<img src="/images/chat_list.png" alt="功能一览" width="260px" />
<img src="/images/chat_buy.png" alt="电商" width="260px" />
<img src="/images/chat_plus.png" alt="扩展" width="260px" />
<img src="/images/chat_voice.png" alt="语音" width="260px" />
<img src="/images/chat_robot.png" alt="智能问答" width="260px" />
<img src="/images/chat_rate.png" alt="满意度" width="260px" />
<img src="/images/chat_faq.png" alt="常见问题" width="260px" />
<img src="/images/chat_tags.png" alt="个人标签" width="260px" />
<img src="/images/chat_leavemsg.png" alt="留言" width="260px" />

## 更新日志

#### 2018-03-30: version 4.9.7
* 优化

#### 2018-03-27: version 4.9.6
* 优化已知问题

#### 2017-11-21: version 4.9.5
* 优化已知问题

#### 2017-10-01: version 4.9.2
* 启用新域名weikefu.net

#### 2017-07-13: version 4.9.0
* 优化更新设备信息接口

#### 2017-06-20: version 4.8.3
* 增加aar集成方式

#### 2017-06-19: version 4.8.2
* 优化兼容性

#### 2017-06-14: version 4.8.0
* 优化兼容性

#### 2017-04-12: version 4.5.0
* 优化发送状态更新

#### 2017-02-17: version 4.4.2
* 优化兼容Android N

#### 2017-02-08: version 4.4.0
* 优化发送图片

#### 2016-12-09: version 4.3.5
* 兼容Android N, 24 相机访问权限处理

#### 2016-11-21: version 4.3.1
* 优化一个小bug

#### 2016-11-16: version 4.3.0
* 优化内存处理
* 解决OOM的bug

#### 2016-09-28: version 4.2.0
* 增加Ip Server登录模式， 解决部分移动运营商域名解析问题
* 优化用户体验

#### 2016-09-15: version 4.1.5
* 优化用户体验
* 支持接收播放PC客服端语音
* 优化支持兼容Android 6.0动态权限
* 优化兼容Unity

#### 2016-08-17: version 4.0.4
* 优化图片大小显示
* 重构:所有常量迁移至KFConstant类
* 完善语音播放模块


## 开发文档


http://admin.appkefu.com/AppKeFu/doc/android.html


## 接口一览

```java

//sdk版本
public static String version()

//是否登录
public static Boolean isConnected()

//获取用户名
public static String getUsername(Context context)

//接收到消息的时候 是否显示在通知栏
public static void setIfShowNotification(String workgroupName, Boolean flag, Context context)
	
//开启关闭调试模式
public static void enableDebugMode(Context context, Boolean flag)

//使用自定义用户名登录
public static void loginWithUserID(String userId, Context context)

//清空聊天记录
public static void clearMessageRecords(String workgroupName, Context context)

//检测工作组内是否有至少一个客服在线
public static void checkKeFuIsOnlineAsync(String workgroupname, Context context)

//发送消息
public static void sendMessage(Context context, String workgroupName, String textContent)

//获取未读消息数目接口
public static int getUnreadMessageCount(String workgroupName, Context context)

//开始会话，具体参数意义请下载demo
public static void startChat(Context context 
			,String workgroupName 
			,String title 
			,String productInfo
			,Boolean showMenu
			,int showMessageCount
			,String kefuAvatarURL
			,String userAvatarURL
			,Boolean defaultRobot
			,Boolean mustRateBoolean
			//,Boolean saveSession
			,KFCallBack callBack)
	
//开始电商会话页面
public static void startECChat(Context context 
			,String workgroupName 
			,String title 
			,String productInfo
			,Boolean showMenu
			,int showMessageCount
			,String kefuAvatarURL
			,String userAvatarURL
			,Boolean defaultRobot
			
			,Boolean showGoodsLayout
			,String goodsImageViewURL
			,String goodsTitleDetail
			,String goodsPrice
			,String goodsWeburl
			,String goodsCallbackID
			
			,Boolean mustRateBoolean
			//,Boolean saveSession
			,KFCallBack callBack)
	
//开始留言
public static void startLeaveMessage(Context context, String workgroupName)
	
//获取个人标签
public static KFUserTagsEntity getTags(Context context)

//设置昵称
public static void setTagNickname(String nickname, Context context)

//设置性别
public static void setTagSex(String sex, Context context)

//设置语言
public static void setTagLanguage(String language, Context context)

//设置城市
public static void setTagCity(String city, Context context)
    
//设置省份
public static void setTagProvince(String province, Context context)

//设置国家
public static void setTagCountry(String country, Context context)

//设置其他
public static void setTagOther(String other, Context context)
    
//设置手机
public static void setTagMobile(String mobile, Context context)
    
//设置QQ
public static void setTagQQ(String qq, Context context)

//设置email
public static void setTagEmail(String email, Context context)
   
//登出
public static void Logout(Context context)


```















