package sy.project2019.itshow.a2019record.Decorator;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import sy.project2019.itshow.a2019record.R;


public class RecordDayDecorator implements DayViewDecorator {

    private int color;
    private HashSet<CalendarDay> dates;

    public RecordDayDecorator(Collection<CalendarDay> dates, Activity context) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, R.color.main_dark_gray)); // 날자밑에 점
    }
}
