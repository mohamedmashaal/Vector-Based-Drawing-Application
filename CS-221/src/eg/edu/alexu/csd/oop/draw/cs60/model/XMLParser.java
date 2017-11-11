package eg.edu.alexu.csd.oop.draw.cs60.model;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    public void saveToXML(String path, ArrayList<Map<String,String>> maps) throws FileNotFoundException {
        Document dom;
        Element shape = null, property = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("shapes");

            // create data elements and place them under root

            for(Map<String,String> map : maps) {
                shape = dom.createElement(map.get("id"));
                for (Map.Entry entry : map.entrySet()) {
                    property = dom.createElement(entry.getKey().toString());
                    property.appendChild(dom.createTextNode(entry.getValue().toString()));
                    shape.appendChild(property);
                }
                rootEle.appendChild(shape);
            }
            dom.appendChild(rootEle);

            NodeList nl = dom.getDocumentElement().getChildNodes();
            for(int i=0; i<nl.getLength(); i++){
                Node node = nl.item(i);
                System.out.println(node.getNodeName() + " " + node.getNodeValue());
            }

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "shapes.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(path)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }


    public ArrayList<Map<String, String>> readXML(File xml) {
        ArrayList<Map<String, String>> shapesMaps = new ArrayList<>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(xml);
            Element doc = dom.getDocumentElement();
            NodeList rootNode = doc.getChildNodes();

            for(int i=0; i<rootNode.getLength(); i++){
                Map<String, String> shapeMap = new HashMap<>();
                Node shapeNode = rootNode.item(i);
                NodeList shapeNL = shapeNode.getChildNodes();
                if(shapeNode.getNodeName().equals("#text"))
                    continue;
System.out.println("Name : " + shapeNode.getNodeName() + " -- " + shapeNode.getTextContent());
                shapeMap.put("id",shapeNode.getNodeName());
                for(int j=0; j<shapeNL.getLength(); j++){
                    Node prop = shapeNL.item(j);
                    if(prop.getNodeName().equals("#text"))
                        continue;
                    shapeMap.put(prop.getNodeName(), prop.getTextContent());
                    System.out.println("Hey : " + prop.getNodeName() + " -- " + prop.getTextContent());
                }
                shapesMaps.add(shapeMap);
            }


        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        StringBuilder x = new StringBuilder();
        for(Map z : shapesMaps) {
        	x.append(z.keySet().toString()+ "\n");
        }
        throw new RuntimeException(x.toString());
        //return shapesMaps;
    }

}
