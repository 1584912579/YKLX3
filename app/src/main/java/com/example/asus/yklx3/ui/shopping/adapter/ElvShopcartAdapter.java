package com.example.asus.yklx3.ui.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.yklx3.R;
import com.example.asus.yklx3.bean.GetCartsBean;
import com.example.asus.yklx3.bean.SellerBean;
import com.example.asus.yklx3.ui.shopping.presenter.ShopCartPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/29.
 */

public class ElvShopcartAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<SellerBean> groupList;
    private List<List<GetCartsBean.DataBean.ListBean>> childList;
    private ShopCartPresenter mPresenter;
    private LayoutInflater inflater;
    private final String uid="14530";
    private int productIndex;
    private int groupPosition;
    private boolean checked;
    private static final int GETCARTS = 0;//查询购物车
    private static final int UPDATE_PRODUCT = 1; //更新商品
    private static final int UPDATE_SELLER = 2; //更新卖家
    private static int state = GETCARTS;
    private boolean allSelected;
    private boolean ccc=true;
    private int flag = 0;
    public ElvShopcartAdapter(Context context, List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList, ShopCartPresenter mPresenter) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        this.mPresenter = mPresenter;
        inflater=LayoutInflater.from(context);
    }

    private GroupEdtorListener mListener;

    public GroupEdtorListener getmListener() {
        return mListener;
    }

    public void setmListener(GroupEdtorListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = inflater.inflate(R.layout.group, null);
            groupViewHolder.cbSeller = convertView.findViewById(R.id.cbSeller);
            groupViewHolder.tvSeller = convertView.findViewById(R.id.tvSeller);
            groupViewHolder.bj = convertView.findViewById(R.id.bj);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        //设置值
        final SellerBean sellerBean = groupList.get(groupPosition);
        groupViewHolder.tvSeller.setText(sellerBean.getSellerName());
        groupViewHolder.cbSeller.setChecked(sellerBean.isSelected());
//        sellerBean.setBg("编辑");
        groupViewHolder.bj.setText(sellerBean.getBg());
        groupViewHolder.cbSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置当前的更新状态
                state = UPDATE_PRODUCT;
                productIndex=0;
                //全局记录一下当前更新的商家
                ElvShopcartAdapter.this.groupPosition = groupPosition;
                //该商家是否选中
                checked = groupViewHolder.cbSeller.isChecked();
                //更新商家下的商品状态
                updateProductInSeller();
            }
        });
        final  List<GetCartsBean.DataBean.ListBean> listBeans = childList.get(groupPosition);

        groupViewHolder.bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sellerBean.getBg()=="编辑"){
                    sellerBean.setBg("完成");
                   for (int i = 0; i <listBeans.size() ; i++) {
                        listBeans.get(i).setIseee(true);
                    }
                    notifyDataSetChanged();
                }else if (sellerBean.getBg()=="完成") {
                    sellerBean.setBg("编辑");
                    for (int i = 0; i <listBeans.size() ; i++) {
                        listBeans.get(i).setIseee(false);
                    }
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }



    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.child, null);
            childViewHolder.cbProduct = convertView.findViewById(R.id.cbProduct);
            childViewHolder.iv = convertView.findViewById(R.id.iv);
            childViewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            childViewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
            childViewHolder.tvDel = convertView.findViewById(R.id.tvDel);
            childViewHolder.num = convertView.findViewById(R.id.num);
            childViewHolder.jia = convertView.findViewById(R.id.jia);
            childViewHolder.jian = convertView.findViewById(R.id.jian);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        if (listBean.isIseee()==true){
            childViewHolder.tvDel.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.tvDel.setVisibility(View.INVISIBLE);
        }

        //根据服务器返回的select值，给checkBox设置是否选中
        childViewHolder.cbProduct.setChecked(listBean.getSelected() == 1 ? true : false);
        childViewHolder.tvTitle.setText(listBean.getTitle());
        childViewHolder.tvPrice.setText(listBean.getPrice() + "");
        childViewHolder.num.setText(listBean.getNum()+"");
        childViewHolder.iv.setImageURI(listBean.getImages().split("\\|")[0]);
        //选中和不选中
        childViewHolder.cbProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=GETCARTS;
                ElvShopcartAdapter.this.groupPosition = groupPosition;
                String sellerid = groupList.get(groupPosition).getSellerid();
                String pid = listBean.getPid() + "";
                int num = listBean.getNum();
                boolean childChecked = childViewHolder.cbProduct.isChecked();
                //uid,sellerid,pid,num,selected
                mPresenter.getupdateCartssuess(uid,sellerid,pid,num+"",childChecked?"1":"0");
            }
        });
        //加
        childViewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=GETCARTS;
                ElvShopcartAdapter.this.groupPosition = groupPosition;
                String sellerid = groupList.get(groupPosition).getSellerid();
                String pid = listBean.getPid() + "";
                int num = listBean.getNum();
                num+= 1;
                boolean childChecked = childViewHolder.cbProduct.isChecked();
                //uid,sellerid,pid,num,selected
                mPresenter.getupdateCartssuess(uid,sellerid,pid,num+"",childChecked?"1":"0");

            }
        });
        //减
        childViewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=GETCARTS;
                ElvShopcartAdapter.this.groupPosition = groupPosition;
                String sellerid = groupList.get(groupPosition).getSellerid();
                String pid = listBean.getPid() + "";
                int num = listBean.getNum();
                if (num <= 1) {
                    Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                num -= 1;
                boolean childChecked = childViewHolder.cbProduct.isChecked();
                //uid,sellerid,pid,num,selected
                mPresenter.getupdateCartssuess(uid,sellerid,pid,num+"",childChecked?"1":"0");

            }
        });
        //删除
        childViewHolder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = GETCARTS;
                //获取pid
                int pid = listBean.getPid();
                mPresenter.getDeleteCartsSuccess(uid,pid+"");
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder {
        CheckBox cbSeller;
        TextView tvSeller;
        TextView bj;
    }

    class ChildViewHolder {
        CheckBox cbProduct;
        SimpleDraweeView iv;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDel;
        TextView jia;
        TextView num;
        TextView jian;
    }
    //删除成功回调接口
    public void delSuccess() {
        mPresenter.getCartssuess(uid);
    }
    public void updataSuccess() {
        switch (state){
            case GETCARTS:
                //更新成功以后调用查询购物车接口
                productIndex = 0;
                groupPosition = 0;
                mPresenter.getCartssuess(uid);
                break;
            case UPDATE_PRODUCT:
                productIndex++;
                if (productIndex<childList.get(groupPosition).size()){
                    updateProductInSeller();
                }else {
                    state=GETCARTS;
                    updataSuccess();
                }
                break;
            case UPDATE_SELLER:
                productIndex++;
                if (productIndex<childList.get(groupPosition).size()){
                    updateProductInSeller(allSelected);
                }else {
                    productIndex=0;
                    groupPosition++;
                    if (groupPosition<groupList.size()){
                        updateProductInSeller(allSelected);
                    }else {
                        state = GETCARTS;
                        updataSuccess();
                    }
                }
                break;
        }
    }
    private void updateProductInSeller() {
        //uid,sellerid,pid,num,selected
        //获取SellerId
        SellerBean sellerBean = groupList.get(groupPosition);
        String sellerid = sellerBean.getSellerid();
        //获取pid
        GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(productIndex);
        int num = listBean.getNum();
        int pid = listBean.getPid();
        mPresenter.getupdateCartssuess(uid, sellerid, pid + "", num + "", checked ? "1" : "0");
    }
    private void updateProductInSeller(boolean bool) {
        //uid,sellerid,pid,num,selected
        SellerBean sellerBean = groupList.get(groupPosition);
        String sellerid = sellerBean.getSellerid();
        //获取pid
        GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(productIndex);
        int num = listBean.getNum();
        int pid = listBean.getPid();

        mPresenter.getupdateCartssuess(uid, sellerid,pid+"",num+"", bool ? "1" : "0");
    }
    public void changeAllState(boolean bool){
        this.allSelected = bool;
        state = UPDATE_SELLER;
        //遍历商家下的商品，修改状态
        updateProductInSeller(bool);

    }
    //计算数量和价钱

    public String[] computeMoneyAndNum() {
        double sum=0;
        int num=0;
        for (int i = 0; i <groupList.size() ; i++) {
            for (int j = 0; j < childList.get(i).size(); j++) {
                //判断商品是否选中
                GetCartsBean.DataBean.ListBean listBean = childList.get(i).get(j);
                if (listBean.getSelected()==1){
                    sum += listBean.getPrice() * listBean.getNum();
                    num += listBean.getNum();
                }
            }
        }
        return new String[]{sum + "", num + ""};
    }
    /**
     * 监听编辑状态
     */
    public interface GroupEdtorListener{
        public void groupEdit(int groupPosition);
    }

}
