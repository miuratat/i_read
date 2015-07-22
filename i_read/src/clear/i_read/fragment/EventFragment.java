package clear.i_read.fragment;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import clear.i_read.R;

import com.tyczj.extendedcalendarview.Event;

@SuppressWarnings("deprecation")
public class EventFragment extends Fragment {

	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.event_list, container, false);

		ArrayList<Event> events = (ArrayList<Event>) getActivity().getIntent().getSerializableExtra("events");
		
		LinearLayout cardLinear = (LinearLayout) view.findViewById(R.id.cardLinear);
		cardLinear.removeAllViews();

		for (int i = 0; i < events.size(); i++) {
			
			LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.card, null);
			CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
			
			TextView textBox = (TextView) linearLayout.findViewById(R.id.descriptionTextView);
			textBox.setText(events.get(i).getTitle());
			
			ImageView imageView = (ImageView)linearLayout.findViewById(R.id.photo);
			imageView.setImageBitmap(events.get(i).getPhoto());

			cardView.setTag(i);
			cardLinear.addView(linearLayout, i);
		}

		return view;
	}
}
