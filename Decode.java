import java.io.*;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class Decode {

	
	public static String p1_F = "AGAGATACCTATAGTTCGGTTCTTGCTTAA";
	public static String p1_R = "TCTCATGAATCGCTGCTCTACTAACAACGT";
	
	public static String start = "CAGTCGCTCCACAAGTACCAGCCTCGTCTCCACAT";
	public static String end = "CCGTCAATCTTGCCATTGCTTGCTCAATCATATCC";
	
	//static String file = "C:\\Users\\sia\\Desktop\\reads\\Hunmin_Total2_High_0819//140min_load//Merge Output File.txt";
	static String file = "C:\\Users\\sia\\Desktop\\reads\\Merged_hunmin_total_0530.txt";
	//static String file = "C:\\Users\\sia\\Desktop\\reads\\sequence_p.txt";
	
	//파일 이름을 코드 돌릴 때마다 다르게 해 줘야 해요 
    static String output2= "C:\\Users\\sia\\Desktop\\reads\\코드 결과\\Align\\015_smm1.txt"; //Page1의 오차범위 (0~1)결과
    static String output = "C:\\Users\\sia\\Desktop\\reads\\코드 결과\\Align\\015_smm1_wide.txt"; 
    static String line_1=null;
  
    public static BufferedWriter bw;
	public static BufferedWriter bw2;
	
	//변경 가능한 변수
	static int min_boundary;  // line length의 최소 길이
	static int max_boundary;  // line length의 최대 길이
	static int error_range=7;     // 오차 범위(+-) 
	static int page_mm=5;         // page miss match
	static int start_mm=1;  // start, end miss match  
	static int length=800;
	static int length_range=50;
	
	
	public Decode(int p_mm, int s_mm, int len_boundary,String output) {
		this.page_mm=p_mm;
		this.start_mm=s_mm;
		this.length_range=len_boundary;
		this.output2=output;
	}
	
	public void run() throws FileNotFoundException, IOException {  

	      int line_num=0;
	      int count = 0;
	     

	      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	  String origin_line=null;
	    	 
	    	  int s_score1=0, s_score2=0;
	    	  
	    	  System.out.println(length_range);
	          System.out.println(page_mm);
	          System.out.println(start_mm);
	    	 
	    	  bw= new BufferedWriter( new FileWriter(output));
	    	  bw2= new BufferedWriter( new FileWriter(output2));
	    	  
	    	  while ((origin_line=br.readLine()) != null) { //파일을 한줄씩 읽는다
    			  
	    		  int idx1 = 0;//line.indexOf(p1_F) ; 
	    		  int idx2 = 0;// line.lastIndexOf(p1_R) ;
	    		  
	    		  line_num++;
	    		  
	    		  if(line_num%100000==0) {  // 결과 진행 상황을 알기 위해 100000줄 씩 진행될 때마다 line_number 출력
	    			  System.out.println("line number: "+line_num);
	    		  }
	    		  

	    		  String line=origin_line.replaceAll(" ","");
	    		  
	    		  if( line.length() < 5*p1_F.length() ) { //line(파일의 한 줄)의 길이가 page의 길이의 5배보다 적으면 다음 line으로 넘어간다.
	    			  continue;
	    		  }
	    		  
	    		  int check = line.charAt(0);
	    		  if(65>check||check>90)
	    			  continue;
	    		  
	    		  
	    	//page1 data를 이용해서 line1을 가져온다.	 

	    		Vector<Integer> pIdx1 = new Vector<Integer>();
	    		Vector<Integer> pIdx2 = new Vector<Integer>();

					
				String p1_F1 = p1_F.substring(0, 15);
					
				lineCheck(line, p1_F, s_score1 ,pIdx1); //p1_F1이 시작되는 위치를 해당 vector(pIdx1)에 담아준다.
					
					
   				String p1_R1 = p1_R.substring(0, 15);
					
				lineCheck(line, p1_R, s_score2 ,pIdx2);//p1_R1이 시작되는 위치를 해당 vector(pIdx2)에 담아준다.
					
					
				if(pIdx1.size()==0||pIdx2.size()==0) { //Vector size가 0이라는 것은 그 line에 page서열이 없다는 것. 
				    	continue; //다음 line으로 넘긴다.
				}
					
				idx1=(int)pIdx1.get(0); // pIdx1의 첫번째 요소를 가져온다
				idx2=(int)pIdx2.get(pIdx2.size()-1); //pIdx2의 마지막 요소를 가져온다.
				
				if(idx2>idx1+p1_F.length()) {  
					line_1 = line.substring(idx1+p1_F.length(),idx2); //line을 p_F~p_R로 잘라준다
				}
				else {
					continue;
				}
				
				count++;
				//result
				getOutput(line_1,line_num,bw2,bw);// 이 method를 통해 line에 따른 결과를 파일로 출력한다.
		
	   	  }
	    	  System.out.println("line num:"+line_num);
	    	  System.out.println("count: "+count);  
	    	  
	  }
	      try (BufferedReader brr = new BufferedReader(new FileReader(output2))) {
	    	  int linenumber=0;
	    	  String line1=null;
	      
	    	  while ((line1=brr.readLine()) != null) { 
	    		  linenumber++;
	    	  }

		      System.out.println("result_number 1:"+ linenumber/2);
	      }
	}   

	   

	public static void flush() {
		try {
			if(bw2 !=null) {
				bw2.flush();
				bw2.close();
				}
			if(bw !=null) {
				bw.flush();
				bw.close();
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/*getOutput method에서는 line을 가져와서 start~end로 line을 자르고 
	 * getScore method를 통해 line에 "TATT"/ "ACCC"를 비교하면서 score를 생성
	 * toForcedBinary method를 통해   binary number로 변환
	 * file로 결과를 출력한다.
	 * */
	public static void getOutput(String line, int line_num, BufferedWriter bw2,BufferedWriter bw) {
		
		try {	
			    String fresult1=null;
		
				String zero = "TATT";
			    String one = "ACCC";
		
			    String score = null;
			    
				//start와 end의 위치를 찾아서 line을 start~end 사이로 끊는다.  
			    
				Vector<Integer> startIdx = new Vector<Integer>();
				Vector<Integer> endIdx=new Vector<Integer>();
				
				int start_score=0;
				int end_score=0;
				int num=0;
				int num2=0;
				
				String start_true = start.substring(0, 15);
				
				lineCheck_o(line, start, start_score ,startIdx,num);
						
				String end_true = end.substring(0, 15);
						
				lineCheck_o(line, end, end_score ,endIdx,num2);
						
				if(startIdx.size()==0||endIdx.size()==0) {
					throw new Exception();
				}
						
				int idx_start=(int)startIdx.get(0);
				int idx_end=(int)endIdx.get(endIdx.size()-1); 
						
				if(idx_end>idx_start+start.length()) {
					line=line.substring(idx_start+start.length(),idx_end);
				}
				else {
					throw new Exception();
				}
				
				min_boundary=length-length_range;
				max_boundary=length+length_range;
				//line 길이 제한 (start와 end 사이의 data)
				if(line.length()<min_boundary||line.length()>max_boundary)   //line 길이를 바꿀 때 -> 현재 범위는 600~850
					throw new Exception();
	
				//line을 "TATT"와 "ACCC"를 비교해가면서 score 내기
				score = getScore2(line,zero, one);
				
				//score의 길이가 0이면 넘긴다.
				if (score.length()==0)
					throw new Exception();
				
				//score를 오차범위 0~1인 binary number로 변환
				fresult1 = toForcedBinary1(score) ;
				
				
				if (fresult1==null) {
					throw new Exception();
				}
				
				//file에 결과 출력
				bw2.write(">>"+line_num+ "\r\n");
				bw2.write(fresult1.replaceAll(" ", "")+"\r\n");
			    bw2.flush();
			    
			    bw.write(">> line number:"+line_num+ "\r\n");		
				bw.write( "> between p_F and p_R : " + "\r\n");
				bw.write( line+"\r\n");
				bw.write( "line to binary code error 0-1:"+"\r\n");
				bw.write(score+"\n"+fresult1+"\r\n");
				bw2.flush();
				

		}
		catch(Exception e){
		}
	}
	
	
	//이 method는 두 문자열을 비교해서 오차 문자의 수를 return해준다.
	public static int GetEditDistance(String sourceString, String destinationString) {
		if (sourceString == null || destinationString == null){
	        throw new IllegalArgumentException("String cannot be null"); 
	        
	    }

	    int distance = 0;
	    int sourceLength = sourceString.length() , destLength = destinationString.length();

	    for ( int i=0 ;i < sourceLength && i < destLength ; i++){
	        
	        if (sourceString.charAt(i) != destinationString.charAt(i)){
	           
	            distance++;
	        }
	    }
	    if (destLength !=  sourceLength ){
	        distance += Math.abs(sourceLength - destLength);
	    }
	    return distance;
	  }
	
	
	
	public static int testNWAlignment(String one, String two) {
		AlignmentResult result=NeedlemanWunsch.computeNWAlignment(one,two,new SimpleAlignmentParameters());
		int matches=0;
		int gaps=0;
		String[] alignments=result.getAlignments();
			
		for(int k=0;k<alignments[0].length();k++) {
			if(alignments[0].charAt(k)==alignments[1].charAt(k)) {
				matches++;
			}
			if(alignments[0].charAt(k)=='-'||alignments[1].charAt(k)=='-')
				gaps++;
		}
		return result.getTotalCost();
	}
	
	public static int align(String a, String b) {
		  
		int[][] T = new int[a.length() + 1][b.length() + 1];

	    for (int i = 0; i <= a.length(); i++)
	        T[i][0] = i;

	    for (int i = 0; i <= b.length(); i++)
	        T[0][i] = i;

	    for (int i = 1; i <= a.length(); i++) {
	        for (int j = 1; j <= b.length(); j++) {
	            if (a.charAt(i - 1) == b.charAt(j - 1))
	               T[i][j] = T[i - 1][j - 1];
	            else
	               T[i][j] = Math.min(T[i - 1][j], T[i][j - 1]) + 1;
	        }
	    }

	    StringBuilder aa = new StringBuilder(), bb = new StringBuilder();

	    for (int i = a.length(), j = b.length(); i > 0 || j > 0; ) {
	        if (i > 0 && T[i][j] == T[i - 1][j] + 1) {
	           aa.append(a.charAt(--i));
	           bb.append("-");
	        } else if (j > 0 && T[i][j] == T[i][j - 1] + 1) {
	           bb.append(b.charAt(--j));
	           aa.append("-");
	        } else if (i > 0 && j > 0 && T[i][j] == T[i - 1][j - 1]) {
	           aa.append(a.charAt(--i));
	           bb.append(b.charAt(--j));
	        }
	    }

	    String[] aligned={aa.reverse().toString(), bb.reverse().toString()};
	        
	    int score=GetEditDistance(aligned[0],aligned[1]);
	    
	   // System.out.println(aligned[0]);
	   // System.out.println(aligned[1]);
	   // System.out.println(score);
	    
	    return score;
	}
	
	//line을 zero와 one 서열로 비교해서 오차의 개수를 나열한 score를 return해준다.
	public static String getScore2(String line, String zero, String one ) {
			
		String score = "";
			
		String zeros_ = "";
		String ones_ = "";
		
		
		for( int x=0 ; x< line.length()-3 ; x++) {
					
		  int zero_ = Decoder.getEditDistance(line.substring(x,x+4), zero);
		  int one_ = Decoder.getEditDistance(line.substring(x,x+4), one);
	
		  zeros_ = zeros_+zero_; //""+zero와 line의 오차범위
		  ones_ = ones_ + one_; //""+one과line의 오차범위
	
	  	}
				
		score = zeros_ + "\n" + ones_;
			
		return score;
	}
	public static int min(int a, int b, int c) {
		return Math.min((Math.min(a,b)),c);
	}
	//score에서 0을 찾아서 그에 따른 결과를 출력하게 한다. 최종 결과를 출력하게 해주는 method
	public static String toForcedBinary2 (String score) {
		
		String [] arr = score.split("\n");
		
		String zero_ = arr[0];
		String one_ = arr[1];
		

		//handling too short read
		if( score.length() < 1000 ) { return ""; }

		String result = "      ";
		int num = 13;
		int i=6;
		
		while(i < zero_.length()-5) {	
			if(num==13) {
				if( zero_.charAt(i)=='0') {
					result = result+"0";
					num = 1;
					
				}else if( one_.charAt(i)=='0'){
					result = result+"1";
					num = 1;
					
				}else if( zero_.charAt(i)!='0'&& one_.charAt(i)!='0'){

					int min_zero = 1000;
					int min0_idx = -1;
					int min_one = 1000;
					int min1_idx = -1;
					
					for( int j = 1 ;  j<= error_range ; j++){   //오차범위 변경시 j<=n에서 n을 지정해주면 됩니다

						int tmp0 = Integer.parseInt( zero_.charAt(i+j)+"" );
						
						if( tmp0 < min_zero ) {
							min_zero = tmp0;   
							min0_idx = j;       
						}
						tmp0 = Integer.parseInt( zero_.charAt(i-j)+"" );
						
						if( tmp0 < min_zero ) {//4=4이므로 건너뜀
							min_zero = tmp0;
							min0_idx = (-1)* j;
						}
						
						
						int tmp1 = Integer.parseInt( one_.charAt(i+j)+"" );
						
						if( tmp1 < min_one ) {
							min_one = tmp1;
							min1_idx = j;
						}
						tmp1 = Integer.parseInt( one_.charAt(i-j)+"" );
						if( tmp1 < min_one ) {
							min_one = tmp1;
							min1_idx = (-1)* j;
						}
					}
					
					if( min_one < 1 || min_zero < 1) {
						if( min_one < min_zero) { 
	
							if( i + min1_idx > result.length() ) {
								for( int k=0 ; k< min1_idx ; k ++) {
									result = result+ " ";
								}
								result = result + "1";							
							}else {
								result = result.substring(0, i+min1_idx)+"1";
							}
							
							num = 1;
							i = i + min1_idx;
							
						}else if ( min_zero < min_one) {
							
							if( i + min0_idx > result.length() ) {
								for( int k=0 ; k< min0_idx ; k ++) {
									result = result+ " ";
								}
								result = result + "0";							
							}else {
								result = result.substring(0, i+min0_idx)+"0";
							}
							
							num = 1;
							i = i + min0_idx;
						}
						
						else if(min_one==min_zero) {
							if(Math.abs(min1_idx)<Math.abs(min0_idx)) {
								
								if( i + min1_idx > result.length() ) { //오차범위가 +일 때
									for( int k=0 ; k< min1_idx ; k ++) {
										result = result+ " ";
									}
									result = result + "1";							
								}else { //오차범위가 -일 때
									result = result.substring(0, i+min1_idx)+"1"; 
								}
								num = 1;
								i = i + min1_idx;
							}
							else if(Math.abs(min1_idx)>Math.abs(min0_idx)) {

								if( i + min0_idx > result.length() ) {
									for( int k=0 ; k< min0_idx ; k ++) {
										result = result+ " ";
									}
									result = result + "0";							
								}else {
									result = result.substring(0, i+min0_idx)+"0";
								}
								
								num = 1;
								i = i + min0_idx;
							}
						}
						
					}else {
						result = result + "N";
						num =1 ;
					}
					
				}
				else {
					result = result + " ";
				}
			}else {
				result = result + " ";
				num++;
			}

			i++;
			
		}

		return(result);
	
	}
	
	//score에서 0 또는 1을 찾아서 그에 따른 결과를 출력하게 한다. 최종 결과를 출력하게 해주는 method
	public static String toForcedBinary1 (String score) {
		
		String [] arr = score.split("\n");
		//System.out.println("score: "+score);
		String zero_ = arr[0];
		String one_ = arr[1];
		

		//handling too short read
		if( score.length() < 1000 ) { return ""; }

		String result = "      ";
		int num = 13;
		int i=6;
		
		while(i < zero_.length()-5) {	
			if(num==13) {
				if( zero_.charAt(i)=='0') {
					result = result+"0";
					num = 1;
					
				}else if( one_.charAt(i)=='0'){
					result = result+"1";
					num = 1;
					
				}else if( zero_.charAt(i)!='0'&& one_.charAt(i)!='0'){
					
					if(zero_.charAt(i)=='1') {
						result=result+"0";
						num=1;
					}
					else if(one_.charAt(i)=='1') {
						result=result+"1";
						num=1;
					}
					
					else {
						int min_zero = 1000;
						int min0_idx = 0;
						int min_one = 1000;
						int min1_idx = 0;
						
						for( int j = 1 ;  j<= error_range ; j++){   //오차범위 변경시 j<=n에서 n을 지정해주면 됩니다
	
							int tmp0 = Integer.parseInt( zero_.charAt(i+j)+"" );
							
							if( tmp0 < min_zero ) {
								min_zero = tmp0;   
								min0_idx = j;       
							}
							tmp0 = Integer.parseInt( zero_.charAt(i-j)+"" );
							
							if( tmp0 < min_zero ) {
								min_zero = tmp0;
								min0_idx = (-1)* j;
							}
							
							
							int tmp1 = Integer.parseInt( one_.charAt(i+j)+"" );
							
							if( tmp1 < min_one ) {
								min_one = tmp1;
								min1_idx = j;
							}
							tmp1 = Integer.parseInt( one_.charAt(i-j)+"" );
							if( tmp1 < min_one ) {
								min_one = tmp1;
								min1_idx = (-1)* j;
							}
						}
						
						if( min_one < 2 || min_zero < 2) {
							if( min_one < min_zero) { //min_one이 0이고, min_zero가 1일 때
		
								if( i + min1_idx > result.length() ) { //오차범위가 +일 때
									for( int k=0 ; k< min1_idx ; k ++) {
										result = result+ " ";
									}
									result = result + "1";							
								}else { //오차범위가 -일 때
									result = result.substring(0, i+min1_idx)+"1"; 
								}
								
								num = 1;
								i = i + min1_idx;
								
							}else if ( min_zero < min_one) { //min_one이 1이고, min_zero가 0일 때
								
								if( i + min0_idx > result.length() ) { //오차범위가 +일 때
									for( int k=0 ; k< min0_idx ; k ++) {
										result = result+ " ";
									}
									result = result + "0";							
								}else { //오차범위가 -일 때
									result = result.substring(0, i+min0_idx)+"0";
								}
								
								num = 1;
								i = i + min0_idx;
							}
							
							else if(min_one==min_zero) {
								if(Math.abs(min1_idx)>Math.abs(min0_idx)) {
									
									if( i + min0_idx > result.length() ) { //오차범위가 +일 때
										for( int k=0 ; k< min0_idx ; k ++) {
											result = result+ " ";
										}
										result = result + "0";							
									}else { //오차범위가 -일 때
										result = result.substring(0, i+min0_idx)+"0";
									}
									
									num = 1;
									i = i + min0_idx;
									
								}
								else if(Math.abs(min1_idx)<Math.abs(min0_idx)) {
									
									if( i + min1_idx > result.length() ) { //오차범위가 +일 때
										for( int k=0 ; k< min1_idx ; k ++) {
											result = result+ " ";
										}
										result = result + "1";							
									}else { //오차범위가 -일 때
										result = result.substring(0, i+min1_idx)+"1"; 
									}
									
									num = 1;
									i = i + min1_idx;
								}
							}
							
						}else {
							result = result + "N";
							num =1 ;
						}
					}	
				}
				else {
					result = result + " ";
				}
			}else {
				result = result + " ";
				num++;
			}

			i++;
			
		}

		return(result);
		
	}
	
	
	public static void lineCheck(String line, String page, int score,Vector<Integer> pIdx) {
		
		for( int z=0 ; z< line.length()-page.length()-1 ; z++) {
			
			score=align(line.substring(z, (z+page.length()) ), page);
			
			if( score <= page_mm ) {    // page miss match 설정 -> score<=n 에서 n을 변경
				pIdx.add(z);
			}
		}	
	}
		
	public static void lineCheck_o (String line, String page, int score,Vector<Integer> pIdx,int result) {
		
		for( int z=0 ; z< line.length()-page.length()-1 ; z++) {
			
			score=align(line.substring(z, (z+page.length()) ), page);
			
			if( score <= start_mm ) {    // page miss match 설정 -> score<=n 에서 n을 변경
				pIdx.add(z);
			}
		}	
	}
	
	
	
}

