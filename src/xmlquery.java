import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import com.saxonica.xqj.SaxonXQDataSource;

public class xmlquery {
    public static void executequery(String pathtoquery) throws FileNotFoundException, XQException{
        InputStream inputStream = new FileInputStream(pathtoquery);
        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();
        while (result.next()) {
            System.out.println(result.getItemAsString(null));
        }
    }
    public static void main(String[] args){
        StringBuilder path = new StringBuilder("src//query0.xqy");
        System.out.println("----------------------------------------------------");
        try {
            System.out.println("1) Details of employee with ID 1004.\n");
            path.setCharAt(10,'1');
            executequery(path.toString());
            System.out.println("----------------------------------------------------\n");

            System.out.println("2) List all the employees names inside name tag.\n");
            path.setCharAt(10,'2');
            executequery(path.toString());
            System.out.println("----------------------------------------------------\n");

            System.out.println("3) List all employee names hired during the year 2005\n");
            path.setCharAt(10,'3');
            executequery(path.toString());
            System.out.println("----------------------------------------------------\n");

            System.out.println("4) Total price of all employees' products\n");
            path.setCharAt(10,'4');
            executequery(path.toString());
            System.out.println("\n----------------------------------------------------\n");

            System.out.println("5) Employees' product price > 100\n");
            path.setCharAt(10,'5');
            executequery(path.toString());
            System.out.println("----------------------------------------------------\n");

            System.out.println("6) Print details of employee who sells Camera\n");
            path.setCharAt(10,'6');
            executequery(path.toString());
            System.out.println("----------------------------------------------------\n");
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (XQException e) {
            e.printStackTrace();
        }
    }
}  