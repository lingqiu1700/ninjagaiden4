package ninjagaiden4.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.powers.Dismemberment;

public class OT extends AbstractGameAction {
    private AbstractMonster target;
    private int executeDamage;

    public OT(AbstractMonster target) {
        this.target = target;
        this.executeDamage = executeDamage;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {

        // 1. 必须有断肢状态才能处决
        if (target.hasPower(Dismemberment.POWER_ID)) {

            // 2. 获取当前生命 + 当前格挡
            int total = target.currentHealth + target.currentBlock;

            // 3. 造成处决伤害（HP_LOSS 无视格挡）
            DamageInfo info = new DamageInfo(
                    AbstractDungeon.player,
                    total,
                    DamageInfo.DamageType.HP_LOSS
            );

            target.damage(info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                    && !this.target.hasPower("Minion")) {
                this.addToTop(new DrawCardAction(1));
                this.addToTop(new HealAction(AbstractDungeon.player,AbstractDungeon.player,2));
                this.addToTop(new GainEnergyAction(2));
            }

        } else {
            System.out.println("敌人没有断肢状态，无法处决");
        }

        this.isDone = true;
    }
}

