package org.hualang.Gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class Example_Gallery1Activity extends Activity implements
		OnItemSelectedListener, ViewFactory, OnClickListener {

	private ImageSwitcher mSwitcher;

	private Integer[] mThumbIds = { R.drawable.sd, R.drawable.m2_03,
			R.drawable.m3_03, R.drawable.m4_03 };

	private Integer[] mImageIds = { R.drawable.m1_05, R.drawable.m2_05,
			R.drawable.m3_05, R.drawable.m4_05 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		setContentView(R.layout.main);
		
		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		mSwitcher.setFactory(this);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		mSwitcher.setOnClickListener(this);

		Gallery g = (Gallery) findViewById(R.id.gallery);

		g.setAdapter(new ImageAdapter(this));
		g.setOnItemSelectedListener(this);
		g.setSelection(3);
		

	}

	// 图片适配器的代码块编写：
	public class ImageAdapter extends BaseAdapter {

		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);

			i.setImageResource(mThumbIds[position % mThumbIds.length]);
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			i.setBackgroundResource(R.drawable.bg);

			return i;
		}

	}

	public void onItemSelected(AdapterView<?> adapter, View v, int position,
			long id) {
		mSwitcher.setImageResource(mImageIds[position % mImageIds.length]);
		if (position==3) {
			
		}
		else {
			Toast.makeText(Example_Gallery1Activity.this, "你选中了第"+position+"张图片了", Toast.LENGTH_SHORT).show();
		}
		
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {

	}

	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Toast.makeText(Example_Gallery1Activity.this, "你选中了第几张图片了！", Toast.LENGTH_SHORT).show();
	}
}