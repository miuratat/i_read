package clear.i_read.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import clear.i_read.R;

public class PrizeAdapter extends RecyclerView.Adapter<PrizeAdapter.ItemViewHolder> {
	
	ArrayList<String> mDataset;

	public static class ItemViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ImageView mImageView;

		public ItemViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.text);
			mImageView = (ImageView) v.findViewById(R.id.prize);
		}
	}

	public PrizeAdapter(ArrayList<String> mDataset) {
		this.mDataset = mDataset;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.prize_card, parent, false);
		return new ItemViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		final String data;
		data = mDataset.get(position);
		holder.mTextView.setText(mDataset.get(position));
		holder.mImageView.setImageResource(R.drawable.image0);

		holder.mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				removeFromDataset(data);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	protected void removeFromDataset(String data) {
		for (int i = 0; i < mDataset.size(); i++) {
			if (mDataset.get(i).equals(data)) {
				mDataset.remove(i);
				notifyItemRemoved(i);
				break;
			}
		}
	}
}