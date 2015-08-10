package clear.i_read.adapter;

import java.util.List;

import com.tyczj.extendedcalendarview.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import clear.i_read.R;

public class EventAdapter extends ArrayAdapter<Event> {

	private int resourceId;
	private List<Event> items;
	private LayoutInflater inflater;
		
	public EventAdapter(Context context, int resourceId, List<Event> items) {
		super(context, resourceId, items);

		this.resourceId = resourceId;
		this.items = items;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = this.inflater.inflate(this.resourceId, null);
        }
        
        Event item = this.items.get(position);
        
        // テキストをセット
	    TextView appInfoText = (TextView)view.findViewById(R.id.item_text);
	    appInfoText.setText(item.getStartDate("yyyy/MM/dd"));

	    // アイコンをセット
	    ImageView appInfoImage = (ImageView)view.findViewById(R.id.item_image);
	    appInfoImage.setImageBitmap(item.getPhoto());
	
	    return view;
    }
}