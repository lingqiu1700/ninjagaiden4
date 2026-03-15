package ninjagaiden4.modcards.Yatousen.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Propulsion_Impact extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Propulsion_Impact");

    public Propulsion_Impact() {
        super(
                ID,
                "Propulsion_Impact",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                20,
                1,
                0.3F,
                5,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.PAP(new DexterityPower(p,4));
        ModHelper.PAP(new PhantasmalPower(p,1));
        ModHelper.PAP(new BlurPower(p,1));
        AttackHelper.coreAction(p,m,this);
    }
}
