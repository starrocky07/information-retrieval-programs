/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exp6;

/**
 *
 * @author Student
 */
import java.io.*;
import java.util.*;
import static java.util.Arrays.asList;

public class soundex {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "D:\\SP172204_Rocky\\experiment_1\\Files\\";
        File filePath = new File("D:\\SP172204_Rocky\\experiment_1\\Files\\");

        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, String> soundex = new HashMap<String, String>();

        File[] listfiles = filePath.listFiles();
        String[] word = null;
        for (int i = 0; i < listfiles.length; i++) {
            if (listfiles[i].isFile()) {
                System.out.println("File: " + listfiles[i].getName());

                String newPath = path + listfiles[i].getName();

                BufferedReader br = new BufferedReader(new FileReader(newPath));
                String line = null;

                while ((line = br.readLine()) != null) {
                    word = line.toUpperCase().replaceAll("\\p{Punct}", "").split(" ");
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
                        word = line.toUpperCase().replaceAll("\\p{Punct}", "").split(" ");
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
            String g = entry.getKey();
            String f = "";
            char it = g.charAt(0);
            String rem = g.substring(1);
            String j = Character.toString(it);
            char gg[] = rem.toCharArray();
            List<String> li = new ArrayList();

            li.add(j);
            for (int i = 0; i < rem.length(); i++) {
                if (gg[i] == 'A' || gg[i] == 'E' || gg[i] == 'I' || gg[i] == 'O' || gg[i] == 'U' || gg[i] == 'W'
                        || gg[i] == 'H' || gg[i] == 'Y') {
                    li.add("0");
                } else if (gg[i] == 'B' || gg[i] == 'F' || gg[i] == 'P' || gg[i] == 'V') {
                    li.add("1");
                } else if (gg[i] == 'C' || gg[i] == 'G' || gg[i] == 'J' || gg[i] == 'K' || gg[i] == 'Q' || gg[i] == 'S' || gg[i] == 'X' || gg[i] == 'Z') {
                    li.add("2");
                } else if (gg[i] == 'D' || gg[i] == 'T') {
                    li.add("3");
                } else if (gg[i] == 'L') {
                    li.add("4");
                } else if (gg[i] == 'M' || gg[i] == 'N') {
                    li.add("5");
                } else if (gg[i] == 'R') {
                    li.add("5");
                }
            }

            
            for (String ff : li) {
                f += ff;
            }

//             char[] s=f.toCharArray();
//             char[]h=null;
//             for(int i=0;i<s.length-1;i++)
//             {
//                 if(s[i]!=s[i+1])
//                 {
//                   h[i]=s[i+1];  
//                 }
//             }
//             String jh=h.toString();
            int i = 0;
            int l = 0;
            char ar[] = new char[f.length()];
            while (i < f.length() - 1) {
                if (f.charAt(i) != f.charAt(i + 1)) {
                    ar[l] = f.charAt(i);
                    i++;
                    l++;
                } else {
                    i++;
                }
            }
            ar[l] = f.charAt(f.length() - 1);
            String jh = "";
            for (char c : ar) {
                //System.out.print(c);
                jh += c;
            }
            String jd="";
//       for(int v=0;v<jh.length();i++)
//       {
//           if(jh.charAt(i)!='0')
//           {
//               jd=jd+jh.charAt(i);
//           }
//       }
           
            String hf = jh.replace("0","");
//            if(hf.length()>4){
//              String ho=hf.substring(0, 3);
//              soundex.put(g, ho);
//            }
//            else if(hf.length()==4)
//            {
//            soundex.put(g, hf);
//            }
//            else if(hf.length()==1)
//            {
//                 hf=hf+"0"+"0"+"0";
//               soundex.put(g, hf+"000");  
//            }
//            else if(hf.length()==2)
//            {
//                hf=hf+"0"+"0";
//               soundex.put(g, hf);  
//            }
//            else if(hf.length()==3)
//            {
//                 hf=hf+"0";
               soundex.put(g, hf);  
           // }
        }

        for (Map.Entry<String, List<String>> m : map.entrySet()) {
            String key = m.getKey();
            List<String> fvalues = m.getValue();
            System.out.println("key: " + key);
            System.out.println("Values: " + fvalues);
        }

        System.out.println("\n\nSoundex\n\n\n");
        for (Map.Entry<String, String> m : soundex.entrySet()) {
            String key = m.getKey();
            String s = m.getValue();
            System.out.println("key: " + key + "\t\tCode: " + s);
        }

        System.out.println("-------Query ------------");
        String ds;
        Scanner s = new Scanner(System.in);
        ds = s.nextLine();

        List<String> fd = new ArrayList();
        StringTokenizer tt = new StringTokenizer(ds);
        while (tt.hasMoreTokens()) {
            List<String> gs = new ArrayList();

            String g = tt.nextToken().toUpperCase();
            String f = "";
            char it = g.charAt(0);
            String rem = g.substring(1);
            String j = Character.toString(it);
            char gg[] = rem.toCharArray();
            List<String> li = new ArrayList();

            li.add(j);
            for (int i = 0; i < rem.length(); i++) {
                if (gg[i] == 'A' || gg[i] == 'E' || gg[i] == 'I' || gg[i] == 'O' || gg[i] == 'U' || gg[i] == 'W'
                        || gg[i] == 'H' || gg[i] == 'Y') {
                    li.add("0");
                } else if (gg[i] == 'B' || gg[i] == 'F' || gg[i] == 'P' || gg[i] == 'V') {
                    li.add("1");
                } else if (gg[i] == 'C' || gg[i] == 'G' || gg[i] == 'J' || gg[i] == 'K' || gg[i] == 'Q' || gg[i] == 'S' || gg[i] == 'X' || gg[i] == 'Z') {
                    li.add("2");
                } else if (gg[i] == 'D' || gg[i] == 'T') {
                    li.add("3");
                } else if (gg[i] == 'L') {
                    li.add("4");
                } else if (gg[i] == 'M' || gg[i] == 'N') {
                    li.add("5");
                } else if (gg[i] == 'R') {
                    li.add("5");
                }
            }
         //   f=li.toString();
            //f= String.join(", ", li);

            for (String ff : li) {
                f += ff;
            }
             int i = 0;
            int l = 0;
            char ar[] = new char[f.length()];
            while (i < f.length() - 1) {
                if (f.charAt(i) != f.charAt(i + 1)) {
                    ar[l] = f.charAt(i);
                    i++;
                    l++;
                } else {
                    i++;
                }
            }
            ar[l] = f.charAt(f.length() - 1);
            String jh = "";
            for (char c : ar) {
                //System.out.print(c);
                jh += c;
            }
            String jd="";
//       for(int v=0;v<jh.length();i++)
//       {
//           if(jh.charAt(i)!='0')
//           {
//               jd=jd+jh.charAt(i);
//           }
//       }
           
            String hf = jh.replace("0","");
            fd.add(hf);
        }

        Iterator itt = fd.iterator();

        HashSet hash = new HashSet();
        HashSet hash2 = new HashSet();
        Iterator iw = fd.iterator();
        while (iw.hasNext()) {
            String da = (String) iw.next();
            System.out.println("\n\nSoundex Code: " + da);

            for (Map.Entry<String, String> entr : soundex.entrySet()) {

                String key = entr.getKey();
                String fvalues = entr.getValue();

                if (da.equals(fvalues)) {
                    hash.add(key);
                }
            }
        }

//              Iterator i = hash.iterator();
//           while(i.hasNext()){
//               String d=(String) i.next();
        for (Map.Entry<String, List<String>> entr : map.entrySet()) {

            String key = entr.getKey();
            List<String> fvalues = entr.getValue();

            if (hash.contains(key)) {
                //  hash2.addall(fvalues);
                hash2.addAll(fvalues);
            }

        }
        Iterator i1 = hash2.iterator();
        System.out.println("Found in------");
        while (i1.hasNext()) {
            System.out.println(i1.next());
        }
    }

}
