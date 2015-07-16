package clear.i_read.fragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import clear.i_read.R;

public class PrizeFragment extends Fragment {

	private List<String> imgList = new ArrayList<String>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.prize_main, container, false);
		
		// GridViewのインスタンスを生成
		GridView gridview = (GridView) v.findViewById(R.id.gridview);
		// BaseAdapter を継承したGridAdapterのインスタンスを生成
		// 子要素のレイアウトファイル grid_items.xml を main.xml に inflate するためにGridAdapterに引数として渡す 
		GridAdapter adapter = new GridAdapter(getActivity(), R.layout.prize_item, imgList);
		// gridViewにadapterをセット
		gridview.setAdapter(adapter);
				
		getImagePath();
		
		return v;
	}
	
	private void getImagePath() {
		AssetManager assetManager = getResources().getAssets();
		String[] fileList = null;
		InputStream input = null;
		FileOutputStream output = null;
		String destPath = null;
 
		try {
			fileList = assetManager.list("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		for(int i=0; i< fileList.length ; i++){
			try{
				input = assetManager.open("image/" + fileList[i]);
				
				destPath = "/data/data/"+getActivity().getPackageName()+"/" + fileList[i];
				output=new FileOutputStream(destPath);
 
				int DEFAULT_BUFFER_SIZE = 10240 * 4; 
				byte[] buffer = new byte[DEFAULT_BUFFER_SIZE]; 
				int n = 0; 
				while (-1 != (n = input.read(buffer))) { 
					output.write(buffer, 0, n); 
				}
				output.close();
				input.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			// List<String> imgList にはファイルのパスを入れる
			imgList.add(destPath);
			
		}
 
 
	}
	
	
	class ViewHolder {
		ImageView imageView;
	}
	class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int layoutId;
 
		public GridAdapter(Context context,  int layoutId, List<String> imgList) {
			super();
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.layoutId = layoutId; 
		}
 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String mFilepath = imgList.get(position);
 
			ViewHolder holder;
			if (convertView == null) {
				// main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
				convertView = inflater.inflate(layoutId, parent, false); 
				// ViewHolder を生成
				holder = new ViewHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}
 
			Bitmap bmp = BitmapFactory.decodeFile(mFilepath);
			holder.imageView.setImageBitmap(bmp);
 
			return convertView;
		}
 
		@Override
		public int getCount() {
			// List<String> imgList の全要素数を返す
			return imgList.size();
		}
 
		@Override
		public Object getItem(int position) {
			return null;
		}
 
		@Override
		public long getItemId(int position) {
			return 0;
		}
	}
}
