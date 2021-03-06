package clear.i_read.fragment;

import java.io.IOException;
import java.util.List;

import jp.ogwork.camerafragment.camera.CameraFragment;
import jp.ogwork.camerafragment.camera.CameraSurfaceView.OnPictureSizeChangeListener;
import jp.ogwork.camerafragment.camera.CameraSurfaceView.OnPreviewSizeChangeListener;
import jp.ogwork.camerafragment.camera.CameraSurfaceView.OnTakePictureListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.FrameLayout;
import clear.i_read.R;
import clear.i_read.activity.MainActivity;
import clear.i_read.activity.ReviewActivity;
import clear.i_read.util.BitmapResizer;

@SuppressWarnings("deprecation")
public class ShotFragment extends Fragment {

	private static final int REVIEW_ACTIVITY = 1;

	protected static final String TAG_CAMERA_FRAGMENT = "camera";

	private FrameLayout fl_camera;
	private CameraFragment cameraFragment;
	private Button btn_take;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.shot_main, container, false);

		if (savedInstanceState == null) {
			/** カメラサイズを決定するため、Viewのサイズを取る */
			fl_camera = (FrameLayout) v.findViewById(R.id.fl_camera);
			fl_camera.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {

						@Override
						public void onGlobalLayout() {
							fl_camera.getViewTreeObserver().removeGlobalOnLayoutListener(this);
							addCameraFragment(fl_camera.getWidth(), fl_camera.getHeight(), R.id.fl_camera);
						}
					});
		}

		btn_take = (Button) v.findViewById(R.id.btn_take);
		btn_take.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// cameraFragment.enableShutterSound(false);
				takePicture();
			}
		});

		return v;
	}

	/***
	 * CameraFragmentをviewに追加
	 * 
	 * @param viewWidth
	 * @param viewHeight
	 */
	public void addCameraFragment(final int viewWidth, final int viewHeight,
			int containerViewId) {

		cameraFragment = new CameraFragment();
		Bundle args = new Bundle();
		args.putInt(CameraFragment.BUNDLE_KEY_CAMERA_FACING,Camera.CameraInfo.CAMERA_FACING_BACK);
		cameraFragment.setArguments(args);

		/** プレビューサイズリスナの設定 */
		cameraFragment.setOnPreviewSizeChangeListener(new OnPreviewSizeChangeListener() {

					/** サイズ変更前 */
					@Override
					public Size onPreviewSizeChange(List<Size> supportedPreviewSizeList) {
						return cameraFragment.choosePreviewSize(
								supportedPreviewSizeList, 0, 0, viewWidth,viewHeight);
					}

					/** サイズ変更後 */
					@Override
					public void onPreviewSizeChanged(Size previewSize) {

						float viewAspectRatio = (float) viewHeight / previewSize.width;
						int height = viewHeight;
						int width = (int) (viewAspectRatio * previewSize.height);

						/** 縦横どちらでfixさせるか */
						if (width < viewWidth) {
							/** 縦fixだと幅が足りないと判断 */
							/** 横fixさせる */
							width = viewWidth;
							height = (int) (viewAspectRatio * previewSize.width);
						}
						/** cameraSurfaceViewのサイズ変更 */
						cameraFragment.setLayoutBounds(width, height);
						return;
					}
				});

		/** カメラ保存サイズリスナの設定 */
		cameraFragment
				.setOnPictureSizeChangeListener(new OnPictureSizeChangeListener() {
					@Override
					public Size onPictureSizeChange(
							List<Size> supportedPictureSizeList) {
						/** 画面横幅以下の中で最大サイズを選ぶ */
						return cameraFragment.choosePictureSize(supportedPictureSizeList, 0, 0, viewWidth, viewHeight);
					}
				});

		getFragmentManager().beginTransaction().add(containerViewId, cameraFragment, TAG_CAMERA_FRAGMENT).commit();
	}

	/***
	 * 撮影・保存
	 */
	private void takePicture() {

		cameraFragment.takePicture(false, new OnTakePictureListener() {

			@Override
			public void onShutter() {

			}

			@Override
			public void onPictureTaken(Bitmap bitmap, Camera camera) {
				
				Intent intent = new Intent(getActivity(), ReviewActivity.class);
				intent.putExtra("photo", bitmap);
				startActivityForResult(intent, REVIEW_ACTIVITY);
				
				/*
				BitmapResizer bitmapResizer = new BitmapResizer(getActivity());

				Bitmap photo;
				try {
					photo = bitmapResizer.resize(bitmap,200,200);
					
					Intent intent = new Intent(getActivity(), ReviewActivity.class);
					intent.putExtra("photo", photo);
					startActivityForResult(intent, REVIEW_ACTIVITY);
					
				} catch (IOException e) {
					e.printStackTrace();
				}*/

			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == REVIEW_ACTIVITY) {
			if (resultCode == android.app.Activity.RESULT_OK) {
				ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
				actionBar.getTabAt(0).select();
			}
		}
	}

}