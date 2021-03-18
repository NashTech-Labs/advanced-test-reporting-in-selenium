package Com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;

public class Content {
    public Content() {
    }
    private static final int FIRSTNAME = 0;
    private static final int LASTNAME = 1;
    private static final int GENDER_INDEX = 2;
    private static final int NUMBER_INDEX = 3;
    private static final int EXPECTED_RESULT = 4;

    private static final List<Integer> testCaseIndex = new ArrayList<>();

    public static Map<SheetColumnHeader, SheetColumnHeader> map = new ConcurrentHashMap<>();

    public List<SheetColumnHeader> readFile() throws Exception {
        List<SheetColumnHeader> sheetColumnHeaders = new ArrayList<>();
        List<Integer> excludelist = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("src//test//java//sheet.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.rowIterator();
            int cellIndex = 0;


            String description = null;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                SheetColumnHeader testcase = new SheetColumnHeader();
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    if (cellIndex == 0) {
                        // for taking the description from sheet we fetch all data Column wise
                        if (cell.getStringCellValue().equalsIgnoreCase("FirstName")) {
                            testCaseIndex.add(FIRSTNAME, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("LastName")) {
                            testCaseIndex.add(LASTNAME, i);
                        }else if (cell.getStringCellValue().equalsIgnoreCase("Gender")) {
                            testCaseIndex.add(GENDER_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Number")) {
                            testCaseIndex.add(NUMBER_INDEX, i);
                        }else if (cell.getStringCellValue().equalsIgnoreCase("Expected result")) {
                            testCaseIndex.add(EXPECTED_RESULT, i);
                        }
                    } else {
                        if (cell==null){
                            continue;
                        }
                        if(!excludelist.contains(i)){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        if (testCaseIndex.get(FIRSTNAME) == i ) {
                            testcase.setFirstname(cell.getStringCellValue());
                        } else if (testCaseIndex.get(LASTNAME) == i ) {
                            testcase.setLastname(cell.getStringCellValue());
                        } else if (testCaseIndex.get(GENDER_INDEX) == i ) {
                            testcase.setGender(cell.getStringCellValue());
                        } else if (testCaseIndex.get(NUMBER_INDEX) == i ) {
                            testcase.setNumber(cell.getStringCellValue());
                        }else if (testCaseIndex.get(EXPECTED_RESULT) == i) {
                            testcase.setExpectedResult(cell.getStringCellValue());
                        }
                    }


                }
                if (cellIndex != 0) {
                    sheetColumnHeaders.add(testcase);
                    map.put(sheetColumnHeaders.get(cellIndex - 1), testcase);
                }
                cellIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetColumnHeaders;
    }

}
