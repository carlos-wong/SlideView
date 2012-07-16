package com.slide.menu;

import java.util.ArrayList;

import com.slide.util.SlideMenuUtil;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 顶部滑动菜单布局设置
 * @Description: 顶部滑动菜单布局设置

 * @FileName: SlideMenuLayout.java 

 * @Package com.slide.menu 

 * @Author Hanyonglu

 * @Date 2012-4-20 上午11:17:31 

 * @Version V1.0
 */
public class SlideMenuLayout {
	// 包含菜单的ArrayList
	private ArrayList<TextView> menuList = null;
	
	private Activity activity;
	private TextView textView = null;
	private SlideMenuUtil menuUtil = null;
	
	public SlideMenuLayout(Activity activity){
		this.activity = activity;
		menuList = new ArrayList<TextView>();
		menuUtil = new SlideMenuUtil();
	}
	
	/**
	 * 顶部滑动菜单布局
	 * @param menuTextViews
	 * @param layoutWidth
	 */
	public View getSlideMenuLinerLayout(String[] menuTextViews,int layoutWidth){
		// 包含TextView的LinearLayout
		LinearLayout menuLinerLayout = new LinearLayout(activity);
		menuLinerLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		// 参数设置
		LinearLayout.LayoutParams menuLinerLayoutParames = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1);
		menuLinerLayoutParames.gravity = Gravity.CENTER_HORIZONTAL;
		
		// 添加TextView控件
		for(int i = 0;i < menuTextViews.length; i++){
			TextView tvMenu = new TextView(activity);
			// 设置标识值
			tvMenu.setTag(menuTextViews[i]);
			tvMenu.setLayoutParams(new LayoutParams(layoutWidth / 4,30)); 
			tvMenu.setPadding(30, 14, 30, 10);
			tvMenu.setText(menuTextViews[i]);
			tvMenu.setTextColor(Color.WHITE);
			tvMenu.setGravity(Gravity.CENTER_HORIZONTAL);
			tvMenu.setOnClickListener(SlideMenuOnClickListener);
			
			// 菜单项计数
			menuUtil.count ++;

			// 设置第一个菜单项背景
			if(menuUtil.count == 1){
				tvMenu.setBackgroundResource(R.drawable.menu_bg);
			}
			
			menuLinerLayout.addView(tvMenu,menuLinerLayoutParames);
			menuList.add(tvMenu);
		}

		return menuLinerLayout;
	}
	
	// 单个菜单事件
	OnClickListener SlideMenuOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String menuTag = v.getTag().toString();
			
			if(v.isClickable()){
				textView = (TextView)v;
				Log.i("SlideMenu", 
						"width：" + textView.getWidth() + 
						"height：" + textView.getHeight());

				textView.setBackgroundResource(R.drawable.menu_bg);
				
				for(int i = 0;i < menuList.size();i++){
					if(!menuTag.equals(menuList.get(i).getText())){
						menuList.get(i).setBackgroundDrawable(null);
					}
				}
				
		        // 点击菜单时改变内容
				slideMenuOnChange(menuTag);
			}
		}
	};
	
	// 点击时改内容
	private void slideMenuOnChange(String menuTag){
		LayoutInflater inflater = activity.getLayoutInflater();
		ViewGroup llc = (ViewGroup)activity.findViewById(R.id.linearLayoutContent);
		llc.removeAllViews();

		if(menuTag.equals(SlideMenuUtil.ITEM_MOBILE)){
			llc.addView(inflater.inflate(R.layout.item_mobile, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_WEB)){
			llc.addView(inflater.inflate(R.layout.item_web, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_CLOUD)){
			llc.addView(inflater.inflate(R.layout.item_cloud, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_DATABASE)){
			llc.addView(inflater.inflate(R.layout.item_database, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_EMBED)){
			llc.addView(inflater.inflate(R.layout.item_embed, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_SERVER)){
			llc.addView(inflater.inflate(R.layout.item_server, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_DOTNET)){
			llc.addView(inflater.inflate(R.layout.item_dotnet, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_JAVA)){
			llc.addView(inflater.inflate(R.layout.item_java, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_SAFE)){
			llc.addView(inflater.inflate(R.layout.item_safe, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_DOMAIN)){
			llc.addView(inflater.inflate(R.layout.item_domain, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_RESEASRCH)){
			llc.addView(inflater.inflate(R.layout.item_research, null));
		}else if(menuTag.equals(SlideMenuUtil.ITEM_MANAGE)){
			llc.addView(inflater.inflate(R.layout.item_manage, null));
		}
	}
}