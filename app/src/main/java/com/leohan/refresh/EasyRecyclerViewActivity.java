package com.leohan.refresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Leo
 */
public class EasyRecyclerViewActivity extends AppCompatActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recyclerView)
    EasyRecyclerView recyclerView;


    boolean isLoading;
    private List<String> data = new ArrayList<>();
    private PersonAdapter adapter = new PersonAdapter(this);
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        ButterKnife.inject(this);
//        initData();
        initView();
    }

    public void initView() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.notice);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setRefreshingColor(R.color.blueStatus);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(2));
        adapter.addAll(data);
        recyclerView.setAdapter(adapter);

        adapter.setNoMore(R.layout.item_complete);

        recyclerView.post(new Runnable()
        {
            @Override
            public void run()
            {
                recyclerView.setRefreshing(true);
                initData();
            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        data.clear();
                        getData();
                    }
                }, 2000);
            }
        });


        adapter.setMore(R.layout.item_foot, new RecyclerArrayAdapter.OnLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (data.size()<30)
                        {
                            if (!isLoading)
                            {
                                adapter.pauseMore();
                                isLoading=true;
                                return;
                            }
                            adapter.clear();
                            getData();
                        }
                        else
                        {
                            adapter.stopMore();
                        }

                    }
                }, 2000);
            }
        });

        adapter.setError(R.layout.item_error).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                adapter.resumeMore();
            }
        });


        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {

            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(int position)
            {
                return false;
            }
        });
    }


    public void initData() {
        getData();

    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 6; i++) {
            String map = new String();
            data.add(map);
        }
        Log.i("Tag",data.size()+"");
        adapter.notifyDataSetChanged();
        adapter.addAll(data);
        recyclerView.setRefreshing(false);
    }




    public class PersonAdapter extends RecyclerArrayAdapter<String>
    {

        public PersonAdapter(Context context) {
            super(context);


        }

        @Override
        public int getViewType(int position)
        {
            return position==2?0:1;
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType==0)
            {
                return new PersonViewHolder1(parent);
            }
            return new PersonViewHolder(parent);
        }


        public class PersonViewHolder extends BaseViewHolder<String> {
            private TextView mTv_name;


            public PersonViewHolder(ViewGroup parent) {
                super(parent,R.layout.item_base);
                mTv_name = $(R.id.tv_title);
            }

            @Override
            public void setData(final String person){

            }
        }

        public class PersonViewHolder1 extends BaseViewHolder<String> {
            private TextView mTv_name;


            public PersonViewHolder1(ViewGroup parent) {
                super(parent,R.layout.item_complete);
            }

            @Override
            public void setData(final String person){

            }
        }

    }



}
