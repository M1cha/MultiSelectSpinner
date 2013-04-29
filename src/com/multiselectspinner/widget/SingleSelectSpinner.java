package com.multiselectspinner.widget;

import java.util.ArrayList;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.widget.Spinner;
import com.multiselectspinner.R;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

/**
 * A Spinner view that does not dismiss the dialog displayed when the control is "dropped down"
 * and the user presses it. This allows for the selection of more than one option.
 */
public class SingleSelectSpinner extends Spinner{
    
	private String _title = null;
    private int _icon=-1;
    /**
     * Constructor for use when instantiating directly.
     * @param context
     */
    public SingleSelectSpinner(Context context) {
        super(context);
    }

    /**
     * Constructor used by the layout inflater.
     * @param context
     * @param attrs
     */
    public SingleSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public void onClick(DialogInterface dialog, int which) {
    	setSelection(which);
    	dialog.dismiss();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performClick() {
    	// build arraylist
    	ArrayList<String> al = new ArrayList<String>();
    	for(int i=0; i<getAdapter().getCount(); i++) {
    		al.add(i, getAdapter().getItem(i).toString());
    	}
    	
    	// convert arraylist to string-array
    	String[] items = new String[al.size()];
    	items = al.toArray(items);
    	
    	// build and show dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setSingleChoiceItems(items, (int) getSelectedItemId(), this);
        builder.setNeutralButton(R.string.back_button_label, null);
        if(_icon!=-1) builder.setIcon(_icon);
        if(_title!=null) builder.setTitle(_title);
        builder.show();
        
        return true;
    }
    
    public void setTitle(String title) {
    	_title = title;
    }
    public void setIcon(int resId) {
    	_icon = resId;
    }
}
