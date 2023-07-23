package com.moody.jobsystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobSave {

    public static String WOODCHOPER = "woodchoper";
    public static String DIGGER = "digger";
    public static String MINER = "miner";
    public static String FARMER = "farmer";

    private String ownerUUID;
    private String job;

    public JobSave(Player owner, String job) {
        this.ownerUUID = owner.getUniqueId().toString();
        this.job = job;
    }
    public void setJob(String job) {
        List<JobSave> saves;
        saves = JobSave.ladeJobs();
        for (JobSave richtigerJob : saves) {
            if (richtigerJob.getOwnerUUID().equals(this.ownerUUID)) {
                richtigerJob.job = job;
                JobSave.speichereJob(saves);
                return;
            }
        }
        JobSave.hinzufuegenJob(this);
    }
    public String getOwnerUUID() {
        return this.ownerUUID;
    }
    public OfflinePlayer getOwnerAsOfflinePlayer() {
        return Bukkit.getOfflinePlayer(UUID.fromString(this.ownerUUID));
    }

    public String getJob() {
        return this.job;
    }

    public static void hinzufuegenJob(JobSave jobSave) {
        // Lade vorhandene Daten aus der JSON-Datei
        List<JobSave> vorhandeneDaten = ladeJobs();

        // Füge den neuen Datensatz hinzu
        vorhandeneDaten.add(jobSave);

        // Speichere die aktualisierten Daten zurück in die JSON-Datei
        speichereJob(vorhandeneDaten);
    }

    public static void speichereJob(List<JobSave> daten) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(daten);

        try (FileWriter writer = new FileWriter("plugins/JobSystem/jobs.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<JobSave> ladeJobs() {

        File file = new File("plugins/JobSystem/jobs.json");

        if (!file.exists()) {
            File directory = new File("plugins/JobSystem");
            if (!directory.exists()) {
                directory.mkdir();
            }
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter("plugins/JobSystem/jobs.json");
                fileWriter.write("[]");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        List<JobSave> daten = new ArrayList<>();

        try (FileReader reader = new FileReader("plugins/JobSystem/jobs.json")) {
            Type datenTyp = new TypeToken<List<JobSave>>() {
            }.getType();
            daten = gson.fromJson(reader, datenTyp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return daten;
    }

    public static JobSave findByOwnerUUID(String ownerUUID) {
        List<JobSave> jobSaves = JobSave.ladeJobs();
        if (jobSaves == null) {
            jobSaves = new ArrayList<>();
        }

        for (JobSave save : jobSaves) {
            if (save != null) {
                if (save.getOwnerUUID().equals(ownerUUID)) {
                    return save;
                }
            }
        }
        return null;
    }

}
