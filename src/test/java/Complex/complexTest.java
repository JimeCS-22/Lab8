package Complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class complexTest {

    @Test
    void test(){

        int[] a = {45, 17 , 23 , 55, 7 };
        System.out.println("The max number: " + util.Utility.maxArray(a));

    }

}