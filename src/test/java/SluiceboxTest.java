import edu.io.player.NoTool;
import edu.io.player.Player;
import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.SluiceboxToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SluiceboxTest {
    @Test
    void default_properties_are_correct() {
        var sluicebox = new SluiceboxToken();
        Assertions.assertEquals(1.2, sluicebox.gainFactor());
        Assertions.assertEquals(5, sluicebox.durability());
    }

    @Test
    void use_decrements_durability() {
        var sluicebox = new SluiceboxToken();
        int initialDur = sluicebox.durability();
        sluicebox.use();
        Assertions.assertEquals(initialDur - 1, sluicebox.durability());
    }

    @Test
    void use_decrements_gainFactor_by_0_04() {
        var sluicebox = new SluiceboxToken();
        double initialGain = sluicebox.gainFactor();
        sluicebox.use();
        Assertions.assertEquals(initialGain - 0.04, sluicebox.gainFactor());
    }

    @Test
    void sluicebox_is_removed_after_5_uses() {
        var player = new Player();
        var sluicebox = new SluiceboxToken();
        var goldToken = new GoldToken(1.0);

        player.interactWithToken(sluicebox);

        for (int i = 0; i < 5; i++) {
            player.interactWithToken(goldToken);
        }

        Assertions.assertTrue(sluicebox.isBroken());
        player.interactWithToken(goldToken);
        Assertions.assertTrue(player.shed.getTool() instanceof NoTool);
    }

    @Test
    void anvil_does_not_repair_sluicebox() {
        var player = new Player();
        var sluicebox = new SluiceboxToken();
        var anvil = new AnvilToken();

        for (int i = 0; i < 5; i++) {
            sluicebox.use();
        }

        player.interactWithToken(sluicebox);
        player.interactWithToken(anvil);

        Assertions.assertEquals(0, sluicebox.durability());
        Assertions.assertEquals(1.0, sluicebox.gainFactor());
    }
}