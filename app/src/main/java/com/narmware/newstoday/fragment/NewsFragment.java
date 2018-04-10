package com.narmware.newstoday.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.narmware.newstoday.adapter.NewsAdapter;
import com.narmware.newstoday.helpers.SupportFunctions;
import com.narmware.newstoday.pojo.CategoryNews;
import com.narmware.newstoday.pojo.Excpert;
import com.narmware.newstoday.pojo.FeaturedImage;
import com.narmware.newstoday.pojo.HomeNews;
import com.narmware.newstoday.pojo.News;
import com.narmware.newstoday.pojo.Title;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NEWS_ID = "news_id";

    // TODO: Rename and change types of parameters
    private int mId;

    private OnFragmentInteractionListener mListener;
    NewsAdapter newsAdapter;
    List<News> news;
    RecyclerView mRecyclerView;
    RequestQueue mVolleyRequest;
    Dialog mNoConnectionDialog;
    ArrayList<CategoryNews> mCategoryNews=new ArrayList<>();
    Excpert excpert;
    Title title;
    String date;
    String image_url,link;
    ArrayList<FeaturedImage> mFeaturedImages=new ArrayList<>();
    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(int id) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(NEWS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(NEWS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news, container, false);
        setAdapter(view);
        mVolleyRequest = Volley.newRequestQueue(getContext());
        GetCatNews();
        return view;
    }

    public void setAdapter(View v){
        news=new ArrayList<>();
       /* if(mId==1) {
            news.add(new News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_xf1FJ_OnwOSJqN1wY397SLNAr70HWo7oEGHdEi0Q1__A5SgV9g", "This is first news", "This is first news","",""));
            news.add(new News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXDoUVQJUZsdOsPoxGyrTfeo-IeHmotP1gxXWcfiLwHhJAI8o", "This is second news", "This is second news","",""));
        }
        if(mId==2) {
            news.add(new News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTObyPteqcWpkYDatiuFWmIW13HjBcAG6fMVm3YrRxmmx39OoPAvA", "This is Football news", "This is Football news","",""));
            news.add(new News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8ooOXgDLIyC7afHFzsa0CUBTcKodY3Lr_yaYdODAB-0-Dxcc56g", "This is cricket news", "This is cricket news","",""));
            news.add(new News("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTObyPteqcWpkYDatiuFWmIW13HjBcAG6fMVm3YrRxmmx39OoPAvA", "This is Football news", "This is Football news","",""));

        }*/

        SnapHelper snapHelper = new LinearSnapHelper();
        mRecyclerView = v.findViewById(R.id.recyclerview);
        newsAdapter = new NewsAdapter(getContext(), news);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(newsAdapter);
       // mRecyclerView.addOnScrollListener(new CustomScrollListener());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);
       // snapHelper.attachToRecyclerView(mRecyclerView);
        newsAdapter.notifyDataSetChanged();

    }

    public class CustomScrollListener extends RecyclerView.OnScrollListener {
        public CustomScrollListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    //System.out.println("The RecyclerView is not scrolling");
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    //System.out.println("Scrolling now");
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    //System.out.println("Scroll Settling");
                    break;

            }

        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            //for horizontal scrolling
           /* if (dx > 0) {
                System.out.println("Scrolled Right");
            } else if (dx < 0) {
                System.out.println("Scrolled Left");
            } else {
                System.out.println("No Horizontal Scrolled");
            }*/

            //for vertical scrolling
            if (dy > 0) {
                System.out.println("Scrolled Downwards");

            }

            else if (dy < 0) {
                System.out.println("Scrolled Upwards");
            }

            else {
                System.out.println("No Vertical Scrolled");
            }
        }
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
        dialog.setMessage("getting News ...");
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String,String> param = new HashMap();
        param.put("categories", String.valueOf(mId));

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
                            mCategoryNews.clear();
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

                                news.add(new HomeNews(image_url, title.getRendered(), excpert.getRendered(), title.getRendered(), date));
                                Log.e("Json cat data", mCategoryNews.get(cat).getId() + "  " + title.getRendered() + "  " + mCategoryNews.get(cat).getDate() + "  " + mCategoryNews.get(cat).getSlug() + "  " + excpert.getRendered() + "  " + mCategoryNews.get(cat).getFeatured_media());*/
                            }

                            newsAdapter.notifyDataSetChanged();

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
        //dialog.show();

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
                            link=mCategoryNews.get(pos).getLink();

                            news.add(new News(link,image_url, title.getRendered(), excpert.getRendered(), title.getRendered(), date));
                            Log.e("Json cat data", mCategoryNews.get(pos).getId() + "  " + title.getRendered() + "  " + mCategoryNews.get(pos).getDate() + "  " + mCategoryNews.get(pos).getSlug() + "  " + excpert.getRendered() + "  " + mCategoryNews.get(pos).getFeatured_media());

                            newsAdapter.notifyDataSetChanged();


                            Log.e("Json_string url", image_url);

                        } catch (Exception e) {
                            e.printStackTrace();
                            //dialog.dismiss();
                        }

                        //dialog.dismiss();
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Test Error");
                       // dialog.dismiss();

                    }
                }
        );
        mVolleyRequest.add(obreq);
    }
}

