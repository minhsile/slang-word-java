package slang;

import java.io.*;
import java.util.*;

/**
 * slang
 * Created by Minh Sĩ Lê
 * Date 12/19/2021 - 9:32 PM
 * Description: Data of dictionary
 */
public class Data {
    private static HashMap<String, ArrayList<String>> dict;
    private final ArrayList<String> historyList;
    private static final String pathDataEdit = "data/slang_edit.txt";
    private static final String pathDataHistory = "data/slang_history.txt";

    /**
     * Constructor
     */
    public Data(){
        dict = new HashMap<>();
        historyList = new ArrayList<>();
        readData(false);
        readHistory();
    }

    /**
     * Get history list
     * @return ArrayList
     */
    public ArrayList<String> getHistoryList() {
        writeHistory();
        return historyList;
    }

    /**
     * Add searched word to history list
     * @param str
     */
    public void addHistory(String str){
        historyList.add(str);
    }

    /**
     * read history list from file
     */
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

    /**
     * Write history list to file
     */
    private void writeHistory(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathDataHistory));
            for (String data: historyList)
                bw.write(data+"\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read data of dictionary file
     * @param isReset use root file or edited file
     */
    public void readData(boolean isReset){
        String path;
        if (isReset) {
            path = "data/slang.txt";
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

    /**
     * Write edited dictionary to file
     */
    public static void writeData(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathDataEdit));
            String[] keys = dict.keySet().toArray(new String[0]);
            for (String key: keys){
                bw.write(key + "`");
                ArrayList<String> meanings = dict.get(key);
                if (meanings != null){
                    if (meanings.size() == 1)
                        bw.write(meanings.get(0)+"\n");
                    else {
                        for (int i = 0; i < meanings.size() - 1; i++) {
                            bw.write(meanings.get(i) + "| ");
                        }
                        bw.write(meanings.get(meanings.size()-1)+"\n");
                    }
                }
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get meaning of word
     * @param str String: word
     * @return String: result
     */
    private ArrayList<String> getDefinition(String str) {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(str.split("\\| ")));
        return result;
    }

    /**
     * Search function
     * @param str word need to search
     * @param flag search by word or meaning
     * @return Arraylist: search result
     */
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

    /**
     * Add new slang word
     * @param word word
     * @param meaning meaning of word
     */
    public void addNewSlangWord(String word, String meaning){
        dict.put(word, new ArrayList<>(List.of(meaning)));
    }

    /**
     * Remove a word
     * @param word word to remove
     * @return boolean: success or fail
     */
    public boolean removeKey(String word){
        if (checkWordExits(word)) {
            dict.remove(word);
            return true;
        }
        return false;
    }

    /**
     * Add new meaning
     * @param word word
     * @param def new meaning
     */
    public void addNewDef(String word, String def){
        ArrayList<String> list = dict.get(word);
        list.add(def);
    }

    /**
     * Delete a meaning
     * @param word word
     * @param def meaning need to delete
     */
    public void deleteDef(String word, String def){
        ArrayList<String> list = dict.get(word);
        list.remove(def);
    }

    /**
     * Check word exist
     * @param word word to check
     * @return boolean
     */
    public boolean checkWordExits(String word){
        return dict.containsKey(word);
    }

    /**
     * Random word
     * @return String
     */
    public String randomWord(){
        Set<String> keySet = dict.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        return randomKey;
    }

    /**
     * Random definition
     * @return String
     */
    public String randomDef(){
        ArrayList<String> meanings = dict.get(randomWord());
        int randIdx = new Random().nextInt(meanings.size());
        return meanings.get(randIdx);
    }

    /**
     * Random definition
     * @return String
     */
    public String randomDef(String word){
        ArrayList<String> meanings = dict.get(word);
        int randIdx = new Random().nextInt(meanings.size());
        return meanings.get(randIdx);
    }

    /**
     * Word for today
     * @return String
     */
    public String wordForToday(){
        Set<String> keySet = dict.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        ArrayList<String> randomValue = dict.get(randomKey);
        return "\t               " + randomKey+ "\n" + toString(randomValue);
    }

    /**
     * Convert Arraylist to String
     * @param str Arraylist
     * @return String
     */
    public String toString(ArrayList<String> str){
        String res = "";
        for (String s : str)
        {
            res += "- " + s + "\n";
        }
        return res;
    }
}
