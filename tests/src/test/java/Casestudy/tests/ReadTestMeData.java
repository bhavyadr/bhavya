package Casestudy.tests;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestMeData{
  
  	public static XSSFWorkbook workbook;
  	public static XSSFSheet worksheet;
  	public static DataFormatter formatter=new  DataFormatter();
  	public static Object[][] ReadData() throws IOException
  	{
  	 FileInputStream fileInputStream=new FileInputStream("C:\\driver\\TestMeData.xlsx");
  	 workbook=new XSSFWorkbook(fileInputStream);
  	 worksheet=workbook.getSheet("sheet1");
  	 XSSFRow row=worksheet.getRow(0);
  	 
  	 int rownum=worksheet.getPhysicalNumberOfRows();
  	 int colnum=row.getLastCellNum();
  	 System.out.println("rownum:"+rownum+"\n"+"colnum:"+colnum);
  	 Object Data[][]=new Object[rownum-1][colnum];
  	 for(int i=0;i<rownum-1;i++)
  	 {
  		 XSSFRow Row=worksheet.getRow(i+1);
  		 for(int j=0;j<colnum;j++)
  		 {
  			 XSSFCell cell=Row.getCell(j);
  			 if(cell==null)
  				 Data[i][j]="";
  			 else
  			 {
  				 String value=formatter.formatCellValue(cell);
  				 Data[i][j]=value;
  			 }
  		 }
  	 }
  
  	return Data;
  
  	}
  }
