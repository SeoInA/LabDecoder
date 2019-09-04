import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
public class Decode_excel {

	public static void main(String[] args) {
		
		try {
			FileInputStream file = new FileInputStream("/home/hdseo/LabDecoder/excel.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(file);

	        int rowindex=0;
	        int rowindex_v=0;
	        int columnindex=1;
	        
	 		String output="";
	        int page_mm=0;
	        int start_mm=0;
	        int len_boundary=0;
	         
	        XSSFSheet sheet=workbook.getSheetAt(0);
	         
	        int rows=sheet.getPhysicalNumberOfRows();
	        System.out.println(rows);
	        for(rowindex=67;rowindex<112;rowindex++){
	        	
	        	System.out.println("rowindex"+rowindex);
	            
	            XSSFRow row=sheet.getRow(rowindex);
	            if(row !=null){
	                
	                int cells=row.getPhysicalNumberOfCells();
	                columnindex=1;

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
	            output="C:\\\\Users\\\\sia\\\\Desktop\\\\reads\\코드 결과\\Align\\excel\\"+rowindex_v+".txt";
	   
	            System.out.println(output);
	            Decode d=new Decode(page_mm,start_mm,len_boundary ,output);
	            d.run();
	 			
	 			System.out.println(rowindex_v+"success");
	         
	        	}
	         
			}catch(Exception e) {
				
		}
	}

}
