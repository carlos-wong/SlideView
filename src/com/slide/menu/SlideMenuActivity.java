package com.slide.menu;

import java.util.ArrayList;

import com.slide.util.SlideMenuUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * Android实现导航菜单左右滑动效果
 * @Description: Android实现导航菜单左右滑动效果

 * @FileName: SlideMenuActivity.java 

 * @Package com.slide.menu 

 * @Author Hanyonglu

 * @Date 2012-4-20 上午09:15:11 

 * @Version V1.0
 */
public class SlideMenuActivity extends Activity {
	private String[][] menus = {{SlideMenuUtil.ITEM_MOBILE,SlideMenuUtil.ITEM_WEB,
									SlideMenuUtil.ITEM_CLOUD,SlideMenuUtil.ITEM_DATABASE},
								{SlideMenuUtil.ITEM_EMBED,SlideMenuUtil.ITEM_SERVER,
									SlideMenuUtil.ITEM_DOTNET,SlideMenuUtil.ITEM_JAVA},
								{SlideMenuUtil.ITEM_SAFE,SlideMenuUtil.ITEM_DOMAIN,
									SlideMenuUtil.ITEM_RESEASRCH,SlideMenuUtil.ITEM_MANAGE}};
	
	// 当前ViewPager索引
	private int pagerIndex = 0; 
	private ArrayList<View> menuViews = null;
	
	private ViewGroup main = null;
	private ViewPager viewPager = null;
	// 左右导航图片按钮
	private ImageView imagePrevious = null;
	private ImageView imageNext = null;
	
	final String TAGS = "carlos";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置无标题窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取得窗口属性
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        Log.v(TAGS, "screenWidth is: "+screenWidth);
        
        LayoutInflater inflater = getLayoutInflater();  
        menuViews = new ArrayList<View>(); 
        
        SlideMenuLayout menu = new SlideMenuLayout(this);
        
        for(int i = 0;i < menus.length;i++){
        	menuViews.add(menu.getSlideMenuLinerLayout(menus[i],screenWidth));
        }
         
        main = (ViewGroup)inflater.inflate(R.layout.main, null);
        
        setContentView(main);
//        // 左右导航图片按钮
//        imagePrevious = (ImageView)findViewById(R.id.ivPreviousButton);
//        imageNext = (ImageView) findViewById(R.id.ivNextButton);
//        imagePrevious.setOnClickListener(new ImagePreviousOnclickListener());
//        imageNext.setOnClickListener(new ImageNextOnclickListener());
//        
//        if(menuViews.size() > 1){
//        	imageNext.setVisibility(View.VISIBLE);
//        }
        
        //菜单背景
//        imageMenuBack = (ImageView)findViewById(R.id.ivMenuBackground);
//        imageMenuBack.setVisibility(View.INVISIBLE);
        
        // 加载移动菜单下内容
        ViewGroup llc = (ViewGroup)findViewById(R.id.linearLayoutContent);
        llc.addView(inflater.inflate(R.layout.item_mobile, null));
        
        viewPager = (ViewPager)main.findViewById(R.id.slideMenu);  
        viewPager.setAdapter(new SlideMenuAdapter());  
        viewPager.setOnPageChangeListener(new SlideMenuChangeListener());  
    }
   
    // 滑动菜单数据适配器
    class SlideMenuAdapter extends PagerAdapter {  
  	  
        @Override  
        public int getCount() {  
//        	View v = menuViews.get(0).findViewWithTag(SlideMenuUtil.ITEM_MOBILE);
//        	v.setBackgroundResource(R.drawable.menu_bg);
            return menuViews.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(menuViews.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
        	((ViewPager) arg0).addView(menuViews.get(arg1));
            
            return menuViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
    } 
    
    // 滑动菜单更改事件监听器
    class SlideMenuChangeListener implements OnPageChangeListener {  
    	  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
        	
        }  
  
        @Override  
        public void onPageSelected(int arg0) {
        	int pageCount = menuViews.size() - 1;
        	pagerIndex = arg0;
        	
//        	// 显示右边导航图片
//        	if(arg0 >= 0 && arg0 < pageCount){
//        		imageNext.setVisibility(View.VISIBLE);
//        	}else{
//        		imageNext.setVisibility(View.INVISIBLE);
//        	}
//        	
//        	// 显示左边导航图片
//        	if(arg0 > 0 && arg0 <= pageCount){
//        		imagePrevious.setVisibility(View.VISIBLE);
//        	}else{
//        		imagePrevious.setVisibility(View.INVISIBLE);
//        	}
        }  
    }  
    
    // 右导航图片按钮事件
    class ImageNextOnclickListener implements OnClickListener{
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		pagerIndex ++;
    		viewPager.setCurrentItem(pagerIndex);
    	}
    }
    
    // 左导航图片按钮事件
    class ImagePreviousOnclickListener implements OnClickListener{
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		pagerIndex --;
    		viewPager.setCurrentItem(pagerIndex);
    	}
    }
}