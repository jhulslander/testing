package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class ReadApacheLog {
    
    public static final String ACCOUNT_LOG_FILE_NAME = "/Users/jdh34/Desktop/bscms-BscAcctDeptResponsibility.txt";
    public static final String ORG_RESPONSIBILITY_LOG_FILE_NAME = "/Users/jdh34/Desktop/bscms-BscKfsOrgResponsibility.txt";
    public static final String SERVICE_CENTER_LOG_FILE_NAME = "/Users/jdh34/Desktop/bscms-BusinessServiceCenter.txt";
    public static final String PORTAL_LOG_FILE_NAME = "/Users/jdh34/Desktop/bscms-portal.txt";
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("People who accessed the BSCMS Legacy FTC/BSC Accounting Department Responsibility Lookup:");
        parseNetIdsFromLogFile(ACCOUNT_LOG_FILE_NAME);
        
        System.out.println("\n\n\n\n People who accessed the BSCMS FTC/BSC KFS Organization Responsibility Lookup:");
        parseNetIdsFromLogFile(ORG_RESPONSIBILITY_LOG_FILE_NAME);
        
        System.out.println("\n\n\n\n People who accessed the BSCMS Financial Transaction/Business Service Center Lookup:");
        parseNetIdsFromLogFile(SERVICE_CENTER_LOG_FILE_NAME);
        
        
        System.out.println("\n\n\n\n People who accessed the BSCMS portal:");
        parseNetIdsFromLogFile(PORTAL_LOG_FILE_NAME);
        
        
        
    }
    
    private static TreeSet<String> parseNetIdsFromLogFile(String fileName) throws IOException {
        TreeSet<String> netIds = new TreeSet<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        int lineCount = 0;
        
        while (line != null) {
            int startPoint = line.indexOf("u:");
            int endPoint = line.indexOf("/d:");
            
            try {
                String netId = line.substring(startPoint + 2, endPoint);
                if (!netIds.contains(netId)) {
                    netIds.add(netId);
                }
            } catch (Exception e) {
                System.err.println("had an error on line " + lineCount + " in file " + fileName);
                //e.printStackTrace();
            }
            
            //System.out.println("startPoint: " + startPoint + "  endPoint: " + endPoint + "  netId: " + netId);
            
            lineCount++;
            line = br.readLine();
        }
        
        System.out.println("lineCount: " + lineCount + " netID count: " + netIds.size());
        netIds.stream().forEach(netid -> System.out.println(netid));
        
        return netIds;
    }

}
