package amdocs.dbcompare.test;

import java.io.*;
import java.sql.*;

public class ColumnNameTypeFinder
{

    public ColumnNameTypeFinder()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        FileWriter filewriter = new FileWriter("SelectScriptDumpTables.txt");
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String s = "jdbc:oracle:thin:@indlin080:1521:VELABP3";
            String s1 = "VCLDBO15";
            String s2 = "VCLDBO15";
            Connection connection = DriverManager.getConnection(s, s1, s2);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            FileReader filereader = new FileReader("dumpTables.txt");
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String s3;
            while((s3 = bufferedreader.readLine()) != null) 
            {
                String s4 = (new StringBuilder()).append("Select * from ").append(s3).toString();
                try
                {
                    ResultSet resultset = statement.executeQuery(s4);
                    System.out.println("Result Set Obtained");
                    writeSelectStatementToFile(s3, resultset, filewriter);
                }
                catch(Exception exception1) { }
            }
            filewriter.close();
            statement.close();
            connection.close();
            System.out.println("Process Finished");
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public static void writeSelectStatementToFile(String s, ResultSet resultset, FileWriter filewriter)
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
    }
}