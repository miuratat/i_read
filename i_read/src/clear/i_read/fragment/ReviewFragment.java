package clear.i_read.fragment;


import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import clear.i_read.R;
import clear.i_read.activity.ReviewActivity;

import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;

@SuppressWarnings("deprecation")
public class ReviewFragment extends Fragment {

	private Button btn_good;
	private Button btn_bad;
	private Bitmap photo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.review_form, container, false);
		
		photo = (Bitmap) getActivity().getIntent().getParcelableExtra("photo");

		ImageView imageView = (ImageView) view.findViewById(R.id.preview);
		imageView.setImageBitmap(photo);

		btn_good = (Button) view.findViewById(R.id.btn_good);
		btn_good.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save(1);
				
				((ReviewActivity)getActivity()).onSaved();
			}
		});

		btn_bad = (Button) view.findViewById(R.id.btn_bad);
		btn_bad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save(0);
				
				((ReviewActivity)getActivity()).onSaved();
			}
		});

		return view;
	}


	private void save(int review) {

		ContentValues values = new ContentValues();
		values.put(CalendarProvider.COLOR, Event.COLOR_RED);
		values.put(CalendarProvider.DESCRIPTION, review == 1 ? "good" : "bad");
		values.put(CalendarProvider.LOCATION, "Some location");
		values.put(CalendarProvider.EVENT, "Event name");

		Calendar cal = Calendar.getInstance();
		TimeZone tz = TimeZone.getDefault();

		int dayJulian = Time.getJulianDay(cal.getTimeInMillis(),
				TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
		
		values.put(CalendarProvider.START, cal.getTimeInMillis());
		values.put(CalendarProvider.START_DAY, dayJulian);
		values.put(CalendarProvider.END, cal.getTimeInMillis());
		values.put(CalendarProvider.END_DAY, dayJulian);
		values.put(CalendarProvider.PHOTO, serializeBitmap(photo));

		getActivity().getContentResolver().insert(CalendarProvider.CONTENT_URI, values);

	}
	
	
	private byte[] serializeBitmap(Bitmap image) {
	   ByteArrayOutputStream bout = new ByteArrayOutputStream();
	   image.compress(Bitmap.CompressFormat.JPEG, 100, bout);
	   return bout.toByteArray();
	}


}
