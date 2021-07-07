import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Problems {

    /*დაწერეთ ფუნქცია, რომელსაც გადაეცემა ტექსტი  და აბრუნებს პალინდრომია თუ არა.
    (პალინდრომი არის ტექსტი რომელიც ერთნაირად იკითხება ორივე მხრიდან).
     */

    private static Boolean isPalindrome(String text){
        if(text.length() <= 1) return true;
        if(text.charAt(0) == text.charAt(text.length() - 1))
            return isPalindrome(text.substring(1,text.length() - 1));
        return false;
    }

    /*
    გვაქვს 1,5,10,20 და 50 თეთრიანი მონეტები. დაწერეთ ფუნქცია, რომელსაც გადაეცემა
    თანხა (თეთრებში) და აბრუნებს მონეტების მინიმალურ რაოდენობას, რომლითაც შეგვიძლია ეს თანხა დავახურდაოთ.
     */
    private static int minSplit(int amount){
        ArrayList<Integer> money = new ArrayList<>();
        money.add(50);
        money.add(20);
        money.add(10);
        money.add(5);
        money.add(1);
        int count = 0;
        for(int i = 0; i < money.size();){
            if(amount - money.get(i) == 0){
                count++;
                return count;
            } else if(amount - money.get(i) > 0){
                count++;
                amount -= money.get(i);
            } else {
                i++;
            }
        }
        return count;
    }



   /* მოცემულია მასივი, რომელიც შედგება მთელი რიცხვებისგან.
   დაწერეთ ფუნქცია რომელსაც გადაეცემა ეს მასივი და აბრუნებს მინიმალურ მთელ რიცხვს,
   რომელიც 0-ზე მეტია და ამ მასივში არ შედის.
    */

    // 1 4 5 7 9 12
    private int notContains(int[] array){
        Arrays.sort(array);
        for(int i = 0; i < array.length - 1; i++){
            if(array[i + 1] - array[i] > 1 && array[i] + 1 > 0)
                return array[i] + 1;
        }
        if(array[array.length - 1] + 1 > 0)
            return array[array.length - 1] + 1;
        return 1;
    }

  /* მოცემულია String რომელიც შედგება „(„ და „)“ ელემენტებისგან. დაწერეთ ფუნქცია რომელიც აბრუნებს
  ფრჩხილები არის თუ არა მათემატიკურად სწორად დასმული.
   */

    private static Boolean isProperly(String sequence){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < sequence.length(); i++){
            if(sequence.charAt(i) == ')' && stack.empty())
                return false;
            if(sequence.charAt(i) == ')' && stack.peek() == '(') stack.pop();
            if(sequence.charAt(i) == '(') stack.push('(');
        }
        return stack.empty();
    }

    //მაგ: (()()) სწორი მიმდევრობაა,  ())() არასწორია

    /*გვაქვს n სართულიანი კიბე, ერთ მოქმედებაში შეგვიძლია ავიდეთ 1 ან 2 საფეხურით.
    დაწერეთ ფუნქცია რომელიც დაითვლის n სართულზე ასვლის ვარიანტების რაოდენობას.
    * */
    private static int countVariants(int stearsCount){
        if(stearsCount == 0) return 1;
        if(stearsCount < 0) return 0;
        return countVariants(stearsCount - 1) + countVariants(stearsCount - 2);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abbacabba"));
        System.out.println(countVariants(4));
        System.out.println(isProperly("()()(())"));
        System.out.println(minSplit(10));
    }
}
