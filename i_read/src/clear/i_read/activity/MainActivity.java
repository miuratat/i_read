package clear.i_read.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import clear.i_read.R;
import clear.i_read.fragment.CalendarFragment;
import clear.i_read.fragment.ShotFragment;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity  {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
//    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        actionBar.setDisplayShowTitleEnabled(false);
        
        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("カレンダー"); // タイトル文字列
        tab1.setTabListener(new MyTabListener(this, "f1", CalendarFragment.class)); // リスナー
        actionBar.addTab(tab1);
        
        
        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("カメラ"); // タイトル文字列
        tab2.setTabListener(new MyTabListener(this, "f2", ShotFragment.class)); // リスナー
        actionBar.addTab(tab2);
       
    }


    class MyTabListener implements ActionBar.TabListener {
    	private Fragment fragment;
    	private final Activity activity;
    	private final String tag;
    	private final Class cls;

        // 新規タブを作成する際にフラグメントインスタンスを一緒に渡す
        public MyTabListener(Activity activity, String tag, Class cls) {
        	this.activity = activity;
        	this.tag = tag;
        	this.cls = cls;
        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // タブが選択された時の処理
            // フラグメントを追加する
        	fragment = Fragment.instantiate(activity, cls.getName());
            ft.replace(R.id.container, fragment, null);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            // タブが切り替えられた時の処理
            // フラグメントを削除する
            ft.detach(fragment);
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // 同じタブを再度タップされた時の処理
            // do nothing
        }
    }
    
}
