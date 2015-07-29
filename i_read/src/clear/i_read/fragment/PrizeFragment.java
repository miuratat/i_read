package clear.i_read.fragment;

import java.util.ArrayList;

import com.tyczj.extendedcalendarview.Event;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import clear.i_read.R;

public class PrizeFragment extends Fragment {



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.prize_main, container, false);

		LinearLayout cardLinear = (LinearLayout) view
				.findViewById(R.id.prize_card_holder);
		cardLinear.removeAllViews();

		for (int i = 0; i < 5; i++) {
			LinearLayout linearLayout = (LinearLayout) inflater.inflate(
					R.layout.prize_card, null);
			CardView cardView = (CardView) linearLayout
					.findViewById(R.id.prize_card);

			ImageView imageView = (ImageView) linearLayout
					.findViewById(R.id.prize);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.image0));
			cardView.setTag(i);						
			cardLinear.addView(linearLayout, i);
		}

		return view;
    }
   
}
