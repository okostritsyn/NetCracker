package ua.edu.sumdu.j2se.kostrytsyn.tasks.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> mapTasks = new TreeMap<>();
        Iterable<Task> iterTasks = Tasks.incoming(tasks,start,end);

        for (Task currTask:
                iterTasks) {
            LocalDateTime currTime = start;
            LocalDateTime nextTime = currTask.nextTimeAfter(currTime);
            while (nextTime != null ){
                if (mapTasks.containsKey(nextTime)) {
                    mapTasks.get(nextTime).add(currTask);
                } else {
                    HashSet<Task> setOfTasks = new HashSet<>();
                    setOfTasks.add(currTask);
                    mapTasks.put(nextTime,setOfTasks);
                }
                currTime = nextTime;
                nextTime = currTask.nextTimeAfter(currTime);
                if (nextTime != null && nextTime.isAfter(end)) break;
            }
         }

        return mapTasks;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start,
                                          LocalDateTime end) {
        Stream<Task> currStream = StreamSupport.stream(tasks.spliterator(), false);
        currStream = currStream.filter((t) -> (t.nextTimeAfter(start) != null));
        return currStream.filter((t) -> {
            LocalDateTime nextTime = t.nextTimeAfter(start);
            return nextTime != null && (nextTime.isAfter(start) || nextTime.isEqual(start))
                    && (nextTime.isBefore(end) || nextTime.isEqual(end));}).collect(Collectors.toList());
    }
}
