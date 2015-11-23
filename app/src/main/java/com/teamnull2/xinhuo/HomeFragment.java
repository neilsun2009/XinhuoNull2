package com.teamnull2.xinhuo;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @paraParameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }
    //下面都是轮播banner
    private List<ImageView> hb_imageViews;
    private ViewPager hb_ViewPager;
    private List<View> hb_dots;
    private TextView hb_title;
    private int hb_currentItem= 0;
    private View hb_dot0;
    private View hb_dot1;
    private View hb_dot2;
    private View hb_dot3;
    private View hb_dot4;
    private View view;
    private List<HomeBanner> hb_list;
    private ScheduledExecutorService scheduledExecutorService;
    private Handler hb_handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            hb_ViewPager.setCurrentItem(hb_currentItem);
        };
    };
    //这个是listView
    private List<HomeListItem> hli_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView= (ListView)view.findViewById(R.id.lv_hf);
        listView.setFocusable(false);
        initHB();//初始化轮播
        startAd();
        initHLI();//初始化ListView
        //下面让滚动条置顶

        ScrollView sv= (ScrollView)view.findViewById(R.id.sv_hf);
        //sv.fullScroll(ScrollView.FOCUS_UP);
        sv.smoothScrollTo(0,0);
        // iv= (ImageView) view.findViewById(R.id.iv_hf_1);
        //iv.setFocusable(true);
        //iv.setFocusableInTouchMode(true);
        //iv.requestFocus();
        return view;
    }
    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        hb_imageViews= new ArrayList<ImageView>();
        for (int i = 0; i < hb_list.size(); i++) {
            ImageView imageView = new ImageView(this.getContext());
            // 异步加载图片
            imageView.setImageResource(hb_list.get(i).getImage());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            hb_imageViews.add(imageView);
            // hb_dots.get(i).setVisibility(View.VISIBLE);
            // dotList.add(hb_dots.get(i));
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }
    //初始化轮播数据
    private void initHB() {
        initHBData();
        hb_dots=new ArrayList<View>();
        hb_dot0=view.findViewById(R.id.hb_dot0);
        hb_dot1=view.findViewById(R.id.hb_dot1);
        hb_dot2=view.findViewById(R.id.hb_dot2);
        hb_dot3=view.findViewById(R.id.hb_dot3);
        hb_dot4=view.findViewById(R.id.hb_dot4);
        hb_dots.add(hb_dot0);
        hb_dots.add(hb_dot1);
        hb_dots.add(hb_dot2);
        hb_dots.add(hb_dot3);
        hb_dots.add(hb_dot4);
        hb_title= (TextView)view.findViewById(R.id.hb_title);
        hb_title.setText(hb_list.get(0).getTitle());
        hb_ViewPager = (ViewPager) view.findViewById(R.id.vp);
        hb_ViewPager.setAdapter(new hb_Adapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        hb_ViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }
    //轮播banner的定时播放
    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 5, 5,
                TimeUnit.SECONDS);
    }
    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (hb_ViewPager) {
                hb_currentItem = (hb_currentItem + 1) % 5;
                hb_handler.obtainMessage().sendToTarget();
            }
        }
    }
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            hb_currentItem = position;
            HomeBanner adDomain = hb_list.get(position);
            hb_title.setText(adDomain.getTitle()); // 设置标题
            hb_dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            hb_dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }
    private class hb_Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return hb_list.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = hb_imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final HomeBanner adDomain = hb_list.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    //初始化本地轮播数据
    private void initHBData() {
        hb_list= new ArrayList<HomeBanner>();

        HomeBanner hb1= new HomeBanner("111",R.drawable.top_banner_android,"#");
        hb_list.add(hb1);
        HomeBanner hb2= new HomeBanner("222",R.drawable.top_banner_android,"#");
        hb_list.add(hb2);
        HomeBanner hb3= new HomeBanner("333",R.drawable.top_banner_android,"#");
        hb_list.add(hb3);
        HomeBanner hb4= new HomeBanner("444",R.drawable.top_banner_android,"#");
        hb_list.add(hb4);
        HomeBanner hb5= new HomeBanner("555",R.drawable.top_banner_android,"#");
        hb_list.add(hb5);
    }
    //初始化ListView
    private void initHLI() {
        hli_list= new ArrayList<HomeListItem>();
        initHLIData();
        HomeListItemAdapter adapter= new HomeListItemAdapter(view.getContext(),R.layout.home_list_item,hli_list);
        ListView listView= (ListView)view.findViewById(R.id.lv_hf);
        listView.setAdapter(adapter);
        ViewGroup.LayoutParams params= listView.getLayoutParams();
        params.height=R.dimen.home_list_item_height*hli_list.size();
        listView.setLayoutParams(params);
        listView.setFocusable(false);
    }
    //初始化本地ListView数据
    private void initHLIData() {
        HomeListItem hli1= new HomeListItem(R.drawable.hli_default, "博哥NB!", R.drawable.hli_special_recommend, "#");
        hli_list.add(hli1);
        HomeListItem hli2= new HomeListItem(R.drawable.hli_default, "博哥NB!", R.drawable.hli_special_recommend, "#");
        hli_list.add(hli2);
        HomeListItem hli3= new HomeListItem(R.drawable.hli_default, "博哥NB!", R.drawable.hli_special_recommend, "#");
        hli_list.add(hli3);
        HomeListItem hli4= new HomeListItem(R.drawable.hli_default, "博哥NB!", R.drawable.hli_special_recommend, "#");
        hli_list.add(hli4);
        HomeListItem hli5= new HomeListItem(R.drawable.hli_default, "博哥NB!", R.drawable.hli_special_recommend, "#");
        hli_list.add(hli5);
    }
    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
