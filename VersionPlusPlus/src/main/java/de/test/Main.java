package de.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.ParsingException;
import nu.xom.Text;




public class Main {

	
	public static String newVersionGit;
	
	public static void increment(Element version){
		String[] versionNumbers = version.getValue().split("\\.");
		Integer end = Integer.valueOf(versionNumbers[versionNumbers.length - 1]) + 1;
		versionNumbers[versionNumbers.length - 1] = String.valueOf(end);
		String newVersion = versionNumbers[0];
		newVersionGit = "V" + newVersion;
		for(int i= 1; i < versionNumbers.length; i++ ){
			newVersion = newVersion + "." + versionNumbers[i];
			newVersionGit = newVersionGit + "-" + versionNumbers[i];
		}
//		Node newNode = new Text(newVersion);
		version.removeChildren();
		//Node old = version.getChildElements().get(0);
		version.appendChild(newVersion);;
	}
	
	
	public static void waitForMe(Process p){

        BufferedReader input = new BufferedReader(new InputStreamReader(
                p.getInputStream()), 13107200);

        String line = null;

        try {
        
        while (input.ready() == false) { /* intentional empty space here */ }
        
      
			if (input.ready()) {
			    while ((line = input.readLine()) != null) {
			        System.out.println(line);
			    }

			    try {
			        p.waitFor();
			    } catch (InterruptedException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		      
		      String command0 = "git commit -m \" Changed version.  \" ";
		      String command00 = "git push origin  ";		      
		      String command1 = "git tag -a " + newVersionGit;
		      String command2 = "git push origin  " + newVersionGit;
		      Runtime runtime = Runtime.getRuntime();
		      Process process = runtime.exec(command0);
		      waitForMe(process);
		      process = runtime.exec(command00);
		      waitForMe(process);
		      process = runtime.exec(command1);
		      waitForMe(process);
		      process = runtime.exec(command2);
		      waitForMe(process);

		      
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
