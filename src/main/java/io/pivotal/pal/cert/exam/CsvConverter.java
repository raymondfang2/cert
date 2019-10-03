package io.pivotal.pal.cert.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;


/*
To convert Csv to List  and vice verser
Since our csv is like following,
'field1','field2','filed3'
we use seperator "','" 3 chars in the double quote
Not using JackSon CSV Mapper, use String split directly to handle leading and ending extra "'"
 */
@Component
public class CsvConverter {
    Logger logger = LoggerFactory.getLogger(CsvConverter.class);

    @Value("${csv.seperator:','}")
    public String seperator;
    @Value("${csv.trimLeadEndChar:true}")
    public boolean trimLeadEndChar;

    private String[] CSVHeader = {"FIRST_NAME",
            "LAST_NAME",
            "IE_CANDIDATE_ID",
            "CANDIDATE_EMAIL",
            "COMPANY_NAME",
            "TITLE",
            "FULL_NAME",
            "HOME_ADDRESS_1",
            "HOME_ADDRESS_2",
            "CITY",
            "STATE_PROVINCE",
            "ZIP",
            "COUNTRY_CODE",
            "PHONE",
            "SPONSOR_NAME",
            "EXAM_NAME",
            "EXAM_LMS_ID",
            "EXAM_CODE",
            "EXAM_DURATION",
            "TRANSACTION_DATE",
            "ADMIN_DATE",
            "EXAM_START_DATE_TIME",
            "EXAM_COMP_DATE_TIME",
            "DELIVERY_COUNTRY",
            "EXAM_STATUS",
            "ATTEMP_NO",
            "EXAM_SCORE",
            "EXAM_PERCENTAGE",
            "EXAM_RESULT",
            "CONFIRMATION_CODE",
            "PROMO_CODE",
            "PAYMENT_METHOD",
            "TAXES",
            "EXAM_FEE",
            "PAYMENT_AMOUNT",
            "PO_BILLING_TYPE",
            "ECREDIT_CODE",
            "RESERVATION_GUID",
            "AGREE_CHECK_DATE_TIME"};

    public List<HashMap<String,String>> csvSplit(List<String> csv) {
        //Make sure the csv format and order of fields are consistent

        List<HashMap<String, String>> result = new ArrayList<>();
        String line;
        //skip the first line which is the csv header from source
        for (int i=1; i<csv.size(); i++) {
            line = csv.get(i);
            if (trimLeadEndChar) line = line.substring(1,line.length()-1);
            String[] fields = line.split(seperator);
            LinkedHashMap<String,String> record = new LinkedHashMap();
            for (int j=0; j<fields.length; j++) {
                if ((fields[j].trim().length()==0)
                        ||(fields[j].trim().equalsIgnoreCase("null"))
                        ||(fields[j].trim().equalsIgnoreCase("N/A")))
                    record.put(CSVHeader[j],null); //handle null case
                else
                    record.put(CSVHeader[j],fields[j].trim());
            }
            result.add(record);
        }

        return result;
    }

    /*
        return csvList contains the head which is fetched from DB meta data -> HashMap key
     */
    public List<String> csvGenerate(List<HashMap> kvRecords) {
        List<String> result = new ArrayList<>();
        //Header preparation
        HashMap first = kvRecords.get(0);
        StringBuffer header = new StringBuffer();
        if(trimLeadEndChar) header.append("'"); //starting
        first.forEach((k,v)->{header.append(k).append(seperator);});
        //remove the last seperator
        header.setLength(header.length()-seperator.length());
        if(trimLeadEndChar) header.append("'"); //ending
        result.add(header.toString());
        //the result of lines
        for (HashMap record: kvRecords) {
            StringBuffer line = new StringBuffer();
            if(trimLeadEndChar) line.append("'"); //starting
            record.forEach((k,v)->{line.append(v).append(seperator);});
            line.setLength(line.length()-seperator.length());
            if(trimLeadEndChar) line.append("'"); //ending
            result.add(line.toString());
        }
        return result;
    }

    public static void main(String[] argv) {
        List<String> testCSV = new ArrayList<>();

        String s1 = "'Firas','Abughazaleh','89184','fabug@allstate.com','Allstate Insurance Company','N/A','Firas Abughazaleh','null','null','null','','null','US','null','Pivotal','Enterprise Spring V4.2','null','EDU-1120-APP5','90','5/24/2017 12:49 PM','5/31/2017 02:00 PM','','','US','Completed','N/A','','76','Passed','CD7-9CB','N/A','None','N/A','$0','$0','null','N/A','9e5423b7-7c30-4d83-9de0-a556648b9c44','5/31/2017 01:46 PM'";
        String s2 = "‘Susan','Abraham','148137','susaneve.abraham@gmail.com','Capgemini Inc.','N/A','Susan Eve Abraham','null','null','null','','null','US','null','Pivotal','Pivotal Cloud Foundry Developer 1.11','null','EDU-1120-PCF7','1817','1/19/2018 12:13 AM','1/22/2018 02:00 PM','1/19/2018 12:15 AM','1/22/2018 02:33 PM','US','Completed','N/A','46','93','Passed','9AE-ADC','N/A','None','N/A','$0','$0','null','N/A','ff38df1b-ade0-4c6e-975f-557d6e4dd9f8','1/22/2018 01:55 PM'";
        String s3 = "'Kumar','Abhinav','111733','kumarabhinav10@gmail.com','Syntel Pvt. Ltd.','N/A','Kumar Abhinav','null','null','null','','null','IN','null','Pivotal','Pivotal Cloud Foundry Developer 1.7','null','EDU-1120-PCF6','90','6/13/2017 09:37 AM','6/28/2017 05:30 AM','','','US','Completed','N/A','','94','Passed','672-B11','N/A','None','N/A','$0','$0','null','N/A','24c360de-1552-4f51-80f8-e86857f8f11a','6/28/2017 05:21 AM'";
        testCSV.add(s2);
        testCSV.add(s1);
        testCSV.add(s3);

        CsvConverter con = new CsvConverter();
        con.trimLeadEndChar=true;
        con.seperator="','";
        List<HashMap<String, String>> result = con.csvSplit(testCSV);
        for(HashMap record: result) {
            System.out.println("===>"+record.toString());
        }

        /*
        List<String> back2CSV = con.csvGenerate(result);
        System.out.println("----------------------");
        for (String line: back2CSV) {
            System.out.println("===>"+line.toString());
        }
        */


     }

}
