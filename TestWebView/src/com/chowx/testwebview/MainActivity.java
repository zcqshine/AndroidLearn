package com.chowx.testwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {
	public static Activity actInstance;
	private LinearLayout m_webLayout;
	private WebView m_webView ;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actInstance = this;
		m_webLayout = new LinearLayout(this);
		
//		
//		initView();
//		requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
//	    getWindow().setFlags(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);  //设置全屏  
		Display mDisplay = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		if(android.os.Build.VERSION.SDK_INT>12){
			mDisplay.getSize(size);
		}
		int W = size.x;
		int H = size.y;
		setContentView(R.layout.activity_main);
		actInstance.addContentView(m_webLayout, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		displayWebView(0,0,W,H);
	}
	
	
	@Override
	public boolean onKeyDown(int keyCoder, KeyEvent enent){
		Log.d("surface: ","m_webView.goBack()");
		if((keyCoder == KeyEvent.KEYCODE_BACK) && m_webView.canGoBack()){
			m_webView.goBack();
			Log.d("surface: ","m_webView.goBack()");
			return true;
		}else{
			return false;
		}
	}

	public void displayWebView(final int x, final int y, final int width,
			final int height) {
		// Log.e("Vincent", "showWebView");

		this.runOnUiThread(new Runnable() {
			public void run() {
				// LinearLayout layout = new LinearLayout(actInstance);
				// actInstance.addContentView(layout, new
				// LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
				m_webView = new WebView(actInstance);
				m_webLayout.addView(m_webView);

				LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) m_webView.getLayoutParams();
				
				linearParams.leftMargin = x;
				linearParams.topMargin = y;
				linearParams.width = width;
				linearParams.height = height;
				m_webView.setVisibility(View.GONE);
				m_webView.setLayoutParams(linearParams);
				Log.d("resolution", "width="+width+",height="+height);

				m_webView.setBackgroundColor(0);
				m_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
				m_webView.getSettings().setAppCacheEnabled(false);
				m_webView.getSettings().setUseWideViewPort(true);
				m_webView.getSettings().setLoadWithOverviewMode(true);
				// m_webView.setBackgroundResource(R.drawable.yourImage);
				
				m_webView.setWebViewClient(new WebViewClient() {
					@Override
					public boolean shouldOverrideUrlLoading(WebView view, String url) {
						view.loadUrl(url);
						return true;
					}
				});
				m_webView.loadUrl("http://119.147.247.249/chat/friend.html");
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void initView(){
		m_webView = (WebView)findViewById(R.id.wv1);
		WebSettings setting = m_webView.getSettings();
//		setting.setJavaScriptEnabled(true);
		m_webView.clearCache(true);
		m_webView.loadUrl("http://119.147.247.249/chat/test3.html");
		setting.setUseWideViewPort(true);
		setting.setLoadWithOverviewMode(false);
		m_webView.setWebViewClient(new WebViewClient(){			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,String url){
				Log.d("surface: ",url);
				view.loadUrl(url);
				return true;
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
