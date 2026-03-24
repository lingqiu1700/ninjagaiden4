package ninjagaiden4.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import ninjagaiden4.modcore.Ninja4;
import ninjagaiden4.powers.Dismemberment;

public class core extends AbstractGameAction {
    public DamageInfo info;
    public AbstractCard card;

    public core(AbstractMonster target, DamageInfo info, AbstractCard sourceCard) {
        this.target = target;
        this.info = info;
        this.card = sourceCard;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        if (!this.target.isDeadOrEscaped()) {
            float rate = Ninja4.CardFields.dismemberRate.get(this.card);

            if (Math.random() < rate) {
                AbstractDungeon.actionManager.addToTop(
                        new ApplyPowerAction(
                                this.target,
                                AbstractDungeon.player,
                                new Dismemberment(this.target), // 你可以换成自己的 Power
                                2
                        )
                );
            }
        }
        this.isDone = true;
    }
}
