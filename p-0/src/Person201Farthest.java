public class Person201Farthest {
    public static void main(String[] args) throws Exception {
        String file = "data/large.txt";
        double max = 0;
        Person201 a = null;
        Person201 b = null;
        Person201[] people = Person201Utilities.readFile(file);
        for (Person201 list1: people) {
            for (Person201 list2: people) {
                if (list1.distanceFrom(list2) > max) {
                    max = list1.distanceFrom(list2);
                    a = list1;
                    b = list2;
                }
            }
        }
        System.out.printf("farthest distance is %3.2f between %s and %s\n",max,a.getName(),b.getName());
    }
}
