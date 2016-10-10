package com.appkefu.androidstudiodemo.activity;

import java.util.ArrayList;

import com.appkefu.androidstudiodemo.R;
import com.appkefu.androidstudiodemo.adapter.ApiAdapter;
import com.appkefu.androidstudiodemo.entity.ApiEntity;
import com.appkefu.lib.interfaces.KFAPIs;
import com.appkefu.lib.interfaces.KFCallBack;
import com.appkefu.lib.service.KFMainService;
import com.appkefu.lib.service.KFXmppManager;
import com.appkefu.lib.utils.KFConstants;
import com.appkefu.lib.utils.KFLog;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 微客服(AppKeFu.com)
 * 微客服，集成到您App里的在线客服 国内首款App里的在线客服，支持文字、表情、图片、语音聊天。 立志为移动开发者提供最好的在线客服
 * 技术交流QQ 2群:474794719
 * 客服开发文档： http://admin.appkefu.com/AppKeFu/doc/android.html
 */
     
public class MainActivity extends Activity {
 
	/**
	 * 提示：如果已经运行过旧版的Demo，请先在手机上删除原先的App再重新运行此工程
	 * 更多使用帮助参见：http://admin.appkefu.com/AppKeFu/doc/android.html
	 * 注意：开发者将SDK嵌入到自己的应用中之后，至少要修改两处： 1.appkey 2.客服工作组名称(参见函数：startChat)
	 */
  
	private TextView mTitle;

	private ListView mApiListView;
	private ArrayList<ApiEntity> mApiArray;
	private ApiAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		// 登录方式
		KFAPIs.visitorLogin(this);

		// 第二种登录方式，传入user_id, 注意user_id中只能包含数字、字母和下划线_,不能含有汉字
		//KFAPIs.loginWithUserID("5610b39ce00dc2ca68c68086edcd801e", this);

	}

	@Override
	protected void onStart() {
		super.onStart();

		IntentFilter intentFilter = new IntentFilter();
		// 监听网络连接变化情况
		intentFilter.addAction(KFConstants.ACTION_XMPP_CONNECTION_CHANGED);
		// 监听消息
		intentFilter.addAction(KFConstants.ACTION_XMPP_MESSAGE_RECEIVED);
		// 工作组在线状态
		intentFilter.addAction(KFConstants.ACTION_XMPP_WORKGROUP_ONLINESTATUS);

		registerReceiver(mXmppreceiver, intentFilter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// 登录方式
		KFAPIs.visitorLogin(this);

		// 第二种登录方式，传入user_id, 注意user_id中只能包含数字、字母和下划线_,不能含有汉字
		// /KFAPIs.loginWithUserID("linkfull_test5", this);
		//
		ApiEntity entity = mApiArray.get(8);
    	entity.setApiName(getString(R.string.unread_message_count) + ":"+KFAPIs.getUnreadMessageCount("wgdemo", this));
    	mAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onStop() {
		super.onStop();

		unregisterReceiver(mXmppreceiver);
	}

	private void initView() {

		// 界面标题
		mTitle = (TextView) findViewById(R.id.demo_title);

		mApiListView = (ListView) findViewById(R.id.api_list_view);
		mApiArray = new ArrayList<ApiEntity>();

		mAdapter = new ApiAdapter(this, mApiArray);
		mApiListView.setAdapter(mAdapter);

		ApiEntity entity = new ApiEntity(1, getString(R.string.chat_with_kefu_before));
		mApiArray.add(entity);
		entity = new ApiEntity(2, getString(R.string.chat_with_kefu_after));
		mApiArray.add(entity);
		entity = new ApiEntity(3, getString(R.string.chat_with_e_commence));
		mApiArray.add(entity);
		entity = new ApiEntity(4, getString(R.string.chat_with_robot));
		mApiArray.add(entity);
		entity = new ApiEntity(5, getString(R.string.set_user_tags));
		mApiArray.add(entity);
		entity = new ApiEntity(6, getString(R.string.clear_message_records));
		mApiArray.add(entity);
		entity = new ApiEntity(7, getString(R.string.show_faq));
		mApiArray.add(entity);
		entity = new ApiEntity(8, getString(R.string.leave_message));
		mApiArray.add(entity);
		entity = new ApiEntity(9, getString(R.string.unread_message_count));
		mApiArray.add(entity);

		mAdapter.notifyDataSetChanged();
		mApiListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long id) {
				// TODO Auto-generated method stub

				ApiEntity entity = mApiArray.get(index);

				switch (entity.getId()) {
				case 1:
					startChat();
					break;
				case 2:
					startChat2();
					break;
				case 3:
					startECChat();
					break;
				case 4:
					startChatRobot();
					break;
				case 5:
					showTagList();
					break;
				case 6:
					clearMessages();
					break;
				case 7:
					showFAQ();
					break;
				case 8:
					leaveMessage();
					break;
				default:
					break;
				}
				;
			}
		});
	}

	// 1.咨询人工客服
	private void startChat() {
		//
		KFAPIs.startChat(this, 
				"wgdemo", // 1. 客服工作组ID(请务必保证大小写一致)，请在管理后台分配
				"客服小秘书", // 2. 会话界面标题，可自定义
				null, // 3. 附加信息，在成功对接客服之后，会自动将此信息发送给客服;
						// 如果不想发送此信息，可以将此信息设置为""或者null
				true, // 4. 是否显示自定义菜单,如果设置为显示,请务必首先在管理后台设置自定义菜单,
						// 请务必至少分配三个且只分配三个自定义菜单,多于三个的暂时将不予显示
						// 显示:true, 不显示:false
				5, // 5. 默认显示消息数量
				//修改SDK自带的头像有两种方式，1.直接替换appkefu_message_toitem和appkefu_message_fromitem.xml里面的头像，2.传递网络图片自定义
				null, //"http://47.90.33.185/PHP/XMPP/gyfd/chat/web/img/kefu-avatar.png",//6. 修改默认客服头像，如果想显示客服真实头像，设置此参数为null
				"http://47.90.33.185/PHP/XMPP/gyfd/chat/web/img/user-avatar.png",		//7. 修改默认用户头像, 如果不想修改默认头像，设置此参数为null
				false, // 8. 默认机器人应答
				true,  //9. 是否强制用户在关闭会话的时候 进行“满意度”评价， true:是， false:否
				null);

	}

	// 2.咨询人工客服
	private void startChat2() {

		KFAPIs.startChat(this, "wgdemo2", // 1. 客服工作组ID(请务必保证大小写一致)，请在管理后台分配
				"客服小秘书", // 2. 会话界面标题，可自定义
				null, // 3. 设置为 null或者"" 将不发送此信息给客服
				false, // 4. 是否显示自定义菜单,如果设置为显示,请务必首先在管理后台设置自定义菜单,
						// 请务必至少分配三个且只分配三个自定义菜单,多于三个的暂时将不予显示
						// 显示:true, 不显示:false
				0, // 5. 默认显示消息数量
				null, // 6. 使用默认客服头像
				null, // 7. 使用默认用户头像
				false, // 8. 默认机器人应答
				false,  //9. 是否强制用户在关闭会话的时候 进行“满意度”评价， true:是， false:否
				new KFCallBack() { // 10. 会话页面右上角回调函数, 如果想要保持默认，请设置为null
			
					@Override
					public Boolean useTopRightBtnDefaultAction() {
						return true;
					}
					
					@Override
					public void OnChatActivityTopRightButtonClicked() {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "右上角回调接口调用");
						Toast.makeText(MainActivity.this, "右上角回调接口调用",
								Toast.LENGTH_SHORT).show();
						// 测试右上角回调接口调用
						showTagList();
					}

					@Override
					public void OnECGoodsImageViewClicked(String imageViewURL) {
						// TODO Auto-generated method stub

						Log.d("KFMainActivity", "OnECGoodsImageViewClicked");

					}

					@Override
					public void OnECGoodsTitleDetailClicked(
							String titleDetailString) {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "OnECGoodsIntroductionClicked");

					}

					@Override
					public void OnECGoodsPriceClicked(String priceString) {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "OnECGoodsPriceClicked");

					}
					
					@Override
					public void OnEcGoodsInfoClicked(String callbackId) {
						// TODO Auto-generated method stub
						
					}
					
					/**
					 * 用户点击会话页面下方“常见问题”按钮时，是否使用自定义action，如果返回true,
					 * 则默认action将不起作用，会调用下方OnFaqButtonClicked函数
					 */
					public Boolean userSelfFaqAction(){
						return false;
					}
					
					/**
					 * 用户点击“常见问题”按钮时，自定义action回调函数接口
					 */
					@Override
					public void OnFaqButtonClicked() {
						
						Log.d("KFMainActivity", "OnFaqButtonClicked");
					}
					
				} // 10. 会话页面右上角回调函数
		);
	}
	
	// 3.电商专用咨询页面
	private void startECChat()
	{
		KFAPIs.startECChat(this,
				"wgdemo", 				//1. 客服工作组名称(请务必保证大小写一致)，请在管理后台分配
				"客服小秘书",				//2. 会话界面标题，可自定义
				"商品简介商品简介商品简介商品简介商品简介 100元  <img src=\"http://appkefu.com/AppKeFu/images/dingyue.jpg\">",			    
										//3. 附加信息，在成功对接客服之后，会自动将此信息发送给客服;
										//   如果不想发送此信息，可以将此信息设置为""或者null
				true,					//4. 是否显示自定义菜单,如果设置为显示,请务必首先在管理后台设置自定义菜单,
										//	请务必至少分配三个且只分配三个自定义菜单,多于三个的暂时将不予显示 
										//	显示:true, 不显示:false
				5,						//5. 默认显示消息数量
				null,					//6. 修改默认客服头像，如果不想修改默认头像，设置此参数为null
				null,					//7. 修改默认用户头像, 如果不想修改默认头像，设置此参数为null
				false,					//8. 默认机器人应答
				
				true,					//9. 是否显示商品详情，显示：true；不显示：false
				"http://appkefu.com/AppKeFu/images/dingyue.jpg",//10.商品详情图片
				"商品简介商品简介商品简介商品简介商品简介",					//11.商品详情简介
				"100元",											//12.商品详情价格
				"http://appkefu.com",							//13.商品网址链接
				"goodsCallbackId", 								//14.点击商品详情布局回调参数
				
				false,											//15.退出对话的时候是否强制评价，强制：true，不评价：false
				new KFCallBack() {		//15. 会话页面右上角回调函数
			
					/**
					 * 16.是否使用对话界面右上角默认动作. 使用默认动作返回：true, 否则返回false
					 */
					@Override
					public Boolean useTopRightBtnDefaultAction() {
						
						return true;
					}
			
					/**
					 * 17.点击对话界面右上角按钮动作，依赖于 上面一个函数的返回结果
					 */
					@Override
					public void OnChatActivityTopRightButtonClicked() {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "右上角回调接口调用");
						Toast.makeText(MainActivity.this, "右上角回调接口调用", Toast.LENGTH_SHORT).show();
						
					}

					/**
					 * 18.点击商品详情图片回调函数
					 */
					@Override
					public void OnECGoodsImageViewClicked(String imageViewURL) {
						// TODO Auto-generated method stub
						
						Log.d("KFMainActivity", "OnECGoodsImageViewClicked"+imageViewURL);
						
					}

					/**
					 * 19.点击商品详情简介回调函数
					 */
					@Override
					public void OnECGoodsTitleDetailClicked(String titleDetailString) {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "OnECGoodsIntroductionClicked"+titleDetailString);
						
					}

					/**
					 * 20.点击商品详情价格回调函数
					 */
					@Override
					public void OnECGoodsPriceClicked(String priceString) {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "OnECGoodsPriceClicked"+priceString);
						
					}
					
					/**
					 * 21.点击商品详情布局回调函数
					 */
					@Override
					public void OnEcGoodsInfoClicked(String callbackId) {
						// TODO Auto-generated method stub
						Log.d("KFMainActivity", "OnEcGoodsInfoClicked"+callbackId);
						
					}
					
					/**
					 * 用户点击会话页面下方“常见问题”按钮时，是否使用自定义action，如果返回true,
					 * 则默认action将不起作用，会调用下方OnFaqButtonClicked函数
					 */
					public Boolean userSelfFaqAction(){
						return false;
					}
					
					/**
					 * 用户点击“常见问题”按钮时，自定义action回调函数接口
					 */
					@Override
					public void OnFaqButtonClicked() {
						Log.d("KFMainActivity", "OnFaqButtonClicked");
					}
					
				});
		
	}

	// 4.默认开启机器人应答
	private void startChatRobot() {

		KFAPIs.startChat(this, 
				"wgdemo", // 1. 客服工作组ID(请务必保证大小写一致)，请在管理后台分配
				"客服小秘书", // 2. 会话界面标题，可自定义
				null, // 3. 附加信息，在成功对接客服之后，会自动将此信息发送给客服;
						// 如果不想发送此信息，可以将此信息设置为""或者null
				false, // 4. 是否显示自定义菜单,如果设置为显示,请务必首先在管理后台设置自定义菜单,
						// 请务必至少分配三个且只分配三个自定义菜单,多于三个的暂时将不予显示
						// 显示:true, 不显示:false
				0, // 5. 默认显示消息数量
				//修改SDK自带的头像有两种方式，1.直接替换appkefu_message_toitem和appkefu_message_fromitem.xml里面的头像，2.传递网络图片自定义
				"http://47.90.33.185/PHP/XMPP/gyfd/chat/web/img/kefu-avatar.png",		//6. 修改默认客服头像，如果不想修改默认头像，设置此参数为null
				"http://47.90.33.185/PHP/XMPP/gyfd/chat/web/img/user-avatar.png",		//7. 修改默认用户头像, 如果不想修改默认头像，设置此参数为null
				true, // 8. 默认机器人应答
				false,  //9. 是否强制用户在关闭会话的时候 进行“满意度”评价， true:是， false:否
				null
		);

	}

	// 5.显示标签列表
	private void showTagList() {
		Intent intent = new Intent(this, TagListActivity.class);
		startActivity(intent);
	}

	// 6.清空聊天记录
	private void clearMessages() {
		KFAPIs.clearMessageRecords("wgdemo", this);
		Toast.makeText(this, "清空聊天记录", Toast.LENGTH_LONG).show();
	}
	
	// 7.显示常见问题FAQ
	private void showFAQ()
	{
		KFAPIs.showFAQ(this, "wgdemo");//工作组ID
	}
	
	//8 留言页面
	private void leaveMessage() {
			
		KFAPIs.startLeaveMessage(this, //Context, 上下文
					"wgdemo"); //工作组ID
	}

	//

	// 监听：连接状态、即时通讯消息、客服在线状态
	private BroadcastReceiver mXmppreceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// 监听：连接状态
			if (action.equals(KFConstants.ACTION_XMPP_CONNECTION_CHANGED))// 监听链接状态
			{
				updateStatus(intent.getIntExtra("new_state", 0));
			}
			// 监听：即时通讯消息
			else if (action.equals(KFConstants.ACTION_XMPP_MESSAGE_RECEIVED))// 监听消息
			{
				//消息内容
            	String body = intent.getStringExtra("body");
            	//消息来自于
            	String from = intent.getStringExtra("from");
            	KFLog.d("消息来自于:"+from+" 消息内容:"+body);
            	//
            	KFLog.d("未读消息数目："+KFAPIs.getUnreadMessageCount(from, MainActivity.this));
            	
            	ApiEntity entity = mApiArray.get(8);
            	entity.setApiName(getString(R.string.unread_message_count) + ":"+KFAPIs.getUnreadMessageCount(from, MainActivity.this));
            	mAdapter.notifyDataSetChanged();
			}
			// 客服工作组在线状态
			else if (action.equals(KFConstants.ACTION_XMPP_WORKGROUP_ONLINESTATUS)) {
				String fromWorkgroupName = intent.getStringExtra("from");
				String onlineStatus = intent.getStringExtra("onlinestatus");
				KFLog.d("客服工作组:" + fromWorkgroupName + " 在线状态:" + onlineStatus);// online：在线；offline:
																				// 离线
				// 截获到客服工作组wgdemo的在线状态
				if (fromWorkgroupName.equalsIgnoreCase("wgdemo")) {
					ApiEntity entity = mApiArray.get(0);

					if (onlineStatus.equals("online")) {
						entity.setApiName(getString(R.string.chat_with_kefu_before)+ "(在线)");
						KFLog.d("online:" + entity.getApiName());
					} else {
						entity.setApiName(getString(R.string.chat_with_kefu_before)+ "(离线)");
						KFLog.d("offline:" + entity.getApiName());
					}
					mApiArray.set(0, entity);
				}
				// 截获到客服工作组wgdemo2的在线状态
				else {
					ApiEntity entity = mApiArray.get(1);
					if (onlineStatus.equals("online")) {
						entity.setApiName(getString(R.string.chat_with_kefu_after)+ "(在线)");
						KFLog.d("online:" + entity.getApiName());
					} else {
						entity.setApiName(getString(R.string.chat_with_kefu_after)+ "(离线)");
						KFLog.d("offline:" + entity.getApiName());
					}

					mApiArray.set(1, entity);
				}

				mAdapter.notifyDataSetChanged();
			}

		}
	};
 
	// 根据监听到的连接变化情况更新界面显示
	private void updateStatus(int status) {

		switch (status) {
		case KFXmppManager.CONNECTED:
			mTitle.setText("微客服4(Demo)");

			// 查询客服工作组在线状态，返回结果在BroadcastReceiver中返回
			KFAPIs.checkKeFuIsOnlineAsync("wgdemo", this);
			KFAPIs.checkKeFuIsOnlineAsync("wgdemo2", this);

			break;
		case KFXmppManager.DISCONNECTED:
			mTitle.setText("微客服4(Demo)(未连接)");
			break;
		case KFXmppManager.CONNECTING:
			mTitle.setText("微客服4(Demo)(登录中...)");
			break;
		case KFXmppManager.DISCONNECTING:
			mTitle.setText("微客服4(Demo)(登出中...)");
			break;
		case KFXmppManager.WAITING_TO_CONNECT:
		case KFXmppManager.WAITING_FOR_NETWORK:
			mTitle.setText("微客服4(Demo)(等待中)");
			break;
		default:
			throw new IllegalStateException();
		}
	}

}
