interface Document {
    void open();
}

// Concrete Document Classes
class WordDocument implements Document {
    public void open() {
        System.out.println("Word Document Created");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("PDF Document Created");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Excel Document Created");
    }
}

// Abstract Factory
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// Concrete Factories
class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Main Class
public class FactoryMethodPatternExample {

    public static void main(String[] args) {

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document word = wordFactory.createDocument();
        word.open();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excel = excelFactory.createDocument();
        excel.open();
    }
}