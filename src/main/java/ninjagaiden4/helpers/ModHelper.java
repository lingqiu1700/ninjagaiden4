package ninjagaiden4.helpers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.function.IntConsumer;

public interface ModHelper {
    String MOD_ID = "ninjagaiden4";

    static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    static void PAP (AbstractPower  power){
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        power.owner,
                        AbstractDungeon.player,
                        power,
                        power.amount
                )
        );
    }

    static void Loop (int start, int end, IntConsumer body) {
        for (int i = start; i < end; i++) {
            body.accept(i);
        }
    }
}

