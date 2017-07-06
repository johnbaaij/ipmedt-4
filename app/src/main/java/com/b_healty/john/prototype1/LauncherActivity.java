package com.b_healty.john.prototype1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.b_healty.john.prototype1.dbhelpers.DBHandler;

import java.lang.reflect.Field;

public class LauncherActivity extends AppCompatActivity {

    DBHandler dbHandler;

    public LauncherActivity() {
        this.dbHandler = new DBHandler(this, null, null, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        String name = dbHandler.usernameToString();
        if (!name.isEmpty()) {
            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("name", name); 	// Your id
            intent.putExtras(b); 	// Put your id to your next Intent
            startActivity(intent);	// start
        }

        else{

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("drawableId", R.drawable.krukken_icon);
            editor.commit();
            startActivity(new Intent(LauncherActivity.this, TutorialActivity.class));
        }
        finish();

    }

    public static class BottomNavigationViewHelper {

        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }
}
