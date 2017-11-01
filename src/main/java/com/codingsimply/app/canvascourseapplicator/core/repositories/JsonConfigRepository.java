package com.codingsimply.app.canvascourseapplicator.core.repositories;

import com.google.gson.Gson;
import com.codingsimply.app.canvascourseapplicator.data.models.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class JsonConfigRepository {

    /**
     * Location of the config file.
     */
    private final String file;

    /**
     * Initialize the repo with the config location.
     *
     * @param String file
     */
    public JsonConfigRepository(String file) {
        this.file = file;
    }

    /**
     * Read the configuration.
     *
     * @return Config
     * @throws Exception if the file is not file.
     */
    public Config read() throws FileNotFoundException {
        Gson gson = new Gson();
        String json;
        try (Scanner scanner = new Scanner(new File(file))) {
            json = scanner.useDelimiter("\\A").next();
        }
        return gson.fromJson(json, Config.class);
    }

    /**
     * Save the config.
     * @param config
     * @throws FileNotFoundException 
     */
    public void write(Config config) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            Gson gson = new Gson();
            out.println(gson.toJson(config, Config.class));
        }
    }

}
