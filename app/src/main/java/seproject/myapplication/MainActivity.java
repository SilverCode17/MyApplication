package seproject.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean Encrypting=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toggleUi();
        setImageClickListener();

    }

    private void setImageClickListener() {

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Drawable newPhoneImage;
                if (Encrypting) {
                    newPhoneImage =
                            getResources().getDrawable(R.drawable.iconchange);
                    Encrypting = false;
                } else {
                    newPhoneImage =
                            getResources().getDrawable(R.drawable.icon);
                    Encrypting = true;
                }
            imageView.setImageDrawable(newPhoneImage);
        }
        });
    }
       /*
        private void toggleUi() {
            ImageView imageView = (ImageView) findViewById(R.id.icon);
            Drawable newPhoneImage;
            if (Encrypting) {
                newPhoneImage =
                        getResources().getDrawable(R.drawable.iconchange);
            } else {
                newPhoneImage =
                        getResources().getDrawable(R.drawable.icon);
            }
            imageView.setImageDrawable(newPhoneImage);
        }*/
    @Override
    protected void onResume() {
        super.onResume();
        setImageClickListener();
    }
    }

