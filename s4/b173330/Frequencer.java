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
*/


public class Frequencer implements FrequencerInterface{
    // Code to Test, *warning: This code  contains intentional problem*
    byte [] myTarget;
    byte [] mySpace;
    boolean targetReady = false;
    boolean spaceReady = false;

    int [] suffixArray;
    /* public void setTarget(byte[] target) { myTarget = target;}
       public void setSpace(byte[] space) { mySpace = space; }*/

    private void printSuffixArray(){
	if(spaceReady) {
	    for(int i=0; i<mySpace.length; i++){
		int s = suffixArray[i];
		for(int j=s; j<mySpace.length; j++){
		    System.out.write(mySpace[j]);
		}
		System.out.write('\n');
	    }
	}
    }

    private int suffixCompare(int i,int j) {
	int si = suffixArray[i];
	int sj = suffixArray[j];
	int s = 0;
	if(si > s) s = si;
	if(sj > s) s = sj;
	int n = mySpace.length -s;
	for(int k = 0; k<n; k++){
	    if(mySpace[si+k]>mySpace[sj+k]) return 1;
	    if(mySpace[si+k]<mySpace[sj+k]) return -1;
	}
	if(si < sj) return 1;
	if(si > sj) return -1;
	return 0;
    }

    public void setSpace(byte[] space) {
	mySpace = space;
	if(mySpace.length>0)
	    spaceReady = true;
	suffixArray = new int[space.length];
	for(int i = 0; i < space.length; i++){
	    suffixArray[i] = i;
	}
	
	//myspace[i]~myspace[end]とmyspace[j]~myspace[end]を比較して,結果に応じてsuffixArrayを交換するようにする
	for(int i=0;i<space.length;i++){
	    for(int j=i+1;j<space.length;j++){
		if(suffixCompare(i,j)==1){
		    int tmp=suffixArray[i];
		    suffixArray[i]=suffixArray[j];
		    suffixArray[j]=tmp;
		}
	    }
	}
	printSuffixArray();
    }

    private int targetCompare(int i,int start,int end){
        int s = suffixArray[i];
        int tl = end-start;
        if(tl > mySpace.length-s) return -1;
        for(int k = 0; k<tl; k++){
            if(mySpace[s+k]>myTarget[start+k]) return 1;
            if(mySpace[s+k]<myTarget[start+k]) return -1;
        }
        return 0;
    }
    
    
    private int subByteStartIndex(int start,int end){
	for(int i=0;i<mySpace.length;i++){
	    if(targetCompare(i,start,end)==0){
		return i;
	    }
	}
	return suffixArray.length;
    }
    
    private int subByteEndIndex(int start,int end){
	for(int i=mySpace.length-1;i>0;i--){
	    if(targetCompare(i,start,end)==0)
		return i+1;
	}
	return suffixArray.length;
    }
    
    public int subByteFrequency(int start,int end){
	int spaceLength = mySpace.length;
	int count = 0;
	for(int offset = 0; offset<spaceLength - (end - start); offset++){
	    boolean abort = false;
	    for(int i=0; i< (end-start); i++){
		if(myTarget[start+i] != mySpace[offset+i])
		    {
			abort = true;
			break;
		    }
	    }
	    if(abort == false)
		{
		    count++;
		}
	}
        
	int first = subByteStartIndex(start,end);
	int last1 = subByteEndIndex(start,end);
	return last1-first;
    }
    
    public void setTarget(byte[] target) {
	myTarget = target;
	if(myTarget.length>0)
	    targetReady=true;
    }
    
    public int frequency() {
	if(targetReady==false)return -1;
	if(spaceReady==false)return 0;
	return subByteFrequency(0,myTarget.length);
    }
    
    /* public int frequency() {
       
       if(mySpace == null || mySpace.length == 0){
       return 0;
       }
       
       if(myTarget == null ||  myTarget.length == 0){
       return -1;
       }
	
       
       int targetLength = myTarget.length;
       int spaceLength = mySpace.length;
       int count = 0;
       
       for(int start = 0; start<spaceLength; start++) {
	    boolean abort = false;
	    for(int i = 0; i<targetLength; i++) {
	    if(myTarget[i] != mySpace[start+i]) { abort = true; break; }
	    }
	    if(abort == false) { count++; }
	    }
	    return count;
	    }
	    
	    // I know that here is a potential problem in the declaration.
	    public int subByteFrequency(int start, int length) { 
	    // Not yet, but it is not currently used by anyone.
	    return -1;
	    }/*/
    
    public static void main(String[] args) {
	Frequencer myObject;
	int freq;
	try {
	    System.out.println("checking my Frequencer");
	    myObject = new Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    
	    myObject.setTarget("H".getBytes());
        System.out.println("settarget");
	    freq = myObject.frequency();
        System.out.println("frequency");
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
    }
}	  
