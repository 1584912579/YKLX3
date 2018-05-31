package com.example.asus.yklx3.ui.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.asus.yklx3.R;
import com.example.asus.yklx3.bean.GetCartsBean;
import com.example.asus.yklx3.bean.SellerBean;
import com.example.asus.yklx3.component.DaggerHttpComponent;
import com.example.asus.yklx3.module.HttpModule;
import com.example.asus.yklx3.ui.base.BaseActivity;
import com.example.asus.yklx3.ui.shopping.adapter.ElvShopcartAdapter;
import com.example.asus.yklx3.ui.shopping.contract.ShopCartContract;
import com.example.asus.yklx3.ui.shopping.presenter.ShopCartPresenter;
import com.example.asus.yklx3.ui.userimfo.UserInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends BaseActivity<ShopCartPresenter> implements ShopCartContract.View {

    @BindView(R.id.expv)
    ExpandableListView mExpv;
    @BindView(R.id.qx)
    CheckBox mQx;
    @BindView(R.id.heji)
    TextView mHeji;
    @BindView(R.id.js)
    TextView mJs;
    @BindView(R.id.tiao)
    TextView tiao;
    private ElvShopcartAdapter adapter;
    private String uid="14530";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        //请求数据
        mPresenter.getCartssuess(uid);
        mQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    adapter.changeAllState(mQx.isChecked());
                }
            }
        });
        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_shop;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getCartssuess(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mQx.setChecked(isSellerAddSelected(groupList));
        adapter = new ElvShopcartAdapter(ShopActivity.this,groupList,childList,mPresenter);
        mExpv.setAdapter(adapter);
        String[] strings = adapter.computeMoneyAndNum();
        mHeji.setText("总计：" + strings[0] + "元");
        mJs.setText("去结算("+strings[1]+"个)");
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mExpv.expandGroup(i);
        }

    }
    //判断所有商家是否全部选中
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void getupdateCartssuess() {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void getDeleteCartsSuccess() {
        if (adapter!=null){
            adapter.delSuccess();
        }
    }
}
