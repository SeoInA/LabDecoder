import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
public class Decode_excel {

	public static void main(String[] args) {
		
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\sia\\Desktop\\reads\\코드 결과\\Align\\조합 시도 -0829 try (1).xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(file);

	        int rowindex=0;
	        int rowindex_v=0;
	        int columnindex=1;
	        
	 		String output="";
	        int page_mm=0;
	        int start_mm=0;
	        int len_boundary=0;
	         //시트 수 (첫번째에만 존재하므로 0을 준다)
	         //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
	        XSSFSheet sheet=workbook.getSheetAt(0);
	         //행의 수
	        int rows=sheet.getPhysicalNumberOfRows();
	        System.out.println(rows);
	        for(rowindex=67;rowindex<112;rowindex++){
	        	
	        	System.out.println("rowindex"+rowindex);
	            //행을읽는다
	            XSSFRow row=sheet.getRow(rowindex);
	            if(row !=null){
	                //셀의 수
	                int cells=row.getPhysicalNumberOfCells();
	                columnindex=1;

	                if(columnindex==1){
	                    //셀값을 읽는다
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    //셀이 빈값일경우를 위한 널체크
	                    if(cell==null){
	                        continue;
	                    }else{
	                        //타입별로 내용 읽기
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
	                    //셀값을 읽는다
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    //셀이 빈값일경우를 위한 널체크
	                    if(cell==null){
	                        continue;
	                    }else{
	                        //타입별로 내용 읽기
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
	                    //셀값을 읽는다
	                    XSSFCell cell=row.getCell(columnindex);
	                    int value=0;
	                    //셀이 빈값일경우를 위한 널체크
	                    if(cell==null){
	                        continue;
	                    }else{
	                        //타입별로 내용 읽기
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
