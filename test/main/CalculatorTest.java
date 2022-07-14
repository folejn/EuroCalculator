package main;

import main.exceptions.ExitException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void should_not_throw_exception_when_invalid_filePath_given_asArgument_and_valid_input_provided() throws FileNotFoundException {
        //given
        String[] args = {"some/invalid/path/file"};

        //when
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("test/test-resources/valid_input.txt"));
        System.setIn(fips);
        Calculator.main(args);
        System.setIn(original);

        //then
        assert true;
    }

    @Test
    public void should_not_throw_exception_when_valid_filePath_given_asArgument_and_valid_input_provided() throws FileNotFoundException {
        //given
        String[] args = {"currencies.xml"};

        //when
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("test/test-resources/valid_input.txt"));
        System.setIn(fips);
        Calculator.main(args);
        System.setIn(original);

        //then
        assert true;
    }

    @Test
    public void should_throw_ExitException_when_invalid_filePath_given_asArgument_and_invalid_currency_provided() throws FileNotFoundException {
        //given
        String[] args = {"some/invalid/path/file"};

        //when, then
        final FileInputStream fips = new FileInputStream(new File("test/test-resources/invalid_currency.txt"));
        System.setIn(fips);
        assertThrows(ExitException.class, () -> Calculator.main(args));
    }

    @Test
    public void should_throw_ExitException_when_invalid_filePath_given_asArgument_and_invalid_format_amount_provided() throws FileNotFoundException {
        //given
        String[] args = {"some/invalid/path/file"};

        //when, then
        final FileInputStream fips = new FileInputStream(new File("test/test-resources/invalid_amount.txt"));
        System.setIn(fips);
        assertThrows(ExitException.class, () -> Calculator.main(args));
    }

    @Test
    public void should_throw_ExitException_when_invalid_filePath_given_asArgument_and_negative_amount_provided() throws FileNotFoundException {
        //given
        String[] args = {"some/invalid/path/file"};

        //when, then
        final FileInputStream fips = new FileInputStream(new File("test/test-resources/negative_amount.txt"));
        System.setIn(fips);
        assertThrows(ExitException.class, () -> Calculator.main(args));
    }
}