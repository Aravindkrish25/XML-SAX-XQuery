import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class xmlsax {
    public static void main(String[] args) {
        try {
            File inputFile = new File("employee.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EmployeeParser employeeparser = new EmployeeParser();
            saxParser.parse(inputFile, employeeparser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Project{
    String product, id, price;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
class Employee{
    String firstname, lastname, hiredate, id;
    Project project;

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", id='" + id + '\'' +
                ", project=" + project +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
class EmployeeParser extends DefaultHandler {

    Employee employee;
    ArrayList<Employee> emplist;
    StringBuilder buff =new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("End Document\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("employees")){
            emplist = new ArrayList<>();
        }
        if (qName.equalsIgnoreCase("employee")){
            employee = new Employee();
            employee.setId(attributes.getValue("id"));
        }
        else if (qName.equalsIgnoreCase("project")){
            employee.project = new Project();
        }
        buff.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("employees")){
            System.out.println("--------------------------------------");
            for (Employee emp:emplist) {
                System.out.println("Employee ID: "+ emp.getId());
                System.out.println("First Name: " + emp.getFirstname());
                System.out.println("Last Name: " + emp.getLastname());
                System.out.println("Hire Date: " + emp.getHiredate());
                System.out.println("Project ID: " + emp.getProject().getId());
                System.out.println("Product Name: " + emp.getProject().getProduct());
                System.out.println("Product Price: " + emp.getProject().getPrice());
                System.out.println("---------------------------");
            }
        }
        if (qName.equalsIgnoreCase("employee")){
            emplist.add(employee);
        }
        else if (qName.equalsIgnoreCase("firstname")){
            employee.setFirstname(buff.toString());
        }
        else if (qName.equalsIgnoreCase("lastname")){
            employee.setLastname(buff.toString());
        }
        else if (qName.equalsIgnoreCase("hiredate")){
            employee.setHiredate(buff.toString());
        }
        else if (employee.getProject()!=null){
            if(qName.equalsIgnoreCase("product")){
                employee.getProject().setProduct(buff.toString());
            }
            else if(qName.equalsIgnoreCase("id")){
                employee.getProject().setId(buff.toString());
            }
            else if(qName.equalsIgnoreCase("price")){
                employee.getProject().setPrice(buff.toString());
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buff.append(ch,start,length);
    }
}
