package com.leohan.refresh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leohan.refresh.collapsing.CollapsingActivity;
import com.leohan.refresh.header.HeaderFooterActivity;
import com.leohan.refresh.loadmore.RefreshAndMoreActivity;
import com.leohan.refresh.multistyle.MultiStyleActivity;
import com.leohan.refresh.staggeredgrid.StaggeredGridActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Leo
 */
public class MainActivity extends AppCompatActivity {



    @InjectView(R.id.refresh_and_more)
    Button refreshAndMore;
    @InjectView(R.id.multi_style)
    Button multiStyle;
    @InjectView(R.id.header_footer)
    Button headerAndFooter;
    @InjectView(R.id.collapsing)
    Button collapsing;
    @InjectView(R.id.staggered_grid)
    Button staggeredGrid;
    @InjectView(R.id.easy)
    Button easy;


    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    public void initView()
    {


        refreshAndMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, RefreshAndMoreActivity.class));
            }
        });
        multiStyle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MultiStyleActivity.class));
            }
        });
        headerAndFooter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, HeaderFooterActivity.class));
            }
        });
        collapsing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, CollapsingActivity.class));
            }
        });
        staggeredGrid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, StaggeredGridActivity.class));
            }
        });
        easy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, EasyRecyclerViewActivity.class));
            }
        });

    }

}
