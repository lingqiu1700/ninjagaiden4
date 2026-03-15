package ninjagaiden4.modcards.Magatsuhi.Blood.Derivative;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;

public class Earth_Shatter_Derivative extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Earth_Shatter_Derivative");

    public Earth_Shatter_Derivative() {
        super(
                ID,
                "Earth_Shatter_Derivative",
                2,
                CardRarity.SPECIAL,
                CardTarget.ENEMY,
                8,
                2,
                0.1F,
                3,
                0
        );
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 3) {

            this.damage = this.damage + 2;
            this.magicNumber = this.magicNumber + 3;
            dismemberRate.set(this,0.25F);

            ModHelper.Loop(0,this.magicNumber, i -> AttackHelper.RandcoreAction(p,m,this));
        }else {
            AttackHelper.coreAction(p,m,this);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy(); // 默认发蓝光

        /* * 发光检测是在卡牌还在手牌里时触发的，此时这张牌【还没有】被打出。
         * 所以这里只需要判断前面的牌是否已经够 2 张了。
         */
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 2) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy(); // 满足条件发金光
        }
    }
}
