package com.test;

import com.test.domain.Round;
import com.test.domain.RoundEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Created by Ravanys on 05/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundEntityTests {

    @Test
    public void testFirstEqualToSecond(){
        RoundEntity first = new RoundEntity("first",0,0,0);
        RoundEntity second = new RoundEntity("first",0,0,0);
        assertTrue(first.equals(second));
    }
    @Test
    public void testFirstUnEqualToSecond(){
        RoundEntity first = new RoundEntity("first",0,0,0);
        RoundEntity second = new RoundEntity("second",0,0,0);
        assertFalse(first.equals(second));
    }



}
