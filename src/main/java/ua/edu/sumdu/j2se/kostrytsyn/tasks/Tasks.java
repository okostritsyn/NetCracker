package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {
    public SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        Stream<Task> currStream = StreamSupport.stream(tasks.spliterator(), false);

        return null;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start,
                                          LocalDateTime end) {
        Stream<Task> currStream = StreamSupport.stream(tasks.spliterator(), false);
        return currStream.filter((t) -> ((t.nextTimeAfter(start) != null && t.nextTimeAfter(start).isAfter(start)
                    || t.nextTimeAfter(start) != null && t.nextTimeAfter(start).isEqual(start))
                    && (t.nextTimeAfter(start) != null && t.nextTimeAfter(start).isBefore(end))
                        || t.nextTimeAfter(start) != null && t.nextTimeAfter(start).isEqual(end))).collect(Collectors.toList());
     }
}
