package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void setisDone(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String SaveAsString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
    //...
}