package com.codingsimply.app.canvascourseapplicator.core.services;

import javafx.concurrent.Task;

/**
 * Generic task for service threads.
 *
 * @author Adam Saladino
 */
public class ServiceTask extends Task<Void> {

    public interface Todo {

        void run();
    };

    private int total = 1;
    private final Todo todo;

    public ServiceTask(Todo todo) {
        this.todo = todo;
    }
    
    public ServiceTask setTitle(String title) {
        updateTitle(title);
        return this;
    }
    
    public void done(String message) {
        this.total = 1;
        updateMessage(message);
        updateProgress(1, total);
    }

    public void set(String message, int progress, int total) {
        this.total = total;
        updateMessage(message);
        updateProgress(progress, total);
    }

    public void set(String message, int progress) {
        updateMessage(message);
        updateProgress(progress, total);
    }

    public void set(String message) {
        updateMessage(message);
        updateProgress(total, total);
    }

    @Override
    public Void call() {
        todo.run();
        done();
        return null;
    }
}
