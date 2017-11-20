package eg.edu.alexu.csd.oop.db.cs60.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.Column;
import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.DBContainer;
import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.Record;
import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.Table;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    public void saveTableToXML(String path, Table databaseTable) throws FileNotFoundException {
        Document dom;
        Element col = null, rec;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("table");
            rootEle.setAttribute("name", databaseTable.getName());

            // create data elements and place them under root

            for (Column column : databaseTable.getColumns()) {
                col = dom.createElement("column");
                col.setAttribute("name", column.getName());
                col.setAttribute("type", column.getType());
                for (Object object : column.getRecords()) {
                    Record record = (Record) object;
                    /*if(record.getValue().getClass().getSimpleName().equalsIgnoreCase("string")){

                    }*/
                    rec = dom.createElement("record");
                    if(record.getValue() != null)
                        rec.setAttribute("value", record.getValue().toString());
                    else
                        rec.setAttribute("value", "null");
                    //rec.appendChild(dom.createTextNode(record.getValue().toString()));
                    col.appendChild(rec);
                }
                rootEle.appendChild(col);
            }
            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                // tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                // "shapes.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(path)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    public Table loadTableFromXML(String path) {
        Table loadedTable = null;
        Document dom;
        InputStream inputStream;
        Reader reader = null;
        try {
            File xml = new File(path);
            inputStream = new FileInputStream(xml);
            reader = new InputStreamReader(inputStream, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputSource is = new InputSource(reader);
        is.setEncoding("ISO-8859-1");
        // Make an instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(is);
            Element doc = dom.getDocumentElement();
            NodeList rootNode = doc.getChildNodes();
            loadedTable = new Table(doc.getAttribute("name"));
            ArrayList<Column> cols = new ArrayList<>();

            for (int i = 0; i < rootNode.getLength(); i++) {
                Node colNode = rootNode.item(i);
                NodeList colNL = colNode.getChildNodes();
                Column col = new Column(colNode.getAttributes().getNamedItem("name").getNodeValue(),
                                        colNode.getAttributes().getNamedItem("type").getNodeValue());
                System.out.println("Col Name : " + colNode.getAttributes().getNamedItem("name"));
                if (colNode.getNodeName().equals("#text"))
                    continue;
                //put("id", colNode.getNodeName());
                for (int j = 0; j < colNL.getLength(); j++) {
                    Node recItem = colNL.item(j);
                    if (recItem.getNodeName().equals("#text"))
                        continue;
                    //shapeMap.put(prop.getNodeName(), prop.getTextContent());
                    Record record = null;
                    if(!recItem.getAttributes().getNamedItem("value").equals("null"))
                        record = new Record(recItem.getAttributes().getNamedItem("value"));
                    else
                        record = new Record(null);

                    col.addRecord(record);
                }
                //loadedTable.addColumn(col);
                cols.add(col);
            }

            loadedTable.setColumns(cols);

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return loadedTable;
    }
}
