package ro.pub.cs.systems.eim.colocviu1_1;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviul_1MainActivity extends Activity {
    private TextView textView;
    private Button northButton, eastButton, westButton, southButton, navigateToSecondaryActivity;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private Integer numberOfSelectedPoints = 0;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String pointsText = textView.getText().toString();
            switch(view.getId()) {
                case R.id.east_button:
                    numberOfSelectedPoints++;
                    Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
                    pointsText += ", East";
                    textView.setText(pointsText);
                    break;
                case R.id.north_button:
                    numberOfSelectedPoints++;
                    Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
                    pointsText += ", North";
                    textView.setText(pointsText);
                    break;
                case R.id.west_button:
                    numberOfSelectedPoints++;
                    Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
                    pointsText += ", West";
                    textView.setText(pointsText);
                    break;
                case R.id.south_button:
                    numberOfSelectedPoints++;
                    pointsText += ", South";
                    Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
                    textView.setText(pointsText);
                    break;

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul_1_main);

        textView = (TextView) findViewById(R.id.tag_text);
        eastButton = (Button) findViewById(R.id.east_button);
        westButton = (Button) findViewById(R.id.west_button);
        northButton = (Button) findViewById(R.id.north_button);
        southButton = (Button) findViewById(R.id.south_button);

        eastButton.setOnClickListener(buttonClickListener);
        northButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.NR_OF_POINTS)) {
                numberOfSelectedPoints = Integer.parseInt(savedInstanceState.getString(Constants.NR_OF_POINTS));
                Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
            }
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.NR_OF_POINTS, numberOfSelectedPoints.toString());
    }

}