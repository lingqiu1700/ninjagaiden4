package ninjagaiden4.modcards.Magatsuhi.Blood.Derivative;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Dragon_Head_Orbit_Derivative extends AbstractNinjaAttackCard {
    public static String ID = ModHelper.makeID("Dragon_Head_Orbit_Derivative");
    private int targetCombo = 0;

    public Dragon_Head_Orbit_Derivative() {
        super(
                ID,
                "Dragon_Head_Orbit_Derivative",
                2,
                CardRarity.SPECIAL,
                CardTarget.ENEMY,
                8,
                1,
                0.1F,
                2,
                0
        );
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        ModHelper.randomCombo(this,this.targetCombo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.EXAttackAction(p,m,this,this.targetCombo,10,1,0.25F);

        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= this.targetCombo+1) {
            ModHelper.PAP(new FlightPower(p,1));
        }

        this.targetCombo = 0;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (ModHelper.hikari(this,this.targetCombo)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
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
