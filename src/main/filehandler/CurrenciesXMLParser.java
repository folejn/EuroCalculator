package main.filehandler;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrenciesXMLParser {

    public CurrenciesXMLParser() {
    }

    public Map<String, Double> parse(String filePath) throws ParserConfigurationException, IOException, SAXException {
        Map<String, Double> currencies = new HashMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(filePath));
        document.getDocumentElement().normalize();
        NodeList list = document.getElementsByTagName("Cube");
        for (int i=0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && !node.hasChildNodes()) {
                Element element = (Element) node;
                try {
                    String currency = element.getAttribute("currency");
                    Double rate = Double.parseDouble(element.getAttribute("rate"));
                    currencies.put(currency, rate);
                } catch(Exception e){}
            }
        }
        return currencies;
    }
}
