package com.narmware.newstoday.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.narmware.newstoday.MyApplication;
import com.narmware.newstoday.R;
import com.narmware.newstoday.activity.Home;
import com.narmware.newstoday.adapter.HomeNewsAdapter;
import com.narmware.newstoday.customfonts.MyTextView;
import com.narmware.newstoday.helpers.SupportFunctions;
import com.narmware.newstoday.pojo.CategoryNews;
import com.narmware.newstoday.pojo.Excpert;
import com.narmware.newstoday.pojo.FeaturedImage;
import com.narmware.newstoday.pojo.HomeNews;
import com.narmware.newstoday.pojo.Title;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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

    RequestQueue mVolleyRequest;
    Dialog mNoConnectionDialog;
    ArrayList<CategoryNews> mCategoryNews=new ArrayList<>();
    Excpert excpert;
    Title title;
    String date;
    String image_url;
    ArrayList<FeaturedImage> mFeaturedImages;


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
        mVolleyRequest = Volley.newRequestQueue(getContext());
        homeNews=new ArrayList<>();
        mFeaturedImages=new ArrayList<>();

        homeNews.clear();
        setAdapter();
        GetCatNews();

    }

    public void setAdapter()
    {
       /* homeNews.add(new HomeNews("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-HKO8qwCgBNGEdbxoW7z51BIDRd8ni1gbaJORgO8MbWX9DHRB","Rohit completed his task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software.","News 1",""));
        homeNews.add(new HomeNews("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIlQxMDgw62pUFpwsuBaEzFDCh2773zvEdicGuhGAe2Odpw-a74Q","Vrushali completed her task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software.","News 2",""));

        homeNews.add(new HomeNews("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKzfLd87SIls2APD7csxZhVBOb-B_VNGfnXlVJ6ufmAjPsCOmB","Suraj completed his task in just 2 seconds.","Intelligence has been defined in many different ways including as one's capacity for logic, understanding, self-awareness, learning, emotional knowledge, reasoning, planning, creativity, and problem solving. It can be more generally described as the ability to perceive or infer information, and to retain it as knowledge to be applied towards adaptive behaviors within an environment or context.\n" +
                "\n" +
                "Intelligence is most widely studied in humans but has also been observed in both non-human animals and in plants. Intelligence in machines is called artificial intelligence, which is commonly implemented in computer systems using program software.","News 3",""));*/
        homeNewsAdapter=new HomeNewsAdapter(homeNews,getContext());
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


    private void GetCatNews() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("getting details ...");
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String,String> param = new HashMap();
        param.put("categories","6");

        String url= SupportFunctions.appendParam(MyApplication.URL_CAT_NEWS,param);
        Log.e("Url",url);

        JsonArrayRequest obreq = new JsonArrayRequest(Request.Method.GET,url,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {

                        try
                        {
                            //getting test master array
                            // testMasterDetails = testMasterArray.toString();

                            Log.e("Json_string",response.toString());
                            Gson gson = new Gson();

                            JSONArray jsonArray=response;

                           /* for(CategoryNews item:jsonArray) {

                            }*/

                            for(int i=0;i<jsonArray.length();i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.e("Json_string object", jsonObject.toString());

                                CategoryNews categoryNews= gson.fromJson(jsonObject.toString(),CategoryNews.class);
                                Log.e("Json cat id",categoryNews.getId()+"");

                                mCategoryNews.add(categoryNews);

                            }
                            Log.e("Json cat size", mCategoryNews.size()+" ");

                            for(int cat=0;cat<mCategoryNews.size();cat++)
                            {
                                GetFeaturedImage(mCategoryNews.get(cat).getFeatured_media(),cat);

                              /*  excpert = mCategoryNews.get(cat).getExcpert();
                                title = mCategoryNews.get(cat).getTitle();
                                date = mCategoryNews.get(cat).getDate();

                                homeNews.add(new HomeNews(image_url, title.getRendered(), excpert.getRendered(), title.getRendered(), date));
                                Log.e("Json cat data", mCategoryNews.get(cat).getId() + "  " + title.getRendered() + "  " + mCategoryNews.get(cat).getDate() + "  " + mCategoryNews.get(cat).getSlug() + "  " + excpert.getRendered() + "  " + mCategoryNews.get(cat).getFeatured_media());*/
                            }

                            homeNewsAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                        dialog.dismiss();
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Test Error");

                        dialog.dismiss();

                    }
                }
        );
        mVolleyRequest.add(obreq);
    }

    private void GetFeaturedImage(final int mediaid, final int pos) {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("getting details ...");
        dialog.setCancelable(false);
        dialog.show();

        /*HashMap<String,String> param = new HashMap();
        param.put(Constants.USER_ID,SharedPreferencesHelper.getUserId(getContext()));*/

        String url= MyApplication.URL_NEWS_IMAGE+mediaid;
        Log.e("Url",url);

        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,url,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            //getting test master array
                            // testMasterDetails = testMasterArray.toString();

                            Log.e("Json_string feat. img",response.toString());
                            Gson gson = new Gson();

                                FeaturedImage featuredImage = gson.fromJson(response.toString(), FeaturedImage.class);
                                image_url = featuredImage.getLink();
                                mFeaturedImages.add(featuredImage);

                            excpert = mCategoryNews.get(pos).getExcpert();
                                title = mCategoryNews.get(pos).getTitle();
                                date = mCategoryNews.get(pos).getDate();

                                homeNews.add(new HomeNews(image_url, title.getRendered(), excpert.getRendered(), title.getRendered(), date));
                                Log.e("Json cat data", mCategoryNews.get(pos).getId() + "  " + title.getRendered() + "  " + mCategoryNews.get(pos).getDate() + "  " + mCategoryNews.get(pos).getSlug() + "  " + excpert.getRendered() + "  " + mCategoryNews.get(pos).getFeatured_media());

                                homeNewsAdapter.notifyDataSetChanged();


                            Log.e("Json_string url", image_url);

                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }

                        dialog.dismiss();
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Test Error");
                        dialog.dismiss();

                    }
                }
        );
        mVolleyRequest.add(obreq);
    }
    private void showNoConnectionDialog() {
        mNoConnectionDialog = new Dialog(getContext(), android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        mNoConnectionDialog.setContentView(R.layout.dialog_noconnectivity);
        mNoConnectionDialog.setCancelable(false);
        mNoConnectionDialog.show();

        Button exit = mNoConnectionDialog.findViewById(R.id.dialog_no_connec_exit);
        Button tryAgain = mNoConnectionDialog.findViewById(R.id.dialog_no_connec_try_again);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AppCompatActivity act = (AppCompatActivity) getContext();
                act.finish();
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mNoConnectionDialog.dismiss();
            }
        });
    }
}
