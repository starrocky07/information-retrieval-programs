/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exp3;

/**
 *
 * @author Student
 */
import java.util.*;
import java.io.*;
public class term {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "D:\\SP172204_Rocky\\experiment_1\\Files\\";
        File filePath = new File("D:\\SP172204_Rocky\\experiment_1\\Files\\");

        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String,List<Integer>> termmap = new HashMap<String, List<Integer>>();

        File[] listfiles = filePath.listFiles();
        String[] word = null;
        for (int i = 0; i < listfiles.length; i++) {
            if (listfiles[i].isFile()) {
                System.out.println("File: " + listfiles[i].getName());

                String newPath = path + listfiles[i].getName();

                //displaying contents of a file
                BufferedReader br = new BufferedReader(new FileReader(newPath));
                String line = null;

                while ((line = br.readLine()) != null) {
                    word = line.toLowerCase().split(" ");
                    List<String> wordList = Arrays.asList(word); //convert array to arrayList
                    words.addAll(wordList); //adding two lists

                }//while

            }//if  
        }//for

        Set<String> unique = new HashSet<>(words);

        System.out.println("\n");

        String text;
        Iterator it2 = unique.iterator();
        while (it2.hasNext()) {
            text = (String) it2.next();
            List<String> index = new ArrayList();
List<Integer> Tindex = new ArrayList();
            for (int i = 0; i < listfiles.length; i++) {
                if (listfiles[i].isFile()) {

                    String newPath = path + listfiles[i].getName();

                    //displaying contents of a file
                    BufferedReader br = new BufferedReader(new FileReader(newPath));
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        word = line.toLowerCase().split(" ");
                        List<String> wordList = Arrays.asList(word); //convert array to arrayList

                        if (wordList.contains(text)) {
                            int n=0;
Iterator fd=wordList.iterator();
while(fd.hasNext())
{
    if(fd.next().equals(text))
    {
        n++;
    }
}
                            index.add(listfiles[i].getName());
                            Tindex.add(n);
                        } //else {
//                           // index.add("");
//                        }

                    }//while

                }//if  
            }//for
            map.put(text, index);
            termmap.put(text,Tindex);
        }
//        System.out.println("Map is: ");
//        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//            String key = entry.getKey();
//            List<String> values = entry.getValue();
//
//            System.out.println("key: " + key);
//            System.out.println("Values: " + values);
//        }
//        System.out.println("");
//        System.out.println("");
//        System.out.println("term-frequency");
//         for (Map.Entry<String, List<Integer>> entry : termmap.entrySet()) {
//            String key = entry.getKey();
//            List<Integer> values = entry.getValue();
//
//            System.out.println("key: " + key);
//            System.out.println("Values: " + values);
//        }
        
        System.out.println("Writing to file successfull");
        try {
            File fileOne = new File("D:\\SP172204_Rocky\\experiment_1\\index_exp3.txt");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(map);
             oos.writeObject(termmap);
            oos.flush();
            oos.close();
            fos.close();
               System.out.println("Writing to file successfull");
        } catch (Exception e) {
        }
        System.out.println("");
        try {
            File toRead = new File("D:\\SP172204_Rocky\\experiment_1\\index_exp3.txt");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, List<String>> mapInFile = (HashMap<String, List<String>>) ois.readObject();
HashMap<String, List<Integer>> mapfi= (HashMap<String, List<Integer>>) ois.readObject();
            ois.close();
            fis.close();
           
             System.out.println("Reading from file successfull");
             System.out.println("document List");
            for (Map.Entry<String, List<String>> m : mapInFile.entrySet()) {
                String key = m.getKey();
                List<String> fvalues = m.getValue();
                System.out.println("key: " + key);
                System.out.println("Values: " + fvalues);
            }
            System.out.println("");
             System.out.println("term-frequency");
             for (Map.Entry<String, List<Integer>> m : mapfi.entrySet()) {
                String key = m.getKey();
                List<Integer> fvalues = m.getValue();
                System.out.println("key: " + key);
                System.out.println("Values: " + fvalues);
            }
        } catch (Exception e) {
        }
        
         System.out.println("-------Query ------------");
        String ds;
        Scanner s = new Scanner(System.in);
        ds = s.nextLine();
        List<String> gs = new ArrayList();
        StringTokenizer tt = new StringTokenizer(ds);
        while (tt.hasMoreTokens()) {
            gs.add(tt.nextToken());
        }
        
      
        Iterator itt = gs.iterator();
//        while(itt.hasNext())
//        {
//            System.out.println(itt.next());
//        }
        HashSet hash = new HashSet();
         
        for (Map.Entry<String, List<String>> entr : map.entrySet()) {
            String key = entr.getKey();
            List<String> fvalues = entr.getValue();
//            System.out.println("key: " + key);
//            System.out.println("Values: " + fvalues);
//           while (itt.hasNext()) {
//                if (key.equals(itt.next())) {
//                    hash.add(fvalues);
//                }
//            }
            if(gs.contains(key))
            {
                hash.addAll(fvalues);
            }
        }
        Iterator i1 = hash.iterator();
        System.out.println("Found in------");
        while (i1.hasNext()) {
            System.out.println(i1.next());
        }

    
    }
   
}
