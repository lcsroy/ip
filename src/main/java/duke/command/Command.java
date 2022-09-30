package duke.command;

import duke.exceptions.*;
import duke.tasklist.Tasklist;
import duke.ui.Ui;
import duke.data.Storage;
import duke.parser.Parser;

import java.lang.NumberFormatException;

public class Command {
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private String command;
    private String taskDescription;
    private boolean isExit = false;

    public Command(String command, String taskDescription) {
        this.command = command;
        this.taskDescription = taskDescription;
    }

    public void execute(Tasklist tasks, Ui ui, Storage storage ) throws duke.exceptions.NumberFormatException, MissingDateException {
        try {
            if (command.equalsIgnoreCase(COMMAND_BYE)) {
                isExit = true;
            } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
                Ui.printList(tasks);
            } else if (command.equalsIgnoreCase(COMMAND_MARK)) {
                int task_no = Parser.parseMark(taskDescription);
                Tasklist.markTask(task_no);
            } else if (command.equalsIgnoreCase(COMMAND_UNMARK)) {
                int task_no = Parser.parseUnmark(taskDescription);
                Tasklist.UnmarkTask(task_no);
            } else if (command.equalsIgnoreCase(COMMAND_TODO)) {
                Tasklist.createTodo(taskDescription);
            } else if (command.equalsIgnoreCase(COMMAND_DEADLINE)) {
                String description = Parser.parseDeadline(taskDescription);
                String by = Parser.parseDeadlineDate(taskDescription);
                Tasklist.createDeadline(description, by);
            } else if (command.equalsIgnoreCase(COMMAND_EVENT)) {
                String description = Parser.parseEvent(taskDescription);
                String at = Parser.parseEventDate(taskDescription);
                Tasklist.createEvent(description, at);
            } else if (command.equalsIgnoreCase(COMMAND_DELETE)) {
                int task_no = Parser.parseDelete(taskDescription);
                Tasklist.deleteTask(task_no);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                Tasklist.findTasks(taskDescription);
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            Ui.showError(e.getMessage());
        } catch (MissingArgumentException e) {
            Ui.showError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDateException();
        } catch (InvalidArgumentException e) {
            Ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            throw new duke.exceptions.NumberFormatException();
        } catch (InvalidTaskNoException e) {
            Ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }


}
