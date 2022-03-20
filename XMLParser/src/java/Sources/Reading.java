/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author VISHAL PRANAV RB
 */
public class Reading {
    static int numberOfBooks = 0;   
    static String color[] = new String[1000];
    public Table[] tablecontent(){
        Table t[] = null;
        try{
         // create a DOMParser
         DOMParser parser=new DOMParser();
         parser.parse("C:\\Users\\Saubali\\Documents\\NetBeansProjects\\XMLParser\\src\\java\\Sources\\BookStore.xml");

         // get the DOM Document object
         Document doc=parser.getDocument();

         // get all the circle nodes
         NodeList nodelist = doc.getElementsByTagName("Book");
         numberOfBooks =  nodelist.getLength();
         t = new Table[numberOfBooks];
         System.out.println("Number of Books " + numberOfBooks); 
         // retrieve all info about the books
        for(int i=0; i<numberOfBooks; i++) {
            t[i] = new Table();
            System.out.println(i+"Book");
            // get one BOOK node
            Node node = nodelist.item(i);
            
            // get the ISBN attribute 
            NamedNodeMap attrs = node.getAttributes();
            if(attrs.getLength() > 0){
               t[i].ISBN=(String)attrs.getNamedItem("ISBN").getNodeValue();
               t[i].Price=(String)attrs.getNamedItem("Price").getNodeValue();
            }
            // get the child nodes of a book node 
          NodeList childnodelist = node.getChildNodes();
          System.out.println("No of Children " + childnodelist.getLength());
         
          for(int j=0; j<childnodelist.getLength(); j++) {
               Node childnode = childnodelist.item(j);
               String temp = childnode.getNodeName();
               System.out.println(temp);
               if(temp.equals("Title")){
                   System.out.println(" Title value " + childnode.getFirstChild().getNodeValue());
                   t[i].Title = childnode.getFirstChild().getNodeValue();
               }//x[i]= Integer.parseInt(textnode.getNodeValue().trim());
               else if(temp.equals("Authors")) 
               {
                  NodeList auth = childnode.getChildNodes();
                  t[i].Author = "";
                  for(int kk=0;kk<auth.getLength();kk++)
                  {
                    Node nn = auth.item(kk);
                    String authtemp = nn.getNodeName();
                    if(authtemp.equals("Author"))
                        if(nn.getChildNodes().item(1).getNodeName().equals("First_Name")){
                            System.out.println(" First name value " + nn.getChildNodes().item(1).getFirstChild().getNodeValue());
                            t[i].Author+=nn.getChildNodes().item(1).getFirstChild().getNodeValue()+", ";
                        }
                  }  
               }
                  //y[i]= Integer.parseInt(textnode.getNodeValue().trim());
               else if(temp.equals("Remark")) {
                  System.out.println(" Remark value " + childnode.getFirstChild().getNodeValue());
                  t[i].Remark = childnode.getFirstChild().getNodeValue();
               }
            } 

        }        
      
      }  catch (Exception e) {e.printStackTrace(System.err);}
        return t;
    }
}