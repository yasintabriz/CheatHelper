package ir.silvertech.cheathelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import sdk.adenda.widget.AdendaUnlockInterface;
import sdk.adenda.widget.AdendaUnlockWidget;


/**
 * Created by yasin on 9/14/2015.
 */

public class SlideButton extends SeekBar implements AdendaUnlockWidget {


    AdendaUnlockInterface aui;
    private Drawable thumb;

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        this.thumb = thumb;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (thumb.getBounds().contains((int) event.getX(), (int) event.getY())) {
                super.onTouchEvent(event);
            } else
                return false;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getProgress() > 49)
                handleSlide();

            setProgress(0);
        } else
            super.onTouchEvent(event);

        return true;
    }


    @Override
    public void setAdendaUnlockInterface(AdendaUnlockInterface adendaUnlockInterface) {
        aui = adendaUnlockInterface;

    }

    private void handleSlide() {
        aui.simpleUnlock();
        aui = null;
    }

}