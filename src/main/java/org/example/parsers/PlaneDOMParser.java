package org.example.parsers;

import org.example.generated.planes.Plane;
import org.example.generated.planes.CharsType;
import org.example.generated.planes.ParametersType;
import org.example.generated.planes.PlaneType;
import java.math.BigDecimal;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlaneDOMParser {

    public List<Plane> parsePlanes(String filePath) {
        List<Plane> planes = new ArrayList<>();

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("plane");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Plane plane = new Plane();

                    // Set ID
                    plane.setId(eElement.getAttribute("id"));

                    // Set model and origin
                    plane.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
                    plane.setOrigin(eElement.getElementsByTagName("origin").item(0).getTextContent());

                    // Set chars
                    Element charsElement = (Element) eElement.getElementsByTagName("chars").item(0);
                    CharsType chars = new CharsType();

                    String planeTypeString = charsElement.getElementsByTagName("type").item(0).getTextContent();
                    chars.setType(PlaneType.valueOf(planeTypeString.toUpperCase()));

                    chars.setSeats(Integer.parseInt(charsElement.getElementsByTagName("seats").item(0).getTextContent()));

                    if (charsElement.getElementsByTagName("ammunition").getLength() != 0) {
                        Element ammoElement = (Element) charsElement.getElementsByTagName("ammunition").item(0);
                        CharsType.Ammunition ammunition = new CharsType.Ammunition();
                        ammunition.setMissiles(Integer.parseInt(ammoElement.getElementsByTagName("missiles").item(0).getTextContent()));
                        ammunition.setRadar(Boolean.parseBoolean(ammoElement.getElementsByTagName("radar").item(0).getTextContent()));
                        chars.setAmmunition(ammunition);
                    }
                    plane.setChars(chars);

                    // Set parameters
                    Element parametersElement = (Element) eElement.getElementsByTagName("parameters").item(0);
                    ParametersType parameters = new ParametersType();
                    parameters.setLength(new BigDecimal(parametersElement.getElementsByTagName("length").item(0).getTextContent()));
                    parameters.setWidth(new BigDecimal(parametersElement.getElementsByTagName("width").item(0).getTextContent()));
                    parameters.setHeight(new BigDecimal(parametersElement.getElementsByTagName("height").item(0).getTextContent()));
                    plane.setParameters(parameters);

                    // Set price
                    plane.setPrice(new BigDecimal(eElement.getElementsByTagName("price").item(0).getTextContent()));

                    planes.add(plane);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return planes;
    }
}

