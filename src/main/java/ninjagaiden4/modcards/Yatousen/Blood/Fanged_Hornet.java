package ninjagaiden4.modcards.Yatousen.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.sun.org.apache.xpath.internal.operations.Mod;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Fanged_Hornet extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Fanged_Hornet");

    public Fanged_Hornet() {
        super(
                ID,
                "Fanged_Hornet",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                18,
                1,
                0.09F,
                -13,
                6
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));
        if (Math.random()<0.7) {
            addToBot(new StunMonsterAction(m,p));
        }
    }
}
