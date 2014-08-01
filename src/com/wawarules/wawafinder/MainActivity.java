package com.wawarules.wawafinder;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity 
	implements MainFragment.MainFragmentSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        
        
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            
            MainFragment mainFrag = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainFrag)
                    .commit();
        }
        
    }
    
    protected void onStop(){
    	super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        	
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        switch(id){
        	case R.id.action_search:
        		openSearch();
        		return true;
        	case R.id.action_near_me:
        		openNearMe();
        		return true;
        	case R.id.action_settings:
        		openSettings();
        		return true;
        	case R.id.action_exit:
        		exit();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
        } 
    }
    
    public void openSearch(){
    	SearchFragment searchFrag = new SearchFragment();
    	getSupportFragmentManager().beginTransaction()
    		.replace(R.id.fragment_container, searchFrag)
    		.addToBackStack(null)
    		.commit();
    }
    
    public void openNearMe(){
    	NearMeFragment nearMeFrag = new NearMeFragment();
    	getSupportFragmentManager().beginTransaction()
    		.replace(R.id.fragment_container, nearMeFrag)
    		.addToBackStack(null)
    		.commit();
    }
    
    public void openSettings(){
    	SettingsFragment settingsFrag = new SettingsFragment();
    	getSupportFragmentManager().beginTransaction()
    		.replace(R.id.fragment_container, settingsFrag)
    		.addToBackStack(null)
    		.commit();
    }
    
    public void exit(){
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    }
    
    public void sendMessageToMainFrag(String someMessage){
    	MainFragment mFrag =  (MainFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_main);
    	if(someMessage == null){
    		someMessage = getResources().getString(R.string.test_string);
    	}
    	Log.i("MainActivity","sendMessageToMainFrag()");
    	if(mFrag != null){
    		//in two pane layout and the mFrag is available
    		mFrag.setText(someMessage);
    	} else{
            // Create fragment and give it an argument for the selected article
    		MainFragment newMainFrag = new MainFragment();
            Bundle args = new Bundle();
            args.putString(MainFragment.NEW_TEXT, someMessage);
            newMainFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newMainFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
    	}
    	
    }
    

    /**
     * A placeholder fragment containing a simple view.
     */
	public static class PlaceholderFragment extends Fragment {
	
	    public PlaceholderFragment() {
	    }
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	        return rootView;
	    }
	}

}
