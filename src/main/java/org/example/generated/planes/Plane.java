//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.11.14 at 12:25:50 PM EET 
//


package org.example.generated.planes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Plane complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Plane">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="model" type="{}modelType"/>
 *         &lt;element name="origin" type="{}countryType"/>
 *         &lt;element name="chars" type="{}charsType"/>
 *         &lt;element name="parameters" type="{}parametersType"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plane", propOrder = {
    "model",
    "origin",
    "chars",
    "parameters",
    "price"
})
public class Plane {

    @XmlElement(required = true)
    protected String model;
    @XmlElement(required = true)
    protected String origin;
    @XmlElement(required = true)
    protected CharsType chars;
    @XmlElement(required = true)
    protected ParametersType parameters;
    @XmlElement(required = true)
    protected BigDecimal price;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the chars property.
     * 
     * @return
     *     possible object is
     *     {@link CharsType }
     *     
     */
    public CharsType getChars() {
        return chars;
    }

    /**
     * Sets the value of the chars property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharsType }
     *     
     */
    public void setChars(CharsType value) {
        this.chars = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link ParametersType }
     *     
     */
    public ParametersType getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametersType }
     *     
     */
    public void setParameters(ParametersType value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s", "ID: " + id));
        sb.append(String.format("%-30s", "Model: " + model));
        sb.append(String.format("%-15s", "Origin: " + origin));
        sb.append(String.format("%-20s", "Type: " + chars.getType()));
        sb.append(String.format("%-10s", "Seats: " + chars.getSeats()));
        if (chars.getAmmunition() != null) {
            sb.append(String.format("%-13s", "Missiles: " + chars.getAmmunition().getMissiles()));
            sb.append(String.format("%-13s", "Radar: " + chars.getAmmunition().isRadar()));
        }
        sb.append(String.format("%-15s", "Length: " + df.format(parameters.getLength()) + "m"));
        sb.append(String.format("%-15s", "Width: " + df.format(parameters.getWidth()) + "m"));
        sb.append(String.format("%-15s", "Height: " + df.format(parameters.getHeight()) + "m"));
        sb.append(String.format("%-15s", "Price: " + df.format(price) + " talers"));
        return sb.toString();
    }

}