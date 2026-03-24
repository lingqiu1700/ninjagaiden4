package ninjagaiden4.modcards.Magatsuhi.Blood.Derivative;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Uppercrush_Strikes_Derivative extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Uppercrush_Strikes_Derivative");
    private int targetCombo = 0;

    public Uppercrush_Strikes_Derivative() {
        super(
                ID,
                "Uppercrush_Strikes_Derivative",
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
    public void applyPowers() {
        super.applyPowers();

        // AbstractDungeon.isPlayerInDungeon() 防止你在主菜单查看图鉴时因为找不到随机数生成器而游戏崩溃
        if (this.targetCombo == 0 && AbstractDungeon.isPlayerInDungeon()) {
            // 给这把武器赋一个 1 到 5 的随机连击要求
            this.targetCombo = AbstractDungeon.cardRandomRng.random(1, 5);
        }

        // 动态更新牌面文字，让玩家一眼就能看到需要把这张牌作为第几张打出
        if (this.targetCombo > 0) {
            this.rawDescription = "造成 !D! 点伤害。 NL 完美连击：如果这是你本回合打出的第 [ #y" + this.targetCombo + " ] 张牌，则造成 3 倍伤害。";
            this.initializeDescription();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (ModHelper.hikari(this,this.targetCombo)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.EXAttackAction(p,m,this,this.targetCombo,10,0,0.3F);

        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= this.targetCombo+1) {
            addToBot(new StunMonsterAction(m,p));
        }

        this.targetCombo = 0;
    }

    @Override
    public void triggerOnManualDiscard() {
        this.targetCombo = 0;
    }

    @Override
    public void onMoveToDiscard() {
        this.targetCombo = 0;
    }
}
