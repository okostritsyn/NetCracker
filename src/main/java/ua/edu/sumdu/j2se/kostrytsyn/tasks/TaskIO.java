package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try {
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.writeInt(tasks.numOfElem);
            for(Task task:tasks) {
                // Writing data of task
                dataOut.writeInt(task.getTitle().length());
                dataOut.writeUTF(task.getTitle());
                dataOut.writeBoolean(task.isActive());
                dataOut.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    LocalDateTime start = task.getStartTime();
                    LocalDateTime end = task.getEndTime();
                    ZoneOffset zoneOffset = ZoneOffset.ofHours(0);
                    int startTimeNano = start.getNano();
                    long startTime = start.toEpochSecond(zoneOffset);
                    int endTimeNano = end.getNano();
                    long endTime = end.toEpochSecond(zoneOffset);
                    dataOut.writeLong(startTime);
                    dataOut.writeInt(startTimeNano);
                    dataOut.writeLong(endTime);
                    dataOut.writeInt(endTimeNano);
                }
                else {
                    LocalDateTime start = task.getTime();
                    ZoneOffset zoneOffset = ZoneOffset.ofHours(0);
                    int startTimeNano = start.getNano();
                    long startTime = start.toEpochSecond(zoneOffset);
                    dataOut.writeLong(startTime);
                    dataOut.writeInt(startTimeNano);
                }
            }
            // Closing binary file object
            dataOut.close();
            System.out.println("write successful");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
        try {
            DataInputStream dataIn = new DataInputStream(in);
            int numOfElem = dataIn.readInt();
            for (int i=0;i<numOfElem;i++){
                dataIn.readInt();
                Task newTask = new Task("",LocalDateTime.now());
                newTask.setTitle(dataIn.readUTF());
                newTask.setActive(dataIn.readBoolean());
                int repeatInterval = dataIn.readInt();
                if (repeatInterval == 0){
                    long startTime = dataIn.readLong();
                    int startTimeNano = dataIn.readInt();
                    newTask.setTime(LocalDateTime.ofEpochSecond(startTime,startTimeNano,ZoneOffset.ofHours(0)));
                }else{
                    long startTime = dataIn.readLong();
                    int startTimeNano = dataIn.readInt();
                    long endTime = dataIn.readLong();
                    int endTimeNano = dataIn.readInt();
                    newTask.setTime(LocalDateTime.ofEpochSecond(startTime,startTimeNano,ZoneOffset.ofHours(0)),LocalDateTime.ofEpochSecond(endTime,endTimeNano,ZoneOffset.ofHours(0)),repeatInterval);
                }
                tasks.add(newTask);
            }
                dataIn.close();
                System.out.println("read successful");
                System.out.println(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void writeBinary(AbstractTaskList tasks, File file){
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos))
        {
            write(tasks,bos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file){
        try (FileInputStream fos = new FileInputStream(file);
             BufferedInputStream bos = new BufferedInputStream(fos))
        {
            read(tasks,bos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out){
        Gson gson = new Gson();
        gson.toJson(tasks,out);
    }

    public static void read(AbstractTaskList tasks, Reader in){
        Gson gson = new Gson();
        gson.fromJson(in, AbstractTaskList.class);
    }

    public static void writeText(AbstractTaskList tasks, File file){
        writeText(tasks,file);
    }
    public static void readText(AbstractTaskList tasks, File file){
        readText(tasks,file);
    }
 }
