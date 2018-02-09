package s4.b173330; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    public static void main(String[] args) {
	/*==========================================================*/
	/*   Frequencer                                             */
	/*==========================================================*/
	
	System.out.println("checking s4.b173330.Frequencer");
	
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    myObject = new s4.b173330.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");	    
	}

	try{
	    // If the Target length is zero, the method "int frequency()" returns "-1" ?
	    FrequencerInterface  myObject2;
	    int freq2;
	    myObject2 = new s4.b173330.Frequencer();
	    myObject2.setSpace("abcabc".getBytes());
	    myObject2.setTarget("".getBytes());
	    freq2 = myObject2.frequency();
	    System.out.print("When target length is zero... ");
	    if(-1 == freq2) { System.out.println("OK"); } else { System.out.println("WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    // If the Target is null, the method "int frequency()" returns "-1" ?
	    FrequencerInterface  myObject3;
	    int freq3;
	    myObject3 = new s4.b173330.Frequencer();
	    myObject3.setSpace("abcabc".getBytes());
	    freq3 = myObject3.frequency();
	    System.out.print("When target is null... ");
	    if(-1 == freq3) { System.out.println("OK"); } else { System.out.println("WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    // If the Space length is zero, the method "int frequency()" return "0" ?
	    FrequencerInterface  myObject4;
	    int freq4;
	    myObject4 = new s4.b173330.Frequencer();
	    myObject4.setTarget("a".getBytes());
	    myObject4.setSpace("".getBytes());
	    freq4 = myObject4.frequency();
	    System.out.print("When space length is zero... ");
	    if(0 == freq4) { System.out.println("OK"); } else { System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    // If the Space is null, the method "int frequency()" return "0" ?
	    FrequencerInterface  myObject5;
	    int freq5;
	    myObject5 = new s4.b173330.Frequencer();
	    myObject5.setTarget("a".getBytes());
	    freq5 = myObject5.frequency();
	    System.out.print("When space is null... ");
	    if(0 == freq5) { System.out.println("OK"); } else { System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}	
	
	/*==========================================================*/
	/*   InformationEstimator                                   */
	/*==========================================================*/
  
	System.out.println("checking s4.b173330.InformationEstimator");
	
	try {
	    // If target length is zero, the method "estimation()" returns "0.0" ?
	    InformationEstimatorInterface myObject;
	    double value;
	    myObject = new s4.b173330.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    // If target length is zero, the method "estimation()" returns "0.0" ?
	    myObject.setTarget("".getBytes());
	    value = myObject.estimation();
	    System.out.print(">Target length is zero "+value);
	    if(value == 0.0) { System.out.println(" OK"); } else { System.out.println(" WRONG"); }
	}catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	
	try{
	    // If target is null, the method "estimation()" returns "0.0" ?
	    InformationEstimatorInterface myObject2;
	    double value2;
	    myObject2 = new s4.b173330.InformationEstimator();
	    myObject2.setSpace("1230123".getBytes());
	    value2 = myObject2.estimation();
	    System.out.print(">Target is null "+value2);
	    if(value2 == 0.0) { System.out.println(" OK"); } else { System.out.println(" WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    // If space is null, the method "estimation()" retuens "Double.MAX_VALUE"?
	    InformationEstimatorInterface myObject3;
	    double value3;
	    myObject3 = new s4.b173330.InformationEstimator();
	    myObject3.setTarget("23".getBytes());
	    value3 = myObject3.estimation();
	    System.out.print(">Space is null "+value3);
	    if( value3 == Double.MAX_VALUE ) { System.out.println(" OK"); } else { System.out.println(" WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    // If the true value is infinite, the method "estimation()" retuens "Double.MAX_VALUE"?
	    InformationEstimatorInterface myObject4;
	    double value4;
	    myObject4 = new s4.b173330.InformationEstimator();
	    myObject4.setSpace("".getBytes());
	    myObject4.setTarget("23".getBytes());
	    value4 = myObject4.estimation();
	    System.out.print(">True value is infinite  "+value4);
	    if( value4 == Double.MAX_VALUE ) { System.out.println(" OK"); } else { System.out.println(" WRONG"); }
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}

	/*
	myObject.setTarget("0".getBytes());
	value = myObject.estimation();
	System.out.println(">0 "+value);
	myObject.setTarget("01".getBytes());
	value = myObject.estimation();
	System.out.println(">01 "+value);
	myObject.setTarget("0123".getBytes());
	value = myObject.estimation();
	System.out.println(">0123 "+value);
	myObject.setTarget("00".getBytes());
	value = myObject.estimation();
	System.out.println(">00 "+value);
	*/
    }
}	    
