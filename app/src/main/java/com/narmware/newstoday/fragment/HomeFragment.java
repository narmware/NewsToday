package com.narmware.newstoday.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.narmware.newstoday.R;
import com.narmware.newstoday.adapter.HomeNewsAdapter;
import com.narmware.newstoday.pojo.HomeNews;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
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

    private OnFragmentInteractionListener mListener;

    ArrayList<HomeNews> homeNews;
    HomeNewsAdapter homeNewsAdapter;
    @BindView(R.id.foldable_list)protected FoldableListLayout mNewsList;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        ButterKnife.bind(this,view);
        homeNews=new ArrayList<>();
        homeNews.add(new HomeNews(getResources().getColor(R.color.amber_300),"Rohit completed his task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software."));
        homeNews.add(new HomeNews(getResources().getColor(R.color.red_300),"Vrushali completed her task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software."));

        homeNews.add(new HomeNews(getResources().getColor(R.color.blue_300),"Suraj completed his task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software."));
        HomeNewsAdapter homeNewsAdapter=new HomeNewsAdapter(homeNews,getContext());
        mNewsList.setAdapter(homeNewsAdapter);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
