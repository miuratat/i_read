package clear.i_read.adapter;

import java.util.ArrayList;

import clear.i_read.R;

import com.tyczj.extendedcalendarview.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater = null;
	ArrayList<Event> eventList;

	public EventAdapter(Context context) {
			this.context = context;
			this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setEventList(ArrayList<Event> eventList) {
			this.eventList = eventList;
	}

	@Override
	public int getCount() {
			return eventList.size();
	}

	@Override
	public Object getItem(int position) {
			return eventList.get(position);
	}

	@Override
	public long getItemId(int position) {
			return eventList.get(position).getEventId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			convertView = layoutInflater.inflate(R.layout.event_row, parent, false);

			((ImageView) convertView.findViewById(R.id.photo)).setImageBitmap(eventList.get(
					position).getPhoto());
			((TextView) convertView.findViewById(R.id.review))
					.setText(eventList.get(position).getDescription());

			return convertView;
	}

}