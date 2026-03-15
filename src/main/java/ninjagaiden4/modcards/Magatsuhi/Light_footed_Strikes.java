package ninjagaiden4.modcards.Magatsuhi;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Light_footed_Strikes extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Light_footed_Strikes");

    public Light_footed_Strikes() {
        super(
                ID,
                "Light_footed_Strikes",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                6,
                5,
                0.05F,
                2,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> {AttackHelper.RandcoreAction(p,m,this);});
    }
}
