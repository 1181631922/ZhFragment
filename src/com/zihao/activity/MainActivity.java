package com.zihao.activity;

import java.util.ArrayList;
import java.util.List;

import com.zihao.R;
import com.zihao.fragment.AllFragment;
import com.zihao.fragment.MyCollectFragment;
import com.zihao.fragment.SiteStatisticsFragment;
import com.zihao.util.VocieAnswersUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 主界面
 * 
 * @author zihao
 * 
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	private FragmentManager manager;// Fragment碎片管理器
	private FragmentTransaction transaction;
	private List<Fragment> fragmentArry;
	private TextView tabALL, tabMyQuestion, tabDrPush;
	private int tabPressedColor = 0xffff6699;// tab选中色
	private int tabNormalColor = 0xff2b2b2b;// tab未选中色
	private int oldTabIndex = VocieAnswersUtil.TabIndex.TAB_ALL;// 上次选中的tab索引
	private int newTabIndex = VocieAnswersUtil.TabIndex.TAB_ALL;// 新选中的tab索引

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		// 初始化控件
		tabALL = (TextView) findViewById(R.id.tab_all);
		tabMyQuestion = (TextView) findViewById(R.id.tab_collect);
		tabDrPush = (TextView) findViewById(R.id.tab_site);

		// 绑定点击监听
		tabALL.setOnClickListener(this);
		tabMyQuestion.setOnClickListener(this);
		tabDrPush.setOnClickListener(this);

		// 初始化Fragment碎片管理
		fragmentArry = new ArrayList<Fragment>();
		fragmentArry.add(new AllFragment());
		fragmentArry.add(new MyCollectFragment());
		fragmentArry.add(new SiteStatisticsFragment());

		manager = getSupportFragmentManager();// 获取Fragment管理器
		transaction = manager.beginTransaction();// 从管理器中得到一个Fragment事务
		transaction.add(R.id.fragment,
				fragmentArry.get(VocieAnswersUtil.TabIndex.TAB_ALL));// 将得到的fragment替换当前的viewGroup内
		transaction.commit();
	}

	/**
	 * 切换Fragment视图
	 * 
	 * @param contentId
	 *            容器ID
	 * @param fragment
	 *            切换对象
	 * @param index
	 *            tab索引位置
	 */
	private void replace(int contentId, Fragment fragment, int index) {
		if (!fragment.isAdded()) {
			changeTabState(index);
			System.out.println("newIndex:" + newTabIndex + ",oldIndex:"
					+ oldTabIndex);

			FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();

			// 根据索引加入切换动画
			if (newTabIndex > oldTabIndex) {
				fragmentTransaction.setCustomAnimations(
						R.anim.fragment_slide_right_enter,
						R.anim.fragment_slide_left_exit);
			} else {
				fragmentTransaction.setCustomAnimations(
						R.anim.fragment_slide_left_enter,
						R.anim.fragment_slide_right_exit);
			}

			fragmentTransaction.replace(contentId, fragment).commit();

			oldTabIndex = newTabIndex;
		}
	}

	/**
	 * 改变Tab状态
	 */
	private void changeTabState(int index) {
		newTabIndex = index;
		switch (index) {
		case VocieAnswersUtil.TabIndex.TAB_ALL:
			tabALL.setTextColor(tabPressedColor);
			tabMyQuestion.setTextColor(tabNormalColor);
			tabDrPush.setTextColor(tabNormalColor);
			break;
		case VocieAnswersUtil.TabIndex.TAB_COLLECT:
			tabALL.setTextColor(tabNormalColor);
			tabMyQuestion.setTextColor(tabPressedColor);
			tabDrPush.setTextColor(tabNormalColor);
			break;
		case VocieAnswersUtil.TabIndex.TAB_STATISTICS:
			tabALL.setTextColor(tabNormalColor);
			tabMyQuestion.setTextColor(tabNormalColor);
			tabDrPush.setTextColor(tabPressedColor);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tab_all:
			replace(R.id.fragment,
					fragmentArry.get(VocieAnswersUtil.TabIndex.TAB_ALL),
					VocieAnswersUtil.TabIndex.TAB_ALL);
			break;
		case R.id.tab_collect:
			replace(R.id.fragment,
					fragmentArry.get(VocieAnswersUtil.TabIndex.TAB_COLLECT),
					VocieAnswersUtil.TabIndex.TAB_COLLECT);
			break;
		case R.id.tab_site:
			replace(R.id.fragment,
					fragmentArry.get(VocieAnswersUtil.TabIndex.TAB_STATISTICS),
					VocieAnswersUtil.TabIndex.TAB_STATISTICS);
			break;
		case R.id.tab_search:

			break;
		}
	}

}