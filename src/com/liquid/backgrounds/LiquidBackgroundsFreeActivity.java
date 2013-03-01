package com.liquid.backgrounds;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class LiquidBackgroundsFreeActivity extends Activity {

	public class AddImgAdp extends BaseAdapter {
		private Context cont;
		int LiqItemBg;

		public AddImgAdp(Context c) {
			cont = c;
			TypedArray typArray = obtainStyledAttributes(R.styleable.LiquidTheme);
			LiqItemBg = typArray.getResourceId(
					R.styleable.LiquidTheme_android_galleryItemBackground, 0);
			typArray.recycle();

		}

		@Override
		public int getCount() {
			return Imgid.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView = new ImageView(cont);

			imgView.setImageResource(Imgid[position]);
			imgView.setLayoutParams(new Gallery.LayoutParams(100, 100));
			imgView.setScaleType(ImageView.ScaleType.FIT_XY);
			imgView.setBackgroundResource(LiqItemBg);

			return imgView;
		}
	}

	private static final int DIALOG_ABOUT = 0;
	private Gallery gallery;
	private Integer[] Imgid = { R.drawable.a_01, R.drawable.a_02, R.drawable.a_03,
			R.drawable.a_04, R.drawable.a_05, R.drawable.a_06, R.drawable.a_07,
			R.drawable.a_08, R.drawable.a_09, R.drawable.a_10, R.drawable.a_11,
			R.drawable.a_12, R.drawable.a_13, R.drawable.a_14, R.drawable.a_15,
			R.drawable.a_16, R.drawable.a_17, R.drawable.a_18, R.drawable.a_19,
			R.drawable.a_20, R.drawable.a_21, R.drawable.a_22, R.drawable.a_23,
			R.drawable.a_24, R.drawable.a_25, R.drawable.a_26, R.drawable.a_27,
			R.drawable.a_28, R.drawable.a_29, R.drawable.a_30 };

	private ImageView imgView;

	int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		position = 0;
		imgView = (ImageView) findViewById(R.id.ImageView01);
		imgView.setImageResource(Imgid[0]);

		gallery = (Gallery) findViewById(R.id.liquidgallery);
		gallery.setAdapter(new AddImgAdp(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(
					@SuppressWarnings("rawtypes") AdapterView parent, View v,
					int position, long id) {
				imgView.setImageResource(Imgid[position]);
				LiquidBackgroundsFreeActivity.this.position = position;
			}
		});

		imgView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {

				AlertDialog alertDialog = new AlertDialog.Builder(
						LiquidBackgroundsFreeActivity.this).create();
				alertDialog.setTitle(Messages.getString("DIA_NUM.0")); //$NON-NLS-1$
				alertDialog.setMessage(Messages.getString("DIA_NUM.1")); //$NON-NLS-1$
				alertDialog.setButton(Messages.getString("DIA_NUM.2"), //$NON-NLS-1$
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								Bitmap bitmap = BitmapFactory.decodeResource(
										getResources(), Imgid[position]);
								try {
									LiquidBackgroundsFreeActivity.this
											.setWallpaper(bitmap);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Log.d(Messages.getString("DIA_NUM.3"), Messages.getString("DIA_NUM.4")); //$NON-NLS-1$ //$NON-NLS-2$

							}
						});

				alertDialog.show();
				return true;
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ABOUT:
			return new AboutDialog(this);

		default:
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.main_menu, menu);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.aboutMenuItem:
			this.showDialog(DIALOG_ABOUT);
			return true;
		}

		return false;
	}

}
