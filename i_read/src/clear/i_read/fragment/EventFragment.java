package clear.i_read.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import clear.i_read.R;
import clear.i_read.adapter.EventAdapter;

import com.tyczj.extendedcalendarview.Event;

@SuppressWarnings("deprecation")
public class EventFragment extends Fragment {

	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.event_list, container, false);
		listView = (ListView) view.findViewById(R.id.listView);

		ArrayList<Event> events = (ArrayList<Event>) getActivity().getIntent()
				.getSerializableExtra("events");
		EventAdapter adapter = new EventAdapter(getActivity());
		adapter.setEventList(events);

		listView.setAdapter(adapter);

		return view;
	}

}