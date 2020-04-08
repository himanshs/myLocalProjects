package amdocs.dbcompare.report;

import amdocs.dbcompare.loader.PropertiesLoader;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.*;

public class DumpCompareReportGenerator
{

    public DumpCompareReportGenerator()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        try
        {
            System.out.println("Going to Load Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            PropertiesLoader propertiesloader = PropertiesLoader.getInstance();
            String s = propertiesloader.getPropertyValue("dbcompare.database.url");
            String s1 = propertiesloader.getPropertyValue("dbcompare.database.source.userName");
            String s2 = propertiesloader.getPropertyValue("dbcompare.database.source.userPass");
            System.out.println("Going to obtain Connection:"+s1+"/"+s2+"@"+s);
            Connection connection = DriverManager.getConnection(s, s1, s2);
            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String s3 = propertiesloader.getPropertyValue("dbcompare.database.newDump");
            String s4 = propertiesloader.getPropertyValue("dbcompare.database.oldDump");
            String s5 = propertiesloader.getPropertyValue("dbcompare.database.dbLink");
            FileWriter filewriter = new FileWriter(propertiesloader.getPropertyValue("dbcompare.select.script"));
            FileWriter filewriter1 = new FileWriter(propertiesloader.getPropertyValue("dbcompare.report.exceptioalReport"));
            FileWriter filewriter2 = new FileWriter(propertiesloader.getPropertyValue("dbcompare.report.nochange"));
            String s6 = (new StringBuilder()).append(System.getProperty("amdocs.epc"))
            	.append(File.separator).append("public")
	            .append(File.separator).append("properties")
	            .append(File.separator).append("dumpTables.config").toString();
            FileReader filereader = new FileReader(s6);
            List list = selectTableCreation(statement, filewriter, filereader);
            boolean flag = false;
            for(int j = 0; j < list.size(); j++)
            {
                String s7 = (new StringBuilder()).append("").append(list.get(j)).toString();
                System.out.println(s7);
                try
                {
                    String s8 = s7.substring(s7.lastIndexOf(" "), s7.length()).trim().toUpperCase();
                    System.out.println((new StringBuilder()).append("Going to execute Query..").append(getSqlStringForColumnSpecificaton(s7, s5, s3, s4)).toString());
                    ResultSet resultset = statement.executeQuery(getSqlStringForColumnSpecificaton(s7, s5, s3, s4));
                    int i = recordCountinResultSet(resultset);
                    if(i == 0)
                    {
                        filewriter2.write((new StringBuilder()).append(s8).append("\n").toString());
                        continue;
                    }
                    if(i > 30000)
                    {
                        filewriter1.write((new StringBuilder()).append(s8).append("\n").toString());
                    } else
                    {
                        ResultSet resultset1 = statement.executeQuery(getSqlStringForColumnSpecificaton(s7, s5, s3, s4));
                        writeToFile(hssfworkbook, resultset1, s8);
                    }
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append("Exception as::").append(exception1.getMessage()).toString());
                    exception1.printStackTrace();
                }
            }

            FileOutputStream fileoutputstream = new FileOutputStream(propertiesloader.getPropertyValue("dbcompare.report.dumpDifference"));
            hssfworkbook.write(fileoutputstream);
            fileoutputstream.close();
            filewriter1.close();
            filewriter2.close();
            statement.close();
            connection.close();
            System.out.println("Process Finished");
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Exception as ").append(exception.getMessage()).toString());
            exception.printStackTrace();
        }
    }

    public static void writeToFile(HSSFWorkbook hssfworkbook, ResultSet resultset, String s)
        throws Exception
    {
        HSSFSheet hssfsheet = hssfworkbook.createSheet(s);
        HSSFRow hssfrow = hssfsheet.createRow(0);
        ResultSetMetaData resultsetmetadata = resultset.getMetaData();
        for(int i = 0; i < resultsetmetadata.getColumnCount(); i++)
            hssfrow.createCell((short)i).setCellValue(resultsetmetadata.getColumnLabel(i + 1));

        for(int j = 1; resultset.next(); j++)
        {
            HSSFRow hssfrow1 = hssfsheet.createRow((short)j);
            for(int k = 0; k < resultsetmetadata.getColumnCount(); k++)
                hssfrow1.createCell((short)k).setCellValue(resultset.getString(k + 1));

        }

    }

    public static String getSqlStringForColumnSpecificaton(String s, String s1, String s2, String s3)
    {
        String s4 = s.substring(6, s.length()).trim();
        String s5 = (new StringBuilder()).append("Select Distinct  'Not in ").append(s3).append("' as Filter,").append(" ").append(s4).toString();
        String s6 = (new StringBuilder()).append("Select Distinct  'Not in ").append(s2).append("' as Filter,").append(" ").append(s4).toString();
        String s7 = (new StringBuilder()).append("((").append(s5).append(" Minus ").append(s5).append(s1).append(")").toString();
        String s8 = (new StringBuilder()).append("(").append(s6).append(s1).append(" Minus ").append(s6).append("))").toString();
        String s9 = (new StringBuilder()).append(s7).append(" Union ").append(s8).toString();
        s4 = null;
        s5 = null;
        s6 = null;
        return s9;
    }

    public static String writeSelectStatementToFile(String s, ResultSet resultset, FileWriter filewriter)
        throws Exception
    {
        String s1 = "Select ";
        ResultSetMetaData resultsetmetadata = resultset.getMetaData();
        for(int i = 0; i < resultsetmetadata.getColumnCount(); i++)
            if(!resultsetmetadata.getColumnTypeName(i + 1).toUpperCase().equals("CLOB") && 
            		!resultsetmetadata.getColumnTypeName(i + 1).toUpperCase().equals("BLOB") && 
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("SYS_CREATION_DATE") &&
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("SYS_UPDATE_DATE") && 
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("OPERATOR_ID") &&
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("APPLICATION_ID") &&
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("DL_UPDATE_STAMP") &&
            		!resultsetmetadata.getColumnName(i + 1).toUpperCase().equals("DL_SERVICE_CODE"))
                s1 = (new StringBuilder()).append(s1).append(resultsetmetadata.getColumnName(i + 1).toUpperCase()).append(",").toString();

        filewriter.write((new StringBuilder()).append(s1.substring(0, s1.length() - 1)).append(" From ").append(s).append("\n").toString());
        return (new StringBuilder()).append(s1.substring(0, s1.length() - 1)).append(" From ").append(s).toString();
    }

    public static List selectTableCreation(Statement statement, FileWriter filewriter, FileReader filereader)
    {
        ArrayList arraylist = new ArrayList();
        try
        {
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String s;
            while((s = bufferedreader.readLine()) != null) 
            {
                String s1 = (new StringBuilder()).append("Select * from ").append(s).toString();
                try
                {
                    ResultSet resultset = statement.executeQuery(s1);
                    arraylist.add(writeSelectStatementToFile(s, resultset, filewriter));
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append("Exception as:").append(exception1.getMessage()).toString());
                    exception1.printStackTrace();
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Exception caught as::").append(exception.getMessage()).toString());
            exception.printStackTrace();
        }
        return arraylist;
    }

    public static int recordCountinResultSet(ResultSet resultset)
    {
        int i = 0;
        try
        {
            while(resultset.next()) 
                i++;
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Exception obtained as").append(exception.getMessage()).toString());
            exception.printStackTrace();
        }
        return i;
    }
}