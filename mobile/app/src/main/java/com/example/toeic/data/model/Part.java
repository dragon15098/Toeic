package com.example.toeic.data.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.example.toeic.R;
import com.example.toeic.config.ApplicationConfig;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Part {

    public enum PartName {
        PART_ONE(R.string.part_one),
        PART_TWO(R.string.part_two),
        PART_THREE(R.string.part_three),
        PART_FOUR(R.string.part_four),
        PART_FIVE(R.string.part_five),
        PART_SIX(R.string.part_six),
        PART_SEVEN(R.string.part_seven);
        private int value;

        public String getValue() {
            return ApplicationConfig.getInstance().getString(value);
        }

        PartName(@StringRes int value) {
            this.value = value;
        }
    }

    public enum PartIcon {
        PART_ONE(R.drawable.part_one),
        PART_TWO(R.drawable.part_two),
        PART_THREE(R.drawable.part_three),
        PART_FOUR(R.drawable.part_four),
        PART_FIVE(R.drawable.part_five),
        PART_SIX(R.drawable.part_six),
        PART_SEVEN(R.drawable.part_seven);

        private int value;

        public Drawable getValue() {
            return ResourcesCompat.getDrawable(ApplicationConfig.getInstance().getResources(), value, null);
        }

        PartIcon(@DrawableRes int value) {
            this.value = value;
        }
    }

    public enum PartIndex {
        PART_ONE(1),
        PART_TWO(2),
        PART_THREE(3),
        PART_FOUR(4),
        PART_FIVE(5),
        PART_SIX(6),
        PART_SEVEN(7);
        private int value;

        public int getValue() {
            return value;
        }

        PartIndex(int value) {
            this.value = value;
        }
    }

    public Integer partId;
    public String name;
    public Drawable drawable;
}
