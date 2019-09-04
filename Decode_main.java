import java.io.FileNotFoundException;
import java.io.IOException;

public class Decode_main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String p1_F="";
		String p1_R="";
		int insert_len=0;
		String output="";
		
		for(int i=1; i<=15;i++) {
			
		    switch(i) {
		    case 1: 
		    	p1_F = "TGCGTTGGGTGTCCGTCAGTCAATTATCAA"; 
		    	p1_R = "TATATCGTACCCGGCGGTACTACTCTCTTA";
		    	insert_len=704;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\one.txt";
		    	break;
		    case 2:
		    	p1_F = "TTTCGATGGATGCCAAATCGTGTTCGCACG";
		    	p1_R = "GGGAGACGTATCTTTAAGGTCATGAACCAC";
		    	insert_len=672;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\two.txt";
		    	break;
		    case 3: 
		    	p1_F = "AACTATGCCGATTCCTATCAATCCGTTAAG"; 
		    	p1_R = "ATTCGTAAGTTGCTACAGCCTTATGGTATG";
		    	insert_len=704;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\three.txt";
		    	break;
		    case 4:
		    	p1_F = "AGAATAGCCATTGATTACCATCCGTAACCA"; 
			    p1_R = "GACACTGCGATTATAGGCTTACAAGTAATC";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\four.txt";
			    break;
		    case 5:
		    	p1_F = "GTGAGTCTCGTATAGGTCTATAAGAAGTGA";
		    	p1_R = "TCAGATCAACCTCTCAACTATACAGATGAT";
			    insert_len=720;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\five.txt";
			    break;
		    case 6: 
		    	p1_F = "AGCTTCCACAATCCAGATTATCGTCGCCAA"; 
			    p1_R = "TGACTTCCTACCTTATCCAGAGACCTGTAG";
			    insert_len=672;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\six.txt";
			    break;
		    case 7: 
		    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA"; 
			    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
			    insert_len=640;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\seven.txt";
			    break;
		    case 8:
		    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA"; 
			    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\eight.txt";
			    insert_len=720;
			    break;
		    case 9:
		    	p1_F = "TGATCATACAGTAGCCTTGTAATGCCGTCA";
			    p1_R = "GATACATAAGTTCACCACGCCTCTACAGTA";
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\nine.txt";
			    insert_len=736;
			    break;
		    case 10:
		    	p1_F = "TTCCATATAATACTAACCAGTGCTCTCGGA"; 
			    p1_R = "GTGTCAGTAGGAAGAATAGCAACATTAAGT";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\ten.txt";
			    break;
		    case 11:
		    	p1_F = "ATCCACGATAAGACATCCATAGTTACTACG";
			    p1_R = "TCTACACCTCAACTCCTGCACTGTGTGAAT";
			    insert_len=672;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\eleven.txt";
			    break;
		    case 12:
		    	p1_F = "TCGTCCGTAATAATTGGTGGTCTTCATAAG"; 
		    	p1_R = "TAACTTGTACCATAGAGAACCAGGATAGAC";
			    insert_len=704;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\twelve.txt";
			    break;
		    case 13:
		    	p1_F = "TAGGTTGCGTCGATTCATACTTCCTACGAT"; 
			    p1_R = "GATGCTGATTCATTATGCCACTGACCTTCA";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\thirteen.txt";
			    break;
		    case 14:
		    	p1_F = "TGCTCGGTCTTAGTCTACGTTAAGGTTGAT";
			    p1_R = "ACGCTTACTTACGTTATGATGATGTTAAGT";
			    insert_len=720;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\fourteen.txt";
			    break;
		    case 15 :
		   	  	p1_F = "AGAGATACCTATAGTTCGGTTCTTGCTTAA"; 
		   	  	p1_R = "TCTCATGAATCGCTGCTCTACTAACAACGT";
		   	  	insert_len=800;
		   	  	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\fifteen.txt";
			    break;
			  	  
			default:
				System.out.println("없는 PAGE 번호입니다");
				break;
	      }	
		    
		    Decoder1 d=new Decoder1(p1_F,p1_R,insert_len,output);
		    d.run();
			
			System.out.println(i+"success");
		}
	}

}
