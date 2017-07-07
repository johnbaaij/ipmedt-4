package com.b_healty.john.prototype1.fragments.tutorial;

import android.view.View;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 14/06/2017.
 */

public class FinalTutorial extends BasicTutorial {


    @Override
    public void tutNext() {
        tutSkip();
    }

    @Override
    public void updateText(int count) {

        tutText.setText(R.string.tut_final);
        tutNext.setText("Begin");
        tutSkip.setVisibility(View.INVISIBLE);
        tutImage.setImageResource(R.drawable.user_screen);
    }
}
