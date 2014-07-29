package com.wawarules.wawafinder;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class MainFragment extends Fragment{
	MainFragmentSelectedListener mCallback;
	
    // Container Activity must implement this interface
    public interface MainFragmentSelectedListener {
        public void sendMessageToMainFrag(String someMessage);
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (MainFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MainFragmentSelectedListener");
        }
    }
    
    public void setText(String newText){
    	EditText editFragText = (EditText) findViewById(R.id.main_fragment_text_view);
    }
}
