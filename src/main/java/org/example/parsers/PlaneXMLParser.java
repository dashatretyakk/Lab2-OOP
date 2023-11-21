package org.example.parsers;

import org.example.generated.planes.Plane;
import org.example.generated.planes.CharsType;
import org.example.generated.planes.ParametersType;
import org.example.generated.planes.PlaneType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlaneXMLParser {

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
                    plane.setId(eElement.getAttribute("id"));
                    plane.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
                    plane.setOrigin(eElement.getElementsByTagName("origin").item(0).getTextContent());

                    Element charsElement = (Element) eElement.getElementsByTagName("chars").item(0);
                    CharsType chars = new CharsType();
                    chars.setType(PlaneType.valueOf(charsElement.getElementsByTagName("type").item(0).getTextContent().toUpperCase()));
                    chars.setSeats(Integer.parseInt(charsElement.getElementsByTagName("seats").item(0).getTextContent()));

                    Node ammoNode = charsElement.getElementsByTagName("ammunition").item(0);
                    if (ammoNode != null && ammoNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element ammoElement = (Element) ammoNode;
                        CharsType.Ammunition ammunition = new CharsType.Ammunition();
                        ammunition.setMissiles(Integer.parseInt(ammoElement.getElementsByTagName("missiles").item(0).getTextContent()));
                        ammunition.setRadar(Boolean.parseBoolean(ammoElement.getElementsByTagName("radar").item(0).getTextContent()));
                        chars.setAmmunition(ammunition);
                    }
                    plane.setChars(chars);

                    Element paramsElement = (Element) eElement.getElementsByTagName("parameters").item(0);
                    ParametersType parameters = new ParametersType();
                    parameters.setLength(new BigDecimal(paramsElement.getElementsByTagName("length").item(0).getTextContent()));
                    parameters.setWidth(new BigDecimal(paramsElement.getElementsByTagName("width").item(0).getTextContent()));
                    parameters.setHeight(new BigDecimal(paramsElement.getElementsByTagName("height").item(0).getTextContent()));
                    plane.setParameters(parameters);

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
