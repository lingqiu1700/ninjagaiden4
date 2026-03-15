package ninjagaiden4.modcards.Yatousen.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Devilish_Mole extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Devilish_Mole");

    public Devilish_Mole() {
        super(
                ID,
                "Devilish_Mole",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                2,
                10,
                0.06F,
                1,
                2
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0, this.magicNumber, i -> AttackHelper.coreAction(p, m, this));
        if (Math.random() < 0.6) {
            addToBot(new StunMonsterAction(m, p));
        }
        ModHelper.PAP(new FlightPower(p,1));
    }
}
