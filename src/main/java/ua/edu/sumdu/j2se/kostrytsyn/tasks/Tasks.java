package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {
    public SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        return null;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start,
                                          LocalDateTime end) {

        System.out.println("tasks = " + tasks + ", start = " + start + ", end = " + end);
        Stream<Task> currStream = StreamSupport.stream(tasks.spliterator(), false);
        List<Task> currList = currStream.filter((t) -> (t.nextTimeAfter(start) != null &&
                t.nextTimeAfter(start).isAfter(start))
                || (t.nextTimeAfter(start) != null
                    && t.nextTimeAfter(start).isEqual(start)
                    && t.nextTimeAfter(start).isBefore(end)) ||
                (t.nextTimeAfter(start) != null
                        && t.nextTimeAfter(start).isEqual(end))).collect(Collectors.toList());
        return currList;
     }

}
