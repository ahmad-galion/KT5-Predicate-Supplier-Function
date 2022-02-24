import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


class LazyLoad<T> {
    private T instance;
    private Supplier<T> supplier;
    public LazyLoad(Supplier<T> supplier) {
        this.supplier=supplier;
    }
    public T get() {
        if(instance==null) {
            instance=  supplier.get();
        }
        return  instance;
    }
}


public class MainClass {


    public static void main(String[] args) {
        predicate();
        supplier();
        function();
    }

    private static void function() {

        Function<Integer,Integer> inc = integer ->  integer+1;
        Function<Integer,Integer> doubled= value->value*2;
        printWithFunction(5,"increment",inc);
        printWithFunction(5,"doubled",doubled);
        Function<Integer,Integer> all=inc.andThen(doubled).andThen(e->e-1);
        printWithFunction(5,"increment and doubled",all);
        printWithFunction(5,"set of functions",inc,doubled,e->e-1);
    }

    private static void printWithFunction(int i, String msg, Function<Integer, Integer> inc) {
        System.out.println(i +" " +msg+" "+inc.apply(i));
    }

    private static void printWithFunction(int i,String msg,Function<Integer,Integer>... funs) {
        Function<Integer,Integer> functionResult= integer -> integer;
        for(Function<Integer,Integer> f : funs) {
            functionResult= functionResult.andThen(f);
        }

     /*  functionResult= Stream.of(funs).
                reduce(Function.identity(),Function::andThen);*/

        System.out.println(i +" " +msg+" "+functionResult.apply(i));
    }


    private static void supplier() {

        int n1=6;

 //why to call calculateN2 ? if n1 <6
/**
        int n2= calculateN2();

        if(n1>6 && n2>10) {
            System.out.println("Flow 1 "+ n2);
        }else{
            System.out.println("Flow 2");
        }
*/

//better solution to define Supplier that will call only on get()
   //but do we call the calculateN2 twice? in case n1>6
/**


        Supplier<Integer> n2 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return  calculateN2();
            }
        };

        if(n1>6 && n2.get()>10) {
            System.out.println("Flow 1 "+ n2.get());
        }else{
            System.out.println("Flow 2");
        }
*/


//best solution to use LazyLoad, inorder ro be sure supplier called only once

            var s= new LazyLoad<>(()->calculateN2());
            if(n1>5 && s.get()>10) {
                System.out.println("Flow 1 "+ s.get());
            }else{
                System.out.println("Flow 2");
            }
    }

    public static int calculateN2() {
        System.out.println("count method called");
        return 15;
    }


    private static void predicate() {

        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        System.out.println(totalValues1(list,value -> true));
        System.out.println(totalValues1(list,value->value%2==0));
        System.out.println(totalValues1(list,value->!(value%2==0)));
        System.out.println(totalValues1(list,value->value>=9));

    }

    public static int totalValues1(List<Integer> list, Predicate<Integer> predicate) {

/**
   int count=0;
        for(int i:list) {
            if(predicate.test(i)) count+=i;
        }
        return count;

 */
        //modern way
        return  list.stream().filter(predicate).mapToInt(Integer::valueOf).sum();
    }


}
