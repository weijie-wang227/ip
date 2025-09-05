package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import yapper.commands.EventCommand;
import yapper.commands.UnmarkCommand;

public class ParserTest {

    /**
     * Test if parser parses unmark input correctly
     */
    @Test
    public void testUnmark() {
        assertEquals(Parser.parse("unmark 2"), new UnmarkCommand(1));
    }

    /**
     * Test if parser parses event input correctly
     */
    @Test
    public void testEvent() {
        assertEquals(Parser.parse("event hackathon/from 12/12/2012 1800/to 12/14/2012 1800"),
                new EventCommand("hackathon",
                        LocalDateTime.of(2012, 12, 12, 18, 0, 0),
                        LocalDateTime.of(2012, 12, 14, 18, 0, 0)));
    }

    /**
     * Test if parser parses deadline input correctly
     */
    @Test
    public void testInvalidInput1() {
        assertThrows(InvalidInputException.class, () -> Parser.parse("deadline borrow books/by 13/1/2025 1800"));
    }

    /**
     * Test if parser correctly throws exception for wrong command
     */
    @Test
    public void testInvalidInput2() {
        assertThrows(InvalidInputException.class, () -> Parser.parse("unknown command"));
    }
}
