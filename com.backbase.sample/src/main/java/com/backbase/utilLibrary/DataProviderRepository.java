package com.backbase.utilLibrary;


import org.testng.annotations.DataProvider;
import com.backbase.baseSetUp.BaseSetUp;


public class DataProviderRepository extends BaseSetUp{
	

	static ExcelReader allURIfileName = null;
    static String SheetName = null;
    static String testDataFilePath=System.getProperty("user.dir" + "\\Test Excel Files\\NewComputerTestData.xlsx");
    ExcelReader excel1=new ExcelReader(testDataFilePath);
    
    @DataProvider(name="newComputerData")
    public static Object[][] getNewCompData(){
        System.out.println("Inside new computer data provider");
		String[][] data = getExcelData1("NewComputerTestData.xlsx", "NewComputerAddition");
    	return data;
    }
    
    @DataProvider(name="existingCompData")
    public static Object[][] getExistingComputerData(){
        System.out.println("Inside data provider for exiting computer dataprovider");
		String[][] data = getExcelData1("SearchComputerTestData.xlsx", "ExistingComputers");
    	return data;
    }
    

    @DataProvider(name="nonExistingCompData")
    public static Object[][] getNonExistingComputerData(){
        System.out.println("Inside data provider for exiting computer dataprovider");
		String[][] data = getExcelData1("SearchComputerTestData.xlsx", "NonExistingComputers");
    	return data;
    }
 
    @DataProvider(name="deleteTestData")
    public static Object[][] getdeleteTestData(){
        System.out.println("Inside data provider for delete computer test data dataprovider");
		String[][] data = getExcelData1("DeleteTestData.xlsx", "DeleteData");
    	return data;
    }
 
    
    public Object [][] getExcelData(String fileName, String sheetName) {
        int rows=excel1.getRowCount(sheetName);
        int cols=excel1.getColumnCount(sheetName);
        Object data [][]=new Object[rows-1][cols];
        for (int rowNum=1;rowNum<rows;rowNum++)
        {

            for (int colNum=0;colNum<cols;colNum++)
            {
                data [rowNum-1][colNum]=excel1.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;
    }

    

}
