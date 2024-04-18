/**
 * Demo of Person201 objects for Compsci 201 @ Duke
 * @author Jack Regan
 * This versions new in Fall 2023
 */
public class Person201Demo {
    public static void main(String[] args) {
        Person201 a = new Person201("claire",37.8044,-122.2712,"courons");
        Person201 b = new Person201("ricardo",-1.2921, 36.8219,"harambee");
        Person201 c = new Person201("julie",40.7081,-73.9571,"help");
        Person201 s = new Person201("Sam", 44.9978, -93.2650, "hello");
        Person201[] people = {a,b,c,s, new Person201()};

        for(Person201 p : people) {
            System.out.println(p);
        }
        System.out.printf("names: ");
        for(Person201 p : people) {
            System.out.print(p.getName()+" ");
        }
        System.out.println();
    }
}
