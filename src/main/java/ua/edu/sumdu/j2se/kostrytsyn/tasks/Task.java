
package ua.edu.sumdu.j2se.kostrytsyn.tasks;

/**
 * Class create tasks which can be set on a time.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class Task {
    /** Some information about task. */
    private String title;
    /** Interval in hours to repeat the task.*/
    private int interval;
    /** mark of activity. */
    private boolean active;
    /** if true the task can be repeated every <b>interval</b>. */
    private boolean repeated;
    /** Start time of the task in hours from the beginning of a day.
     * As example  22 means 22.00
     */
    private int startTime;
    /** End time of the task in hours from the beginning of a day.
     * As example  22 means 22.00
     */
    private int endTime;

    /**
     * Constructor - create new object without repeatable.
     * @param titleTask - Name of the task
     * @param time - Time of the task, hours from the beginning of a day
     * @see Task#Task(String,int)
     */
    public Task(final String titleTask, final int time) {
        setTitle(titleTask);
        setTime(time);
        setActive(false);
    }

    /**
     * Constructor - create new object without repeatable.
     * @param titleTask - Name of the task
     * @param start - Start time of the task, hours from the beginning of a day
     * @param end - End time of the task, hours from the beginning of a day
     * @param intervalTime - Interval of repeating, hours
     * @see Task#Task(String,int,int,int)
     */
      public Task(final String titleTask,
                  final int start,
                  final int end,
                  final int intervalTime) {
        setTitle(titleTask);
        setTime(start, end, intervalTime);
        setActive(false);
    }

    /**
     * Setter for title {@link Task#title}.
     * @param titleTask - Name of the task
     */
    public void setTitle(final String titleTask) {
        this.title = titleTask;
    }

    /**
     * Setter for activation|deactivation task.
     * @param markActive mark of activity of the task
     */
    public void setActive(final boolean markActive) {
        this.active = markActive;
    }

    /**
     * Setter for start time of the non repeatable task.
     * If task is repeatable it becomes non repeatable
     * @param time - in hours from the beginning of a day
     */
    public void setTime(final int time) {
        if (isRepeated()) {
            setRepeated(false);
        }
        setTime(time, time, 0);
    }


    /**
     * Setter for start time of the repeatable task.
     * @param start - in hours from the beginning of a day
     * @param end - in hours from the beginning of a day
     * @param intervalTime - in hours from the beginning of a day
     */
    public void setTime(final int start,
                        final int end,
                        final int intervalTime) {
        if (start > end) {
            System.out.println("End time must be bigger or equal of start time."
                    + "Task will be not repeatable!");
            setTime(start, start, 0);
            return;
        } else {
            this.startTime = start;
            this.endTime = end;
            this.interval = intervalTime;
        }
        if (intervalTime != 0) {
            setRepeated(true);
        }
    }

    /**
     * Getter for name of the task.
     * @return name of the task
     */
    public String getTitle() {
        return this.title;
    }

    private void setRepeated(final boolean isRepeated) {
        this.repeated = isRepeated;
    }

    /**
     * Get time of next start of the repeatable task.
     * @return time of the task in hours from the beginning of a day
     */
    public int getTime() {
        return this.startTime;
    }

    /**
     * Get start time of the repeatable task.
     * @return return time of the task in hours from the beginning of a day
     */
    public int getStartTime() {
        return this.startTime;
    }

    /**
     * Get end time of the repeatable task.
     * @return return time of the task in hours from the beginning of a day
     */
    public int getEndTime() {
        return this.endTime;
    }

    /**
     * Getter for interval of the repeatable task.
     * @return 0 if not repeated
     */
    public int getRepeatInterval() {
        return this.interval;
    }

    /**
     * Getter for activity.
     * @return true if active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Getter for repeatable of task.
     * @return true if repeated
     */
    public boolean isRepeated() {
        return this.repeated;
    }

    /**
     * Calculate next time start for the task.
     * @param current current time in hours
     * @return -1 if error, hours if ok
     */
    public int nextTimeAfter(final int current) {
        if (!isActive()) {
            return -1;
        }
        if (current >= this.endTime) {
            return -1; //task already complete
        }

        int i = this.startTime;

        do {
            if (current <= i) {
                break;
            }
            i = i + this.interval;
        }
        while (i <= this.endTime);

        if (i > this.endTime) {
            return -1; //if i + interval = endTime than return error
        } else {
            return i;
        }
    }
}