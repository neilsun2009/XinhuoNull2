package com.teamnull2.xinhuo;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.amiee.nicetab.NiceTabLayout;
import me.amiee.nicetab.NiceTabStrip;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // private static final String ARG_PARAM1 = "param1";
    // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    // private String mParam1;
    // private String mParam2;
    private NiceTabLayout mNiceTabLayout;
    private FrameLayout mWrapFl;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private boolean mIosStyleIcon = false;
    private List<SamplePagerItem> mTabs = new ArrayList<>();

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance() {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // mParam1 = getArguments().getString(ARG_PARAM1);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
        boolean add = mTabs.add(new SamplePagerItem(
                getString(R.string.tab_home), // Title
                R.drawable.ic_home, // Icon
                R.drawable.ic_home_a, // Icon
                getResources().getColor(R.color.tab_indicator), // Indicator color
                getResources().getColor(R.color.tab_divider), // Divider color
                getFragmentManager().findFragmentByTag("HomeFragment")
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.tab_topic), // Title
                R.drawable.ic_activity, // Icon
                R.drawable.ic_activity_a, // Icon
                getResources().getColor(R.color.tab_indicator), // Indicator color
                getResources().getColor(R.color.tab_divider), // Divider color
                getFragmentManager().findFragmentByTag("TopicFragment")
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.tab_stuff), // Title
                R.drawable.ic_search, // Icon
                R.drawable.ic_search_a, // Icon
                getResources().getColor(R.color.tab_indicator), // Indicator color
                getResources().getColor(R.color.tab_divider), // Divider color
                getFragmentManager().findFragmentByTag("StuffFragment")
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.tab_me), // Title
                R.drawable.ic_me, // Icon
                R.drawable.ic_me_a, // Icon
                getResources().getColor(R.color.tab_indicator), // Indicator color
                getResources().getColor(R.color.tab_divider), // Divider color
                getFragmentManager().findFragmentByTag("MeFragment")
        ));
    }
    static class SamplePagerItem {
        private final CharSequence mTitle;
        private final int mIosIconResId;
        private final int mAndroidIconResId;
        private final int mIndicatorColor;
        private final int mDividerColor;
        private final Fragment mFragment;

        SamplePagerItem(CharSequence title, int iosIconResId, int androidIconResId, int indicatorColor, int dividerColor, Fragment fragment) {
            mTitle = title;
            mIosIconResId = iosIconResId;
            mAndroidIconResId = androidIconResId;
            mIndicatorColor = indicatorColor;
            mDividerColor = dividerColor;
            mFragment= fragment;
        }



        Fragment createFragment() {
            if (mAndroidIconResId==R.drawable.ic_activity_a)
                return TopicFragment.newInstance();
            else if (mAndroidIconResId==R.drawable.ic_search_a)
                return StuffFragment.newInstance();
            else if (mAndroidIconResId==R.drawable.ic_me_a)
                return MeFragment.newInstance();
            return HomeFragment.newInstance();
        }

        CharSequence getTitle() {
            return mTitle;
        }

        int getIosIconResId() {
            return mIosIconResId;
        }

        int getAndroidIconResId() {
            return mAndroidIconResId;
        }

        int getIndicatorColor() {
            return mIndicatorColor;
        }

        int getDividerColor() {
            return mDividerColor;
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mWrapFl = (FrameLayout) view.findViewById(R.id.wrap_fl);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));

        mNiceTabLayout = (NiceTabLayout) view.findViewById(R.id.sliding_tabs);
        mNiceTabLayout.setViewPager(mViewPager);

        mNiceTabLayout.setTabStripColorize(new NiceTabStrip.TabStripColorize() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mTabs.get(position).getDividerColor();
            }
        });

        mNiceTabLayout.setTabColorize(new NiceTabLayout.TabColorize() {

            @Override
            public int getDefaultTabColor(int position) {
                if (isAdded()) {
                    return getResources().getColor(android.R.color.white);
                } else {
                    return Color.WHITE;
                }
            }

            @Override
            public int getSelectedTabColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }
        });

        mNiceTabLayout.setOnIndicatorColorChangedListener((MainActivity) getActivity());
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
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
    }*/

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
    class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements NiceTabLayout.IconTabProvider {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).createFragment();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }

        @Override
        public int getPageIconResId(int position) {
            return mIosStyleIcon ? mTabs.get(position).getIosIconResId() : mTabs.get(position).getAndroidIconResId();
        }
    }
    public void invalidateBlur() {
        mNiceTabLayout.invalidateBlur();
    }
}
