package com.liquid.backgrounds;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @author Gerald Baggett
 * 
 */
class AboutDialog extends Dialog {

	/**
	 * Creates a new instance of AboutDialog.
	 * 
	 * @param context
	 */
	public AboutDialog(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Dialog#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setTitle(String.format(Messages.getString("AboutDialog.0"), //$NON-NLS-1$
				this.getContext().getString(R.string.app_name), this
						.getContext().getString(R.string.versionInfo)));

		this.setContentView(R.layout.about);

		this.findViewById(R.id.aboutCloseButton).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}
}