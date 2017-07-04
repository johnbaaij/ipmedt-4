package com.b_healty.john.prototype1.fragments.Text;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 03/07/2017.
 */

public class HomeText extends Text {

    private ViewFlipper viewFlipper;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolBar = (android.support.v7.widget.Toolbar) activity.findViewById(R.id.toolbarMain);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackPressed();

                viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);

                viewFlipper.setDisplayedChild(1);

            }
        });

    }
}
