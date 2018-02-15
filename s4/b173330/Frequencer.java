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

public class Frequencer implements FrequencerInterface {
	// Code to Test, *warning: This code  contains intentional problem*
	byte[] myTarget;
	byte[] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;
	int[] suffixArray;

	/*****************************************/
	// suffixの比較を行う                     
	// 1  : i > j							 
	// -1 : i < j
	// 0  : i = j                            
	/****************************************/
	private int suffixCompare(int i, int j) {
		int si = i;
		int sj = j;
		int s = 0;
		if (si > s)
			s = si;
		if (sj > s)
			s = sj;
		int n = mySpace.length - s;
		for (int k = 0; k < n; k++) {
			if (mySpace[si + k] > mySpace[sj + k]){
				return 1;
			}
			if (mySpace[si + k] < mySpace[sj + k]){
				return -1;
			}
		}
		if (si < sj){
			return 1;
		}
		if (si > sj){
			return -1;
		}
		return 0;
	}


	/*****************************************/
	// 1.mySpaceのセット     
	// 2.suffixArrayの生成・ソート               
	/****************************************/
	public void setSpace(byte[] space) {
		mySpace = space;
		if (mySpace.length > 0)
			spaceReady = true;
		suffixArray = new int[space.length];
		for (int i = 0; i < space.length; i++) {
			suffixArray[i] = i;
		}

		/*****************************************/
		// クイックソート実装のためのローカルクラス    
		// 1  : i > j							 
		// -1 : i < j
		// 0  : i = j                            
		/****************************************/
		class QuickSort{	
			public void quickSort(int[] suffixArray, int left, int right) {
				if(left<=right) {
					int p = suffixArray[(left+right)/2];
					int l = left;
					int r = right;
					while(l <= r) {
						while(suffixCompare(suffixArray[l] , p) == -1) l++;
						while(suffixCompare(p , suffixArray[r]) == -1 ) r--;
						
						if(l<=r) {
							int tmp = suffixArray[l];
							suffixArray[l] = suffixArray[r];
							suffixArray[r] = tmp;
							l++;
							r--;
						}
					}
					
					quickSort(suffixArray, left, r);
					quickSort(suffixArray, l, right);
				}
			}
		}

		QuickSort myQuickSort = new QuickSort();
		myQuickSort.quickSort(suffixArray, 0, space.length-1);
	}

	/*****************************************/
	// Targetとspaceの部分文字列の比較           
	// 1  : space > target							 
	// -1 : space < target
	// 0  : space = target           
	/****************************************/
	private int targetCompare(int i, int start, int end) {
		int s = suffixArray[i];
		int tl = end - start;

		if(tl > (mySpace.length - s)){ //部分文字列よりも，ターゲットの方が長い場合
			for(int k = 0; k < (mySpace.length-s); k++){
				if(mySpace[s+k] > myTarget[start + k]){
					return 1;
				}
			}
			return -1;
		}else{
			for (int k = 0; k < tl; k++) {
				if (mySpace[s + k] > myTarget[start + k]){
					return 1;
				}
				if (mySpace[s + k] < myTarget[start + k]){
					return -1;
				}
			}
			return 0;
		}
	}

	/*****************************************/
	// Targetと一致するsuffixの開始位置                         
	/****************************************/
	private int subByteStartIndex(int start, int end) {
		// before
		/*
		for (int i = 0; i < mySpace.length; i++) {
			if (targetCompare(i, start, end) == 0) {
				return i;
			}
		}
		return suffixArray.length;
		*/
	        
		// after
		int pLeft = 0;
		int pRight = suffixArray.length-1;
		int center = 0;;
		while( pLeft < pRight){
			center = (pLeft + pRight) / 2;
			int center_stut = targetCompare(center, start, end);
			//System.out.println("pLeft : "+pLeft+" center : "+center+" pRight : "+pRight+" status : "+center_stut);		
			if(targetCompare(center, start, end) == -1){
				pLeft = center + 1;
			}
			else{
				pRight = center;
			}
		}

		//System.out.println("pLeft : "+pLeft+" center : "+center+" pRight : "+pRight);		

		if((pLeft == pRight) && (targetCompare(pLeft, start, end)==0 )){
			return pLeft;
		}
		return suffixArray.length;
	}

	/*****************************************/
	// Targetと一致するsuffixの終了位置                    
	/****************************************/
	private int subByteEndIndex(int start, int end) {
		// before
		/*
		for (int i = mySpace.length - 1; i > 0; i--) {
			if (targetCompare(i, start, end) == 0)
				return i + 1;
		}
		return suffixArray.length;
	    */
	        
		// after
		int pLeft = 0;
		int pRight = suffixArray.length-1;
		int center = 0;
		while( pLeft < pRight){
			if((pRight-pLeft) == 1){
				center = (pLeft + pRight) / 2 + 1;
			}else{
				center = (pLeft + pRight) / 2 ;
			}

			//System.out.println("pLeft : "+pLeft+" center : "+center+" pRight : "+pRight);		

			if(targetCompare(center, start, end) == 1){
				pRight = center-1;
			}
			else{
				pLeft = center;
			}
		}

		//System.out.println("pLeft : "+pLeft+" center : "+center+" pRight : "+pRight);		

		if((pLeft == pRight) && (targetCompare(pRight, start, end)==0 )){
			return pRight + 1;
		}
		return suffixArray.length;
	}

	/*****************************************/
	// mySpace内に出てくるターゲットの数                       
	/****************************************/
	public int subByteFrequency(int start, int end) {
		int spaceLength = mySpace.length;
		int count = 0;
		for (int offset = 0; offset < spaceLength - (end - start); offset++) {
			boolean abort = false;
			for (int i = 0; i < (end - start); i++) {
				if (myTarget[start + i] != mySpace[offset + i]) {
					abort = true;
					break;
				}
			}
			if (abort == false) {
				count++;
			}
		}

		int first = subByteStartIndex(start, end);
		int last1 = subByteEndIndex(start, end);
		
		//inspection_code
		/*
		for(int	k=start;k<end;k++){
		    System.out.write(myTarget[k]);
		}
		System.out.printf(": first=%d last1=%d\n", first, last1);
		*/
		return last1 - first;
	}

	public void setTarget(byte[] target) {
		myTarget = target;
		if (myTarget.length > 0){
			targetReady = true;
		}
	}

	public int frequency() {
	    if (targetReady == false){
			return -1;
	    }
	    if (spaceReady == false){
			return 0;
	    }
	    return subByteFrequency(0, myTarget.length);
	}
    
    public static void main(String[] args) {
	Frequencer myObject;
	int freq;
		try {
		    System.out.println("checking my Frequencer");
		    myObject = new Frequencer();
		    myObject.setSpace("Hi Ho Hi Ho".getBytes());		
		    myObject.setTarget("H".getBytes());
		    freq = myObject.frequency();
		    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
		    if (4 == freq) {
			System.out.println("OK");
		    } else {
			System.out.println("WRONG");
		    }
		} catch (Exception e) {
		    System.out.println("Exception occurred: STOP");
		}
    }
}
