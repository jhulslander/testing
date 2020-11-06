package testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestSQLParsing {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("/Users/jdh34/Downloads/CG_PRPSL_T.csv"));
        PrintWriter writer1 = new PrintWriter("outputUpdateSQL.sql", "UTF-8");

        String line = br.readLine();

        try {
            StringBuilder sb = new StringBuilder();
            line = br.readLine();
            int count = 1;
            while (line != null) {
                System.out.println("Line Number " + count + ": " + line);
                // UPDATE KFS.CG_PRPSL_T SET CGPRPSL_SUBMSSN_DT = TO_DATE('12/22/11',
                // 'MM/DD/YY'), LAST_UPDT_TS = SYSDATE WHERE CGPRPSL_NBR = '66133';

                String[] lineSplitArray = line.split(",");
                String proposalNumber = lineSplitArray[0];
                String submissionDate = lineSplitArray[1];

                sb.append("UPDATE KFS.CG_PRPSL_T SET CGPRPSL_SUBMSSN_DT = TO_DATE('").append(submissionDate);
                sb.append("', 'MM/DD/YY'), LAST_UPDT_TS = SYSDATE WHERE CGPRPSL_NBR = '").append(proposalNumber)
                        .append("';");
                writer1.println(sb.toString());
                sb = new StringBuilder();

                count++;

                if (count % 1000 == 0) {
                    writer1.println("COMMIT;");
                }

                line = br.readLine();
            }

            if (count % 1000 != 0) {
                writer1.println("COMMIT;");
            }

        } finally {
            br.close();
            writer1.close();

        }

    }

}
