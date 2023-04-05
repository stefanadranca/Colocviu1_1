package ro.pub.cs.systems.eim.colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviul_1SecondaryActivity extends AppCompatActivity {

    private TextView commandsTextView;
    private Button registerButton, cancelButton;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.register_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul_1_secondary);
        commandsTextView = (TextView) findViewById(R.id.commands_text);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.SELECTED_POINTS)) {
            String selectedPoints = intent.getStringExtra(Constants.SELECTED_POINTS);
            commandsTextView.setText(selectedPoints);
        }
        registerButton = (Button) findViewById(R.id.register_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        registerButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}