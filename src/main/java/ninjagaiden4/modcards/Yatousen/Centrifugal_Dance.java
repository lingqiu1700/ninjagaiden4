package ninjagaiden4.modcards.Yatousen;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Centrifugal_Dance extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Centrifugal_Dance");

    public Centrifugal_Dance() {
        super(
                ID,
                "Centrifugal_Dance",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                4,
                4,
                0.15F,
                2,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));
    }
}
