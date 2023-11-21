package org.example.parsers;

import org.example.generated.planes.Plane;
import org.example.generated.planes.CharsType;
import org.example.generated.planes.ParametersType;
import org.example.generated.planes.PlaneType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlaneSaxParser {
    private final List<Plane> planes = new ArrayList<>();

    public List<Plane> parsePlanes(String filePath) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                private Plane currentPlane;
                private CharsType currentChars;
                private ParametersType currentParameters;
                private CharsType.Ammunition currentAmmunition;
                private String currentValue;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    switch (qName.toLowerCase()) {
                        case "plane":
                            currentPlane = new Plane();
                            currentPlane.setId(attributes.getValue("id"));
                            break;
                        case "chars":
                            currentChars = new CharsType();
                            break;
                        case "parameters":
                            currentParameters = new ParametersType();
                            break;
                        case "ammunition":
                            currentAmmunition = new CharsType.Ammunition();
                            break;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    currentValue = new String(ch, start, length).trim();
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    switch (qName.toLowerCase()) {
                        case "model":
                            currentPlane.setModel(currentValue);
                            break;
                        case "origin":
                            currentPlane.setOrigin(currentValue);
                            break;
                        case "type":
                            currentChars.setType(PlaneType.valueOf(currentValue.toUpperCase()));
                            break;
                        case "seats":
                            currentChars.setSeats(Integer.parseInt(currentValue));
                            break;
                        case "missiles":
                            currentAmmunition.setMissiles(Integer.parseInt(currentValue));
                            break;
                        case "radar":
                            currentAmmunition.setRadar(Boolean.parseBoolean(currentValue));
                            break;
                        case "length":
                            currentParameters.setLength(new BigDecimal(currentValue));
                            break;
                        case "width":
                            currentParameters.setWidth(new BigDecimal(currentValue));
                            break;
                        case "height":
                            currentParameters.setHeight(new BigDecimal(currentValue));
                            break;
                        case "price":
                            currentPlane.setPrice(new BigDecimal(currentValue));
                            break;
                        case "ammunition":
                            currentChars.setAmmunition(currentAmmunition);
                            break;
                        case "chars":
                            currentPlane.setChars(currentChars);
                            break;
                        case "parameters":
                            currentPlane.setParameters(currentParameters);
                            break;
                        case "plane":
                            planes.add(currentPlane);
                            break;
                    }
                }

                @Override
                public void endDocument() {

                }
            };

            File inputFile = new File(filePath);
            saxParser.parse(inputFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return planes;
    }
}