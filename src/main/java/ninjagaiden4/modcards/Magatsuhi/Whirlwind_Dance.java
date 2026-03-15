package ninjagaiden4.modcards.Magatsuhi;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Whirlwind_Dance extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Whirlwind_Dance");

    public Whirlwind_Dance() {
        super(
                ID,
                "Whirlwind_Dance",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                3,
                4,
                0.04F,
                1,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber, i -> {
            AttackHelper.RandcoreAction(p,m,this);
        });

        ModHelper.PAP(new FlightPower(p,1));
    }
}
