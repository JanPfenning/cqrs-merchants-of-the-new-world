package xyz.game.merchants.domain.roll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RollFactoryTest {
    @Test
    public void shouldCreateRandomRollsWhereEveryDiceIsValidD6() throws Exception {
        RollFactory rf = new RollFactory((long) Math.random()*10_000);
        Map<Integer, Boolean> producedValues = new HashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            Roll r = rf.createRoll();
            producedValues.put(r.getFirstD6(), true);
            producedValues.put(r.getSecondD6(), true);
            // boolean firstDiceValid = r.getFirstD6()>=1 && r.getFirstD6()<=6;
            // boolean secondDiceValid = r.getSecondD6()>=1 && r.getSecondD6()<=6;
            // assertTrue("both dice should be a valid d6", firstDiceValid && secondDiceValid);
        }
        for (int i = 1; i <= 6; i++) {
            assertTrue(producedValues.get(i));
        }
        Optional<Integer> max = producedValues.keySet().stream().max(Integer::compareTo);
        if(max.isPresent()){
            assertEquals(max.get().intValue(), 6);
        }else{
            throw new Exception("producedValues was empty");
        }
    }

    @Test
    public void shouldCreateReproducableRollsDueToSeed() {
        RollFactory rf = new RollFactory((long) 10);
        RollFactory rf2 = new RollFactory((long) 10);
        for (int i = 0; i < 10_000; i++) {
            assertEquals(rf.createRoll(), rf2.createRoll());
        }
    }

    @Test
    public void shouldCreateDifferentResultsWithDifferentSeeds() {
        RollFactory rf = new RollFactory((long) 10);
        RollFactory rf2 = new RollFactory((long) 11);
        for (int i = 0; i < 10; i++) {
            assertNotEquals(rf.createRoll(), rf2.createRoll());
        }
    }
    
    @Test
    public void shouldCreateStagedRolls() {
        RollFactory rf = new RollFactory((long) 10);
        int n = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                n++;        
                final int p = i;
                final int q = j;
                assertDoesNotThrow(() -> rf.createStagedRoll(p, q));
            }
        }
        assertEquals(n, 36);
    }

    @Test
    public void shouldThrowOnCreatingNonD6StagedRolls() {
        RollFactory rf = new RollFactory((long) 10);
        int[][] testCases = new int[][]{
            new int[]{0,1},
            new int[]{0,2},
            new int[]{1,7},
            new int[]{-1,2},
            new int[]{0,7},
            new int[]{2,8}
        };
        for (int[] testCase : testCases) {
            assertThrows(InvalidRollException.class, () -> rf.createStagedRoll(testCase[0], testCase[1]));
            assertThrows(InvalidRollException.class, () -> rf.createStagedRoll(testCase[1], testCase[0]));
        }
    }
}
