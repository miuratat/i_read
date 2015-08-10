package clear.i_read.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import clear.i_read.R;
import clear.i_read.adapter.EventAdapter;
import clear.i_read.adapter.EventItem;

@SuppressWarnings("deprecation")
public class EventFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//ArrayList<Event> events = (ArrayList<Event>) getActivity().getIntent().getSerializableExtra("events");
		ArrayList<Event> events = new ArrayList<Event>();
		Calendar cal = Calendar.getInstance();
		TimeZone tz = TimeZone.getDefault();
		int dayJulian = Time.getJulianDay(cal.getTimeInMillis(),
				TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
		
		/*
		Cursor c = getActivity().getContentResolver().query(CalendarProvider.CONTENT_URI,
				new String[] {
						CalendarProvider.ID,CalendarProvider.EVENT,
						CalendarProvider.DESCRIPTION,
						CalendarProvider.LOCATION,
						CalendarProvider.START,
						CalendarProvider.END,
						CalendarProvider.COLOR,
						CalendarProvider.PHOTO
				},"?>="+CalendarProvider.START_DAY+" AND "+ CalendarProvider.END_DAY+" >= ?", 
				new String[] {
					String.valueOf(dayJulian),
					String.valueOf(dayJulian)
				}, null);
		*/
		
		Cursor c = getActivity().getContentResolver().query(CalendarProvider.CONTENT_URI,
				new String[] {
						CalendarProvider.ID,CalendarProvider.EVENT,
						CalendarProvider.DESCRIPTION,
						CalendarProvider.LOCATION,
						CalendarProvider.START,
						CalendarProvider.END,
						CalendarProvider.COLOR,
						CalendarProvider.PHOTO
				},null,null, CalendarProvider.START_DAY + " DESC");
		
		if(c != null){
			if(c.moveToFirst()){
				do{
					Event event = new Event(c.getLong(0),c.getLong(4),c.getLong(5));
					event.setName(c.getString(1));
					event.setDescription(c.getString(2));
					event.setLocation(c.getString(3));
					event.setColor(c.getInt(6));
					event.setPhoto(c.getBlob(7));
					events.add(event);
				}while(c.moveToNext());	
			}	
			c.close();
		}
		

		View view = inflater.inflate(R.layout.event_list, container, false);

		
		
		List<EventItem> list = new ArrayList<EventItem>();

		for (int i = 0; i < events.size(); i++) {
			EventItem item = new EventItem();
			item.setText("アイテム" + i);
			item.setPhoto(events.get(i).getPhoto());
			list.add(item);
		}

		// adapterのインスタンスを作成
		EventAdapter adapter = new EventAdapter(getActivity(), R.layout.event_row, events);

		ListView lv = (ListView) view.findViewById(R.id.listview);
		lv.setAdapter(adapter);

		return view;
	}

	/*
	 * private ListView listView;
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) {
	 * 
	 * View view = inflater.inflate(R.layout.event_list, container, false);
	 * 
	 * ArrayList<Event> events = (ArrayList<Event>) getActivity().getIntent()
	 * .getSerializableExtra("events");
	 * 
	 * LinearLayout cardLinear = (LinearLayout) view
	 * .findViewById(R.id.event_card_holder); cardLinear.removeAllViews();
	 * 
	 * for (int i = 0; i < events.size(); i++) { final Bitmap photo =
	 * events.get(i).getPhoto(); LinearLayout linearLayout = (LinearLayout)
	 * inflater.inflate( R.layout.event_card, null); CardView cardView =
	 * (CardView) linearLayout .findViewById(R.id.event_card);
	 * 
	 * TextView textBox = (TextView) linearLayout
	 * .findViewById(R.id.descriptionTextView); textBox.setText("あいうえお");
	 * 
	 * ImageView imageView = (ImageView) linearLayout .findViewById(R.id.photo);
	 * imageView.setImageBitmap(photo); cardView.setTag(i);
	 * 
	 * cardView.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) {
	 * 
	 * // アラートダイアログ作成. AlertDialog.Builder alert = new
	 * AlertDialog.Builder(getActivity()); // ページ作成 LinearLayout ll = new
	 * LinearLayout(getActivity()); ll.setOrientation(LinearLayout.VERTICAL);
	 * ImageView imageView = new ImageView(getActivity());
	 * imageView.setImageBitmap(photo); imageView.setPadding(5, 5, 5, 5);
	 * ll.addView(imageView);
	 * 
	 * // ダイアログにviewを設定 alert.setView(ll); alert.setPositiveButton("とじる", null);
	 * alert.show(); } });
	 * 
	 * cardLinear.addView(linearLayout, i); }
	 * 
	 * return view; }
	 * 
	 * private byte[] serializeBitmap(Bitmap image) { ByteArrayOutputStream bout
	 * = new ByteArrayOutputStream(); image.compress(Bitmap.CompressFormat.JPEG,
	 * 100, bout); return bout.toByteArray(); }
	 */
}
