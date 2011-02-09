package com.android.astra2.catalog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class CustomImageView extends ImageView{
	public CustomImageView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    Drawable drawable = getDrawable();
	    if (drawable != null) {
	        int measureHeight = View.MeasureSpec.getSize(heightMeasureSpec);
	        int measureWidth = View.MeasureSpec.getSize(widthMeasureSpec);

	        int resultWidth = measureWidth;
	        float scaleFactor = (float)resultWidth / drawable.getMinimumWidth();
	        int resultHeight = (int)(scaleFactor * drawable.getMinimumHeight());

	        if (resultHeight > measureHeight) {
	            resultHeight = measureHeight;
	            scaleFactor = (float)resultHeight / drawable.getMinimumHeight();
	            resultWidth = (int)(scaleFactor * drawable.getMinimumWidth());
	        }

	        setMeasuredDimension(resultWidth, resultHeight);
	    } else {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }

	}

}
