package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class DomParser {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        parse();

        Catalog catalog = new Catalog();
        Article article = new Article();
        article.id = "3442";
        article.author = "Author";
        article.title = "title";
        article.genre = "genre";
        article.publishDate = "01-01-2019";
        article.text = "dfgdfdfg";

        catalog.articleList.add(article);

        System.out.println(createXml(catalog));
    }


    public static void parse() throws ParserConfigurationException, IOException, SAXException {
        Catalog catalog = new Catalog();

        // Инициализация DOM Parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // работа с документом
        Document document = builder.parse(DomParser.class.getClassLoader().getResourceAsStream("articles.xml"));

        // получаем корневой элемент
        Element element = document.getDocumentElement();
        System.out.println(element);

        // получаем дочерние узлы
        NodeList articlesList = element.getChildNodes();
        for (int i = 0; i < articlesList.getLength(); i++) {
            Node articleNode = articlesList.item(i);
            if (articleNode instanceof Element) {
                Article article = new Article();
                catalog.articleList.add(article);

                // считываем атрибуты
                NamedNodeMap attrs = articleNode.getAttributes();
                article.id = attrs.getNamedItem("id").getNodeValue();

                // получаем дочерние узлы (element)
                NodeList articleProp = articleNode.getChildNodes();

                for (int j = 0; j < articleProp.getLength(); j++) {
                    Node prop = articleProp.item(j);
                    if (prop instanceof Element) {
                        String propName = prop.getNodeName();
                        String value = prop.getNodeValue();

                        if ("author".equals(propName)) article.author = value;
//                        ...
//                        ...
                    }
                }
            }
        }
        System.out.println(catalog);
    }

    public static String createXml(Catalog catalog) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        // Инициализация DOM Parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // работа с документом
        Document document = builder.newDocument();
        document.setXmlVersion("1.0");

        // создаем корневой элемент
        Element element = document.createElement("catalog");
        document.appendChild(element);

        for (Article article : catalog.articleList) {
            Element articleElement = document.createElement("article");
            element.appendChild(articleElement);

            articleElement.setAttribute("id", article.id);
            addNode(document, articleElement, "author", article.author);
            addNode(document, articleElement, "title", article.title);
            addNode(document, articleElement, "genre", article.genre);
            addNode(document, articleElement, "publish_date", article.publishDate);
            addNode(document, articleElement, "text", article.text);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 4);

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        FileOutputStream fos = new FileOutputStream("catalog.xml");
        StreamResult resultFile = new StreamResult(fos);

        transformer.transform(source, result);
        transformer.transform(source, resultFile);
        return writer.toString();
    }

    public static void addNode(Document document, Element parent, String tagName, String value){
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value));
        parent.appendChild(element);
    }

}