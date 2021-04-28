package com.example.actividad2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListFragment extends Fragment implements View.OnClickListener {

    //private ArrayList<String> data;
    public RecyclerView recyclerView;
    public JSONArray JSONarray;
    private static String info;
    private static ArrayList<String> ArrayListdatos;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            JSONarray=new JSONArray(ArrayListdatos);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.list_amigos, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);

        listAdapter adapter = new listAdapter(ArrayListdatos, this);
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return v;
    }

    public static ListFragment newInstance(JSONArray json) throws JSONException{
        ListFragment fragment=new ListFragment();
        ArrayListdatos=new ArrayList<String>();
        Bundle args=new Bundle();
        if(json!=null){
            for(int i=0;i<json.length();i++){
                JSONObject temp=json.getJSONObject(i);
                ArrayListdatos.add(temp.toString());
            }
        }
        args.putStringArrayList("JSON",ArrayListdatos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

        int pos = recyclerView.getChildLayoutPosition(v);
        ((MainActivity) getActivity()).toInfo(v, pos);

    }

}