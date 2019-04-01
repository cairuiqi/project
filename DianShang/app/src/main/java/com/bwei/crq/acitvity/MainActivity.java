package com.bwei.crq.acitvity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.bwei.crq.R;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.fragment.CircleFragment;
import com.bwei.crq.fragment.HomeFragment;
import com.bwei.crq.fragment.MyFragment;
import com.bwei.crq.fragment.OrderFragment;
import com.bwei.crq.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        /*//集合
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CircleFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MyFragment());
        //适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        //默认
        radiogroup.check(radiogroup.getChildAt(0).getId());
        //切换页面
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radiogroup.check(radiogroup.getChildAt(i).getId());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //切换按钮
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        pager.setCurrentItem(3);
                        break;
                    case R.id.rb5:
                        pager.setCurrentItem(4);
                        break;
                }
            }
        });

*/


        //获得布局管理者
        final FragmentManager manager = getSupportFragmentManager();
        //开启事务
        final FragmentTransaction transaction = manager.beginTransaction();
        //添加事务
        final HomeFragment homeFragment = new HomeFragment();
        final CircleFragment circleFragment = new CircleFragment();
        final ShoppingFragment shoppingFragment = new ShoppingFragment();
        final OrderFragment orderFragment = new OrderFragment();
        final MyFragment myFragment = new MyFragment();
        transaction.add(R.id.fragment, homeFragment);
        transaction.add(R.id.fragment, circleFragment);
        transaction.add(R.id.fragment, shoppingFragment);
        transaction.add(R.id.fragment, orderFragment);
        transaction.add(R.id.fragment, myFragment);
        //显示隐藏
        transaction.show(homeFragment).hide(circleFragment).hide(shoppingFragment).hide(orderFragment).hide(myFragment);
        //提交
        transaction.commit();
        //默认第一个
        rg.check(rg.getChildAt(0).getId());


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction qq = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.rb1:
                        qq.show(homeFragment).hide(circleFragment).hide(shoppingFragment).hide(orderFragment).hide(myFragment);

                        break;
                    case R.id.rb2:
                        qq.show(circleFragment).hide(homeFragment).hide(shoppingFragment).hide(orderFragment).hide(myFragment);

                        break;
                    case R.id.rb3:
                        qq.show(shoppingFragment).hide(circleFragment).hide(homeFragment).hide(orderFragment).hide(myFragment);

                        break;
                    case R.id.rb4:
                        qq.show(orderFragment).hide(circleFragment).hide(shoppingFragment).hide(homeFragment).hide(myFragment);

                        break;
                    case R.id.rb5:
                        qq.show(myFragment).hide(circleFragment).hide(shoppingFragment).hide(orderFragment).hide(homeFragment);

                        break;
                }
                qq.commit();
            }
        });

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
