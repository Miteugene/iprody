package lesson8;

public class Lesson8 {
    public static void main(String[] args) {
        firstTask();
        System.out.println("\n===========================================");
        secondTask();
        System.out.println("\n===========================================");
        thirdTask();
        System.out.println("\n===========================================");
        fourthTask();
        System.out.println("\n===========================================");
        fifthTask();
    }

    private static void firstTask() {
        String source = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        char ch = 'o';
        int occurrences = StringWorks.findSymbolOccurrence(source, ch);
        // Ожидаем 4
        System.out.printf("Source: %s\nChar: %c\nOccurrences: %d", source, ch, occurrences);
    }

    private static void secondTask() {
        String source = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        String target = "dolor";
        String target2 = "dolor2";
        // Ожидаем 12
        System.out.printf(
                "Source: %s\nTarget: %s\nIndex: %d\nIndex using indexOf: %d\n",
                source,
                target,
                StringWorks.findWordPosition(source, target),
                source.indexOf(target)
        );
        // Ожидаем -1
        System.out.printf(
                "Target: %s\nIndex: %d\nIndex using indexOf: %d",
                target2,
                StringWorks.findWordPosition(source, target2),
                source.indexOf(target2)
        );
    }

    private static void thirdTask() {
        String source = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        System.out.printf(
                "Target: %s\nReversed: %s",
                source,
                StringWorks.stringReverse(source)
        );
    }

    private static void fourthTask() {
        String source = "Hello";
        System.out.printf(
                "Target: %s\nIs palindrome: %s\n",
                source,
                StringWorks.isPalindrome(source)
        );

        source = "EReRE";
        System.out.printf(
                "Target: %s\nIs palindrome: %s",
                source,
                StringWorks.isPalindrome(source)
        );
    }

    private static void fifthTask() {
        WordGuesser.start();
    }

}
