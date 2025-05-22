import java.util.HashMap;

public class stringpractice
{
    public static void main(String[] args)
    {
        //findsequenceofcharactersinastring("sai rama krishna%$$^&*");
        //sequencingcharactersnotsymbolsorspaces("sai# rt% ghyijkhhhgchgf9090909");
        reversekeepingsomepartofstringsame("I am a programmer in java","java");
    }

    public  static  void sequencingcharactersnotsymbolsorspaces(String word)
    {
        String input = "Java Programming 101!!!";

        // Convert to lowercase for case-insensitivity
        input = input.toLowerCase();

        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            // Only count letters and digits
            if (Character.isLetterOrDigit(c)) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
        }

        // Display results
        for (char c : charCountMap.keySet()) {
            System.out.println(c + ": " + charCountMap.get(c));
        }
    }

    public static void reversekeepingsomepartofstringsame(String stringtoreverse,String fixed)
    {
        String s = stringtoreverse;
        String cons = fixed;

        int length = s.length();
        String[] values = s.split(" ");
        StringBuilder sb =  new StringBuilder();
        for(String su : values)
        {
            if(su.equals(fixed))
            {
                sb.append(su);
            }
            else
            {
                sb.append(new StringBuilder(su).reverse().toString());
            }

            sb.append(" ");
        }

        System.out.println(sb.toString());
    }

    public static void findsequenceofcharactersinastring(String word)
    {
         char[] ch = word.toCharArray();
        System.out.println(ch);
        HashMap<Character,Integer> hp = new HashMap<Character,Integer>();
         for(char c : ch)
         {
             if(c==' ')
             {
                 continue;
             }

//Character.isLetterOrDigit(c)

            else if(hp.containsKey(c))
            {
                hp.put(c,hp.get(c)+1);
            }

            else
            {
                hp.put(c,1);
            }
         }

         String unique = "";
         //for giving unique characters only
         for (char c : hp.keySet())
         {
             if(hp.getOrDefault(c,0)==1)
             {
                 unique = unique+c;
             }
         }

        System.out.println(hp);
        System.out.println("unique value is :" + unique);
    }
}
