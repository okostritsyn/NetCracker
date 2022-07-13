package ua.edu.sumdu.j2se.kostrytsyn.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * Class create tasks which can be set on a time.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class Task implements Cloneable, Serializable {
    /** Some information about task. */
    private String title;
    /** Interval in seconds to repeat the task.*/
    private int interval;
    /** mark of activity. */
    private boolean active;
    /** if true the task can be repeated every <b>interval</b>. */
    private boolean repeated;
    /** Start time of the task in seconds from the beginning of an epoch.*/
    private LocalDateTime startTime;
    /** End time of the task in seconds from the beginning of an epoch.*/
    private LocalDateTime endTime;

    /**
     * Constructor - create new object without repeatable.
     * @param titleTask - Name of the task
     * @param time - Time of the task,LocalDateTime
     * @see Task#Task(String,LocalDateTime)
     */
    public Task(final String titleTask, final LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("Start time is not set!");
        }
        setTitle(titleTask);
        setTime(time);
        setActive(false);
    }

    /**
     * Constructor - create new object without repeatable.
     * @param titleTask - Name of the task
     * @param start - Start time of the task
     * @param end - End time of the task
     * @param intervalTime - Interval of repeating, seconds
     * @see Task#Task(String,LocalDateTime,LocalDateTime,int)
     */
    public Task(final String titleTask,
                final LocalDateTime start,
                final LocalDateTime end,
                final int intervalTime) throws IllegalArgumentException  {
        if (start == null) {
            throw new IllegalArgumentException("Start time is not set!");
        }
        else if (end == null) {
            throw new IllegalArgumentException("End time must is not set!");
        }
        else if (intervalTime < 0) {
            throw new IllegalArgumentException("Interval time must not be negative");
        }
        else if (intervalTime == 0 && start.isBefore(end)) {
            throw new IllegalArgumentException("Interval time must be bigger than 0!");
        }
        setTitle(titleTask);
        setTime(start, end, intervalTime);
        setActive(false);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return interval == task.interval && active == task.active && repeated == task.repeated && startTime.isEqual(task.startTime) && endTime.isEqual(task.endTime) && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, interval, active, repeated, startTime, endTime);
    }

    @Override
    public String toString() {
        return title;
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
     * @param time - LocalDateTime
     */
    public void setTime(final LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("Start time is not set!");
        }


        if (isRepeated()) {
            setRepeated(false);
        }
        setTime(time, time, 0);
    }


    /**
     * Setter for start time of the repeatable task.
     * @param start - LocalDateTime
     * @param end - LocalDateTime
     * @param intervalTime - in seconds from the beginning of a day
     */
    public void setTime(final LocalDateTime start,
                        final LocalDateTime end,
                        final int intervalTime) throws IllegalArgumentException {
        if (start == null) {
            throw new IllegalArgumentException("Start time is not set!");
        }
        else if (end == null) {
            throw new IllegalArgumentException("End time is not set!");
        }
        else if (intervalTime < 0) {
            throw new IllegalArgumentException("Interval time must not be negative");
        }
        else if (intervalTime == 0 && start.isBefore(end)) {
            throw new IllegalArgumentException("Interval time must be bigger than 0!");
        }

        if (start.isAfter(end)) {
            System.out.println("End time must be bigger or equal of start time."
                    + "Task will be not repeatable!");
            setTime(start, start, 0);
            return;
        } else {
            setStartTime(start);
            setEndTime(end);
            setInterval(intervalTime);
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
        return title;
    }

    public void setRepeated(final boolean isRepeated) {
        this.repeated = isRepeated;
    }

    public void setStartTime(final LocalDateTime time) {
        this.startTime = time;
    }

    public void setEndTime(final LocalDateTime time) {
        this.endTime = time;
    }

    public void setInterval(final int interval) {
        this.interval = interval;
    }

    /**
     * Get time of next start of the repeatable task.
     * @return time of the task in hours from the beginning of a day
     */
    public LocalDateTime getTime() {
        return startTime;
    }

    public String getStartTimeStr() {
        return startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getEndTimeStr() {
        return endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Get start time of the repeatable task.
     * @return return time of the task in hours from the beginning of a day
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get end time of the repeatable task.
     * @return return time of the task in hours from the beginning of a day
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Getter for interval of the repeatable task.
     * @return 0 if not repeated
     */
    public int getRepeatInterval() {
        return interval;
    }

    /**
     * Getter for activity.
     * @return true if active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Getter for repeatable of task.
     * @return true if repeated
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Calculate next time start for the task.
     * @param current current time
     * @return null if error, LocalDateTime if ok
     */
    public LocalDateTime nextTimeAfter(final LocalDateTime current) {
         if (!isActive()) {
             return null;
        }
        LocalDateTime endTimeDate = endTime;

        if (current.isAfter(endTimeDate) || current.isEqual(endTimeDate)){
            return null; //task already complete
        }

        LocalDateTime i = startTime;
        do {
            if (current.isBefore(i)) {
                break;
            }
            i = i.plusSeconds(interval);
        }
        while (i.isBefore(endTimeDate)||i.isEqual(endTimeDate));

        if (i.isAfter(endTimeDate)) {
            return null; //if i + interval = endTime than return error
        } else {
            return i;
        }
    }
}

