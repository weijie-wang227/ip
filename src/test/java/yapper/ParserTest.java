package yapper;

import org.junit.jupiter.api.Test;
import yapper.commands.EventCommand;
import yapper.commands.UnmarkCommand;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    /**
     * Test if parser parses unmark input correctly
     */
    @Test
    public void Test1() {
        assertEquals(Parser.parse("unmark 2"), new UnmarkCommand(1));
    }

    /**
     * Test if parser parses event input correctly
     */
    @Test
    public void Test2() {
        assertEquals(Parser.parse("event hackathon/from 12/12/2012 1800/to 12/14/2012 1800"),
                new EventCommand("hackathon",
                        LocalDateTime.of(2012, 12, 12, 18, 0, 0),
                        LocalDateTime.of(2012, 12, 14, 18, 0, 0)));
    }

    /**
     * Test if parser parses deadline input correctly
     */
    @Test
    public void Test3() {
        assertThrows(InvalidInputException.class,
                () -> Parser.parse("deadline borrow books/by 13/1/2025 1800"));
    }

    /**
     * Test if parser correctly throws exception for wrong command
     */
    @Test
    public void Test4() {
        assertThrows(InvalidInputException.class,
                () -> Parser.parse("unknown command"));
    }
}
