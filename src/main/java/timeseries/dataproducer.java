package timeseries;

/**
 * Created by 11245 on 2018/5/2.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class dataproducer {
    public static void main(String[] args)  {
        String path = "E:\\timeserisedata\\data.txt";
        int dataNum = 100;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(path,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Random rand = new Random();

        for (int i = 0;i<dataNum;i++){
            int length = rand.nextInt(10)+20;
            String str = "";
            for (int j = 0;j<length;j++){
                if (j!=length-1){
                    str += String.valueOf(rand.nextInt(50)+50)+",";
                }else {
                    str += String.valueOf(rand.nextInt(50)+50);
                }
            }
            //System.out.println(str);
            if (i==dataNum){
                pw.print(str);
            }else {
                pw.println(str);
            }

        }
        pw.close();
    }
}
