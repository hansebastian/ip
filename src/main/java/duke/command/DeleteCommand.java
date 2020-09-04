package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command when user deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String userInput;

    /**
     * Constructor to create a delete command.
     * @param userInput command give from user via command line.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Delete a task from Duke's TaskList and update Duke's storage.
     *
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     * @throws DukeException when the task to be deleted does not exist in Duke's TaskList.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            String taskIndex = userInput.substring(7);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task deletedTask = taskList.delete(index);

            storage.deleteTask(index);

            return "Noted. I've removed this task:\n" + deletedTask.toString() + taskList.printNumTasks();

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me delete.");
        }
    }
}
