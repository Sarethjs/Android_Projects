package stellarsyntax.thunder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


// Math extern libraries
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {

    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initiate attributes
        tvRes = findViewById(R.id.tvResult);

        // Adding task or functionalities for buttons
        this.addTaskForButtons();
    }

    private void addTaskForButtons(){
        //Nine button
        findViewById(R.id.btnNine).setOnClickListener(view -> this.addInfo("9"));

        // Eight button
        findViewById(R.id.btnEight).setOnClickListener(view -> this.addInfo("8"));

        // Seven button
        findViewById(R.id.btnSeven).setOnClickListener(view -> this.addInfo("7"));

        // Six button
        findViewById(R.id.btnSix).setOnClickListener(view -> this.addInfo("6"));

        // Five button
        findViewById(R.id.btnFive).setOnClickListener(view -> this.addInfo("5"));

        // Four button
        findViewById(R.id.btnFour).setOnClickListener(view -> this.addInfo("4"));

        // Three button
        findViewById(R.id.btnThree).setOnClickListener(view -> this.addInfo("3"));

        // Two button
        findViewById(R.id.btnTwo).setOnClickListener(view -> this.addInfo("2"));

        // One button
        findViewById(R.id.btnOne).setOnClickListener(view -> this.addInfo("1"));

        // Zero button
        findViewById(R.id.btnZero).setOnClickListener(view -> this.addInfo("0"));

        // Restore button
        findViewById(R.id.btnAc).setOnClickListener(view -> this.tvRes.setText("0"));

        // Plus button
        findViewById(R.id.btnAdd).setOnClickListener(view -> this.addInfo("+"));

        // Subtraction button
        findViewById(R.id.btnDif).setOnClickListener(view -> this.addInfo("-"));

        // Dot button
        findViewById(R.id.btnDot).setOnClickListener(view -> this.addInfo("."));

        // Slash button
        findViewById(R.id.btnSlash).setOnClickListener(view -> this.addInfo("/"));

        // Multiply button
        findViewById(R.id.btnMul).setOnClickListener(view -> this.addInfo("*"));

        // Delete button
        findViewById(R.id.btnDel).setOnClickListener(view -> {
            String tvResText = (String) this.tvRes.getText();
            tvResText = tvResText.substring(0, tvResText.length() - 1);
            this.tvRes.setText(tvResText);
        });


        // Equals button
        findViewById(R.id.btnEquals).setOnClickListener(view -> {

            String expression = (String) this.tvRes.getText();

            // Resolve the operation and displays result
            try {
                Expression exp = new ExpressionBuilder(expression).build();
                this.tvRes.setText(String.valueOf(exp.evaluate()));
            } catch (Exception e) {
                String errorMessage = "ERROR";
                this.tvRes.setText(errorMessage);
            }

        });

    }

    private void addInfo(String inf){

        String resValue = (String) this.tvRes.getText();

        if (resValue.equals("0")) resValue = "";

        resValue =resValue + inf;
        this.tvRes.setText(resValue);
    }
}