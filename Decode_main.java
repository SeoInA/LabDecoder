import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;

public class Decode_main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		try {
			FileInputStream file = new FileInputStream("/home/hdseo/LabDecoder/조합시도.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        
	        int rowindex=0;
	        int rowindex_v=0;
	        int columnindex=0;
	        
	 		String output="";
	        int page_mm=0;
	        int start_mm=0;
	        int len_boundary=0;
	        int bit=0;
	        int c=0;
	        
	        XSSFSheet sheet=workbook.getSheetAt(0);
	         
	        int rows=sheet.getPhysicalNumberOfRows();
	        
	        for(rowindex=3;rowindex<78;rowindex++){
	            XSSFRow row=sheet.getRow(rowindex);
	            if(row !=null){
	               
	                int cells=row.getPhysicalNumberOfCells();
	                columnindex=0;

	                if(columnindex==0){
	                   
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    
	                    if(cell==null){
	                        continue;
	                    }else{
	                       
	                        switch (cell.getCellType()){
	                        	case FORMULA:
		                           break;
	                        	case NUMERIC:
		                    	   value=(int) cell.getNumericCellValue();
		                           break;
	                        	case STRING:
		                    	   break;
	                        	case BLANK:
		                           break;
	                        	case ERROR:
		                           break;
	                        	default:
	                        		break;
	                        }
	                    }
	                    c=value;
                        columnindex++;  
	                }
	                
	                if(columnindex==1){
	                    
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    
	                    if(cell==null){
	                        continue;
	                    }else{
	                       
	                        switch (cell.getCellType()){
	                        	case FORMULA:
		                           break;
	                        	case NUMERIC:
		                           value=(int) cell.getNumericCellValue();
		                           break;
	                        	case STRING:
		                           break;
	                        	case BLANK:
		                           break;
	                        	case ERROR:
		                           break;
	                        	default:
	                        		break;
	                        }
	                    }
	                    len_boundary=value;
                        columnindex=columnindex+2;  
	                }
	                
	               if(columnindex==3){
	                    
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    
	                    if(cell==null){
	                        continue;
	                    }else{
	                       
	                        switch (cell.getCellType()){
	                        	case FORMULA:
		                           break;
	                        	case NUMERIC:
		                           value=(int) cell.getNumericCellValue();
		                           break;
	                        	case STRING:
		                           break;
	                        	case BLANK:
		                           break;
	                        	case ERROR:
		                           break;
	                        	default:
	                        		break;
	                        }
	                    }
	                    page_mm=value;
                        columnindex++;  
	                }
	                
	                if(columnindex==4){
	                    
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                  
	                    if(cell==null){
	                        continue;
	                    }else{
	                      
	                        switch (cell.getCellType()){
	                        	case FORMULA:
		                           break;
	                        	case NUMERIC:
		                           value=(int) cell.getNumericCellValue();
		                           break;
	                        	case STRING:
		                           break;
	                        	case BLANK:
		                           break;
	                        	case ERROR:
		                           break;
	                        	default:
	                        		break;
	                        }
	                    } 
	                    start_mm=value;
                        
	                }
	             }
	        rowindex_v=rowindex+1;
	          
	        
			String p1_F="";
			String p1_R="";
			int insert_len=0;
			
			switch(c) {
				case 1: 
			    	p1_F = "TGCGTTGGGTGTCCGTCAGTCAATTATCAA"; 
			    	p1_R = "TATATCGTACCCGGCGGTACTACTCTCTTA";
			    	insert_len=704;
			    	bit=44;
			    	break;
			    case 2:
			    	p1_F = "TTTCGATGGATGCCAAATCGTGTTCGCACG";
			    	p1_R = "GGGAGACGTATCTTTAAGGTCATGAACCAC";
			    	insert_len=672;
			    	bit=42;
			    	break;
			    case 3: 
			    	p1_F = "AACTATGCCGATTCCTATCAATCCGTTAAG"; 
			    	p1_R = "ATTCGTAAGTTGCTACAGCCTTATGGTATG";
			    	insert_len=704;
			    	bit=44;
			    	break;
			    case 4:
			    	p1_F = "AGAATAGCCATTGATTACCATCCGTAACCA"; 
				    p1_R = "GACACTGCGATTATAGGCTTACAAGTAATC";
				    insert_len=736;
				    bit=46;
				    break;
			    case 5:
			    	p1_F = "GTGAGTCTCGTATAGGTCTATAAGAAGTGA";
			    	p1_R = "TCAGATCAACCTCTCAACTATACAGATGAT";
				    insert_len=720;
				    bit=45;
				    break;
			    case 6: 
			    	p1_F = "AGCTTCCACAATCCAGATTATCGTCGCCAA"; 
				    p1_R = "TGACTTCCTACCTTATCCAGAGACCTGTAG";
				    insert_len=672;
				    bit=42;
				    break;
			    case 7: 
			    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA";
				    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
				    insert_len=640;
				    bit=40;
				    break;
			    case 8:
			    	p1_F = "ATTGAACATCAACTCGTCCATCGCTGATTA"; 
				    p1_R = "AGTCTCACTGCTTATAATTACTTACTGTCT";
				    insert_len=720;
				    bit=45;
				    break;
			    case 9:
			    	p1_F = "TGATCATACAGTAGCCTTGTAATGCCGTCA"; 
				    p1_R = "GATACATAAGTTCACCACGCCTCTACAGTA";
				    insert_len=736;
				    bit=46;
				    break;
			    case 10:
			    	p1_F = "TTCCATATAATACTAACCAGTGCTCTCGGA"; 
				    p1_R = "GTGTCAGTAGGAAGAATAGCAACATTAAGT";
				    insert_len=736;
				    bit=46;
				    break;
			    case 11:
			    	p1_F = "ATCCACGATAAGACATCCATAGTTACTACG"; 
				    p1_R = "TCTACACCTCAACTCCTGCACTGTGTGAAT";
				    insert_len=672;
				    bit=42;
				    break;
			    case 12:
			    	p1_F = "TCGTCCGTAATAATTGGTGGTCTTCATAAG"; 
			    	p1_R = "TAACTTGTACCATAGAGAACCAGGATAGAC";
				    insert_len=704;
				    bit=44;
				    break;
			    case 13:
			    	p1_F = "TAGGTTGCGTCGATTCATACTTCCTACGAT"; 
				    p1_R = "GATGCTGATTCATTATGCCACTGACCTTCA";
				    insert_len=736;
				    bit=46;
				    break;
			    case 14:
			    	p1_F = "TGCTCGGTCTTAGTCTACGTTAAGGTTGAT";
				    p1_R = "ACGCTTACTTACGTTATGATGATGTTAAGT";
				    insert_len=720;
				    bit=45;
				    break;
			    case 15 :
			   	  	p1_F = "AGAGATACCTATAGTTCGGTTCTTGCTTAA";
			   	  	p1_R = "TCTCATGAATCGCTGCTCTACTAACAACGT";
			   	  	insert_len=800;
			   	  	bit=50;
				    break;
				  	  
				default:
					System.out.println("없는 PAGE 번호입니다");
					break;
			      }	
				output="/home/hdseo/result/"+rowindex_v+".txt";
					   
				System.out.println("C"+c+" : " );
				Decoder1 d=new Decoder1(page_mm,start_mm,len_boundary,bit,p1_F,p1_R,insert_len,output);
				d.run();
					
				System.out.println(rowindex_v+"success");
			}
		}
		catch(Exception e) {}
	}
	

}

