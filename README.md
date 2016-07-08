# 微客服
安卓客服demo 4.x 版本 - 致力于为开发者提供极致用户体验的SDK

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







