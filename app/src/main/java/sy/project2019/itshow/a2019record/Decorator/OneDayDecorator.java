package sy.project2019.itshow.a2019record.Decorator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;

import sy.project2019.itshow.a2019record.R;

public class OneDayDecorator implements DayViewDecorator {

    private CalendarDay date;
    Context context;

    public OneDayDecorator(Context context) {
        date = CalendarDay.today();
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.addSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.mainColor)));
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
