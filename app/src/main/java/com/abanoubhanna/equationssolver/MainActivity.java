package com.abanoubhanna.equationssolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.abanoubhanna.equationssolver.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import Jama.Matrix;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.solveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 *Solving three variable linear equation system
                 * 3x + 2y -  z =  1 ---&gt; Eqn(1)
                 * 2x - 2y + 4z = -2 ---&gt; Eqn(2)
                 * -x + y/2-  z =  0 ---&gt; Eqn(3)
                 */

                //Creating  Arrays Representing Equations
                // double[][] lhsArray = {{3, 2, -1}, {2, -2, 4}, {-1, 0.5, -1}};
                // double[] rhsArray = {1, -2, 0};

                double a1 = Double.parseDouble(binding.a1.getText().toString());
                double a2 = Double.parseDouble(binding.a2.getText().toString());
                double a3 = Double.parseDouble(binding.a3.getText().toString());

                double b1 = Double.parseDouble(binding.b1.getText().toString());
                double b2 = Double.parseDouble(binding.b2.getText().toString());
                double b3 = Double.parseDouble(binding.b3.getText().toString());

                double c1 = Double.parseDouble(binding.c1.getText().toString());
                double c2 = Double.parseDouble(binding.c2.getText().toString());
                double c3 = Double.parseDouble(binding.c3.getText().toString());

                double d1 = Double.parseDouble(binding.d1.getText().toString());
                double d2 = Double.parseDouble(binding.d2.getText().toString());
                double d3 = Double.parseDouble(binding.d3.getText().toString());

                double[] data = {a1, b1, c1, a2, b2, c2, a3, b3, c3, d1, d2, d3};
                double[] results = equations_solver(data);

                //double[] results = solve(lhsArray, rhsArray);
                String txt = "X = " + results[0] + "\nY = " + results[1] +"\nZ = "+ results[2];
                binding.textv.setText(txt);
            }
        });

        //admob
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4971969455307153/2207008166");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    double[] equations_solver(double[] val){
//        double[][] lhsArray = {{3, 2, -1}, {2, -2, 4}, {-1, 0.5, -1}};
        double[][] lhsArray = {
                {val[0], val[1], val[2]},
                {val[3], val[4], val[5]},
                {val[6], val[7], val[8]}
        };
//        double[] rhsArray = {1, -2, 0};
        double[] rhsArray = {val[9], val[10], val[11]};
        return solve(lhsArray, rhsArray);
    }

    double[] solve(double[][] lhsArray, double[] rhsArray) {
        //Creating Matrix Objects with arrays
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 3);
        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
        //return Answers
        return new double[]{Math.round(ans.get(0, 0)),
                Math.round(ans.get(1, 0)),
                Math.round(ans.get(2, 0))};
    }
}
