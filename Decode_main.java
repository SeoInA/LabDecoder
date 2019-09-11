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
		    	p1_F = "TGCGTTGGGTGTCCGTCAGTCAATTATCAA"; //page ������ ���´�.
		    	p1_R = "TATATCGTACCCGGCGGTACTACTCTCTTA";
		    	insert_len=704;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\one.txt";
		    	break;
		    case 2:
		    	p1_F = "TTTCGATGGATGCCAAATCGTGTTCGCACG";
		    	p1_R = "GGGAGACGTATCTTTAAGGTCATGAACCAC";
		    	insert_len=672;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\two.txt";
		    	break;
		    case 3: 
		    	p1_F = "AACTATGCCGATTCCTATCAATCCGTTAAG"; //page ������ ���´�.
		    	p1_R = "ATTCGTAAGTTGCTACAGCCTTATGGTATG";
		    	insert_len=704;
		    	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\three.txt";
		    	break;
		    case 4:
		    	p1_F = "AGAATAGCCATTGATTACCATCCGTAACCA"; //page ������ ���´�.
			    p1_R = "GACACTGCGATTATAGGCTTACAAGTAATC";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\four.txt";
			    break;
		    case 5:
		    	p1_F = "GTGAGTCTCGTATAGGTCTATAAGAAGTGA"; //page ������ ���´�.
		    	p1_R = "TCAGATCAACCTCTCAACTATACAGATGAT";
			    insert_len=720;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\five.txt";
			    break;
		    case 6: 
		    	p1_F = "AGCTTCCACAATCCAGATTATCGTCGCCAA"; //page ������ ���´�.
			    p1_R = "TGACTTCCTACCTTATCCAGAGACCTGTAG";
			    insert_len=672;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\six.txt";
			    break;
		    case 7: 
		    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA"; //page ������ ���´�.
			    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
			    insert_len=640;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\seven.txt";
			    break;
		    case 8:
		    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA"; //page ������ ���´�.
			    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\eight.txt";
			    insert_len=720;
			    break;
		    case 9:
		    	p1_F = "TGATCATACAGTAGCCTTGTAATGCCGTCA"; //page ������ ���´�.
			    p1_R = "GATACATAAGTTCACCACGCCTCTACAGTA";
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\nine.txt";
			    insert_len=736;
			    break;
		    case 10:
		    	p1_F = "TTCCATATAATACTAACCAGTGCTCTCGGA"; //page ������ ���´�.
			    p1_R = "GTGTCAGTAGGAAGAATAGCAACATTAAGT";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\ten.txt";
			    break;
		    case 11:
		    	p1_F = "ATCCACGATAAGACATCCATAGTTACTACG"; //page ������ ���´�.
			    p1_R = "TCTACACCTCAACTCCTGCACTGTGTGAAT";
			    insert_len=672;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\eleven.txt";
			    break;
		    case 12:
		    	p1_F = "TCGTCCGTAATAATTGGTGGTCTTCATAAG"; //page ������ ���´�.
		    	p1_R = "TAACTTGTACCATAGAGAACCAGGATAGAC";
			    insert_len=704;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\twelve.txt";
			    break;
		    case 13:
		    	p1_F = "TAGGTTGCGTCGATTCATACTTCCTACGAT"; //page ������ ���´�.
			    p1_R = "GATGCTGATTCATTATGCCACTGACCTTCA";
			    insert_len=736;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\thirteen.txt";
			    break;
		    case 14:
		    	p1_F = "TGCTCGGTCTTAGTCTACGTTAAGGTTGAT"; //page ������ ���´�.
			    p1_R = "ACGCTTACTTACGTTATGATGATGTTAAGT";
			    insert_len=720;
			    output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\fourteen.txt";
			    break;
		    case 15 :
		   	  	p1_F = "AGAGATACCTATAGTTCGGTTCTTGCTTAA"; //page ������ ���´�.
		   	  	p1_R = "TCTCATGAATCGCTGCTCTACTAACAACGT";
		   	  	insert_len=800;
		   	  	output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\�ڵ� ���\\Align\\fifteen.txt";
			    break;
			  	  
			default:
				System.out.println("���� PAGE ��ȣ�Դϴ�");
				break;
	      }	
		    
		    Decoder1 d=new Decoder1(p1_F,p1_R,insert_len,output);
		    d.run();
			
			System.out.println(i+"success");
		}
	}

}
