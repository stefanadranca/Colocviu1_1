package ro.pub.cs.systems.eim.colocviu1_1;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviul_1MainActivity extends Activity {
    private TextView textView;
    private Button northButton, eastButton, westButton, southButton, navigateToSecondaryActivity;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private Integer numberOfSelectedPoints = 0;

    private Boolean serviceStatus = Constants.SERVICE_STOPPED;
    private IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

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
                case R.id.navigate_to_secondary_activity:
                    Intent intent = new Intent(getApplicationContext(),Colocviul_1SecondaryActivity.class);
                    intent.putExtra(Constants.SELECTED_POINTS, pointsText);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    numberOfSelectedPoints = 0;
                    break;

            }

            if (numberOfSelectedPoints >= 4 && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), Colocviul_1Service.class);
                intent.putExtra(Constants.COMMANDS, pointsText);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
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
        navigateToSecondaryActivity = (Button) findViewById(R.id.navigate_to_secondary_activity);

        eastButton.setOnClickListener(buttonClickListener);
        northButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        navigateToSecondaryActivity.setOnClickListener(buttonClickListener);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.NR_OF_POINTS)) {
                numberOfSelectedPoints = Integer.parseInt(savedInstanceState.getString(Constants.NR_OF_POINTS));
                Log.d(Constants.LOG_NR_POINTS, numberOfSelectedPoints.toString());
            }
        }
        intentFilter.addAction("BROADCAST");
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.NR_OF_POINTS, numberOfSelectedPoints.toString());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, "The activity returned from REGISTER", Toast.LENGTH_LONG).show();
            else Toast.makeText(this, "The activity returned from CANCEL", Toast.LENGTH_LONG).show();
        }
        String pointsText = "";
        textView.setText(pointsText);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


}