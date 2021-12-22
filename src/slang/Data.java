package slang;

import java.io.*;
import java.util.*;

/**
 * slang
 * Created by Minh Sĩ Lê
 * Date 12/19/2021 - 9:32 PM
 * Description: ...
 */
public class Data {
    private HashMap<String, ArrayList<String>> dict;
    private ArrayList<String> historyList;
    String pathDataRoot = "data/slang.txt";
    String pathDataEdit = "data/slang_edit.txt";
    String pathDataHistory = "data/slang_history.txt";
    boolean isReset = false;

    public Data(){
        dict = new HashMap<>();
        historyList = new ArrayList<>();
        readData(false);
        readHistory();
    }

    public ArrayList<String> getHistoryList() {
        return historyList;
    }

    public void addHistory(String str){
        historyList.add(str);
    }

    private void readHistory() {
        File tempFile = new File(pathDataHistory);
        boolean exists = tempFile.exists();
        if (exists) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(pathDataHistory));
                while (true) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    historyList.add(line);
                }
                br.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
      }
    }

    private void writeHistory(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathDataHistory));
            for (String data: historyList)
                bw.write(data);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    void readData(boolean flag){
        String path;
        if (flag) {
            path = pathDataRoot;
            dict.clear();
        }
        else path = pathDataEdit;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                String[] str = line.split("`");
                if (str.length<2)
                    continue;
                dict.put(str[0], getDefinition(str[1]));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getDefinition(String str) {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(str.split("\\| ")));
        return result;
    }


    public ArrayList<String> search(String str, boolean flag){
        if (flag)
            return dict.get(str.toLowerCase());
        else{
            String[] keys = dict.keySet().toArray(new String[0]);
            ArrayList<String> result = new ArrayList<>();
            for (String key: keys){
                ArrayList<String> meanings = dict.get(key);
                if (meanings != null){
                    for (String value: meanings){
                        if(value.toLowerCase().contains(str.toLowerCase())){
                            result.add(key+": "+ value);
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }

    public void edit(String oldWord, String newWord, String oldDef, String newDef){
        dict.remove(oldWord);
        dict.put(newWord, getDefinition(newDef));
    }

    public static void main(String[] args){
        Data a = new Data();
        a.search("$", true);
        a.search(">.<", true);

    }

    public String wordForToday(){
        Set<String> keySet = dict.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        ArrayList<String> randomValue = dict.get(randomKey);
        return "\t             " + randomKey+ "\n" + toString(randomValue);
    }

    public String toStringOneLine(ArrayList<String> str){
        String res = "";
        for (String s : str)
        {
            res += s + ", ";
        }
        return res;
    }

    public String toString(ArrayList<String> str){
        String res = "";
        for (String s : str)
        {
            res += "- " + s + "\n";
        }
        return res;
    }
}
