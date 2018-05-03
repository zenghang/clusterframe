package timeseries;

import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import net.seninp.jmotif.sax.SAXException;
import net.seninp.jmotif.sax.SAXProcessor;
import net.seninp.jmotif.sax.alphabet.NormalAlphabet;
import net.seninp.jmotif.sax.datastructure.SAXRecords;

import java.io.*;
import java.util.Set;

/**
 * Created by 11245 on 2018/5/2.
 */
public class clustering {
    public static void main(String[] args){
        //1.读取数据转换为字母，计算距离，进行聚类
        readFromfile();
    }
    private static void readFromfile(){
        String readParh = "E:\\timeserisedata\\data.txt";
        int dataNum = 100;
        int num = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(readParh)));
            while (num < dataNum){
                String s = bf.readLine();
                String[] strs = s.split(",");
                int length = strs.length;
                double[] tsRed = new double[length];
                for (int i = 0;i<length;i++){
                    tsRed[i] = Integer.valueOf(strs[i]);
                }
                //将一维序列通过SAX算法转换，返回SAX记录值
                SAXAnalysisWindow window = new SAXAnalysisWindow(length,length,4);//保持一致
                int slidingWindowSize = window.getnLength();
                int paaSize = window.getwSegment();
                int alphabetSize = window.getaAlphabet();
                NumerosityReductionStrategy nrStrategy = NumerosityReductionStrategy.NONE;
                double nThreshold = 0.1;

                NormalAlphabet na = new NormalAlphabet();
                SAXProcessor sp = new SAXProcessor();

                SAXRecords res = null;
                res = sp.ts2saxViaWindow(tsRed, slidingWindowSize, paaSize,
                        na.getCuts(alphabetSize), nrStrategy, nThreshold);
                String alphabet = new String();
                Set<Integer> index = res.getIndexes();
                for (Integer idx : index){
                    alphabet += String.valueOf(res.getByIndex(idx).getPayload());
                    //转化为
                    System.out.println(alphabet);
                }
                num++;
                System.out.println(num);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
