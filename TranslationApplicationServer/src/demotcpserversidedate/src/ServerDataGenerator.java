package demotcpserversidedate.src;

import java.util.Date;

/**
 * This class generates date.
 * 
 * @author emalianakasmuri
 *
 */

public class ServerDataGenerator {

	
	
	

private String currentDate;

private String NoOFWordsInAString;
	
public void setNoOFWordsInAString(String InputString) 

{
	
	 int count=0;  
     
     char ch[]= new char[InputString.length()];     
     for(int i=0;i<InputString.length();i++)  
     {  
         ch[i]= InputString.charAt(i);  
         if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )  
             count++;  
        
     }  
     
     String s=String.valueOf(count);  
     
	this.NoOFWordsInAString =s;
}

public String getNoOFWordsInAString() {
	
	
	return NoOFWordsInAString ;
}

	
	public void setCurrentDate(String currentDate ) {

		
		
		this.currentDate=currentDate;
		
		
	}
	public String getCurrentDate() {
		
		
		
		return currentDate;
		
	}
	
}
