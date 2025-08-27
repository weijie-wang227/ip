package yapper.commands;

import org.junit.jupiter.api.Test;
import yapper.Storage;
import yapper.Ui;
import yapper.tasks.Deadline;
import yapper.tasks.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void Test1() {
        TaskList tasks = new TaskList();
        TaskList tasks2 = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/YapperBot.txt");
        Command deadline = new DeadlineCommand("return books", LocalDateTime.of(2024, 10, 2, 8, 30, 0));
        tasks2.add(new Deadline("return books", LocalDateTime.of(2024, 10, 2, 8, 30, 0)));
        deadline.execute(tasks, ui, storage);
        System.out.println("TASKS");
        System.out.println(tasks);
        System.out.println(tasks2);
        assertEquals(tasks, tasks2);
    }
}
