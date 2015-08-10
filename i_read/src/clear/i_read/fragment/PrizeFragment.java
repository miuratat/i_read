package clear.i_read.fragment;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import clear.i_read.R;
import clear.i_read.adapter.PrizeAdapter;

public class PrizeFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private ArrayList<String> mDataset;

	@Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.prize_main, container, false);

    	mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1); 
    	
    	//mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    	
    	//mRecyclerView.setHasFixedSize(true); 
    	mLayoutManager = new LinearLayoutManager(getActivity());
    	
    	mRecyclerView.setLayoutManager(mLayoutManager); 
    	
    	mDataset = new ArrayList();
    	for(int i = 0 ; i < 50; i++) {
    		mDataset.add("A");
    	}
    	mAdapter = new PrizeAdapter(mDataset);
    	AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter, 5f);
        SlideInBottomAnimationAdapter scaleAdapter = new SlideInBottomAnimationAdapter(alphaAdapter);
        
    	mRecyclerView.setAdapter(scaleAdapter);
    	
    	
//		LinearLayout cardLinear = (LinearLayout) view
//				.findViewById(R.id.prize_card_holder);
//		cardLinear.removeAllViews();
//
//		for (int i = 0; i < 5; i++) {
//			LinearLayout linearLayout = (LinearLayout) inflater.inflate(
//					R.layout.prize_card, null);
//			CardView cardView = (CardView) linearLayout
//					.findViewById(R.id.prize_card);
//
//			ImageView imageView = (ImageView) linearLayout
//					.findViewById(R.id.prize);
//			imageView.setImageDrawable(getResources().getDrawable(R.drawable.image0));
//			cardView.setTag(i);						
//			cardLinear.addView(linearLayout, i);
//		}

		return view;
    }
}
