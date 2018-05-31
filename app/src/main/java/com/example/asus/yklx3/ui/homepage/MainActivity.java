package com.example.asus.yklx3.ui.homepage;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.yklx3.R;
import com.example.asus.yklx3.bean.GetAdBean;
import com.example.asus.yklx3.component.DaggerHttpComponent;
import com.example.asus.yklx3.module.HttpModule;
import com.example.asus.yklx3.ui.MediaPlayer.MediaPlayerActivity;
import com.example.asus.yklx3.ui.base.BaseActivity;
import com.example.asus.yklx3.ui.homepage.adapter.RvRecommendAdapter;
import com.example.asus.yklx3.ui.homepage.adapter.RvSecKAdapter;
import com.example.asus.yklx3.ui.homepage.contract.GetAdContract;
import com.example.asus.yklx3.ui.homepage.presenter.GetAdPresenter;
import com.example.asus.yklx3.utils.MyApp;
import com.example.asus.yklx3.utils.MyApp2;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<GetAdPresenter> implements GetAdContract.View {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tvJD)
    TextView mTvJD;
    @BindView(R.id.llMore)
    LinearLayout mLlMore;
    @BindView(R.id.marqueeView)
    MarqueeView mMarqueeView;
    @BindView(R.id.rvSecKill)
    RecyclerView mRvSecKill;
    @BindView(R.id.rvRecommend)
    RecyclerView mRvRecommend;
    @BindView(R.id.tiao)
    TextView tiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter.GetAdPresenter();
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        mMarqueeView.startWithList(info);
        //设置图片加载器
        mBanner.setImageLoader(new MyApp2());
        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBanner.stopAutoPlay();
    }

    @Override
    public void getGetAdSueess(GetAdBean getAdBean) {
        //banner
        List<GetAdBean.DataBean> data = getAdBean.getData();
        ArrayList<String> image = new ArrayList<>();
        for (int i = 0; i <data.size() ; i++) {
            String icon = data.get(i).getIcon();
            image.add(icon);
        }
        mBanner.setImages(image);
        mBanner.start();
        //秒杀
        List<GetAdBean.MiaoshaBean.ListBeanX> miaosha = getAdBean.getMiaosha().getList();
        mRvSecKill.setLayoutManager(new GridLayoutManager(this,1, RecyclerView.HORIZONTAL, false));
        RvSecKAdapter rvSecKillAdapter = new RvSecKAdapter(this, miaosha);
        mRvSecKill.setAdapter(rvSecKillAdapter);

        //推荐
        List<GetAdBean.TuijianBean.ListBean> tuijian = getAdBean.getTuijian().getList();
        mRvRecommend.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL, false));
        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(this, tuijian);
        mRvRecommend.setAdapter(rvRecommendAdapter);

    }

}
