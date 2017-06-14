package com.b_healty.john.prototype1.Fragments.tutorial;

import android.view.View;

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

        tutText.setText("final");
        tutNext.setText("Begin");
        tutSkip.setVisibility(View.INVISIBLE);
    }
}
