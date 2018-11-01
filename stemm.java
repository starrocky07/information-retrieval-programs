/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exp4;

/**
 *
 * @author Student
 */
import java.io.*;
import java.util.*;
public class stemm {
    public static void main(String[] args) throws FileNotFoundException, IOException {
         String path = "D:\\SP172204_Rocky\\experiment_1\\Files\\";
        File filePath = new File("D:\\SP172204_Rocky\\experiment_1\\Files\\");

        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String,List<String>> stemmap = new HashMap<String, List<String>>();

        File[] listfiles = filePath.listFiles();
        String[] word = null;
        for (int i = 0; i < listfiles.length; i++) {
            if (listfiles[i].isFile()) {
                System.out.println("File: " + listfiles[i].getName());

                String newPath = path + listfiles[i].getName();

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
     
                            index.add(listfiles[i].getName());
                            
                        } 
                    }

                } 
            }
            map.put(text, index);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String keys = entry.getKey();
            List<String> svalues = entry.getValue();
Stack<Character> s = new Stack<Character>();
            char gg[] = keys.toCharArray();
            for (int f = 0; f < gg.length; f++) {
                s.add(gg[f]);
            }
           if (s.peek() == 's') {
               char ss= s.pop();
               if(s.peek()=='s')
                {
                    s.push(ss);
                }
                if (s.peek() == 'e'&&s.peek()!='s') {
                    char d = s.pop();
                    if (s.peek() != 's') {
                        if (s.peek() != 'i') {
                            s.push(d);
                        } else {
                        }
                    }
                }

            }
            String d = "";
            Stack<Character> ds = new Stack<Character>();
            while (!s.empty()) {
                ds.add(s.pop());
            }
            while (!ds.empty()) {
                d = d + ds.pop();
            }
            //System.out.println("strng------" + d);
            stemmap.put(d, svalues);

        }
//    for (Map.Entry<String, List<String>> entr : stemmap.entrySet()) {
//            String key = entr.getKey();
//            List<String> fvalues = entr.getValue();
//            System.out.println("key: " + key);
//            System.out.println("Values: " + fvalues);
//        
//
//        }
        
        System.out.println("Writing to file successfull");
        try {
            File fileOne = new File("D:\\SP172204_Rocky\\experiment_1\\exp4_index\\index.txt");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(stemmap);
            oos.flush();
            oos.close();
            fos.close();
               System.out.println("Writing to file successfull");
        } catch (Exception e) {
        }
        
        
        System.out.println("");
        try {
            File toRead = new File("D:\\SP172204_Rocky\\experiment_1\\exp4_index\\index.txt");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, List<String>> mapInFile = (HashMap<String, List<String>>) ois.readObject();

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
          
        } catch (Exception e) {
        }
        
         System.out.println("-------Query ------------");
        String ds;
        Scanner s = new Scanner(System.in);
        ds = s.nextLine();
        List<String> gs = new ArrayList();
        StringTokenizer tt = new StringTokenizer(ds);
        while (tt.hasMoreTokens()) {
            
         //   gs.add(tt.nextToken());
            
                     Stack<Character> ss = new Stack<Character>();
             char gg[] = tt.nextToken().toCharArray();
            for (int f = 0; f < gg.length; f++) {
                ss.add(gg[f]);
            }
               if (ss.peek() == 's') {
               char sss= ss.pop();
               if(ss.peek()=='s')
                {
                    ss.push(sss);
                }
                if (ss.peek() == 'e'&&ss.peek()!='s') {
                    char d = ss.pop();
                    if (ss.peek() != 's') {
                        if (ss.peek() != 'i') {
                            ss.push(d);
                        } else {
                        }
                    }
                }

            }
                  
            String d = "";
            Stack<Character> dss = new Stack<Character>();
            while (!ss.empty()) {
                dss.add(ss.pop());
            }
            while (!dss.empty()) {
                d = d + dss.pop();
            }
             gs.add(d);
        }
        
      
        Iterator itt = gs.iterator();
        System.out.println("Stemmed query keywords");
        while(itt.hasNext())
        {
            System.out.println(itt.next());
        }
        HashSet hash = new HashSet();
         
        for (Map.Entry<String, List<String>> entr : stemmap.entrySet()) {
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
 

