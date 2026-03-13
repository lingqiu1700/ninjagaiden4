package ninjagaiden4.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FlightPower;

public class Flight_Down extends AbstractPower {
    public static final String POWER_ID = "ninjagaiden4:LoseCustomStatPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public Flight_Down(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF; // 这是一个负面效果
        this.isTurnBased = true;

        // 加载图标
        this.loadRegion("flex"); // 借用活动肌肉的图标
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    // 核心逻辑：在回合结束时触发
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        // 让能力图标闪烁一下，给玩家视觉反馈
        this.flash();

        // 1. 扣除对应的属性（这里以力量为例，传负数就是扣除）
        addToBot(new ApplyPowerAction(this.owner, this.owner, new FlightPower(this.owner, -this.amount), -this.amount));

        // 2. 效果触发完了，把自己（这个LosePower）移除掉
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}