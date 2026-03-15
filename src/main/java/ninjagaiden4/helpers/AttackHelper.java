package ninjagaiden4.helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.modcore.Ninja4;

import java.util.function.BiFunction;

import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;

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

    public static void RandcoreAction(AbstractPlayer p, AbstractMonster m, AbstractCard card) {
        AttackHelper.coreAction(p, m, card);

        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(
                m,    // 传 m，代表“排除玩家选中的这个怪”，确保一定打在另一个怪身上
                true, // 只找活着的
                AbstractDungeon.cardRandomRng
        );

        AttackHelper.coreAction(p, randomMonster, card);
    }

    public static void EXAttackAction(AbstractPlayer p, AbstractMonster m, AbstractCard card,int num,int Damage,int magicnumber, float dismemberRate) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= num+1) {

            card.damage = card.damage + Damage;
            card.magicNumber = card.magicNumber + magicnumber;
            Ninja4.CardFields.dismemberRate.set(card,dismemberRate);

            ModHelper.Loop(0,card.magicNumber, i -> AttackHelper.RandcoreAction(p,m,card));
        }else {
            AttackHelper.coreAction(p,m,card);
        }
    }
}
