package com.hnkntoc.navigationdrawerv2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Nikita on 12.08.2016.
 */
public class CardViewFactory {
    private Activity activity;
    private CardView cardView;
    private RelativeLayout relativeLayout;
    private ImageView cardImage;
    private TextView lessonName;
    private TextView time1;
    private TextView time2;
    private ImageView lessonNumberImage;
    private TextView lessonDescription;

    public CardViewFactory(Activity activity) {
        this.activity = activity;
    }

    public CardView getCardView(){
        cardView = new CardView(activity);
        relativeLayout = new RelativeLayout(activity);
        cardImage = new ImageView(activity);
        lessonName = new TextView(activity);

        settingCardView(cardView);
        settingRelativeLayout(relativeLayout);

        relativeLayout.addView(settingImageView(cardImage));
        relativeLayout.addView(settingTextView("Матан", lessonName ));
        cardView.addView(relativeLayout);
        return cardView;
    }

    private CardView settingCardView(CardView cardView){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,10);
        cardView.setLayoutParams(params);
        return cardView;
    }

    private RelativeLayout settingRelativeLayout(RelativeLayout relativeLayout){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(params);
        relativeLayout.setPadding(5,5,5,5);
        return relativeLayout;
    }

    private ImageView settingImageView(ImageView imageView){
        RelativeLayout.LayoutParams params = createLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        params.setMargins(5,5,5,5);
        imageView.setLayoutParams(params);
        imageView.setMaxHeight(60);
        imageView.setMaxWidth(70);
        imageView.setMinimumHeight(70);
        imageView.setMinimumWidth(80);
        imageView.setImageResource(R.drawable.geometry);
        return imageView;
    }

    public TextView settingTextView(String text, TextView textView){
        RelativeLayout.LayoutParams layoutParams = createLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_TOP,cardImage.getId());
        layoutParams.addRule(RelativeLayout.LEFT_OF,cardImage.getId());
        layoutParams.addRule(RelativeLayout.RIGHT_OF,cardImage.getId());
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(20);
        textView.setText(text);
        return textView;
    }

    @NonNull
    private RelativeLayout.LayoutParams createLayoutParams() {
        return new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

}
