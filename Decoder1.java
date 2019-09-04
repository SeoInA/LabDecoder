import java.io.*;
import java.util.Vector;

public class Decoder1 {

	
	public static String p_F = "TGCGTTGGGTGTCCGTCAGTCAATTATCAA"; 
	public static String p_R = "TATATCGTACCCGGCGGTACTACTCTCTTA";

	public static String start = "CAGTCGCTCCACAAGTACCAGCCTCGTCTCCACAT";
	public static String end = "CCGTCAATCTTGCCATTGCTTGCTCAATCATATCC";
	
	static String file = "C:\\Users\\sia\\Desktop\\reads\\Hunmin_Total2_High_0819//20min_load//Merge Output File.txt";
	//static String file = "C:\\Users\\sia\\Desktop\\reads\\practice2.txt";
	//static String file = "C:\\Users\\sia\\Desktop\\reads\\Merged_hunmin_total_0530.txt";
	
	
    static String output; 
    static String line_1=null;

	public static BufferedWriter bw2;

	static int min_boundary;  
	static int max_boundary;
	static int error_range=6;    
	static int page_mm=5;         // page miss match
	static int start_mm=20;        // start, end miss match   
	static int length=0;
	static int length_range=30;
	
	public Decoder1(String p_F, String p_R, int length,String output) {
		this.p_F=p_F;
		this.p_R=p_R;
		this.length=length;
		this.output=output;
	}
	
	public void run() throws FileNotFoundException, IOException {  

	      int line_num=0;
	      int count = 0;
	      
	      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	  String line=null;
	    	 
	    	  int s_score1=0, s_score2=0;
	    	 
	    	  bw2= new BufferedWriter( new FileWriter(output));

	    	  while ((line=br.readLine()) != null) { 
    			  
	    		  int idx1 = 0;//line.indexOf(p1_F) ; 
	    		  int idx2 = 0;// line.lastIndexOf(p1_R) ;
	    		  
	    		  line_num++;
	    		  
	    		  if(line_num%100000==0) {  
	    			  System.out.println("line number: "+line_num);
	    		  
	    		  }
	    		  
	    		  if( line.length() < 5*p_F.length() ) { 
	    			  continue;
	    		  }
	    		  
	    		  int check = line.charAt(0);
	    		  if(65>check||check>90)
	    			  continue;
	

	    		Vector<Integer> pIdx1 = new Vector<Integer>();
	    		Vector<Integer> pIdx2 = new Vector<Integer>();

					
				String p1_F1 = p_F.substring(0, 15);
					
				lineCheck(line, p1_F1, s_score1 ,pIdx1); 
					
   				String p1_R1 = p_R.substring(0, 15);
					
				lineCheck(line, p1_R1, s_score2 ,pIdx2);
					
				if(pIdx1.size()==0||pIdx2.size()==0) { 
				    	continue; 
				}
					
				idx1=(int)pIdx1.get(0); 
				idx2=(int)pIdx2.get(pIdx2.size()-1);
				
				if(idx2>idx1+p_F.length()) {  
					line_1 = line.substring(idx1+p_F.length(),idx2); 
				}
				else {
					continue;
				}
				
				count++;
				//result
				getOutput(line_1,line_num,bw2);
		
	   	  }
	    	  System.out.println("count: "+count);  
	  }
	      try (BufferedReader brr = new BufferedReader(new FileReader(output))) {
	    	  int linenumber=0;
	    	  String line1=null;
	      
	    	  while ((line1=brr.readLine()) != null) { 
	    		  linenumber++;
	    	  }

		      System.out.println("result_number:"+ linenumber/2);
	      }
	     
	}   

	   

	public static void flush() {
		try {
			if(bw2 !=null) {
				bw2.flush();
				bw2.close();
				
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public static void getOutput(String line, int line_num, BufferedWriter bw2) {
		
		try {	
			    String fresult1=null;
		
				String zero = "TATT";
			    String one = "ACCC";
		
			    String score = null;
			  
			    
				Vector<Integer> startIdx = new Vector<Integer>();
				Vector<Integer> endIdx=new Vector<Integer>();
				
				int start_score=0;
				int end_score=0;
				
				String start_true = start.substring(0, 15);
				
				lineCheck_o(line, start, start_score ,startIdx);
						
				String end_true = end.substring(0, 15);
						
				lineCheck_o(line, end, end_score ,endIdx);
						
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
				
				if(line.length()<min_boundary||line.length()>max_boundary)   
					throw new Exception();
	
				
				score = getScore2(line,zero, one);
			
				if (score.length()==0)
					throw new Exception();
				
				fresult1 = toForcedBinary1(score) ;
				
		
			
				if (fresult1==null) {
					throw new Exception();
				}
				
				
				bw2.write(">>"+line_num+ "\r\n");
				bw2.write(fresult1.replaceAll(" ", "")+"\r\n");
				
			    bw2.flush();
		}
		catch(Exception e){
		}
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
	        } 
	        else if (j > 0 && T[i][j] == T[i][j - 1] + 1) {
	           bb.append(b.charAt(--j));
	           aa.append("-");
	        } 
	        else if (i > 0 && j > 0 && T[i][j] == T[i - 1][j - 1]) {
	           aa.append(a.charAt(--i));
	           bb.append(b.charAt(--j));
	        }
	    }

	    String[] aligned={aa.reverse().toString(), bb.reverse().toString()};
	        
	    int score=GetEditDistance(aligned[0],aligned[1]);
	    
	    	
	    return score;
	}
	
	
	
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
	
	public static String getScore2(String line, String zero, String one ) {
			
		String score = "";
			
		String zeros_ = "";
		String ones_ = "";
		
		
		for( int x=0 ; x< line.length()-3 ; x++) {
					
		  int zero_ = GetEditDistance(line.substring(x,x+4), zero);
		  int one_ = GetEditDistance(line.substring(x,x+4), one);
	
		  zeros_ = zeros_+zero_; 
		  ones_ = ones_ + one_; 
	
	  	}
				
		score = zeros_ + "\n" + ones_;
			
		return score;
	}

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
						
						for( int j = 1 ;  j<= error_range ; j++){
	
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
								if(Math.abs(min1_idx)>Math.abs(min0_idx)) {
									
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
								else if(Math.abs(min1_idx)<Math.abs(min0_idx)) {
									
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
			score = GetEditDistance(line.substring(z, (z+page.length()) ), page);
			
			if( score <= page_mm ) {    
				pIdx.add(z);
			}
		}	
	}
		
	public static void lineCheck_o (String line, String page, int score,Vector<Integer> pIdx) {
		
		for( int z=0 ; z< line.length()-page.length()-1 ; z++) {
			score = align(line.substring(z, (z+page.length()) ), page);
			
			if( score <= start_mm ) {     
				pIdx.add(z);
			}
		}	
	}
}

