package org.example;

import org.example.comparators.*;
import org.example.generated.planes.Plane;
import org.example.generated.planes.CharsType;
import org.example.generated.planes.ParametersType;
import org.example.parsers.PlaneDOMParser;
import org.example.parsers.PlaneSaxParser;
import org.example.parsers.PlaneStaxParser;
import org.example.parsers.PlaneXMLParser;
import org.example.utlities.XmlValidation;

import java.util.List;

public class Main {
    private static final String XML_FILE_PATH = "src//main//resources//military_planes.xml";
    private static final String XSD_FILE_PATH = "srcmain//resources//military_planes.xsd";

    public static void main(String[] args) {

        String delimiter = "---------------------------------------------------------------\n";

        // Testing XML Validator
        System.out.println("XML is valid: " + XmlValidation.isXmlValid(XML_FILE_PATH, XSD_FILE_PATH));
        System.out.println(delimiter);

        // Testing XML Parser
        PlaneXMLParser planeXmlParser = new PlaneXMLParser();
        List<Plane> planesFromXML = planeXmlParser.parsePlanes(XML_FILE_PATH);
        System.out.println("XML Parser Output:\n");
        printPlanesInfo(planesFromXML);
        System.out.println(delimiter);


        // Testing DOM Parser
        PlaneDOMParser domParser = new PlaneDOMParser();
        List<Plane> planesFromDOM = domParser.parsePlanes(XML_FILE_PATH);
        System.out.println("DOM Parser Output:\n");
        printPlanesInfo(planesFromDOM);
        System.out.println(delimiter);


        // Testing SAX Parser
        PlaneSaxParser saxParser = new PlaneSaxParser();
        List<Plane> planesFromSAX = saxParser.parsePlanes(XML_FILE_PATH);
        System.out.println("SAX Parser Output:\n");
        printPlanesInfo(planesFromSAX);
        System.out.println(delimiter);


        // Testing StAX Parser
        PlaneStaxParser staxParser = new PlaneStaxParser();
        List<Plane> planesFromStAX = staxParser.parsePlanes(XML_FILE_PATH);
        System.out.println("StAX Parser Output:\n");
        printPlanesInfo(planesFromStAX);
        System.out.println(delimiter);


        List<Plane> planes = planesFromStAX;

        System.out.println("Sorted by Model:\n");
        planes.sort(new PlaneModelComparator());
        planes.forEach(System.out::println);
        System.out.println(delimiter);

        System.out.println("Sorted by Origin:\n");
        planes.sort(new PlanePlaneOriginComparator());
        planes.forEach(System.out::println);
        System.out.println(delimiter);

        System.out.println("\nSorted by Type:\n");
        planes.sort(new PlaneTypeComparator());
        planes.forEach(System.out::println);
        System.out.println(delimiter);

        System.out.println("\nSorted by Seats:\n");
        planes.sort(new PlaneSeatsComparator());
        planes.forEach(System.out::println);
        System.out.println(delimiter);

        System.out.println("\nSorted by Price:\n");
        planes.sort(new PriceComparator());
        planes.forEach(System.out::println);
        System.out.println(delimiter);

    }

    private static void printPlanesInfo(List<Plane> planes) {
        for (Plane plane : planes) {
            System.out.println("Plane ID: " + plane.getId());
            System.out.println("Model: " + plane.getModel());
            System.out.println("Origin: " + plane.getOrigin());

            CharsType chars = plane.getChars();
            if (chars != null) {
                System.out.println("Type: " + chars.getType());
                System.out.println("Seats: " + chars.getSeats());
                CharsType.Ammunition ammo = chars.getAmmunition();
                if (ammo != null) {
                    System.out.println("Missiles: " + ammo.getMissiles());
                    System.out.println("Radar: " + ammo.isRadar());
                }
            }

            ParametersType params = plane.getParameters();
            if (params != null) {
                System.out.println("Length: " + params.getLength() + " meters");
                System.out.println("Width: " + params.getWidth() + " meters");
                System.out.println("Height: " + params.getHeight() + " meters");
            }

            System.out.println("Price: " + plane.getPrice() + " talers");
            System.out.println();
        }
    }
}
