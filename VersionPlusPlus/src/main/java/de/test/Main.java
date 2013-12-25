package de.test;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.ParsingException;
import nu.xom.Text;




public class Main {

	public static void increment(Element version){
		String[] versionNumbers = version.getValue().split("\\.");
		Integer end = Integer.valueOf(versionNumbers[versionNumbers.length - 1]) + 1;
		versionNumbers[versionNumbers.length - 1] = String.valueOf(end);
		String newVersion = versionNumbers[0];
		for(int i= 1; i < versionNumbers.length; i++ ){
			newVersion = newVersion + "." + versionNumbers[i];
		}
//		Node newNode = new Text(newVersion);
		version.removeChildren();
		//Node old = version.getChildElements().get(0);
		version.appendChild(newVersion);;
	}
	
	
	
	public static void main(String[] args){
		
		   Builder builder = new Builder();
		     
		    try {
		      String dir = System.getProperty("user.dir");
		      String sep = System.getProperty("file.separator");
		      System.out.println(dir + sep + "pom.xml");
		      File f = new File(dir + sep + "pom.xml");	
		      Document doc = builder.build(f);
		      Element root = doc.getRootElement();
		      Elements e = root.getChildElements();
		      Element version = null;
		      for(int i = 0; i < e.size(); i++){
		    	  if (e.get(i).getLocalName().equalsIgnoreCase("version")){
		    		  version = e.get(i);
		    		  break;
		    	  }
		      }
		      if (version != null){
		    	  increment(version);
		      }
		      // write to file
		      String result = doc.toXML();
		      PrintWriter out = new PrintWriter("pom.xml");
		      out.println(result);
		      out.close();
		    }
		    // indicates a well-formedness error
		    catch (ParsingException ex) { 
		      System.out.println(args[0] + " is not well-formed.");
		      System.out.println(ex.getMessage());
		    }  
		    catch (IOException ex) { 
		      System.out.println(ex);
		    }  
		
		
	}
	
}
