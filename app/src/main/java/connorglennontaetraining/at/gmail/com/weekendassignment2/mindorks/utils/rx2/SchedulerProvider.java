package connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2;

import io.reactivex.Scheduler;

/**
 * Created by hrskrs on 5/8/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
