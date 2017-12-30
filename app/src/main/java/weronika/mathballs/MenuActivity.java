package weronika.mathballs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends Activity {

    ImageView mathballs;
    ImageButton start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.menu_activity);
        start=(ImageButton)findViewById(R.id.start);
        start.setOnClickListener(new PrzyciskStart());
    }

    public class PrzyciskStart implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MenuActivity.this, MainActivity.class));
            finish();
        }
    }
}
