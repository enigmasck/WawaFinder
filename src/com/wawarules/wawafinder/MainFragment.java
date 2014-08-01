package com.wawarules.wawafinder;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFragment extends Fragment implements OnClickListener{
	MainFragmentSelectedListener mCallback;
	final static String NEW_TEXT = "this should not show.";
	
    // Container Activity must implement this interface
    public interface MainFragmentSelectedListener {
        public void sendMessageToMainFrag(String someMessage);
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
    	Button changeTextButton = (Button) v.findViewById(R.id.main_fragment_button);
    	changeTextButton.setOnClickListener(this);
    	Log.i("MainFragment","onCreateView() - End");
        return v;
    }
    
    @Override
    public void onStart(){
    	super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            setText(args.getString(NEW_TEXT));
        } 
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
    	Log.i("MainFragment","setText() - Start");
    	TextView mfTextView = (TextView) getActivity().findViewById(R.id.main_fragment_text_view);
    	Log.i("MainFragment","setText() - After instantiating mfTextView");
    	mfTextView.setText(newText);
    	Log.i("MainFragment","setText() - After mfTextView.setText()");
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.main_fragment_button:
			mCallback.sendMessageToMainFrag(getResources().getString(R.string.main_frag_change_text));
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
