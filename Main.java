import java.util.StringJoiner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String input = "I am an automatoin tester";
        String text = "Iamsairamakrishna";
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        StringJoiner sj = new StringJoiner("+","(",")");
        sj.add("sai");
        sj.add("Rama");
        sj.add("krishna");
        System.out.println(sj);
        System.out.println(text.subSequence(1,8));
        System.out.println(text.substring(1,8));

        String sub = text.substring(0,3);
        CharSequence subs = text.subSequence(0,3);
        System.out.println(sub);
        System.out.println(subs);

        for (String word : words) {
            if (word.equals("tester")) {
                result.append(word);  // Keep "tester" unchanged
            } else {
                result.append(new StringBuilder(word).reverse());
            }
            result.append(" ");
        }

        // Remove the trailing space
        System.out.println(result.toString().trim());


    //converting  string to integer and integer to string
    String str1 = "1296";
    int  i= Integer.parseInt(str1);
     System.out.println(i);
    String str2 = Integer.toString(i);
     System.out.println(str2);

     //converting string to string builder
        String strs[] = {"Scaler", "by", "InterviewBit!"};
        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);
        sb.append(" "+strs[1]);
        sb.append(" "+strs[2]);
        System.out.println(sb.toString());

}

}