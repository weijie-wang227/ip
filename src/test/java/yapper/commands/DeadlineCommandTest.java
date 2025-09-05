package yapper.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import yapper.Storage;
import yapper.tasks.Deadline;
import yapper.tasks.TaskList;



public class DeadlineCommandTest {

    /**
     * Test if deadline task is created correctly
     */
    @Test
    public void testDeadline() {
        TaskList tasks = new TaskList();
        TaskList tasks2 = new TaskList();
        Storage storage = new Storage("data/YapperBot.txt");
        Command deadline = new DeadlineCommand("return books", LocalDateTime.of(2024, 10, 2, 8, 30, 0));
        tasks2.add(new Deadline("return books", LocalDateTime.of(2024, 10, 2, 8, 30, 0)));
        deadline.execute(tasks, storage);
        System.out.println("TASKS");
        System.out.println(tasks);
        System.out.println(tasks2);
        assertEquals(tasks, tasks2);
    }
}
