package ninjagaiden4.helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;

import java.util.function.BiFunction;

public class AttackHelper {
    public static void AoeAction (AbstractPlayer p, AbstractCard card, BiFunction<AbstractMonster, DamageInfo, AbstractGameAction> actionMaker) {
        if (card.multiDamage != null) {
            int i = 0;
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped()) {
                    // 自动组装好对应这个怪物的 DamageInfo
                    DamageInfo info = new DamageInfo(p, card.multiDamage[i], DamageInfo.DamageType.NORMAL);
                    // 执行你传入的动作逻辑
                    AbstractDungeon.actionManager.addToBottom(actionMaker.apply(m, info));
                }
                i++;
            }
        }
    }

    public static void coreAction(AbstractPlayer p, AbstractMonster m, AbstractCard card) {
        if (m != null && !m.isDeadOrEscaped()) {
            DamageInfo info = new DamageInfo(p, card.damage, DamageInfo.DamageType.NORMAL);

            AbstractDungeon.actionManager.addToBottom(new core(m, info, card));
        }
    }
}
