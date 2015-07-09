package clear.i_read.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import clear.i_read.R;
import clear.i_read.activity.EventActivity;

import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;
import com.tyczj.extendedcalendarview.ExtendedCalendarView.OnDayClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalendarFragment extends Fragment implements OnDayClickListener {

	private ExtendedCalendarView calender;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.calendar_main, container,
				false);
		calender = (ExtendedCalendarView) rootView
				.findViewById(R.id.calendar);
		calender.setOnDayClickListener(this);
		return rootView;
	}

	@Override
	public void onDayClicked(AdapterView<?> adapter, View view, int position,
			long id, Day day) {

		if (day.getNumOfEvenets() > 0) {

			// 遷移先のactivityを指定してintentを作成
			Intent intent = new Intent(getActivity(), EventActivity.class);

			// intentへ添え字付で値を保持させる
			intent.putExtra("events", day.getEvents());
			// 指定のActivityを開始する
			startActivity(intent);

		}
	}

	public void refresh() {
		calender.refreshCalendar();
	}
}
