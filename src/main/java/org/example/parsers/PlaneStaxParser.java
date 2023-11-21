package org.example.parsers;

import org.example.generated.planes.Plane;
import org.example.generated.planes.CharsType;
import org.example.generated.planes.ParametersType;
import org.example.generated.planes.PlaneType;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlaneStaxParser {

    public List<Plane> parsePlanes(String filePath) {
        List<Plane> planes = new ArrayList<>();
        Plane currentPlane = null;
        CharsType currentChars = null;
        ParametersType currentParameters = null;
        CharsType.Ammunition currentAmmunition = null;
        boolean inChars = false;
        boolean inParameters = false;
        boolean inAmmunition = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();

                    switch (localPart) {
                        case "plane":
                            currentPlane = new Plane();
                            String idValue = event.asStartElement().getAttributeByName(new QName("id")).getValue();
                            currentPlane.setId(idValue);
                            break;
                        case "model":
                            event = eventReader.nextEvent();
                            if (currentPlane != null) {
                                currentPlane.setModel(event.asCharacters().getData());
                            }
                            break;
                        case "origin":
                            event = eventReader.nextEvent();
                            if (currentPlane != null) {
                                currentPlane.setOrigin(event.asCharacters().getData());
                            }
                            break;
                        case "chars":
                            inChars = true;
                            currentChars = new CharsType();
                            break;
                        case "parameters":
                            inParameters = true;
                            currentParameters = new ParametersType();
                            break;
                        case "type":
                            if (inChars) {
                                event = eventReader.nextEvent();
                                currentChars.setType(PlaneType.valueOf(event.asCharacters().getData().toUpperCase()));
                            }
                            break;
                        case "seats":
                            if (inChars) {
                                event = eventReader.nextEvent();
                                currentChars.setSeats(Integer.parseInt(event.asCharacters().getData()));
                            }
                            break;
                        case "ammunition":
                            inAmmunition = true;
                            currentAmmunition = new CharsType.Ammunition();
                            break;
                        case "missiles":
                            if (inAmmunition) {
                                event = eventReader.nextEvent();
                                currentAmmunition.setMissiles(Integer.parseInt(event.asCharacters().getData()));
                                if (currentChars != null) {
                                    currentChars.setAmmunition(currentAmmunition);
                                }
                            }
                            break;
                        case "radar":
                            if (inAmmunition) {
                                event = eventReader.nextEvent();
                                currentAmmunition.setRadar(Boolean.parseBoolean(event.asCharacters().getData()));
                            }
                            break;
                        case "length":
                            if (inParameters) {
                                event = eventReader.nextEvent();
                                currentParameters.setLength(new BigDecimal(event.asCharacters().getData()));
                            }
                            break;
                        case "width":
                            if (inParameters) {
                                event = eventReader.nextEvent();
                                currentParameters.setWidth(new BigDecimal(event.asCharacters().getData()));
                            }
                            break;
                        case "height":
                            if (inParameters) {
                                event = eventReader.nextEvent();
                                currentParameters.setHeight(new BigDecimal(event.asCharacters().getData()));
                            }
                            break;
                        case "price":
                            event = eventReader.nextEvent();
                            if (currentPlane != null) {
                                currentPlane.setPrice(new BigDecimal(event.asCharacters().getData()));
                            }
                            break;
                    }
                }

                if (event.isEndElement()) {
                    String localPart = event.asEndElement().getName().getLocalPart();

                    switch (localPart) {
                        case "chars":
                            inChars = false;
                            inAmmunition = false; // Reset inAmmunition when exiting chars
                            if (currentPlane != null) {
                                currentPlane.setChars(currentChars);
                            }
                            break;
                        case "parameters":
                            inParameters = false;
                            if (currentPlane != null) {
                                currentPlane.setParameters(currentParameters);
                            }
                            break;
                        case "plane":
                            planes.add(currentPlane);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return planes;
    }
}