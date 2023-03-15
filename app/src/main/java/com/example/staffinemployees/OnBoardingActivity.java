package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.staffinemployees.Adapters.MyViewPagerAdapter;
import com.example.staffinemployees.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnBoardingBinding binding;

    MyViewPagerAdapter adapter;

    TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter=new MyViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        setDotIndicator(0);
        binding.viewPager.addOnPageChangeListener(viewPagerListener);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)<2)
                {
                    binding.viewPager.setCurrentItem(getItem(1),true);
                }
                else
                {
                    Intent intent=new Intent(OnBoardingActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OnBoardingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }   // onCreate ends here

    public void setDotIndicator(int position)
    {
        dots=new TextView[3];
        binding.dotIndicatorLinearLayout.removeAllViews();

        for(int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setText(Html.fromHtml("&bull", Html.FROM_HTML_MODE_LEGACY));
                dots[i].setTextSize(35);
                dots[i].setTextColor(Color.parseColor("#808080"));
                binding.dotIndicatorLinearLayout.addView(dots[i]);
            }
        }
        dots[position].setTextSize(38);
        dots[position].setTextColor(Color.parseColor("#4766F9"));
    }

    private int getItem(int i)
    {
        return binding.viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position)
        {

            setDotIndicator(position);
            if(position==2)
            {
                binding.skipBtn.setVisibility(View.GONE);
                binding.nextBtn.setText("Finish");
            }
            else
            {
                binding.skipBtn.setVisibility(View.VISIBLE);

                binding.nextBtn.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {}

    };
}