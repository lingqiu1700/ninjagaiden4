package ninjagaiden4.modcards.Magatsuhi.Blood.Derivative;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;

public class Dragon_Trial_Orbit_Derivative extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Dragon_Trial_Orbit_Derivative");
    private int targetCombo = 0;

    public Dragon_Trial_Orbit_Derivative() {
        super(
                ID,
                "Dragon_Trial_Orbit_Derivative",
                2,
                CardRarity.SPECIAL,
                CardTarget.ALL_ENEMY,
                15,
                1,
                0.1F,
                0,
                0
        );
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        ModHelper.randomCombo(this, targetCombo);
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

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.EXAoeAttackAction(p,this,this.targetCombo,10,1,0.5F);
    }
}
