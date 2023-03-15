package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.staffinemployees.R;
public class MyViewPagerAdapter extends PagerAdapter {

    Context context;
    public MyViewPagerAdapter(Context context) {
        this.context = context;
    }

    // images and strings ka project me location
    int sliderAllImages[] = {R.drawable.img_onboarding1, R.drawable.img_onboarding2, R.drawable.img_onboarding3};
    int sliderAllTitles[] = {R.string.screen1, R.string.screen2, R.string.screen3};
    int sliderAllDesc[] = {R.string.screen1desc, R.string.screen2desc, R.string.screen3desc};

    // already override hua he jisme apne ko kitni entries he uski length de do
    @Override
    public int getCount() {
        return sliderAllTitles.length;
    }

    // iska dhyan rkhna pdega alg se override krna pdega
    // jo objectaya he usko view me daal k return kr do
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }


    // isme apn ko saare views bnane he inflated view ko use kr k and usme set krna he cheeze positions k hisaab se
    // aakhri me container.addView kr k view pass kr do
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView sliderTitle = (TextView) view.findViewById(R.id.sliderTitle);
        TextView sliderDesc = (TextView) view.findViewById(R.id.sliderDesc);

        sliderImage.setImageResource(sliderAllImages[position]);
        sliderTitle.setText(this.sliderAllTitles[position]);
        sliderDesc.setText(this.sliderAllDesc[position]);

        container.addView(view);

        return view;
    }

    // us container ie the viewpager me se hta do previous position se related details ko
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
