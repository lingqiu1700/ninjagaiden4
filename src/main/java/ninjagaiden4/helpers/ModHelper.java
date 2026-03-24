package ninjagaiden4.helpers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

    static void MakeCardAction(AbstractCard c, int amount) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(
                c,amount,false,true,true
        ));
    }

    static boolean hikari (AbstractCard c ,int num) {
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= num;
    }

    static int randomCombo(AbstractCard c, int targetCombo) {

        // 如果连击数是 0 且在战斗中，随机生成 1 到 5
        if (targetCombo == 0 && AbstractDungeon.isPlayerInDungeon()) {
            targetCombo = AbstractDungeon.cardRandomRng.random(1, 5);
        }

        // 注意这里，改成了 != 0（只要有了连击数，就更新描述）
        if (targetCombo != 0) {
            // 直接修改这张具体卡牌的原始描述
            c.rawDescription = "造成 !D! 点伤害。 NL 完美连击：如果这是你本回合打出的第 [ #y" + targetCombo + " ] 张牌，则造成 额外 伤害。";

            // 呼叫这张具体卡牌去刷新它的描述排版
            c.initializeDescription();
        }

        // 把最终的连击数返回出去，让卡牌知道自己到底随机到了几
        return targetCombo;
    }
}

