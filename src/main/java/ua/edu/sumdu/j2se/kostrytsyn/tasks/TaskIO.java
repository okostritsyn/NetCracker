package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {
    static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public LocalDateTime read(JsonReader reader) throws IOException {
            reader.beginObject();
            int nanoSec = 0;
            long secOfEpoch = 0;
            String fieldName = null;
            while (reader.hasNext()) {
                JsonToken token = reader.peek();

                if (token.equals(JsonToken.NAME)) {
                    //get the current token
                    fieldName = reader.nextName();
                }

                if ("secOfEpoch".equals(fieldName)) {
                    secOfEpoch = reader.nextLong();
                }

                if("nanoSec".equals(fieldName)) {
                    nanoSec = reader.nextInt();
                }
            }
            reader.endObject();
            return LocalDateTime.ofEpochSecond(secOfEpoch,nanoSec,ZoneOffset.ofHours(0));
        }

        @Override
        public void write(JsonWriter writer, LocalDateTime time) throws IOException {
            writer.beginObject();
            writer.name("secOfEpoch");
            writer.value(time.toEpochSecond(ZoneOffset.ofHours(0)));
            writer.name("nanoSec");
            writer.value(time.getNano());
            writer.endObject();
        }
    }

    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream dataOut = new DataOutputStream(out)){
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
            System.out.println("write successful");
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
        try (DataInputStream dataIn = new DataInputStream(in)){
            int numOfElem = dataIn.readInt();
            for (int i=0;i<numOfElem;i++){
                dataIn.readInt();
                Task newTask = new Task("",LocalDateTime.now());
                newTask.setTitle(dataIn.readUTF());
                newTask.setActive(dataIn.readBoolean());
                int repeatInterval = dataIn.readInt();
                long startTime = dataIn.readLong();
                int startTimeNano = dataIn.readInt();
                if (repeatInterval == 0){
                    newTask.setTime(LocalDateTime.ofEpochSecond(startTime,startTimeNano,ZoneOffset.ofHours(0)));
                }else{
                    long endTime = dataIn.readLong();
                    int endTimeNano = dataIn.readInt();
                    newTask.setTime(LocalDateTime.ofEpochSecond(startTime,startTimeNano,ZoneOffset.ofHours(0)),LocalDateTime.ofEpochSecond(endTime,endTimeNano,ZoneOffset.ofHours(0)),repeatInterval);
                }
                tasks.add(newTask);
            }
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
        try{
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
            Gson gson = builder.create();
            out.write(gson.toJson(tasks.toArray()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in){
        try(BufferedReader buffReader = new BufferedReader(in)){
            StringBuilder strBuild = new StringBuilder();
            strBuild.append(buffReader.readLine());
            while ((buffReader.readLine()) != null) {
                strBuild.append(buffReader.readLine());
            }

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
            Gson gson = builder.create();

            Task[] taskArray = gson.fromJson(strBuild.toString(), Task[].class);

            for (Task task:taskArray) {
                tasks.add(task);
            }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try (Writer fOut = new BufferedWriter(new FileWriter(file))){
            write(tasks,fOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try (Reader fIn = new BufferedReader(new FileReader(file))){
            read(tasks,fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
