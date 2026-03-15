package ninjagaiden4.modcards.Magatsuhi;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Aftershock_Dance extends AbstractNinjaAttackCard {
    public static final String ID = "Aftershock_Dance";

    public Aftershock_Dance() {
        super(
                ID,
                "Aftershock_Dance",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                2,
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
    }
}
