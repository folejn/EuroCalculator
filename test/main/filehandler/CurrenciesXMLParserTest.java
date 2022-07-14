package main.filehandler;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CurrenciesXMLParserTest {
    private CurrenciesXMLParser parser;
    @Before
    public void setUp() {
        parser = new CurrenciesXMLParser();
    }

    @Test
    public void should_complete_map_with_expected_length_given_validFile() throws IOException, SAXException, ParserConfigurationException {
        //given
        String validFilename = "currencies.xml";

        //when
        Map<String, Double> result = parser.parse(validFilename);
        int expectedSize = 31;

        //then
        assertEquals(expectedSize, result.size());
    }

    @Test
    public void should_throw_IOException_given_invalid_fileName() {
        //given
        String invalidFilename = "filename";

        //when, then
        assertThrows(IOException.class, () ->  parser.parse(invalidFilename));
    }

    @Test
    public void should_complete_map_as_expected_given_validFile() throws IOException, SAXException, ParserConfigurationException {
        //given
        String validFilename = "test/test-resources/validFile.xml";
        Map<String, Double> expected = new HashMap<>();

        //when
        Map<String, Double> result = parser.parse(validFilename);
        expected.put("USD", 1.0067);
        expected.put("JPY", 138.02);
        expected.put("BGN", 1.9558);
        expected.put("CZK", 24.397);

        //then
        assertEquals(expected, result);
    }

    @Test
    public void should_throw_SAXParseException_given_file_with_invalid_content() {
        //given
        String invalidFilename = "test/test-resources/invalidFile.xml";

        //when, then
        assertThrows(SAXParseException.class, () ->  parser.parse(invalidFilename));
    }

    @Test
    public void should_return_expected_given_file_with_missing_attributes_in_some_tags() throws IOException, SAXException, ParserConfigurationException {
        //given
        String missing = "test/test-resources/missingTags.xml";
        Map<String, Double> expected = new HashMap<>();

        //when
        Map<String, Double> result = parser.parse(missing);
        expected.put("USD", 1.0067);
        expected.put("JPY", 138.02);
        expected.put("BGN", 1.9558);

        //then
        assertEquals(expected, result);
    }

}