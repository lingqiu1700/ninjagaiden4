package ninjagaiden4.modcards.Magatsuhi;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Tremorous_Pummel extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Tremorous_Pummel");

    public Tremorous_Pummel() {
        super(
                ID,
                "Tremorous_Pummel",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                8,
                2,
                0.15F,
                2,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber, i -> {
            AttackHelper.coreAction(p,m,this);
            ModHelper.PAP(new FlightPower(p,1));
            ModHelper.PAP(new StunMonsterPower(m,1));
        });
    }
}
